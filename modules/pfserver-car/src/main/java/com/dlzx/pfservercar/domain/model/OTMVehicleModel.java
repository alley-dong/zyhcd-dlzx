package com.dlzx.pfservercar.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 类描述：
 *
 * @className OTMVehicleModel
 * company: 赛拓信息技术有限公司
 * @author：DongCL
 * @date 2020-12-24 14:14
 */
public class OTMVehicleModel {

    /* 主键id */
    private String TEMPLATE_ID;
    /* 车牌号 */
    private String CAR_NUMBER;
    /* 承运商 */
    private String SERVPROV_NAME;
    /* 车辆类别 */
    private String CLASS_OF_VEHICLE;
    /* 车辆类型代码 */
    private String TRUCK_TYPE_CODE;
    /* 货品属性 */
    private String ITEM_ATTRIBUTE;
    /* 车辆颜色代码 */
    private String VEHICLE_PLATE_COLOR_CODE;
    /* 车辆能源类型 */
    private String VEHICLE_ENERGY_TYPE;
    /* 吨位（千克） */
    private String GROSS_MASS;
    /* 道路运输许可证号 */
    private String ROAD_TRANSPORT_CERT_NUM;
    /* 是否生效（Y/N） */
    private String ENABLED_FLAG;
    /* 车型 */
    private String EQUIPMENT_GROUP;
    /* 长（米） */
    private String LENGTH;
    /* 宽（米） */
    private String WIDTH;
    /* 高（米） */
    private String HEIGHT;
    /* 核载重量（千克） */
    private String EFFECTIVE_WEIGHT;



    /**
     * 非必填字段
     */
    /* 所有人 */
    private String OWNER;
    /* 使用性质 */
    private String USE_CHARACTER;
    /* 车辆识别代号 */
    private String VIN;
    /* 发证机关 */
    private String ISSUING_ORGANAZATIONS;
    /* 注册日期 */
    private String REGISTER_DATE;
    /* 发证日期 */
    private String ISSUE_DATE;
    /* 设备品牌 */
    private String DEVICE_BRAND;
    /* 设备号 */
    private String DEVICE_NUMBER;
    /* 核载体积（立方） */
    private String EFFECTIVE_VOLUME;


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
    @JsonProperty("CAR_NUMBER")
    public String getCAR_NUMBER() {
        return CAR_NUMBER;
    }

    public void setCAR_NUMBER(String CAR_NUMBER) {
        this.CAR_NUMBER = CAR_NUMBER;
    }

    @JsonProperty("VIN")
    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    @JsonProperty("REGISTER_DATE")
    public String getREGISTER_DATE() {
        return REGISTER_DATE;
    }

    public void setREGISTER_DATE(String REGISTER_DATE) {
        this.REGISTER_DATE = REGISTER_DATE;
    }

    @JsonProperty("CLASS_OF_VEHICLE")
    public String getCLASS_OF_VEHICLE() {
        return CLASS_OF_VEHICLE;
    }

    public void setCLASS_OF_VEHICLE(String CLASS_OF_VEHICLE) {
        this.CLASS_OF_VEHICLE = CLASS_OF_VEHICLE;
    }

    @JsonProperty("TRUCK_TYPE_CODE")
    public String getTRUCK_TYPE_CODE() {
        return TRUCK_TYPE_CODE;
    }

    public void setTRUCK_TYPE_CODE(String TRUCK_TYPE_CODE) {
        this.TRUCK_TYPE_CODE = TRUCK_TYPE_CODE;
    }

    @JsonProperty("VEHICLE_PLATE_COLOR_CODE")
    public String getVEHICLE_PLATE_COLOR_CODE() {
        return VEHICLE_PLATE_COLOR_CODE;
    }

    public void setVEHICLE_PLATE_COLOR_CODE(String VEHICLE_PLATE_COLOR_CODE) {
        this.VEHICLE_PLATE_COLOR_CODE = VEHICLE_PLATE_COLOR_CODE;
    }

    @JsonProperty("GROSS_MASS")
    public String getGROSS_MASS() {
        return GROSS_MASS;
    }

    public void setGROSS_MASS(String GROSS_MASS) {
        this.GROSS_MASS = GROSS_MASS;
    }

    @JsonProperty("ROAD_TRANSPORT_CERT_NUM")
    public String getROAD_TRANSPORT_CERT_NUM() {
        return ROAD_TRANSPORT_CERT_NUM;
    }

    public void setROAD_TRANSPORT_CERT_NUM(String ROAD_TRANSPORT_CERT_NUM) {
        this.ROAD_TRANSPORT_CERT_NUM = ROAD_TRANSPORT_CERT_NUM;
    }

    @JsonProperty("DEVICE_BRAND")
    public String getDEVICE_BRAND() {
        return DEVICE_BRAND;
    }

    public void setDEVICE_BRAND(String DEVICE_BRAND) {
        this.DEVICE_BRAND = DEVICE_BRAND;
    }

    @JsonProperty("EQUIPMENT_GROUP")
    public String getEQUIPMENT_GROUP() {
        return EQUIPMENT_GROUP;
    }

    public void setEQUIPMENT_GROUP(String EQUIPMENT_GROUP) {
        this.EQUIPMENT_GROUP = EQUIPMENT_GROUP;
    }

    @JsonProperty("LENGTH")
    public String getLENGTH() {
        return LENGTH;
    }

    public void setLENGTH(String LENGTH) {
        this.LENGTH = LENGTH;
    }

    @JsonProperty("WIDTH")
    public String getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(String WIDTH) {
        this.WIDTH = WIDTH;
    }

    @JsonProperty("HEIGHT")
    public String getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(String HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    @JsonProperty("EFFECTIVE_WEIGHT")
    public String getEFFECTIVE_WEIGHT() {
        return EFFECTIVE_WEIGHT;
    }

    public void setEFFECTIVE_WEIGHT(String EFFECTIVE_WEIGHT) {
        this.EFFECTIVE_WEIGHT = EFFECTIVE_WEIGHT;
    }

    @JsonProperty("VEHICLE_ENERGY_TYPE")
    public String getVEHICLE_ENERGY_TYPE() {
        return VEHICLE_ENERGY_TYPE;
    }

    @JsonProperty("SERVPROV_NAME")
    public String getSERVPROV_NAME() {
        return SERVPROV_NAME;
    }

    public void setSERVPROV_NAME(String SERVPROV_NAME) {
        this.SERVPROV_NAME = SERVPROV_NAME;
    }
    @JsonProperty("ITEM_ATTRIBUTE")
    public String getITEM_ATTRIBUTE() {
        return ITEM_ATTRIBUTE;
    }

    public void setITEM_ATTRIBUTE(String ITEM_ATTRIBUTE) {
        this.ITEM_ATTRIBUTE = ITEM_ATTRIBUTE;
    }
    @JsonProperty("ENABLED_FLAG")
    public String getENABLED_FLAG() {
        return ENABLED_FLAG;
    }

    public void setENABLED_FLAG(String ENABLED_FLAG) {
        this.ENABLED_FLAG = ENABLED_FLAG;
    }
    @JsonProperty("OWNER")
    public String getOWNER() {
        return OWNER;
    }

    public void setOWNER(String OWNER) {
        this.OWNER = OWNER;
    }
    @JsonProperty("USE_CHARACTER")
    public String getUSE_CHARACTER() {
        return USE_CHARACTER;
    }

    public void setUSE_CHARACTER(String USE_CHARACTER) {
        this.USE_CHARACTER = USE_CHARACTER;
    }
    @JsonProperty("ISSUING_ORGANAZATIONS")
    public String getISSUING_ORGANAZATIONS() {
        return ISSUING_ORGANAZATIONS;
    }

    public void setISSUING_ORGANAZATIONS(String ISSUING_ORGANAZATIONS) {
        this.ISSUING_ORGANAZATIONS = ISSUING_ORGANAZATIONS;
    }
    @JsonProperty("ISSUE_DATE")
    public String getISSUE_DATE() {
        return ISSUE_DATE;
    }

    public void setISSUE_DATE(String ISSUE_DATE) {
        this.ISSUE_DATE = ISSUE_DATE;
    }
    @JsonProperty("DEVICE_NUMBER")
    public String getDEVICE_NUMBER() {
        return DEVICE_NUMBER;
    }

    public void setDEVICE_NUMBER(String DEVICE_NUMBER) {
        this.DEVICE_NUMBER = DEVICE_NUMBER;
    }
    @JsonProperty("EFFECTIVE_VOLUME")
    public String getEFFECTIVE_VOLUME() {
        return EFFECTIVE_VOLUME;
    }

    public void setEFFECTIVE_VOLUME(String EFFECTIVE_VOLUME) {
        this.EFFECTIVE_VOLUME = EFFECTIVE_VOLUME;
    }

    public void setVEHICLE_ENERGY_TYPE(String VEHICLE_ENERGY_TYPE) {
        this.VEHICLE_ENERGY_TYPE = VEHICLE_ENERGY_TYPE;
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
