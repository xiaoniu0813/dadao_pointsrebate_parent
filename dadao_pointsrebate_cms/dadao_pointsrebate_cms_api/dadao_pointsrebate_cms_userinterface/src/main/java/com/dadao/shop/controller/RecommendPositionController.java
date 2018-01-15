package com.dadao.shop.controller;


import com.dadao.shop.service.RecommendPositionService;
import com.dadao.shop.entity.RecommendPosition;
import com.dadao.utils.DADAO;

import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Created by guoyu on 2017/7/19.
 */
@RestController
public class RecommendPositionController {

    @Autowired
    private RecommendPositionService recommendService;

    /**
     * 增加推荐位
     * @param recommend
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/addRecommend")
    public Object  addRecommend(RecommendPosition recommend, HttpServletRequest request, HttpServletResponse response){
        boolean flag = recommendService.addRecommend(recommend) == 1;
        return  DADAO.encryption(request,response,new Result(flag? ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL,flag));
    }

    @PostMapping(value="findTotalRecommend")
    public Object findTotalRecommend(RecommendPosition recommend, HttpServletRequest request, HttpServletResponse response){
        int totalRecommend = recommendService.findTotalRecommend(recommend);
        boolean flag =  totalRecommend >= 0;
        return DADAO.encryption(request,response,new Result(flag? ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL, totalRecommend));
    }


    @PostMapping(value = "/findByBan")
    public Object findByBan(RecommendPosition recommendPosition, HttpServletRequest request, HttpServletResponse response){
        List list = recommendService.findByEntity(recommendPosition);

        return  DADAO.encryption(request,response,new Result(list!=null? ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL,list));
    }

    @PostMapping(value = "/findRecommendPositionById")
    public Object findById(int recommendId, HttpServletRequest request, HttpServletResponse response){

        RecommendPosition recommendPosition = (RecommendPosition) recommendService.findById(recommendId);

        return  DADAO.encryption(request,response,new Result(recommendPosition!=null? ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL,recommendPosition));
    }
    @PostMapping(value = "/updateByRecommendPosition")
    public Object updateByRecommendPosition(RecommendPosition recommendPosition,HttpServletRequest request,HttpServletResponse response){
        boolean flag = recommendService.update(recommendPosition) == 1;
        return DADAO.encryption(request, response, new Result(flag? ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL,flag));

    }
    @PostMapping(value = "/delete")
    public Object delete(RecommendPosition recommendPosition,HttpServletRequest request,HttpServletResponse response){
    boolean flag = recommendService.delete(recommendPosition) == 1;
    return DADAO.encryption(request, response, new Result(flag? ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL,flag));
    }
    @PostMapping(value = "/findStartPage")
    public Object findStartPage(RecommendPosition recommendPosition,HttpServletRequest request,HttpServletResponse response){
        RecommendPosition recommendPosition1 = recommendService.findStartPage(recommendPosition);
        return DADAO.encryption(request,response,new Result(ResultCode.SYS_SUCCESS,recommendPosition1));
    }
    @PostMapping(value = "/saveStartPage")
    public Object saveStartPage(RecommendPosition recommendPosition,HttpServletResponse response,HttpServletRequest request){
        int isSave = recommendService.saveStartPage(recommendPosition);
        return DADAO.encryption(request, response, new Result(isSave == 1? ResultCode.SYS_SUCCESS:ResultCode.SYS_FAIL,isSave));
    }

}
