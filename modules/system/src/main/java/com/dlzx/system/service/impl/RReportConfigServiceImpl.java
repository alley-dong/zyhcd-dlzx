package com.dlzx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.system.mapper.RReportConfigMapper;
import com.dlzx.system.domain.RReportConfig;
import com.dlzx.system.service.IRReportConfigService;

/**
 * 统计报-统计数据配置Service业务层处理
 * 
 * @author dlzx
 * @date 2021-01-05
 */
@Service
public class RReportConfigServiceImpl implements IRReportConfigService 
{
    @Autowired
    private RReportConfigMapper rReportConfigMapper;

    /**
     * 查询统计报-统计数据配置
     * 
     * @param id 统计报-统计数据配置ID
     * @return 统计报-统计数据配置
     */
    @Override
    public RReportConfig selectRReportConfigById(String id)
    {
        return rReportConfigMapper.selectRReportConfigById(id);
    }

    /**
     * 查询统计报-统计数据配置列表
     * 
     * @param rReportConfig 统计报-统计数据配置
     * @return 统计报-统计数据配置
     */
    @Override
    public List<RReportConfig> selectRReportConfigList(RReportConfig rReportConfig)
    {
        return rReportConfigMapper.selectRReportConfigList(rReportConfig);
    }

    /**
     * 新增统计报-统计数据配置
     * 
     * @param rReportConfig 统计报-统计数据配置
     * @return 结果
     */
    @Override
    public int insertRReportConfig(RReportConfig rReportConfig)
    {
        return rReportConfigMapper.insertRReportConfig(rReportConfig);
    }

    /**
     * 修改统计报-统计数据配置
     * 
     * @param rReportConfig 统计报-统计数据配置
     * @return 结果
     */
    @Override
    public int updateRReportConfig(RReportConfig rReportConfig)
    {
        return rReportConfigMapper.updateRReportConfig(rReportConfig);
    }

    /**
     * 批量删除统计报-统计数据配置
     * 
     * @param ids 需要删除的统计报-统计数据配置ID
     * @return 结果
     */
    @Override
    public int deleteRReportConfigByIds(String[] ids)
    {
        return rReportConfigMapper.deleteRReportConfigByIds(ids);
    }

    /**
     * 删除统计报-统计数据配置信息
     * 
     * @param id 统计报-统计数据配置ID
     * @return 结果
     */
    @Override
    public int deleteRReportConfigById(String id)
    {
        return rReportConfigMapper.deleteRReportConfigById(id);
    }
}
