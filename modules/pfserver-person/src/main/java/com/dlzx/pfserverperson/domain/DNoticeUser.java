package com.dlzx.pfserverperson.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import java.util.List;

/**
 * 驾驶员-公告人员关联对象 d_notice_user
 * 
 * @author dlzx
 * @date 2020-11-24
 */
public class DNoticeUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 公告ID */
    @Excel(name = "公告ID")
    private String noticeId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userId;

    private List<DNoticeUserRecord> dNoticeUserRecords;
    private Long status;
    private String driverName ;
    private String idCard;
    private String tel;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setNoticeId(String noticeId) 
    {
        this.noticeId = noticeId;
    }

    public String getNoticeId() 
    {
        return noticeId;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }

    public void setDNoticeUserRecords(List<DNoticeUserRecord> dNoticeUserRecords)
    {
        this.dNoticeUserRecords = dNoticeUserRecords;
    }

    public List<DNoticeUserRecord> getDNoticeUserRecords()
    {
        return dNoticeUserRecords;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("noticeId", getNoticeId())
            .append("userId", getUserId())
                .append("status", getStatus())
                .append("driverName", getDriverName())
                .append("idCard", getIdCard())
                .append("tel", getTel())
            .toString();
    }
}
