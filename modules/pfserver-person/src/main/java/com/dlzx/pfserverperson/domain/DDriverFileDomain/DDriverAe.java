package com.dlzx.pfserverperson.domain.DDriverFileDomain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author ylh
 * @program:dlzx
 * @description:驾驶员档案新增＆编辑
 * @create:2020-11-20 21-23
 */
public class DDriverAe {
    /**司机主键id*/
    private Long id;

    /**姓名*/
    private String name;

    /**性别*/
    private Long sex;

    /**联系电话*/
    private String contact;

    /**身份证号码*/
    private String idCard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank(message = "姓名不能为空")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "性别不能为空")
    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    @NotBlank(message = "联系电话不能为空")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @NotBlank(message = "身份证号不能为空")
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("concat", getContact())
                .append("idCard", getIdCard())
                .append("sex", getSex())
                .toString();
    }
}
