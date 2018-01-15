package com.dadao.activities.feedback.activity;

import com.dadao.feedback.entity.FeedbackPO;
import com.dadao.feedback.mapper.IFeedbackMapper;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.mapper.UserMapper;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 意见反馈业务逻辑
 *
 * @auther NFY niufuyang
 * @create 2018-1-10
 */
@Repository
public class FeedBackActivity {
    @Autowired
    private IFeedbackMapper iFeedbackMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 意见反馈
     * @param feedbackPO
     * @return
     */
    public boolean feedback(FeedbackPO feedbackPO,String token){
        UserAccount account=new UserAccount();
        account.setToken(token);
        account=userMapper.findByToken(account);
        if (account==null){
            return false;
        }
        if(feedbackPO.getContent()==null||feedbackPO.getContent()==""){
            return false;
        }
        feedbackPO.setUserId(account.getUserId());
        int saveFeedbackResult=iFeedbackMapper.save(feedbackPO);
        if(saveFeedbackResult!=1){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 查询我的反馈
     * @param feedbackPO
     * @param token
     * @return
     */
    public QueryResult findFeedbackList(FeedbackPO feedbackPO,String token){
        UserAccount account=new UserAccount();
        account.setToken(token);
        account=userMapper.findByToken(account);
        if (account==null){
            return null;
        }
        feedbackPO.setUserId(account.getUserId());
        QueryResult queryResult = new QueryResult();
        Long totalSize = iFeedbackMapper.findCount(feedbackPO);
        List list = iFeedbackMapper.findByPage(feedbackPO);
        Long totalPage = totalSize % feedbackPO.getPageSize() == 0 ? totalSize / feedbackPO.getPageSize() : totalSize / feedbackPO.getPageSize() + 1;
        queryResult.setTotalPage(totalPage);
        queryResult.setTotalSize(totalSize);
        queryResult.setList(list);
        return queryResult;
    }

}
