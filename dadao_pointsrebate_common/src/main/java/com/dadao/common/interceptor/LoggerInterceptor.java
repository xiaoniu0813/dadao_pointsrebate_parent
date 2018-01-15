package com.dadao.common.interceptor;

import net.sf.json.JSONObject;
import nl.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by e on 2017-08-02.
 */
public class LoggerInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String method = request.getMethod();
        String requestUrl = request.getRequestURL().toString();
        String userAgent = request.getHeader("user-agent");

        //UserAgent agent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));

        //获取参数
        Map<String, String> parameterMap = request.getParameterMap();
        TreeMap<String, String> parameterTreeMap = new TreeMap<String, String>();
        parameterTreeMap.putAll(parameterMap);

        logger.info("logger.LoggerInterceptor.info\tuserAgent={}\tmethod={}\trequestUrl={}\tparameterMap={}",
                userAgent,
                method,
                requestUrl,
                JSONObject.fromObject(parameterMap).toString());

        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
