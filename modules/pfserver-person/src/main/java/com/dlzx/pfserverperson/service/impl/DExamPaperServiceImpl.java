package com.dlzx.pfserverperson.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DExamPaperMapper;
import com.dlzx.pfserverperson.domain.DExamPaper;
import com.dlzx.pfserverperson.service.IDExamPaperService;

/**
 * 驾驶员-试卷Service业务层处理
 * 
 * @author dlzx
 * @date 2020-11-18
 */
@Service
public class DExamPaperServiceImpl implements IDExamPaperService 
{
    @Autowired
    private DExamPaperMapper dExamPaperMapper;

    /**
     * 查询驾驶员-试卷
     * 
     * @param id 驾驶员-试卷ID
     * @return 驾驶员-试卷
     */
    @Override
    public DExamPaper selectDExamPaperById(String id)
    {
        return dExamPaperMapper.selectDExamPaperById(id);
    }

    /**
     * 查询驾驶员-试卷列表
     * 
     * @param dExamPaper 驾驶员-试卷
     * @return 驾驶员-试卷
     */
    @Override
    public List<DExamPaper> selectDExamPaperList(DExamPaper dExamPaper)
    {
        return dExamPaperMapper.selectDExamPaperList(dExamPaper);
    }

    /**
     * 新增驾驶员-试卷
     * 
     * @param dExamPaper 驾驶员-试卷
     * @return 结果
     */
    @Override
    public int insertDExamPaper(DExamPaper dExamPaper)
    {
        dExamPaper.setCreateTime(DateUtils.getNowDate());
        return dExamPaperMapper.insertDExamPaper(dExamPaper);
    }


    /**
     * 修改驾驶员-试卷
     * 
     * @param dExamPaper 驾驶员-试卷
     * @return 结果
     */
    @Override
    public int updateDExamPaper(DExamPaper dExamPaper)
    {
        dExamPaper.setUpdateTime(DateUtils.getNowDate());
        return dExamPaperMapper.updateDExamPaper(dExamPaper);
    }

    /**
     * 批量删除驾驶员-试卷
     * 
     * @param ids 需要删除的驾驶员-试卷ID
     * @return 结果
     */
    @Override
    public int deleteDExamPaperByIds(String[] ids)
    {
        return dExamPaperMapper.deleteDExamPaperByIds(ids);
    }

    /**
     * 删除驾驶员-试卷信息
     * 
     * @param id 驾驶员-试卷ID
     * @return 结果
     */
    @Override
    public int deleteDExamPaperById(String id)
    {
        return dExamPaperMapper.deleteDExamPaperById(id);
    }
}
