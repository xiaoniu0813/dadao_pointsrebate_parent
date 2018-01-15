package com.dadao.constants;

import java.math.BigDecimal;

/**
 * 常量池
 * Created by yangrui on 2017/7/16.
 */
public class GeneralConstants {
    /*首页分类类型*/
    public static final Integer INDEX_CATEGORY = 10001;
    /*提现扣税比例*/
    public static final BigDecimal WITHDRAW_TAX_PROPORTION=new BigDecimal("0.08");
    /*平台手续费*/
    public static final BigDecimal FEE_ONE=new BigDecimal("1");
    public static final BigDecimal FEE_FIVE=new BigDecimal("5");
    /*提现限额*/
    public static final BigDecimal WITHDRAW_QUOTA=new BigDecimal("2000");
    /*最小提现限额*/
    public static final BigDecimal MINIMAL_WITHDRAW_QUOTA=new BigDecimal("10");

}
