package com.dadao.category.service.impl;

import com.dadao.category.activity.CategoryActivity;
import com.dadao.category.entity.Category;
import com.dadao.category.entity.CategoryPO;
import com.dadao.category.service.CategoryService;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by guoyu on 2017/7/18.
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryActivity categoryActivity;

    public QueryResult findByPage(Category category) {
        return categoryActivity.findByPage(category);
    }

    public Integer updateCategory(Category category) {
        return categoryActivity.updateById(category);
    }

    public Integer save(CategoryPO categoryPO){return categoryActivity.save(categoryPO);};

    public int updateCategoryPicture(Category category){return categoryActivity.updateCategoryPicture(category);}
    public int moveUP(int categoryId){return categoryActivity.moveUP(categoryId);}

}
