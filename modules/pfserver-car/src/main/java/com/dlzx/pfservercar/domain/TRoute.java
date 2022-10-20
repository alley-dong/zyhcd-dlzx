package com.dlzx.pfservercar.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 任务管理-线路对象 t_route
 * 
 * @author dlzx
 * @date 2020-12-01
 */
public class TRoute extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 线路类型 1长途 2短途 */
    @Excel(name = "线路类型 1长途 2短途")
    private Long type;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 最少点检次数 */
    @Excel(name = "最少点检次数")
    private Long minInspect;

    /** 始发地 */
    @Excel(name = "始发地")
    private String origin;

    /** 目的地 */
    @Excel(name = "目的地")
    private String destination;

    /*** 里程 */
    @Excel(name = "里程")
    private String mileage;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag = 1L;

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setMinInspect(Long minInspect) 
    {
        this.minInspect = minInspect;
    }

    public Long getMinInspect() 
    {
        return minInspect;
    }
    public void setOrigin(String origin) 
    {
        this.origin = origin;
    }

    public String getOrigin() 
    {
        return origin;
    }
    public void setDestination(String destination) 
    {
        this.destination = destination;
    }

    public String getDestination() 
    {
        return destination;
    }
    public void setDeleteFlag(Long deleteFlag) 
    {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag() 
    {
        return deleteFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("name", getName())
            .append("minInspect", getMinInspect())
            .append("origin", getOrigin())
            .append("destination", getDestination())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
