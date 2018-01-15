package com.dadao.util;

import com.dadao.user.entity.UserAccount;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    public UserAccount getUserAccount(HttpServletRequest request) {
        return (UserAccount) request.getAttribute("userAccount");
    }
}
