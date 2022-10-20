package com.dlzx.pfservercar.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.EtcQuotaMapper;
import com.dlzx.pfservercar.domain.EtcQuota;
import com.dlzx.pfservercar.service.IEtcQuotaService;

/**
 * ETC管理-ETC定额Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-02
 */
@Service
public class EtcQuotaServiceImpl implements IEtcQuotaService 
{
    @Autowired
    private EtcQuotaMapper etcQuotaMapper;

    /**
     * 查询ETC管理-ETC定额
     * 
     * @param id ETC管理-ETC定额ID
     * @return ETC管理-ETC定额
     */
    @Override
    public EtcQuota selectEtcQuotaById(String id)
    {
        return etcQuotaMapper.selectEtcQuotaById(id);
    }

    /**
     * 查询ETC管理-ETC定额列表
     * 
     * @param etcQuota ETC管理-ETC定额
     * @return ETC管理-ETC定额
     */
    @Override
    public List<EtcQuota> selectEtcQuotaList(EtcQuota etcQuota)
    {
        return etcQuotaMapper.selectEtcQuotaList(etcQuota);
    }

    @Override
    public List<EtcQuota> selectEtcQuotaListDingE(String routeId,String vehicleModel)
    {
        return etcQuotaMapper.selectEtcQuotaListDingE(routeId,vehicleModel);
    }
    /**
     * 新增ETC管理-ETC定额
     * 
     * @param etcQuota ETC管理-ETC定额
     * @return 结果
     */
    @Override
    public int insertEtcQuota(EtcQuota etcQuota)
    {
        etcQuota.setCreateTime(DateUtils.getNowDate());
        return etcQuotaMapper.insertEtcQuota(etcQuota);
    }

    /**
     * 修改ETC管理-ETC定额
     * 
     * @param etcQuota ETC管理-ETC定额
     * @return 结果
     */
    @Override
    public int updateEtcQuota(EtcQuota etcQuota)
    {
        etcQuota.setUpdateTime(DateUtils.getNowDate());
        return etcQuotaMapper.updateEtcQuota(etcQuota);
    }

    /**
     * 批量删除ETC管理-ETC定额
     * 
     * @param ids 需要删除的ETC管理-ETC定额ID
     * @return 结果
     */
    @Override
    public int deleteEtcQuotaByIds(String[] ids)
    {
        return etcQuotaMapper.deleteEtcQuotaByIds(ids);
    }

    /**
     * 删除ETC管理-ETC定额信息
     * 
     * @param id ETC管理-ETC定额ID
     * @return 结果
     */
    @Override
    public int deleteEtcQuotaById(String id)
    {
        return etcQuotaMapper.deleteEtcQuotaById(id);
    }
}
