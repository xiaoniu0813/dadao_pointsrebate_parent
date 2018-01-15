package com.dadao.utils;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by e on 2017-07-29.
 */
public class DaDaoUtil {

    private static Logger logger = LoggerFactory.getLogger(JedisUtil.class);

    /**
     * 发送验证码至手机
     *
     * param phoneNum 接受验证码的手机号
     * @return
     * @throws Exception
     */
    public static void sendVerifyCode(String phoneNumber) throws Exception {
        // 1.调用短信接口，将验证码发送至手机,随机码生成
        Integer code = getRandomNum();
        SmsSendUtil.sendCode(phoneNumber, code.toString());
        // 2.将手机号和验证码存入Redis
        JedisUtil.set(phoneNumber, code.toString(), 60 * 15);// 设置超时时间15分钟
    }

    /**
     * 验证码验证
     *
     * @param phoneNum 接受验证码的手机号
     * @return
     * @throws Exception
     */
    public static boolean verification(String phoneNum, String verifyCode) {
        // 1.根据手机号，从Redis服务器获取验证码value
        String verifyCodeOld = JedisUtil.get(phoneNum);// 设置超时时间10分钟
        // 2.对用户传入的验证码与Redis服务器取回的验证码做校验
        return !(StringUtils.isEmpty(verifyCodeOld) || !verifyCodeOld.equals(verifyCode));
    }

    public static int getRandomNum() {
        int x;// 定义两变量
        Random ne = new Random();// 实例化一个random的对象ne
        x = ne.nextInt(9999 - 1000 + 1) + 1000;// 为变量赋随机值1000-9999
        return x;
    }

    /**
     * 将java对象转换为hashMap
     * @param obj
     * @return
     */
    public static HashMap<String, Object> objToHash(Object obj){
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        Class clazz = obj.getClass();
        List<Class> clazzs = new ArrayList<Class>();
        do {
            clazzs.add(clazz);
            clazz = clazz.getSuperclass();
        } while (!clazz.equals(Object.class));
        for (Class iClazz : clazzs) {
            Field[] fields = iClazz.getDeclaredFields();
            for (Field field : fields) {
                Object objVal = null;
                field.setAccessible(true);
                try {
                    objVal = field.get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                hashMap.put(field.getName(), objVal);
            }
        }
        return hashMap;
    }

    public static HashMap<String, String> objToMapStr(Object obj){
        HashMap<String, String> hashMap = new HashMap<>();
        Class clazz = obj.getClass();
        List<Class> clazzs = new ArrayList<Class>();
        do {
            clazzs.add(clazz);
            clazz = clazz.getSuperclass();
        } while (!clazz.equals(Object.class));
        for (Class iClazz : clazzs) {
            Field[] fields = iClazz.getDeclaredFields();
            for (Field field : fields) {
                String objVal = null;
                field.setAccessible(true);
                try {

                        objVal = "" + field.get(obj);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                hashMap.put(field.getName(), objVal);
            }
        }
        return hashMap;
    }

    /**
     * 将易宝返回的数据转成大道的数据
     * @param responseYop 易宝api返回数据
     * @return
     */
    public static Result YopResult2DadaoResult(Map responseYop){
        if(responseYop.get("returnCode").equals("REG00000")){
            return new Result(ResultCode.SYS_SUCCESS, responseYop);
        }else{
            return new Result(ResultCode.YOP_ERROR_MSG, responseYop);
        }
    }

    /**
     * 生成入网请求号
     */
    private static String generateRequestNo(String type){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String requestDate = sdf.format(date);
        String requestNo = type + date.getTime();
        return requestNo;
    }

    /**
     *个人注册入网请求号
     * @return
     */
    public static String  generatePersonageRquestNo(){
        return generateRequestNo("YOP_PERSONREG");
    }

    /**
     *个体工商户入网请求号
     * @return
     */
    public static String  generateIndividualregRquestNo(){
        return generateRequestNo("YOP_INDIVIDUALREG");
    }

    /**
     *企业注册入网请求号
     * @return
     */
    public static String  generateEnterpriseregRquestNo(){
        return generateRequestNo("YOP_ENTERPRISEREG");
    }


    /**
     * 个人商户注册开通产品
     */
    public static String getPersonageProductInfo(){
        return "";
    }


    /**
     *个体工商户注册开通产品
     */
    public static String getIndividualregProductInfo(){
        return "";
    }

    /**
     * 企业注册开通产品
     */
    public static String getEnterpriseregProductInfo(){
        return "";
    }

    /**
     * 分页计算总页数
     * @param totalSize
     * @param pageSize
     * @return
     */
    public static long getTotalPage(long totalSize, int pageSize){
        System.out.println(totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1);
        return totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
    }

    /**
     * 分页计算下标
     */
    public static long getBeginIndex(long pageNum, long pageSize){
        return (pageNum - 1) * pageSize;
    }

    /**
     * 处理当前页
     * @param pageNum 当前页
     * @param totalPage 总页数
     * @return
     */
    public static long dealWithPageNum(Long pageNum, Long totalPage){
        if (pageNum == null){
            pageNum = 1L;
        }
        if(pageNum <= 0){
            pageNum = 1L;
        }
        return pageNum;
    }

    /**
     * 极光推送
     */

    private static Logger LOG = LoggerFactory.getLogger(DaDaoUtil.class);

    private static final String MASTER_SECRET_CONSUMER = "57cdc23e019d9d650433f4cb";

    private static final String APP_KEY_CONSUMER = "d45e10494635b108bd2cdc1b";

    private static final String MASTER_SECRET_BUSINESS = "b647d09d4b4a2e928fbdd780";

    private static final String APP_KEY_BUSINESS = "4c105431ee1b0ac467839feb";

    private static void pushForUser(String pushType, String masterKey, String appKey, String userId, String message) {
        //创建极光推送客户端实例
        JPushClient jPushClient = new JPushClient(masterKey, appKey, null, ClientConfig.getInstance());
        //创建PushPayload(推送对象)
        PushPayload payload = null;
        //所有用户
        String flag = "USERS";
        if (flag.equals(pushType)){
            payload = buildPushObject_all_all_alert(message);
        }
        //单个用户
        flag = "USER";
        if(flag.equals(pushType)){
            payload = buildPushObject_all_all_alert(userId, message);
        }
        try {
            PushResult result = jPushClient.sendPush(payload);
            LOG.info("推送结果 - " + result);
        } catch (APIConnectionException e) {
            // Connection error, should retry later
            LOG.error("连接错误, 应该稍后重试", e);
        } catch (APIRequestException e) {
            //给特定用户推送时发生了错误
            String pushObj = "";
            if (flag.equals("USER")){
                pushObj = "给" + userId + "推送消息时发生了错误，";
            }
            // Should review the error, and fix the request
            LOG.error(pushObj + "应该检查错误，并修复请求", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    private static PushPayload buildPushObject_all_all_alert(String userId,String message) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(userId))
                .setNotification(Notification.alert(message))
                .build();
    }

    private static PushPayload buildPushObject_all_all_alert(String message) {
        return PushPayload.alertAll(message);
    }

    /**
     * 向所有用户推送消息
     * @param message 信息内容
     */
    public static void pushForAllConsumer(String message) {
        pushForUser("USERS",MASTER_SECRET_CONSUMER, APP_KEY_CONSUMER,null, message);
    }

    /**
     * 向所有商户推送消息
     * @param message 信息内容
     */
    public static void pushForAllBusiness(String message) {
        pushForUser("USERS", MASTER_SECRET_BUSINESS, APP_KEY_BUSINESS,null, message);
    }

    /**
     * 向特定用户推送消息
     * @param userId 用户userId
     * @param message 信息内容
     */
    public static void pushForConsumer(String userId, String message) {
        pushForUser("USER",MASTER_SECRET_CONSUMER,APP_KEY_CONSUMER, userId, message);
    }

    /**
     * 向特定商户推送消息
     * @param userId 用户userId
     * @param message 信息内容
     */
    public static void pushForBusiness(String userId, String message) {
        pushForUser("USER",MASTER_SECRET_BUSINESS, APP_KEY_BUSINESS, userId, message);
    }

}
