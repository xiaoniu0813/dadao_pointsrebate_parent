package com.dadao.cityareacounty.controller;

import com.dadao.city.entity.CityAreaCountyPO;
import com.dadao.cityareacounty.service.ICityAreaCountService;
import com.dadao.utils.DADAO;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 查询省市信息
 *
 * @auther NFY niufuyang
 * @create 2017-11-13
 */
@RestController
public class CityAreaCountController {

    @Autowired
    private ICityAreaCountService iCityAreaCountService;

    /**
     * 查询省、直辖市信息
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/findAllProvinceList")
    public Object findAllProvinceList(HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request,response,new Result(ResultCode.SYS_SUCCESS, iCityAreaCountService.findAllProvinceList()));
    }

    /**
     * 根据parentid查询下级菜单
     *
     * @param cityAreaCountyPO
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/findLowerCityById")
    public Object findLowerCityById(CityAreaCountyPO cityAreaCountyPO,HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request,response,new Result(ResultCode.SYS_SUCCESS,iCityAreaCountService.findLowerCityById(cityAreaCountyPO)));
    }
}
