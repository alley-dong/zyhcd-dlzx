package com.dlzx.system.domain;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 系统管理-审批配置对象 sys_examine_config
 *
 * @author dlzx
 * @date 2020-12-15
 */
public class SysExamineConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 审批业务-关联字典项表 */
    @Excel(name = "审批业务-关联字典项表")
    private Long type;

    /** 审批业务-关联字典项表翻译 */
    @Excel(name = "审批业务-关联字典项表")
    private String types;

    /** 审批级别 */
    @Excel(name = "审批级别")
    private Long level;

    /** 审批角色名称 */
    @Excel(name = "审批角色名称")
    private String roleName;

    /** 抄送角色名称 */
    @Excel(name = "抄送角色名称")
    private String ccRoleName;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag=1L;


    /** 审批业务-关联字典项表 */
    @Excel(name = "审批业务翻译")
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
    public void setType(Long type)
    {
        this.type = type;
    }

    public Long getType()
    {
        return type;
    }
    public void setLevel(Long level)
    {
        this.level = level;
    }

    public Long getLevel()
    {
        return level;
    }
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getRoleName()
    {
        return roleName;
    }
    public void setCcRoleName(String ccRoleName)
    {
        this.ccRoleName = ccRoleName;
    }

    public String getCcRoleName()
    {
        return ccRoleName;
    }
    public void setDeleteFlag(Long deleteFlag)
    {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag()
    {
        return deleteFlag;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("type", getType())
                .append("level", getLevel())
                .append("roleName", getRoleName())
                .append("ccRoleName", getCcRoleName())
                .append("deleteFlag", getDeleteFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}