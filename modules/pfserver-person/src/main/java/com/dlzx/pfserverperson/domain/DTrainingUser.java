package com.dlzx.pfserverperson.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import java.util.Date;

/**
 * 驾驶员-培训人员关联对象 d_training_user
 * 
 * @author dlzx
 * @date 2020-11-26
 */
public class DTrainingUser extends BaseEntity
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

    private Long status;
    private String driverName ;
    private String idCard;
    private String tel;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date latelyTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long minDuration;
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

    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }

    public String getIdCard()
    {
        return idCard;
    }
    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public String getTel()
    {
        return tel;
    }
    public void setLatelyTime(Date latelyTime)
    {
        this.latelyTime = latelyTime;
    }

    public Date getLatelyTime()
    {
        return latelyTime;
    }
    public Long getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(Long minDuration) {
        this.minDuration = minDuration;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("trainingId", getTrainingId())
            .append("userId", getUserId())
                .append("status", getStatus())
                .append("driverName", getDriverName())
                .append("idCard", getIdCard())
                .append("tel", getTel())
                .append("latelyTime", getLatelyTime())
                .append("minDuration", getMinDuration())
            .toString();
    }


}
