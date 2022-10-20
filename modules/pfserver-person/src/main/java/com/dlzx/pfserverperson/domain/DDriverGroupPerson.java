package com.dlzx.pfserverperson.domain;

import com.dlzx.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 驾驶员-驾驶员群组对象 d_driver_group_person
 *
 * @author hyb
 * @date 2020-11-23
 */
public class DDriverGroupPerson extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String id;
    private String groupId;
    //群组名称
    private String name;
    //勾选驾驶员id
    private  List<DDriverGroupUser> driver;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public String getGroupId()
    {
        return groupId;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    @NotBlank(message = "群组名称不能为空")
    public String getName()
    {
        return name;
    }
    public void setDriver(List<DDriverGroupUser> driver){ this.driver =driver;}

    public List<DDriverGroupUser> getDriver() {return driver;}

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("groupId", getGroupId())
                .append("name", getName())
                .append("driver", getDriver())
                .toString();
    }
}
