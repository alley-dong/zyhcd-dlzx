package com.dlzx.pfservercar.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 维修保养-维修站对象 r_garage
 * 
 * @author yangluhe
 * @date 2020-12-01
 */
public class RGarageCar extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 电话 */
    @Excel(name = "电话")
    private String tel;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 是否定点 1是 2否 */
    @Excel(name = "是否定点 1是 2否")
    private Long isFixed;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;

/*    *//** 创建人 *//*
    @Excel(name = "创建人")
    private String createUser;*/

   /* *//** 更新人 *//*
    @Excel(name = "更新人")
    private String updateUser;
*/
    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    @NotBlank(message = "维修服务站名称不能为空")
    public String getName() 
    {
        return name;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setIsFixed(Long isFixed) 
    {
        this.isFixed = isFixed;
    }

    @NotNull(message = "定点维修站不能为空")
    public Long getIsFixed() 
    {
        return isFixed;
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
            .append("name", getName())
            .append("tel", getTel())
            .append("address", getAddress())
            .append("isFixed", getIsFixed())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
