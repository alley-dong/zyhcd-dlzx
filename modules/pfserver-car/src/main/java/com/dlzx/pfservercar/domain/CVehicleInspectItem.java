package com.dlzx.pfservercar.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 车辆管理-车辆定检记录-检查项对象 c_vehicle_inspect_item
 * 
 * @author dlzx
 * @date 2020-12-08
 */
public class CVehicleInspectItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 记录ID 关联c_vehicle_inspect表 */
    @Excel(name = "记录ID 关联c_vehicle_inspect表")
    private String inspectId;

    /** 检查类型 1主车 2挂车 */
    @Excel(name = "检查类型 1主车 2挂车")
    private Long type;

    /** 检查项目类型 */
    @Excel(name = "检查项目类型")
    private Long itemType;

    /** 检查内容 */
    @Excel(name = "检查内容")
    private String name;

    /** 状态 1正常 2维修 */
    @Excel(name = "状态 1正常 2维修")
    private Long status;

    /** 附件地址 */
    @Excel(name = "附件地址")
    private String files;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setInspectId(String inspectId) 
    {
        this.inspectId = inspectId;
    }

    public String getInspectId() 
    {
        return inspectId;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setItemType(Long itemType) 
    {
        this.itemType = itemType;
    }

    public Long getItemType() 
    {
        return itemType;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setFiles(String files) 
    {
        this.files = files;
    }

    public String getFiles() 
    {
        return files;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("inspectId", getInspectId())
            .append("type", getType())
            .append("itemType", getItemType())
            .append("name", getName())
            .append("status", getStatus())
            .append("files", getFiles())
            .toString();
    }
}
