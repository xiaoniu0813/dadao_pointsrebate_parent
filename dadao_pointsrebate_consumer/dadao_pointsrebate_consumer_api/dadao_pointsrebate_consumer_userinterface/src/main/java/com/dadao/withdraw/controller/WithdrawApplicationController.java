package com.dadao.withdraw.controller;

import com.dadao.pub.utils.DADAO;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import com.dadao.withdraw.entity.WithdrawApplicationPO;
import com.dadao.withdraw.entity.WithdrawList;
import com.dadao.withdraw.service.IWithdrawApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 提现申请处理
 *
 * @auther NFY niufuyang
 * @create 2017-08-08
 */
@RestController
public class WithdrawApplicationController {
    @Autowired
    private IWithdrawApplicationService withdrawApplicationService;

    /**
     * 提现列表
     * @return
     * @throws Exception
     */
    @PostMapping(value = "findWithdrawList")
    public Object findWithdrawList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, WithdrawList withdrawList, String token, HttpServletRequest request, HttpServletResponse response) throws Exception {
        withdrawList.setBeginIndex((pageNum - 1) * withdrawList.getPageSize());
        QueryResult result = withdrawApplicationService.findWithdrawList(withdrawList, token);
        if (result != null)
            result.setPageNum(pageNum);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, result));
    }

    /**
     * 提现申请
     * @return
     */
    @PostMapping(value = "withdrawApplication")
    public Object withdrawApplication(String payPassword,WithdrawApplicationPO withdrawApplicationPO, String token, HttpServletRequest request, HttpServletResponse response) {
        return DADAO.encryption(request, response, withdrawApplicationService.withdrawApplication(withdrawApplicationPO,token,payPassword));
    }

}
