package com.dadao.user.activity;

import com.dadao.user.entity.UserIntegral;
import com.dadao.user.mapper.IUserIntegralMapper;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户积分
 *
 * @auther NFY niufuyang
 * @create 2017-8-24
 */
@Repository
public class UserIntegralActivity {
    @Autowired
    private IUserIntegralMapper userIntegralMapper;

    /**
     * 查询用户有效积分（当前积分减去7天内获得的积分） --niufy
     * @param userIntegral
     * @return
     */
    public QueryResult listUserIntegral(UserIntegral userIntegral){
        QueryResult queryResult = new QueryResult();
        Long totalSize = userIntegralMapper.findCount(userIntegral);
        List list = userIntegralMapper.findByPage(userIntegral);
        Long totalPage = totalSize % userIntegral.getPageSize() == 0 ? totalSize / userIntegral.getPageSize() : totalSize / userIntegral.getPageSize() + 1;
        queryResult.setTotalPage(totalPage);
        queryResult.setTotalSize(totalSize);
        queryResult.setList(list);
        return queryResult;
    }

}
