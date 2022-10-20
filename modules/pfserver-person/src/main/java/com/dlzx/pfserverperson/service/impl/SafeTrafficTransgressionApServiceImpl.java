package com.dlzx.pfserverperson.service.impl;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.pfserverperson.domain.SafeTrafficTransgression;
import com.dlzx.pfserverperson.domain.SafeTrafficTransgressionAp;
import com.dlzx.pfserverperson.domain.SysFile;
import com.dlzx.pfserverperson.mapper.SafeTrafficTransgressionApMapper;
import com.dlzx.pfserverperson.mapper.SafeTrafficTransgressionMapper;
import com.dlzx.pfserverperson.service.ISafeTrafficTransgressionApService;
import com.dlzx.pfserverperson.service.ISafeTrafficTransgressionService;
import com.dlzx.pfserverperson.service.ISysFileService;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 安全管理-违章记录Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-10
 */
@Service
public class SafeTrafficTransgressionApServiceImpl implements ISafeTrafficTransgressionApService
{
    @Autowired
    private SafeTrafficTransgressionApMapper safeTrafficTransgressionApMapper;
    @Autowired
    private SafeTrafficTransgressionMapper safeTrafficTransgressionMapper;
    @Autowired
    private ISysFileService sysFileService;
    /**
     * 查询安全管理-违章记录
     * 
     * @param id 安全管理-违章记录ID
     * @return 安全管理-违章记录
     */
    @Override
    public SafeTrafficTransgressionAp selectSafeTrafficTransgressionById(String id)
    {
        return safeTrafficTransgressionApMapper.selectSafeTrafficTransgressionById(id);
    }

    /**
     * 查询安全管理-违章记录列表
     * 
     * @param safeTrafficTransgression 安全管理-违章记录
     * @return 安全管理-违章记录
     */
    @Override
    public List<SafeTrafficTransgression> selectSafeTrafficTransgressionList(SafeTrafficTransgression safeTrafficTransgression)
    {
        return safeTrafficTransgressionMapper.selectSafeTrafficTransgressionList(safeTrafficTransgression);
    }

    @Override
    public List<SafeTrafficTransgressionAp> selectSafeTrafficTransgressionListDriver(SafeTrafficTransgressionAp safeTrafficTransgression)
    {
        return safeTrafficTransgressionApMapper.selectSafeTrafficTransgressionListDriver(safeTrafficTransgression);
    }
    /**
     * 新增安全管理-违章记录
     * 
     * @param safeTrafficTransgression 安全管理-违章记录
     * @return 结果
     */
    @Override
    public int insertSafeTrafficTransgression(SafeTrafficTransgression safeTrafficTransgression)
    {
        safeTrafficTransgression.setCreateTime(DateUtils.getNowDate());
        return safeTrafficTransgressionMapper.insertSafeTrafficTransgression(safeTrafficTransgression);
    }

    /**
     * 修改安全管理-违章记录
     * 
     * @param safeTrafficTransgression 安全管理-违章记录
     * @return 结果
     */
    @Override
    public int updateSafeTrafficTransgression(SafeTrafficTransgressionAp safeTrafficTransgression)
    {
        safeTrafficTransgression.setUpdateTime(DateUtils.getNowDate());
        safeTrafficTransgression.setCompleteTime(DateUtils.getNowDate());
        return safeTrafficTransgressionApMapper.updateSafeTrafficTransgression(safeTrafficTransgression);
    }

    @Override
    @Transactional
    public int updateSafeTrafficTransgressionUrl(SafeTrafficTransgressionAp safeTrafficTransgression, List<SysFile> list) {
        int result = 1;
        try {
            sysFileService.updateSysFileDel(safeTrafficTransgression.getId(),"8");

            if (list.size()>0) {
                result = sysFileService.insertSysfileBatch(list);
            }
            if (safeTrafficTransgression!= null) {
                result = this.updateSafeTrafficTransgression(safeTrafficTransgression);
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    /**
     * 批量删除安全管理-违章记录
     * 
     * @param ids 需要删除的安全管理-违章记录ID
     * @return 结果
     */
    @Override
    public int deleteSafeTrafficTransgressionByIds(String[] ids)
    {
        return safeTrafficTransgressionMapper.deleteSafeTrafficTransgressionByIds(ids);
    }

    /**
     * 删除安全管理-违章记录信息
     * 
     * @param id 安全管理-违章记录ID
     * @return 结果
     */
    @Override
    public int deleteSafeTrafficTransgressionById(String id)
    {
        return safeTrafficTransgressionMapper.deleteSafeTrafficTransgressionById(id);
    }
}
