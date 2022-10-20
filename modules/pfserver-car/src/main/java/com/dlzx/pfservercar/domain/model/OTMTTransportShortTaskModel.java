package com.dlzx.pfservercar.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class OTMTTransportShortTaskModel {

    /** 主键*/
    private String TEMPLATE_ID;

    /** 承运商*/
    private String SERVPROV_NAME;

    /** 绩效日期*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date machine_date;

    /** 驾驶员手机*/
    private String phone_number;

    /** 驾驶员姓名*/
    private String driver_name;

    /** 台班数*/
    private Long total_machine_time_count;

    /** 绩效金额*/
    private BigDecimal machine_time_price;

    /**
     * 扩展字段
    * */
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

    @JsonProperty("SERVPROV_NAME")
    public String getSERVPROV_NAME() {
        return SERVPROV_NAME;
    }

    public void setSERVPROV_NAME(String SERVPROV_NAME) {
        this.SERVPROV_NAME = SERVPROV_NAME;
    }

    @JsonProperty("machine_date")
    public Date getMachine_date() {
        return machine_date;
    }

    public void setMachine_date(Date machine_date) {
        this.machine_date = machine_date;
    }

    @JsonProperty("phone_number")
    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @JsonProperty("driver_name")
    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    @JsonProperty("total_machine_time_count")
    public Long getTotal_machine_time_count() {
        return total_machine_time_count;
    }

    public void setTotal_machine_time_count(Long total_machine_time_count) {
        this.total_machine_time_count = total_machine_time_count;
    }

    @JsonProperty("machine_time_price")
    public BigDecimal getMachine_time_price() {
        return machine_time_price;
    }

    public void setMachine_time_price(BigDecimal machine_time_price) {
        this.machine_time_price = machine_time_price;
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
