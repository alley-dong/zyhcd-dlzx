package com.dlzx.pfservercar.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.CInsuranceTypeMapper;
import com.dlzx.pfservercar.domain.CInsuranceType;
import com.dlzx.pfservercar.service.ICInsuranceTypeService;

/**
 * 车辆管理-险种类别Service业务层处理
 * 
 * @author jijawen
 * @date 2020-11-23
 */
@Service
public class CInsuranceTypeServiceImpl implements ICInsuranceTypeService 
{
    @Autowired
    private CInsuranceTypeMapper cInsuranceTypeMapper;

    /**
     * 查询车辆管理-险种类别
     * 
     * @param id 车辆管理-险种类别ID
     * @return 车辆管理-险种类别
     */
    @Override
    public CInsuranceType selectCInsuranceTypeById(String id)
    {
        return cInsuranceTypeMapper.selectCInsuranceTypeById(id);
    }

    @Override
    public int countAll(String name) {
        return cInsuranceTypeMapper.countAll(name);
    }

    @Override
    public int countAllEdit(String name) {
        return cInsuranceTypeMapper.countAllEdit(name);
    }


    /**
     * 查询车辆管理-险种类别列表
     * 
     * @param cInsuranceType 车辆管理-险种类别
     * @return 车辆管理-险种类别
     */
    @Override
    public List<CInsuranceType> selectCInsuranceTypeList(CInsuranceType cInsuranceType)
    {
        return cInsuranceTypeMapper.selectCInsuranceTypeList(cInsuranceType);
    }

    /**
     * 新增车辆管理-险种类别
     * 
     * @param cInsuranceType 车辆管理-险种类别
     * @return 结果
     */
    @Override
    public int insertCInsuranceType(CInsuranceType cInsuranceType)
    {
        cInsuranceType.setCreateTime(DateUtils.getNowDate());
        return cInsuranceTypeMapper.insertCInsuranceType(cInsuranceType);
    }

    /**
     * 修改车辆管理-险种类别
     * 
     * @param cInsuranceType 车辆管理-险种类别
     * @return 结果
     */
    @Override
    public int updateCInsuranceType(CInsuranceType cInsuranceType)
    {
        cInsuranceType.setUpdateTime(DateUtils.getNowDate());
        return cInsuranceTypeMapper.updateCInsuranceType(cInsuranceType);
    }

    /**
     * 批量删除车辆管理-险种类别
     * 
     * @param ids 需要删除的车辆管理-险种类别ID
     * @return 结果
     */
    @Override
    public int deleteCInsuranceTypeByIds(String[] ids)
    {
        return cInsuranceTypeMapper.deleteCInsuranceTypeByIds(ids);
    }

    /**
     * 删除车辆管理-险种类别信息
     * 
     * @param id 车辆管理-险种类别ID
     * @return 结果
     */
    @Override
    public int deleteCInsuranceTypeById(String id)
    {
        return cInsuranceTypeMapper.deleteCInsuranceTypeById(id);
    }
}
