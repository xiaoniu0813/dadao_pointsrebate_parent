package com.dadao.utils;

import java.math.BigDecimal;

/**
 * Bigdecimal运算帮助类
 *
 * @auther NFY niufuyang
 * @create 2017-08-09
 */
public class ArithUtil { //默认除法运算精度
    private static final int DEF_DIV_SCALE = 10; //这个类不能实例化

    private ArithUtil(){
    }
    /**
     * 提供精确的加法运算。
     * @param b1 被加数
     * @param b2 加数
     * @return 两个参数的和
     */
    public static BigDecimal add(BigDecimal b1,BigDecimal b2){
        return b1.add(b2);
    }
    /**
     * 提供精确的减法运算。
     * @param b1 被减数
     * @param b2 减数
     * @return 两个参数的差
     */
    public static BigDecimal sub(BigDecimal b1,BigDecimal b2){
        return b1.subtract(b2);
    }
    /**
     * 提供精确的乘法运算。
     * @param b1 被乘数
     * @param b2 乘数
     * @return 两个参数的积
     */
    public static BigDecimal mul(BigDecimal b1,BigDecimal b2){
        return b1.multiply(b2);
    }
    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     * @param b1 被除数
     * @param b2 除数
     * @return 两个参数的商
     */
    public static BigDecimal div(BigDecimal b1,BigDecimal b2){
        return div(b1,b2,DEF_DIV_SCALE);
    }
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param b1 被除数
     * @param b2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static BigDecimal div(BigDecimal b1,BigDecimal b2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param b1 被除数
     * @param b2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static BigDecimal divDown(BigDecimal b1,BigDecimal b2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        return b1.divide(b2,scale,BigDecimal.ROUND_DOWN);
    }
    /**
     * 提供精确的小数位四舍五入处理。
     * @param bigDecimal 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static BigDecimal round(BigDecimal bigDecimal,int scale){
        if(scale<0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal one = new BigDecimal("1");
        return bigDecimal.divide(one,scale,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 提供精确的小数位不做四舍五入处理。
     * @param bigDecimal 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static BigDecimal roundDown(BigDecimal bigDecimal,int scale){
        if(scale<0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal one = new BigDecimal("1");
        return bigDecimal.divide(one,scale,BigDecimal.ROUND_DOWN);
    }

}
