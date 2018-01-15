package com.dadao.callback.controller;

import com.dadao.callback.service.IPayCallbackService;
import com.dadao.order.entity.YOPCallbackParameter;
import com.dadao.util.DADAO;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 支付回调接口
 *
 * @auther NFY niufuyang
 * @create 2017-11-20
 */
@RestController
@RequestMapping(value = "Callback")
public class PayCallbackController {
    @Autowired
    private IPayCallbackService iPayCallbackService;

    @RequestMapping(value = "YOPPayCallback")
    public Object YOPPayCallback(String response,HttpServletRequest request, HttpServletResponse responses) throws Exception {
        return iPayCallbackService.YOPPayCallback(response);
    }
}
