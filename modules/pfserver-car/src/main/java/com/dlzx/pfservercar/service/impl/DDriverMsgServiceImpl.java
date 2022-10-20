package com.dlzx.pfservercar.service.impl;

import java.util.List;
import java.util.UUID;

import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.DDriverMsgMapper;
import com.dlzx.pfservercar.domain.DDriverMsg;
import com.dlzx.pfservercar.service.IDDriverMsgService;

/**
 * 驾驶员-司机消息Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-07
 */
@Service
public class DDriverMsgServiceImpl implements IDDriverMsgService 
{
    @Autowired
    private DDriverMsgMapper dDriverMsgMapper;

    /**
     * 查询驾驶员-司机消息
     * 
     * @param id 驾驶员-司机消息ID
     * @return 驾驶员-司机消息
     */
    @Override
    public DDriverMsg selectDDriverMsgById(String id)
    {
        return dDriverMsgMapper.selectDDriverMsgById(id);
    }

    /**
     * 查询驾驶员-司机消息列表
     * 
     * @param dDriverMsg 驾驶员-司机消息
     * @return 驾驶员-司机消息
     */
    @Override
    public List<DDriverMsg> selectDDriverMsgList(DDriverMsg dDriverMsg)
    {
        return dDriverMsgMapper.selectDDriverMsgList(dDriverMsg);
    }

    /**
     * 新增驾驶员-司机消息
     * 
     * @param dDriverMsg 驾驶员-司机消息
     * @return 结果
     */
    @Override
    public int insertDDriverMsg(DDriverMsg dDriverMsg)
    {
        dDriverMsg.setCreateTime(DateUtils.getNowDate());
        return dDriverMsgMapper.insertDDriverMsg(dDriverMsg);
    }

    /**
     * 修改驾驶员-司机消息
     * 
     * @param dDriverMsg 驾驶员-司机消息
     * @return 结果
     */
    @Override
    public int updateDDriverMsg(DDriverMsg dDriverMsg)
    {
        dDriverMsg.setUpdateTime(DateUtils.getNowDate());
        return dDriverMsgMapper.updateDDriverMsg(dDriverMsg);
    }

    /**
     * 批量删除驾驶员-司机消息
     * 
     * @param ids 需要删除的驾驶员-司机消息ID
     * @return 结果
     */
    @Override
    public int deleteDDriverMsgByIds(String[] ids)
    {
        return dDriverMsgMapper.deleteDDriverMsgByIds(ids);
    }

    /**
     * 删除驾驶员-司机消息信息
     * 
     * @param id 驾驶员-司机消息ID
     * @return 结果
     */
    @Override
    public int deleteDDriverMsgById(String id)
    {
        return dDriverMsgMapper.deleteDDriverMsgById(id);
    }

    /**
     * 删除驾驶员-司机消息信息列表
     *
     * @param userid 驾驶员-司机ID
     * @return 结果
     */
    @Override
    public List<DDriverMsg> queryDriverMsgList(String userid) {
        return dDriverMsgMapper.queryDriverMsgList(userid);
    }

    /**
     * App 个人消息-工作台未读消息列表
     *
     * @param userid 驾驶员-司机ID
     * @return 结果
     */
    @Override
    public List<DDriverMsg> queryDriverUnreadList(String userid) {
        return dDriverMsgMapper.queryDriverUnreadList(userid);
    }

    /**
     * 提供其他service调用
     * */
    @Override
    public int insertDDriverMsg(Long type, String relationId, String driverId, String title, String content) {
        DDriverMsg dDriverMsg = new DDriverMsg();
        dDriverMsg.setType(type);
        dDriverMsg.setRelationId(relationId);
        dDriverMsg.setDriverId(driverId);
        dDriverMsg.setTitle(title);
        dDriverMsg.setContent(content);
        dDriverMsg.setStatus(1L);
        dDriverMsg.setId(UUID.randomUUID()+"");
        return dDriverMsgMapper.insertDDriverMsg(dDriverMsg);
    }
}
