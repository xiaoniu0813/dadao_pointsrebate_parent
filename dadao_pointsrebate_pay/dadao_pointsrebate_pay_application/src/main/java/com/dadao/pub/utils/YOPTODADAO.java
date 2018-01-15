package com.dadao.pub.utils;

import com.dadao.yop.service.Md5Utils;
import com.dadao.yop.service.YeepayService;
import com.yeepay.g3.sdk.yop.client.YopClient3;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 重新封装YeepayService
 *
 * @auther NFY niufuyang
 * @create 2017-11-16
 */
public class YOPTODADAO {
    /**
     * 获取子商户密钥
     * @return
     */
    public static String getMerchantKey(String parentMerchantNo,String merchantNo){
        Map<String, String> params = new HashMap<>();
        params.put("parentMerchantNo", parentMerchantNo);
        params.put("merchantNo", merchantNo);

        Map<String, String> result = new HashMap<>();
        String uri = YeepayService.getUrl(YeepayService.HMACKEYQUERY_URL);
        result = YeepayService.requestYOP(params, uri,YeepayService.HMACKEYQUERY);
        return result.get("merHmacKey");
    }

    /**
     * 获得交易token
     * @param params
     * @param uri
     * @param paramSign
     * @param paramHmac
     * @return
     */
    public static Map<String, String> requestYOP(Map<String, String> params, String uri, String[] paramSign, String[] paramHmac){
        Map<String, String> result = new HashMap<String, String>();
        String BASE_URL = YeepayService.getUrl("baseURL");
        String parentMerchantNo=params.get("parentMerchantNo");
        String merchantNo=params.get("merchantNo");
        String hmackey = getMerchantKey(parentMerchantNo,merchantNo);

        YopRequest request = new YopRequest("OPR:" + parentMerchantNo,"",BASE_URL);
        for (int i = 0; i < paramSign.length; i ++) {
            String key = paramSign[i];
            request.addParam(key, params.get(key)==null?"":params.get(key));
        }

        StringBuffer hmacBuffer = new StringBuffer();
        for(int i = 0; i < paramHmac.length; i++){
            String key = paramHmac[i];
            hmacBuffer.append(key).append("=").append(params.get(key)).append("&");
        }
        String hmacStr = hmacBuffer.subSequence(0, hmacBuffer.length()-1).toString();
        System.out.println("hmacStr:"+hmacStr);
        System.out.println("hmackey:"+hmackey);
        String hmac = Md5Utils.encoderHmacSha256(hmacStr, hmackey);
        System.out.println("hmac:"+hmac);
        request.addParam("hmac", hmac);
        System.out.println(request.getParams());
        YopResponse response = YopClient3.postRsa(uri, request);
        System.out.println(response.toString());
        if("FAILURE".equals(response.getState())){
            if(response.getError() != null)
                result.put("code",response.getError().getCode());
            result.put("message",response.getError().getMessage());
            return result;
        }
        if (response.getStringResult() != null) {
            result = YeepayService.parseResponse(response.getStringResult());
        }
        return result;
    }

    /**
     * 拼接URL
     * @param paramValues
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getUrl(Map<String,String> paramValues) throws UnsupportedEncodingException {
        StringBuffer url = new StringBuffer();
        url.append(YeepayService.getUrl("CASHIER"));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < YeepayService.CASHIER.length; i++) {
            String name = YeepayService.CASHIER[i];
            String value = paramValues.get(name);
            if(i != 0){
                stringBuilder.append("&");
            }
            stringBuilder.append(name+"=").append(value);
        }
        String sign = YeepayService.getSign(stringBuilder.toString());
        url.append("?sign="+sign+"&"+stringBuilder);
        return url.toString();
    }
}
