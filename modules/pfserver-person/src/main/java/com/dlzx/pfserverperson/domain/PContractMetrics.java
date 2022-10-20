package com.dlzx.pfserverperson.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 合作方管理-合同评价指标对象 p_contract_metrics
 * 
 * @author dlzx
 * @date 2020-12-09
 */
public class PContractMetrics extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 类型ID 关联p_contract_metrics_type表 */
    @Excel(name = "类型ID 关联p_contract_metrics_type表")
    private String typeId;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String typeName;

    /** 评价部分 1技术部分 2商业部分 */
    @Excel(name = "评价部分 1技术部分 2商业部分")
    private Long type;

    /** 维度 */
    @Excel(name = "维度")
    private String dimension;

    /** 评价内容 */
    @Excel(name = "评价内容")
    private String content;

    /** 评价方法 */
    @Excel(name = "评价方法")
    private String method;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag = 1L;

    /** 评价部分翻译 */
    private String typeEnum;

    public String getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(String typeEnum) {
        this.typeEnum = typeEnum;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setTypeId(String typeId) 
    {
        this.typeId = typeId;
    }

    public String getTypeId() 
    {
        return typeId;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setDimension(String dimension) 
    {
        this.dimension = dimension;
    }

    public String getDimension() 
    {
        return dimension;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setMethod(String method) 
    {
        this.method = method;
    }

    public String getMethod() 
    {
        return method;
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
            .append("typeId", getTypeId())
            .append("typeName", getTypeName())
            .append("type", getType())
            .append("dimension", getDimension())
            .append("content", getContent())
            .append("method", getMethod())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
