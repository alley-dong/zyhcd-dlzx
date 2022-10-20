package com.dlzx.system.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 驾驶员-招聘计划对象 d_recruit_plan
 * 
 * @author yangluhe
 * @date 2020-11-16
 */
public class DRecruitPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /*审核人*/
    private String userId;

    /** 线路ID */
    @Excel(name = "线路ID")
    private String routeId;

    /** 线路名称 */

    @Excel(name = "线路名称")
    private String routeName;

    /** 招聘人数 */

    @Excel(name = "招聘人数")
    private Long number;

    /** 学历要求 */

    private String education;

    /** 年龄 */
    @Excel(name = "年龄")
    private String age;

    /** 驾驶要求 */

    @Excel(name = "驾驶要求")
    private String drivingRequirements;

    /** 入职时间 */

    @Excel(name = "入职时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date entryTime;

    /** 工资福利 */

    @Excel(name = "工资福利")
    private String wage;

    /** 需求原因 */

    private String reason;

    /** 工作职责 */

    private String duty;

    /** 审核状态 1待审核  2拒绝 3通过 */
    @Excel(name = "审核状态 1待审核  2拒绝 3通过")
    private Long status;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reviewTime;

    /** 审核人 */
    private String reviewUser;

    /** 审核备注 */
    private String reviewRemarks;

    /** 删除状态 1正常 2删除 */
    private Long deleteFlag;

    /** 创建人 */
    private String createUser;

    /** 更新人 */
    private Date updateUser;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setRouteId(String routeId) 
    {
        this.routeId = routeId;
    }

    public String getRouteId() 
    {
        return routeId;
    }
    public void setRouteName(String routeName) 
    {
        this.routeName = routeName;
    }

    @NotBlank(message = "线路不能为空")
    public String getRouteName() 
    {
        return routeName;
    }
    public void setNumber(Long number)
    {
        this.number = number;
    }


    @Min(0)
    @NotNull(message = "招聘人数不能为空")
    public Long getNumber()
    {
        return number;
    }
    public void setEducation(String education) 
    {
        this.education = education;
    }

    @NotNull(message = "学历要求不能为空")
    public String getEducation() 
    {
        return education;
    }
    public void setAge(String age)
    {
        this.age = age;
    }


    @NotNull(message = "年龄不能为空")
    public String getAge()
    {
        return age;
    }
    public void setDrivingRequirements(String drivingRequirements)
    {
        this.drivingRequirements = drivingRequirements;
    }

    @NotBlank(message = "驾驶要求不能为空")
    public String getDrivingRequirements()
    {
        return drivingRequirements;
    }
    public void setEntryTime(Date entryTime) 
    {
        this.entryTime = entryTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "入职时间不能为空")
    public Date getEntryTime() 
    {
        return entryTime;
    }
    public void setWage(String wage) 
    {
        this.wage = wage;
    }

    @NotBlank(message = "工资待遇不能为空")
    public String getWage() 
    {
        return wage;
    }
    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    @NotBlank(message = "需求原因不能为空")
    public String getReason() 
    {
        return reason;
    }
    public void setDuty(String duty) 
    {
        this.duty = duty;
    }

    @NotBlank(message = "工作职责不能为空")
    public String getDuty() 
    {
        return duty;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setReviewTime(Date reviewTime) 
    {
        this.reviewTime = reviewTime;
    }

    public Date getReviewTime() 
    {
        return reviewTime;
    }
    public void setReviewUser(String reviewUser) 
    {
        this.reviewUser = reviewUser;
    }

    public String getReviewUser() 
    {
        return reviewUser;
    }
    public void setReviewRemarks(String reviewRemarks) 
    {
        this.reviewRemarks = reviewRemarks;
    }

    public String getReviewRemarks() 
    {
        return reviewRemarks;
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
    public void setUpdateUser(Date updateUser) 
    {
        this.updateUser = updateUser;
    }

    public Date getUpdateUser() 
    {
        return updateUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("routeId", getRouteId())
            .append("routeName", getRouteName())
            .append("number", getNumber())
            .append("education", getEducation())
            .append("age", getAge())
            .append("drivingRequirements", getDrivingRequirements())
            .append("entryTime", getEntryTime())
            .append("wage", getWage())
            .append("reason", getReason())
            .append("duty", getDuty())
            .append("status", getStatus())
            .append("reviewTime", getReviewTime())
            .append("reviewUser", getReviewUser())
            .append("reviewRemarks", getReviewRemarks())
            .append("deleteFlag", getDeleteFlag())
            .append("createUser", getCreateUser())
            .append("createTime", getCreateTime())
            .append("updateUser", getUpdateUser())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
