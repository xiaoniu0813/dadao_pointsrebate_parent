package com.dadao.category.mapper;


import com.dadao.pub.mapper.BaseMapper;

import java.util.Map;

/**
 * Created by yunqiang1 on 2017/7/18.
 */
public interface CategoryMapper extends BaseMapper{

    int updateCategoryPicture(Object entity);

    int moveUP(int categoryId);

    int updateCategorySequence(Object entity);

    int findCategoryBySequence(int categoryId);

    int findCategoryByCategoryId(int sequence);

    /**
     * 查找最大的Sequence
     * @param object
     * @return
     */
    int MaxSequence(Object object);


}
