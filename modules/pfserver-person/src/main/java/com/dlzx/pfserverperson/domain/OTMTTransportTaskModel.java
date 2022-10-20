package com.dlzx.pfserverperson.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

public class OTMTTransportTaskModel {

    /** 主键ID*/
    private String TEMPLATE_ID;

    /* 运单GID */
    private String SHIPMENT_GID;

    /* 运单号 */
    private String SHIPMENT_XID;

    /* 线路编码 */
    private String X_LANE_GID;

    /* 线路名称 */
    private String SHIPMENT_NAME;

    /* 运单类型 */
    private String SHIPMENT_TYPE;

    /* 运输方式 */
    private String TRANSPORT_MODE_NAME;

    /* 承运商名称 */
    private String SERVPROV_NAME;

    /* 业务部门 */
    private String BUSINESS_UNIT_NAME;

    /* 客户名称 */
    private String CUSTOMER_NAME;

    /* 出发地编码 */
    private String SOURCE_LOCATION_CODE;

    /* 出发地名称 */
    private String SOURCE_LOCATION_NAME;

    /* 发货省 */
    private String SOURCE_PROVINCE;

    /* 发货市 */
    private String SOURCE_CITY;

    /* 发货区县 */
    private String SOURCE_DISTRICT;

    /* 发货地址 */
    private String SOURCE_ADDRESS;

    /* 发货联系人 */
    private String SOURCE_CONTACTS;

    /* 发货联系电话 */
    private String SOURCE_PHONE_NUMBER;

    /* 目的地编码 */
    private String DEST_LOCATION_CODE;

    /* 收货点名称 */
    private String DEST_LOCATION_NAME;

    /* 收货省 */
    private String DEST_PROVINCE;

    /* 收货市 */
    private String DEST_CITY;

    /* 收货区县 */
    private String DEST_DISTRICT;

    /* 收货地址 */
    private String DEST_ADDRESS;

    /* 收货联系人 */
    private String DEST_CONTACTS;

    /* 收货联系电话 */
    private String DEST_PHONE_NUMBER;

    /** 主车ID */
    private String mainVehicleId;

    /* 主车车牌号 */
    private String EQUIPMENT_NUMBER;

    /** 主车车型ID */
    private String mainVehicleModel;

    /** 主车车型 */
    private String mainVehicleModelName;

    /** 挂车ID */
    private String hangVehicleId;

    /* 挂车车牌号 */
    private String VEHICLE_PLATE_NUMBER;

    /** 挂车车型ID */
    private String hangVehicleModel;

    /** 挂车车型 */
    private String hangVehicleModelName;

    /** 司机ID-关联d_driver表 */
    private String driverId;

    /* 司机姓名 */
    private String DRIVER_NAME;

    /* 司机电话 */
    private String DRIVER_NUMBER;

    /** 副司机ID-关联d_driver表 */
    private String viceDriverId;

    /* 副司机姓名 */
    private String ASSISTANT_DRIVER_NAME;

    /* 副司机电话 */
    private String ASSISTANT_DRIVER_NUMBER;

    /* 计划提货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date PLAN_START_SHIPPING_DATE;

    /* 计划送到时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date PLAN_DELIVERY_DATE;

    /* 总数量 */
    private String TOTAL_ITEM_COUNT;

    /* 总重量 */
    private String TOTAL_GROSS_WEIGHT;

    /* 总体积 */
    private String TOTAL_GROSS_VOLUME;

    /* 总包装数量 */
    private String TOTAL_PACKAGE_COUNT;

    /* 站点数 */
    private String TOTAL_STOP_COUNT;

    /* 备注 */
    private String SHIPMENT_REMARK;

    /* 任务开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ACTUAL_DEPARTURE;

    /* 任务结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ACTUAL_ARRIVAL;

    /** 业务类型*/
    private Long businessType;

    /* 扩展字段 */
    /** 干线运输单号*/
    private String EXTEND_TEXT1;
    /** 加班费*/
    private String EXTEND_TEXT2;
    /** 燃油费*/
    private String EXTEND_TEXT3;
    private String EXTEND_TEXT4;
    private String EXTEND_TEXT5;
    private String EXTEND_TEXT6;
    private String EXTEND_TEXT7;
    private String EXTEND_TEXT8;
    private String EXTEND_TEXT9;
    private String EXTEND_TEXT10;


    public Long getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Long businessType) {
        this.businessType = businessType;
    }

    @JsonProperty("TEMPLATE_ID")
    public String getTEMPLATE_ID() {
        return TEMPLATE_ID;
    }

    public void setTEMPLATE_ID(String TEMPLATE_ID) {
        this.TEMPLATE_ID = TEMPLATE_ID;
    }

    @JsonProperty("X_LANE_GID")
    public String getX_LANE_GID() {
        return X_LANE_GID;
    }

    public void setX_LANE_GID(String x_LANE_GID) {
        X_LANE_GID = x_LANE_GID;
    }

    @JsonProperty("ACTUAL_DEPARTURE")
    public Date getACTUAL_DEPARTURE() {
        return ACTUAL_DEPARTURE;
    }

    public void setACTUAL_DEPARTURE(Date ACTUAL_DEPARTURE) {
        this.ACTUAL_DEPARTURE = ACTUAL_DEPARTURE;
    }

    @JsonProperty("ACTUAL_ARRIVAL")
    public Date getACTUAL_ARRIVAL() {
        return ACTUAL_ARRIVAL;
    }

    public void setACTUAL_ARRIVAL(Date ACTUAL_ARRIVAL) {
        this.ACTUAL_ARRIVAL = ACTUAL_ARRIVAL;
    }

    public String getMainVehicleId() {
        return mainVehicleId;
    }

    public void setMainVehicleId(String mainVehicleId) {
        this.mainVehicleId = mainVehicleId;
    }

    public String getMainVehicleModel() {
        return mainVehicleModel;
    }

    public void setMainVehicleModel(String mainVehicleModel) {
        this.mainVehicleModel = mainVehicleModel;
    }

    public String getMainVehicleModelName() {
        return mainVehicleModelName;
    }

    public void setMainVehicleModelName(String mainVehicleModelName) {
        this.mainVehicleModelName = mainVehicleModelName;
    }

    public String getHangVehicleId() {
        return hangVehicleId;
    }

    public void setHangVehicleId(String hangVehicleId) {
        this.hangVehicleId = hangVehicleId;
    }

    public String getHangVehicleModel() {
        return hangVehicleModel;
    }

    public void setHangVehicleModel(String hangVehicleModel) {
        this.hangVehicleModel = hangVehicleModel;
    }

    public String getHangVehicleModelName() {
        return hangVehicleModelName;
    }

    public void setHangVehicleModelName(String hangVehicleModelName) {
        this.hangVehicleModelName = hangVehicleModelName;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getViceDriverId() {
        return viceDriverId;
    }

    public void setViceDriverId(String viceDriverId) {
        this.viceDriverId = viceDriverId;
    }

    @JsonProperty("SHIPMENT_GID")
    public String getSHIPMENT_GID() {
        return SHIPMENT_GID;
    }

    public void setSHIPMENT_GID(String SHIPMENT_GID) {
        this.SHIPMENT_GID = SHIPMENT_GID;
    }
    @JsonProperty("SHIPMENT_XID")
    public String getSHIPMENT_XID() {
        return SHIPMENT_XID;
    }

    public void setSHIPMENT_XID(String SHIPMENT_XID) {
        this.SHIPMENT_XID = SHIPMENT_XID;
    }
    @JsonProperty("SHIPMENT_NAME")
    public String getSHIPMENT_NAME() {
        return SHIPMENT_NAME;
    }

    public void setSHIPMENT_NAME(String SHIPMENT_NAME) {
        this.SHIPMENT_NAME = SHIPMENT_NAME;
    }
    @JsonProperty("SHIPMENT_TYPE")
    public String getSHIPMENT_TYPE() {
        return SHIPMENT_TYPE;
    }

    public void setSHIPMENT_TYPE(String SHIPMENT_TYPE) {
        this.SHIPMENT_TYPE = SHIPMENT_TYPE;
    }
    @JsonProperty("TRANSPORT_MODE_NAME")
    public String getTRANSPORT_MODE_NAME() {
        return TRANSPORT_MODE_NAME;
    }

    public void setTRANSPORT_MODE_NAME(String TRANSPORT_MODE_NAME) {
        this.TRANSPORT_MODE_NAME = TRANSPORT_MODE_NAME;
    }
    @JsonProperty("SERVPROV_NAME")
    public String getSERVPROV_NAME() {
        return SERVPROV_NAME;
    }

    public void setSERVPROV_NAME(String SERVPROV_NAME) {
        this.SERVPROV_NAME = SERVPROV_NAME;
    }
    @JsonProperty("BUSINESS_UNIT_NAME")
    public String getBUSINESS_UNIT_NAME() {
        return BUSINESS_UNIT_NAME;
    }

    public void setBUSINESS_UNIT_NAME(String BUSINESS_UNIT_NAME) {
        this.BUSINESS_UNIT_NAME = BUSINESS_UNIT_NAME;
    }@JsonProperty("CUSTOMER_NAME")

    public String getCUSTOMER_NAME() {
        return CUSTOMER_NAME;
    }

    public void setCUSTOMER_NAME(String CUSTOMER_NAME) {
        this.CUSTOMER_NAME = CUSTOMER_NAME;
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
    public String getDEST_PHONE_NUMBER() {
        return DEST_PHONE_NUMBER;
    }

    public void setDEST_PHONE_NUMBER(String DEST_PHONE_NUMBER) {
        this.DEST_PHONE_NUMBER = DEST_PHONE_NUMBER;
    }
    @JsonProperty("EQUIPMENT_NUMBER")
    public String getEQUIPMENT_NUMBER() {
        return EQUIPMENT_NUMBER;
    }

    public void setEQUIPMENT_NUMBER(String EQUIPMENT_NUMBER) {
        this.EQUIPMENT_NUMBER = EQUIPMENT_NUMBER;
    }
    @JsonProperty("VEHICLE_PLATE_NUMBER")
    public String getVEHICLE_PLATE_NUMBER() {
        return VEHICLE_PLATE_NUMBER;
    }

    public void setVEHICLE_PLATE_NUMBER(String VEHICLE_PLATE_NUMBER) {
        this.VEHICLE_PLATE_NUMBER = VEHICLE_PLATE_NUMBER;
    }
    @JsonProperty("DRIVER_NAME")
    public String getDRIVER_NAME() {
        return DRIVER_NAME;
    }

    public void setDRIVER_NAME(String DRIVER_NAME) {
        this.DRIVER_NAME = DRIVER_NAME;
    }
    @JsonProperty("DRIVER_NUMBER")
    public String getDRIVER_NUMBER() {
        return DRIVER_NUMBER;
    }

    public void setDRIVER_NUMBER(String DRIVER_NUMBER) {
        this.DRIVER_NUMBER = DRIVER_NUMBER;
    }
    @JsonProperty("ASSISTANT_DRIVER_NAME")
    public String getASSISTANT_DRIVER_NAME() {
        return ASSISTANT_DRIVER_NAME;
    }

    public void setASSISTANT_DRIVER_NAME(String ASSISTANT_DRIVER_NAME) {
        this.ASSISTANT_DRIVER_NAME = ASSISTANT_DRIVER_NAME;
    }
    @JsonProperty("ASSISTANT_DRIVER_NUMBER")
    public String getASSISTANT_DRIVER_NUMBER() {
        return ASSISTANT_DRIVER_NUMBER;
    }

    public void setASSISTANT_DRIVER_NUMBER(String ASSISTANT_DRIVER_NUMBER) {
        this.ASSISTANT_DRIVER_NUMBER = ASSISTANT_DRIVER_NUMBER;
    }
    @JsonProperty("PLAN_START_SHIPPING_DATE")
    public Date getPLAN_START_SHIPPING_DATE() {
        return PLAN_START_SHIPPING_DATE;
    }

    public void setPLAN_START_SHIPPING_DATE(Date PLAN_START_SHIPPING_DATE) {
        this.PLAN_START_SHIPPING_DATE = PLAN_START_SHIPPING_DATE;
    }
    @JsonProperty("PLAN_DELIVERY_DATE")
    public Date getPLAN_DELIVERY_DATE() {
        return PLAN_DELIVERY_DATE;
    }

    public void setPLAN_DELIVERY_DATE(Date PLAN_DELIVERY_DATE) {
        this.PLAN_DELIVERY_DATE = PLAN_DELIVERY_DATE;
    }
    @JsonProperty("TOTAL_ITEM_COUNT")
    public String getTOTAL_ITEM_COUNT() {
        return TOTAL_ITEM_COUNT;
    }

    public void setTOTAL_ITEM_COUNT(String TOTAL_ITEM_COUNT) {
        this.TOTAL_ITEM_COUNT = TOTAL_ITEM_COUNT;
    }
    @JsonProperty("TOTAL_GROSS_WEIGHT")
    public String getTOTAL_GROSS_WEIGHT() {
        return TOTAL_GROSS_WEIGHT;
    }

    public void setTOTAL_GROSS_WEIGHT(String TOTAL_GROSS_WEIGHT) {
        this.TOTAL_GROSS_WEIGHT = TOTAL_GROSS_WEIGHT;
    }
    @JsonProperty("TOTAL_GROSS_VOLUME")
    public String getTOTAL_GROSS_VOLUME() {
        return TOTAL_GROSS_VOLUME;
    }

    public void setTOTAL_GROSS_VOLUME(String TOTAL_GROSS_VOLUME) {
        this.TOTAL_GROSS_VOLUME = TOTAL_GROSS_VOLUME;
    }
    @JsonProperty("TOTAL_PACKAGE_COUNT")
    public String getTOTAL_PACKAGE_COUNT() {
        return TOTAL_PACKAGE_COUNT;
    }

    public void setTOTAL_PACKAGE_COUNT(String TOTAL_PACKAGE_COUNT) {
        this.TOTAL_PACKAGE_COUNT = TOTAL_PACKAGE_COUNT;
    }
    @JsonProperty("TOTAL_STOP_COUNT")
    public String getTOTAL_STOP_COUNT() {
        return TOTAL_STOP_COUNT;
    }

    public void setTOTAL_STOP_COUNT(String TOTAL_STOP_COUNT) {
        this.TOTAL_STOP_COUNT = TOTAL_STOP_COUNT;
    }
    @JsonProperty("SHIPMENT_REMARK")
    public String getSHIPMENT_REMARK() {
        return SHIPMENT_REMARK;
    }

    public void setSHIPMENT_REMARK(String SHIPMENT_REMARK) {
        this.SHIPMENT_REMARK = SHIPMENT_REMARK;
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
