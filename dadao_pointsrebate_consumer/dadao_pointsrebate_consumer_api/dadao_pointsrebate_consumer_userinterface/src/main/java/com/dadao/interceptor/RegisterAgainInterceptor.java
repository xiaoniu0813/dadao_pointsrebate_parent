package com.dadao.interceptor;

import com.dadao.utils.JedisUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 防止重复注册
 * @author YunQiang
 */
@Component
public class RegisterAgainInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String phone = request.getParameter("phone");
        if(phone != null && !"".equals(phone)) {
            //判断redis中是否有相同的手机号访问过注册接口
            String prefix = "REGISTER";
            String phoneSave = JedisUtil.get(prefix + phone);
            if (phoneSave == null){
                //将请求注册的手机号放入redis中，设置过期时间为5秒过期
                JedisUtil.set(prefix + phone, phone, 5);
                return true;
            } else {
                return false;
            }
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
