package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.CVehicleInspectRecord;
import com.dlzx.pfservercar.domain.REtcCost;
import com.dlzx.pfservercar.domain.model.ERPCostModel;

/**
 * 车辆管理-车辆年检记录Mapper接口
 * 
 * @author yangluhe
 * @date 2020-11-27
 */
public interface CVehicleInspectRecordMapper 
{
    /**
     * 查询车辆管理-车辆年检记录
     * 
     * @param id 车辆管理-车辆年检记录ID
     * @return 车辆管理-车辆年检记录
     */
    public CVehicleInspectRecord selectCVehicleInspectRecordById(String id);

    /**
     * 查询车辆管理-车辆年检记录列表
     * 
     * @param cVehicleInspectRecord 车辆管理-车辆年检记录
     * @return 车辆管理-车辆年检记录集合
     */
    public List<CVehicleInspectRecord> selectCVehicleInspectRecordList(CVehicleInspectRecord cVehicleInspectRecord);

    /**
     * 新增车辆管理-车辆年检记录
     * 
     * @param cVehicleInspectRecord 车辆管理-车辆年检记录
     * @return 结果
     */
    public int insertCVehicleInspectRecord(CVehicleInspectRecord cVehicleInspectRecord);

    /**
     * 修改车辆管理-车辆年检记录
     * 
     * @param cVehicleInspectRecord 车辆管理-车辆年检记录
     * @return 结果
     */
    public int updateCVehicleInspectRecord(CVehicleInspectRecord cVehicleInspectRecord);

    /**
     * 删除车辆管理-车辆年检记录
     * 
     * @param id 车辆管理-车辆年检记录ID
     * @return 结果
     */
    public int deleteCVehicleInspectRecordById(String id);

    /**
     * 批量删除车辆管理-车辆年检记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCVehicleInspectRecordByIds(String[] ids);

    /**
     * 查看详情列表
     */
    public List<CVehicleInspectRecord> selectCVehicleInspectRecordInfoList(CVehicleInspectRecord cVehicleInspectRecord);

    /**
     * @describe 车检ERP费用
     * @author DongCL
     * @date 2021-02-21 16:12
     * @param
     * @return
     */
    List<ERPCostModel> queryERPInspectRecordList(ERPCostModel erpCostModel);
}
