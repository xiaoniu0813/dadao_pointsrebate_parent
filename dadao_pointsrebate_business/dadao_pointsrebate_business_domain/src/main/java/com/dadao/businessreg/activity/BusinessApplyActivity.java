package com.dadao.businessreg.activity;


import com.dadao.businessreg.entity.BusinessApply;
import com.dadao.businessreg.mapper.BusinessApplyMapper;
import com.dadao.merchants.entity.MerchantsInfo;
import com.dadao.push.entity.InformationPO;
import com.dadao.push.service.IPushService;
import com.dadao.user.entity.UserAccount;
import com.dadao.utils.DaDaoUtil;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 处理关于注册的所有
 *
 * @Author GUOYU 2017/12/11
 */
@Repository
public class BusinessApplyActivity {
    @Autowired
    private IPushService iPushService;
    @Autowired
    private BusinessApplyMapper businessApplyMapper;

    /**
     * 修改商户入驻信息
     *
     * @param businessApply
     * @return
     */
    public Result update(BusinessApply businessApply) {

        //修改yb_merchants_info
        int a = businessApplyMapper.updateMerchanrsInfo(businessApply);
        if (a != 1) {
            throw new RuntimeException("信息修改失败merchantsInfo");
        }
        businessApply.setRegInfo("等待审核中");
        businessApply.setStatus(0);
        int b = businessApplyMapper.updateBusinessApply(businessApply);
        if (b != 1) {
            throw new RuntimeException("信息修改失败businessApply");
        }
        return new Result(ResultCode.SYS_SUCCESS,"修改信息完成！ 信息已提交正在审核中");
    }

    /**
     * 个体 企业注册
     *
     * @param businessApply
     * @return
     */
    @Transactional
    public Result reg(BusinessApply businessApply) {

        //获取token
        String token = businessApply.getToken();
        //第一步判断 该账户是否已经注册
        UserAccount userAccount = new UserAccount();
        userAccount.setToken(token);
        userAccount.setMerchant(1);
        //获取该账户的单条数据
        UserAccount data = businessApplyMapper.findUserAccountByToken(userAccount);
        Long userId = data.getUserId();
        if(businessApplyMapper.findBusinessApplyByUserId(userId) != null){
            return new Result(ResultCode.SYS_FAIL,"一个账户只能入驻一次！");
        }
        businessApply.setBusinessFunction("{\"SUBACCOUNT_IS_OPENED\": [ \"YES\" ],\"ACCESS_TYPE\": [ \"ACCESS_AHEAD\" ]}");
        businessApply.setParentMerchantNo("10017920417");
        businessApply.setMerNetInStatus("INIT");
        //表示个体
        if ("1".equals(businessApply.getMerType().trim())) {
            businessApply.setRequestNo(DaDaoUtil.generateIndividualregRquestNo());
        }
        //表示企业
        if ("2".equals(businessApply.getMerType().trim())) {
            businessApply.setRequestNo(DaDaoUtil.generateEnterpriseregRquestNo());
        }
        //将授权类型写死（短信授权）
        businessApply.setMerAuthorizeType("SMS_AUTHORIZE");
        //表示待审核
        businessApply.setRemark("待审核！");
        businessApply.setUserId(userId);
        int a = businessApplyMapper.save(businessApply);
        if (a != 1) {
            throw new RuntimeException("信息提交失败！");
        }
        //获取请求号
        String requestNo = businessApply.getRequestNo();
        //查到的单条数据
        MerchantsInfo merchantsInfo = businessApplyMapper.findByRequestNo(requestNo);
        BusinessApply saveBusinessApply = new BusinessApply();
        saveBusinessApply.setYid(merchantsInfo.getId());
        saveBusinessApply.setUserId(userId);
        saveBusinessApply.setRegInfo("待审核！");
        saveBusinessApply.setStatus(0);
        int b = businessApplyMapper.saveBusinessApply(saveBusinessApply);
        if (b != 1) {
            throw new RuntimeException("信息提交失败！");
        }
        MerchantsInfo find = businessApplyMapper.findMerchantsInfo(businessApply);
        //注册成功后进行消息推送
        InformationPO informationPO = new InformationPO();
        //标题
        informationPO.setTitle("审核进展");
        //内容
        informationPO.setContent("您的注册信息已注册成功，等待审核中！");
        //商户id
        informationPO.setUserId(userAccount.getUserId());
        //是否已读(已读)
        informationPO.setHaveRead(0);
        //状态（0付款、1收款、2申请退款、3退款成功、4系统消息、5返利、6活动、7优惠、8广告）
        informationPO.setStatus(4);
        informationPO.setObjectId(find.getId());

        iPushService.pushForBusiness("信息提交成功！等待审核中！", informationPO);
        return new Result(ResultCode.SYS_SUCCESS, "信息提交成功！等待审核中！");

    }

    /**
     * 查询商户状态
     *
     * @param businessApply
     * @return
     */
    public BusinessApply findByUserId(BusinessApply businessApply) {

        UserAccount userAccount1 = new UserAccount();
        //获取传过来的token
        userAccount1.setToken(businessApply.getToken());
        //设置类型为商户
        userAccount1.setMerchant(1);
        UserAccount userAccount = businessApplyMapper.findUserAccountByToken(userAccount1);
        businessApply.setUserId(userAccount.getUserId());
        System.out.println("值============" + businessApply.getStatus());

        BusinessApply businessApplys = businessApplyMapper.findByUserId(businessApply);

        MerchantsInfo merchantsInfo = businessApplyMapper.findByMerchantInfo(businessApplys.getYid());
        //开户省编码 数据库查出来的是文字信息
        businessApplys.setBankProvinceCode(merchantsInfo.getBankProvince());
        //开户市
        businessApplys.setBankCityCode(merchantsInfo.getBankCity());
        //商户经营所在区
        businessApplys.setMerDistrictCode(merchantsInfo.getMerDistrict());
        //商户经营所在市编码
        businessApplys.setMerCityCode(merchantsInfo.getMerCity());
        //商户经营地所在省编码
        businessApplys.setMerProvinceCode(merchantsInfo.getMerProvince());
        //商户一级分类编码
        businessApplys.setMerLevel1NoCode(merchantsInfo.getMerLevel1No());
        //商户二级分类编码
        businessApplys.setMerLevel2NoCode(merchantsInfo.getMerLevel2No());
        //总行编码
        businessApplys.setHeadBank(merchantsInfo.getHeadBankCode());
        if (businessApplys != null) {
            //处理佣金率和积分率为null
            if (businessApplys.getIntegralRate() == null) {
                businessApplys.setIntegralRate("");
            }
            if (businessApplys.getCommissionRate() == null) {
                businessApplys.setCommissionRate("");
            }
        }
        return businessApplys;
    }


}
