package com.dlzx.pfservercar.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 驾驶员-考勤对象 d_vacation
 * 
 * @author dcl
 * @date 2020-11-30
 */
public class DVacation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 司机ID */
    @Excel(name = "司机ID")
    private String driverId;

    /** 司机姓名 */
    @Excel(name = "司机姓名")
    private String driverName;

    /** 请假类型(d_leave_type) */
    @Excel(name = "请假类型(d_leave_type)")
    private String type;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date vStartTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date vEndTime;

    /** 请假时长 */
    @Excel(name = "请假时长")
    private Long duration;

    /** 是否有薪 1是 2否 */
    @Excel(name = "是否有薪 1是 2否")
    private Long isSalary;

    /** 请假原因 */
    @Excel(name = "请假原因")
    private String remarks;

    /** 审核状态(1.待审核2.已通过3.已拒绝) */
    @Excel(name = "审核状态(1.待审核2.已通过3.已拒绝 4.已撤回)")
    private Long status;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reviewTime;

    /** 审核人 */
    @Excel(name = "审核人")
    private String reviewUser;

    /** 审核备注 */
    @Excel(name = "审核备注")
    private String reviewRemarks;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag = 1L;

    /** 请假天数（统计） */
    @Excel(name = "请假天数（统计）")
    private Long days;

    /** 驾驶员请假集合 */
    private List<DVacation> dVacationList;

    /** 请假类型翻译*/
    private String typeEnum;

    /** 审核人*/
    private String userid;

    /** 审核状态翻译*/
    private String statusEnum;

    /** 附件文件 url（多个逗号隔开） */
    private List<String> fileUrls;

    /** 附件文件(返回)*/
    private List<String> fileList;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }
    public List<String> getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(List<String> fileUrls) {
        this.fileUrls = fileUrls;
    }
    public String getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(String typeEnum) {
        this.typeEnum = typeEnum;
    }

    public String getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(String statusEnum) {
        this.statusEnum = statusEnum;
    }

    public List<DVacation> getdVacationList() {
        return dVacationList;
    }

    public void setdVacationList(List<DVacation> dVacationList) {
        this.dVacationList = dVacationList;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }

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
    public void setDriverName(String driverName)
    {
        this.driverName = driverName;
    }

    public String getDriverName()
    {
        return driverName;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setvStartTime(Date vStartTime)
    {
        this.vStartTime = vStartTime;
    }

    public Date getvStartTime()
    {
        return vStartTime;
    }
    public void setvEndTime(Date vEndTime)
    {
        this.vEndTime = vEndTime;
    }

    public Date getvEndTime()
    {
        return vEndTime;
    }
    public void setDuration(Long duration)
    {
        this.duration = duration;
    }

    public Long getDuration()
    {
        return duration;
    }
    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    public String getRemarks()
    {
        return remarks;
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

    public Long getIsSalary() {
        return isSalary;
    }

    public void setIsSalary(Long isSalary) {
        this.isSalary = isSalary;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("driverId", getDriverId())
                .append("driverName", getDriverName())
                .append("type", getType())
                .append("vStartTime", getvStartTime())
                .append("vEndTime", getvEndTime())
                .append("duration", getDuration())
                .append("remarks", getRemarks())
                .append("status", getStatus())
                .append("reviewTime", getReviewTime())
                .append("reviewUser", getReviewUser())
                .append("reviewRemarks", getReviewRemarks())
                .append("deleteFlag", getDeleteFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
