package com.dadao.feedback.controller;

import com.dadao.feedback.entity.FeedbackPO;
import com.dadao.feedback.service.FeedbackService;
import com.dadao.utils.DADAO;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther NFY niufuyang
 * @create 2018-1-10
 */
@RestController
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping(value = "/treatFeedback")
    public Object treatFeedback(FeedbackPO feedbackPO, String token, HttpServletRequest request, HttpServletResponse response){
        boolean result = feedbackService.treatFeedback(feedbackPO,token);
        return DADAO.encryption(request,response,new Result(result?ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL ,""));
    }

    @PostMapping(value = "/listFeedback")
    public Object listFeedback(@RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum, FeedbackPO feedbackPO, HttpServletRequest request, HttpServletResponse response){
        feedbackPO.setBeginIndex((pageNum-1)*feedbackPO.getPageSize());
        QueryResult queryResult = feedbackService.listFeedback(feedbackPO);
        if(queryResult!=null)
            queryResult.setPageNum(pageNum);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, queryResult));
    }

}
