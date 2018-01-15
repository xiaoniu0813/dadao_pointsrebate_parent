package com.dadao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yangrui on 2017/7/16.
 */
public class DADAO {
    private static final Logger logger = LoggerFactory.getLogger(DADAO.class);

    /**
     * 数据加密
     *
     * @return
     */
    public static Object encryption(HttpServletRequest request, HttpServletResponse response, Object object) {
        return object;
    }

}
