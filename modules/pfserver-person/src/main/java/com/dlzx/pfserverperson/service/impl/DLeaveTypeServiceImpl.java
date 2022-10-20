package com.dlzx.pfserverperson.service.impl;

import java.util.List;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.pfserverperson.domain.DDriver;
import com.dlzx.pfserverperson.mapper.DDriverMapper;
import com.dlzx.system.api.model.LoginDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DLeaveTypeMapper;
import com.dlzx.pfserverperson.domain.DLeaveType;
import com.dlzx.pfserverperson.service.IDLeaveTypeService;

/**
 * 驾驶员-请假类型Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-11-24
 */
@Service
public class DLeaveTypeServiceImpl implements IDLeaveTypeService 
{
    @Autowired
    private DLeaveTypeMapper dLeaveTypeMapper;

    @Autowired
    private DriverTokenService driverTokenService;

    @Autowired
    private DDriverMapper dDriverMapper;

    /**
     * 查询驾驶员-请假类型
     *
     * @param id 驾驶员-请假类型ID
     * @return 驾驶员-请假类型
     */
    @Override
    public DLeaveType selectDLeaveTypeById(String id)
    {
        return dLeaveTypeMapper.selectDLeaveTypeById(id);
    }

    /**
     * 查询驾驶员-请假类型列表
     * 
     * @param dLeaveType 驾驶员-请假类型
     * @return 驾驶员-请假类型
     */
    @Override
    public List<DLeaveType> selectDLeaveTypeList(DLeaveType dLeaveType)
    {
        return dLeaveTypeMapper.selectDLeaveTypeList(dLeaveType);
    }

    /**
     * 新增驾驶员-请假类型
     * 
     * @param dLeaveType 驾驶员-请假类型
     * @return 结果
     */
    @Override
    public int insertDLeaveType(DLeaveType dLeaveType)
    {
        dLeaveType.setCreateTime(DateUtils.getNowDate());
        return dLeaveTypeMapper.insertDLeaveType(dLeaveType);
    }

    /**
     * 修改驾驶员-请假类型
     * 
     * @param dLeaveType 驾驶员-请假类型
     * @return 结果
     */
    @Override
    public int updateDLeaveType(DLeaveType dLeaveType)
    {
        dLeaveType.setUpdateTime(DateUtils.getNowDate());
        return dLeaveTypeMapper.updateDLeaveType(dLeaveType);
    }

    /**
     * 批量删除驾驶员-请假类型
     * 
     * @param ids 需要删除的驾驶员-请假类型ID
     * @return 结果
     */
    @Override
    public int deleteDLeaveTypeByIds(String[] ids)
    {
        return dLeaveTypeMapper.deleteDLeaveTypeByIds(ids);
    }

    /**
     * 删除驾驶员-请假类型信息
     * 
     * @param id 驾驶员-请假类型ID
     * @return 结果
     */
    @Override
    public int deleteDLeaveTypeById(String id)
    {
        return dLeaveTypeMapper.deleteDLeaveTypeById(id);
    }

    /**
     * @describe 请假类型
     * @author DongCL
     * @date 2021-01-27 14:38
     * @param
     * @return
     */
    @Override
    public List<DLeaveType> E03VacationTypeList() {

        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        DDriver dDriver = dDriverMapper.selectDDriverById(loginDriver.getUserid());
        if (dDriver == null){
            throw new CustomException("驾驶员不存在");
        }
        if (StringUtils.isNull(dDriver.getStaffType())){
            throw new CustomException("员工类型不能为空");
        }
        DLeaveType dLeaveType = new DLeaveType();
        dLeaveType.setStaffType(dDriver.getStaffType());
        return dLeaveTypeMapper.selectDLeaveTypeList(dLeaveType);
    }
}
