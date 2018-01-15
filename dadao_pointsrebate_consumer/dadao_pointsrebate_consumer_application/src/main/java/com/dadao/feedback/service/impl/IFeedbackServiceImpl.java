package com.dadao.feedback.service.impl;

import com.dadao.activities.feedback.activity.FeedBackActivity;
import com.dadao.feedback.entity.FeedbackPO;
import com.dadao.feedback.service.IFeedbackService;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther NFY niufuyang
 * @create 2018-1-10
 */
@Service
public class IFeedbackServiceImpl implements IFeedbackService {

    @Autowired
    private FeedBackActivity feedBackActivity;

    @Override
    public boolean feedback(FeedbackPO feedbackPO,String token) {
        return feedBackActivity.feedback(feedbackPO,token);
    }

    @Override
    public QueryResult findFeedbackList(FeedbackPO feedbackPO, String token) {
        return feedBackActivity.findFeedbackList(feedbackPO,token);
    }
}
