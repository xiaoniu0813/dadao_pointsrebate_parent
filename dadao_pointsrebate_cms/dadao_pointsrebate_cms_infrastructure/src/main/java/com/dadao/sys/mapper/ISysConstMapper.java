package com.dadao.sys.mapper;


import com.dadao.sys.entity.SysConst;

public interface ISysConstMapper {
    int deleteByPrimaryKey(Integer constId);

    int insert(SysConst record);

    int insertSelective(SysConst record);

    SysConst selectByPrimaryKey(Integer constId);

    int updateByPrimaryKeySelective(SysConst record);

    int updateByPrimaryKey(SysConst record);
}