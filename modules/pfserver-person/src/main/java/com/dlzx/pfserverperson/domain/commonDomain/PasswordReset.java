package com.dlzx.pfserverperson.domain.commonDomain;

/**
 * @author ylh
 * @program:dlzx
 * @description：重置密码和启禁用状态实体DTO
 * @create:2020-11-23 15-50
 */
public class PasswordReset {

    /**司机档案主键*/
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**新密码*/
    private String password;

    /**启禁用状态*/
    private Long disableStatus;

    public Long getDisableStatus() {
        return disableStatus;
    }

    public void setDisableStatus(Long disableStatus) {
        this.disableStatus = disableStatus;
    }
}
