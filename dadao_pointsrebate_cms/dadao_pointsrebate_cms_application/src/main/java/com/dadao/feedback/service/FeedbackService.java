package com.dadao.feedback.service;

import com.dadao.feedback.entity.FeedbackPO;
import com.dadao.utils.QueryResult;

/**
 * @auther NFY niufuyang
 * @create 2018-1-10
 */
public interface FeedbackService {

    boolean treatFeedback(FeedbackPO feedbackPO, String token);

    QueryResult listFeedback(FeedbackPO feedbackPO);
}
