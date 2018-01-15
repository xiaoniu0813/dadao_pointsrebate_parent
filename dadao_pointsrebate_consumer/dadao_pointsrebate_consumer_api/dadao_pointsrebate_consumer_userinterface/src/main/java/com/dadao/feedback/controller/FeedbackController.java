package com.dadao.feedback.controller;

import com.dadao.feedback.entity.FeedbackPO;
import com.dadao.feedback.service.IFeedbackService;
import com.dadao.pub.utils.DADAO;
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
    private IFeedbackService iFeedbackService;

    @PostMapping(value = "feedback")
    public Object feedback(FeedbackPO feedbackPO,String token, HttpServletRequest request, HttpServletResponse response){
        boolean result=iFeedbackService.feedback(feedbackPO,token);
        return DADAO.encryption(request, response, new Result(result ? ResultCode.SYS_SUCCESS : ResultCode.SYS_FAIL, ""));
    }

    @PostMapping(value = "findBeedbackList")
    public Object findBeedbackList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, String token,FeedbackPO feedbackPO, HttpServletRequest request, HttpServletResponse response){
        //pageNum = pageNum <= 0 ? 1 : pageNum;
        feedbackPO.setBeginIndex((pageNum - 1) * feedbackPO.getPageSize());
        QueryResult result = iFeedbackService.findFeedbackList(feedbackPO, token);
        if (result != null)
            result.setPageNum(pageNum);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, result));
    }
}
