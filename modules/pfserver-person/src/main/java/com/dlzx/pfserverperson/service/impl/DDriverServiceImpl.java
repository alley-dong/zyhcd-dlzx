package com.dlzx.pfserverperson.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dlzx.common.core.enums.UserStatus;
import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.utils.VerifyCodeUtil;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.mongo.utils.StringUtil;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.common.security.utils.SecurityUtils;
import com.dlzx.pfserverperson.client.CarClient;
import com.dlzx.pfserverperson.domain.DDriver;
import com.dlzx.pfserverperson.domain.DDriverType;
import com.dlzx.pfserverperson.domain.SysFile;
import com.dlzx.pfserverperson.domain.model.OTMDriverModel;
import com.dlzx.pfserverperson.mapper.DDriverTypeMapper;
import com.dlzx.pfserverperson.mapper.SysFileMapper;
import com.dlzx.pfserverperson.service.IDDriverService;
import com.dlzx.system.api.model.LoginDriver;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DDriverMapper;
import com.dlzx.pfserverperson.domain.DDriver;
import com.dlzx.pfserverperson.service.IDDriverService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 驾驶员-司机档案Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-11-18
 */
@Service
public class DDriverServiceImpl implements IDDriverService
{
    @Value("${esb-config.is-enable}")
    private boolean enable;

    @Autowired
    private DDriverMapper dDriverMapper;
    @Autowired
    private SysFileMapper sysFileMapper;
    @Autowired
    private CarClient carClient;
    @Autowired
    private DDriverTypeMapper dDriverTypeMapper;
    @Autowired
    private DriverTokenService driverTokenService;

    /**
     * 查询驾驶员-司机档案
     * 
     * @param id 驾驶员-司机档案ID
     * @return 驾驶员-司机档案
     */
    @Override
    public DDriver selectDDriverById(String id)
    {
        DDriver dDriver = dDriverMapper.selectDDriverById(id);
        List<SysFile> sysFileList = sysFileMapper.queryFilesByRelationId(id);
        List<String> fileUrl = new ArrayList<>();
        fileUrl = sysFileList.stream().map(s->s.getFilePath()).collect(Collectors.toList());
        dDriver.setFileUrl(fileUrl);
        if (!StringUtils.isEmpty(dDriver.getDriverTypeId())){
            DDriverType dDriverType = dDriverTypeMapper.selectDDriverTypeById(dDriver.getDriverTypeId());
            dDriver.setDriverTypeName(dDriverType.getName());
        }
        return dDriver;
    }

    @Override
    public List<DDriver> selectDDriverByDriverTypeId(@Param("driverTypeId") String driverTypeId) {
        return dDriverMapper.selectDDriverByDriverTypeId(driverTypeId);
    }

    /**
     * 查询驾驶员-司机档案列表
     * 
     * @param dDriver 驾驶员-司机档案
     * @return 驾驶员-司机档案
     */
    @Override
    public List<DDriver> selectDDriverList(DDriver dDriver)
    {

        return dDriverMapper.selectDDriverList(dDriver);
    }

    @Override
    public List<DDriver> selectDDriverListName()
    {
        return dDriverMapper.selectDDriverListName();
    }

    @Override
    public List<DDriver> selectDDriverListQi(DDriver dDriver)
    {
        return dDriverMapper.selectDDriverListQi(dDriver);
    }

    @Override
    public List<DDriver> selectDDriverListTong(DDriver dDriver)
    {
        return dDriverMapper.selectDDriverListTong(dDriver);
    }

    @Override
    public List<DDriver> selectDDriverListZhong(DDriver dDriver)
    {
        return dDriverMapper.selectDDriverListZhong(dDriver);
    }

    @Override
    public List<DDriver> selectDDriverListS(List<String> dDriverId)
    {
        return dDriverMapper.selectDDriverListS(dDriverId);
    }

    @Override
    public List<DDriver> selectDDriverOfIdsList() {
        return dDriverMapper.selectDDriverOfIdsList();
    }

    /**
     * 新增驾驶员-司机档案
     * 
     * @param dDriver 驾驶员-司机档案
     * @return 结果
     */
    @Override
    public int insertDDriver(DDriver dDriver) {

        // yml文件配置是否开启
        if (enable) {
            // 调用OTM接口，将驾驶员信息同步给零件系统
            if (carClient.OTMCarDriver(dDriver) <= 0) {
                throw new CustomException("OTM驾驶员传入失败");
            }
        }
        dDriver.setCreateTime(DateUtils.getNowDate());
        return dDriverMapper.insertDDriver(dDriver);
    }

    /**
     * 修改驾驶员-司机档案
     * 
     * @param dDriver 驾驶员-司机档案
     * @return 结果
     */
    @Override
    public int updateDDriver(DDriver dDriver)
    {
        dDriver.setUpdateTime(DateUtils.getNowDate());
        return dDriverMapper.updateDDriver(dDriver);
    }

    /**
     * 修改驾驶员-司机档案
     *
     * @param dDriver 驾驶员-司机档案
     * @return 结果
     */
    @Override
    public int updateOTMDriver(DDriver dDriver)
    {
        // yml文件配置是否开启
        if (enable) {
            // 调用OTM接口，将驾驶员信息同步给零件系统
            if (carClient.OTMCarDriver(dDriver) <= 0) {
                throw new CustomException("OTM驾驶员传入失败");
            }
        }
        dDriver.setUpdateTime(DateUtils.getNowDate());
        return dDriverMapper.updateDDriver(dDriver);
    }

    /**
     * 批量删除驾驶员-司机档案
     * 
     * @param ids 需要删除的驾驶员-司机档案ID
     * @return 结果
     */
    @Override
    public int deleteDDriverByIds(String[] ids)
    {
        return dDriverMapper.deleteDDriverByIds(ids);
    }

    /**
     * 删除驾驶员-司机档案信息
     * 
     * @param id 驾驶员-司机档案ID
     * @return 结果
     */
    @Override
    public int deleteDDriverById(String id)
    {
        DDriver dDriver = dDriverMapper.selectDDriverById(id);
        // 删除要将禁用状态改为已禁用
        dDriver.setDisableStatus(2L);

        // yml文件配置是否开启
        if (enable) {
            // 调用OTM接口，将驾驶员信息同步给零件系统
            if (carClient.OTMCarDriver(dDriver) <= 0) {
                throw new CustomException("OTM驾驶员传入失败");
            }
        }
        return dDriverMapper.deleteDDriverById(id);
    }

    /**
     * 校验身份证号
     */
    @Override
    public int selectDDriverByIdCard(String idCard){
        return dDriverMapper.selectDDriverByIdCard(idCard);
    }

    /**
     * 校验手机号号是否重复
     */
    @Override
    public int selectDDriverByIdContact(String contact){
        return dDriverMapper.selectDDriverByIdContact(contact);
    }

    /**
     * 新增档案时新增文件
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertDDriverAndFile(DDriver dDriver){
        int result = 1;
        try {
            if (dDriver!= null) {
                result = this.insertDDriver(dDriver);

            if (dDriver.getFileUrl()!= null &&dDriver.getFileUrl().size() > 0) {
                List<SysFile> sysFileList = new ArrayList<>();
                for (String fileUrl:dDriver.getFileUrl()){
                    SysFile sysFile = new SysFile();
                    sysFile.setId(UUID.randomUUID().toString());
                    sysFile.setType(9L);
                    sysFile.setRelationId(dDriver.getId());
                    sysFile.setFilePath(fileUrl);
                    sysFile.setDeleteFlag(1L);
                    sysFile.setCreateUser(dDriver.getCreateUser());
                    sysFile.setCreateTime(new Date());
                    sysFileList.add(sysFile);
                }
                result = sysFileMapper.insertList(sysFileList);
            }
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    /**
     * 长途司机list
     * @return
     */
    @Override
    public List<DDriver> selectDDriverLongD(){
        return dDriverMapper.selectDDriverLongD();
    }

    /**
     * 短途司机list
     * @return
     */
    @Override
    public List<DDriver> selectDDriverShortD(){
        return dDriverMapper.selectDDriverShortD();
    }


    /**
     * 查出所有司机
     * @return
     */
    @Override
    public List<DDriver> selectDDriverAll(){
        return dDriverMapper.selectDDriverAll();
    }

    /**
     * 驾驶员档案列表
     * @param dDriver
     * @return
     */
    @Override
    public List<DDriver> selectDDriverListFile(DDriver dDriver){
        return dDriverMapper.selectDDriverListFile(dDriver);
    }


    /**
     * OTM-查出所有司机
     * @return
     */
    @Override
    public List<OTMDriverModel> selectOTMDriverAll(){
        return dDriverMapper.selectOTMDriverAll();
    }

    /**
     * @describe
     * @author DongCL
     * @date 2021-01-08 16:03
     * @param contact
     * @return DDriver
     */
    @Override
    public DDriver queryDriverByContact(String contact) {
        return dDriverMapper.queryDriverByContact(contact);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult login(DDriver dDriver) {

        try {
            if (StringUtils.isEmpty(dDriver.getContact()))
            {
                throw new CustomException("手机号必须填写");

            }
            if(!VerifyCodeUtil.isPhone(dDriver.getContact())) {
                throw new CustomException("请输入正确的手机号码");
            }
            // 查询用户信息
            DDriver dDriverData = dDriverMapper.queryDriverByContact(dDriver.getContact());
            if (dDriverData == null)
            {
                throw new CustomException("登录手机号：" + dDriver.getContact() + " 不存在");
            }
            if (2==dDriverData.getDisableStatus())
            {
                throw new CustomException("对不起，您的账号：" + dDriverData.getName() + " 已禁用");
            }
            if (StringUtils.isEmpty(dDriver.getPassword())){
                throw new CustomException("密码不能为空");
            }
            if (!SecurityUtils.matchesPassword(dDriver.getPassword(),dDriverData.getPassword())){
                throw new CustomException("密码错误，请重新输入");
            }
            LoginDriver loginDriver = new LoginDriver();
            loginDriver.setUserid(dDriverData.getId());
            loginDriver.setUsername(dDriverData.getName());

            //更新登录用户的设备类型和token
            if (StringUtils.isNotEmpty(dDriver.getToken())&& StringUtils.isNotEmpty(dDriver.getDeviceType())){
            dDriverData.setToken(dDriver.getToken());
            dDriverData.setDeviceType(dDriver.getDeviceType());
            dDriverMapper.updateDDriver(dDriverData);
            }

            Map<String, Object> token = driverTokenService.createToken(loginDriver);
            return AjaxResult.success(token);
        } catch (CustomException e) {
            throw e;
        }
    }

    /**
     * @describe App更新用户密码
     * @author DongCL
     * @date 2021-02-23 16:53
     * @param
     * @return
     */
    @Override
    public int updateDDriverPassword(String id, String encryptPassword) {
        return dDriverMapper.updateDDriverPassword(id,encryptPassword);
    }

    @Override
    public DDriver selectDDriverId(String name){
        return dDriverMapper.selectDDriverId(name);
    }

    @Override
    public List<DDriver> selectDDriverStaff() {
        return dDriverMapper.selectDDriverStaff();
    }

}
