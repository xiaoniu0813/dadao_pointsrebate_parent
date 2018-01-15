package com.dadao.bank.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 操作银行相关信息接口
 *
 * @auther NFY niufuyang
 * @create 2017-11-10
 */
public interface IBankService {
    Object getBankBranchInfo(String headBankCode, String provinceCode, String cityCode,HttpServletRequest request, HttpServletResponse response);
}
