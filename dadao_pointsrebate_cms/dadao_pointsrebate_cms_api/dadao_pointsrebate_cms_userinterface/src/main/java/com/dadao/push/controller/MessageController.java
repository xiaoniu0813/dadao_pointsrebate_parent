package com.dadao.push.controller;

import com.dadao.push.entity.InformationPO;
import com.dadao.push.service.IMessageService;
import com.dadao.utils.DADAO;
import com.dadao.utils.DaDaoUtil;
import com.dadao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author YunQiang
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private IMessageService iMessageService;

    @PostMapping("/list")
    public Object listInformation(@RequestParam(required = true) long userType, @RequestParam(required = false, defaultValue = "1")Long pageNum, @RequestParam(required = false, defaultValue = "10")Integer pageSize, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, iMessageService.listInformation(userType, pageNum, pageSize));
    }

    @PostMapping("/detail")
    public Object detail(@RequestParam(required = true)long infoId){
        return iMessageService.detailMessage(infoId);
    }

    @PostMapping("/save")
    public Object saveInformation(InformationPO informationPO, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, iMessageService.addInformation(informationPO));
    }

    @PostMapping("/update")
    public Object updateInformation(InformationPO informationPO, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, iMessageService.updateInformation(informationPO));
    }

    @PostMapping("/delete")
    public Object deleteInformation(InformationPO informationPO, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, iMessageService.deleteInformation(informationPO));
    }

}
