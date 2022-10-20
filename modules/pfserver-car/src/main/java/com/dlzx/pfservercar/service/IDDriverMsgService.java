package com.dlzx.pfservercar.service;

import java.util.List;
import com.dlzx.pfservercar.domain.DDriverMsg;

/**
 * 驾驶员-司机消息Service接口
 * 
 * @author dlzx
 * @date 2020-12-07
 */
public interface IDDriverMsgService 
{
    /**
     * 查询驾驶员-司机消息
     * 
     * @param id 驾驶员-司机消息ID
     * @return 驾驶员-司机消息
     */
    public DDriverMsg selectDDriverMsgById(String id);

    /**
     * 查询驾驶员-司机消息列表
     * 
     * @param dDriverMsg 驾驶员-司机消息
     * @return 驾驶员-司机消息集合
     */
    public List<DDriverMsg> selectDDriverMsgList(DDriverMsg dDriverMsg);

    /**
     * 新增驾驶员-司机消息
     * 
     * @param dDriverMsg 驾驶员-司机消息
     * @return 结果
     */
    public int insertDDriverMsg(DDriverMsg dDriverMsg);

    /**
     * 修改驾驶员-司机消息
     * 
     * @param dDriverMsg 驾驶员-司机消息
     * @return 结果
     */
    public int updateDDriverMsg(DDriverMsg dDriverMsg);

    /**
     * 批量删除驾驶员-司机消息
     * 
     * @param ids 需要删除的驾驶员-司机消息ID
     * @return 结果
     */
    public int deleteDDriverMsgByIds(String[] ids);

    /**
     * 删除驾驶员-司机消息信息
     * 
     * @param id 驾驶员-司机消息ID
     * @return 结果
     */
    public int deleteDDriverMsgById(String id);

    /**
     * App 个人消息-消息列表接口
     *
     * @param userid 驾驶员-司机ID
     * @return 结果
     */
    List<DDriverMsg> queryDriverMsgList(String userid);

    /**
     * App 个人消息-工作台未读消息列表
     *
     * @param userid 驾驶员-司机ID
     * @return 结果
     */
    List<DDriverMsg> queryDriverUnreadList(String userid);


    /**
     * 提供其他service调用
    * */
    int insertDDriverMsg(Long type, String relationId, String driverId, String title, String content);
}
