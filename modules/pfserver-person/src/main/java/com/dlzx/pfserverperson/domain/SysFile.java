package com.dlzx.pfserverperson.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 文件对象 sys_file
 * 
 * @author dlzx
 * @date 2020-12-08
 */
public class SysFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 类型 */
    @Excel(name = "类型(1,事故照片2,事故其他 3,考勤附件 4,客户合同附件 5供应商合同附件 6供应商评价附件 7交通违章图片 8违章处理图片 9驾驶员档案附件 10车辆档案附件 11保险保单照片 12.任务装货照片 13任务卸货照片 14任务异常情况照片)")
    private Long type;

    /** 关联ID */
    @Excel(name = "关联ID")
    private String relationId;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private Long fileType;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件地址 */
    @Excel(name = "文件地址")
    private String filePath;

    /** 后缀名 */
    @Excel(name = "后缀名")
    private String fileSuffix;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag = 1L;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createUser;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updateUser;

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
    public void setRelationId(String relationId) 
    {
        this.relationId = relationId;
    }

    public String getRelationId() 
    {
        return relationId;
    }
    public void setFileType(Long fileType) 
    {
        this.fileType = fileType;
    }

    public Long getFileType() 
    {
        return fileType;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setFileSuffix(String fileSuffix) 
    {
        this.fileSuffix = fileSuffix;
    }

    public String getFileSuffix() 
    {
        return fileSuffix;
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
    public void setUpdateUser(String updateUser) 
    {
        this.updateUser = updateUser;
    }

    public String getUpdateUser() 
    {
        return updateUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("relationId", getRelationId())
            .append("fileType", getFileType())
            .append("fileName", getFileName())
            .append("filePath", getFilePath())
            .append("fileSuffix", getFileSuffix())
            .append("deleteFlag", getDeleteFlag())
            .append("createUser", getCreateUser())
            .append("createTime", getCreateTime())
            .append("updateUser", getUpdateUser())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
