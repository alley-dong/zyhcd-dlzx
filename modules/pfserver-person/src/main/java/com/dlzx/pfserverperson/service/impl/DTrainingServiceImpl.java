package com.dlzx.pfserverperson.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dlzx.common.core.utils.DateUtils;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.pfserverperson.domain.DTrainingUser;
import com.dlzx.pfserverperson.domain.DTrainingUserRecord;
import com.dlzx.pfserverperson.domain.DVacation;
import com.dlzx.pfserverperson.domain.SysFile;
import com.dlzx.pfserverperson.mapper.DTrainingUserMapper;
import com.dlzx.pfserverperson.mapper.DTrainingUserRecordMapper;
import com.dlzx.pfserverperson.mapper.SysFileMapper;
import com.dlzx.system.api.model.LoginDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DTrainingMapper;
import com.dlzx.pfserverperson.domain.DTraining;
import com.dlzx.pfserverperson.service.IDTrainingService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 驾驶员-培训Service业务层处理
 * 
 * @author dlzx
 * @date 2020-11-26
 */
@Service
public class DTrainingServiceImpl implements IDTrainingService 
{
    @Autowired
    private DTrainingMapper dTrainingMapper;
    @Autowired
    private DTrainingUserRecordMapper dTrainingUserRecordMapper;

    @Autowired
    private DTrainingUserMapper dTrainingUserMapper;

    @Autowired
    private SysFileMapper sysFileMapper;

    @Autowired
    private DriverTokenService driverTokenService;

    /**
     * 查询驾驶员-培训
     * 
     * @param id 驾驶员-培训ID
     * @return 驾驶员-培训
     */
    @Override
    public DTraining selectDTrainingById(String id)
    {
        DTraining dTraining = dTrainingMapper.selectDTrainingById(id);

        List<SysFile> files = sysFileMapper.queryFilesByRelationId(id);
        if (files.size()>0){
            List<String> list = files.stream().map(SysFile::getFilePath).collect(Collectors.toList());
            dTraining.setFileList(list);
        }
        return dTraining;
    }

    /**
     * 查询驾驶员-培训列表
     * 
     * @param dTraining 驾驶员-培训
     * @return 驾驶员-培训
     */
    @Override
    public List<DTraining> selectDTrainingList(DTraining dTraining)
    {
        return dTrainingMapper.selectDTrainingList(dTraining);
    }



    /**
     * 新增驾驶员-培训
     * 
     * @param dTraining 驾驶员-培训
     * @return 结果
     */
    @Override
    public int insertDTraining(DTraining dTraining)
    {
        dTraining.setCreateTime(DateUtils.getNowDate());
        return dTrainingMapper.insertDTraining(dTraining);
    }

    /**
     * 新增驾驶员-培训
     *
     * @param dTraining 驾驶员-培训
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertDTrainingBC(DTraining dTraining)
    {
        // 文件
        try {
            Date nowDate = DateUtils.getNowDate();
            List<SysFile> files = new ArrayList<>();
            if (StringUtils.isNotNull(dTraining.getFileUrls())){
                this.uploadPhoto(files,dTraining,nowDate);
            }
            if (files.size()>0){
                sysFileMapper.insertList(files);
            }
        } catch (Exception e) {
            throw e;
        }

        return dTrainingMapper.insertDTrainingBC(dTraining);
    }
    /**
     * 修改驾驶员-培训
     * 
     * @param dTraining 驾驶员-培训
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateDTraining(DTraining dTraining)
    {
        try {
            // 根据主键id 去附件表里查询 并删除
            sysFileMapper.delelteFilesByRelationId(dTraining.getId());

            Date nowDate = DateUtils.getNowDate();
            List<SysFile> files = new ArrayList<>();
            if (StringUtils.isNotNull(dTraining.getFileUrls())){
                this.uploadPhoto(files,dTraining,nowDate);
            }
            if (files.size()>0){
                sysFileMapper.insertList(files);
            }
        } catch (Exception e) {
            throw e;
        }

        dTraining.setUpdateTime(DateUtils.getNowDate());
        return dTrainingMapper.updateDTraining(dTraining);
    }

    /**
     * 批量删除驾驶员-培训
     * 
     * @param ids 需要删除的驾驶员-培训ID
     * @return 结果
     */
    @Override
    public int deleteDTrainingByIds(String[] ids)
    {
        return dTrainingMapper.deleteDTrainingByIds(ids);
    }

    /**
     * 删除驾驶员-培训信息
     * 
     * @param id 驾驶员-培训ID
     * @return 结果
     */
    @Override
    public int deleteDTrainingById(String id)
    {
        return dTrainingMapper.deleteDTrainingById(id);
    }

    /**
     * @describe 修改培训状态
     * @author DongCL
     * @date 2021-03-15 11:26
     * @param
     * @return
     */
    @Override
    public int updateDTrainingStatus(DTraining dTraining) {
        dTraining.setUpdateTime(DateUtils.getNowDate());
        return dTrainingMapper.updateDTraining(dTraining);
    }

    @Override
    public DTraining selectDriverDTrainingById(String id) {
        DTraining dTraining = dTrainingMapper.selectDTrainingById(id);

        if (dTraining != null){
            LoginDriver loginDriver = driverTokenService.getLoginDriver();
            DTrainingUserRecord dTrainingUserRecord = new DTrainingUserRecord();
            dTrainingUserRecord.setUserId(loginDriver.getUserid());
            dTrainingUserRecord.setTrainingId(dTraining.getId());
            DTrainingUserRecord dTrainingUserRecord1 = dTrainingUserRecordMapper.selectDTrainingUserRecordList(dTrainingUserRecord).stream().findFirst().orElse(null);
            if (dTrainingUserRecord1!= null){
                dTraining.setUserStatus(dTrainingUserRecord1.getStatus()+"");
            }
        }
        List<SysFile> files = sysFileMapper.queryFilesByRelationId(id);
        if (files.size()>0){
            List<String> list = files.stream().map(SysFile::getFilePath).collect(Collectors.toList());
            dTraining.setFileList(list);
        }
        return dTraining;
    }


    private List<SysFile> uploadPhoto(List<SysFile>sysFiles, DTraining dTraining, Date nowDate){
        // 插入附件表
        for (String file : dTraining.getFileUrls()) {
            SysFile sysFile = new SysFile();
            sysFile.setId(UUID.randomUUID()+"");
            sysFile.setFilePath(file);
            sysFile.setRelationId(dTraining.getId());
            sysFile.setCreateUser(dTraining.getCreateBy());
            sysFile.setCreateTime(nowDate);
            // 类型(类型(1,事故照片2,事故其他 3,考勤附件 4,客户合同附件 5供应商合同附件 6供应商评价附件 7交通违章图片 8违章处理图片 9驾驶员档案附件 10车辆档案附件 11保险保单照片 12.任务装货照片 13任务卸货照片 14任务异常情况照片 15培训))
            sysFile.setType(15L);
            sysFiles.add(sysFile);
        }
        return sysFiles;
    }
}
