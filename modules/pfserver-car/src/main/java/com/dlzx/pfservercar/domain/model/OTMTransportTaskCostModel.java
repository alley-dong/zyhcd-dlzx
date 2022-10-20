package com.dlzx.pfservercar.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OTMTransportTaskCostModel {

    /** 接口表主键*/
    private String TEMPLATE_ID;
    /** 运单编号(运单ID)*/
    private String SHIPMENT_GID;
    /** 运单号*/
    private String shipment_xid;
    /** 收付款标记（AR:应收，AP：应付）*/
    private String PAYMENT_TYPE;
    /** 客户名称*/
    private String CUSTOMER_NAME;
    /** 承运商名称*/
    private String SERVPROV_NAME;
    /** 业务类型*/
    private String BUSINESS_TYPE;
    /** 费用类型(吨公里结算运费，按件结算运费）*/
    private String FEE_TYPE;
    /** 含税总金额*/
    private String TOTAL_AMOUNT;
    /** 税率*/
    private String TAX_RATE;
    /** 不含税总金额*/
    private String AMOUNT;
    /** TMS系统账单号*/
    private String SETTLE_NUM;
    /** 单证号*/
    private String DOCUMENT_NUMBER;

    /* 扩展字段 */
    private String EXTEND_TEXT1;
    private String EXTEND_TEXT2;
    private String EXTEND_TEXT3;
    private String EXTEND_TEXT4;
    private String EXTEND_TEXT5;
    private String EXTEND_TEXT6;
    private String EXTEND_TEXT7;
    private String EXTEND_TEXT8;
    private String EXTEND_TEXT9;
    private String EXTEND_TEXT10;

    @JsonProperty("shipment_xid")
    public String getShipment_xid() {
        return shipment_xid;
    }

    public void setShipment_xid(String shipment_xid) {
        this.shipment_xid = shipment_xid;
    }

    @JsonProperty("TEMPLATE_ID")
    public String getTEMPLATE_ID() {
        return TEMPLATE_ID;
    }

    public void setTEMPLATE_ID(String TEMPLATE_ID) {
        this.TEMPLATE_ID = TEMPLATE_ID;
    }

    @JsonProperty("SHIPMENT_GID")
    public String getSHIPMENT_GID() {
        return SHIPMENT_GID;
    }

    public void setSHIPMENT_GID(String SHIPMENT_GID) {
        this.SHIPMENT_GID = SHIPMENT_GID;
    }

    @JsonProperty("PAYMENT_TYPE")
    public String getPAYMENT_TYPE() {
        return PAYMENT_TYPE;
    }

    public void setPAYMENT_TYPE(String PAYMENT_TYPE) {
        this.PAYMENT_TYPE = PAYMENT_TYPE;
    }

    @JsonProperty("CUSTOMER_NAME")
    public String getCUSTOMER_NAME() {
        return CUSTOMER_NAME;
    }

    public void setCUSTOMER_NAME(String CUSTOMER_NAME) {
        this.CUSTOMER_NAME = CUSTOMER_NAME;
    }

    @JsonProperty("SERVPROV_NAME")
    public String getSERVPROV_NAME() {
        return SERVPROV_NAME;
    }

    public void setSERVPROV_NAME(String SERVPROV_NAME) {
        this.SERVPROV_NAME = SERVPROV_NAME;
    }

    @JsonProperty("BUSINESS_TYPE")
    public String getBUSINESS_TYPE() {
        return BUSINESS_TYPE;
    }

    public void setBUSINESS_TYPE(String BUSINESS_TYPE) {
        this.BUSINESS_TYPE = BUSINESS_TYPE;
    }

    @JsonProperty("FEE_TYPE")
    public String getFEE_TYPE() {
        return FEE_TYPE;
    }

    public void setFEE_TYPE(String FEE_TYPE) {
        this.FEE_TYPE = FEE_TYPE;
    }

    @JsonProperty("TOTAL_AMOUNT")
    public String getTOTAL_AMOUNT() {
        return TOTAL_AMOUNT;
    }

    public void setTOTAL_AMOUNT(String TOTAL_AMOUNT) {
        this.TOTAL_AMOUNT = TOTAL_AMOUNT;
    }

    @JsonProperty("TAX_RATE")
    public String getTAX_RATE() {
        return TAX_RATE;
    }

    public void setTAX_RATE(String TAX_RATE) {
        this.TAX_RATE = TAX_RATE;
    }

    @JsonProperty("AMOUNT")
    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    @JsonProperty("SETTLE_NUM")
    public String getSETTLE_NUM() {
        return SETTLE_NUM;
    }

    public void setSETTLE_NUM(String SETTLE_NUM) {
        this.SETTLE_NUM = SETTLE_NUM;
    }

    @JsonProperty("DOCUMENT_NUMBER")
    public String getDOCUMENT_NUMBER() {
        return DOCUMENT_NUMBER;
    }

    public void setDOCUMENT_NUMBER(String DOCUMENT_NUMBER) {
        this.DOCUMENT_NUMBER = DOCUMENT_NUMBER;
    }


    @JsonProperty("EXTEND_TEXT1")
    public String getEXTEND_TEXT1() {
        return EXTEND_TEXT1;
    }

    public void setEXTEND_TEXT1(String EXTEND_TEXT1) {
        this.EXTEND_TEXT1 = EXTEND_TEXT1;
    }

    @JsonProperty("EXTEND_TEXT2")
    public String getEXTEND_TEXT2() {
        return EXTEND_TEXT2;
    }

    public void setEXTEND_TEXT2(String EXTEND_TEXT2) {
        this.EXTEND_TEXT2 = EXTEND_TEXT2;
    }

    @JsonProperty("EXTEND_TEXT3")
    public String getEXTEND_TEXT3() {
        return EXTEND_TEXT3;
    }

    public void setEXTEND_TEXT3(String EXTEND_TEXT3) {
        this.EXTEND_TEXT3 = EXTEND_TEXT3;
    }

    @JsonProperty("EXTEND_TEXT4")
    public String getEXTEND_TEXT4() {
        return EXTEND_TEXT4;
    }

    public void setEXTEND_TEXT4(String EXTEND_TEXT4) {
        this.EXTEND_TEXT4 = EXTEND_TEXT4;
    }

    @JsonProperty("EXTEND_TEXT5")
    public String getEXTEND_TEXT5() {
        return EXTEND_TEXT5;
    }

    public void setEXTEND_TEXT5(String EXTEND_TEXT5) {
        this.EXTEND_TEXT5 = EXTEND_TEXT5;
    }

    @JsonProperty("EXTEND_TEXT6")
    public String getEXTEND_TEXT6() {
        return EXTEND_TEXT6;
    }

    public void setEXTEND_TEXT6(String EXTEND_TEXT6) {
        this.EXTEND_TEXT6 = EXTEND_TEXT6;
    }

    @JsonProperty("EXTEND_TEXT7")
    public String getEXTEND_TEXT7() {
        return EXTEND_TEXT7;
    }

    public void setEXTEND_TEXT7(String EXTEND_TEXT7) {
        this.EXTEND_TEXT7 = EXTEND_TEXT7;
    }

    @JsonProperty("EXTEND_TEXT8")
    public String getEXTEND_TEXT8() {
        return EXTEND_TEXT8;
    }

    public void setEXTEND_TEXT8(String EXTEND_TEXT8) {
        this.EXTEND_TEXT8 = EXTEND_TEXT8;
    }

    @JsonProperty("EXTEND_TEXT9")
    public String getEXTEND_TEXT9() {
        return EXTEND_TEXT9;
    }

    public void setEXTEND_TEXT9(String EXTEND_TEXT9) {
        this.EXTEND_TEXT9 = EXTEND_TEXT9;
    }

    @JsonProperty("EXTEND_TEXT10")
    public String getEXTEND_TEXT10() {
        return EXTEND_TEXT10;
    }

    public void setEXTEND_TEXT10(String EXTEND_TEXT10) {
        this.EXTEND_TEXT10 = EXTEND_TEXT10;
    }
}
