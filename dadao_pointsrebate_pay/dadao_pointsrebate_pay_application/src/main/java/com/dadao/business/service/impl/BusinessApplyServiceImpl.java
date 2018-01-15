package com.dadao.business.service.impl;

import com.dadao.business.service.BusinessApplyService;
import com.dadao.businessreg.entity.BusinessApply;
import com.dadao.businessreg.mapper.BusinessApplyMapper;
import com.dadao.merchants.entity.MerchantsInfo;
import com.dadao.push.entity.InformationPO;
import com.dadao.push.service.IPushService;
import com.dadao.utils.*;
import com.dadao.yop.service.YeepayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author GUOYU 2017/12/13
 */
@Service
public class BusinessApplyServiceImpl implements BusinessApplyService {
    @Autowired
    private IPushService iPushService;
    @Autowired
    private BusinessApplyMapper businessApplyMapper;

    @Override
    @Transactional
    public Result reg(BusinessApply businessApply1) {

        BusinessApply businessApply = businessApplyMapper.findInfo(businessApply1);
        businessApply.setCommissionRate(businessApply1.getCommissionRate());
        businessApply.setIntegralRate(businessApply1.getIntegralRate());

        //审核中
        businessApply.setStatus(1);
        businessApply.setRegInfo("审核中！");
        businessApply.setRemark("审核中！");
        businessApply.setMerNetInStatus("PROCESSING");


        if(businessApply.getBusinessFunction() == null){
            businessApply.setBusinessFunction("");
        }
            //添加回调地址
        businessApply.setNotifyUrl(YeepayService.getRegisterCallbackUrl());
            //将授权类型写死（短信授权）
        businessApply.setMerAuthorizeType("SMS_AUTHORIZE");
        Map<String, String> result = new HashMap<String, String>();

        //注册成功后进行消息推送
        InformationPO informationPO = new InformationPO();
        //标题
        informationPO.setTitle("审核进展");
        //商户id
        informationPO.setUserId(businessApply.getUserId());
        //是否已读(已读)
        informationPO.setHaveRead(0);
        //状态（0付款、1收款、2申请退款、3退款成功、4系统消息、5返利、6活动、7优惠、8广告）
        informationPO.setStatus(4);
        //对象id
        informationPO.setObjectId(businessApply.getYid());
        //收单商编
        businessApply.setParentMerchantNo(YeepayService.getReceiptMerchantNo());
        businessApply.setProductInfo("");
        //表示个体注册
        if ("1".equals(businessApply.getMerType().trim())) {
            System.out.println("个体================");
            //获取productInfo (暂时不需)
            //businessApply.setProductInfo(YeepayService.getIndividualProductInfo(businessApply.getCommissionRate()));
            //System.out.println("productInfo====: " + YeepayService.getIndividualProductInfo(businessApply.getCommissionRate()));
            //获取个体注册请求号
            String uri = YeepayService.getUrl(YeepayService.INDIVIDUAL_URL);
            result = YeepayService.requestYOP(DaDaoUtil.objToMapStr(businessApply), uri, YeepayService.INDIVIDUAL);

            //如果响应成功(表示易宝注册成功) 存到数据库
            if (result.get("returnCode").equals("REG00000")) {

                //设置类型为营业执照
                businessApply.setMerCertType("CORP_CODE");
                //在易宝注册成功后返回的信息
                businessApply.setReturnCode(result.get("returnCode"));
                businessApply.setReturnMsg(result.get("returnMsg"));
                businessApply.setParentMerchantNo(result.get("parentMerchantNo"));
                businessApply.setMerchantNo(result.get("merchantNo"));
                businessApply.setRequestNo(result.get("requestNo"));
                businessApply.setExternalId(result.get("externalId"));
                businessApply.setAgreementContent(result.get("agreementContent"));

                //更新数据库内容
                int update3 = businessApplyMapper.update(businessApply);
                if (update3 != 1) {
                    throw new RuntimeException("更新yb_merchants_info失败");
                }
                int update4 = businessApplyMapper.updateBusinessApply(businessApply);
                if (update4 != 1) {
                    throw new RuntimeException("更新business_apply失败");
                }
                //内容
                informationPO.setContent("您提交的信息正在审核中！！！");

                iPushService.pushForBusiness("您提交的信息正在审核中！！",informationPO);
                return new Result(ResultCode.SYS_SUCCESS, "个体注册成功！");
            }else{
                //审核失败
                businessApply.setStatus(4);
                businessApply.setRegInfo(result.get("returnMsg"));
                businessApply.setRemark(result.get("returnMsg"));
                businessApply.setMerNetInStatus("PROCESSING_FAILURE");
            }

        }
        //表示企业注册
        if ("2".equals(businessApply.getMerType().trim())) {
            System.out.println("企业==========");
            //将原来字段改为易包需求字段
            businessApply.setMerContactEmail(businessApply.getMerLegalEmail());
            businessApply.setMerContactPhone(businessApply.getMerLegalPhone());
            //获取productInfo（暂时不需）
            //businessApply.setProductInfo(YeepayService.getEnterpriseProductInfo(businessApply.getCommissionRate()));

            //获取企业注册url
            String uri = YeepayService.getUrl(YeepayService.ENTERPRISE_URL);
            result = YeepayService.requestYOP(DaDaoUtil.objToMapStr(businessApply), uri, YeepayService.ENTERPRISE);
            if (result.get("returnCode").equals("REG00000")) {
                //企业注册成功后返回的数据
                businessApply.setReturnCode(result.get("returnCode"));
                businessApply.setReturnMsg(result.get("returnMsg"));
                businessApply.setParentMerchantNo(result.get("parentMerchantNo"));
                businessApply.setMerchantNo(result.get("merchantNo"));
                businessApply.setRequestNo(result.get("requestNo"));
                businessApply.setExternalId(result.get("externalId"));
                //更新数据库内容
                int update5 = businessApplyMapper.update(businessApply);
                if (update5 != 1) {
                    throw new RuntimeException("更新yb_merchants_info失败");
                }
                int update6 = businessApplyMapper.updateBusinessApply(businessApply);
                if (update6 != 1) {
                    throw new RuntimeException("更新business_apply失败");
                }


                //内容
                informationPO.setContent("您提交的信息正在审核中！！！");

                iPushService.pushForBusiness("您提交的信息正在审核中！！",informationPO);
                return new Result(ResultCode.SYS_SUCCESS, "企业注册成功！");
            }else{
                //审核失败
                businessApply.setStatus(4);
                businessApply.setRegInfo(result.get("returnMsg"));
                businessApply.setRemark(result.get("returnMsg"));
                businessApply.setMerNetInStatus("PROCESSING_FAILURE");
            }
        }
        //内容
        informationPO.setContent(result.get("returnMsg"));

        iPushService.pushForBusiness("您提交的信息出现问题",informationPO);


        int update1 = businessApplyMapper.update(businessApply);
        if(update1 != 1){
            throw new RuntimeException("修改gt_merchants_info状态失败");
        }
        int update2 = businessApplyMapper.updateBusinessApply(businessApply);
        if(update2 != 1){
            throw new RuntimeException("修改business_apply状态失败");
        }

        return new Result(ResultCode.SYS_FAIL,result.get("returnMsg"));
    }

    @Override
    public BasePage findUserRegisterInfo(BusinessApply businessApply) {
        //给页数初始值
        if(businessApply.getPageNum() == null || "".equals(businessApply.getPageNum())){
            businessApply.setPageNum(1);
        }
        //给每页大小初始值
        if(businessApply.getPageSize() == null || "".equals(businessApply.getPageSize())){
            businessApply.setPageSize(10);
        }
        BasePage basePage = new BasePage();
        //当前页
        int pageNum = businessApply.getPageNum();
        basePage.setPageNum(pageNum);
        //总记录数
        long totalSize = businessApplyMapper.totalSize(businessApply);
        basePage.setTotalSize(totalSize);
        //页数
        long pageSize = businessApply.getPageSize();
        long totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
        basePage.setTotalPage(totalPage);
        //索引
        long beginIndex = (pageNum- 1) * pageSize;
        basePage.setBeginIndex(beginIndex);
        businessApply.setBeginIndex(beginIndex);
        //数据
        basePage.setList(businessApplyMapper.findUserRegisterInfo(businessApply));
        //每页的大小
        basePage.setPageSize(new Long(pageSize).intValue());
        return basePage;
    }

    @Override
    @Transactional
    public BusinessApply findSingletonData(BusinessApply businessApply) {
        BusinessApply businessApply1 = businessApplyMapper.findSingletonData(businessApply);
        MerchantsInfo merchantsInfo = businessApplyMapper.findMerchantsInfo(businessApply.getYid());
        businessApply1.setBankCityCode(merchantsInfo.getBankCity());
        businessApply1.setBankProvinceCode(merchantsInfo.getBankProvince());
        //根据条件返回单条注册信息
        return businessApply1;
    }
    //导出
    @Override
    public void findByAll(HttpServletRequest request, HttpServletResponse response, com.dadao.shop.entity.BusinessApply businessApply) {

        //查找出所有数据
        List<com.dadao.shop.entity.BusinessApply> list = businessApplyMapper.findByAll(businessApply);
        /*38*/
        String param[] = {"商户id","佣金率","积分率","审核信息","审核状态","商户全称","商户简称","证件类型","证件号","法人姓名","法人身份证","手机","邮箱","商户一级分类","商户二级分类",
        "商户经营地址所在省","商户经营地址所在市","商户经营地址所在区","商户经营地址所在具体地址","商户经营范围","商户联系人姓名","税务登记证编号","开户许可证编号","组织机构代码证",
        "组织机构代码证是否长期有效","组织机构代理证有效期","银行账户","开户银行总行编码","开户省","开户市","开户银行编码","商户类型","入网请求号","该入网商户所在易宝的商户商编",
        "返回该条请求易宝的流水号","易宝所生成电子协议内容","创建时间","商户入网状态"};
        InputStream is  = ExcelUtil.exportExcel(list,param);
        ExcelUtil.print(request,response,is);
    }
}
