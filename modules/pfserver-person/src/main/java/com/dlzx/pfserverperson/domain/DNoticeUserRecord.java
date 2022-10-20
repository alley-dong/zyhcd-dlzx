package com.dlzx.pfserverperson.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 驾驶员-公告人员记录对象 d_notice_user_record
 * 
 * @author dlzx
 * @date 2020-11-24
 */
public class DNoticeUserRecord extends BaseEntity
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

    /** 状态 1未读 2已读 */
    @Excel(name = "状态 1未读 2已读")
    private Long status;

    /** 姓名 */
    @Excel(name = "姓名")
    private String driverName;

    /** 性别 */
    @Excel(name = "身份证号")
    private String idCard;

    /** 联系电话 */
    @Excel(name = "联系电话")
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
