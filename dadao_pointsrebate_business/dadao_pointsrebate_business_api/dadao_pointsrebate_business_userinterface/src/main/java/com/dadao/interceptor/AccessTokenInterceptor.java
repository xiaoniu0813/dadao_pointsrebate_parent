package com.dadao.interceptor;

import com.dadao.user.entity.UserAccount;
import com.dadao.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessTokenInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(AccessTokenInterceptor.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.debug("method {}", httpServletRequest.getMethod());
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Accept, Content-Type, Origin, Authorization, token");
        httpServletResponse.setHeader("Access-Control-Expose-Headers", "token");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        String method = httpServletRequest.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        } else {
            String token = httpServletRequest.getHeader("token");
            logger.info("business interceptor token = {}", token);
            if (StringUtils.isEmpty(token)) {
                httpServletResponse.setStatus(618);
                logger.error("business interceptor token为空 ");
                return false;
            }
            UserAccount userAccount = (UserAccount) userService.findByToken(token);
            if (userAccount == null) {
                httpServletResponse.setStatus(618);
                logger.error("business interceptor token无效 ");
                return false;
            }

            httpServletRequest.setAttribute("userAccount", userAccount);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
