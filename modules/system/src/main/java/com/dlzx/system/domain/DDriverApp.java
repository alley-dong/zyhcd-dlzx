package com.dlzx.system.domain;

/**
 * @author ylh
 * @program:dlzx
 * @description：驾驶员DTO
 * @create:2020-12-15 11-41
 */
public class DDriverApp {

    /**主键id*/
    private String id;

    /**驾驶员姓名*/
    private String name;

    /***/
    private String contact;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
