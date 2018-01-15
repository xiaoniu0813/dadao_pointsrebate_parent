package com.dadao.yop.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yeepay.g3.facade.yop.ca.dto.DigitalEnvelopeDTO;
import com.yeepay.g3.facade.yop.ca.dto.DigitalSignatureDTO;
import com.yeepay.g3.facade.yop.ca.enums.CertTypeEnum;
import com.yeepay.g3.facade.yop.ca.enums.DigestAlgEnum;
import com.yeepay.g3.frame.yop.ca.DigitalEnvelopeUtils;
import com.yeepay.g3.sdk.yop.client.YopClient3;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import com.yeepay.g3.sdk.yop.utils.InternalConfig;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class YeepayService {

	//yop接口应用URI地址
	public static final String BASE_URL = "baseURL";
	public static final String TRADEORDER_URL = "tradeOrderURI";
	public static final String ORDERQUERY_URL = "orderQueryURI";
	public static final String REFUND_URL = "refundURI";
	public static final String REFUNDQUERY_URL = "refundQueryURI";
	public static final String MULTIORDERQUERY_URL = "multiOrderQueryURI";
	public static final String ORDERCLOSE_URL = "orderCloseURI";
	public static final String SETTLEMENTSQUERY_URL = "settlementsqueryURI";
	public static final String BALANCEQUERY_URL = "balancequeryURI";
	public static final String HMACKEYQUERY_URL = "hmackeyqueryURI";
	public static final String APICASHIER_URI = "APICASHIER";
	
	public static final String TRADEDIVIDE_URL = "tradedivideURI";
	public static final String TRADEDIVIDEQUERY_URL = "tradedividequeryURI";
	public static final String TRADEFULLSETTLE_URL = "tradefullsettleURI";
	
	public static final String PERSON_URL = "personURI";
	public static final String INDIVIDUAL_URL = "individualURI";
	public static final String ENTERPRISE_URL = "enterpriseURI";
	public static final String AGREEINFOQUERY_URL = "agreeinfoqueryURI";
	public static final String BANKBRANCHINFO_URL = "bankBranchInfoURI";
	public static final String REGSTATUSQUERY_URL = "regstatusqueryURI";
	public static final String UPLOAD_URL = "uploadURI";
	public static final String SENDAUTHORIZENUM_URL = "sendauthorizenumURI";
	
	//接口参数
	public static final String[] TRADEORDER = {"parentMerchantNo","merchantNo","orderId","orderAmount","timeoutExpress","requestDate","redirectUrl","notifyUrl","goodsParamExt","paymentParamExt","industryParamExt","memo","riskParamExt","csUrl","fundProcessType"};
	public static final String[] ORDERQUERY = {"parentMerchantNo","merchantNo","orderId","uniqueOrderNo"};
	public static final String[] REFUND = {"parentMerchantNo","merchantNo","orderId","uniqueOrderNo","refundRequestId","refundAmount","description","memo","notifyUrl"};
	public static final String[] REFUNDQUERY = {"parentMerchantNo","merchantNo","refundRequestId","orderId","uniqueRefundNo"};
	public static final String[] MULTIORDERQUERY = {"status","parentMerchantNo","merchantNo","requestDateBegin","requestDateEnd","pageNo","pageSize"};
	public static final String[] ORDERCLOSE = {"parentMerchantNo","merchantNo","orderId","uniqueOrderNo","description"};
	public static final String[] SETTLEMENTSQUERY = {"parentMerchantNo","merchantNo","startSettleDate","endSettleDate"};
	public static final String[] BALANCEQUERY = {"parentMerchantNo","merchantNo"};
	public static final String[] HMACKEYQUERY = {"parentMerchantNo","merchantNo"};
	
	public static final String[] TRADEDIVIDE = {"parentMerchantNo","merchantNo","divideRequestId","orderId","uniqueOrderNo","contractNo","divideDetail","infoParamExt"};
	public static final String[] TRADEDIVIDEQUERY = {"parentMerchantNo","merchantNo","divideRequestId","orderId","uniqueOrderNo"};
	public static final String[] TRADEFULLSETTLE = {"parentMerchantNo","merchantNo","orderId","uniqueOrderNo"};
	
	//商户入网接口参数
	public static final String[] PERSON = {"requestNo","parentMerchantNo","merShortName","legalName","legalIdCard","merLegalPhone","merLegalEmail","merLevel1No","merLevel2No","merProvince","merCity","merDistrict","merAddress","merScope","cardNo","headBankCode","bankCode","bankProvince","bankCity","productInfo","fileInfo","businessFunction","notifyUrl","merAuthorizeType"};
	public static final String[] INDIVIDUAL = {"requestNo","parentMerchantNo","merFullName","merShortName","merCertNo","legalName","legalIdCard","merLegalPhone","merLegalEmail","merLevel1No","merLevel2No","merProvince","merCity","merDistrict","merAddress","cardNo","headBankCode","bankCode","bankProvince","bankCity","productInfo","fileInfo","businessFunction","notifyUrl","merAuthorizeType"};
	public static final String[] ENTERPRISE = {"requestNo","parentMerchantNo","merFullName","merShortName","merCertType","merCertNo","legalName","legalIdCard","merLevel1No","merLevel2No","merProvince","merCity","merDistrict","merAddress","merContactName","merContactPhone","merContactEmail","taxRegistCert","accountLicense","orgCode","isOrgCodeLong","orgCodeExpiry","cardNo","headBankCode","bankCode","bankProvince","bankCity","productInfo","fileInfo","businessFunction","notifyUrl","merAuthorizeType"};
	public static final String[] AGREEINFOQUERY = {"parentMerchantNo","merchantNo"};
	public static final String[] BANKBRANCHINFO = {"headBankCode","provinceCode","cityCode"};
	public static final String[] REGSTATUSQUERY = {"parentMerchantNo","merchantNo"};
	public static final String[] UPLOAD = {"fileType","_file"};
	public static final String[] SENDAUTHORIZENUM = {"parentMerchantNo","merchantNo","phone"};
	
	//验签顺序
	public static final String[] DIVIDED_HMAC = {"parentMerchantNo","merchantNo","orderId","uniqueOrderNo","divideRequestId"};
	public static final String[] TRADEORDER_HMAC = {"parentMerchantNo","merchantNo","orderId","orderAmount","notifyUrl"};
	public static final String[] TRADEDIVIDE_HMAC={"parentMerchantNo","merchantNo","orderId","uniqueOrderNo","divideRequestId"};
	public static final String[] ORDERQUERY_HMAC = {"parentMerchantNo","merchantNo","orderId","uniqueOrderNo"};
	public static final String[] REFUND_HMAC = {"parentMerchantNo","merchantNo","orderId","uniqueOrderNo","refundRequestId","refundAmount"};	
	public static final String[] REFUNDQUERY_HMAC = {"parentMerchantNo","merchantNo","refundRequestId","orderId","uniqueRefundNo"};
	public static final String[] MULTIORDERQUERY_HMAC = {"parentMerchantNo","merchantNo","requestDateBegin","requestDateEnd","pageNo","pageSize"};
	public static final String[] ORDERCLOSE_HMAC = {"parentMerchantNo","merchantNo","orderId","uniqueOrderNo"};
	public static final String[] SETTLEMENTSQUERY_HMAC = {"parentMerchantNo","merchantNo","startSettleDate","endSettleDate"};

	//支付方式
	public static final String[] CASHIER = {"merchantNo","token","timestamp","directPayType","cardType","userNo","userType","ext"};
	public static final String[] APICASHIER = {"token","payTool","payType","userNo","userType","appId","openId","payEmpowerNo","merchantTerminalId","merchantStoreNo","userIp","version"};

	//获取对应的请求地址
	public static String getUrl(String payType){
		return Configuration.getInstance().getValue(payType);
	}

	//拼接支付链接
	public static String getUrl(Map<String,String> paramValues) throws UnsupportedEncodingException{
		StringBuffer url = new StringBuffer();
		url.append(getUrl("CASHIER"));
		paramValues.put("merchantNo", getParentMerchantNo());
		StringBuilder stringBuilder = new StringBuilder();
		System.out.println(paramValues);
		for (int i = 0; i < CASHIER.length; i++) {
			String name = CASHIER[i];
			String value = paramValues.get(name);
			if(i != 0){
				stringBuilder.append("&");
			}
			stringBuilder.append(name+"=").append(value);
		}
		System.out.println(stringBuilder);
		String sign = getSign(stringBuilder.toString());
		url.append("?sign="+sign+"&"+stringBuilder);
		return url.toString();
	}
	
	//获取父商编
	public static String getParentMerchantNo(){
		return Configuration.getInstance().getValue("parentMerchantNo");
	}

	//获取收单商编
	public static String getReceiptMerchantNo(){
		return Configuration.getInstance().getValue("receiptMerchantNo");
	}
	
	//获取子商编
	public static String getMerchantNo(){
		return Configuration.getInstance().getValue("merchantNo");
	}
	
	//获取父商编私钥
	public static PrivateKey getSecretKey(){
		PrivateKey isvPrivateKey = InternalConfig.getISVPrivateKey(CertTypeEnum.RSA2048);
		return isvPrivateKey;
	}
	//获取注册回调信息
	public static String getRegisterCallbackUrl(){
		return Configuration.getInstance().getValue("registerUrl");
	}
	
	//获取子商户密钥
	public static String getMerchantKey(String merchantNo){
		Map<String, String> params = new HashMap<>();
		params.put("parentMerchantNo", getParentMerchantNo());
		params.put("merchantNo", merchantNo);
		
		Map<String, String> result = new HashMap<>();
		String uri = getUrl(HMACKEYQUERY_URL);
		result = YeepayService.requestYOP(params, uri, HMACKEYQUERY);
		
		System.out.println("result:"+result);
		return result.get("merHmacKey");
	}
	
	//获取公钥
	public static PublicKey getPublicKey(){
		PublicKey isvPublicKey = InternalConfig.getYopPublicKey(CertTypeEnum.RSA2048);
		return isvPublicKey;
	}
	//获取sign
	public static String getSign(String stringBuilder){
		String appKey = "OPR:"+getParentMerchantNo();
		PrivateKey isvPrivateKey = getSecretKey();
		DigitalSignatureDTO digitalSignatureDTO = new DigitalSignatureDTO();
		digitalSignatureDTO.setAppKey(appKey);
		digitalSignatureDTO.setCertType(CertTypeEnum.RSA2048);
		digitalSignatureDTO.setDigestAlg(DigestAlgEnum.SHA256);
		digitalSignatureDTO.setPlainText(stringBuilder.toString());
		String sign = DigitalEnvelopeUtils.sign0(digitalSignatureDTO,isvPrivateKey);
		return sign;
	}
	
	/**
	 * 请求YOP接口---其他接口使用
	 * params 请求参数,parentMerchantNo除外
	 * uri 请求yop的应用URI地址
	 * paramSign 请求参数的验签顺序
	 * hmackey 子商编私钥
	 * paramHmac 验签参数
	 */
	public static Map<String, String> requestYOP(Map<String, String> params, String uri, String[] paramSign, String[] paramHmac){
		Map<String, String> result = new HashMap<String, String>();
		String BASE_URL = getUrl("baseURL");
		String parentMerchantNo = YeepayService.getParentMerchantNo();
		String hmackey = getMerchantKey(params.get("merchantNo"));
		
		YopRequest request = new YopRequest("OPR:" + parentMerchantNo,"",BASE_URL);
		for (int i = 0; i < paramSign.length; i ++) {
			String key = paramSign[i];
			System.out.println("name:"+key);
			System.out.println("value:"+params.get(key));
			request.addParam(key, params.get(key)==null?"":params.get(key));
		}
		System.out.println("yoprui:"+uri);
		System.out.println("yopRequest:"+request.getParams());
		
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
			result = parseResponse(response.getStringResult());
		}
		
		return result;
	}
	
	/**
	 * 请求YOP接口-----子商户注册接口使用
	 * params 请求参数,parentMerchantNo除外
	 * uri 请求yop的应用URI地址
	 * paramSign 请求参数的验签顺序
	 */
	public static Map<String, String> requestYOP(Map<String, String> params, String uri, String[] paramSign){
		Map<String, String> result = new HashMap<String, String>();
		String BASE_URL = getUrl("baseURL");
		
		String parentMer = getParentMerchantNo();
		YopRequest request = new YopRequest("OPR:"+parentMer,"",BASE_URL);
		System.out.println(BASE_URL);
		for (int i = 0; i < paramSign.length; i ++) {
			String key = paramSign[i];
			System.out.println("name:"+key);
			System.out.println("value:"+params.get(key));
			request.addParam(key, params.get(key)==null?"":params.get(key));
		}
		System.out.println("yoprui:"+uri);
		System.out.println("yopRequest:"+request.getParams());

		YopResponse response = YopClient3.postRsa(uri, request);
		
		System.out.println(response.toString());
		if("FAILURE".equals(response.getState())){
			if(response.getError() != null)
			result.put("returnCode",response.getError().getCode());
			result.put("returnMsg",response.getError().getMessage());
			result.put("detailMsg", response.getError().getSubErrors().toString());
			return result;
		}
		if (response.getStringResult() != null) {
			result = parseResponse(response.getStringResult());
		}
		
		return result;
	}

	/**
	 * 短信验证码重发
	 * @param merchantNo
	 * @param phone
	 * @return
	 */
	public static YopResponse sendauthorizenum(String merchantNo,String phone){
		String BASE_URL = getUrl("baseURL");
		String parentMer = getParentMerchantNo();
		//组装请求
		YopRequest request = new YopRequest("OPR:"+parentMer,"",BASE_URL);
		request.addParam("parentMerchantNo",phone == null ? "" : parentMer);
		request.addParam("merchantNo",phone == null ? "" : merchantNo);
		request.addParam("phone",phone == null ? "" : phone);
		//发送请求
		String uri = Configuration.getInstance().getValue("sendauthorizenumURI");
		YopResponse response = YopClient3.postRsa(uri, request);
		return response;
	}

	//将获取到的response转换成json格式
	public static Map<String, String> parseResponse(String response){
		
		Map<String,String> jsonMap  = new HashMap<>();
		jsonMap	= JSON.parseObject(response, 
				new TypeReference<TreeMap<String,String>>() {});
		
		return jsonMap;
	}
	
	/**
	 *  获取支行信息
	 *  headBankCode 银行总称
	 *  provinceCode 省编码
	 * 	cityCode     市编码 
	 */
	public static Map<String, String> getBankBranchInfo(String headBankCode, String provinceCode, String cityCode){
		Map<String, String> result = new HashMap<>();
		Map<String, String> params = new HashMap<>();
		params.put("headBankCode", headBankCode);
		params.put("provinceCode", provinceCode);
		params.put("cityCode", cityCode);
		
		String uri = YeepayService.getUrl(YeepayService.BANKBRANCHINFO_URL);
		System.out.println(uri);
		result = YeepayService.requestYOP(params, uri, YeepayService.BANKBRANCHINFO);
		System.out.println(result);
		
		return result;
	}
	
	/**
	 *	商户产品协议获取
	 */
	public static Map<String, String> getAgreeInfoQuery(){
		Map<String, String> result = new HashMap<>();
		String parentMerchantNo = getParentMerchantNo();
		String merchantNo = getMerchantNo();
		Map<String, String> params = new HashMap<>();
		params.put("parentMerchantNo", parentMerchantNo);
		params.put("merchantNo", merchantNo);
		String uri = YeepayService.getUrl(YeepayService.AGREEINFOQUERY_URL);
		System.out.println(uri);
		result = YeepayService.requestYOP(params, uri, YeepayService.AGREEINFOQUERY);
		System.out.println(result);
		return result;
	}
	
	/**
	 * 文件上传，获取地址
	 * @param response
	 * @return
	 */
	public static Map<String,String> upload(String fileType, String file) {
		Map<String, String> result = new HashMap<>();
		String uri = YeepayService.getUrl(YeepayService.UPLOAD_URL);
		System.out.println(uri);
		YopRequest request = new YopRequest("OPR:"+getParentMerchantNo(),"",getUrl("baseURL"));
		request.addParam("fileType", fileType);
	    request.addParam("_file", "file:"+file);

	    System.out.println(request.toQueryString());
		YopResponse response = YopClient3.uploadRsa(uri, request);
		System.out.println(response.toString());
		if("FAILURE".equals(response.getState())){
			if(response.getError() != null)
			result.put("code",response.getError().getCode());
			result.put("message",response.getError().getMessage());
			return result;
		}
		if (response.getStringResult() != null) {
			result = parseResponse(response.getStringResult());
		}
		return result;
	}
	
	//回调
	public static Map<String, String> callback(String response){
		System.out.println(response);
		DigitalEnvelopeDTO dto = new DigitalEnvelopeDTO();
		dto.setCipherText(response);
		Map<String,String> jsonMap  = new HashMap<>();
	    try {
	        PrivateKey privateKey = InternalConfig.getISVPrivateKey(CertTypeEnum.RSA2048);
	        PublicKey publicKey = InternalConfig.getYopPublicKey(CertTypeEnum.RSA2048);
	        dto = DigitalEnvelopeUtils.decrypt(dto, privateKey, publicKey);
	        System.out.println(dto.getPlainText());
	        jsonMap = parseResponse(dto.getPlainText());
        } catch (Exception e) {
        	e.printStackTrace();
        }
	    
		return jsonMap;
	}
	
	public static boolean verifyCallback(Map<String,String> responseMap){
		boolean flag = false;
		String merchantNo = responseMap.get("merchantNo");
		String parentMerchantNo = responseMap.get("parentMerchantNo");
		String orderId = responseMap.get("orderId");
		String signResp = responseMap.get("sign");
	    String s = "merchantNo="+merchantNo+"&parentMerchantNo="+parentMerchantNo+"&orderId="+orderId;
	    System.out.println("s===="+s);
	    String appKey = "OPR:"+getMerchantNo();
		PublicKey isvPublicKey = getPublicKey();
		DigitalSignatureDTO digitalSignatureDTO = new DigitalSignatureDTO();
		digitalSignatureDTO.setAppKey(appKey);
		digitalSignatureDTO.setCertType(CertTypeEnum.RSA2048);
		digitalSignatureDTO.setDigestAlg(DigestAlgEnum.SHA256);
		digitalSignatureDTO.setPlainText(s.toString());
		digitalSignatureDTO.setSignature(signResp);
		try {
			DigitalEnvelopeUtils.verify0(digitalSignatureDTO,isvPublicKey);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}
	//获取回调地址
	public static String getNotifyUrl(){
		return Configuration.getInstance().getValue("notifyUrl");
	}

	/**
	 * 获取退款回调地址
	 * @return
	 */
	public static String getRefundUrl(){
		return Configuration.getInstance().getValue("refundUrl");
	}

	/**
	 * 个人商户注册产品信息productInfo
	 */
	public static String getPersonageProductInfo(){
		return Configuration.getInstance().getValue("personageProductInfo");
	}

	/**
	 * 个体商户注册产品信息productInfo
	 */
	public static String getIndividualProductInfo(String commissionRate){
		//处理0.9费率
		Double double09 = new Double(commissionRate);
		double09 += 0.9;
		//处理0.45费率
		Double double45 = new Double(commissionRate);
		double45 += 0.45;
		return Configuration.getInstance().getValue("individualProductInfo").replaceAll("0.45", double45.toString()).replaceAll("0.9", double09.toString());
	}

	/**
	 * 企业商户注册产品信息productInfo
	 */
	public static String getEnterpriseProductInfo(String commissionRate){
		//处理0.9费率
		Double double09 = new Double(commissionRate);
		double09 += 0.9;
		//处理0.45费率
		Double double45 = new Double(commissionRate);
		double45 += 0.45;
		return Configuration.getInstance().getValue("enterpriseProductInfo").replaceAll("0.45", double45.toString()).replaceAll("0.9", double09.toString());
	}

	/**
	 *查找子商户密匙
	 * @param parentMerchantNo 父商户编号
	 * @param merchantNo 子商户编号
	 * @return
	 */
	public static String getHmackey(String parentMerchantNo, String merchantNo){
		//构建查询参数map
		Map<String, String> params = new HashMap<>();
		params.put("parentMerchantNo", parentMerchantNo);
		params.put("merchantNo", merchantNo);
		Map<String, String> result = new HashMap<>();
		String url = YeepayService.getUrl(YeepayService.HMACKEYQUERY_URL);
		result = YeepayService.requestYOP(params, url, YeepayService.HMACKEYQUERY);
		return result.get("merHmacKey");
	}

}
