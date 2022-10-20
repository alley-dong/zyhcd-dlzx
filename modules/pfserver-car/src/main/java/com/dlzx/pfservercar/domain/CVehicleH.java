package com.dlzx.pfservercar.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 车辆档案对象 c_vehicle
 * 
 * @author yangluhe
 * @date 2020-11-24
 */
public class CVehicleH  {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 车型id
     */
    private String vehicleModel;


    /**
     * 车架号
     */
    private String frameNumber;

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    /**
     * 车牌号
     */
    @Excel(name = "车牌号")
    private String licensePlate;

    /**车辆型号*/
    private String vehicleModelName;

    public String getVehicleModelName() {
        return vehicleModelName;
    }

    public void setVehicleModelName(String vehicleModelName) {
        this.vehicleModelName = vehicleModelName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @NotBlank(message = "车牌号不能为空")
    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())

                .append("licensePlate", getLicensePlate())
                .append("vehicleModelName",getVehicleModelName())
                .append("vehicleModel",getVehicleModel())


                .toString();
    }


    /*public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/

}
