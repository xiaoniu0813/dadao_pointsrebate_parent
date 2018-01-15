package com.dadao.user.activity;

import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserRole;
import com.dadao.user.mapper.UserAccountMapper;
import com.dadao.user.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by NFY on 2017-07-16.
 */
@Repository
public class UserAccountActivity {
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 用户登录验证----niufy
     */
    public String findByLogin(UserAccount userAccount) {
        UserAccount account = userAccountMapper.findByLogin(userAccount);
        if (account == null) {
            return "";
        }
        UserRole userRole = new UserRole();
        userRole.setUserId(account.getUserId());
        userRole = userRoleMapper.findByUserId(userRole);
        if (userRole != null && userRole.getRoleStatus() == 1) {
            return account.getToken();
        } else {
            return "";
        }
    }

    /**
     * 添加用户----niufy
     */
    public boolean saveUser(UserAccount userAccount) {
        try {
            //判断该电话是否已被添加
            if (userAccountMapper.findByPhon(userAccount.getPhone()) == null) {
                int accountResult = userAccountMapper.save(userAccount);
                if (accountResult < 1) {
                    return false;
                }
                UserRole userRole = new UserRole();
                userRole.setUserId(userAccount.getUserId());
                userRole.setRoleName("管理员");
                int role = userRoleMapper.save(userRole);
                if (role < 1) {
                    return false;
                } else {
                    return true;
                }
            }else{
                return false;
            }
        } catch (Exception e) {
            System.out.println("UserAccountActivity:" + e);
            return false;
        }
    }
}
