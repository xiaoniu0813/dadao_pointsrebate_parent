package com.dadao.user.service;

import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserBandCard;

public interface UserService {
    UserAccount findByToken(String token);

    boolean save(UserBandCard userBandCard);

    boolean updateStatus(Long cardId);
}
