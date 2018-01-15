package com.dadao.category.controller;

import com.dadao.category.entity.Category;
import com.dadao.category.entity.CategoryPO;
import com.dadao.category.service.CategoryService;
import com.dadao.utils.DADAO;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yunqiang1 on 2017/7/18.
 */
@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping(value = "/findCategoryByPage" )
    public Object findByPage(@RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum, Category category, HttpServletRequest request, HttpServletResponse response) {
        category.setBeginIndex((pageNum-1)*category.getPageSize());
        QueryResult queryResult = categoryService.findByPage(category);
        queryResult.setPageNum(pageNum);
        boolean flag = !(queryResult.getList().size() == 0);
        return DADAO.encryption(request, response, new Result(flag? ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL, queryResult));
    }

    @PostMapping(value = "/updateCategory")
    public Object updatePage(Category category, HttpServletRequest request, HttpServletResponse response){
        boolean flag = categoryService.updateCategory(category) == 1;
        return DADAO.encryption(request, response, new Result(flag? ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL,flag));
    }

    @PostMapping(value = "/addCategory")
    public Object addCategory(CategoryPO categoryPO, HttpServletRequest request, HttpServletResponse response){
        boolean flag = categoryService.save(categoryPO) == 1;

        return DADAO.encryption(request, response, new Result(flag? ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL,flag));
    }
    @PostMapping(value = "/updateCategoryPicture")
    public Object updateCategoryPicture(Category category, HttpServletRequest request, HttpServletResponse response){
        boolean flag = categoryService.updateCategoryPicture(category) == 1;
        return DADAO.encryption(request, response, new Result(flag? ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL,flag));
    }

    @PostMapping(value = "/moveUP")
    public Object moveUP(Category category, HttpServletRequest request, HttpServletResponse response){
        boolean flag = categoryService.moveUP(category.getCategoryId()) == 1;
        return DADAO.encryption(request, response, new Result(flag? ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL,flag));
    }
}
