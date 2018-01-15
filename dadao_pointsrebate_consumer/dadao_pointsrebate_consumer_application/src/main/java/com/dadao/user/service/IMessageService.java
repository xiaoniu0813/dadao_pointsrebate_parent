package com.dadao.user.service;

import com.dadao.dto.MessageUnread;
import com.dadao.push.entity.InformationPO;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;

/**
 * @author YunQiang
 */
public interface IMessageService {

    /**
     * 找到未阅读的信息数
     * @param token 用户token
     * @return
     */
    Result findMessageUnread(String token);

    /**
     * 用户消息列表查询
     * @param token 用户token
     * @param status 系统消息：status为0、交易消息：status为1、活动消息：status为2
     * @param pageNum 第几页
     * @param pageSize 页面大小
     * @return 处理结果
     */
    Result findMessageList(String token, int status, Long pageNum, int pageSize);

    /**
     * 用户消息详情查询
     * @param token 用户token
     * @param infoId 消息id
     * @return
     */
    Result detailMessage(String token, long infoId);

    /**
     * 更新用户消息为已读
     * @param token 用户token
     * @param infoId 消息id
     * @return
     */
    Result updateMessageHaveRead(String token, long infoId);
}
