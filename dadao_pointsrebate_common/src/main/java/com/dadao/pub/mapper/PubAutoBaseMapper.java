package com.dadao.pub.mapper;

import java.util.List;


/**
 * 本mapper用来给自动生成的mapper继承
 * @author YunQiang
 */
public interface PubAutoBaseMapper {

    /**
     * 通过id删除一条记录（禁止使用）
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     *插入所有的字段
     * @param record 数据表对应实体类
     * @return
     */
    int insert(Object record);

    /**
     * 插入不为空的字段
     * @param record 数据表对应实体类
     * @return
     */
    int insertSelective(Object record);

    /**
     * 通过主键查询一条记录
     * @param id
     * @return
     */
    Object selectByPrimaryKey(Long id);

    /**
     * 更新不为空的字段
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Object record);

    /**
     * 更新所有字段
     * @param record
     * @return
     */
    int updateByPrimaryKey(Object record);

    /**
     * 分页查询
     * @param object
     * @return
     */
    List<Object> selectByObject(Object object);

    /**
     * 统计总记录数，辅助分页查询
     * @param object
     * @return
     */
    long countRecordByObject(Object object);

}
