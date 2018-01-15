package com.dadao.push.service.impl;

import com.dadao.push.activity.MessageActivity;
import com.dadao.push.entity.InformationPO;
import com.dadao.push.service.IMessageService;
import com.dadao.push.service.IPushService;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YunQiang
 */
@Service
public class MessageServiceImpl implements IMessageService{

    @Autowired
    private MessageActivity messageActivity;

    @Autowired
    private IPushService iPushService;

    @Override
    public Result listInformation(long userType, Long pageNum, Integer pageSize) {
        return new Result(ResultCode.SYS_SUCCESS, messageActivity.listInformation(userType, pageNum, pageSize));
    }

    @Override
    public Result addInformation(InformationPO informationPO) {
        informationPO.setInfoId(null);
        String emptyString = "";
        if(informationPO.getTitle() == null || emptyString.equals(informationPO.getTitle().trim())){
            return new Result(ResultCode.INPUT_PARAMS_FAIL, "标题不能为空！");
        }
        if(informationPO.getNotifyUrl() == null || emptyString.equals(informationPO.getNotifyUrl().trim())){
            return new Result(ResultCode.INPUT_PARAMS_FAIL, "H5地址不能为空！");
        }
        if(informationPO.getImage() == null || emptyString.equals(informationPO.getImage().trim())){
            return new Result(ResultCode.INPUT_PARAMS_FAIL, "消息图片地址不能为空！");
        }
        if(informationPO.getContent() == null || emptyString.equals(informationPO.getContent().trim())){
            return new Result(ResultCode.INPUT_PARAMS_FAIL, "内容不能为空！");
        }
        if(informationPO.getEndTime() == null){
            return new Result(ResultCode.INPUT_PARAMS_FAIL, "结束时间不能为空！");
        }
        if(informationPO.getStatus() != 6 && informationPO.getStatus() != 7 && informationPO.getStatus() != 8){
            return new Result(ResultCode.INPUT_PARAMS_FAIL, "status只能为：6活动、7优惠、8广告");
        }
        if(informationPO.getUserId() != -1 && informationPO.getUserId() != -2){
            return new Result(ResultCode.INPUT_PARAMS_FAIL, "userId只能为：-1用户活动，-2商户活动");
        }

        int flag = messageActivity.addInformation(informationPO);
        if (flag == 1){
            //推送消息给所有用户或者所有商户
            if(informationPO.getUserId() == -1){
                iPushService.pushForAllConsumer(informationPO.getTitle(), informationPO);
            }
            if (informationPO.getUserId() == -2){
                iPushService.pushForBusiness(informationPO.getTitle(), informationPO);
            }
        }
        return new Result(ResultCode.SYS_SUCCESS, null);
    }

    @Override
    public Result updateInformation(InformationPO informationPO) {
        if (informationPO.getInfoId() == null){
            return new Result(ResultCode.ENTITY_ID_NULL, "infoId不能为空！");
        }
        return messageActivity.updateInformation(informationPO) == 1 ? new Result(ResultCode.SYS_SUCCESS, null) : new Result(ResultCode.SYS_FAIL);
    }

    @Override
    public Result deleteInformation(InformationPO informationPO) {
        if (informationPO.getInfoId() == null){
            return new Result(ResultCode.ENTITY_ID_NULL, "infoId不能为空！");
        }
        informationPO.setStatus(-10);
        return messageActivity.updateInformation(informationPO) == 1 ? new Result(ResultCode.SYS_SUCCESS, "删除成功！") : new Result(ResultCode.SYS_FAIL, null);
    }

    @Override
    public Result detailMessage(long infoId) {
        return new Result(ResultCode.SYS_SUCCESS, messageActivity.findMessageById(infoId));
    }

}
