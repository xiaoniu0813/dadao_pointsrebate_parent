package com.dadao.category;

import com.dadao.category.entity.Category;
import com.dadao.category.mapper.CategoryMapper;
import com.dadao.pub.BaseTest;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by yunqiang1 on 2017/7/21.
 */
public class TestCategory extends BaseTest{

    @Resource
    CategoryMapper categoryMapper;

    @Test
    @Transactional
    public void testAddCategory(){
        System.out.println("------------------------------------");
        System.out.println(categoryMapper.findByPage(new Category()));
        System.out.println("------------------------------------");
    }



}
