package com.dlzx.system.mapper;

import java.util.List;
import com.dlzx.system.domain.AdministratorMsg;

/**
 * 管理员-管理员消息Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-14
 */
public interface AdministratorMsgMapper 
{
    /**
     * 查询管理员-管理员消息
     * 
     * @param id 管理员-管理员消息ID
     * @return 管理员-管理员消息
     */
    public AdministratorMsg selectAdministratorMsgById(String id);

    /**
     * 查询管理员-管理员消息列表
     * 
     * @param administratorMsg 管理员-管理员消息
     * @return 管理员-管理员消息集合
     */
    public List<AdministratorMsg> selectAdministratorMsgList(AdministratorMsg administratorMsg);

    /**
     * 新增管理员-管理员消息
     * 
     * @param administratorMsg 管理员-管理员消息
     * @return 结果
     */
    public int insertAdministratorMsg(AdministratorMsg administratorMsg);

    /**
     * 修改管理员-管理员消息
     * 
     * @param administratorMsg 管理员-管理员消息
     * @return 结果
     */
    public int updateAdministratorMsg(AdministratorMsg administratorMsg);

    /**
     * 删除管理员-管理员消息
     * 
     * @param id 管理员-管理员消息ID
     * @return 结果
     */
    public int deleteAdministratorMsgById(String id);

    /**
     * 批量删除管理员-管理员消息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAdministratorMsgByIds(String[] ids);

    /**
     * App 个人消息-消息列表接口
     *
     * @param userid 驾驶员-司机ID
     * @return 结果
     */
    List<AdministratorMsg> queryAdministratorMsgList(Long userid);

    /**
     * 批量插入
    * */
    int insertList(List<AdministratorMsg> administratorMsgList);

}
