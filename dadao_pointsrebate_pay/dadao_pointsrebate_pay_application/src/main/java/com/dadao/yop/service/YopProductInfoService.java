package com.dadao.yop.service;

import java.util.Map;

public class YopProductInfoService {
	//支付产品
	private static final String ONEKEY = "ONEKEY";
	private static final String NETBANK = "NETBANK";
	private static final String SCANPAY = "SCANPAY";
	private static final String WECHATOPEN = "WECHATOPEN";
	private static final String ZFBSHH = "ZFBSHH";
	private static final String MSCANPAY = "MSCANPAY";
	private static final String EWALLETH5 = "EWALLETH5";

	//支付场景
	private static final String WEB_ACCESS = "WEB_ACCESS";
	private static final String H5_ACCESS = "H5_ACCESS";
	private static final String APP_ACCESS = "APP_ACCESS";
	/*private static final String OFFICIAL_ACCOUNT_ACCESS = "OFFICIAL_ACCOUNT_ACCESS";
	private static final String FACE_TO_FACE_ACCESS = "FACE_TO_FACE_ACCESS";
	private static final String ZF_SHH_ZFB = "ZF_SHH_ZFB";*/
		
	/**
	 * 支付产品
	 * @param payProducts
	 * @param payProductMap
	 * @return
	 */
	public static String createProduct(String[] payProducts, Map<String, String> payProductMap) {
		StringBuffer json = new StringBuffer();
		json.append("\"payProductMap\":{");
		for (String product : payProducts) {
			if (ONEKEY.equals(product)) {
				json.append(createONEKEY(payProductMap));
			}
			if (NETBANK.equals(product)) {
				json.append(createNETBANK(payProductMap));
			}
			if (SCANPAY.equals(product)) {
				json.append(createSCANPAY(payProductMap));
			}
			if (WECHATOPEN.equals(product)) {
				json.append(createWECHATOPEN(payProductMap));
			}
			if (ZFBSHH.equals(product)) {
				json.append(createZFBSHH(payProductMap));
			}
			if (MSCANPAY.equals(product)) {
				json.append(createMSCANPAY(payProductMap));
			}
			if (EWALLETH5.equals(product)) {
				json.append(createEWALLETH5(payProductMap));
			}
			json.append(",");
		}
		String jsonProduct = json.substring(0, json.length()-1)+"}";
		return jsonProduct;
	}
	
	/**
	 * 支付场景
	 * @param payScenarios
	 * @param payScenarioMap
	 * @return
	 */
	public static String createScenario(String[] payScenarios, Map<String, String> payScenarioMap){
		StringBuffer json = new StringBuffer();
		json.append("\"payScenarioMap\":{");
		for (String scenario : payScenarios) {
			json.append("\""+scenario+"\":{");
			if(WEB_ACCESS.equals(scenario)){
				String webUrl = payScenarioMap.get("webUrl_web");
				json.append("\"webUrl\":\""+webUrl+"\",");
				json.append("\"icp\":\""+webUrl+"\"");
			}
			if(H5_ACCESS.equals(scenario)){
				String webUrl = payScenarioMap.get("webUrl_h5");
				json.append("\"webUrl\":\""+webUrl+"\"");			
			}
			if(APP_ACCESS.equals(scenario)){
				String appName = payScenarioMap.get("appName");
				json.append("\"appName\":\""+appName+"\",");
				String appDownloadUrl = payScenarioMap.get("appDownloadUrl");
				json.append("\"appDownloadUrl\":\""+appDownloadUrl+"\"");
			}
			json.append("},");
		}
		String jsonScenario = json.substring(0, json.length()-1)+"}";
		return jsonScenario;
	}
	
	/**
	 * 一键支付
	 * @param payProductMap
	 * @return
	 */
	public static String createONEKEY(Map<String, String> payProductMap){
		StringBuffer json = new StringBuffer();
		String ONE_KEY_PAY_DEBIT = payProductMap.get("ONE_KEY_PAY_DEBIT");
		System.out.println("ONE_KEY_PAY_DEBIT:"+ONE_KEY_PAY_DEBIT);
		String ONE_KEY_PAY_CREDIT = payProductMap.get("ONE_KEY_PAY_CREDIT");
		System.out.println("ONE_KEY_PAY_CREDIT:"+ONE_KEY_PAY_CREDIT);
		if(ONE_KEY_PAY_DEBIT.equals(ONE_KEY_PAY_CREDIT)){
			json.append("\"ONE_KEY_PAY_MULTIPLE\":{\"dsPayBankMap\":{\"BANK_PAY_WAP\":{\"rateType\":\"PERCENTAGE\",\"rate\":\""+ONE_KEY_PAY_DEBIT+"\"}}}}");
		}else{
			if(!"".equals(ONE_KEY_PAY_DEBIT))
				json.append("\"ONE_KEY_PAY_DEBIT\":{\"dsPayBankMap\":{\"BANK_PAY_WAP\":{\"rateType\":\"PERCENTAGE\",\"rate\":\""+ONE_KEY_PAY_DEBIT+"\"}}},");
			if(!"".equals(ONE_KEY_PAY_CREDIT))
				json.append("\"ONE_KEY_PAY_CREDIT\":{\"dsPayBankMap\":{\"BANK_PAY_WAP\":{\"rateType\":\"PERCENTAGE\",\"rate\":\""+ONE_KEY_PAY_CREDIT+"\"}}}}");
		}
		if("".equals(json)) return "";
		return json.substring(0, json.length()-1);
	}

	/**
	 * 网银支付
	 * @param payProductMap
	 * @return
	 */
	public static String createNETBANK(Map<String, String> payProductMap){
		StringBuffer json = new StringBuffer();
		String B2C_PAY = payProductMap.get("B2C_PAY");
		String B2B_PAY = payProductMap.get("B2B_PAY");
		System.out.println("B2C_PAY:"+B2C_PAY);
		System.out.println("B2B_PAY:"+B2B_PAY);
		if(!"".equals(B2C_PAY))
			json.append("\"B2C_PAY\":{\"dsPayBankMap\":{\"NET_BANK\":{\"rateType\":\"PERCENTAGE\",\"rate\":\""+B2C_PAY+"\"}}},");
		if(!"".equals(B2B_PAY))
			json.append("\"B2B_PAY\":{\"dsPayBankMap\":{\"NET_BANK\":{\"rateType\":\"ONEPAY\",\"rate\":\""+B2B_PAY+"\"}}},");
		if(json.length()<1) return "";
		return json.substring(0, json.length()-1);
	}

	/**
	 * 用户扫码
	 * @param payProductMap
	 * @return
	 */
	public static String createSCANPAY(Map<String, String> payProductMap){
		StringBuffer json = new StringBuffer();
		String WECHAT_ATIVE_SCAN = payProductMap.get("WECHAT_ATIVE_SCAN");
		String ALIPAY = payProductMap.get("ALIPAY");
		String JD_ATIVE_SCAN = payProductMap.get("JD_ATIVE_SCAN");
		String UPOP_ATIVE_SCAN = payProductMap.get("UPOP_ATIVE_SCAN");
		json.append("\"USER_SCAN_PAY\":{\"dsPayBankMap\":{");
		System.out.println("WECHAT_ATIVE_SCAN:"+WECHAT_ATIVE_SCAN);
		System.out.println("ALIPAY:"+ALIPAY);
		System.out.println("JD_ATIVE_SCAN:"+JD_ATIVE_SCAN);
		System.out.println("UPOP_ATIVE_SCAN:"+UPOP_ATIVE_SCAN);
		if(WECHAT_ATIVE_SCAN != "")
			json.append("\"WECHAT_ATIVE_SCAN\":{\"rateType\":\"PERCENTAGE\",\"rate\":\""+WECHAT_ATIVE_SCAN+"\"},");
		if(ALIPAY != "")
			json.append("\"ALIPAY\":{\"rateType\":\"PERCENTAGE\",\"rate\":\""+ALIPAY+"\"},");
		if(JD_ATIVE_SCAN != "")
			json.append("\"JD_ATIVE_SCAN\":{\"rateType\":\"PERCENTAGE\",\"rate\":\""+JD_ATIVE_SCAN+"\"},");
		if(UPOP_ATIVE_SCAN != "")
			json.append("\"UPOP_ATIVE_SCAN\":{\"rateType\":\"PERCENTAGE\",\"rate\":\""+UPOP_ATIVE_SCAN+"\"},");
		if(json.length()<1) return "";
		return json.substring(0, json.length()-1)+"}}";
	}
	
	/**
	 * 公众号支付
	 * @param payProductMap
	 * @return
	 */
	public static String createWECHATOPEN(Map<String, String> payProductMap){
		StringBuffer json = new StringBuffer();
		String OFFICIAL_ACCOUNT_PAY = payProductMap.get("OFFICIAL_ACCOUNT_PAY");
		String weChatId = payProductMap.get("weChatId");
		String officialAccAppId = payProductMap.get("officialAccAppId");
		String recommendOfficialAccAppId = payProductMap.get("recommendOfficialAccAppId");
		String officialAccAuthorizeDirectory = payProductMap.get("officialAccAuthorizeDirectory");
		json.append("\"OFFICIAL_ACCOUNT_PAY\":{\"dsPayBankMap\":{\"WECHAT_OPENID\":{\"rateType\":\"PERCENTAGE\",\"rate\":\""+OFFICIAL_ACCOUNT_PAY+"\"}},");
		System.out.println("OFFICIAL_ACCOUNT_PAY:"+OFFICIAL_ACCOUNT_PAY);
		System.out.println("weChatId:"+weChatId);
		System.out.println("officialAccAppId:"+officialAccAppId);
		System.out.println("recommendOfficialAccAppId:"+recommendOfficialAccAppId);
		System.out.println("officialAccAuthorizeDirectory:"+officialAccAuthorizeDirectory);
		json.append("\"weChatId\":\""+weChatId+"\",");
		json.append("\"officialAccAppId\":\""+officialAccAppId+"\",");
		json.append("\"recommendOfficialAccAppId\":\""+recommendOfficialAccAppId+"\",");
		json.append("\"officialAccAuthorizeDirectory\":\""+officialAccAuthorizeDirectory+"\"}");
		if(json.length()<1) return "";
		return json.toString();
	}

	/**
	 * 生活号支付
	 * @param payProductMap
	 * @return
	 */
	public static String createZFBSHH(Map<String, String> payProductMap){
		StringBuffer json = new StringBuffer();
		String ZFB_SHH = payProductMap.get("ZFB_SHH");
		String aliPayPID = payProductMap.get("aliPayPID");
		json.append("\"ZFB_SHH\":{\"dsPayBankMap\":{\"ALIPAY\":{\"rateType\":\"PERCENTAGE\",\"rate\":\""+ZFB_SHH+"\"}},");
		System.out.println("ZFB_SHH:"+ZFB_SHH);
		System.out.println("aliPayPID:"+aliPayPID);
		json.append("\"aliPayPID\":\""+aliPayPID+"\"}");
		if(json.length()<1) return "";
		return json.toString();
	}

	/**
	 * 商家扫码
	 * @param payProductMap
	 * @return
	 */
	public static String createMSCANPAY(Map<String, String> payProductMap){
		StringBuffer json = new StringBuffer();
		String WECHAT_SCAN = payProductMap.get("WECHAT_SCAN");
		String ALIPAY_SCAN = payProductMap.get("ALIPAY_SCAN");
		String JD_PASSIVE_SCAN = payProductMap.get("JD_PASSIVE_SCAN");
		String UPOP_PASSIVE_SCAN = payProductMap.get("UPOP_PASSIVE_SCAN");
		json.append("\"MERCHANT_SCAN_PAY\":{\"dsPayBankMap\":{");
		System.out.println("WECHAT_SCAN:"+WECHAT_SCAN);
		System.out.println("ALIPAY_SCAN:"+ALIPAY_SCAN);
		System.out.println("JD_PASSIVE_SCAN:"+JD_PASSIVE_SCAN);
		System.out.println("UPOP_PASSIVE_SCAN:"+UPOP_PASSIVE_SCAN);
		if(WECHAT_SCAN != "")
			json.append("\"WECHAT_SCAN\":{\"rateType\":\"PERCENTAGE\",\"rate\":\""+WECHAT_SCAN+"\"},");
		if(ALIPAY_SCAN != "")
			json.append("\"ALIPAY_SCAN\":{\"rateType\":\"PERCENTAGE\",\"rate\":\""+ALIPAY_SCAN+"\"},");
		if(JD_PASSIVE_SCAN != "")
			json.append("\"JD_PASSIVE_SCAN\":{\"rateType\":\"PERCENTAGE\",\"rate\":\""+JD_PASSIVE_SCAN+"\"},");
		if(UPOP_PASSIVE_SCAN != "")
			json.append("\"UPOP_PASSIVE_SCAN\":{\"rateType\":\"PERCENTAGE\",\"rate\":\""+UPOP_PASSIVE_SCAN+"\"},");
		if(json.length()<1) return "";
		return json.substring(0, json.length()-1)+"}}";
	}

	/**
	 * 钱包H5支付
	 * @param payProductMap
	 * @return
	 */
	public static String createEWALLETH5(Map<String, String> payProductMap){
		StringBuffer json = new StringBuffer();
		String WECHAT_H5 = payProductMap.get("WECHAT_H5");
		String ALIPAY_H5 = payProductMap.get("ALIPAY_H5");
		json.append("\"EWALLETH5\":{\"dsPayBankMap\":{");
		System.out.println("WECHAT_H5:"+WECHAT_H5);
		System.out.println("ALIPAY_H5:"+ALIPAY_H5);
		if(WECHAT_H5 != "")
			json.append("\"WECHAT_H5\":{\"rateType\":\"PERCENTAGE\",\"rate\":\""+WECHAT_H5+"\"},");
		if(ALIPAY_H5 != "")
			json.append("\"ALIPAY_H5\":{\"rateType\":\"PERCENTAGE\",\"rate\":\""+ALIPAY_H5+"\"},");
		if(json.length()<1) return "";
		return json.substring(0, json.length()-1)+"}}";
	}
}
