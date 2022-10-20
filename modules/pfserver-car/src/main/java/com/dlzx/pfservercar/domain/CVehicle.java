package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import com.dlzx.common.core.web.domain.TMSCarDto;
import com.dlzx.pfservercar.domain.model.OTMDriverModel;
import com.dlzx.pfservercar.domain.model.OTMVehicleModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 车辆档案对象 c_vehicle
 * 
 * @author yangluhe
 * @date 2020-11-24
 */
public class CVehicle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private String id;

    /** 档案编号 */
    @Excel(name = "档案编号")
    private String identifier;

    /**车辆类别-名称*/
    @Excel(name = "车辆类别")
    private String vehicleTypeName;

    /**车辆型号-名称*/
    @Excel(name = "车辆类型")
    private String vehicleModelName;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String licensePlate;

    /** 品牌型号 */
    @Excel(name = "品牌型号")
    private String brand;

    /** 驱动形式
     */
    private Long driveForm;

    public String getDriveFormName() {
        return driveFormName;
    }

    public void setDriveFormName(String driveFormName) {
        this.driveFormName = driveFormName;
    }

    @Excel(name = "驱动形式")
    private String driveFormName;

    /** 马力
     */
    @Excel(name = "马力")
    private String horsepower;

    /** 发动机号
     */
    @Excel(name = "发动机号")
    private String engineNumber;

    /** 车架号
     */
    @Excel(name = "车架号")
    private String frameNumber;

    /** 变速箱
     */
    @Excel(name = "变速箱")
    private String gearbox;

    /** 发动机
     */
    @Excel(name = "发动机")
    private String engine;

    /** 燃油种类
     */
    @Excel(name = "燃料种类")
    private String fuelType;

    /** 后桥
     */
    @Excel(name = "后桥")
    private String rearAxle;

    /** 制造厂商
     */
    @Excel(name = "制造厂商")
    private String manufacturer;

    /** 主油箱容积
     */
    @Excel(name = "主油箱容积")
    private String mainTankVolume;

    /** 副油箱容积
     */
    @Excel(name = "副油箱容积")
    private String viceTankVolume;

    /** 车总质量
     */@Excel(name = "车总质量")
    private String totalMass;

    /** 核定载质量
     */
    @Excel(name = "核定载质量")
    private String approvedLoad;

    /** 整备质量
     */
    @Excel(name = "整备质量")
    private String curbWeight;

    /** 购车服务站
     */
    @Excel(name = "购车服务站")
    private String distributor;

    /** 购车日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "购车日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date purchaseDate;

    /** 购车金额
     */
    @Excel(name = "购车金额")
    private String purchasePrice;

    /** 购置税
     */
    @Excel(name = "购置税")
    private String purchaseTax;

    /** 车辆原值
     */
    @Excel(name = "车辆原值")
    private String originalPrice;

    /** GPS费用
     */
    @Excel(name = "GPS费用")
    private String gpsCost;


    /** 转固费用*/
    private String turnCost;

    /** 检车落籍费用
     */
    @Excel(name = "检车落籍费用")
    private String inspectCost;

    /** 强制报废日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "强制报废日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date scrapDate;

    /** 行驶证注册日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "行驶证注册日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registerDate;

    /** 是否需要定检 1是 2否
     */
    @Excel(name = "是否需要定检 1是 2否 ")
    private Long isInspect;

    /** 检车日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检车日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inspectDate;

    /** 下次检车日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下次检车日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nextInspectDate;

    /** 卡号/流水号
     */
    @Excel(name = "卡号/流水号")
    private String cardNumber;

    /** 长
     */
    @Excel(name = "长")
    private String lasting;

    /** 宽
     */
    @Excel(name = "宽")
    private String wide;

    /** 高
     */
    @Excel(name = "高")
    private String high;

    //车辆类别 关联sys_dictionary表
    private String vehicleType;

    @Excel(name = "颜色")
    private String colorName;

    //禁启用 1启用 2禁用
    private Long disable;

    public Long getDisable() {
        return disable;
    }

    public void setDisable(Long disable) {
        this.disable = disable;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    /**保险办理日期*/
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date insuranceHandleTime;

    public Date getInsuranceHandleTime() {
        return insuranceHandleTime;
    }

    public void setInsuranceHandleTime(Date insuranceHandleTime) {
        this.insuranceHandleTime = insuranceHandleTime;
    }

    //车辆型号 关联sys_dictionary表
    private String vehicleModel;

//    @NotBlank(message = "车辆卡号不能为空")
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @NotBlank(message = "车辆类型代码不能为空")
    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    @NotBlank(message = "车辆类型不能为空")
    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    @NotBlank(message = "车辆型号不能为空")
    public String getVehicleModelName() {
        return vehicleModelName;
    }

    public void setVehicleModelName(String vehicleModelName) {
        this.vehicleModelName = vehicleModelName;
    }

    /** 删除状态 0删除 1未删除
 */
    private Long deleteFlag = 1L;

    /** 创建人
 */
    private String createUser;

    public List<String> getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(List<String> fileUrl) {
        this.fileUrl = fileUrl;
    }

    private List<String> fileUrl;

    /**到期标识*/
    private Long outTimeFlag;

    /**车辆状态*/
    private Long vehicleStatus;

    /***驱动形式*/
    private String driveFormLable;

    /**车辆颜色*/
    @Excel(name = "车辆颜色")
    private String color;

    /**车长*/
    private String conductor;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date insuranceStartDate;

    public Date getsTime() {
        return sTime;
    }

    public void setsTime(Date sTime) {
        this.sTime = sTime;
    }

    public Date getfTime() {
        return fTime;
    }

    public void setfTime(Date fTime) {
        this.fTime = fTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")

    private Date insuranceEndDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date sTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fTime;

    public Date getInsuranceStartDate() {
        return insuranceStartDate;
    }

    public void setInsuranceStartDate(Date insuranceStartDate) {
        this.insuranceStartDate = insuranceStartDate;
    }

    public Date getInsuranceEndDate() {
        return insuranceEndDate;
    }

    public void setInsuranceEndDate(Date insuranceEndDate) {
        this.insuranceEndDate = insuranceEndDate;
    }

    @NotBlank(message = "车辆颜色代码不能为空")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    @NotBlank(message = "道路运输许可证号不能为空")
    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    /**道路运输许可证*/
    @Excel(name = "道路运输许可证")
    private String permit;

    public String getDriveFormLable() {
        return driveFormLable;
    }

    public void setDriveFormLable(String driveFormLable) {
        this.driveFormLable = driveFormLable;
    }

    public String getFuelTypeLable() {
        return fuelTypeLable;
    }

    public void setFuelTypeLable(String fuelTypeLable) {
        this.fuelTypeLable = fuelTypeLable;
    }

    /**燃油类型*/
    private String fuelTypeLable;


    public Long getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(Long vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public Long getOutTimeFlag() {
        return outTimeFlag;
    }

    public void setOutTimeFlag(Long outTimeFlag) {
        this.outTimeFlag = outTimeFlag;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setIdentifier(String identifier) 
    {
        this.identifier = identifier;
    }

    public String getIdentifier() 
    {
        return identifier;
    }
    public void setLicensePlate(String licensePlate) 
    {
        this.licensePlate = licensePlate;
    }

    @NotBlank(message = "长不能为空")
    public String getLasting() {
        return lasting;
    }

    public void setLasting(String lasting) {
        this.lasting = lasting;
    }

    @NotBlank(message = "宽不能为空")
    public String getWide() {
        return wide;
    }

    public void setWide(String wide) {
        this.wide = wide;
    }

    @NotBlank(message = "高不能为空")
    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    @NotBlank(message = "车牌号不能为空")
    public String getLicensePlate() 
    {
        return licensePlate;
    }
    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }
    public void setDriveForm(Long driveForm)
    {
        this.driveForm = driveForm;
    }

    public Long getDriveForm()
    {
        return driveForm;
    }
    public void setHorsepower(String horsepower) 
    {
        this.horsepower = horsepower;
    }

    public String getHorsepower() 
    {
        return horsepower;
    }
    public void setEngineNumber(String engineNumber) 
    {
        this.engineNumber = engineNumber;
    }

    public String getEngineNumber() 
    {
        return engineNumber;
    }
    public void setFrameNumber(String frameNumber) 
    {
        this.frameNumber = frameNumber;
    }

    @NotBlank(message = "车架号不能为空")
    public String getFrameNumber() 
    {
        return frameNumber;
    }
    public void setGearbox(String gearbox) 
    {
        this.gearbox = gearbox;
    }

    public String getGearbox() 
    {
        return gearbox;
    }
    public void setEngine(String engine) 
    {
        this.engine = engine;
    }

    public String getEngine() 
    {
        return engine;
    }
    public void setFuelType(String fuelType)
    {
        this.fuelType = fuelType;
    }

    @NotBlank(message = "车辆能源类型不能为空")
    public String getFuelType()
    {
        return fuelType;
    }
    public void setRearAxle(String rearAxle) 
    {
        this.rearAxle = rearAxle;
    }

    public String getRearAxle() 
    {
        return rearAxle;
    }
    public void setManufacturer(String manufacturer) 
    {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() 
    {
        return manufacturer;
    }
    public void setMainTankVolume(String mainTankVolume) 
    {
        this.mainTankVolume = mainTankVolume;
    }

    public String getMainTankVolume() 
    {
        return mainTankVolume;
    }
    public void setViceTankVolume(String viceTankVolume) 
    {
        this.viceTankVolume = viceTankVolume;
    }

    public String getViceTankVolume() 
    {
        return viceTankVolume;
    }
    public void setTotalMass(String totalMass) 
    {
        this.totalMass = totalMass;
    }


    @NotBlank(message = "车辆吨位不能为空")
    public String getTotalMass() 
    {
        return totalMass;
    }
    public void setApprovedLoad(String approvedLoad) 
    {
        this.approvedLoad = approvedLoad;
    }

    @NotBlank(message = "车辆核载重量不能为空")
    public String getApprovedLoad() 
    {
        return approvedLoad;
    }
    public void setCurbWeight(String curbWeight) 
    {
        this.curbWeight = curbWeight;
    }

    public String getCurbWeight() 
    {
        return curbWeight;
    }
    public void setDistributor(String distributor) 
    {
        this.distributor = distributor;
    }

    public String getDistributor() 
    {
        return distributor;
    }
    public void setPurchaseDate(Date purchaseDate) 
    {
        this.purchaseDate = purchaseDate;
    }

    public Date getPurchaseDate() 
    {
        return purchaseDate;
    }
    public void setPurchasePrice(String purchasePrice) 
    {
        this.purchasePrice = purchasePrice;
    }

    public String getPurchasePrice() 
    {
        return purchasePrice;
    }
    public void setPurchaseTax(String purchaseTax) 
    {
        this.purchaseTax = purchaseTax;
    }

    public String getPurchaseTax() 
    {
        return purchaseTax;
    }
    public void setOriginalPrice(String originalPrice) 
    {
        this.originalPrice = originalPrice;
    }

    public String getOriginalPrice() 
    {
        return originalPrice;
    }
    public void setGpsCost(String gpsCost) 
    {
        this.gpsCost = gpsCost;
    }

    public String getGpsCost() 
    {
        return gpsCost;
    }
    public void setInspectCost(String inspectCost) 
    {
        this.inspectCost = inspectCost;
    }

    public String getInspectCost() 
    {
        return inspectCost;
    }
    public void setScrapDate(Date scrapDate) 
    {
        this.scrapDate = scrapDate;
    }

    public Date getScrapDate() 
    {
        return scrapDate;
    }
    public void setRegisterDate(Date registerDate) 
    {
        this.registerDate = registerDate;
    }

    public Date getRegisterDate() 
    {
        return registerDate;
    }
    public void setIsInspect(Long isInspect) 
    {
        this.isInspect = isInspect;
    }

    @NotNull(message = "请选择是否需要定检")
    public Long getIsInspect() 
    {
        return isInspect;
    }
    public void setInspectDate(Date inspectDate) 
    {
        this.inspectDate = inspectDate;
    }

    public Date getInspectDate() 
    {
        return inspectDate;
    }
    public void setNextInspectDate(Date nextInspectDate) 
    {
        this.nextInspectDate = nextInspectDate;
    }

    public Date getNextInspectDate() 
    {
        return nextInspectDate;
    }
    public void setDeleteFlag(Long deleteFlag) 
    {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag() 
    {
        return deleteFlag;
    }
    public void setCreateUser(String createUser) 
    {
        this.createUser = createUser;
    }

    public String getCreateUser() 
    {
        return createUser;
    }

    public String getTurnCost() {
        return turnCost;
    }

    public void setTurnCost(String turnCost) {
        this.turnCost = turnCost;
    }

    @Override
    public String toString() {
        return "CVehicle{" +
                "id='" + id + '\'' +
                ", identifier='" + identifier + '\'' +
                ", vehicleTypeName='" + vehicleTypeName + '\'' +
                ", vehicleModelName='" + vehicleModelName + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", brand='" + brand + '\'' +
                ", driveForm=" + driveForm +
                ", horsepower='" + horsepower + '\'' +
                ", engineNumber='" + engineNumber + '\'' +
                ", frameNumber='" + frameNumber + '\'' +
                ", gearbox='" + gearbox + '\'' +
                ", engine='" + engine + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", rearAxle='" + rearAxle + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", mainTankVolume='" + mainTankVolume + '\'' +
                ", viceTankVolume='" + viceTankVolume + '\'' +
                ", totalMass='" + totalMass + '\'' +
                ", approvedLoad='" + approvedLoad + '\'' +
                ", curbWeight='" + curbWeight + '\'' +
                ", distributor='" + distributor + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", purchasePrice='" + purchasePrice + '\'' +
                ", purchaseTax='" + purchaseTax + '\'' +
                ", originalPrice='" + originalPrice + '\'' +
                ", gpsCost='" + gpsCost + '\'' +
                ", inspectCost='" + inspectCost + '\'' +
                ", scrapDate=" + scrapDate +
                ", registerDate=" + registerDate +
                ", isInspect=" + isInspect +
                ", inspectDate=" + inspectDate +
                ", nextInspectDate=" + nextInspectDate +
                ", vehicleType='" + vehicleType + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", createUser='" + createUser + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", outTimeFlag=" + outTimeFlag +
                ", vehicleStatus=" + vehicleStatus +
                ", driveFormLable='" + driveFormLable + '\'' +
                ", color='" + color + '\'' +
                ", conductor='" + conductor + '\'' +
                ", permit='" + permit + '\'' +
                ", fuelTypeLable='" + fuelTypeLable + '\'' +
                '}';
    }

    public boolean equals() {
        String str = new StringBuilder()
                .append(getId()!=null?getId():"")
                .append(getIdentifier()!=null?getIdentifier():"")
                .append(getLicensePlate()!=null?getLicensePlate():"")
                .append(getBrand()!=null?getBrand():"")
                .append(getDriveForm()!=null?getDriveForm():"")
                .append(getHorsepower()!=null?getHorsepower():"")
                .append(getEngineNumber()!=null?getEngineNumber():"")
                .append(getFrameNumber()!=null?getFrameNumber():"")
                .append(getGearbox()!=null?getGearbox():"")
                .append(getEngine()!=null?getEngine():"")
                .append(getFuelType()!=null?getFuelType():"")
                .append(getRearAxle()!=null?getRearAxle():"")
                .append(getManufacturer()!=null?getManufacturer():"")
                .append(getMainTankVolume()!=null?getMainTankVolume():"")
                .append(getViceTankVolume()!=null?getViceTankVolume():"")
                .append(getTotalMass()!=null?getTotalMass():"")
                .append(getApprovedLoad()!=null?getApprovedLoad():"")
                .append(getCurbWeight()!=null?getCurbWeight():"")
                .append(getDistributor()!=null?getDistributor():"")
                .append(getPurchaseDate()!=null?getPurchaseDate():"")
                .append(getPurchasePrice()!=null?getPurchasePrice():"")
                .append(getPurchaseTax()!=null?getPurchaseTax():"")
                .append(getOriginalPrice()!=null?getOriginalPrice():"")
                .append(getGpsCost()!=null?getGpsCost():"")
                .append(getInspectCost()!=null?getInspectCost():"")
                .append(getScrapDate()!=null?getScrapDate():"")
                .append(getRegisterDate()!=null?getRegisterDate():"")
                .append(getIsInspect()!=null?getIsInspect():"")
                .append(getInspectDate()!=null?getInspectDate():"")
                .append(getNextInspectDate()!=null?getNextInspectDate():"")
                .append(getDeleteFlag()!=null?getDeleteFlag():"")
                .append(getCreateBy()!=null?getCreateBy():"")
                .append(getCreateTime()!=null?getCreateTime():"")
                .append(getUpdateBy()!=null?getUpdateBy():"")
                .append(getUpdateTime()!=null?getUpdateTime():"")
                .append(getVehicleTypeName()!=null?getVehicleTypeName():"")
                .append(getVehicleModelName()!=null?getVehicleModelName():"")
                .append(getVehicleType()!=null?getVehicleType():"")
                .append(getVehicleModel()!=null?getVehicleModel():"")
                .append(getVehicleStatus()!=null?getVehicleStatus():"")
                .append(getColor()!=null?getColor():"")
                .append(getConductor()!=null?getConductor():"")
                .append(getPermit()!=null?getPermit():"")
                .toString();
        boolean flag = ("".equals(str.trim())||str.trim().length()==0);
//        System.out.println("数据长度:"+str+""+flag);
        return flag;
    }


    /**
     * @describe Bean赋值
     * @author DongCL
     * @date 2021-01-06 14:26
     * @return OTMVehicleModel
     */
    public OTMVehicleModel convert() {
        return new Function<CVehicle, OTMVehicleModel>() {
            @Override
            public OTMVehicleModel apply(CVehicle cVehicle) {
                //属性拷贝
                OTMVehicleModel otmVehicleModel = new OTMVehicleModel();
                otmVehicleModel.setTEMPLATE_ID(cVehicle.getId());
                //车牌号
                otmVehicleModel.setCAR_NUMBER(cVehicle.getLicensePlate());
                //承运商
                otmVehicleModel.setSERVPROV_NAME("专业运营部");
                //车辆类别
                otmVehicleModel.setCLASS_OF_VEHICLE(cVehicle.getVehicleTypeName());
                //车辆类型代码
                otmVehicleModel.setTRUCK_TYPE_CODE(cVehicle.getVehicleModel());
                //货品属性
                otmVehicleModel.setITEM_ATTRIBUTE("普货");
                //车辆颜色代码
                otmVehicleModel.setVEHICLE_PLATE_COLOR_CODE(cVehicle.getColor());
                //车辆能源类型
                otmVehicleModel.setVEHICLE_ENERGY_TYPE(cVehicle.getFuelType());
                //吨位（千克）
                otmVehicleModel.setGROSS_MASS(cVehicle.getTotalMass());
                //道路运输许可证号
                otmVehicleModel.setROAD_TRANSPORT_CERT_NUM(cVehicle.getPermit());
                //是否生效（Y/N）
                otmVehicleModel.setENABLED_FLAG(cVehicle.getDeleteFlag()==1?"Y":"N");
                //车型
                otmVehicleModel.setEQUIPMENT_GROUP(cVehicle.getVehicleModelName());
                //长（米）
                otmVehicleModel.setLENGTH(cVehicle.getLasting());
                //宽（米）
                otmVehicleModel.setWIDTH(cVehicle.getWide());
                //高（米）
                otmVehicleModel.setHEIGHT(cVehicle.getHigh());
                //核载重量（千克）
                otmVehicleModel.setEFFECTIVE_WEIGHT(cVehicle.getApprovedLoad());
                return otmVehicleModel;
            }
        }.apply(this);
    }


 /**
     * @describe Bean赋值
     * @author DongCL
     * @date 2021-01-06 14:26
     * @return OTMVehicleModel
     */
    public TMSCarDto TMSConvert() {
        return new Function<CVehicle, TMSCarDto>() {
            @Override
            public TMSCarDto apply(CVehicle cVehicle) {
                //属性拷贝
                TMSCarDto otmVehicleModel = new TMSCarDto();
                //车牌号
                otmVehicleModel.setVehicleNumber(cVehicle.getLicensePlate());
                //承运商
                otmVehicleModel.setServprovName("专业运营部");
                //车辆类别
                otmVehicleModel.setClassOfVehicle(cVehicle.getVehicleTypeName());
                //车辆类型代码
                otmVehicleModel.setVehicleType(cVehicle.getVehicleModel());
                //货品属性
                otmVehicleModel.setItemAttribute("G");
                //车辆颜色代码
                otmVehicleModel.setVehiclePlateColorCode(cVehicle.getColor());
                //车辆能源类型
                otmVehicleModel.setVehicleEnergyType(cVehicle.getFuelType());
                //吨位（千克）
                otmVehicleModel.setGrossMass(cVehicle.getTotalMass());
                //道路运输许可证号
                otmVehicleModel.setRoadTransportCertificateNumber(cVehicle.getPermit());
                //是否生效（Y/N）
                otmVehicleModel.setEnabledFlag(cVehicle.getDeleteFlag()==1?"Y":"N");
                //车型
                otmVehicleModel.setEquipmentGroup("17米5");
                //长（米）
                otmVehicleModel.setLength(cVehicle.getLasting());
                //宽（米）
                otmVehicleModel.setWidth(cVehicle.getWide());
                //高（米）
                otmVehicleModel.setHeight(cVehicle.getHigh());
                //核载重量（千克）
                otmVehicleModel.setEffectiveWeight(cVehicle.getApprovedLoad());
                return otmVehicleModel;
            }
        }.apply(this);
    }


}
