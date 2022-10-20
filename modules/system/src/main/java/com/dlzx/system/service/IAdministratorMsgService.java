package com.dlzx.system.service;

import java.util.List;
import com.dlzx.system.domain.AdministratorMsg;

/**
 * 管理员-管理员消息Service接口
 * 
 * @author dlzx
 * @date 2020-12-14
 */
public interface IAdministratorMsgService 
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
     * 批量删除管理员-管理员消息
     * 
     * @param ids 需要删除的管理员-管理员消息ID
     * @return 结果
     */
    public int deleteAdministratorMsgByIds(String[] ids);

    /**
     * 删除管理员-管理员消息信息
     * 
     * @param id 管理员-管理员消息ID
     * @return 结果
     */
    public int deleteAdministratorMsgById(String id);


    /**
     * App 个人消息-消息列表接口
     *
     * @param userid 驾驶员-司机ID
     * @return 结果
     */
    List<AdministratorMsg> queryAdministratorMsgList(Long userid);

    /*
    * 批量插入
    * */
    int insertList(List<AdministratorMsg> administratorMsgList);

    /**
     * 插入消息节点
     */
    int insertMessagePort(Long type,String relationId,String title,String content,String userId);
}
