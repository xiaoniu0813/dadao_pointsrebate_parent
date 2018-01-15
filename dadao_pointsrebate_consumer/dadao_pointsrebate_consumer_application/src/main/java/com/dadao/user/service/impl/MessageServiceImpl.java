package com.dadao.user.service.impl;

import com.dadao.activities.user.activity.MessageActivity;
import com.dadao.user.service.IMessageService;
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

    @Override
    public Result findMessageUnread(String token) {
        return new Result(ResultCode.SYS_SUCCESS, messageActivity.findMessageUnread(token));
    }

    @Override
    public Result findMessageList(String token, int status, Long pageNum, int pageSize) {
        return new Result(ResultCode.SYS_SUCCESS, messageActivity.findMessageList(token, status, pageNum, pageSize));
    }

    @Override
    public Result detailMessage(String token, long infoId) {
        return new Result(ResultCode.SYS_SUCCESS, messageActivity.detailMessage(token, infoId));
    }

    @Override
    public Result updateMessageHaveRead(String token, long infoId) {
        messageActivity.updateMessageHaveRead(token, infoId);
        return new Result(ResultCode.SYS_SUCCESS, "'" + infoId + "'更新成功！");
    }
}
