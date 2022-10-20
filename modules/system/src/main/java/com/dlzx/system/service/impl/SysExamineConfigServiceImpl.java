package com.dlzx.system.service.impl;

import com.dlzx.system.domain.SysExamineConfig;
import com.dlzx.system.mapper.SysExamineConfigMapper;
import com.dlzx.system.service.ISysExamineConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysExamineConfigServiceImpl  implements ISysExamineConfigService {
    @Autowired
    SysExamineConfigMapper  sysExamineConfigMapper;
    @Override
    public List<SysExamineConfig> selectSysExamineConfigList(SysExamineConfig sysExamineConfig) {
        return sysExamineConfigMapper.selectSysExamineConfigList(sysExamineConfig);
    }

    @Override
    public int updateSysExamineConfig(SysExamineConfig sysExamineConfig) {
        return sysExamineConfigMapper.updateSysExamineConfig(sysExamineConfig);
    }
}
