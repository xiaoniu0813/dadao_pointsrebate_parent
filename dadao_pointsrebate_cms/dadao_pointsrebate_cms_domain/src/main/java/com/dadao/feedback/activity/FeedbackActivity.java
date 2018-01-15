package com.dadao.feedback.activity;

import com.dadao.feedback.entity.FeedbackPO;
import com.dadao.feedback.mapper.FeedbackMapper;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.mapper.UserAccountMapper;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @auther NFY niufuyang
 * @create 2018-1-10
 */
@Repository
public class FeedbackActivity {

    @Autowired
    private FeedbackMapper feedbackMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;

    /**
     * 处理意见反馈
     *
     * @param feedbackPO
     * @param token
     * @return
     */
    public boolean treatFeedback(FeedbackPO feedbackPO, String token) {
        feedbackPO.setStatus(1);
        UserAccount userAccount = new UserAccount();
        userAccount.setToken(token);
        userAccount = userAccountMapper.findByToken(userAccount);

        if (userAccount == null) {
            return false;
        }
        feedbackPO.setTreatUserId(userAccount.getUserId());
        feedbackPO.setTreatTime(new Date());
        int updateResult = feedbackMapper.update(feedbackPO);
        if(updateResult!=1){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 查询分页
     * @param feedbackPO
     * @return
     */
    public QueryResult listFeedback(FeedbackPO feedbackPO){
        QueryResult queryResult = new QueryResult();
        Long totalSize = feedbackMapper.findCount(feedbackPO);
        List list = feedbackMapper.findByPage(feedbackPO);
        Long totalPage = totalSize % feedbackPO.getPageSize() == 0 ? totalSize / feedbackPO.getPageSize() : totalSize / feedbackPO.getPageSize() + 1;
        queryResult.setTotalPage(totalPage);
        queryResult.setTotalSize(totalSize);
        queryResult.setList(list);
        return queryResult;
    }

}
