package com.dlzx.pfserverperson.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.pfserverperson.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DDriverTypeMapper;
import com.dlzx.pfserverperson.domain.DDriverType;
import com.dlzx.pfserverperson.service.IDDriverTypeService;

/**
 * 驾驶员-驾驶员类型Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-11-18
 */
@Service
public class DDriverTypeServiceImpl implements IDDriverTypeService 
{
    @Autowired
    private DDriverTypeMapper dDriverTypeMapper;
    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 查询驾驶员-驾驶员类型
     * 
     * @param id 驾驶员-驾驶员类型ID
     * @return 驾驶员-驾驶员类型
     */
    @Override
    public DDriverType selectDDriverTypeById(String id)
    {
        DDriverType dDriverType = dDriverTypeMapper.selectDDriverTypeById(id);
        if (dDriverType != null){
            dDriverType.setTypeEmnu(sysDictDataMapper.selectDictLabel("d_driver_type_type", dDriverType.getType().toString()));
        }
        return dDriverType;
    }

    /**
     * 查询驾驶员-驾驶员类型列表
     * 
     * @param dDriverType 驾驶员-驾驶员类型
     * @return 驾驶员-驾驶员类型
     */
    @Override
    public List<DDriverType> selectDDriverTypeList(DDriverType dDriverType)
    {
        return dDriverTypeMapper.selectDDriverTypeList(dDriverType);
    }

    /**
     * 新增驾驶员-驾驶员类型
     * 
     * @param dDriverType 驾驶员-驾驶员类型
     * @return 结果
     */
    @Override
    public int insertDDriverType(DDriverType dDriverType)
    {
        dDriverType.setCreateTime(DateUtils.getNowDate());
        return dDriverTypeMapper.insertDDriverType(dDriverType);
    }

    /**
     * 修改驾驶员-驾驶员类型
     * 
     * @param dDriverType 驾驶员-驾驶员类型
     * @return 结果
     */
    @Override
    public int updateDDriverType(DDriverType dDriverType)
    {
        dDriverType.setUpdateTime(DateUtils.getNowDate());
        return dDriverTypeMapper.updateDDriverType(dDriverType);
    }

    /**
     * 批量删除驾驶员-驾驶员类型
     * 
     * @param ids 需要删除的驾驶员-驾驶员类型ID
     * @return 结果
     */
    @Override
    public int deleteDDriverTypeByIds(String[] ids)
    {
        return dDriverTypeMapper.deleteDDriverTypeByIds(ids);
    }

    /**
     * 删除驾驶员-驾驶员类型信息
     * 
     * @param id 驾驶员-驾驶员类型ID
     * @return 结果
     */
    @Override
    public int deleteDDriverTypeById(String id)
    {
        return dDriverTypeMapper.deleteDDriverTypeById(id);
    }
}
