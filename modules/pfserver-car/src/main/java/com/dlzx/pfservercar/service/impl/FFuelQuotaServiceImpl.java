package com.dlzx.pfservercar.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.FFuelQuotaMapper;
import com.dlzx.pfservercar.domain.FFuelQuota;
import com.dlzx.pfservercar.service.IFFuelQuotaService;

/**
 * 燃料管理-燃油定额Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-02
 */
@Service
public class FFuelQuotaServiceImpl implements IFFuelQuotaService 
{
    @Autowired
    private FFuelQuotaMapper fFuelQuotaMapper;

    /**
     * 查询燃料管理-燃油定额
     * 
     * @param id 燃料管理-燃油定额ID
     * @return 燃料管理-燃油定额
     */
    @Override
    public FFuelQuota selectFFuelQuotaById(String id)
    {
        return fFuelQuotaMapper.selectFFuelQuotaById(id);
    }

    /**
     * 查询燃料管理-燃油定额列表
     * 
     * @param fFuelQuota 燃料管理-燃油定额
     * @return 燃料管理-燃油定额
     */
    @Override
    public List<FFuelQuota> selectFFuelQuotaList(FFuelQuota fFuelQuota)
    {
        return fFuelQuotaMapper.selectFFuelQuotaList(fFuelQuota);
    }

    /**
     * 新增燃料管理-燃油定额
     * 
     * @param fFuelQuota 燃料管理-燃油定额
     * @return 结果
     */
    @Override
    public int insertFFuelQuota(FFuelQuota fFuelQuota)
    {
        fFuelQuota.setCreateTime(DateUtils.getNowDate());
        return fFuelQuotaMapper.insertFFuelQuota(fFuelQuota);
    }

    /**
     * 修改燃料管理-燃油定额
     * 
     * @param fFuelQuota 燃料管理-燃油定额
     * @return 结果
     */
    @Override
    public int updateFFuelQuota(FFuelQuota fFuelQuota)
    {
        fFuelQuota.setUpdateTime(DateUtils.getNowDate());
        return fFuelQuotaMapper.updateFFuelQuota(fFuelQuota);
    }

    /**
     * 批量删除燃料管理-燃油定额
     * 
     * @param ids 需要删除的燃料管理-燃油定额ID
     * @return 结果
     */
    @Override
    public int deleteFFuelQuotaByIds(String[] ids)
    {
        return fFuelQuotaMapper.deleteFFuelQuotaByIds(ids);
    }

    /**
     * 删除燃料管理-燃油定额信息
     * 
     * @param id 燃料管理-燃油定额ID
     * @return 结果
     */
    @Override
    public int deleteFFuelQuotaById(String id)
    {
        return fFuelQuotaMapper.deleteFFuelQuotaById(id);
    }
}
