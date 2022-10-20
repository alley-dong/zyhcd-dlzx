package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.CInsuranceTypeItem;

/**
 * 车辆管理-险种项Mapper接口
 * 
 * @author dlzx
 * @date 2020-11-23
 */
public interface CInsuranceTypeItemMapper 
{
    /**
     * 查询车辆管理-险种项
     * 
     * @param id 车辆管理-险种项ID
     * @return 车辆管理-险种项
     */
    public CInsuranceTypeItem selectCInsuranceTypeItemById(String id);

    /**
     * 查询车辆管理-险种项列表
     * 
     * @param cInsuranceTypeItem 车辆管理-险种项
     * @return 车辆管理-险种项集合
     */
    public List<CInsuranceTypeItem> selectCInsuranceTypeItemList(CInsuranceTypeItem cInsuranceTypeItem);

    /**
     * 新增车辆管理-险种项
     * 
     * @param cInsuranceTypeItem 车辆管理-险种项
     * @return 结果
     */
    public int insertCInsuranceTypeItem(CInsuranceTypeItem cInsuranceTypeItem);

    /**
     * 修改车辆管理-险种项
     * 
     * @param cInsuranceTypeItem 车辆管理-险种项
     * @return 结果
     */
    public int updateCInsuranceTypeItem(CInsuranceTypeItem cInsuranceTypeItem);

    /**
     * 删除车辆管理-险种项
     * 
     * @param id 车辆管理-险种项ID
     * @return 结果
     */
    public int deleteCInsuranceTypeItemById(String id);

    /**
     * 批量删除车辆管理-险种项
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCInsuranceTypeItemByIds(String[] ids);

    /**
     * E02Type
     */
    List<CInsuranceTypeItem> selectTypeList(CInsuranceTypeItem cInsuranceTypeItem);

    /**
     * 查询险种是否重复
     * @param cInsuranceTypeItem
     * @return
     */
    CInsuranceTypeItem selectRepeatTypeItem(CInsuranceTypeItem cInsuranceTypeItem);
}
