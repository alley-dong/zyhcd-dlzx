package com.dlzx.pfservercar.service.impl;


import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.client.SystemClient;
import com.dlzx.pfservercar.domain.CInsuranceVehicleGoogds;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.Common.SysDictionary;
import com.dlzx.pfservercar.domain.DDriver;
import com.dlzx.pfservercar.domain.RFuelCost;
import com.dlzx.pfservercar.domain.SysExamineTask;
import com.dlzx.pfservercar.domain.SysFile;
import com.dlzx.pfservercar.domain.model.OTMVehicleModel;
import com.dlzx.pfservercar.mapper.CVehicleMapper;
import com.dlzx.pfservercar.mapper.CommonMapper.SysDictionaryCarMapper;
import com.dlzx.pfservercar.mapper.SysDictDataMapper;
import com.dlzx.pfservercar.mapper.SysFileMapper;
import com.dlzx.pfservercar.service.ICVehicleService;
import com.dlzx.pfservercar.service.ITTransportTaskService;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 车辆档案Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-11-24
 */
@Service
public class CVehicleServiceImpl implements ICVehicleService
{
    @Value("${esb-config.is-enable}")
    private boolean enable;

    @Autowired
    private CVehicleMapper cVehicleMapper;
    @Autowired
    private SysDictionaryCarMapper sysDictionaryMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysDictDataMapper sysDictDataCarMapper;
    @Autowired
    private SysFileMapper sysFileMapper;
    @Autowired
    private SystemClient systemClient;

    @Autowired
    private ITTransportTaskService transportTaskService;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;
    /**
     * 查询车辆档案
     * 
     * @param id 车辆档案ID
     * @return 车辆档案
     */
    @Override
    public CVehicle selectCVehicleById(String id)
    {
        //查找数据
        CVehicle cVehicle = cVehicleMapper.selectCVehicleById(id);

        BigDecimal addCost = new BigDecimal(
                cVehicle.getGpsCost()==null||"".equals(cVehicle.getGpsCost())?"0":cVehicle.getGpsCost())  //GSP
                .add(new BigDecimal(cVehicle.getOriginalPrice()==null||"".equals(cVehicle.getOriginalPrice())?"0":cVehicle.getOriginalPrice()) //原值
                        .add(new BigDecimal(cVehicle.getPurchaseTax()==null||"".equals(cVehicle.getPurchaseTax())?"0":cVehicle.getPurchaseTax()) // 购置税
                                .add(new BigDecimal(cVehicle.getInspectCost()==null||"".equals(cVehicle.getInspectCost())?"0":cVehicle.getInspectCost())))); //检车落籍费用
        cVehicle.setTurnCost(addCost.toString());
        if (cVehicle.getFuelType()!= null){
            String fuelTypeLable = sysDictDataCarMapper.selectDictLabel("c_vehicle_drive_fuel_type",cVehicle.getFuelType().toString());
            cVehicle.setFuelTypeLable(fuelTypeLable);
        }
        if (cVehicle.getDriveForm()!= null){
            String driveFormLable = sysDictDataCarMapper.selectDictLabel("c_vehicle_drive_form",cVehicle.getDriveForm().toString());
            cVehicle.setDriveFormLable(driveFormLable);
        }
        if (cVehicle.getColor()!= null){
            String colorName = sysDictDataCarMapper.selectDictLabel("vehicle_color_code",cVehicle.getColor());
            cVehicle.setColorName(colorName);
        }
        List<SysFile> sysFiles = sysFileMapper.selectSysFileByListId(cVehicle.getId());
        List<String> fileUrls = new ArrayList<>();
        for (SysFile sysFile:sysFiles){
            String file = sysFile.getFilePath();
            fileUrls.add(file);
        }
        cVehicle.setFileUrl(fileUrls);
        return cVehicle;
    }


    /**
     * 查询车辆档案列表
     *
     * @param cVehicle 车辆档案
     * @return 车辆档案
     */
    @Override
    public List<CVehicle> selectCVehicleList(CVehicle cVehicle)
    {
        return cVehicleMapper.selectCVehicleList(cVehicle);
    }

    @Override
    public String selectCVehicleIdByLicensePlate(String licensePlate) {
        return cVehicleMapper.selectCVehicleIdByLicensePlate(licensePlate);
    }

    @Override
    public List<CVehicle> selectCVehicleListAll()
    {
        return cVehicleMapper.selectCVehicleListAll();
    }

    @Override
    public List<CVehicle> selectCVehicleListCarNum(CVehicle cVehicle)
    {
        return cVehicleMapper.selectCVehicleListCarNum(cVehicle);
    }
// 主车车牌号
    @Override
    public List<CVehicle> selectCVehicleListCarZhuNum(CVehicle cVehicle)
    {
        return cVehicleMapper.selectCVehicleListCarZhuNum(cVehicle);
    }

    /**
     * 查询审批列表
     * @param cVehicle 车辆档案
     * @return
     */
    @Override
    public List<CVehicle> selectCVehicleAndCIV(CVehicle cVehicle) {
        return cVehicleMapper.selectCVehicleAndCIV(cVehicle);
    }

    /**
     * 新增车辆档案
     * 
     * @param cVehicle 车辆档案
     * @return 结果
     */
    @Override
    public int insertCVehicle(CVehicle cVehicle)
    {
        // yml文件配置是否开启
        if (enable) {
            // 调用OTM接口，将车辆信息同步给零件系统
            if (transportTaskService.OTMCarVehicle(cVehicle) <= 0) {
                throw new CustomException("OTM车辆传入失败");
            }
        }

        cVehicle.setCreateTime(DateUtils.getNowDate());
        //新增附件
        List<SysFile> sysFileList = new ArrayList<>();
        if (cVehicle.getFileUrl().size()>0) {
            sysFileMapper.delelteFilesByRelationId(cVehicle.getId());
            for (String url:cVehicle.getFileUrl()){
                SysFile sysFile = new SysFile();
                sysFile.setId(UUID.randomUUID().toString());
                sysFile.setType(9L);
                sysFile.setRelationId(cVehicle.getId());
                sysFile.setFilePath(url);
                sysFile.setDeleteFlag(1L);
                sysFile.setCreateUser(cVehicle.getCreateBy());
                sysFile.setCreateTime(new Date());
                sysFileList.add(sysFile);
            }
            int fileNum = sysFileMapper.insertList(sysFileList);
        }
        return cVehicleMapper.insertCVehicle(cVehicle);
    }

    /**
     * 修改车辆档案
     * 
     * @param cVehicle 车辆档案
     * @return 结果
     */
    @Override
    public int updateCVehicle(CVehicle cVehicle)
    {
        cVehicle.setUpdateTime(DateUtils.getNowDate());
        List<SysFile> sysFileList = new ArrayList<>();
        sysFileMapper.delelteFilesByRelationId(cVehicle.getId());
        if (cVehicle.getFileUrl() != null && cVehicle.getFileUrl().size()>0) {
            sysFileMapper.delelteFilesByRelationId(cVehicle.getId());
            for (String url:cVehicle.getFileUrl()){
                SysFile sysFile = new SysFile();
                sysFile.setId(UUID.randomUUID().toString());
                sysFile.setType(9L);
                sysFile.setRelationId(cVehicle.getId());
                sysFile.setFilePath(url);
                sysFile.setDeleteFlag(1L);
                sysFile.setCreateUser(cVehicle.getCreateBy());
                sysFile.setCreateTime(new Date());
                sysFileList.add(sysFile);
            }
            int fileNum = sysFileMapper.insertList(sysFileList);
        }
        return cVehicleMapper.updateCVehicle(cVehicle);
    }

    /**
     * 批量删除车辆档案
     * 
     * @param ids 需要删除的车辆档案ID
     * @return 结果
     */
    @Override
    public int deleteCVehicleByIds(String[] ids)
    {
        return cVehicleMapper.deleteCVehicleByIds(ids);
    }

    /**
     * 删除车辆档案信息
     * 
     * @param id 车辆档案ID
     * @return 结果
     */
    @Override
    public int deleteCVehicleById(String id)
    {
        CVehicle cVehicle = cVehicleMapper.selectCVehicleById(id);
        // 删除要将禁用状态改为已禁用
        cVehicle.setDeleteFlag(2L);
        return cVehicleMapper.deleteCVehicleById(id);
    }

    /**
     * 导入数据判断内容是否符合要求
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String,String> importVechicle(List<CVehicle> cVehicleList,Boolean isUpdateSupport, String sellId){
        Map<String, String> result = new HashMap<>();
        //判断数据流是否为空
        if (StringUtils.isNull(cVehicleList) || cVehicleList.size() == 0) {
            throw new CustomException("导入数据为空！");
        }
        //需要新增的数据
        List<CVehicle> insList = new ArrayList<>();
        //需要修改的数据
        List<CVehicle> updList = new ArrayList<>();
        //查出数据表所有记录
        List<CVehicle> cVehicleListAll = cVehicleMapper.selectCVehicleAll();
        List<SysDictionary> sysDictionaryList = sysDictionaryMapper.selectSysDictionaryList(new SysDictionary());
        List<SysDictionary> vehicleTypeAll = new ArrayList<>();
        List<SysDictionary> vehicleModelAll = new ArrayList<>();
        //取出字典库中车辆类别和车辆型号
        for (SysDictionary sysDictionary:sysDictionaryList){
            if (sysDictionary.getType()==1&&sysDictionary.getLevel()==1){
                vehicleTypeAll.add(sysDictionary);
            }
            if (sysDictionary.getType()==2&&sysDictionary.getLevel()==2){
                vehicleModelAll.add(sysDictionary);
            }
        }
        //创建错误信息对象
        /*StringBuilder errorInfo = new StringBuilder();*/
        StringBuilder errorInfoTypeName = new StringBuilder();
        StringBuilder errorInfoModelName = new StringBuilder();
        StringBuilder errorInfoLicense = new StringBuilder();
        StringBuilder errorInfoFrame = new StringBuilder();
        StringBuilder errorInfoCheckVehicleLiencse = new StringBuilder();
        StringBuilder errorInfoLasting = new StringBuilder();
        StringBuilder errorInfoWide = new StringBuilder();
        StringBuilder errorInfoHigh = new StringBuilder();
        StringBuilder errorInfoFuel = new StringBuilder();
        StringBuilder errorInfoTotalMass = new StringBuilder();
        StringBuilder errorInfoColor = new StringBuilder();

        StringBuilder errorInfoApproved = new StringBuilder();
        StringBuilder errorInfoPermit = new StringBuilder();
        StringBuilder errorInfoIsInspect = new StringBuilder();
        StringBuilder errorInfoForm = new StringBuilder();

        boolean hasError = false;
        int countFlag = 0;

        //判断是否符合要求
        try {
            for (int i = 0; i < cVehicleList.size(); i++) {
                boolean f = cVehicleList.get(i).equals();

                if (f) {
                    break;
                }
                countFlag = i+1;
            }
            for (int i = 0; i < countFlag; i++){
                CVehicle cve = cVehicleList.get(i);

                cve.setDeleteFlag(Long.valueOf(1));
                String vehicleTypeName = cve.getVehicleTypeName();
                //数据判空
                if (StringUtils.isEmpty(vehicleTypeName)){
                    errorInfoTypeName.append((i + 2) + "行");
                    hasError = true;
                    break;
                }

               /* String vehicelType = cve.getVehicleType();
                String vehicelModel = cve.getVehicleModel();*/
                //判断车辆类型和车辆型号是否在在dictionary里
                if (vehicleTypeAll != null){
                SysDictionary sysDictionaryCheckType = vehicleTypeAll.stream().filter(o ->(Objects.nonNull(o.getName()))&&(Objects.equals(o.getName(),vehicleTypeName))).findFirst().orElse(null);
                if (null == sysDictionaryCheckType){
                    errorInfoTypeName.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                else {
                    cve.setVehicleType(sysDictionaryCheckType.getId());
                }
                }
                String vehicleModelName = cve.getVehicleModelName();
                if (StringUtils.isEmpty(vehicleModelName)){
                    errorInfoModelName.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                if (vehicleModelAll != null){
                SysDictionary sysDictionaryCheckModel = vehicleModelAll.stream().filter(o -> (Objects.nonNull(o.getName()))&&(Objects.equals(o.getName(),vehicleModelName))).findFirst().get();
                if (null == sysDictionaryCheckModel){
                    errorInfoModelName.append((i + 2) + "行");
                   hasError = true;
                   break;
                }
                else {
                    cve.setVehicleModel(sysDictionaryCheckModel.getId());
                }
                }
                String licensePlate = cve.getLicensePlate();
                String frameNumber = cve.getFrameNumber();
                if (StringUtils.isEmpty(licensePlate)){
                    errorInfoLicense.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                if (StringUtils.isEmpty(frameNumber)){
                    errorInfoFrame.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                if (StringUtils.isEmpty(cve.getLasting())){
                    errorInfoLasting.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                if (StringUtils.isEmpty(cve.getHigh())){
                    errorInfoHigh.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                if (StringUtils.isEmpty(cve.getWide())){
                    errorInfoWide.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                if (StringUtils.isEmpty(cve.getColor())){
                    errorInfoColor.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                    String colorName = sysDictDataCarMapper.querySysDictLabelData(cve.getColor(),"vehicle_color_code");
                    if(colorName != null){
                        cve.setColor(colorName);
                    }
                    else {
                        errorInfoColor.append((i + 2) + "行");
                        hasError = true;
                        break;
                    }
                if (StringUtils.isNotEmpty(cve.getDriveFormName())){
                    String driveFormLable = sysDictDataCarMapper.querySysDictLabelData(cve.getDriveFormName(),"c_vehicle_drive_form");
                    if (driveFormLable != null){
                        cve.setDriveForm(Long.valueOf(driveFormLable));
                    }
                    else {
                        errorInfoForm.append((i + 2) + "行");
                        hasError = true;
                        break;
                    }
                }
                if (StringUtils.isEmpty(cve.getFuelType())){
                    errorInfoFuel.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                else {
                    String fuelTypeLable = sysDictDataCarMapper.querySysDictLabelData(cve.getFuelType().toString(),"c_vehicle_drive_fuel_type");
                    if (fuelTypeLable != null){
                        cve.setFuelType(fuelTypeLable);
                    }
                    else {
                        errorInfoFuel.append((i + 2) + "行");
                        hasError = true;
                        break;
                    }
                }

                if (StringUtils.isEmpty(cve.getTotalMass())){
                    errorInfoTotalMass.append((i + 2) + "行");
                    hasError = true;
                    break;
                }

                if (StringUtils.isEmpty(cve.getApprovedLoad())){
                    errorInfoApproved.append((i + 2) + "行");
                    hasError = true;
                    break;
                }

                if (StringUtils.isEmpty(cve.getPermit())){
                    errorInfoPermit.append((i + 2) + "行");
                    hasError = true;
                    break;
                }

                if (cve.getIsInspect() == null){
                    errorInfoIsInspect.append((i + 2) + "行");
                    hasError = true;
                    break;
                }

                //判断车牌号是否重复
                List<CVehicle> cVehicleListCheckLicensePlate = cVehicleListAll.stream().filter(o ->(o.getLicensePlate() != null) &&(o.getLicensePlate().equals(licensePlate))).collect(Collectors.toList());
                if (hasError) {
                    continue;
                }
                if (cVehicleListCheckLicensePlate.size() != 0){
                    errorInfoCheckVehicleLiencse.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                else {
                    //新增
                    cve.setId(UUID.randomUUID().toString());
                    LoginUser userInfo = tokenService.getLoginUser();
                    cve.setCreateBy(userInfo.getUsername());
                    cve.setCreateTime(new Date());
                    cve.setDisable(1L);
                    insList.add(cve);
                }
            }
            //提示错误信息
            String strOne = errorInfoTypeName.length() > 0 ? "导入的第" + errorInfoTypeName.toString() + "的位置车辆类型不存在;" : "";
            String strTwo = errorInfoModelName.length() > 0 ? "导入的第" + errorInfoModelName.toString() + "的位置车辆型号不存在;" : "";
            String strThird = errorInfoCheckVehicleLiencse.length() > 0 ? "导入的第" + errorInfoCheckVehicleLiencse.toString() + "的位置车牌号已存在;" : "";
            String strFour = errorInfoFrame.length() > 0 ? "导入的第" + errorInfoFrame.toString() + "的位置车架号没有填写;" : "";
            String strLasting = errorInfoLasting.length() > 0 ? "导入的第" + errorInfoLasting.toString() + "的位置长没有填写;" : "";
            String strHigh = errorInfoHigh.length() > 0 ? "导入的第" + errorInfoHigh.toString() + "的位置宽没有填写;" : "";
            String strWide = errorInfoWide.length() > 0 ? "导入的第" + errorInfoWide.toString() + "的位置高没有填写;" : "";
            String strColor = errorInfoColor.length() > 0 ? "导入的第" + errorInfoColor.toString() + "的位置颜色没有填写;" : "";
            String strFuel = errorInfoFuel.length() > 0 ? "导入的第" + errorInfoFuel.toString() + "的位置燃料种类没有填写;" : "";
            String strTotalMass = errorInfoTotalMass.length() > 0 ? "导入的第" + errorInfoTotalMass.toString() + "的位置车总质量没有填写;" : "";
            String strApproved = errorInfoApproved.length() > 0 ? "导入的第" + errorInfoApproved.toString() + "的位置核定载质量没有填写;" : "";
            String strPermit = errorInfoPermit.length() > 0 ? "导入的第" + errorInfoPermit.toString() + "的位置道路运输许可证没有填写;" : "";
            String strIsInspect = errorInfoIsInspect.length() > 0 ? "导入的第" + errorInfoIsInspect.toString() + "的位置是否需要定检没有填写;" : "";
            String strForm = errorInfoForm.length() > 0 ? "导入的第" + errorInfoForm.toString() + "的位置驱动形式没有填写;" : "";

            if (hasError) {
                String test = ((strOne == null || "".equals(strOne))?"":(strOne +"<br>"))+ ((strTwo==null || "".equals(strTwo))?"":(strTwo +"<br>")) + ((strThird==null || "".equals(strThird))?"":(strThird +"<br>")) + ((strFour==null || "".equals(strFour))?"":(strFour +"<br>"))+ ((strLasting==null || "".equals(strLasting))?"":(strLasting +"<br>"))+ ((strHigh==null || "".equals(strHigh))?"":(strHigh +"<br>"))+ ((strWide==null || "".equals(strWide))?"":(strWide +"<br>"))
                        + ((strColor==null || "".equals(strColor))?"":(strColor +"<br>"))+ ((strFuel==null || "".equals(strFuel))?"":(strFuel +"<br>"))+ ((strTotalMass==null || "".equals(strTotalMass))?"":(strTotalMass +"<br>"))+ ((strApproved==null || "".equals(strApproved))?"":(strApproved +"<br>"))
                        + ((strPermit==null || "".equals(strPermit))?"":(strPermit +"<br>"))+ ((strIsInspect==null || "".equals(strIsInspect))?"":(strIsInspect +"<br>"))+ ((strForm==null || "".equals(strForm))?"":(strForm +"<br>"));

                result.put("code", "-1");
                result.put("msg", test);
                return result;
            }
            //增入数据表中
            if (insList.size() > 0) {
                insList.forEach(r -> {
                    cVehicleMapper.insertCVehicle(r);
                });
            }
        }
        catch (Exception e){
            result.put("code", "-1");
            result.put("msg", "系统异常");
            return result;
        }
        result.put("code", "0");
        result.put("msg", "导入成功" );
        return result;
    }

    @Override
    public int selectCVehicleByLicensePlate(String licensePlate){
        return cVehicleMapper.selectCVehicleByLicensePlate(licensePlate);
    }

    /**
     * 车检记录list
     */
    @Override
    public List<CVehicle> selectCVehicleRecordList(CVehicle cVehicle){
        return cVehicleMapper.selectCVehicleRecordList(cVehicle);
    }

    @Override
    public List<CVehicle> selectCVehicleIsInspect() {
        return cVehicleMapper.selectCVehicleIsInspect();
    }

    /**
     * 车辆状态
     */
    @Override
    public List<CVehicle> selectCVehicleStatusList(CVehicle cVehicle){

        return cVehicleMapper.selectCVehicleStatusList(cVehicle);
    }

    /**
     * 根据车牌号查询车辆信息
     * */
    @Override
    public CVehicle queryCVehicleByLicensePlate(String licensePlate) {
        return cVehicleMapper.queryCVehicleByLicensePlate(licensePlate);
    }

    /**
     * 查询充电卡车辆列表
     * */
    @Override
    public List<CVehicle> selectCVehicleRechargeList(CVehicle cVehicle) {
        return cVehicleMapper.selectCVehicleRechargeList(cVehicle);
    }

    /**
     * 充电卡导入
     * */
    @Override
    public AjaxResult importFuelRechargeCard(List<CVehicle> cVehicles, String username) {

        List<CVehicle> cVehicleListAll = cVehicleMapper.selectCVehicleListAll();
        for (CVehicle cVehicle : cVehicles) {
            cVehicle.setUpdateBy(username);
            cVehicle.setUpdateTime(new Date());
            List<CVehicle> collect = cVehicleListAll.stream()
                    .filter(c -> c.getLicensePlate().equals(cVehicle.getLicensePlate()))
                    .collect(Collectors.toList());
            if (collect.size()<=0){
                return AjaxResult.error(cVehicle.getLicensePlate()+"车牌号不存在");
            }
        }
        int result = cVehicleMapper.updateList(cVehicles);
        return AjaxResult.success("成功导入"+result+"条数据");
    }

    /**
     * OTM-车队车辆基础数据
     * */
    @Override
    public List<OTMVehicleModel> selectOTMVehicleListAll() {
        return cVehicleMapper.selectOTMVehicleListAll();
    }

    /**
     * OTM-车队根据车排号查询车辆基础数据
     * */
    @Override
    public List<CVehicle> queryCVehicleByOTMLicensePlate(List<String> equipmentnumber) {
        return cVehicleMapper.queryCVehicleByOTMLicensePlate(equipmentnumber);
    }

    /**
     * OTM-车队根据司机手机号查询司机基础数据
     * */
    @Override
    public DDriver queryCDriverByOTMPhone(String driverNumber) {
        return cVehicleMapper.queryCDriverByOTMPhone(driverNumber);
    }

    /**
     * @describe 首页统计数据
     * @author DongCL
     * @date 2021-01-13 11:55
     * @return data
     */
    @Override
    public Map<String, Object> queryIndexStatistics() {
        return cVehicleMapper.queryIndexStatistics();
    }

//    /**
//     * @describe 首页待办
//     * @author DongCL
//     * @date 2021-01-13 11:55
//     * @return data
//     */
//    @Override
//    public List<SysExamineTask> queryIndexExamineTaskList(Long userid) {
//        return systemClient.queryExamineTaskList(userid);
//    }

    @Override
    public List<CVehicle> selectIBookList(CVehicle cVehicle){
        return cVehicleMapper.selectIBookList(cVehicle);
    }

    /**
     * @describe 根据卡号查询车辆
     * @author DongCL
     * @date 2021-01-29 14:11
     * @param
     * @return
     */
    @Override
    public List<CVehicle> queryCardNumber(String cardNumber) {
        return cVehicleMapper.queryCardNumber(cardNumber);
    }

    @Override
    public int updateCardCVehicle(CVehicle cVehicle) {
        return cVehicleMapper.updateCardCVehicle(cVehicle);
    }

    @Override
    public int updateCDisable(CVehicle cVehicle){
        return cVehicleMapper.updateCVehicle(cVehicle);
    }

    /**
     * @describe 修改车辆数据（ESB使用）
     * @author DongCL
     * @date 2021-03-04 12:39
     * @param
     * @return
     */
    @Override
    public int updateCVehicleAndESBData(CVehicle cVehicle) {

        // yml文件配置是否开启
        if (enable) {
            // 调用OTM接口，将车辆信息同步给零件系统
            if (transportTaskService.OTMCarVehicle(cVehicle) <= 0) {
                throw new CustomException("OTM传入失败");
            }
        }
        cVehicle.setUpdateTime(DateUtils.getNowDate());
        List<SysFile> sysFileList = new ArrayList<>();
        sysFileMapper.delelteFilesByRelationId(cVehicle.getId());
        if (cVehicle.getFileUrl() != null && cVehicle.getFileUrl().size()>0) {
            sysFileMapper.delelteFilesByRelationId(cVehicle.getId());
            for (String url:cVehicle.getFileUrl()){
                SysFile sysFile = new SysFile();
                sysFile.setId(UUID.randomUUID().toString());
                sysFile.setType(9L);
                sysFile.setRelationId(cVehicle.getId());
                sysFile.setFilePath(url);
                sysFile.setDeleteFlag(1L);
                sysFile.setCreateUser(cVehicle.getCreateBy());
                sysFile.setCreateTime(new Date());
                sysFileList.add(sysFile);
            }
            int fileNum = sysFileMapper.insertList(sysFileList);
        }
        return cVehicleMapper.updateCVehicle(cVehicle);
    }

    /**
     * @describe 获取车辆列表
     * @author DongCL
     * @date 2021-03-09 15:10
     * @param
     * @return
     */
    @Override
    public List<CVehicle> selectCVehicleNoDisableList(CVehicle cVehicle) {
        return cVehicleMapper.selectCVehicleNoDisableList(cVehicle);
    }
}
