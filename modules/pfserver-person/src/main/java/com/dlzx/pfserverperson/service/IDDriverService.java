package com.dlzx.pfserverperson.service;

import java.util.List;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfserverperson.domain.DDriver;
import com.dlzx.pfserverperson.domain.model.OTMDriverModel;
import com.dlzx.system.api.model.LoginDriver;
import org.springframework.stereotype.Service;

/**
 * 驾驶员-司机档案Service接口
 * 
 * @author yangluhe
 * @date 2020-11-18
 */
public interface IDDriverService 
{
    /**
     * 查询驾驶员-司机档案
     * 
     * @param id 驾驶员-司机档案ID
     * @return 驾驶员-司机档案
     */
    public DDriver selectDDriverById(String id);

    /**
     * 链表查询是否存在
     * @param driverTypeId
     * @return
     */
    public List<DDriver> selectDDriverByDriverTypeId(String driverTypeId);

    /**
     * 查询驾驶员-司机档案列表
     * 
     * @param dDriver 驾驶员-司机档案
     * @return 驾驶员-司机档案集合
     */
    public List<DDriver> selectDDriverList(DDriver dDriver);

    public List<DDriver> selectDDriverListName();

    public List<DDriver> selectDDriverListQi(DDriver dDriver);

    public List<DDriver> selectDDriverListTong(DDriver dDriver);

    public List<DDriver> selectDDriverListZhong(DDriver dDriver);

    public List<DDriver> selectDDriverListS(List<String> dDriverId);

    public List<DDriver> selectDDriverOfIdsList();
    /**
     * 新增驾驶员-司机档案
     * 
     * @param dDriver 驾驶员-司机档案
     * @return 结果
     */
    public int insertDDriver(DDriver dDriver);

    /**
     * 新增档案时新增文件
     */
    public int insertDDriverAndFile(DDriver dDriver);

    /**
     * 修改驾驶员-司机档案
     * 
     * @param dDriver 驾驶员-司机档案
     * @return 结果
     */
    public int updateDDriver(DDriver dDriver);
/**
     * 修改驾驶员-司机档案
     *
     * @param dDriver 驾驶员-司机档案
     * @return 结果
     */
    public int updateOTMDriver(DDriver dDriver);

    /**
     * 批量删除驾驶员-司机档案
     * 
     * @param ids 需要删除的驾驶员-司机档案ID
     * @return 结果
     */
    public int deleteDDriverByIds(String[] ids);

    /**
     * 删除驾驶员-司机档案信息
     * 
     * @param id 驾驶员-司机档案ID
     * @return 结果
     */
    public int deleteDDriverById(String id);

    /**
     * 校验身份证号是否重复
     */
    public int selectDDriverByIdCard(String idCard);

    /**
     * 校验手机号号是否重复
     */
    public int selectDDriverByIdContact(String idCard);

    /**
     * 长途司机list
     * @return
     */
    public List<DDriver> selectDDriverLongD();

    /**
     * 短途司机list
     * @return
     */
    public List<DDriver> selectDDriverShortD();

    /**
     * 查出所有司机
     * @return
     */
    public List<DDriver> selectDDriverAll();

    /**
     * 驾驶员档案列表
     * @param dDriver
     * @return
     */
    public List<DDriver> selectDDriverListFile(DDriver dDriver);

    /**
     * OTM-查出所有司机
     * @return
     */
    public List<OTMDriverModel> selectOTMDriverAll();

    /**
     * @describe
     * @author DongCL
     * @date 2021-01-08 16:03
     * @param contact
     * @return DDriver
     */
    DDriver queryDriverByContact(String contact);

    /**
     * @describe 登录
     * @author DongCL
     * @date 2021-01-26 15:31
     * @param
     * @return
     */
    AjaxResult login(DDriver dDriver);

    /**
     * @describe App更新用户密码
     * @author DongCL
     * @date 2021-02-23 16:53
     * @param
     * @return
     */
    int updateDDriverPassword(String id, String encryptPassword);

    /**
     * 消息推送通过createBy 反查驾驶员id
     */
    DDriver selectDDriverId(String name);

    List<DDriver> selectDDriverStaff();
}
