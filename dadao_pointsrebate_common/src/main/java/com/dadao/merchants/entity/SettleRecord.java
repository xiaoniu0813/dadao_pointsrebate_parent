package com.dadao.merchants.entity;

public class SettleRecord {

    private String settlementDate;//结算日期
    private String beginSettlementDate;//结算起始日期
    private String endSettlementDate;//结算终止日期
    private String sumRealSettlementAmount;//结算金额
    private String remitFee;//结算手续费、
    private String sumNetAmount;//实结金额
    private String settlementType;//结算方式
    private String remitStatus;//结算状态
    private String failMsg;//失败原因

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getBeginSettlementDate() {
        return beginSettlementDate;
    }

    public void setBeginSettlementDate(String beginSettlementDate) {
        this.beginSettlementDate = beginSettlementDate;
    }

    public String getEndSettlementDate() {
        return endSettlementDate;
    }

    public void setEndSettlementDate(String endSettlementDate) {
        this.endSettlementDate = endSettlementDate;
    }

    public String getSumRealSettlementAmount() {
        return sumRealSettlementAmount;
    }

    public void setSumRealSettlementAmount(String sumRealSettlementAmount) {
        this.sumRealSettlementAmount = sumRealSettlementAmount;
    }

    public String getRemitFee() {
        return remitFee;
    }

    public void setRemitFee(String remitFee) {
        this.remitFee = remitFee;
    }

    public String getSumNetAmount() {
        return sumNetAmount;
    }

    public void setSumNetAmount(String sumNetAmount) {
        this.sumNetAmount = sumNetAmount;
    }

    public String getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }

    public String getRemitStatus() {
        return remitStatus;
    }

    public void setRemitStatus(String remitStatus) {
        this.remitStatus = remitStatus;
    }

    public String getFailMsg() {
        return failMsg;
    }

    public void setFailMsg(String failMsg) {
        this.failMsg = failMsg;
    }

    @Override
    public String toString() {
        return "SettleRecord{" +
                "settlementDate='" + settlementDate + '\'' +
                ", beginSettlementDate='" + beginSettlementDate + '\'' +
                ", endSettlementDate='" + endSettlementDate + '\'' +
                ", sumRealSettlementAmount='" + sumRealSettlementAmount + '\'' +
                ", remitFee='" + remitFee + '\'' +
                ", sumNetAmount='" + sumNetAmount + '\'' +
                ", settlementType='" + settlementType + '\'' +
                ", remitStatus='" + remitStatus + '\'' +
                ", failMsg='" + failMsg + '\'' +
                '}';
    }
}
