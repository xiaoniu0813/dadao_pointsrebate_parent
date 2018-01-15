package com.dadao.utils;


import com.dadao.cashback.entity.CashbackDetailsPO;
import com.dadao.shop.entity.BusinessApply;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.entity.UserTransactionRecodPO;
import org.apache.poi.hssf.usermodel.*;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.util.List;

/**
 * Created by GUOYU on 2017-08-07.
 */
public class ExcelUtil {

    public static InputStream exportExcel(List list, String param[]) {
        //创建一个新的excel
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        HSSFCell cell = row.createCell(0);
        //循环遍历查询出首行
        for (int i = 0; i < param.length; i++) {
            cell.setCellValue(param[i]);
            cell.setCellStyle(style);
            cell = row.createCell(i + 1);
        }
        //循环将集合中的值加入到excel中
        for (int i = 0; i < list.size(); i++) {
            if (UserTransactionRecodPO.class == list.get(0).getClass()) {
                row = sheet.createRow((int) i + 1);
                UserTransactionRecodPO userTransactionRecodPO = (UserTransactionRecodPO) list.get(i);
                row.createCell(0).setCellValue(userTransactionRecodPO.getTransactionId());
                row.createCell(1).setCellValue(userTransactionRecodPO.getCreatetime());
                row.createCell(2).setCellValue(userTransactionRecodPO.getOtherAccount());
                row.createCell(3).setCellValue(userTransactionRecodPO.getTransactionAmount().doubleValue());
                int pays = userTransactionRecodPO.getPayMethod();
                String payMethod = pays == 0 ? "钱包" : pays == 1 ? "微信" : pays == 2 ? "支付宝" : pays == 3 ? "银联" : pays == 4 ? "余额" : "其他";
                row.createCell(4).setCellValue(payMethod);
                int mold = userTransactionRecodPO.getExpenditureIncome();
                String expenditureIncome = mold == 0 ? "支出" : mold == 1 ? "收入" : "其他";
                row.createCell(5).setCellValue(expenditureIncome);
                int deal = userTransactionRecodPO.getTransactionType();
                String transactionType = deal == 0 ? "返利" : deal == 1 ? "转入" : deal == 2 ? "转出" : deal == 3 ? "提现" : deal == 4 ? "支付" : deal == 5 ? "退款" : "其他";
                row.createCell(6).setCellValue(transactionType);
            }
            if (CashbackDetailsPO.class == list.get(0).getClass()) {
                row = sheet.createRow((int) i + 1);
                CashbackDetailsPO cashbackDetailsPO = (CashbackDetailsPO) list.get(i);
                row.createCell(0).setCellValue(cashbackDetailsPO.getDetailsId());
                row.createCell(1).setCellValue(cashbackDetailsPO.getCashbackSpecificDate());
                row.createCell(2).setCellValue(cashbackDetailsPO.getCashbackMoney().doubleValue());
                row.createCell(3).setCellValue(cashbackDetailsPO.getTaxPayment().doubleValue());
                row.createCell(4).setCellValue(cashbackDetailsPO.getReally().doubleValue());
                int model = cashbackDetailsPO.getStatus();
                String status = model == 0 ? "未返利" : model == 1 ? "已返利" : "错误";
                row.createCell(5).setCellValue(status);

            }
            if(UserIntegralRecordingPO.class == list.get(0).getClass()){
                row = sheet.createRow((int) i + 1);
                UserIntegralRecordingPO userIntegralRecordingPO = (UserIntegralRecordingPO) list.get(i);
                row.createCell(0).setCellValue(userIntegralRecordingPO.getIRid());
                row.createCell(1).setCellValue(userIntegralRecordingPO.getIntegral().doubleValue());
                row.createCell(2).setCellValue(userIntegralRecordingPO.getDirection());
                row.createCell(3).setCellValue(userIntegralRecordingPO.getMarketGrade());
                row.createCell(4).setCellValue(userIntegralRecordingPO.getCreateTime());

            }
            if(BusinessApply.class == list.get(0).getClass()){
                row = sheet.createRow((int) i + 1);
                BusinessApply businessApply = (BusinessApply)list.get(i);
                //商户id
                Long userId = businessApply.getUserId();
                row.createCell(0).setCellValue(userId == null ? "" : userId+"");
               //佣金率
                String comm = businessApply.getCommissionRate();
                row.createCell(1).setCellValue(comm == null ? "" :comm);
                //积分率
                Integer integral = businessApply.getIntegralRate();
                row.createCell(2).setCellValue(integral == null ? "" : integral + "");
                //审核信息
                String regInfo = businessApply.getRegInfo();
                row.createCell(3).setCellValue(regInfo == null ? "" : regInfo);
                //审核状态
                Integer status = businessApply.getStatus();
                row.createCell(4).setCellValue(status == null ? "" : status + "");
                //商户全称
                String merFullName = businessApply.getMerFullName();
                row.createCell(5).setCellValue(merFullName == null ? "" : merFullName);
                //商户简称
                String merShortName = businessApply.getMerShortName();
                row.createCell(6).setCellValue(merShortName == null ? "" : merShortName);
                //证件类型
                String merCertType = businessApply.getMerCertType();
                row.createCell(7).setCellValue(merCertType == null ? "" : merCertType.equals("0") ? "营业执照":"统一社会信用代码证");
                //证件号
                String merCertNo = businessApply.getMerCertNo();
                row.createCell(8).setCellValue(merCertNo == null ? "" : merCertNo);
                //法人姓名
                String legalName = businessApply.getLegalName();
                row.createCell(9).setCellValue(legalName == null ? "" : legalName);
                //法人身份证
                String legalIdCard = businessApply.getLegalIdCard();
                row.createCell(10).setCellValue(legalIdCard == null ? "" : legalIdCard);
                //手机
                String phone = businessApply.getMerLegalPhone();
                row.createCell(11).setCellValue(phone == null ? "" : phone);
                //邮箱
                String email = businessApply.getMerLegalEmail();
                row.createCell(12).setCellValue(email == null ? "" : email);
                //商户一级分类
                String merLevel1No = businessApply.getMerLevel1No();
                row.createCell(13).setCellValue(merLevel1No == null ? "" : merLevel1No);
                //商户二级分类
                String merLevel2No = businessApply.getMerLevel2No();
                row.createCell(14).setCellValue(merLevel2No == null ? "" : merLevel2No);
                //商户经营地址所在省
                String merProvince = businessApply.getMerProvince();
                row.createCell(15).setCellValue(merProvince == null ? "" : merProvince);
                //商户经营地址所在市
                String merCity = businessApply.getMerCity();
                row.createCell(16).setCellValue(merCity == null ? "" : merCity);
                //商户经营地址所在区
                String merDistrict = businessApply.getMerDistrict();
                row.createCell(17).setCellValue(merDistrict == null ? "" : merDistrict);
                //商户经营地址所在具体地址
                String merAddress = businessApply.getMerAddress();
                row.createCell(18).setCellValue(merAddress == null ? "" : merAddress);
                //商户经营范围
                String merScope = businessApply.getMerScope();
                row.createCell(19).setCellValue(merScope == null ? "" : merScope);
                //商户联系人姓名
                String merContactName = businessApply.getMerContactName();
                row.createCell(20).setCellValue(merContactName == null ? "" : merContactName);
                //税务登记证编号
                String taxRegistCert = businessApply.getTaxRegistCert();
                row.createCell(21).setCellValue(taxRegistCert == null ? "" : taxRegistCert);
                //开户许可证编号
                String accountLicense = businessApply.getAccountLicense();
                row.createCell(22).setCellValue(accountLicense == null ? "" : accountLicense);
                //组织机构代码证
                String orgCode = businessApply.getOrgCode();
                row.createCell(23).setCellValue(orgCode == null ? "" : orgCode);
                //组织机构代码证是否长期有效
                String isOrgCodeLong = businessApply.getIsOrgCodeLong();
                row.createCell(24).setCellValue(isOrgCodeLong == null ? "" : isOrgCodeLong);
                //组织机构代理证有效期
                String orgCodeExpiry = businessApply.getOrgCodeExpiry();
                row.createCell(25).setCellValue(orgCodeExpiry);
                //银行账户
                String cardNo = businessApply.getCardNo();
                row.createCell(26).setCellValue(cardNo == null ? "" : cardNo);
                //开户银行总行编码
                String headBankCode = businessApply.getHeadBankCode();
                row.createCell(27).setCellValue(headBankCode == null ? "" : headBankCode);
                //开户省
                String bankProvince = businessApply.getBankProvince();
                row.createCell(28).setCellValue(bankProvince == null ? "" : bankProvince);
                //开户市
                String bankCity = businessApply.getBankCity();
                row.createCell(29).setCellValue(bankCity == null ? "" : bankCity);
                //开户银行编码
                String bankCode = businessApply.getBankCode();
                row.createCell(30).setCellValue(bankCode == null ? "" : bankCode);
                //商户类型
                String merType= businessApply.getMerType();
                row.createCell(31).setCellValue(merType == null ? "" :merType.equals(0) ? "个人" : merType.equals("1") ? "个体" : "企业");
                //入网请求号
                String requestNo = businessApply.getRequestNo();
                row.createCell(32).setCellValue(requestNo == null ? "" : requestNo);
                //该入网商户所在易宝的商户商编
                String merChantNo = businessApply.getMerchantNo();
                row.createCell(33).setCellValue(merChantNo == null ? "" : merChantNo);
                //返回该条请求易宝的流水号
                String externald = businessApply.getExternalId();
                row.createCell(34).setCellValue(externald == null ? "" : externald);
                //易宝所生成电子协议内容
                String agree = businessApply.getAgreementContent();
                row.createCell(35).setCellValue(agree == null ? "" : agree);
                //创建时间
                String createTime = businessApply.getCreateTime();
                row.createCell(36).setCellValue(createTime == null ? "" : createTime);
                //商户入网状态
                String merNetInStatus = businessApply.getMerNetInStatus();
                row.createCell(37).setCellValue(merNetInStatus == null ? "" : merNetInStatus);

            }
        }
        //将excel写入到字节数组流中
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //字节流转换为一个 byte数组
        byte[] content = os.toByteArray();
        //将byte数组加入到输入流中
        InputStream is = new ByteArrayInputStream(content);

        return is;

    }

    public static void print(HttpServletRequest request, HttpServletResponse response, InputStream is) {

        try {
            //调用浏览器下载
            String fileName = new String("download.xls".getBytes("UTF-8"), "iso-8859-1");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ServletOutputStream out = null;

        try {
            //向输出流中写入返回页面的内容.
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            //将输入流加入到缓冲输入流中
            bis = new BufferedInputStream(is);
            //将servletOu
            bos = new BufferedOutputStream(out);

            byte[] buff = new byte[2048];
            int bytesRead;

            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
         }
    }

}
