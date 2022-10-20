package com.dlzx.pfserverperson.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.pfserverperson.domain.SysFile;
import com.dlzx.pfserverperson.mapper.SysDictDataMapper;
import com.dlzx.pfserverperson.mapper.SysFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.PContractMapper;
import com.dlzx.pfserverperson.domain.PContract;
import com.dlzx.pfserverperson.service.IPContractService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 合作方管理-合同Service业务层处理
 * 
 * @author dlzx
 * @date 2020-11-26
 */
@Service
public class PContractServiceImpl implements IPContractService 
{
    @Autowired
    private PContractMapper pContractMapper;
    @Autowired
    private SysDictDataMapper sysDictDataMapper;
    @Autowired
    private SysFileMapper sysFileMapper;

    /**
     * 查询合作方管理-合同
     * 
     * @param id 合作方管理-合同ID
     * @return 合作方管理-合同
     */
    @Override
    public PContract selectPContractById(String id)
    {
        PContract pContract = pContractMapper.selectPContractById(id);
        if (pContract.getType()==1){
            pContract.setContractTypeEnum(sysDictDataMapper.selectDictLabel("p_clientele_type",pContract.getContractType().toString()));
        }
        List<SysFile> files = sysFileMapper.queryFilesByRelationId(id);
        if (files.size()>0){
            List<String> list = files.stream().map(SysFile::getFilePath).collect(Collectors.toList());
            pContract.setFileList(list);
        }
        return pContract;
    }

    /**
     * 查询合作方管理-合同列表
     * 
     * @param pContract 合作方管理-合同
     * @return 合作方管理-合同
     */
    @Override
    public List<PContract> selectPContractList(PContract pContract)
    {
        return pContractMapper.selectPContractList(pContract);
    }

    /**
     * 新增合作方管理-合同
     * 
     * @param pContract 合作方管理-合同
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertPContract(PContract pContract)
    {
        try{
            Date nowDate = DateUtils.getNowDate();
            List<SysFile> sysFiles = new ArrayList<>();
            if (StringUtils.isNotNull(pContract.getFileUrls())){
                this.uploadPhoto(sysFiles,pContract,nowDate);
            }
            if (sysFiles.size()>0){
                sysFileMapper.insertList(sysFiles);
            }
            pContract.setCreateTime(nowDate);
            return pContractMapper.insertPContract(pContract);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 修改合作方管理-合同
     * 
     * @param pContract 合作方管理-合同
     * @return 结果
     */
    @Override
    public int updatePContract(PContract pContract)
    {
        // 根据主键id 去附件表里查询 并删除
        sysFileMapper.delelteFilesByRelationId(pContract.getId());

        Date nowDate = DateUtils.getNowDate();
        List<SysFile> sysFiles = new ArrayList<>();
        if (StringUtils.isNotNull(pContract.getFileUrls())){
            this.uploadPhoto(sysFiles,pContract,nowDate);
        }
        if (sysFiles.size()>0){
            sysFileMapper.insertList(sysFiles);
        }
        pContract.setUpdateTime(nowDate);
        return pContractMapper.updatePContract(pContract);
    }

    /**
     * 批量删除合作方管理-合同
     * 
     * @param ids 需要删除的合作方管理-合同ID
     * @return 结果
     */
    @Override
    public int deletePContractByIds(String[] ids)
    {
        return pContractMapper.deletePContractByIds(ids);
    }

    /**
     * 删除合作方管理-合同信息
     * 
     * @param id 合作方管理-合同ID
     * @return 结果
     */
    @Override
    public int deletePContractById(String id)
    {
        return pContractMapper.deletePContractById(id);
    }

    /**
     * 修改客户合同列表 标记/预警状态
     *
     * @param pContract 合作方管理-合同
     * @return 结果
     */
    @Override
    public int updateCustomerContractStatus(PContract pContract) {
        return pContractMapper.updateCustomerContractStatus(pContract);
    }

    private List<SysFile> uploadPhoto(List<SysFile>sysFiles,PContract pContract,Date nowDate){
        // 插入附件表
        for (String file : pContract.getFileUrls()) {
            SysFile sysFile = new SysFile();
            sysFile.setId(UUID.randomUUID()+"");
            sysFile.setFilePath(file);
            sysFile.setRelationId(pContract.getId());
            sysFile.setCreateUser(pContract.getCreateBy());
            sysFile.setCreateTime(nowDate);
            // 类型(1,事故照片2,事故其他 3,考勤附件 4,客户合同附件 5供应商合同附件)
            if (pContract.getType()==1){
                sysFile.setType(4L);
            }else if (pContract.getType()==2){
                sysFile.setType(5L);
            }
            sysFiles.add(sysFile);
        }
        return sysFiles;
    }


    @Override
    public void checkMonth(PContract item, SimpleDateFormat df, Date date) throws ParseException {
        Calendar aft = Calendar.getInstance();
        item.setMathMonth(0);
        aft.setTime(item.getContractEndTiem());
        aft.add(Calendar.MONTH, -3);
        if (df.parse(df.format(aft.getTime())).getTime()<= date.getTime()){
            item.setMathMonth(3);
        }

        aft.setTime(item.getContractEndTiem());
        aft.add(Calendar.MONTH, -2);
        if (df.parse(df.format(aft.getTime())).getTime()<= date.getTime()){
            item.setMathMonth(2);
        }

        aft.setTime(item.getContractEndTiem());
        aft.add(Calendar.MONTH, -1);
        if (df.parse(df.format(aft.getTime())).getTime()<= date.getTime()){
            item.setMathMonth(1);

            // 如何合同已过期，并且没有预警，则不显示颜色
            if (date.getTime()>item.getContractEndTiem().getTime() && item.getFlag()==1){
                item.setMathMonth(0);
            }
        }
        // 预警直接标红
        if (item.getFlag()==2){
            item.setMathMonth(1);
        }
    }
}
