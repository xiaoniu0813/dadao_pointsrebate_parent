package com.dadao.merchants.controller;

import com.dadao.merchants.entity.ProductsInfo;
import com.dadao.merchants.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by YunQiang on 2017/11/14
 * @author YunQiang
 */
@RestController
@RequestMapping("/productInfo")
public class ProductInfoController {


    @Autowired
    ProductInfoService productInfoService;

    @PostMapping("/create")
    public Object createProductInfoServlet(ProductsInfo productsInfo, HttpServletRequest request, HttpServletResponse response){
        return productInfoService.createProductInfo(productsInfo);
    }

}

