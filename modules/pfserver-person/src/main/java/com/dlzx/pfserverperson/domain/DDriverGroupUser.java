package com.dlzx.pfserverperson.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 驾驶员-驾驶员群组成员对象 d_driver_group_user
 * 
 * @author dlzx
 * @date 2020-11-23
 */
public class DDriverGroupUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 分组ID */
    @Excel(name = "分组ID")
    private String groupId;

    /** 司机ID */
    @Excel(name = "司机ID")
    private String driverId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String driverName;

    /** 性别 */
    @Excel(name = "性别")
    private String driverSex;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String tel;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idCard;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setGroupId(String groupId) 
    {
        this.groupId = groupId;
    }

    public String getGroupId() 
    {
        return groupId;
    }
    public void setDriverId(String driverId) 
    {
        this.driverId = driverId;
    }

    public String getDriverId() 
    {
        return driverId;
    }
    public void setDriverName(String driverName) 
    {
        this.driverName = driverName;
    }

    public String getDriverName() 
    {
        return driverName;
    }
    public void setDriverSex(String driverSex) 
    {
        this.driverSex = driverSex;
    }

    public String getDriverSex() 
    {
        return driverSex;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("groupId", getGroupId())
            .append("driverId", getDriverId())
            .append("driverName", getDriverName())
            .append("driverSex", getDriverSex())
            .append("tel", getTel())
            .append("idCard", getIdCard())
            .toString();
    }
}
