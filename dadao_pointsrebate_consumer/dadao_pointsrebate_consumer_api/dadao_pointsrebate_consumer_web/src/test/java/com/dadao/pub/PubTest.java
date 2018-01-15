package com.dadao.pub;

import com.dadao.category.entity.Category;
import com.dadao.category.mapper.CategoryMapper;
import com.dadao.common.BaseTest;
import com.dadao.city.entity.PubCity;
import com.dadao.city.mapper.PubCityMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by yangrui on 2017/7/16.
 */
public class PubTest extends BaseTest {

    @Autowired
    private PubCityMapper pubCityMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void testFindById() {
        PubCity pubCity = pubCityMapper.findById(110000);
        System.out.println(pubCity);
    }

    @Test
    public void testFindByPage() {
        Category category = new Category();
        List list = categoryMapper.findByPage(category);
        for (Object object : list) {
            System.out.println((Category) object);
        }
    }
}
