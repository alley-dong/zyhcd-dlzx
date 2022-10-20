package com.dlzx.pfserverperson.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 驾驶员-培训人员记录对象 d_training_user_record
 * 
 * @author dlzx
 * @date 2020-11-26
 */
public class DTrainingUserRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 培训ID */
    @Excel(name = "培训ID")
    private String trainingId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userId;

    /** 状态 1未完成 2已完成 3超期未完成 4超期已完成 */
    @Excel(name = "状态 1未完成 2已完成 3超期未完成 4超期已完成")
    private Long status;

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

    /** 学习完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "学习完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completeTime;

    /** 最近学习时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近学习时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date latelyTime;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setTrainingId(String trainingId) 
    {
        this.trainingId = trainingId;
    }

    public String getTrainingId() 
    {
        return trainingId;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
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
    public void setCompleteTime(Date completeTime) 
    {
        this.completeTime = completeTime;
    }

    public Date getCompleteTime() 
    {
        return completeTime;
    }
    public void setLatelyTime(Date latelyTime) 
    {
        this.latelyTime = latelyTime;
    }

    public Date getLatelyTime() 
    {
        return latelyTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("trainingId", getTrainingId())
            .append("userId", getUserId())
            .append("status", getStatus())
            .append("driverName", getDriverName())
            .append("driverSex", getDriverSex())
            .append("tel", getTel())
            .append("idCard", getIdCard())
            .append("completeTime", getCompleteTime())
            .append("latelyTime", getLatelyTime())
            .toString();
    }
}
