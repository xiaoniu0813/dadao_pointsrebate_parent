package com.dadao.category.service;

import com.dadao.category.entity.Category;
import com.dadao.category.entity.CategoryPO;
import com.dadao.utils.QueryResult;
import org.springframework.stereotype.Service;

/**
 * Created by guoyu on 2017/7/18.
 */
public interface CategoryService {

    QueryResult findByPage(Category category);

    Integer updateCategory(Category category);

    Integer save(CategoryPO categoryPO);

    int updateCategoryPicture(Category category);

    int moveUP(int categoryId);

}
