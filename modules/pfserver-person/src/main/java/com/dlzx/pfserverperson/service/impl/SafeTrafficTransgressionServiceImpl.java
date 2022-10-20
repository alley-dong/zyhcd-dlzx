package com.dlzx.pfserverperson.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.pfserverperson.domain.DTrainingUser;
import com.dlzx.pfserverperson.domain.DTrainingUserRecord;
import com.dlzx.pfserverperson.domain.SysFile;
import com.dlzx.pfserverperson.mapper.SysFileMapper;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.SafeTrafficTransgressionMapper;
import com.dlzx.pfserverperson.domain.SafeTrafficTransgression;
import com.dlzx.pfserverperson.service.ISafeTrafficTransgressionService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安全管理-违章记录Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-10
 */
@Service
public class SafeTrafficTransgressionServiceImpl implements ISafeTrafficTransgressionService 
{
    @Autowired
    private SafeTrafficTransgressionMapper safeTrafficTransgressionMapper;

    @Autowired
    private SysFileMapper sysFileMapper;

    /**
     * 查询安全管理-违章记录
     * 
     * @param id 安全管理-违章记录ID
     * @return 安全管理-违章记录
     */
    @Override
    public SafeTrafficTransgression selectSafeTrafficTransgressionById(String id)
    {
        SafeTrafficTransgression safeTrafficTransgression = safeTrafficTransgressionMapper.selectSafeTrafficTransgressionById(id);
        List<SysFile> files = sysFileMapper.queryFilesByRelationId(id);
        if (files.size()>0){
            // 违章图片
            safeTrafficTransgression.setFileList(files.stream().filter(l->l.getType()==7).collect(Collectors.toList()));
            // 处理凭证
            safeTrafficTransgression.setVoucherFileList(files.stream().filter(l->l.getType()==8).collect(Collectors.toList()));
        }
        return safeTrafficTransgression;
    }

    /**
     * 查询安全管理-违章记录列表
     * 
     * @param safeTrafficTransgression 安全管理-违章记录
     * @return 安全管理-违章记录
     */
    @Override
    public List<SafeTrafficTransgression> selectSafeTrafficTransgressionList(SafeTrafficTransgression safeTrafficTransgression)
    {
        return safeTrafficTransgressionMapper.selectSafeTrafficTransgressionList(safeTrafficTransgression);
    }

    @Override
    public List<SafeTrafficTransgression> selectSafeTrafficTransgressionListDriver(SafeTrafficTransgression safeTrafficTransgression)
    {
        return safeTrafficTransgressionMapper.selectSafeTrafficTransgressionListDriver(safeTrafficTransgression);
    }

    //    查询驾驶员档案-违章记录表
    @Override
    public List<SafeTrafficTransgression> selectSafeTrafficTransgressionListPerson(SafeTrafficTransgression safeTrafficTransgression)
    {
        return safeTrafficTransgressionMapper.selectSafeTrafficTransgressionListPerson(safeTrafficTransgression);
    }
    /**
     * 新增安全管理-违章记录
     * 
     * @param safeTrafficTransgression 安全管理-违章记录
     * @return 结果
     */
    @Override
    public int insertSafeTrafficTransgression(SafeTrafficTransgression safeTrafficTransgression)
    {
        safeTrafficTransgression.setCreateTime(DateUtils.getNowDate());
        return safeTrafficTransgressionMapper.insertSafeTrafficTransgression(safeTrafficTransgression);
    }

    @Override
    @Transactional
    public int insertSafeTrafficTransgressionUrl(SafeTrafficTransgression safeTrafficTransgression, String Id, LoginUser userInfo) {
        int result = 1;
        try {
            Date nowDate = DateUtils.getNowDate();
            List<SysFile> sysFiles = new ArrayList<>();
            if (StringUtils.isNotNull(safeTrafficTransgression.getPhotoUrls())){
                this.uploadPhoto(sysFiles,safeTrafficTransgression.getPhotoUrls(),safeTrafficTransgression,nowDate);
            }
            if (sysFiles.size()>0){
                sysFileMapper.insertList(sysFiles);
            }
            safeTrafficTransgression.setCreateTime(nowDate);
            if (safeTrafficTransgression!= null) {
                result = this.insertSafeTrafficTransgression(safeTrafficTransgression);
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    private List<SysFile> uploadPhoto(List<SysFile> sysFiles, List<String> photoUrls, SafeTrafficTransgression safeTrafficTransgression, Date nowDate) {
        // 插入附件表
        for (String file : photoUrls) {
            SysFile sysFile = new SysFile();
            sysFile.setId(UUID.randomUUID()+"");
            sysFile.setFilePath(file);
            sysFile.setRelationId(safeTrafficTransgression.getId());
            sysFile.setCreateUser(safeTrafficTransgression.getCreateBy());
            sysFile.setCreateTime(nowDate);
            // 类型(1,事故照片2,事故其他 3,考勤附件 4,客户合同附件 5供应商合同附件)
            sysFile.setType(7L);
            sysFiles.add(sysFile);
        }
        return sysFiles;
    }

    /**
     * 修改安全管理-违章记录
     * 
     * @param safeTrafficTransgression 安全管理-违章记录
     * @return 结果
     */
    @Override
    public int updateSafeTrafficTransgression(SafeTrafficTransgression safeTrafficTransgression)
    {
        try {
            // 根据主键id 去附件表里查询 并删除
            sysFileMapper.delelteFilesByRelationId(safeTrafficTransgression.getId());

            Date nowDate = DateUtils.getNowDate();
            List<SysFile> sysFiles = new ArrayList<>();
            if (StringUtils.isNotNull(safeTrafficTransgression.getPhotoUrls())){
                this.uploadPhoto(sysFiles,safeTrafficTransgression.getPhotoUrls(),safeTrafficTransgression,nowDate);
            }
            if (sysFiles.size()>0){
                sysFileMapper.insertList(sysFiles);
            }

            safeTrafficTransgression.setUpdateTime(DateUtils.getNowDate());
            return safeTrafficTransgressionMapper.updateSafeTrafficTransgression(safeTrafficTransgression);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 批量删除安全管理-违章记录
     * 
     * @param ids 需要删除的安全管理-违章记录ID
     * @return 结果
     */
    @Override
    public int deleteSafeTrafficTransgressionByIds(String[] ids)
    {
        return safeTrafficTransgressionMapper.deleteSafeTrafficTransgressionByIds(ids);
    }

    /**
     * 删除安全管理-违章记录信息
     * 
     * @param id 安全管理-违章记录ID
     * @return 结果
     */
    @Override
    public int deleteSafeTrafficTransgressionById(String id)
    {
        return safeTrafficTransgressionMapper.deleteSafeTrafficTransgressionById(id);
    }

    /**
     * @describe 修改违章状态 1未处理 2已处理
     * @author DongCL
     * @date 2021-04-13 14:38
     * @param
     * @return
     */
    @Override
    public int updateSafeTrafficTransgressionStatus(SafeTrafficTransgression safeTrafficTransgression) {
        return safeTrafficTransgressionMapper.updateSafeTrafficTransgression(safeTrafficTransgression);
    }
}
