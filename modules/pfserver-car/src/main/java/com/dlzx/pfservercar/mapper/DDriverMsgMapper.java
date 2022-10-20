package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.DDriverMsg;

/**
 * 驾驶员-司机消息Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-07
 */
public interface DDriverMsgMapper 
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
     * 删除驾驶员-司机消息
     * 
     * @param id 驾驶员-司机消息ID
     * @return 结果
     */
    public int deleteDDriverMsgById(String id);

    /**
     * 批量删除驾驶员-司机消息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDDriverMsgByIds(String[] ids);

    /**
     * 删除驾驶员-司机消息信息列表
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
}
