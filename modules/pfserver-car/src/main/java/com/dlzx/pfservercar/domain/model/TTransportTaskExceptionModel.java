package com.dlzx.pfservercar.domain.model;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.dlzx.pfservercar.domain.TTransportTaskException;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 任务管理-运输任务异常上报对象
 * 
 * @author dcl
 * @date 2020-12-18
 */
public class TTransportTaskExceptionModel extends TTransportTaskException
{
    private static final long serialVersionUID = 1L;


    /** 审批人 */
    private String userid;

    /** 附件文件 url（多个逗号隔开） */
    private List<String> fileUrls;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM")
    private Date createTimeModel;

    public Date getCreateTimeModel() {
        return createTimeModel;
    }

    public void setCreateTimeModel(Date createTimeModel) {
        this.createTimeModel = createTimeModel;
    }

    public List<String> getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(List<String> fileUrls) {
        this.fileUrls = fileUrls;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
