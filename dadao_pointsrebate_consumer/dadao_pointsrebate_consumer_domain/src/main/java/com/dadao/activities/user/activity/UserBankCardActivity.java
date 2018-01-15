package com.dadao.activities.user.activity;

import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserBankCardPO;
import com.dadao.user.mapper.IUserBankCardMapper;
import com.dadao.user.mapper.UserMapper;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户绑定银行卡
 *
 * @auther NFY niufuyang
 * @create 2017-08-07
 */
@Repository
public class UserBankCardActivity {
    @Autowired
    private IUserBankCardMapper userBankCardMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据token查询用户绑定银行卡
     * @param token
     * @return
     */
    public QueryResult findUserBankCardList(String token){
        UserAccount userAccount=new UserAccount();
        userAccount.setToken(token);
        UserAccount account=userMapper.findByToken(userAccount);
        if (account==null)
            return null;
        QueryResult queryResult = new QueryResult();
        UserBankCardPO userBankCardPO=new UserBankCardPO();
        userBankCardPO.setUserId(account.getUserId());
        Long totalSize = userBankCardMapper.findCount(userBankCardPO);
        List list = userBankCardMapper.findByUserId(userBankCardPO);
        Long totalPage = totalSize % userBankCardPO.getPageSize() == 0 ? totalSize / userBankCardPO.getPageSize() : totalSize / userBankCardPO.getPageSize() + 1;
        queryResult.setTotalPage(totalPage);
        queryResult.setTotalSize(totalSize);
        queryResult.setList(list);
        return queryResult;

    }

}
