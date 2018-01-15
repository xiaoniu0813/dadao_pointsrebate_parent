package com.dadao.common.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by e on 2017-07-30.
 */
public class BaseController {

    public Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * ThreadLocal确保高并发下每个请求的request，response都是独立的
     */
    private static ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<HttpServletRequest>();
    private static ThreadLocal<HttpServletResponse> currentResponse = new ThreadLocal<HttpServletResponse>();

    /**
     * 线程安全初始化reque，respose对象
     *
     * @param request
     * @param response
     * @date 2015年11月24日
     * @author 漂泊者及其影子
     */
    @ModelAttribute
    public void initReqAndRep(HttpServletRequest request, HttpServletResponse response) {
        currentRequest.set(request);
        currentResponse.set(response);
    }


    /**
     * 线程安全
     *
     * @return
     * @date 2015年11月24日
     * @author 漂泊者及其影子
     */
    public HttpServletRequest request() {
        return (HttpServletRequest) currentRequest.get();
    }

    /**
     * 线程安全
     *
     * @return
     * @date 2015年11月24日
     * @author 漂泊者及其影子
     */
    public HttpServletResponse response() {
        return (HttpServletResponse) currentResponse.get();
    }

}
