package com.dadao.callback.service.impl;

import com.dadao.businessreg.entity.BusinessApply;
import com.dadao.businessreg.mapper.BusinessApplyMapper;
import com.dadao.callback.service.CallBackRegisterInfoService;
import com.dadao.market.entity.MarketGradePO;
import com.dadao.merchants.entity.MerchantsInfo;
import com.dadao.merchants.mapper.MerchantsInfoMapper;
import com.dadao.push.entity.InformationPO;
import com.dadao.push.service.IPushService;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserInfo;
import com.dadao.user.entity.UserIntegral;
import com.dadao.user.entity.UserWalletPO;
import com.dadao.utils.EncryptUtil;
import com.dadao.yop.service.YeepayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * created by GUOYU 2017/11/16
 */
@Service
public class CallBackRegisterInfoServiceImpl implements CallBackRegisterInfoService {

    @Autowired
    private IPushService iPushService;
    @Autowired
    private MerchantsInfoMapper merchantsInfoMapper;
    @Autowired
    private BusinessApplyMapper businessApplyMapper;

    /**
     * 回调接口
     *
     * @param response
     * @return
     */
    @Transactional
    public String callbackUrl(String response) {

        //回调接口实现消息推送

        Map<String, String> result = YeepayService.callback(response);

        String merchantNo = result.get("merNo");
        String externalId = result.get("externalId");
        String requestNo = result.get("requestNo");
        String merFullName = result.get("merFullName");
        String merNetInStatus = result.get("merNetInStatus");
        String remark = result.get("remark");

        BusinessApply businessApply = new BusinessApply();
        businessApply.setMerchantNo(merchantNo);
        businessApply.setExternalId(externalId);
        businessApply.setRequestNo(requestNo);
        businessApply.setMerFullName(merFullName);
        businessApply.setMerNetInStatus(merNetInStatus);
        businessApply.setRemark(remark);
        //根据入网请求号查询出手机号
        MerchantsInfo allInfo = merchantsInfoMapper.selectIdByRequestNo(businessApply.getRequestNo());
        businessApply.setYid(allInfo.getId());


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
        informationPO.setObjectId(allInfo.getId());

        //审核成功
        if ("PROCESS_SUCCESS".equals(businessApply.getMerNetInStatus())) {
            //内容
            informationPO.setContent(remark);
            iPushService.pushForBusiness("审核成功！",informationPO);
            businessApply.setStatus(2);
        }
        //审核拒绝
        if ("PROCESS_REJECT".equals(businessApply.getMerNetInStatus())) {
            //内容
            informationPO.setContent(remark);
            iPushService.pushForBusiness("审核拒绝！",informationPO);
            businessApply.setStatus(5);
        }
        //审核回退
        if ("PROCESS_BACK".equals(businessApply.getMerNetInStatus())) {
            //内容
            informationPO.setContent(remark);
            iPushService.pushForBusiness("审核回退！",informationPO);
            businessApply.setStatus(4);
        }
        businessApply.setRegInfo(remark);
        int updateBusinessApply = businessApplyMapper.updateBusinessApply(businessApply);
        if (updateBusinessApply != 1) {
            throw new RuntimeException("更新business_apply数据失败");
        }
        int updateMerchantInfo = businessApplyMapper.update(businessApply);
        if (updateMerchantInfo != 1) {
            throw new RuntimeException("更新gt_merchant_info数据失败");
        }
        return "success";
    }
}

