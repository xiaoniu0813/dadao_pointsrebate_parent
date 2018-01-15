package com.dadao.user.service.impl;

import com.dadao.user.activity.UserActivity;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserBandCard;
import com.dadao.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserActivity userActivity;

    public UserAccount findByToken(String token) {
        return userActivity.findByToken(token);
    }

    public boolean save(UserBandCard userBandCard) {
        return userActivity.save(userBandCard);
    }

    public boolean updateStatus(Long cardId) {
        return userActivity.updateStatus(cardId);
    }
}
