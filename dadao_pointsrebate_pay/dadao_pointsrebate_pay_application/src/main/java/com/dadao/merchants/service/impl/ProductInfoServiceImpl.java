package com.dadao.merchants.service.impl;

import com.dadao.merchants.entity.ProductsInfo;
import com.dadao.merchants.service.ProductInfoService;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import com.dadao.yop.service.YopProductInfoService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YunQiang on 2017/11/14
 * @author YunQiang
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService{
    @Override
    public Result createProductInfo(ProductsInfo productsInfo) {
        String json = "";
        try{
            String[] payProducts = productsInfo.getPayProductMap();
            String[] payScenarios = productsInfo.getPayScenarioMap();
            String webUrl_web = productsInfo.getWebUrl_h5();
            String icp = productsInfo.getIcp();
            String webUrl_h5 = productsInfo.getWebUrl_h5();
            String appName = productsInfo.getAppName();
            String appDownloadUrl = productsInfo.getAppDownloadUrl();
            for (String string : payProducts) {
                System.out.print(string+",");
            }
            System.out.println();
            for (String string : payScenarios) {
                System.out.print(string+"--");
            }
            System.out.println();
            String ONE_KEY_PAY_DEBIT = productsInfo.getONE_KEY_PAY_DEBIT();
            String ONE_KEY_PAY_CREDIT = productsInfo.getONE_KEY_PAY_CREDIT();
            String B2C_PAY = productsInfo.getB2C_PAY();
            String B2B_PAY = productsInfo.getB2B_PAY();
            String WECHAT_ATIVE_SCAN = productsInfo.getWECHAT_ATIVE_SCAN();
            String ALIPAY = productsInfo.getALIPAY();
            String JD_ATIVE_SCAN = productsInfo.getJD_ATIVE_SCAN();
            String UPOP_ATIVE_SCAN = productsInfo.getUPOP_ATIVE_SCAN();
            String weChatId = productsInfo.getWeChatId();
            String officialAccAppId = productsInfo.getOfficialAccAppId();
            String recommendOfficialAccAppId = productsInfo.getRecommendOfficialAccAppId();
            String officialAccAuthorizeDirectory = productsInfo.getOfficialAccAuthorizeDirectory();
            String OFFICIAL_ACCOUNT_PAY = productsInfo.getOFFICIAL_ACCOUNT_PAY();
            String aliPayPID = productsInfo.getAliPayPID();
            String ZFB_SHH = productsInfo.getZFB_SHH();
            String WECHAT_SCAN = productsInfo.getWECHAT_SCAN();
            String ALIPAY_SCAN = productsInfo.getALIPAY_SCAN();
            String JD_PASSIVE_SCAN = productsInfo.getJD_PASSIVE_SCAN();
            String UPOP_PASSIVE_SCAN = productsInfo.getUPOP_PASSIVE_SCAN();
            String WECHAT_H5 = productsInfo.getWECHAT_H5();
            String ALIPAY_H5 = productsInfo.getALIPAY_H5();


            Map<String, String> payProductMap = new HashMap<String, String>();
            payProductMap.put("ONE_KEY_PAY_DEBIT", ONE_KEY_PAY_DEBIT);
            payProductMap.put("ONE_KEY_PAY_CREDIT", ONE_KEY_PAY_CREDIT);
            payProductMap.put("B2C_PAY", B2C_PAY);
            payProductMap.put("B2B_PAY", B2B_PAY);
            payProductMap.put("WECHAT_ATIVE_SCAN", WECHAT_ATIVE_SCAN);
            payProductMap.put("ALIPAY", ALIPAY);
            payProductMap.put("JD_ATIVE_SCAN", JD_ATIVE_SCAN);
            payProductMap.put("UPOP_ATIVE_SCAN", UPOP_ATIVE_SCAN);
            payProductMap.put("weChatId", weChatId);
            payProductMap.put("officialAccAppId", officialAccAppId);
            payProductMap.put("recommendOfficialAccAppId", recommendOfficialAccAppId);
            payProductMap.put("officialAccAuthorizeDirectory", officialAccAuthorizeDirectory);
            payProductMap.put("OFFICIAL_ACCOUNT_PAY", OFFICIAL_ACCOUNT_PAY);
            payProductMap.put("aliPayPID", aliPayPID);
            payProductMap.put("ZFB_SHH", ZFB_SHH);
            payProductMap.put("WECHAT_SCAN", WECHAT_SCAN);
            payProductMap.put("ALIPAY_SCAN", ALIPAY_SCAN);
            payProductMap.put("JD_PASSIVE_SCAN", JD_PASSIVE_SCAN);
            payProductMap.put("UPOP_PASSIVE_SCAN", UPOP_PASSIVE_SCAN);
            payProductMap.put("WECHAT_H5", WECHAT_H5);
            payProductMap.put("ALIPAY_H5", ALIPAY_H5);

            Map<String, String> payScenarioMap = new HashMap<String, String>();

            payScenarioMap.put("webUrl_web", webUrl_web);
            payScenarioMap.put("icp", icp);
            payScenarioMap.put("webUrl_h5", webUrl_h5);
            payScenarioMap.put("appName", appName);
            payScenarioMap.put("appDownloadUrl", appDownloadUrl);

            String jsonProduct = YopProductInfoService.createProduct(payProducts, payProductMap);
            String jsonScenario = YopProductInfoService.createScenario(payScenarios, payScenarioMap);
            System.out.println("payProductMap:\n"+payProductMap);
            System.out.println("payScenarioMap:\n"+payScenarioMap);
            System.out.println("jsonProduct:\n"+jsonProduct);
            System.out.println("jsonScenario:\n"+jsonScenario);

            json = "{"+jsonProduct+","+jsonScenario+"}";
        }catch (Exception e){
            return new Result(ResultCode.INPUT_PARAMS_FAIL, "此接口的参数由前台限制，具体请参考易宝的生成productsInfo页面");
        }
        return new Result(ResultCode.SYS_SUCCESS, json);
    }
}
