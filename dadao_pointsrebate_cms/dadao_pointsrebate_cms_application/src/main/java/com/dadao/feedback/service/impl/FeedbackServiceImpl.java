package com.dadao.feedback.service.impl;

import com.dadao.feedback.activity.FeedbackActivity;
import com.dadao.feedback.entity.FeedbackPO;
import com.dadao.feedback.service.FeedbackService;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther NFY niufuyang
 * @create 2018-1-10
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackActivity feedbackActivity;

    @Override
    public boolean treatFeedback(FeedbackPO feedbackPO, String token) {
        return feedbackActivity.treatFeedback(feedbackPO,token);
    }

    @Override
    public QueryResult listFeedback(FeedbackPO feedbackPO) {
        return feedbackActivity.listFeedback(feedbackPO);
    }
}
