package com.dlzx.pfservercar.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class OTMTransportTaskShipmentStopModel {

    /** 接口表主键*/
    private String TEMPLATE_ID;
    /** 站点ID*/
    private String SHIPMENT_STOP_ID;
    /** 运单ID*/
    private String SHIPMENT_GID;
    /** 序号序号*/
    private String STOP_NUM;
    /** 站点编号*/
    private String SHIPMENT_STOP_XID;
    /** 站点名称*/
    private String SHIPMENT_STOP_NAME;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    /** 计划离开时间*/
    private Date PLANNED_DEPARTURE;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    /** 计划到达时间*/
    private Date PLANNED_ARRIVAL;
    /** 停靠省*/
    private String LOCATION_PROVINCE;
    /** 停靠市*/
    private String LOCATION_CITY;
    /** 停靠区*/
    private String LOCATION_DISTRICT;
    /** 停靠地址*/
    private String LOCATION_ADDRESS;

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



    @JsonProperty("TEMPLATE_ID")
    public String getTEMPLATE_ID() {
        return TEMPLATE_ID;
    }

    public void setTEMPLATE_ID(String TEMPLATE_ID) {
        this.TEMPLATE_ID = TEMPLATE_ID;
    }

    @JsonProperty("SHIPMENT_STOP_ID")
    public String getSHIPMENT_STOP_ID() {
        return SHIPMENT_STOP_ID;
    }

    public void setSHIPMENT_STOP_ID(String SHIPMENT_STOP_ID) {
        this.SHIPMENT_STOP_ID = SHIPMENT_STOP_ID;
    }

    @JsonProperty("SHIPMENT_GID")
    public String getSHIPMENT_GID() {
        return SHIPMENT_GID;
    }

    public void setSHIPMENT_GID(String SHIPMENT_GID) {
        this.SHIPMENT_GID = SHIPMENT_GID;
    }

    @JsonProperty("STOP_NUM")
    public String getSTOP_NUM() {
        return STOP_NUM;
    }

    public void setSTOP_NUM(String STOP_NUM) {
        this.STOP_NUM = STOP_NUM;
    }

    @JsonProperty("SHIPMENT_STOP_XID")
    public String getSHIPMENT_STOP_XID() {
        return SHIPMENT_STOP_XID;
    }

    public void setSHIPMENT_STOP_XID(String SHIPMENT_STOP_XID) {
        this.SHIPMENT_STOP_XID = SHIPMENT_STOP_XID;
    }

    @JsonProperty("SHIPMENT_STOP_NAME")
    public String getSHIPMENT_STOP_NAME() {
        return SHIPMENT_STOP_NAME;
    }

    public void setSHIPMENT_STOP_NAME(String SHIPMENT_STOP_NAME) {
        this.SHIPMENT_STOP_NAME = SHIPMENT_STOP_NAME;
    }

    @JsonProperty("PLANNED_DEPARTURE")
    public Date getPLANNED_DEPARTURE() {
        return PLANNED_DEPARTURE;
    }

    public void setPLANNED_DEPARTURE(Date PLANNED_DEPARTURE) {
        this.PLANNED_DEPARTURE = PLANNED_DEPARTURE;
    }

    @JsonProperty("PLANNED_ARRIVAL")
    public Date getPLANNED_ARRIVAL() {
        return PLANNED_ARRIVAL;
    }

    public void setPLANNED_ARRIVAL(Date PLANNED_ARRIVAL) {
        this.PLANNED_ARRIVAL = PLANNED_ARRIVAL;
    }

    @JsonProperty("LOCATION_PROVINCE")
    public String getLOCATION_PROVINCE() {
        return LOCATION_PROVINCE;
    }

    public void setLOCATION_PROVINCE(String LOCATION_PROVINCE) {
        this.LOCATION_PROVINCE = LOCATION_PROVINCE;
    }

    @JsonProperty("LOCATION_CITY")
    public String getLOCATION_CITY() {
        return LOCATION_CITY;
    }

    public void setLOCATION_CITY(String LOCATION_CITY) {
        this.LOCATION_CITY = LOCATION_CITY;
    }

    @JsonProperty("LOCATION_DISTRICT")
    public String getLOCATION_DISTRICT() {
        return LOCATION_DISTRICT;
    }

    public void setLOCATION_DISTRICT(String LOCATION_DISTRICT) {
        this.LOCATION_DISTRICT = LOCATION_DISTRICT;
    }

    @JsonProperty("LOCATION_ADDRESS")
    public String getLOCATION_ADDRESS() {
        return LOCATION_ADDRESS;
    }

    public void setLOCATION_ADDRESS(String LOCATION_ADDRESS) {
        this.LOCATION_ADDRESS = LOCATION_ADDRESS;
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
