package com.dadao.feedback.service;

import com.dadao.feedback.entity.FeedbackPO;
import com.dadao.utils.QueryResult;

/**
 * Created by NFY on 2018-1-10.
 */
public interface IFeedbackService {

    boolean feedback(FeedbackPO feedbackPO,String token);

    QueryResult findFeedbackList(FeedbackPO feedbackPO, String token);
}