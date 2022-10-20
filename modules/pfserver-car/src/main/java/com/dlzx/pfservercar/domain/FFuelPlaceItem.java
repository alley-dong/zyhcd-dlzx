package com.dlzx.pfservercar.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 燃油定点加油站对象 f_fuel_place_item
 * 
 * @author dlzx
 * @date 2020-12-01
 */
public class FFuelPlaceItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 定点ID */
    @Excel(name = "定点ID")
    private String placeId;

    /** 加油站名称 */
    @Excel(name = "加油站名称")
    private String name;

    /** 加油站地点 */
    @Excel(name = "加油站地点")
    private String address;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setPlaceId(String placeId) 
    {
        this.placeId = placeId;
    }

    public String getPlaceId() 
    {
        return placeId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("placeId", getPlaceId())
            .append("name", getName())
            .append("address", getAddress())
            .toString();
    }
}
