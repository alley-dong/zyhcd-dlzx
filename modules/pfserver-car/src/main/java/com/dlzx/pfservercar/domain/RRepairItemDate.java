package com.dlzx.pfservercar.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 维修保养-维修保养单-维修保养项数据对象 r_repair_item_date
 * 
 * @author yangluhe
 * @date 2020-12-07
 */
public class RRepairItemDate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 维修保养单ID-关联r_repair表 */
    @Excel(name = "维修保养单ID-关联r_repair表")
    private String repairId;

    /** 维修保养项ID 关联r_repair_item表 */
    @Excel(name = "维修保养项ID 关联r_repair_item表")
    private String itemId;

    /** 维修保养项名称 */
    @Excel(name = "维修保养项名称")
    private String itemName;

    /** 维保原因 */
    @Excel(name = "维保原因")
    private String remarks;

    /** 附件地址-json数组 */
    @Excel(name = "附件地址-json数组")
    private String files;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date finishTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Long getStatistics() {
        return statistics;
    }

    public void setStatistics(Long statistics) {
        this.statistics = statistics;
    }

    private Long statistics;

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }

    private List<String> fileList;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setRepairId(String repairId) 
    {
        this.repairId = repairId;
    }

    public String getRepairId() 
    {
        return repairId;
    }
    public void setItemId(String itemId) 
    {
        this.itemId = itemId;
    }

    public String getItemId() 
    {
        return itemId;
    }
    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public String getItemName() 
    {
        return itemName;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setFiles(String files) 
    {
        this.files = files;
    }

    public String getFiles() 
    {
        return files;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("repairId", getRepairId())
            .append("itemId", getItemId())
            .append("itemName", getItemName())
            .append("remarks", getRemarks())
            .append("files", getFiles())
                .append("fileList",getFileList())
            .toString();
    }
}
