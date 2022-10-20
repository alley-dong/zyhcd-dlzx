package com.dlzx.pfservercar.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.CVehicleInspectItemMapper;
import com.dlzx.pfservercar.domain.CVehicleInspectItem;
import com.dlzx.pfservercar.service.ICVehicleInspectItemService;

/**
 * 车辆管理-车辆定检记录-检查项Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-08
 */
@Service
public class CVehicleInspectItemServiceImpl implements ICVehicleInspectItemService 
{
    @Autowired
    private CVehicleInspectItemMapper cVehicleInspectItemMapper;

    /**
     * 查询车辆管理-车辆定检记录-检查项
     * 
     * @param id 车辆管理-车辆定检记录-检查项ID
     * @return 车辆管理-车辆定检记录-检查项
     */
    @Override
    public CVehicleInspectItem selectCVehicleInspectItemById(String id)
    {
        return cVehicleInspectItemMapper.selectCVehicleInspectItemById(id);
    }

    /**
     * 查询车辆管理-车辆定检记录-检查项列表
     * 
     * @param cVehicleInspectItem 车辆管理-车辆定检记录-检查项
     * @return 车辆管理-车辆定检记录-检查项
     */
    @Override
    public List<CVehicleInspectItem> selectCVehicleInspectItemList(CVehicleInspectItem cVehicleInspectItem)
    {
        return cVehicleInspectItemMapper.selectCVehicleInspectItemList(cVehicleInspectItem);
    }

    /**
     * 新增车辆管理-车辆定检记录-检查项
     * 
     * @param cVehicleInspectItem 车辆管理-车辆定检记录-检查项
     * @return 结果
     */
    @Override
    public int insertCVehicleInspectItem(CVehicleInspectItem cVehicleInspectItem)
    {
        return cVehicleInspectItemMapper.insertCVehicleInspectItem(cVehicleInspectItem);
    }

    @Override
    public int insertList(List<CVehicleInspectItem> cVehicleInspectItem) {
        return cVehicleInspectItemMapper.insertList(cVehicleInspectItem);
    }

    /**
     * 修改车辆管理-车辆定检记录-检查项
     * 
     * @param cVehicleInspectItem 车辆管理-车辆定检记录-检查项
     * @return 结果
     */
    @Override
    public int updateCVehicleInspectItem(CVehicleInspectItem cVehicleInspectItem)
    {
        return cVehicleInspectItemMapper.updateCVehicleInspectItem(cVehicleInspectItem);
    }

    /**
     * 批量删除车辆管理-车辆定检记录-检查项
     * 
     * @param ids 需要删除的车辆管理-车辆定检记录-检查项ID
     * @return 结果
     */
    @Override
    public int deleteCVehicleInspectItemByIds(String[] ids)
    {
        return cVehicleInspectItemMapper.deleteCVehicleInspectItemByIds(ids);
    }

    /**
     * 删除车辆管理-车辆定检记录-检查项信息
     * 
     * @param id 车辆管理-车辆定检记录-检查项ID
     * @return 结果
     */
    @Override
    public int deleteCVehicleInspectItemById(String id)
    {
        return cVehicleInspectItemMapper.deleteCVehicleInspectItemById(id);
    }

    /**
     * 查询定检记录下的检查项
     * @param id
     * @return
     */
    @Override
    public List<CVehicleInspectItem> selectCVehicleInspectItemByInspectId(String id){
        return cVehicleInspectItemMapper.selectCVehicleInspectItemByInspectId(id);
    }

    /**
     * @describe 获取主车定检项
     * @author DongCL
     * @date 2021-03-24 16:19
     * @param
     * @return
     */
    @Override
    public List<CVehicleInspectItem> selectCMainVehicleInspectItemByInspectId(String id) {
        return cVehicleInspectItemMapper.selectCMainVehicleInspectItemByInspectId(id);
    }


    /**
     * @describe 获取挂车定检项
     * @author DongCL
     * @date 2021-03-24 16:19
     * @param
     * @return
     */
    @Override
    public List<CVehicleInspectItem> selectCHangVehicleInspectItemByInspectId(String id) {
        return cVehicleInspectItemMapper.selectCHangVehicleInspectItemByInspectId(id);
    }

    /**
     * @describe 根据定检id 删除item信息
     * @author DongCL
     * @date 2021-04-14 13:34
     * @param
     * @return
     */
    @Override
    public int deleteCVehicleInspectItemByinspectId(String id) {
        return cVehicleInspectItemMapper.deleteCVehicleInspectItemByinspectId(id);
    }
}
