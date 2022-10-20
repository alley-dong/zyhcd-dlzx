package com.dlzx.pfserverperson.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfserverperson.domain.PContractEvaluateMetrics;
import com.dlzx.pfserverperson.domain.PContractMetrics;
import com.dlzx.pfserverperson.domain.PContractMetricsType;
import com.dlzx.pfserverperson.domain.SysFile;
import com.dlzx.pfserverperson.mapper.PContractEvaluateMetricsMapper;
import com.dlzx.pfserverperson.mapper.PContractMetricsMapper;
import com.dlzx.pfserverperson.mapper.SysFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.PContractEvaluateMapper;
import com.dlzx.pfserverperson.domain.PContractEvaluate;
import com.dlzx.pfserverperson.service.IPContractEvaluateService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 合作方管理-合同评价记录Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-09
 */
@Service
public class PContractEvaluateServiceImpl implements IPContractEvaluateService 
{
    @Autowired
    private PContractEvaluateMapper pContractEvaluateMapper;

    @Autowired
    private PContractEvaluateMetricsMapper pContractEvaluateMetricsMapper;

    @Autowired
    private PContractMetricsMapper pContractMetricsMapper;

    @Autowired
    private SysFileMapper sysFileMapper;

    /**
     * 查询合作方管理-合同评价记录
     * 
     * @param pContractEvaluate 合作方管理-合同评价记录
     * @return 合作方管理-合同评价记录
     */
    @Override
    public PContractEvaluate selectPContractEvaluateById(PContractEvaluate pContractEvaluate)
    {
        PContractEvaluate pContractEvaluateRes = pContractEvaluateMapper.selectPContractEvaluateById(pContractEvaluate.getId());
        // 返回附件信息
        List<SysFile> files = sysFileMapper.queryFilesByRelationId(pContractEvaluate.getId());
        if (files.size()>0){
            List<String> list = files.stream().map(SysFile::getFilePath).collect(Collectors.toList());
            pContractEvaluateRes.setFileList(list);
        }

        // 返回评价列表
        PContractEvaluateMetrics pContractEvaluateMetrics = new PContractEvaluateMetrics();
        pContractEvaluateMetrics.setTypeId(pContractEvaluateRes.getTypeId());
        pContractEvaluateMetrics.setMetricsId(pContractEvaluateRes.getId());
        List<PContractEvaluateMetrics> pContractEvaluateMetricsList = pContractEvaluateMetricsMapper.selectPContractEvaluateMetricsList(pContractEvaluateMetrics);
        if (pContractEvaluateMetricsList.size()>0){
            pContractEvaluateRes.setpContractEvaluateMetricsList(pContractEvaluateMetricsList);
            // 计算总分数
            Long total = 0L;
            for (PContractEvaluateMetrics contractEvaluateMetrics : pContractEvaluateMetricsList) {
                // 技术部分
                if (contractEvaluateMetrics.getType()==1){
                    total += contractEvaluateMetrics.getScore()==null?0L:contractEvaluateMetrics.getScore();
                }
                // 商务部分
                if (contractEvaluateMetrics.getType()==2){
                    contractEvaluateMetrics.setScore(null);
                }
            }
            pContractEvaluateRes.setTotalScore(total);
            // 权重70%
            BigDecimal bigDecimal = new BigDecimal(String.valueOf(BigDecimal.valueOf(0.7)));
            // 计算总分数的70%
            pContractEvaluateRes.setWeightTotalScore(bigDecimal.multiply(BigDecimal.valueOf(total)));
        }

        return pContractEvaluateRes;
    }

    /**
     * 查询合作方管理-合同评价记录列表
     * 
     * @param pContractEvaluate 合作方管理-合同评价记录
     * @return 合作方管理-合同评价记录
     */
    @Override
    public List<PContractEvaluate> selectPContractEvaluateList(PContractEvaluate pContractEvaluate)
    {
        return pContractEvaluateMapper.selectPContractEvaluateList(pContractEvaluate);
    }

    /**
     * 新增合作方管理-合同评价记录
     * 
     * @param pContractEvaluate 合作方管理-合同评价记录
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxResult insertPContractEvaluate(PContractEvaluate pContractEvaluate)
    {

        // 先查询指标类型下的评价指标
        PContractMetrics pContractMetrics = new PContractMetrics();
        pContractMetrics.setTypeId(pContractEvaluate.getTypeId());
        List<PContractMetrics> pContractMetricsList = pContractMetricsMapper.selectPContractMetricsList(pContractMetrics);
        if(pContractMetricsList.size()>0){
            AjaxResult.error("当前指标类型未添加评价指标,请重新选择");
        }
        try {

            // 再新增供应商评价下的 评价
            List<PContractEvaluateMetrics> pContractEvaluateMetrics = new ArrayList<>();
            for (PContractMetrics contractMetrics : pContractMetricsList) {
                PContractEvaluateMetrics pContractEvaluateMetricsAdd = new PContractEvaluateMetrics();
                pContractEvaluateMetricsAdd.setId(UUID.randomUUID() + "");
                pContractEvaluateMetricsAdd.setContent(contractMetrics.getContent());
                pContractEvaluateMetricsAdd.setDimension(contractMetrics.getDimension());
                pContractEvaluateMetricsAdd.setMethod(contractMetrics.getMethod());
                // 这里应该取  pContractEvaluate 的 id
                pContractEvaluateMetricsAdd.setMetricsId(pContractEvaluate.getId());
                pContractEvaluateMetricsAdd.setType(contractMetrics.getType());
                pContractEvaluateMetricsAdd.setTypeId(contractMetrics.getTypeId());
                pContractEvaluateMetricsAdd.setTypeName(contractMetrics.getTypeName());
                pContractEvaluateMetricsAdd.setCreateBy(pContractEvaluate.getCreateBy());
                pContractEvaluateMetricsAdd.setCreateTime(DateUtils.getNowDate());
                pContractEvaluateMetricsAdd.setScore(0L);
                pContractEvaluateMetrics.add(pContractEvaluateMetricsAdd);
            }
            if (pContractMetricsList.size() > 0) {
                pContractEvaluateMetricsMapper.insertList(pContractEvaluateMetrics);
            }

            pContractEvaluate.setCreateTime(DateUtils.getNowDate());
            return AjaxResult.success(pContractEvaluateMapper.insertPContractEvaluate(pContractEvaluate));
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 修改合作方管理-合同评价记录
     * 
     * @param pContractEvaluate 合作方管理-合同评价记录
     * @return 结果
     */
    @Override
    public int updatePContractEvaluate(PContractEvaluate pContractEvaluate)
    {

        // 根据主键id 去附件表里查询 并删除
        sysFileMapper.delelteFilesByRelationId(pContractEvaluate.getId());

        // 上传附件
        Date nowDate = DateUtils.getNowDate();
        List<SysFile> sysFiles = new ArrayList<>();
        if (StringUtils.isNotNull(pContractEvaluate.getFileUrls())){
            this.uploadPhoto(sysFiles,pContractEvaluate,nowDate);
        }
        if (sysFiles.size()>0){
            sysFileMapper.insertList(sysFiles);
        }

        pContractEvaluate.setUpdateTime(DateUtils.getNowDate());
        return pContractEvaluateMapper.updatePContractEvaluate(pContractEvaluate);
    }

    /**
     * 批量删除合作方管理-合同评价记录
     * 
     * @param ids 需要删除的合作方管理-合同评价记录ID
     * @return 结果
     */
    @Override
    public int deletePContractEvaluateByIds(String[] ids)
    {
        return pContractEvaluateMapper.deletePContractEvaluateByIds(ids);
    }

    /**
     * 删除合作方管理-合同评价记录信息
     * 
     * @param id 合作方管理-合同评价记录ID
     * @return 结果
     */
    @Override
    public int deletePContractEvaluateById(String id)
    {
        return pContractEvaluateMapper.deletePContractEvaluateById(id);
    }

    private List<SysFile> uploadPhoto(List<SysFile>sysFiles, PContractEvaluate pContractEvaluate, Date nowDate){
        // 插入附件表
        for (String file : pContractEvaluate.getFileUrls()) {
            SysFile sysFile = new SysFile();
            sysFile.setId(UUID.randomUUID()+"");
            sysFile.setFilePath(file);
            sysFile.setRelationId(pContractEvaluate.getId());
            sysFile.setCreateUser(pContractEvaluate.getCreateBy());
            sysFile.setCreateTime(nowDate);
            // 类型(1,事故照片2,事故其他 3,考勤附件 4,客户合同附件 5供应商合同附件 6供应商评价附件)
            sysFile.setType(6L);
            sysFiles.add(sysFile);
        }
        return sysFiles;
    }

}
