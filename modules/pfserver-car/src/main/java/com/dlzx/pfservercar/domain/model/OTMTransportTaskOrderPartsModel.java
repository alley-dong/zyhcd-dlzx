package com.dlzx.pfservercar.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OTMTransportTaskOrderPartsModel {

    /** ID(主键ID) */
    private String TEMPLATE_ID;

    /** SHIPMENT_GID(任务ID) */
    private String SHIPMENT_GID;

    /** 运单号 */
    private String shipmentXid;

    /** 业务类型 */
    private String BUSINESS_TYPE;

    /** 订单行ID */
    private String ORDER_MOVEMENT_LINE_GID;

    /** 订单ID(订单编号) */
    private String ORDER_MOVEMENT_GID;

    /** 零件ID */
    private String PACKAGED_ITEM_GID;

    /** 零件编号 */
    private String PACKAGED_ITEM_XID;

    /** 零件名称 */
    private String PACKAGED_ITEM_NAME;

    /** 零件类型 */
    private String PACKAGED_ITEM_TYPE;

    /** 零件数量 */
    private String PACKAGE_ITEM_COUNT;


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

    @JsonProperty("BUSINESS_TYPE")
    public String getBUSINESS_TYPE() {
        return BUSINESS_TYPE;
    }

    public void setBUSINESS_TYPE(String BUSINESS_TYPE) {
        this.BUSINESS_TYPE = BUSINESS_TYPE;
    }

    @JsonProperty("TEMPLATE_ID")
    public String getTEMPLATE_ID() {
        return TEMPLATE_ID;
    }

    public void setTEMPLATE_ID(String TEMPLATE_ID) {
        this.TEMPLATE_ID = TEMPLATE_ID;
    }

    @JsonProperty("ORDER_MOVEMENT_LINE_GID")
    public String getORDER_MOVEMENT_LINE_GID() {
        return ORDER_MOVEMENT_LINE_GID;
    }

    public void setORDER_MOVEMENT_LINE_GID(String ORDER_MOVEMENT_LINE_GID) {
        this.ORDER_MOVEMENT_LINE_GID = ORDER_MOVEMENT_LINE_GID;
    }

    @JsonProperty("ORDER_MOVEMENT_GID")
    public String getORDER_MOVEMENT_GID() {
        return ORDER_MOVEMENT_GID;
    }

    public void setORDER_MOVEMENT_GID(String ORDER_MOVEMENT_GID) {
        this.ORDER_MOVEMENT_GID = ORDER_MOVEMENT_GID;
    }

    @JsonProperty("PACKAGED_ITEM_GID")
    public String getPACKAGED_ITEM_GID() {
        return PACKAGED_ITEM_GID;
    }

    public void setPACKAGED_ITEM_GID(String PACKAGED_ITEM_GID) {
        this.PACKAGED_ITEM_GID = PACKAGED_ITEM_GID;
    }

    @JsonProperty("PACKAGED_ITEM_XID")
    public String getPACKAGED_ITEM_XID() {
        return PACKAGED_ITEM_XID;
    }

    public void setPACKAGED_ITEM_XID(String PACKAGED_ITEM_XID) {
        this.PACKAGED_ITEM_XID = PACKAGED_ITEM_XID;
    }

    @JsonProperty("PACKAGED_ITEM_NAME")
    public String getPACKAGED_ITEM_NAME() {
        return PACKAGED_ITEM_NAME;
    }

    public void setPACKAGED_ITEM_NAME(String PACKAGED_ITEM_NAME) {
        this.PACKAGED_ITEM_NAME = PACKAGED_ITEM_NAME;
    }

    @JsonProperty("PACKAGED_ITEM_TYPE")
    public String getPACKAGED_ITEM_TYPE() {
        return PACKAGED_ITEM_TYPE;
    }

    public void setPACKAGED_ITEM_TYPE(String PACKAGED_ITEM_TYPE) {
        this.PACKAGED_ITEM_TYPE = PACKAGED_ITEM_TYPE;
    }

    @JsonProperty("PACKAGE_ITEM_COUNT")
    public String getPACKAGE_ITEM_COUNT() {
        return PACKAGE_ITEM_COUNT;
    }

    public void setPACKAGE_ITEM_COUNT(String PACKAGE_ITEM_COUNT) {
        this.PACKAGE_ITEM_COUNT = PACKAGE_ITEM_COUNT;
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
