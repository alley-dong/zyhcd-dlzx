package com.dlzx.pfservercar.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.domain.SysExamineConfigParams;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.client.SystemClient;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.Common.SubmitVo;
import com.dlzx.pfservercar.domain.RGarageCar;
import com.dlzx.pfservercar.domain.RGarageRepairItem;
import com.dlzx.pfservercar.domain.RRepairDetailed;
import com.dlzx.pfservercar.domain.RRepairItemDate;
import com.dlzx.pfservercar.domain.RRepairOutExcel;
import com.dlzx.pfservercar.domain.ReviewSubmit;
import com.dlzx.pfservercar.domain.ReviewSubmitR;
import com.dlzx.pfservercar.domain.SysFile;
import com.dlzx.pfservercar.mapper.CVehicleMapper;
import com.dlzx.pfservercar.mapper.RGarageCarMapper;
import com.dlzx.pfservercar.mapper.RGarageRepairItemMapper;
import com.dlzx.pfservercar.mapper.RRepairDetailedMapper;
import com.dlzx.pfservercar.mapper.RRepairItemDateMapper;
import com.dlzx.pfservercar.mapper.SysDictDataMapper;
import com.dlzx.pfservercar.mapper.SysFileMapper;
import com.dlzx.system.api.model.LoginUser;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.RRepairMapper;
import com.dlzx.pfservercar.domain.RRepair;
import com.dlzx.pfservercar.service.IRRepairService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 维修保养-维修保养单Service业务层处理
 *
 * @author yangluhe
 * @date 2020-12-04
 */
@Service
public class RRepairServiceImpl implements IRRepairService {
    @Autowired
    private RRepairMapper rRepairMapper;
    @Autowired
    private SystemClient systemClient;
    @Autowired
    private RRepairItemDateMapper rRepairItemDateMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysDictDataMapper sysDictDataCarMapper;
    @Autowired
    private CVehicleMapper cVehicleMapper;
    @Autowired
    private RRepairDetailedMapper rRepairDetailedMapper;
    @Autowired
    private RGarageRepairItemMapper rGarageRepairItemMapper;
    @Autowired
    private RGarageCarMapper rGarageCarMapper;
    @Autowired
    private DriverTokenService driverTokenService;
    @Autowired
    private SysFileMapper sysFileMapper;

    /**
     * 查询维修保养-维修保养单
     *
     * @param id 维修保养-维修保养单ID
     * @return 维修保养-维修保养单
     */
    @Override
    public RRepair selectRRepairById(String id) {
        RRepair rRepair = rRepairMapper.selectRRepairById(id);
        if (!StringUtils.isNull(rRepair.getRoadCondition())){
            String road = sysDictDataCarMapper.selectDictLabel("road_condition", rRepair.getRoadCondition().toString());
            rRepair.setRoad(road);
        }
        // 维修保养服务站附件
        List<SysFile> files = sysFileMapper.queryFilesByRelationId(id,"16");
        if (files.size()>0){
            List<String> list = files.stream().map(SysFile::getFilePath).collect(Collectors.toList());
            rRepair.setFileList(list);
        }
        if (!StringUtils.isNull(rRepair.getWeatherCondition())){
            String weather = sysDictDataCarMapper.selectDictLabel("weather_condition", rRepair.getWeatherCondition().toString());
            rRepair.setWeather(weather);
        }
        List<RRepairItemDate> rRepairItemDateList = rRepairItemDateMapper.selectRRepairItemDateByRepairId(id);
        if (rRepairItemDateList.size() == 0) {
            return rRepair;
        } else {
            List<RRepairItemDate> rRepairItemDates = rRepairItemDateMapper.queryRepairItemDateList(id);
            for (RRepairItemDate rRepairItemDate : rRepairItemDates) {
                rRepairItemDate.setFileList(rRepairItemDateList.stream()
                        .filter(r->r.getItemId().equals(rRepairItemDate.getItemId()))
                        .map(RRepairItemDate::getFiles)
                        .collect(Collectors.toList()));
            }
            rRepair.setRepairItemDate(rRepairItemDates);
            return rRepair;
        }
    }

    /**
     * 查询维修保养-维修保养单列表
     *
     * @param rRepair 维修保养-维修保养单
     * @return 维修保养-维修保养单
     */
    @Override
    public List<RRepair> selectRRepairList(RRepair rRepair) {
        return rRepairMapper.selectRRepairList(rRepair);
    }

    /**
     * 新增维修保养-维修保养单
     *
     * @param rRepair 维修保养-维修保养单
     * @return 结果
     */
    @Override
    public int insertRRepair(RRepair rRepair) {
        rRepair.setCreateTime(DateUtils.getNowDate());
        return rRepairMapper.insertRRepair(rRepair);
    }

    /**
     * 修改维修保养-维修保养单
     *
     * @param rRepair 维修保养-维修保养单
     * @return 结果
     */
    @Override
    public int updateRRepair(RRepair rRepair) {
        rRepair.setUpdateTime(DateUtils.getNowDate());
        return rRepairMapper.updateRRepair(rRepair);
    }

    /**
     * @describe 更新维修服务站附件
     * @author DongCL
     * @date 2021-04-27 17:09
     * @param
     * @return
     */
    @Override
    public int updateRRepairUpload(RRepair rRepair) {
        rRepair.setUpdateTime(DateUtils.getNowDate());

        try {
            Date nowDate = DateUtils.getNowDate();
            List<SysFile> files = new ArrayList<>();
            if (StringUtils.isNotNull(rRepair.getFileUrls())){
                this.uploadPhoto(files,rRepair,nowDate);
            }
            if (files.size()>0){
                sysFileMapper.insertList(files);
            }
        } catch (Exception e) {
            throw e;
        }
        return rRepairMapper.updateRRepair(rRepair);
    }

    /**
     * 批量删除维修保养-维修保养单
     *
     * @param ids 需要删除的维修保养-维修保养单ID
     * @return 结果
     */
    @Override
    public int deleteRRepairByIds(String[] ids) {
        return rRepairMapper.deleteRRepairByIds(ids);
    }

    /**
     * 删除维修保养-维修保养单信息
     *
     * @param id 维修保养-维修保养单ID
     * @return 结果
     */
    @Override
    public int deleteRRepairById(String id) {
        return rRepairMapper.deleteRRepairById(id);
    }

    /**
     * 维修订单管理（维修站申请）
     */
    @Override
    public List<RRepair> selectRRepairDriverPass(RRepair rRepair) {
        return rRepairMapper.selectRRepairDriverPass(rRepair);
    }

    /**
     * 维修保养管理（维修站申请审批）
     */
    @Override
    public List<RRepair> selectRRepairStationReview(RRepair rRepair) {
        return rRepairMapper.selectRRepairStationReview(rRepair);
    }

    /**
     * 台账
     */
    @Override
    public List<RRepair> selectRRepairBook(RRepair rRepair) {
        return rRepairMapper.selectRRepairBook(rRepair);
    }

    /**
     * 维修保养记录AppList
     */
    @Override
    public List<RRepair> selectRRepairAppList(RRepair rRepair) {
        return rRepairMapper.selectRRepairAppList(rRepair);
    }

    /**
     * 审核
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxResult checkRRepair(ReviewSubmitR reviewSubmit) {
        // update by 4.16 如果订单未提交，在首页不能对维修服务站进行审批
        RRepair rRepair = rRepairMapper.selectRRepairById(reviewSubmit.getRelationId());
        if (rRepair != null && rRepair.getStatus()==2){
            if (rRepair.getGarageStatus()==1){
                return AjaxResult.error("请先提交维修保养订单！提交后进行审批");
            }
        }
        ReviewSubmit reviewSubmit1 = new ReviewSubmit();
        reviewSubmit1.setUserId(reviewSubmit.getUserId());
        reviewSubmit1.setReviewRemarks(reviewSubmit.getReviewRemarks());
        reviewSubmit1.setRelationId(reviewSubmit.getRelationId());
        reviewSubmit1.setStatus(reviewSubmit.getStatus());
        Map map = systemClient.reviewCommon(reviewSubmit1);
        // 判断返回结构-是否需要操作业务表
        int code = (int) map.get("code");
        if (code == 3 || code == 4) {
            LoginUser userInfo = tokenService.getLoginUser();
            // 审核校验
            RRepair rRepairOne = rRepairMapper.selectRRepairById(reviewSubmit.getRelationId());
            rRepairOne.setStatus(code == 3 ? 2L : 3L);
            if (rRepairOne.getStatus() == 2L) {
                rRepairOne.setRepairStatus(2L);
            }
            rRepairOne.setReviewRemarks(reviewSubmit.getReviewRemarks());
            rRepairOne.setReviewTime(new Date());
            rRepairOne.setReviewUser(userInfo.getUsername());
            if (rRepairOne.getIsOther() == 1) {
                rRepairOne.setGarageStatus(3L);
            }
            int result = this.updateRRepair(rRepairOne);
            if (code == 3) {
                systemClient.adminSubmitCommon(8L, rRepairOne.getId(),rRepairOne.getDriverId(),rRepairOne.getDriverName());
            }
            if (result <= 0) {
                return AjaxResult.error("操作失败");
            }
        }
        //返回msg
        String msg = (String) map.get("msg");
        if (code == 1) {
            return AjaxResult.error(msg);
        }
        if (StringUtils.isNotEmpty(reviewSubmit.getGarageName())) {
            rRepair.setGarageName(reviewSubmit.getGarageName());
            rRepair.setGarageId(reviewSubmit.getGarageId());
            RGarageCar rGarageCar = rGarageCarMapper.selectRGarageCarById(reviewSubmit.getGarageId());
            if (rGarageCar!= null && rGarageCar.getIsFixed() == 2L){
                rRepair.setGarageStatus(3L);
                //systemClient.isFixedUpdate(2L,new Date(),rRepair.getId());
            }
        }
        if (code == 3) {
            RGarageCar rGarageCar = rGarageCarMapper.selectRGarageCarById(rRepair.getGarageId());
            if (rGarageCar!= null && rGarageCar.getIsFixed() == 2L){
                systemClient.isFixedUpdate(2L,new Date(),rRepair.getId());
            }
            if (rRepair.getType() == 1L) {
                CVehicle cVehicle = cVehicleMapper.selectCVehicleById(rRepair.getMainVehicleId());
                cVehicle.setVehicleStatus(2L);
                cVehicleMapper.updateCVehicle(cVehicle);
            }
            if (rRepair.getType() == 2L) {
                CVehicle cVehicle = cVehicleMapper.selectCVehicleById(rRepair.getHangVehicleId());
                cVehicle.setVehicleStatus(2L);
                cVehicleMapper.updateCVehicle(cVehicle);
            }
        }
        rRepair.setReviewTime(new Date());
        rRepair.setReviewUser(tokenService.getLoginUser().getUsername());
        this.updateRRepair(rRepair);
        return AjaxResult.success(msg);
    }

    /**
     * 审核
     */
    @Override
    public AjaxResult checkGarageRRepair(ReviewSubmit reviewSubmit) {
        // update by 4.16 如果订单未提交，在首页不能对维修服务站进行审批
        RRepair rRepair = rRepairMapper.selectRRepairById(reviewSubmit.getRelationId());
        if (rRepair != null && rRepair.getStatus()==2){
            if (rRepair.getGarageStatus()==1){
                return AjaxResult.error("请先提交维修保养订单！提交后进行审批");
            }
        }
        Map map = systemClient.reviewCommon(reviewSubmit);
        // 判断返回结构-是否需要操作业务表
        int code = (int) map.get("code");
        if (code == 3 || code == 4) {
            //获取当前操作人员信息
            LoginUser userInfo = tokenService.getLoginUser();
            RRepair rRepairOne = rRepairMapper.selectRRepairById(reviewSubmit.getRelationId());
            rRepairOne.setGarageStatus(code == 3 ? 3L : 4L);
            rRepairOne.setGarageReviewRemarks(reviewSubmit.getReviewRemarks());
            rRepairOne.setGarageReviewTime(new Date());
            rRepairOne.setGarageReviewUser(userInfo.getUsername());
            int result = this.updateRRepair(rRepairOne);
            if (result <= 0) {
                return AjaxResult.error("操作失败");
            }
        }
        if (code == 3) {
            List<RGarageRepairItem> rGarageRepairItemList = new ArrayList<>();
            List<RRepairDetailed> rRepairDetailedList = rRepairDetailedMapper.selectRRepairDetailedIsNewList(rRepair.getId());
            if (rRepairDetailedList.size() > 0) {
                for (RRepairDetailed rRepairDetailed : rRepairDetailedList) {
                    RGarageRepairItem rGarageRepairItem = new RGarageRepairItem();
                    rGarageRepairItem.setId(UUID.randomUUID().toString());
                    rGarageRepairItem.setGarageId(rRepairDetailed.getGarageId());
                    rGarageRepairItem.setVehicleModel(rRepairDetailed.getVehicleModel());
                    rGarageRepairItem.setVehicleModelName(rRepairDetailed.getVehicleModelName());
                    rGarageRepairItem.setUnitPrice(rRepairDetailed.getUnitPrice());
                    rGarageRepairItem.setUnit(rRepairDetailed.getUnit());
                    rGarageRepairItem.setName(rRepairDetailed.getName());
                    rGarageRepairItem.setDuration(rRepairDetailed.getDuration());
                    rGarageRepairItem.setDeleteFlag(1L);
                    rGarageRepairItem.setCreateBy(tokenService.getLoginUser().getUsername());
                    rGarageRepairItem.setCreateTime(new Date());
                    rGarageRepairItemList.add(rGarageRepairItem);
                }
                rGarageRepairItemList.forEach(r -> {
                    rGarageRepairItemMapper.insertRGarageRepairItem(r);
                });
            }
        }
        //返回msg
        String msg = (String) map.get("msg");
        if (code == 1) {
            return AjaxResult.error(msg);
        }
        return AjaxResult.success(msg);
    }

    /**
     * 新增数据时审批任务表填入数据
     */
    @Override
    public int submit(SubmitVo submitVo) {
        return systemClient.submitCommon(new SysExamineConfigParams(submitVo.getType(),submitVo.getRelationId(),driverTokenService.getLoginDriver().getUserid(),driverTokenService.getLoginDriver().getUsername(),submitVo.getSysExamineConfigProcessList()));
    }

    /**
     * 事务回滚新增
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertAffair(RRepair rRepair) {
        int result = 1;
        try {
            if (rRepair != null) {
                if (rRepair.getIsOther()==1L){
                    rRepair.setGarageStatus(3L);
                }

                if (0 == systemClient.submitCommon(new SysExamineConfigParams(7L,rRepair.getId(),rRepair.getDriverId(),driverTokenService.getLoginDriver().getUsername(),rRepair.getSysExamineConfigProcessList()))) {
                    rRepair.setStatus(2L);
                    rRepair.setRepairStatus(2L);
                    rRepair.setGarageStatus(2L);
                } else {
                    rRepair.setStatus(1L);
                    rRepair.setGarageStatus(1L);
                }
                result = this.insertRRepair(rRepair);
                if (rRepair.getRepairItemDate() != null && rRepair.getRepairItemDate().size() > 0) {
                    result = rRepairItemDateMapper.insertRRepairItemDateBatch(
                            rRepair.getRepairItemDate());
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    /**
     * 修改
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAffair(RRepair rRepair) {
        int result = 1;
        try {
            if (rRepair != null) {
                if (rRepair.getIsOther() == 1L){
                    rRepair.setGarageStatus(3L);
                }
                int i = systemClient.submitCommon(new SysExamineConfigParams(7L,rRepair.getId(),rRepair.getDriverId(),rRepair.getDriverName(),rRepair.getSysExamineConfigProcessList()));
                if (i == 0) {
                    rRepair.setStatus(2L);
                }
                result = this.updateRRepair(rRepair);
                if (rRepair.getRepairItemDate() != null && rRepair.getRepairItemDate().size() > 0) {
                    rRepairItemDateMapper.deleteRRepairItemDateBatch(rRepair.getId());
                    List<RRepairItemDate> rRepairItemDateList = new ArrayList<>();
                    for (RRepairItemDate rRepairItemDate : rRepair.getRepairItemDate()) {
                        for (String files : rRepairItemDate.getFileList()) {
                            RRepairItemDate repairItemDate = new RRepairItemDate();
                            repairItemDate.setId(UUID.randomUUID().toString());
                            repairItemDate.setRepairId(rRepair.getId());
                            repairItemDate.setFiles(files);
                            repairItemDate.setItemId(rRepairItemDate.getItemId());
                            repairItemDate.setItemName(rRepairItemDate.getItemName());
                            repairItemDate.setRemarks(rRepairItemDate.getRemarks());
                            rRepairItemDateList.add(repairItemDate);
                        }
                    }
                    rRepair.setRepairItemDate(rRepairItemDateList);
                    result = rRepairItemDateMapper.insertRRepairItemDateBatch(rRepair.getRepairItemDate());
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    /**
     * @describe 维保导出
     * @author DongCL
     * @date 2021-05-06 13:47
     * @param
     * @return
     */
    @Override
    public List<RRepairOutExcel> queryRRepairImport(RRepair rRepair) {
        return rRepairMapper.queryRRepairImport(rRepair);
    }

    private List<SysFile> uploadPhoto(List<SysFile>sysFiles, RRepair rRepair, Date nowDate){
        // 插入附件表
        for (String file : rRepair.getFileUrls()) {
            SysFile sysFile = new SysFile();
            sysFile.setId(UUID.randomUUID()+"");
            sysFile.setFilePath(file);
            sysFile.setRelationId(rRepair.getId());
            sysFile.setCreateUser(rRepair.getCreateBy());
            sysFile.setCreateTime(nowDate);
            // 类型(1,事故照片2,事故其他 3,考勤附件 4,客户合同附件 5供应商合同附件 6供应商评价附件 7交通违章图片 8违章处理图片 9驾驶员档案附件 10车辆档案附件 11保险保单照片 12.任务装货照片 13任务卸货照片 14任务异常情况照片 15培训 16 维修服务站附件)
            sysFile.setType(16L);
            sysFiles.add(sysFile);
        }
        return sysFiles;
    }
}
