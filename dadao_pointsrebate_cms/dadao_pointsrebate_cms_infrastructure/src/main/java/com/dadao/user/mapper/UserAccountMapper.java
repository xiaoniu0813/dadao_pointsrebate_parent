package com.dadao.user.mapper;

import com.dadao.pub.mapper.BaseMapper;
import com.dadao.user.entity.UserAccount;

/**
 * Created by NFY on 2017-07-16.
 */
public interface UserAccountMapper extends BaseMapper {
    UserAccount findByLogin(UserAccount userAccount);

    UserAccount findByPhon(String Phon);
    //查询最后一条记录
    Long findByLastRecord();

    UserAccount findByToken(UserAccount userAccount);
}
