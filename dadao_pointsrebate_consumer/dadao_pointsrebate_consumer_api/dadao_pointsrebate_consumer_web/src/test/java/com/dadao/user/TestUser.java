package com.dadao.user;

import com.dadao.common.BaseTest;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.mapper.UserMapper;
import com.dadao.utils.EncryptUtil;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yunqiang1 on 2017/7/24.
 */
public class TestUser extends BaseTest{

    @Resource
    private UserMapper userAccMapper;


    /**
     * 1.测试条件查找
     */
    @Test
    public void testFindByEntity(){
        UserAccount userAccount = new UserAccount();
        userAccount.setPhone("15933667730");
        List list = userAccMapper.findByEntity(userAccount);
        System.out.println(list.size() > 0);
    }

    /**
     * 2.测试保存用户
     */
    @Test
    public void testSave(){
        UserAccount userAccount = new UserAccount();
        userAccount.setPhone("18309291927");
        userAccount.setToken("31231");
        userAccount.setGesturePassword("213231");
        userAccount.setStatus(1);
        userAccount.setPassword("2131");
        userAccount.setPayPassword("231321");
        userAccMapper.save(userAccount);
    }

    @Test
    public void testEncryptUtil(){
        String ps1 = EncryptUtil.getEncodeStr("1223 aer a");
        String ps2 = EncryptUtil.getEncodeStr("1223 aer a");
        String ps3 = EncryptUtil.getEncodeStr("1223 aer a");
        String ps4 = EncryptUtil.getEncodeStr("1223 aer a12");
        System.out.println(ps1.equals(ps2));
        System.out.println(ps3.equals(ps4));
        System.out.println(ps1);
        System.out.println(ps2);
        System.out.println(ps3);
        System.out.println(ps4);
    }



}
