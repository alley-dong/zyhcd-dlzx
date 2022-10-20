package com.dlzx.pfservercar.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OTMTransportTaskTrayModel {

    /** 接口表主键*/
    private String TEMPLATE_ID;
    /** 运单ID*/
    private String SHIPMENT_GID;
    /** 运单号*/
    private String shipmentXid;
    /** 订单ID*/
    private String ORDER_MOVEMENT_GID;
    /** 托单ID*/
    private String SHIP_UNIT_GID;
    /** 托盘代码*/
    private String TRAN_HANDLING_UNIT_GID;
    /** 托盘名称*/
    private String TRAN_HANDLING_UNIT_NAME;
    /** 托盘数量*/
    private String SHIP_UNIT_COUNT;

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

    @JsonProperty("SHIPMENT_GID")
    public String getSHIPMENT_GID() {
        return SHIPMENT_GID;
    }

    public void setSHIPMENT_GID(String SHIPMENT_GID) {
        this.SHIPMENT_GID = SHIPMENT_GID;
    }

    @JsonProperty("shipmentXid")
    public String getShipmentXid() {
        return shipmentXid;
    }

    public void setShipmentXid(String shipmentXid) {
        this.shipmentXid = shipmentXid;
    }

    @JsonProperty("TEMPLATE_ID")
    public String getTEMPLATE_ID() {
        return TEMPLATE_ID;
    }

    public void setTEMPLATE_ID(String TEMPLATE_ID) {
        this.TEMPLATE_ID = TEMPLATE_ID;
    }

    @JsonProperty("ORDER_MOVEMENT_GID")
    public String getORDER_MOVEMENT_GID() {
        return ORDER_MOVEMENT_GID;
    }

    public void setORDER_MOVEMENT_GID(String ORDER_MOVEMENT_GID) {
        this.ORDER_MOVEMENT_GID = ORDER_MOVEMENT_GID;
    }

    @JsonProperty("SHIP_UNIT_GID")
    public String getSHIP_UNIT_GID() {
        return SHIP_UNIT_GID;
    }

    public void setSHIP_UNIT_GID(String SHIP_UNIT_GID) {
        this.SHIP_UNIT_GID = SHIP_UNIT_GID;
    }

    @JsonProperty("TRAN_HANDLING_UNIT_GID")
    public String getTRAN_HANDLING_UNIT_GID() {
        return TRAN_HANDLING_UNIT_GID;
    }

    public void setTRAN_HANDLING_UNIT_GID(String TRAN_HANDLING_UNIT_GID) {
        this.TRAN_HANDLING_UNIT_GID = TRAN_HANDLING_UNIT_GID;
    }

    @JsonProperty("TRAN_HANDLING_UNIT_NAME")
    public String getTRAN_HANDLING_UNIT_NAME() {
        return TRAN_HANDLING_UNIT_NAME;
    }

    public void setTRAN_HANDLING_UNIT_NAME(String TRAN_HANDLING_UNIT_NAME) {
        this.TRAN_HANDLING_UNIT_NAME = TRAN_HANDLING_UNIT_NAME;
    }

    @JsonProperty("SHIP_UNIT_COUNT")
    public String getSHIP_UNIT_COUNT() {
        return SHIP_UNIT_COUNT;
    }

    public void setSHIP_UNIT_COUNT(String SHIP_UNIT_COUNT) {
        this.SHIP_UNIT_COUNT = SHIP_UNIT_COUNT;
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
