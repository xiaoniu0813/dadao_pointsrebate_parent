package com.dadao.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;


public class SmsSendUtil {

	private static final String accessKeyId = "LTAIs5q5GaQnLxry";
	private static final String accessKeySecret =  "gEgJoBMJdzIDSUDDFTE2KlSRJhOobz";
	private static final String signName = "大道科技";
	private static final String templateCode = "SMS_80185020";
	//初始化ascClient需要的几个参数
	private static final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
	private static final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）

	/**
	 * 发送短信验证码
	 * @param phoneNumber
	 * @param code
	 * @throws ClientException
	 */
	public static void sendCode(String phoneNumber, String code) throws ClientException{

		//初始化ascClient,暂时不支持多region
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
				accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		//组装请求对象
		SendSmsRequest request = new SendSmsRequest();
		//使用post提交
		request.setMethod(MethodType.POST);
		//必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		request.setPhoneNumbers(phoneNumber);
		//必填:短信签名-可在短信控制台中找到
		request.setSignName(signName);
		//必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(templateCode);
		request.setTemplateParam("{code:"+code+"}");
		//请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			//请求成功
		}

	}
	
	public static void main(String[] args) throws ClientException {
		sendCode("17611101631", "123456");
	}
}
