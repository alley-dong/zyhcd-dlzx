package com.dlzx.pfserverperson.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 驾驶员-交通事故经历对象 d_driver_accident_record
 * 
 * @author yangluhe
 * @date 2020-11-19
 */
public class DDriverAccidentRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 司机ID */
    @Excel(name = "司机ID")
    private String driverId;

    /** 事故时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "事故时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date accidentTime;

    /** 事故地点 */
    @Excel(name = "事故地点")
    private String place;

    /** 事故说明 */
    @Excel(name = "事故说明")
    private String remarks;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;

/*    *//** 创建人 *//*
    @Excel(name = "创建人")
    private String createUser;*/

    /** 更新人 */
/*    @Excel(name = "更新人")
    private String updateUser;*/

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setDriverId(String driverId) 
    {
        this.driverId = driverId;
    }

    public String getDriverId() 
    {
        return driverId;
    }
    public void setAccidentTime(Date accidentTime) 
    {
        this.accidentTime = accidentTime;
    }

    public Date getAccidentTime() 
    {
        return accidentTime;
    }
    public void setPlace(String place) 
    {
        this.place = place;
    }

    public String getPlace() 
    {
        return place;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setDeleteFlag(Long deleteFlag) 
    {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag() 
    {
        return deleteFlag;
    }
    /*public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    public String getCreateUser() 
    {
        return createUser;
    }
    public void setUpdateUser(String updateUser) 
    {
        this.updateUser = updateUser;
    }

    public String getUpdateUser() 
    {
        return updateUser;
    }*/

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("driverId", getDriverId())
            .append("accidentTime", getAccidentTime())
            .append("place", getPlace())
            .append("remarks", getRemarks())
            .append("deleteFlag", getDeleteFlag())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
