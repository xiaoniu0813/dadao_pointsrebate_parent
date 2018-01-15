package com.dadao.user.controller;

import com.dadao.user.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YunQiang
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private IMessageService messageService;

    /**
     * 未读消息统计
     * @param token 用户token
     * @return
     */
    @PostMapping("/unread")
    public Object unread(@RequestParam(required = true) String token){
        return messageService.findMessageUnread(token);
    }

    /**
     * 消息列表
     * @param token 用户token
     * @param status 用户状态
     * @param pageNum 第几页
     * @param pageSize 页面大小
     * @return
     */
    @PostMapping("/list")
    public Object list(@RequestParam(required = true)String token, int status, @RequestParam(required = false, defaultValue = "1")long pageNum, @RequestParam(required = false, defaultValue = "5")int pageSize){
        return messageService.findMessageList(token, status, pageNum, pageSize);
    }

    /**
     * 消息详情
     * @param token 用户token
     * @param infoId  消息id
     * @return
     */
    @PostMapping("/detail")
    public Object detail(@RequestParam(required = true)String token, @RequestParam(required = true)long infoId){
        return messageService.detailMessage(token, infoId);
    }

    /**
     * 标记消息为已读
     * @param token 用户token
     * @param infoId 消息id
     * @return
     */
    @PostMapping("/read")
    public Object updateMessageHaveRead(@RequestParam(required = true)String token, @RequestParam(required = true)long infoId){
        return messageService.updateMessageHaveRead(token, infoId);
    }

}
