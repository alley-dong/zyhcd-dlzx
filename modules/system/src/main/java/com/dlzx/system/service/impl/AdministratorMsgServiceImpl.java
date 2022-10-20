package com.dlzx.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.system.domain.SysExamineConfigProcess;
import com.dlzx.system.service.SysExamineConfigProcessService;
import com.dlzx.system.service.impl.PushMessage.PushMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.system.mapper.AdministratorMsgMapper;
import com.dlzx.system.domain.AdministratorMsg;
import com.dlzx.system.service.IAdministratorMsgService;

/**
 * 管理员-管理员消息Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-14
 */
@Service
public class AdministratorMsgServiceImpl implements IAdministratorMsgService 
{
    @Autowired
    private AdministratorMsgMapper administratorMsgMapper;
    @Autowired
    private SysExamineConfigProcessService sysExamineConfigProcessService;
    @Autowired
    private PushMessage pushMessage;

    /**
     * 查询管理员-管理员消息
     * 
     * @param id 管理员-管理员消息ID
     * @return 管理员-管理员消息
     */
    @Override
    public AdministratorMsg selectAdministratorMsgById(String id)
    {
        return administratorMsgMapper.selectAdministratorMsgById(id);
    }

    /**
     * 查询管理员-管理员消息列表
     * 
     * @param administratorMsg 管理员-管理员消息
     * @return 管理员-管理员消息
     */
    @Override
    public List<AdministratorMsg> selectAdministratorMsgList(AdministratorMsg administratorMsg)
    {
        return administratorMsgMapper.selectAdministratorMsgList(administratorMsg);
    }

    /**
     * 新增管理员-管理员消息
     * 
     * @param administratorMsg 管理员-管理员消息
     * @return 结果
     */
    @Override
    public int insertAdministratorMsg(AdministratorMsg administratorMsg)
    {
        administratorMsg.setCreateTime(DateUtils.getNowDate());
        return administratorMsgMapper.insertAdministratorMsg(administratorMsg);
    }

    /**
     * 修改管理员-管理员消息
     * 
     * @param administratorMsg 管理员-管理员消息
     * @return 结果
     */
    @Override
    public int updateAdministratorMsg(AdministratorMsg administratorMsg)
    {
        administratorMsg.setUpdateTime(DateUtils.getNowDate());
        return administratorMsgMapper.updateAdministratorMsg(administratorMsg);
    }

    /**
     * 批量删除管理员-管理员消息
     * 
     * @param ids 需要删除的管理员-管理员消息ID
     * @return 结果
     */
    @Override
    public int deleteAdministratorMsgByIds(String[] ids)
    {
        return administratorMsgMapper.deleteAdministratorMsgByIds(ids);
    }

    /**
     * 删除管理员-管理员消息信息
     * 
     * @param id 管理员-管理员消息ID
     * @return 结果
     */
    @Override
    public int deleteAdministratorMsgById(String id)
    {
        return administratorMsgMapper.deleteAdministratorMsgById(id);
    }

    /**
     * App 个人消息-消息列表接口
     *
     * @param userid 驾驶员-司机ID
     * @return 结果
     */
    @Override
    public List<AdministratorMsg> queryAdministratorMsgList(Long userid) {
        return administratorMsgMapper.queryAdministratorMsgList(userid);
    }

    /*
     * 批量插入
     * */
    @Override
    public int insertList(List<AdministratorMsg> administratorMsgList) {
        return administratorMsgMapper.insertList(administratorMsgList);
    }

    /**
     * 插入消息节点
     */
    @Override
    public int insertMessagePort(Long type, String relationId, String title, String content,String userId) {
        // 根据业务类型 获取对应管理员
        List<AdministratorMsg> administratorMsgList = new ArrayList<>();
        AdministratorMsg administratorMsg = new AdministratorMsg();
        administratorMsg.setType(type);
        administratorMsg.setRelationId(relationId);
        administratorMsg.setUserId(userId + "");
        administratorMsg.setTitle(title);
        administratorMsg.setContent(content);
        administratorMsg.setStatus(1L);
        administratorMsg.setId(UUID.randomUUID() + "");
        administratorMsg.setCreateTime(new Date());
        administratorMsgList.add(administratorMsg);
        if (administratorMsgList.size() > 0) {
            try {
                pushMessage.testAdvancedPush(title,content,userId,1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return this.insertList(administratorMsgList);
        }
        return 0;
    }
}
