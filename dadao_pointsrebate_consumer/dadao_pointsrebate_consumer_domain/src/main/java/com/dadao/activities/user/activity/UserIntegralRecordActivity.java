package com.dadao.activities.user.activity;

import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.mapper.IUserIntegralRecordingMapper;
import com.dadao.user.mapper.entity.UserIntegrals;
import com.dadao.utils.Page;
import com.dadao.utils.PageNew;
import com.dadao.utils.QueryResult;
import net.sf.ehcache.pool.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by YunQiang on 2017/8/2
 */
@Repository
public class UserIntegralRecordActivity {

    @Autowired
    private IUserIntegralRecordingMapper iUserIntegralRecordingMapper;

    /**
     *用户积分记录
     * @param token
     * @param userIntegrals
     * @return
     */
    public QueryResult findUserIntegrals(String token, UserIntegrals userIntegrals,Integer pageNum){
        QueryResult queryResult = new QueryResult();
        UserAccount userAccount = new UserAccount();
        userAccount.setToken(token);
        Integer totalSize = iUserIntegralRecordingMapper.countUserIntegralRecord(userAccount);
        //封装参数
        HashMap hashMap = new HashMap();
        hashMap.put("token", userAccount.getToken());
        hashMap.put("beginIndex", (pageNum - 1) * userIntegrals.getPageSize());
        hashMap.put("pageSize",userIntegrals.getPageSize());
        hashMap.put("sort",userIntegrals.getSort());
        hashMap.put("order",userIntegrals.getOrder());
        List list =  iUserIntegralRecordingMapper.findUserIntegrals(hashMap);
        Integer totalPage = totalSize % userIntegrals.getPageSize() == 0 ? totalSize / userIntegrals.getPageSize() : totalSize / userIntegrals.getPageSize() + 1;
        queryResult.setTotalSize(totalSize.longValue());
        queryResult.setTotalPage(totalPage.longValue());
        queryResult.setList(list);
        queryResult.setPageNum(pageNum);
        return queryResult;
    }

    /**
     * 获取当前用户本月交易完成数量 Integer
     * @param userIntegralRecordingPO
     * @return
     */
    public Integer currentMonthDealCount(UserIntegralRecordingPO userIntegralRecordingPO){
        UserIntegralRecordingPO userIntegralRecordingPO1 = new UserIntegralRecordingPO();
        userIntegralRecordingPO1.setUserId(userIntegralRecordingPO.getUserId());
        userIntegralRecordingPO1.setDirection(0);
        //交易
        userIntegralRecordingPO1.setStatus(0);
        //获取交易集合
        List<UserIntegralRecordingPO> list = iUserIntegralRecordingMapper.currentMonthDeal(userIntegralRecordingPO);
        //循环遍历
        int sum = 0;
        for(int i = 0;i < list.size();i++){
            userIntegralRecordingPO1.setObjectId(list.get(i).getObjectId());
            //发生方向为减少
            userIntegralRecordingPO1.setDirection(1);
            if(iUserIntegralRecordingMapper.findUserIntegralRecord(userIntegralRecordingPO1) == null){
                sum ++;
            }

        }
        return sum;
    }
}
