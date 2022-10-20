package com.dlzx.pfservercar.domain.model;

import com.dlzx.common.core.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class OTMTransportTaskOrderModel {

    /** ID(主键ID) */
    private String TEMPLATE_ID;

    /** 运单ID(任务ID) */
    private String SHIPMENT_GID;

    /** 站点ID */
    private String SHIPMENT_STOP_ID;

    /** 订单ID(订单编号) */
    private String ORDER_MOVEMENT_GID;

    /** 运单号 */
    private String shipmentXid;

    /** 订单编号 */
    private String ORDER_MOVEMENT_XID;

    /** 业务类型 */
    private String BUSINESS_TYPE;

    /** 没用字段 */
    private String BUSINESS_UNIT_NAME;
    private String SOURCE_LOCATION_CODE;
    private String SOURCE_LOCATION_NAME;
    private String SOURCE_PROVINCE;
    private String SOURCE_CITY;
    private String SOURCE_DISTRICT;
    private String SOURCE_ADDRESS;
    private String SOURCE_CONTACTS;
    private String SOURCE_PHONE_NUMBER;
    private String DEST_LOCATION_CODE;
    private String DEST_LOCATION_NAME;
    private String DEST_PROVINCE;
    private String DEST_CITY;
    private String DEST_DISTRICT;
    private String DEST_ADDRESS;
    private String DEST_CONTACTS;
    private String DEST_PHONE_NUMBER;
    private String REMARK;

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

    @JsonProperty("shipmentXid")
    public String getShipmentXid() {
        return shipmentXid;
    }

    public void setShipmentXid(String shipmentXid) {
        this.shipmentXid = shipmentXid;
    }

    @JsonProperty("SHIPMENT_GID")
    public String getSHIPMENT_GID() {
        return SHIPMENT_GID;
    }

    public void setSHIPMENT_GID(String SHIPMENT_GID) {
        this.SHIPMENT_GID = SHIPMENT_GID;
    }

    @JsonProperty("SHIPMENT_STOP_ID")
    public String getSHIPMENT_STOP_ID() {
        return SHIPMENT_STOP_ID;
    }

    public void setSHIPMENT_STOP_ID(String SHIPMENT_STOP_ID) {
        this.SHIPMENT_STOP_ID = SHIPMENT_STOP_ID;
    }

    @JsonProperty("ORDER_MOVEMENT_GID")
    public String getORDER_MOVEMENT_GID() {
        return ORDER_MOVEMENT_GID;
    }

    public void setORDER_MOVEMENT_GID(String ORDER_MOVEMENT_GID) {
        this.ORDER_MOVEMENT_GID = ORDER_MOVEMENT_GID;
    }

    @JsonProperty("ORDER_MOVEMENT_XID")
    public String getORDER_MOVEMENT_XID() {
        return ORDER_MOVEMENT_XID;
    }

    public void setORDER_MOVEMENT_XID(String ORDER_MOVEMENT_XID) {
        this.ORDER_MOVEMENT_XID = ORDER_MOVEMENT_XID;
    }

    @JsonProperty("BUSINESS_TYPE")
    public String getBUSINESS_TYPE() {
        return BUSINESS_TYPE;
    }

    public void setBUSINESS_TYPE(String BUSINESS_TYPE) {
        this.BUSINESS_TYPE = BUSINESS_TYPE;
    }

    @JsonProperty("BUSINESS_UNIT_NAME")
    public String getBUSINESS_UNIT_NAME() {
        return BUSINESS_UNIT_NAME;
    }

    public void setBUSINESS_UNIT_NAME(String BUSINESS_UNIT_NAME) {
        this.BUSINESS_UNIT_NAME = BUSINESS_UNIT_NAME;
    }

    @JsonProperty("SOURCE_LOCATION_CODE")
    public String getSOURCE_LOCATION_CODE() {
        return SOURCE_LOCATION_CODE;
    }

    public void setSOURCE_LOCATION_CODE(String SOURCE_LOCATION_CODE) {
        this.SOURCE_LOCATION_CODE = SOURCE_LOCATION_CODE;
    }

    @JsonProperty("SOURCE_LOCATION_NAME")
    public String getSOURCE_LOCATION_NAME() {
        return SOURCE_LOCATION_NAME;
    }

    public void setSOURCE_LOCATION_NAME(String SOURCE_LOCATION_NAME) {
        this.SOURCE_LOCATION_NAME = SOURCE_LOCATION_NAME;
    }

    @JsonProperty("SOURCE_PROVINCE")
    public String getSOURCE_PROVINCE() {
        return SOURCE_PROVINCE;
    }

    public void setSOURCE_PROVINCE(String SOURCE_PROVINCE) {
        this.SOURCE_PROVINCE = SOURCE_PROVINCE;
    }

    @JsonProperty("SOURCE_CITY")
    public String getSOURCE_CITY() {
        return SOURCE_CITY;
    }

    public void setSOURCE_CITY(String SOURCE_CITY) {
        this.SOURCE_CITY = SOURCE_CITY;
    }

    @JsonProperty("SOURCE_DISTRICT")
    public String getSOURCE_DISTRICT() {
        return SOURCE_DISTRICT;
    }

    public void setSOURCE_DISTRICT(String SOURCE_DISTRICT) {
        this.SOURCE_DISTRICT = SOURCE_DISTRICT;
    }

    @JsonProperty("SOURCE_ADDRESS")
    public String getSOURCE_ADDRESS() {
        return SOURCE_ADDRESS;
    }

    public void setSOURCE_ADDRESS(String SOURCE_ADDRESS) {
        this.SOURCE_ADDRESS = SOURCE_ADDRESS;
    }

    @JsonProperty("SOURCE_CONTACTS")
    public String getSOURCE_CONTACTS() {
        return SOURCE_CONTACTS;
    }

    public void setSOURCE_CONTACTS(String SOURCE_CONTACTS) {
        this.SOURCE_CONTACTS = SOURCE_CONTACTS;
    }

    @JsonProperty("SOURCE_PHONE_NUMBER")
    @NotNull(message = "111111111")
    public String getSOURCE_PHONE_NUMBER() {
        return SOURCE_PHONE_NUMBER;
    }

    public void setSOURCE_PHONE_NUMBER(String SOURCE_PHONE_NUMBER) {
        this.SOURCE_PHONE_NUMBER = SOURCE_PHONE_NUMBER;
    }

    @JsonProperty("DEST_LOCATION_CODE")
    public String getDEST_LOCATION_CODE() {
        return DEST_LOCATION_CODE;
    }

    public void setDEST_LOCATION_CODE(String DEST_LOCATION_CODE) {
        this.DEST_LOCATION_CODE = DEST_LOCATION_CODE;
    }

    @JsonProperty("DEST_LOCATION_NAME")
    public String getDEST_LOCATION_NAME() {
        return DEST_LOCATION_NAME;
    }

    public void setDEST_LOCATION_NAME(String DEST_LOCATION_NAME) {
        this.DEST_LOCATION_NAME = DEST_LOCATION_NAME;
    }

    @JsonProperty("DEST_PROVINCE")
    public String getDEST_PROVINCE() {
        return DEST_PROVINCE;
    }

    public void setDEST_PROVINCE(String DEST_PROVINCE) {
        this.DEST_PROVINCE = DEST_PROVINCE;
    }

    @JsonProperty("DEST_CITY")
    public String getDEST_CITY() {
        return DEST_CITY;
    }

    public void setDEST_CITY(String DEST_CITY) {
        this.DEST_CITY = DEST_CITY;
    }

    @JsonProperty("DEST_DISTRICT")
    public String getDEST_DISTRICT() {
        return DEST_DISTRICT;
    }

    public void setDEST_DISTRICT(String DEST_DISTRICT) {
        this.DEST_DISTRICT = DEST_DISTRICT;
    }

    @JsonProperty("DEST_ADDRESS")
    public String getDEST_ADDRESS() {
        return DEST_ADDRESS;
    }

    public void setDEST_ADDRESS(String DEST_ADDRESS) {
        this.DEST_ADDRESS = DEST_ADDRESS;
    }

    @JsonProperty("DEST_CONTACTS")
    public String getDEST_CONTACTS() {
        return DEST_CONTACTS;
    }

    public void setDEST_CONTACTS(String DEST_CONTACTS) {
        this.DEST_CONTACTS = DEST_CONTACTS;
    }

    @JsonProperty("DEST_PHONE_NUMBER")
    @NotNull(message = "请输入收货联系电话")
    public String getDEST_PHONE_NUMBER() {
        return DEST_PHONE_NUMBER;
    }

    public void setDEST_PHONE_NUMBER(String DEST_PHONE_NUMBER) {
        this.DEST_PHONE_NUMBER = DEST_PHONE_NUMBER;
    }

    @JsonProperty("REMARK")
    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
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
