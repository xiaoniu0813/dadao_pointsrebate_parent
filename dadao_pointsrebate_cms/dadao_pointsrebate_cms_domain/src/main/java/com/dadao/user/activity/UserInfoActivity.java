package com.dadao.user.activity;

import com.dadao.user.entity.UserInfo;
import com.dadao.user.mapper.UserInfoMapper;
import com.dadao.utils.BasePage;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserInfoActivity {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 分页查询用户信息
     * @param userInfo
     * @return
     */
    public BasePage findUserInfoByPage(UserInfo userInfo){
        if(userInfo.getPageNum() == null){
            userInfo.setPageNum(1);
        }
        if(userInfo.getPageSize() == null){
            userInfo.setPageSize(10);
        }
        long beginIndex =(userInfo.getPageNum()-1)*userInfo.getPageSize();
        userInfo.setBeginIndex(beginIndex);
        long totalSize = userInfoMapper.findCount(userInfo);
        long totalPage = totalSize % userInfo.getPageSize() == 0 ? totalSize / userInfo.getPageSize(): totalSize / userInfo.getPageSize() + 1;
        List<UserInfo> list = userInfoMapper.findByPage(userInfo);
        for(int i = 0;i< list.size();i++){
            list.get(i).setBankCardCount(userInfoMapper.findCountByBankCard(list.get(i).getUserId()));
        }
        BasePage basePage = new BasePage();
        basePage.setPageNum(userInfo.getPageNum());
        basePage.setPageSize(userInfo.getPageSize());
        basePage.setBeginIndex(beginIndex);
        basePage.setTotalSize(totalSize);
        basePage.setTotalPage(totalPage);
        basePage.setList(list);
        return  basePage;
    }

    /**
     * 查看单个用户
     */
    public UserInfo findUser(UserInfo userInfo){
        UserInfo user = (UserInfo) userInfoMapper.findUser(userInfo);
        user.setBankCardCount(userInfoMapper.findCountByBankCard(userInfo.getUserId()));
        return user;
    }

}
