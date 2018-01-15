package com.dadao.temp.controller;

import com.dadao.temp.service.ITempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by YunQiang on 2017/8/16
 */
@RestController
public class TempController {

    @Autowired
    private ITempService iTempService;

    @PostMapping("/delUser")
    public Object delUser(String phone, String pwd, String merchant, HttpServletRequest request, HttpServletResponse response){
        return iTempService.delUser(phone, pwd, merchant);
    }

}
