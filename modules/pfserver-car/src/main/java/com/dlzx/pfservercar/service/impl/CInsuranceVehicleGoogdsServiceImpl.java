package com.dlzx.pfservercar.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.dlzx.common.core.text.UUID;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.CInsuranceVehicle;
import com.dlzx.pfservercar.domain.CInsuranceVehicleItem;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.SysFile;
import com.dlzx.pfservercar.mapper.CInsuranceVehicleItemMapper;
import com.dlzx.pfservercar.mapper.CInsuranceVehicleMapper;
import com.dlzx.pfservercar.mapper.CVehicleMapper;
import com.dlzx.pfservercar.mapper.SysFileMapper;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.CInsuranceVehicleGoogdsMapper;
import com.dlzx.pfservercar.domain.CInsuranceVehicleGoogds;
import com.dlzx.pfservercar.service.ICInsuranceVehicleGoogdsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 车辆管理-货物保险-车辆Service业务层处理
 * 
 * @author dlzx
 * @date 2020-11-27
 */
@Service
public class CInsuranceVehicleGoogdsServiceImpl implements ICInsuranceVehicleGoogdsService 
{
    @Autowired
    private CInsuranceVehicleGoogdsMapper cInsuranceVehicleGoogdsMapper;

    @Autowired
    private SysFileMapper sysFileMapper;
    @Autowired
    private CInsuranceVehicleMapper cInsuranceVehicleMapper;
    @Autowired
    private CInsuranceVehicleItemMapper cInsuranceVehicleItemMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CVehicleMapper cVehicleMapper;

    /**
     * 查询车辆管理-货物保险-车辆
     * 
     * @param id 车辆管理-货物保险-车辆ID
     * @return 车辆管理-货物保险-车辆
     */
    @Override
    public CInsuranceVehicle selectCInsuranceVehicleGoogdsById(String id)
    {
        CInsuranceVehicle cInsuranceVehicle = cInsuranceVehicleMapper.selectCInsuranceVehicleById(id);
        List<CInsuranceVehicleItem> cInsuranceVehicleItems = cInsuranceVehicleItemMapper.selectCInsuranceItemByInsuranceId(id);
        List<CInsuranceVehicleGoogds> cInsuranceVehicleGoogdsList = cInsuranceVehicleGoogdsMapper.selectCIGByCId(id);
        if (cInsuranceVehicleItems.size()>0){
            cInsuranceVehicle.setcInsuranceVehicleItems(cInsuranceVehicleItems);
        }
        if (cInsuranceVehicleGoogdsList.size()>0){
            cInsuranceVehicle.setcInsuranceVehicleGoogds(cInsuranceVehicleGoogdsList);
        }
        return cInsuranceVehicle ;
    }

    /**
     * 查询车辆管理-货物保险-车辆列表
     * 
     * @param cInsuranceVehicleGoogds 车辆管理-货物保险-车辆
     * @return 车辆管理-货物保险-车辆
     */
    @Override
    public List<CInsuranceVehicleGoogds> selectCInsuranceVehicleGoogdsList(CInsuranceVehicleGoogds cInsuranceVehicleGoogds)
    {
        return cInsuranceVehicleGoogdsMapper.selectCInsuranceVehicleGoogdsList(cInsuranceVehicleGoogds);
    }

    @Override
    public CInsuranceVehicleGoogds selectCInsuranceVehicleGoogdInfo(String id) {
        return cInsuranceVehicleGoogdsMapper.selectCInsuranceVehicleGoogdInfo(id);
    }

    @Override
    public List<CInsuranceVehicleGoogds> selectCInsuranceVehicleGoogdAndCIV(CInsuranceVehicleGoogds cInsuranceVehicleGoogds) {

        return cInsuranceVehicleGoogdsMapper.selectCInsuranceVehicleGoogdAndCIV(cInsuranceVehicleGoogds);
    }

    @Override
    public List<SysFile> selectSysFileList(String civgd) {
        return sysFileMapper.queryFilesByRelationId(civgd, "11");
    }

    /**
     * 新增车辆管理-货物保险-车辆
     * 
     * @param cInsuranceVehicleGoogds 车辆管理-货物保险-车辆
     * @return 结果
     */
    @Override
    public int insertCInsuranceVehicleGoogds(CInsuranceVehicleGoogds cInsuranceVehicleGoogds)
    {
        return cInsuranceVehicleGoogdsMapper.insertCInsuranceVehicleGoogds(cInsuranceVehicleGoogds);
    }

    /**
     * 修改车辆管理-货物保险-车辆
     * 
     * @param cInsuranceVehicleGoogds 车辆管理-货物保险-车辆
     * @return 结果
     */
    @Override
    public int updateCInsuranceVehicleGoogds(CInsuranceVehicleGoogds cInsuranceVehicleGoogds)
    {
        // 先根据主键ID删除以前存的
        sysFileMapper.delelteFilesByRelationId(cInsuranceVehicleGoogds.getId());

        // 获取当前的时间
        Date nowDate = DateUtils.getNowDate();
        // 创建一个list用来存文件名
        List<SysFile> files = new ArrayList<>();
        // 判断如果获取的FileUrls非空(附件文件的url)
        if (StringUtils.isNotNull(cInsuranceVehicleGoogds.getFileUrls())) {
            this.uploadPhoto(files, cInsuranceVehicleGoogds, nowDate);
        }
        if (files.size() > 0) {
            sysFileMapper.insertList(files);
        }
        cInsuranceVehicleGoogds.setUpdateTime(nowDate);
        return cInsuranceVehicleGoogdsMapper.updateCInsuranceVehicleGoogds(cInsuranceVehicleGoogds);
    }

    private List<SysFile> uploadPhoto(List<SysFile> sysFiles, CInsuranceVehicleGoogds cInsuranceVehicleGoogds, Date nowDate) {
        // 先将数据分隔开
        String[] split = cInsuranceVehicleGoogds.getFileUrls().split(",");
        for (String file : split) {
            SysFile sysFile = new SysFile();
            // 主键ID
            sysFile.setId(UUID.randomUUID().toString());
            // 地址
            sysFile.setFilePath(file);
            // 关联ID
            sysFile.setRelationId(cInsuranceVehicleGoogds.getId());
            sysFile.setCreateUser(cInsuranceVehicleGoogds.getCreateBy());
            sysFile.setCreateTime(nowDate);
            // 类型 11保险保单照片
            sysFile.setType(Long.valueOf(11));
            sysFiles.add(sysFile);
        }
        return sysFiles;
    }

    /**
     * 批量删除车辆管理-货物保险-车辆
     * 
     * @param ids 需要删除的车辆管理-货物保险-车辆ID
     * @return 结果
     */
    @Override
    public int deleteCInsuranceVehicleGoogdsByIds(String[] ids)
    {
        return cInsuranceVehicleGoogdsMapper.deleteCInsuranceVehicleGoogdsByIds(ids);
    }

    /**
     * 删除车辆管理-货物保险-车辆信息
     * 
     * @param id 车辆管理-货物保险-车辆ID
     * @return 结果
     */
    @Override
    public int deleteCInsuranceVehicleGoogdsById(String id)
    {
        return cInsuranceVehicleGoogdsMapper.deleteCInsuranceVehicleGoogdsById(id);
    }

    /**
     * 批量新增
     */
    @Override
    public int insertBatch(List<CInsuranceVehicleGoogds> cInsuranceVehicleGoogdsList){
        return cInsuranceVehicleGoogdsMapper.insertBatch(cInsuranceVehicleGoogdsList);
    }

    @Override
    public CInsuranceVehicleGoogds selectCInVeGoodById(String id){
        CInsuranceVehicleGoogds cInsuranceVehicleGoogds = cInsuranceVehicleGoogdsMapper.selectCInsuranceVehicleGoogdsById(id);
        CInsuranceVehicle cInsuranceVehicle = cInsuranceVehicleMapper.selectCInsuranceVehicleById(cInsuranceVehicleGoogds.getInsuranceId());
        cInsuranceVehicleGoogds.setType(cInsuranceVehicle.getType());
        List<CInsuranceVehicleItem> cInsuranceVehicleItem = cInsuranceVehicleItemMapper.selectCInsuranceItemByInsuranceId(cInsuranceVehicleGoogds.getInsuranceId());
        List<SysFile> sysFiles = sysFileMapper.selectSysFileByListId(cInsuranceVehicleGoogds.getId());
        if (!sysFiles.isEmpty()){
            List<String> fileList = new ArrayList<>();
            for (SysFile sysFile:sysFiles){
                fileList.add(sysFile.getFilePath());
            }
            cInsuranceVehicleGoogds.setFileList(fileList);
        }
        cInsuranceVehicleGoogds.setcInsuranceVehicleItemList(cInsuranceVehicleItem);
        return cInsuranceVehicleGoogds;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateCIVG(CInsuranceVehicleGoogds cInsuranceVehicleGoogds){
        sysFileMapper.delelteFilesByRelationId(cInsuranceVehicleGoogds.getId());
        if (cInsuranceVehicleGoogds.getFileList()!= null&&cInsuranceVehicleGoogds.getFileList().size()>0){
            List<SysFile> sysFiles = new ArrayList<>();
            for (String file:cInsuranceVehicleGoogds.getFileList()){
                SysFile sysFile = new SysFile();
                sysFile.setId(UUID.randomUUID().toString());
                sysFile.setRelationId(cInsuranceVehicleGoogds.getId());
                sysFile.setDeleteFlag(1L);
                sysFile.setType(11L);
                sysFile.setFilePath(file);
                sysFile.setCreateUser(tokenService.getLoginUser().getUsername());
                sysFile.setCreateTime(new Date());
                sysFiles.add(sysFile);
            }
            sysFileMapper.insertList(sysFiles);
        }
        CVehicle cVehicle = cVehicleMapper.selectCVehicleById(cInsuranceVehicleGoogds.getVehicleId());
        cVehicle.setInsuranceStartDate(cInsuranceVehicleGoogds.getInsuranceStartDate());
        cVehicle.setInsuranceEndDate(cInsuranceVehicleGoogds.getInsuranceEndDate());
        cVehicle.setInsuranceHandleTime(cInsuranceVehicleGoogds.getInsuranceHandleTime());
        cVehicleMapper.updateCVehicle(cVehicle);
        return cInsuranceVehicleGoogdsMapper.updateCInsuranceVehicleGoogds(cInsuranceVehicleGoogds);
    }
}
