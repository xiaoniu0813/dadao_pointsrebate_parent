package com.dadao.merchants.service;

import com.dadao.merchants.entity.ProductsInfo;
import com.dadao.utils.Result;

/**
 * Created by YunQiang on 2017/11/14
 * @author YunQiang
 */
public interface ProductInfoService {

    /**
     * 生成个人商户注册需要的ProductInfo
     * @param productsInfo 传入身份证等相关信息
     * @return
     */
    Result createProductInfo(ProductsInfo productsInfo);

}
