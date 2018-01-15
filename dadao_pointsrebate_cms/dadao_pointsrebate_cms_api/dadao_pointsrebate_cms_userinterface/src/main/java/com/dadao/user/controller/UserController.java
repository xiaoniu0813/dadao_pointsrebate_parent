package com.dadao.user.controller;

import com.dadao.user.entity.*;
import com.dadao.user.service.*;
import com.dadao.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by NFY on 2017-07-16.
 */
@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserAccountService service;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserTransactionRecodService userTransactionRecodService;
    @Autowired
    private UserIntegralRecordingService userIntegralRecordingService;
    @Autowired
    private IUserIntegralService userIntegralService;




    @PostMapping(value = "userLogin")
    public Object userLogin(UserAccount userAccount, HttpServletRequest request, HttpServletResponse response) {
        userAccount.setPassword(EncryptUtil.getEncodeStr(userAccount.getPassword()));
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS,service.findByLogin(userAccount)));
    }

    @PostMapping(value = "addUser")
    public Object saveUser(UserAccount userAccount, HttpServletRequest request, HttpServletResponse response) {
        userAccount.setPassword(EncryptUtil.getEncodeStr((userAccount.getPassword())));
        userAccount.setToken(DigestUtils.md5DigestAsHex(new Date().toString().getBytes()));
        boolean result = service.saveUser(userAccount);
        return DADAO.encryption(request, response, new Result(result ? ResultCode.SYS_SUCCESS : ResultCode.SYS_FAIL));
    }

    @PostMapping(value = "showUserTransactionRecodPOByPage")
    public Object showUserTransactionRecodPOByPage(UserTransactionRecodPO userTransactionRecodPO, HttpServletRequest request, HttpServletResponse response) {
        POPage poPage = userTransactionRecodService.showUserTransactionRecodPOByPage(userTransactionRecodPO);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, poPage.getList()));

    }

    @GetMapping(value = "/exportUserTransactionRecod")
    public void exportUserTransactionRecod(UserTransactionRecodPO userTransactionRecodPO, HttpServletRequest request, HttpServletResponse response) {
        //导出消费记录excel
        List<UserTransactionRecodPO> list = userTransactionRecodService.findByEntity(userTransactionRecodPO);
        String param[] = {"消费编号", "消费时间", "消费商户", "消费金额", "支付方式", "支出收入类型", "交易类型"};
        InputStream is = ExcelUtil.exportExcel(list, param);
        ExcelUtil.print(request, response, is);
    }

    @PostMapping(value = "findUserInfoByPage")
    public Object findUserInfoByPage(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response) {
        BasePage basePage = userInfoService.findUserInfoByPage(userInfo);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, basePage));
    }

    @PostMapping(value = "findUser")
    public Object findUser(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response) {
        UserInfo user = userInfoService.findUser(userInfo);
        return DADAO.encryption(request, response, new Result(user != null ? ResultCode.SYS_SUCCESS : ResultCode.SYS_FAIL, user));
    }

    @PostMapping(value = "findUserIntegralRecordingByPage")
    public Object findUserIntegralRecordingByPage(UserIntegralRecordingPO userIntegralRecordingPO, HttpServletRequest request, HttpServletResponse response) {
        QueryResult queryResult = userIntegralRecordingService.findUserIntegralRecordingByPage(userIntegralRecordingPO);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, queryResult));
    }

    @GetMapping(value = "/exportUserIntegralRecording")
    public void exportUserIntegralRecording(UserIntegralRecordingPO userIntegralRecordingPO, HttpServletRequest request, HttpServletResponse response) {
        //导出用户积分信息excel
        List<UserIntegralRecordingPO> list = userIntegralRecordingService.exportUserIntegralRecordingPO(userIntegralRecordingPO);
        String param[] = {"编号", "获得的积分", "发生方向(0增加、1减少)", "市场等级", "创建时间"};
        InputStream is = ExcelUtil.exportExcel(list, param);
        ExcelUtil.print(request, response, is);

    }

    @PostMapping(value = "/detailsButtons")
    public Object details(int direction, long objectId, HttpServletRequest request, HttpServletResponse response) {
      //详情 direction=0 objectId为订单表id direction=1 objectId返现记录表Id
        Object object = userIntegralRecordingService.objectDetails(direction, objectId);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, object));
    }

    /**
     * 询用户有效积分（当前积分减去7天内获得的积分） --niufy
     * @param pageNum
     * @param userIntegral
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/listUserIntegral")
    public Object listUserIntegral(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, UserIntegral userIntegral, HttpServletRequest request, HttpServletResponse response){
        userIntegral.setBeginIndex((pageNum - 1) * userIntegral.getPageSize());
        QueryResult result = userIntegralService.listUserIntegral(userIntegral);
        if (result != null){
            result.setPageNum(pageNum);
        }
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, result));
    }

    /**
     * 商户积分列表
     */
    @PostMapping("/findBusinessIntegralList")
    public Object findBusinessesIntegral(@RequestParam(required = true) Long marketId, @RequestParam(required = true)BigDecimal currentIntegralUpper, @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize, HttpServletRequest request, HttpServletResponse response){
        return userIntegralRecordingService.findBusinessesIntegral(marketId,currentIntegralUpper,pageNum,pageSize);
    }

}
