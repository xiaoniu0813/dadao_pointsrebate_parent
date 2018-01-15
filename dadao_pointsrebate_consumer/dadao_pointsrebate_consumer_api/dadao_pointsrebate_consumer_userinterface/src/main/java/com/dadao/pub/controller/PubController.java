package com.dadao.pub.controller;

import com.dadao.category.entity.Category;
import com.dadao.city.entity.CityAreaCountyPO;
import com.dadao.pub.service.PubService;
import com.dadao.pub.utils.DADAO;
import com.dadao.pub.utils.SendCodeVerification;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.service.UserService;
import com.dadao.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 公用接口
 * Created by yangrui on 2017/7/16.
 */
@RestController
public class PubController {

    private Logger logger = LoggerFactory.getLogger(PubController.class);

    @Autowired
    private PubService pubService;

    @Autowired
    private UserService userService;

    /**
     * @Author: yangrui
     * @Description: 获取城市列表
     * @Date: 下午2:50 2017/7/16
     */
    @GetMapping(value = "listCities/{pid}")
    public Object listCities(@PathVariable int pid, HttpServletRequest request, HttpServletResponse response) {
        Object obj = pubService.findByPid(pid);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, obj));
    }

    /**
     * @Author: yangrui
     * @Description: 获取首页分类列表
     * @Date: 下午5:49 2017/7/16
     */
    @PostMapping(value = "listCategories")
    public Object listCategories(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, Category category, HttpServletRequest request, HttpServletResponse response) {
        category.setBeginIndex((pageNum - 1) * category.getPageSize());
        QueryResult result = pubService.findByPage(category);
        result.setPageNum(pageNum);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, result));
    }

    /**
     * 发送短信接口   ----niufy
     */
    @PostMapping(value = "sendCode")
    public Object sendCode(String phone, @RequestParam(value = "type", required = false, defaultValue = "-1") int type, @RequestParam(value = "merchant", required = false, defaultValue = "0") int merchant, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            return DADAO.encryption(request, response, new Result(SendCodeVerification.Verification(phone, type, merchant, userService), ""));
        } catch (Exception e) {
            System.out.println("PubController Error------>>>" + e);
            return DADAO.encryption(request, response, new Result(ResultCode.SYS_FAIL, ""));
        }
    }

    /**
     * 获取所有城市列表
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "findAllCityList")
    public Object findAllCityList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object obj = pubService.findAllCityList();
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, obj));
    }

    @PostMapping(value = "findLowerCityList")
    public Object findLowerCityList(CityAreaCountyPO cityAreaCountyPO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return DADAO.encryption(request, response, pubService.findLowerCity(cityAreaCountyPO));
    }



}
