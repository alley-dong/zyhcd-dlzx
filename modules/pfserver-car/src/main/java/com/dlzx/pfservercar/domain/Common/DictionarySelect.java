package com.dlzx.pfservercar.domain.Common;

/**
 * @author ylh
 * @program:dlzx
 * @description：公共二级联动
 * @create:2020-11-24 19-51
 */
public class DictionarySelect {

    private Long id;

    private Long type;

    private Long level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }
}
