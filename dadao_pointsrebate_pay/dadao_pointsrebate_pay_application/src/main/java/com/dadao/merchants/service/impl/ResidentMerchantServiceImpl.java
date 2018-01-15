package com.dadao.merchants.service.impl;


import com.dadao.merchants.entity.MerchantsInfo;
import com.dadao.merchants.mapper.MerchantsInfoMapper;
import com.dadao.merchants.service.ResidentMerchantService;

import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserInfo;
import com.dadao.utils.DaDaoUtil;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import com.dadao.yop.service.YeepayService;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YunQiang on 2017/11/9
 *
 * @author YunQiang
 */
@Service
public class ResidentMerchantServiceImpl implements ResidentMerchantService {

    @Autowired
    private MerchantsInfoMapper merchantsInfoMapper;

    private static final String PARENT_MERCHANT_NO = "10017920417";


    @Override
    public Result personRegInfoAdd(MerchantsInfo merchantsInfo) {
        //佣金率不能为空
        if (merchantsInfo.getCommissionRate() != null && "".equals(merchantsInfo.getCommissionRate().trim())) {
            return new Result(ResultCode.INPUT_PARAMS_FAIL, "佣金率commissionRate不能为空");
        }
        //保存请求号
        merchantsInfo.setRequestNo(DaDaoUtil.generatePersonageRquestNo());
        //保存父商编
        merchantsInfo.setParentMerchantNo(PARENT_MERCHANT_NO);
        //选择个人注册url
        String url = YeepayService.getUrl(YeepayService.PERSON_URL);
        //写入回调地址
        merchantsInfo.setNotifyUrl(YeepayService.getRegisterCallbackUrl());
        //替换佣金率并写入productInfo
        merchantsInfo.setProductInfo("");
        merchantsInfo.setBusinessFunction("{\"SUBACCOUNT_IS_OPENED\": [ \"YES\" ],\"ACCESS_TYPE\": [ \"ACCESS_AHEAD\" ]}");
        //将实体类转为map,适应易宝
        //将授权类型写死（短信授权）
        merchantsInfo.setMerAuthorizeType("SMS_AUTHORIZE");
        HashMap<String, String> params = DaDaoUtil.objToMapStr(merchantsInfo);
        Map response = YeepayService.requestYOP(params, url, YeepayService.PERSON);
        //如果易宝入驻成功，则将入驻信息写入大道数据库
        if (response.get("returnCode").equals("REG00000")) {
            //商户类型（0个人、1个体、2企业）
            merchantsInfo.setMerType("0");
            //写入协议
            merchantsInfo.setAgreementContent((String) response.get("agreementContent"));
            merchantsInfo.setExternalId((String) response.get("externalId"));
            merchantsInfo.setMerLegalEmail(merchantsInfo.getMerContactEmail());
            merchantsInfoMapper.insertSelective(merchantsInfo);
        }
        //获得响应码
        return DaDaoUtil.YopResult2DadaoResult(response);
    }

    /**
     * 个体商户注册
     *
     * @param merchantsInfo
     * @return
     */
    @Override
    @Transactional
    public Result individualReginfoAdd(MerchantsInfo merchantsInfo) {
        //佣金率不能为空
        if (merchantsInfo.getCommissionRate() != null && "".equals(merchantsInfo.getCommissionRate().trim())) {
            return new Result(ResultCode.INPUT_PARAMS_FAIL, "佣金率commissionRate不能为空");
        }
        UserAccount phoneIsCorrect = new UserAccount();
        phoneIsCorrect.setPhone(merchantsInfo.getMerLegalPhone());
        //是商户
        phoneIsCorrect.setMerchant(1);
        //0表示输入的手机号为空
        if ("0".equals(phoneIsCorrect(phoneIsCorrect))) {
            return new Result(ResultCode.LOGIN_PHONE_FAIL, "手机号不能为空");
        }
        //1表示该手机号已经注册
        if ("1".equals(phoneIsCorrect(phoneIsCorrect))) {
            return new Result(ResultCode.REGISTER_EXIST_FAIL, "手机号已经被注册");
        }
        //获取父编号
        merchantsInfo.setParentMerchantNo(YeepayService.getParentMerchantNo());
        //获取个体商户请求号
        merchantsInfo.setRequestNo(DaDaoUtil.generateIndividualregRquestNo());
        //添加productInfo
        merchantsInfo.setProductInfo(YeepayService.getIndividualProductInfo(merchantsInfo.getCommissionRate()));
        //添加回调地址
        merchantsInfo.setNotifyUrl(YeepayService.getRegisterCallbackUrl());
        //将授权类型写死（短信授权）
        merchantsInfo.setMerAuthorizeType("SMS_AUTHORIZE");
        //处理业务功能为null的情况
        if (merchantsInfo.getBusinessFunction() == null) {
            merchantsInfo.setBusinessFunction("");
        }
        Map<String, String> result = new HashMap<String, String>();
        //获取请求
        String uri = YeepayService.getUrl(YeepayService.INDIVIDUAL_URL);
        //DaDaoUtil.objToMapStr(merchantsInfo)将实体类转为map
        result = YeepayService.requestYOP(DaDaoUtil.objToMapStr(merchantsInfo), uri, YeepayService.INDIVIDUAL);
        //如果响应成功 存到数据库
        if (result.get("returnCode").equals("REG00000")) {
            //设置类型商户类型（0个人、1个体、2企业）
            merchantsInfo.setMerType("1");
            //设置类型为营业执照
            merchantsInfo.setMerCertType("CORP_CODE");
            //保存库
            int a = merchantsInfoMapper.insertSelective(merchantsInfo);
            if (a != 1) {
                throw new RuntimeException("个体商户注册失败");
            }
            //根据请求号查询数据
            MerchantsInfo merchantsInfo1 = merchantsInfoMapper.selectIdByRequestNo(merchantsInfo.getRequestNo());
            //更新响应信息
            MerchantsInfo updateMerchantsInfo = new MerchantsInfo();
            updateMerchantsInfo.setAgreementContent(result.get("agreementContent"));
            updateMerchantsInfo.setExternalId(result.get("externalId"));
            updateMerchantsInfo.setMerchantNo(result.get("merchantNo"));
            updateMerchantsInfo.setParentMerchantNo(result.get("parentMerchantNo"));
            updateMerchantsInfo.setRequestNo(result.get("requestNo"));
            updateMerchantsInfo.setReturnCode(result.get("returnCode"));
            updateMerchantsInfo.setReturnMsg(result.get("returnMsg"));
            updateMerchantsInfo.setMerNetInStatus("PROCESSING");
            updateMerchantsInfo.setId(merchantsInfo1.getId());
            int update = merchantsInfoMapper.updateRequestNotifyInfo(updateMerchantsInfo);
            if (update != 1) {
                throw new RuntimeException("响应信息保存失败");
            }
            return new Result(ResultCode.SYS_SUCCESS, result);
        }
        return new Result(ResultCode.SYS_FAIL, result);
    }

    /**
     * 企业注册
     *
     * @param merchantsInfo
     * @return
     */
    @Override
    @Transactional
    public Result enterpriseReginfoAdd(MerchantsInfo merchantsInfo) {
        //佣金率不能为空
        if (merchantsInfo.getCommissionRate() != null && "".equals(merchantsInfo.getCommissionRate().trim())) {
            return new Result(ResultCode.INPUT_PARAMS_FAIL, "佣金率commissionRate不能为空");
        }
        UserAccount phoneIsCorrect = new UserAccount();
        //设置手机号
        phoneIsCorrect.setPhone(merchantsInfo.getMerContactPhone());
        //是商户
        phoneIsCorrect.setMerchant(1);
        //0表示输入的手机号为空
        if ("0".equals(phoneIsCorrect(phoneIsCorrect))) {
            return new Result(ResultCode.LOGIN_PHONE_FAIL, "手机号不能为空");
        }
        //1表示该手机号已经注册
        if ("1".equals(phoneIsCorrect(phoneIsCorrect))) {
            return new Result(ResultCode.REGISTER_EXIST_FAIL, "手机号已经被注册");
        }
        //获取父编号
        merchantsInfo.setParentMerchantNo(YeepayService.getParentMerchantNo());
        //获取企业请求号
        merchantsInfo.setRequestNo(DaDaoUtil.generateEnterpriseregRquestNo());
        //添加productInfo，并替换佣金率
        merchantsInfo.setProductInfo(YeepayService.getEnterpriseProductInfo(merchantsInfo.getCommissionRate()));
        //处理业务功能为null的情况
        if (merchantsInfo.getBusinessFunction() == null) {
            merchantsInfo.setBusinessFunction("");
        }
        //添加回调地址
        merchantsInfo.setNotifyUrl(YeepayService.getRegisterCallbackUrl());
        //将授权类型写死（短信授权）
        merchantsInfo.setMerAuthorizeType("SMS_AUTHORIZE");
        //创建map集合
        Map<String, String> result = new HashMap<String, String>();
        //获取企业注册url
        String uri = YeepayService.getUrl(YeepayService.ENTERPRISE_URL);
        result = YeepayService.requestYOP(DaDaoUtil.objToMapStr(merchantsInfo), uri, YeepayService.ENTERPRISE);
        if (result.get("returnCode").equals("REG00000")) {
            //企业注册
            merchantsInfo.setMerType("2");
            merchantsInfo.setMerLegalEmail(merchantsInfo.getMerContactEmail());
            //手机号统一使用merchantsInfo字段
            merchantsInfo.setMerLegalPhone(merchantsInfo.getMerContactPhone());
            int a = merchantsInfoMapper.insertSelective(merchantsInfo);
            if (a != 1) {
                throw new RuntimeException("企业注册失败");
            }
            //根据请求号查询数据
            MerchantsInfo merchantsInfo1 = merchantsInfoMapper.selectIdByRequestNo(merchantsInfo.getRequestNo());
            //创建MerchantsInfo
            MerchantsInfo updateMerchantsInfo = new MerchantsInfo();
            updateMerchantsInfo.setExternalId(result.get("externalId"));
            updateMerchantsInfo.setMerchantNo(result.get("merchantNo"));
            updateMerchantsInfo.setParentMerchantNo(result.get("parentMerchantNo"));
            updateMerchantsInfo.setRequestNo(result.get("requestNo"));
            updateMerchantsInfo.setReturnCode(result.get("returnCode"));
            updateMerchantsInfo.setReturnMsg(result.get("returnMsg"));
            updateMerchantsInfo.setMerNetInStatus("PROCESSING");
            updateMerchantsInfo.setId(merchantsInfo1.getId());
            updateMerchantsInfo.setMerLegalEmail(merchantsInfo.getMerContactEmail());

            int update = merchantsInfoMapper.updateRequestNotifyInfo(updateMerchantsInfo);
            if (update != 1) {
                throw new RuntimeException("回调信息保存失败");
            }
            return new Result(ResultCode.SYS_SUCCESS, result);
        }
        return new Result(ResultCode.SYS_FAIL, result);
    }

    @Override
    public Result findByPage(MerchantsInfo merchantsInfo, Long pageNum) {
        //1、总记录数
        long totalRecord = merchantsInfoMapper.countRecordByObject(merchantsInfo);
        //2、总页数
        long totalPage = DaDaoUtil.getTotalPage(totalRecord, merchantsInfo.getPageSize());
        //3、处理pageNum
        pageNum = DaDaoUtil.dealWithPageNum(pageNum, totalPage);
        //4、limit下标
        Long beginIndex = DaDaoUtil.getBeginIndex(pageNum, merchantsInfo.getPageSize());
        merchantsInfo.setBeginIndex(beginIndex);
        //数据
        List<Object> list = merchantsInfoMapper.selectByObject(merchantsInfo);
        QueryResult queryResult = new QueryResult();
        queryResult.setTotalSize(totalRecord);
        queryResult.setTotalPage(totalPage);
        queryResult.setPageNum(pageNum.intValue());
        queryResult.setList(list);
        queryResult.setTotalSize(totalRecord);
        return new Result(ResultCode.SYS_SUCCESS, queryResult);
    }

    @Override
    public Result sendauthorizenum(String merchantNo, String phone) {
        Map<String, String> result = new HashMap<>();
        YopResponse yopResponse = YeepayService.sendauthorizenum(merchantNo, phone);
        if ("FAILURE".equals(yopResponse.getState())) {
            if (yopResponse.getError() != null) {
                result.put("code", yopResponse.getError().getCode());
                result.put("message", yopResponse.getError().getMessage());
            }
            return new Result(ResultCode.YOP_ERROR_MSG, result);
        }
        if (yopResponse.getStringResult() != null) {
            result = YeepayService.parseResponse(yopResponse.getStringResult());
        }
        return new Result(ResultCode.SYS_SUCCESS, result);
    }

    /**
     * 0表示输入的为空 1表示该手机号已经注册 表示正确
     *
     * @param userAccount
     * @return
     */
    private String phoneIsCorrect(UserAccount userAccount) {
        if (userAccount.getPhone() == null || "".equals(userAccount.getPhone().trim())) {
            return "0";
        }
        UserAccount user = new UserAccount();
        user.setPhone(userAccount.getPhone());
        user.setMerchant(userAccount.getMerchant());
        if (merchantsInfoMapper.findUserAccountByEntity(user).size() > 0) {
            return "1";
        }
        return "";
    }

    /**
     * 修改
     *
     * @param merchantsInfo
     * @return
     */
    public Result update(MerchantsInfo merchantsInfo) {
        //根据请求号查询数据
        MerchantsInfo merchantsInfo1 = merchantsInfoMapper.selectIdByRequestNo(merchantsInfo.getRequestNo());

        if (merchantsInfo.getBusinessFunction() == null) {
            merchantsInfo.setBusinessFunction("");
        }
        //1 个体
        if ("1".equals(merchantsInfo.getMerType())) {
            //查询修改之前的手机号
            String oldPhone = merchantsInfo1.getMerLegalPhone();
            //添加productInfo
            merchantsInfo.setProductInfo(YeepayService.getIndividualProductInfo(merchantsInfo.getCommissionRate()));
            Map<String, String> result = new HashMap<String, String>();
            //获取请求
            String uri = YeepayService.getUrl(YeepayService.INDIVIDUAL_URL);
            //DaDaoUtil.objToMapStr(merchantsInfo)将实体类转为map
            result = YeepayService.requestYOP(DaDaoUtil.objToMapStr(merchantsInfo), uri, YeepayService.INDIVIDUAL);
            if (result.get("returnCode").equals("REG00000")) {
                int a = merchantsInfoMapper.update(merchantsInfo);
                if (a == 1) {

                    String newPhone = merchantsInfo.getMerLegalPhone();
                    System.out.println("新手机：" + newPhone);
                    System.out.println("旧手机: " + oldPhone);
                    //如果新的手机号与之前手机号不同表示商户修改了手机号
                    if (!newPhone.equals(oldPhone)) {

                        if (updatePhone(newPhone, oldPhone) != 1) {
                            throw new RuntimeException("修改手机号失败");
                        }
                    }

                    return new Result(ResultCode.SYS_SUCCESS, result);
                } else {
                    return new Result(ResultCode.SYS_FAIL, result);
                }
            }

            return new Result(ResultCode.SYS_FAIL, result);
        }
        //2 企业
        if ("2".equals(merchantsInfo.getMerType())) {
            //查询修改之前的手机号
            String oldPhone = merchantsInfo1.getMerLegalPhone();
            //添加productInfo，并替换佣金率
            merchantsInfo.setProductInfo(YeepayService.getEnterpriseProductInfo(merchantsInfo.getCommissionRate()));
            //创建map集合
            Map<String, String> result = new HashMap<String, String>();
            //获取企业注册url
            String uri = YeepayService.getUrl(YeepayService.ENTERPRISE_URL);
            result = YeepayService.requestYOP(DaDaoUtil.objToMapStr(merchantsInfo), uri, YeepayService.ENTERPRISE);
            if (result.get("returnCode").equals("REG00000")) {
                merchantsInfo.setMerLegalEmail(merchantsInfo.getMerContactEmail());
                int b = merchantsInfoMapper.update(merchantsInfo);
                if (b == 1) {
                    String newPhone = merchantsInfo.getMerContactPhone();
                    System.out.println("新手机：" + newPhone);
                    System.out.println("旧手机: " + oldPhone);
                    //如果新的手机号与之前手机号不同表示商户修改了手机号
                    if (!newPhone.equals(oldPhone)) {

                        if (updatePhone(newPhone, oldPhone) != 1) {
                            throw new RuntimeException("修改手机号失败");
                        }
                    }
                    return new Result(ResultCode.SYS_SUCCESS, result);
                } else {
                    throw new RuntimeException("更新失败");
                }
            }
        }
        return new Result(ResultCode.INPUT_PARAMS_FAIL);

    }

    /**
     * 如果商户修改手机号的时候要进行判断
     *
     * @param newPhone
     * @param oldPhone
     */
    private int updatePhone(String newPhone, String oldPhone) {

        //根据旧手机号查询用户id
        UserAccount userAccount = new UserAccount();
        userAccount.setPhone(oldPhone);
        //商户
        userAccount.setMerchant(1);
        Long userId = merchantsInfoMapper.findByPhone(userAccount).getUserId();
        //修改旧的手机号
        userAccount.setUserId(userId);
        userAccount.setPhone(newPhone);
        int updateUserAccountPhone = merchantsInfoMapper.updateUserAccountPhone(userAccount);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setPhone(newPhone);
        int updateUserInfoPhone = merchantsInfoMapper.updateUserInfoPhone(userInfo);
        if (updateUserAccountPhone == 1 && updateUserInfoPhone == 1) {
            return 1;
        }
        return 0;
    }


}
