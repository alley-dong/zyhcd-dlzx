package com.dlzx.pfservercar.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OTMRouteModel {

    /** 接口表主键*/
    private String TEMPLATE_ID;
    /* 线路编码 */
    private String X_LANE_XID;
    /* 线路名称 */
    private String X_LANE_NAME;
    /* 起始地点代码 */
    private String SOURCE_LOCATION_CODE;
    /* 起始地点名称 */
    private String SOURCE_LOCATION_NAME;
    /* 起始地点省份 */
    private String SOURCE_PROVINCE;
    /* 起始地点城市 */
    private String SOURCE_CITY;
    /* 起始地点区县 */
    private String SOURCE_DISTRICT;
    /* 终止地点代码 */
    private String DEST_LOCATION_CODE;
    /* 终止地点名称 */
    private String DEST_LOCATION_NAME;
    /* 终止地点省份 */
    private String DEST_PROVINCE;
    /* 终止地点城市 */
    private String DEST_CITY;
    /* 终止地点区县 */
    private String DEST_DISTRICT;
    /* 里程 */
    private String DISTANCE;


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


    public String getTEMPLATE_ID() {
        return TEMPLATE_ID;
    }

    public void setTEMPLATE_ID(String TEMPLATE_ID) {
        this.TEMPLATE_ID = TEMPLATE_ID;
    }

    @JsonProperty("X_LANE_XID")
    public String getX_LANE_XID() {
        return X_LANE_XID;
    }

    public void setX_LANE_XID(String x_LANE_XID) {
        X_LANE_XID = x_LANE_XID;
    }
    @JsonProperty("X_LANE_NAME")
    public String getX_LANE_NAME() {
        return X_LANE_NAME;
    }

    public void setX_LANE_NAME(String x_LANE_NAME) {
        X_LANE_NAME = x_LANE_NAME;
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
    @JsonProperty("DISTANCE")
    public String getDISTANCE() {
        return DISTANCE;
    }

    public void setDISTANCE(String DISTANCE) {
        this.DISTANCE = DISTANCE;
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
