package com.dlzx.pfservercar.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * 类描述：
 * @ClassName OTMDriverModel
 * Company: 赛拓信息技术有限公司
 * @author：DongCL
 * @Date 2020-12-24 11:38
 */
public class OTMDriverModel {

    /**
     * 主键id
     */
    private String TEMPLATE_ID;

    /**
     * 司机姓名
     */
    private String NAME;

    /**
     * 手机号
     */
    private String PHONE;

    /**
     * 身份证号
     */
    private String ID_NUMBER;

    /**
     * 从业资格证号
     */
    private String QUALIFICATION_CERTIFICATE;

    /**
     * 是否生效
     */
    private String ENABLED_FLAG;

    /**
     * 准驾车型
     */
    private String VEHICLE_CLASS;



    /** 非必填字段*/
    /**
     * 固定车辆
     * */
    private String CAR_NUMBER;
    /**
     * 备注
     * */
    private String DRIVER_REMARK;
    /**
     * 所属承运商
     * */
    private String SERVPROV_NAME;
    /**
     * 驾驶证发证机关
     */
    private String ISSUING_ORGANAZATIONS;
    /**
     * 驾驶证有效期自
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date VALID_PERIOD_FROM;
    /**
     * 驾驶证有效期至
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date VALID_PERIOD_TO;


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

    @JsonProperty("NAME")
    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    @JsonProperty("PHONE")
    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }
    @JsonProperty("ID_NUMBER")
    public String getID_NUMBER() {
        return ID_NUMBER;
    }

    public void setID_NUMBER(String ID_NUMBER) {
        this.ID_NUMBER = ID_NUMBER;
    }
    @JsonProperty("QUALIFICATION_CERTIFICATE")
    public String getQUALIFICATION_CERTIFICATE() {
        return QUALIFICATION_CERTIFICATE;
    }

    public void setQUALIFICATION_CERTIFICATE(String QUALIFICATION_CERTIFICATE) {
        this.QUALIFICATION_CERTIFICATE = QUALIFICATION_CERTIFICATE;
    }
    @JsonProperty("ENABLED_FLAG")
    public String getENABLED_FLAG() {
        return ENABLED_FLAG;
    }

    public void setENABLED_FLAG(String ENABLED_FLAG) {
        this.ENABLED_FLAG = ENABLED_FLAG;
    }
    @JsonProperty("VEHICLE_CLASS")
    public String getVEHICLE_CLASS() {
        return VEHICLE_CLASS;
    }

    public void setVEHICLE_CLASS(String VEHICLE_CLASS) {
        this.VEHICLE_CLASS = VEHICLE_CLASS;
    }

    @JsonProperty("CAR_NUMBER")
    public String getCAR_NUMBER() {
        return CAR_NUMBER;
    }

    public void setCAR_NUMBER(String CAR_NUMBER) {
        this.CAR_NUMBER = CAR_NUMBER;
    }

    @JsonProperty("DRIVER_REMARK")
    public String getDRIVER_REMARK() {
        return DRIVER_REMARK;
    }

    public void setDRIVER_REMARK(String DRIVER_REMARK) {
        this.DRIVER_REMARK = DRIVER_REMARK;
    }
    @JsonProperty("SERVPROV_NAME")
    public String getSERVPROV_NAME() {
        return SERVPROV_NAME;
    }

    public void setSERVPROV_NAME(String SERVPROV_NAME) {
        this.SERVPROV_NAME = SERVPROV_NAME;
    }
    @JsonProperty("ISSUING_ORGANAZATIONS")
    public String getISSUING_ORGANAZATIONS() {
        return ISSUING_ORGANAZATIONS;
    }

    public void setISSUING_ORGANAZATIONS(String ISSUING_ORGANAZATIONS) {
        this.ISSUING_ORGANAZATIONS = ISSUING_ORGANAZATIONS;
    }
    @JsonProperty("VALID_PERIOD_FROM")
    public Date getVALID_PERIOD_FROM() {
        return VALID_PERIOD_FROM;
    }

    public void setVALID_PERIOD_FROM(Date VALID_PERIOD_FROM) {
        this.VALID_PERIOD_FROM = VALID_PERIOD_FROM;
    }
    @JsonProperty("VALID_PERIOD_TO")
    public Date getVALID_PERIOD_TO() {
        return VALID_PERIOD_TO;
    }

    public void setVALID_PERIOD_TO(Date VALID_PERIOD_TO) {
        this.VALID_PERIOD_TO = VALID_PERIOD_TO;
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
