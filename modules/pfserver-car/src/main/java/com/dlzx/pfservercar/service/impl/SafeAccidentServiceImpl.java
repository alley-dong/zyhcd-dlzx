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
import com.dlzx.pfservercar.client.SystemClient;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.ReviewSubmit;
import com.dlzx.pfservercar.domain.SysFile;
import com.dlzx.pfservercar.mapper.CVehicleMapper;
import com.dlzx.pfservercar.mapper.SysDictDataMapper;
import com.dlzx.pfservercar.mapper.SysFileMapper;
import com.dlzx.pfservercar.service.IDDriverMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.SafeAccidentMapper;
import com.dlzx.pfservercar.domain.SafeAccident;
import com.dlzx.pfservercar.service.ISafeAccidentService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安全管理-事故Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-08
 */
@Service
public class SafeAccidentServiceImpl implements ISafeAccidentService 
{
    @Autowired
    private SafeAccidentMapper safeAccidentMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Autowired
    private CVehicleMapper cVehicleMapper;

    @Autowired
    private SysFileMapper sysFileMapper;

    @Autowired
    private SystemClient systemClient;

    @Autowired
    private IDDriverMsgService idDriverMsgService;

    /**
     * 查询安全管理-事故
     * 
     * @param id 安全管理-事故ID
     * @return 安全管理-事故
     */
    @Override
    public SafeAccident selectSafeAccidentById(String id)
    {
        SafeAccident safeAccident = safeAccidentMapper.selectSafeAccidentById(id);
        safeAccident.setStatusEnum(sysDictDataMapper.selectDictLabel("safe_accident_status",safeAccident.getStatus().toString()));

        List<SysFile> accidentFileList = sysFileMapper.queryFilesByRelationId(id,"1");
        List<SysFile> partsFilesList = sysFileMapper.queryFilesByRelationId(id,"2");
        if (accidentFileList.size()>0){
            List<String> list = accidentFileList.stream().map(SysFile::getFilePath).collect(Collectors.toList());
            safeAccident.setAccidentFileList(list);
        }
        if (partsFilesList.size()>0){
            List<String> lists = partsFilesList.stream().map(SysFile::getFilePath).collect(Collectors.toList());
            safeAccident.setPartsFilesList(lists);
        }
        return safeAccident;
    }

    /**
     * 查询安全管理-事故列表
     * 
     * @param safeAccident 安全管理-事故
     * @return 安全管理-事故
     */
    @Override
    public List<SafeAccident> selectSafeAccidentList(SafeAccident safeAccident)
    {
        return safeAccidentMapper.selectSafeAccidentList(safeAccident);
    }


    @Override
    public List<SafeAccident> selectSafeAccidentListVehName(SafeAccident safeAccident)
    {
        return safeAccidentMapper.selectSafeAccidentListVehName(safeAccident);
    }

    @Override
    public List<SafeAccident> selectSafeAccidentListVehNameTu(SafeAccident safeAccident)
    {
        return safeAccidentMapper.selectSafeAccidentListVehNameTu(safeAccident);
    }

    @Override
    public List<SafeAccident> selectSafeAccidentListVehNameAll(SafeAccident safeAccident)
    {
        return safeAccidentMapper.selectSafeAccidentListVehNameAll(safeAccident);
    }
    /**
     * 新增安全管理-事故
     * 
     * @param safeAccident 安全管理-事故
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertSafeAccident(SafeAccident safeAccident)
    {
       try{
           Date nowDate = DateUtils.getNowDate();
           List<SysFile> sysFiles = new ArrayList<>();
           if (StringUtils.isNotNull(safeAccident.getAccidentPhotos())){
               this.uploadPhoto(sysFiles,"accidentPhotos",1L,safeAccident.getAccidentPhotos(),safeAccident,nowDate);
           }
           if (StringUtils.isNotNull(safeAccident.getPartsPhotos())){
               this.uploadPhoto(sysFiles,"partsPhotos",2L,safeAccident.getPartsPhotos(),safeAccident,nowDate);
           }

           // 4 交通事故审批
           int res = systemClient.submitCommon(new SysExamineConfigParams(4L,safeAccident.getId(),safeAccident.getDriverId(),safeAccident.getDriverName(),safeAccident.getSysExamineConfigProcessList()));
           if(res == 0){
               safeAccident.setStatus(2L);
           }

           if (sysFiles.size()>0){
               sysFileMapper.insertList(sysFiles);
           }
           safeAccident.setCreateTime(nowDate);
           return safeAccidentMapper.insertSafeAccident(safeAccident);
       }catch (Exception e){
           throw e;
       }
    }

    private List<SysFile> uploadPhoto(List<SysFile>sysFiles,String fileName,Long type,List<String>files,SafeAccident safeAccident,Date nowDate){
        // 插入附件表
        for (String file : files) {
            SysFile sysFile = new SysFile();
            sysFile.setId(UUID.randomUUID()+"");
            sysFile.setFilePath(file);
            sysFile.setRelationId(safeAccident.getId());
            sysFile.setCreateUser(safeAccident.getCreateBy());
            sysFile.setCreateTime(nowDate);
            // 交通事故有两个存入附件表，通过fileName区分
            sysFile.setFileName(fileName);
            // 类型(1,事故照片2,事故其他 3,考勤附件 4,客户合同附件 5供应商合同附件)
            sysFile.setType(type);
            sysFiles.add(sysFile);
        }
        return sysFiles;
    }
    /**
     * 修改安全管理-事故
     * 
     * @param safeAccident 安全管理-事故
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateSafeAccident(SafeAccident safeAccident)
    {
        try {
            // 根据主键id 去附件表里查询 并删除
            sysFileMapper.delelteFilesByRelationId(safeAccident.getId());

            Date nowDate = DateUtils.getNowDate();
            List<SysFile> sysFiles = new ArrayList<>();
            if (StringUtils.isNotNull(safeAccident.getAccidentPhotos())){
                this.uploadPhoto(sysFiles,"accidentPhotos",1L,safeAccident.getAccidentPhotos(),safeAccident,nowDate);
            }
            if (StringUtils.isNotNull(safeAccident.getPartsPhotos())){
                this.uploadPhoto(sysFiles,"partsPhotos",2L,safeAccident.getPartsPhotos(),safeAccident,nowDate);
            }
            if (sysFiles.size()>0){
                sysFileMapper.insertList(sysFiles);
            }

            // 4 交通事故审批 （再次上报要把首次提交的审核记录删除）
            int res = systemClient.submitCommon(new SysExamineConfigParams(4L,safeAccident.getId(),safeAccident.getDriverId(),safeAccident.getDriverName(),safeAccident.getSysExamineConfigProcessList()));
            if(res == 0){
                safeAccident.setStatus(2L);
            }

            safeAccident.setUpdateTime(nowDate);
            return safeAccidentMapper.updateSafeAccident(safeAccident);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 批量删除安全管理-事故
     * 
     * @param ids 需要删除的安全管理-事故ID
     * @return 结果
     */
    @Override
    public int deleteSafeAccidentByIds(String[] ids)
    {
        return safeAccidentMapper.deleteSafeAccidentByIds(ids);
    }

    /**
     * 删除安全管理-事故信息
     * 
     * @param id 安全管理-事故ID
     * @return 结果
     */
    @Override
    public int deleteSafeAccidentById(String id)
    {
        return safeAccidentMapper.deleteSafeAccidentById(id);
    }

    /**
     * 事故记录列表
     *
     * @param safeAccident 安全管理-事故
     * @return 结果
     */
    @Override
    public List<SafeAccident> querySafeAccidentList(SafeAccident safeAccident) {
        return safeAccidentMapper.querySafeAccidentList(safeAccident);
    }

    /**
     * 车牌下拉选
     */
    @Override
    public List<CVehicle> carVehicleNameList() {
        return cVehicleMapper.selectCVehicleAll();
    }

    /**
     * 事故审批
     * */
    @Override
    public AjaxResult checkSafeAccident(SafeAccident safeAccident) {

        // 审核校验
        ReviewSubmit reviewSubmit = new ReviewSubmit();
        reviewSubmit.setRelationId(safeAccident.getId());
        reviewSubmit.setReviewRemarks(safeAccident.getReviewRemarks());
        reviewSubmit.setUserId(safeAccident.getUserid());
        reviewSubmit.setStatus(safeAccident.getStatus());
        Map map = systemClient.reviewCommon(reviewSubmit);
        if (map.get("code").equals(1) || map.get("code").equals(2)){
            safeAccident.setStatus(1L);
            return AjaxResult.success(map.get("msg").toString());
        }
        // 已通过
        if (map.get("code").equals(3)){
            safeAccident.setStatus(2L);
        }
        // 已驳回
        if (map.get("code").equals(4)){
            safeAccident.setStatus(3L);
        }

        // 2  驾驶员消息节点
        idDriverMsgService.insertDDriverMsg(2L,safeAccident.getId(),safeAccident.getDriverId(),"考勤审核结果","");

        safeAccidentMapper.checkSafeAccident(safeAccident);
        return AjaxResult.success(map.get("msg").toString());
    }

    /**
     * 查看车牌号下的事故
     */
    @Override
    public List<SafeAccident> selectSafeAccidentByType(String vehicleId){
        return safeAccidentMapper.selectSafeAccidentByType(vehicleId);
    }

}
