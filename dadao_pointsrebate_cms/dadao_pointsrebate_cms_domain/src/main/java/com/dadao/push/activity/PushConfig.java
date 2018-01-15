package com.dadao.push.activity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by e on 2017-07-23.
 */
@Component
public class PushConfig {

    @Value("${PUSH.APP_KEY}")
    public String  APP_KEY;
    @Value("${PUSH.MASTER_SECRET}")
    public String MASTER_SECRET;

}
