package com.dadao.user.controller;

import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserBandCard;
import com.dadao.user.entity.UserTransactionRecodPO;
import com.dadao.user.service.IUserTransactionRecodService;
import com.dadao.user.service.IUserWalloetService;
import com.dadao.user.service.UserService;
import com.dadao.util.BaseController;
import com.dadao.util.DADAO;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 钱包
 *
 * @auther NFY niufuyang
 * @create 2017-08-11
 */
@RestController
public class UserController extends BaseController {

    @Autowired
    private IUserWalloetService iUserWalloetService;

    @Autowired
    private UserService userService;

    @Autowired
    private IUserTransactionRecodService userTransactionRecodService;

    /**
     * 查询商户资产
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @PostMapping(value = "findUserWalloet")
    public Object findUserWalloet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        UserAccount userAccount = getUserAccount(httpServletRequest);
        Long userId = userAccount.getUserId();
        return DADAO.encryption(httpServletRequest, httpServletResponse, new Result(ResultCode.SYS_SUCCESS, iUserWalloetService.findUserWallet(userId)));
    }

    /**
     * @Author: yangrui
     * @Description:绑定银行卡
     * @Date: 下午2:09 2017/8/13
     */
    @PostMapping(value = "saveUserBandCard")
    public Object saveUserBandCard(@Valid UserBandCard userBandCard, BindingResult result, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if (result.hasFieldErrors()) {
            return DADAO.encryption(httpServletRequest, httpServletResponse, new Result(ResultCode.SYS_FAIL, result.getFieldError().getDefaultMessage()));
        }
        UserAccount userAccount = getUserAccount(httpServletRequest);
        Long userId = userAccount.getUserId();
        userBandCard.setUserId(userId);
        boolean aBoolean = userService.save(userBandCard);
        if (aBoolean) {
            return DADAO.encryption(httpServletRequest, httpServletResponse, new Result(ResultCode.SYS_SUCCESS));
        } else {
            return DADAO.encryption(httpServletRequest, httpServletResponse, new Result(ResultCode.SYS_FAIL));
        }
    }

    /**
     * @Author: yangrui
     * @Description: 解绑银行卡
     * @Date: 下午2:14 2017/8/13
     */
    @PostMapping(value = "card/{cardId}/updateStatus")
    public Object updateStatus(@PathVariable Long cardId, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        boolean aBoolean = userService.updateStatus(cardId);
        if (aBoolean) {
            return DADAO.encryption(httpServletRequest, httpServletResponse, new Result(ResultCode.SYS_SUCCESS));
        } else {
            return DADAO.encryption(httpServletRequest, httpServletResponse, new Result(ResultCode.SYS_FAIL));
        }
    }

    /**
     * 查询商户用户交易记录
     * @param pageNum
     * @param userTransactionRecodPO
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "listTransactionRecord")
    public Object listTransactionRecord(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, UserTransactionRecodPO userTransactionRecodPO, HttpServletRequest request, HttpServletResponse response){
        UserAccount userAccount = getUserAccount(request);
        userTransactionRecodPO.setUserId(userAccount.getUserId());
        userTransactionRecodPO.setBeginIndex((pageNum - 1) * userTransactionRecodPO.getPageSize());
        QueryResult result = userTransactionRecodService.listTransactionRecord(userTransactionRecodPO);
        result.setPageNum(pageNum);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS,result));
    }

    /**
     * 查询交易记录明细（作废）
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "infoTransactionRecord<zuofei>")
    public Object infoTransactionRecord(Long transactionId, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS,userTransactionRecodService.infoTransactionRecord(transactionId)));
    }
}
