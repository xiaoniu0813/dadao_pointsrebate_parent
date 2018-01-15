package com.dadao.user.activity;

import com.dadao.user.entity.UserTransactionRecodPO;
import com.dadao.user.mapper.UserTransactionRecodMapper;
import com.dadao.utils.POPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserTransactionRecodActivity {

    @Autowired
    private UserTransactionRecodMapper userTransactionRecodMapper;

    /**
     * 用户交易记录
     * @param userTransactionRecodPO
     * @return
     */
    public POPage showUserTransactionRecodPOByPage(UserTransactionRecodPO userTransactionRecodPO){

        if(userTransactionRecodPO.getPageNum() == null){
            userTransactionRecodPO.setPageNum(1);
        }
        if(userTransactionRecodPO.getPageSize() == null){
            userTransactionRecodPO.setPageSize(6);
        }
        userTransactionRecodPO.setBeginIndex((userTransactionRecodPO.getPageNum()-1)*userTransactionRecodPO.getPageSize());
        POPage poPage = new POPage();
        poPage.setPageNum(userTransactionRecodPO.getPageNum());
        poPage.setPageSize(userTransactionRecodPO.getPageSize());
        poPage.setBeginIndex((userTransactionRecodPO.getPageNum()-1)*userTransactionRecodPO.getPageSize());
        poPage.setList(userTransactionRecodMapper.findByPage(userTransactionRecodPO));
        poPage.setStartTime(userTransactionRecodPO.getStartTime());
        poPage.setEndTime(userTransactionRecodPO.getEndTime());
        poPage.setCount(userTransactionRecodMapper.findCount(userTransactionRecodPO));
        poPage.setTotalPage(userTransactionRecodMapper.findCount(userTransactionRecodPO) % userTransactionRecodPO.getPageSize() ==0?userTransactionRecodMapper.findCount(userTransactionRecodPO) / userTransactionRecodPO.getPageSize() : (userTransactionRecodMapper.findCount(userTransactionRecodPO) / userTransactionRecodPO.getPageSize() + 1));
        return poPage;
    }

    public List<UserTransactionRecodPO> findByEntity(UserTransactionRecodPO userTransactionRecodPO){
        List<UserTransactionRecodPO> list = userTransactionRecodMapper.findByEntity(userTransactionRecodPO);
        return list;
    }
}
