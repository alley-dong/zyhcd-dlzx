package com.dlzx.pfserverperson.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DExamPaperProblemMapper;
import com.dlzx.pfserverperson.domain.DExamPaperProblem;
import com.dlzx.pfserverperson.service.IDExamPaperProblemService;

/**
 * 驾驶员-试卷问题Service业务层处理
 * 
 * @author dlzx
 * @date 2020-11-19
 */
@Service
public class DExamPaperProblemServiceImpl implements IDExamPaperProblemService 
{
    @Autowired
    private DExamPaperProblemMapper dExamPaperProblemMapper;

    /**
     * 查询驾驶员-试卷问题
     * 
     * @param id 驾驶员-试卷问题ID
     * @return 驾驶员-试卷问题
     */
    @Override
    public DExamPaperProblem selectDExamPaperProblemById(String id)
    {
        return dExamPaperProblemMapper.selectDExamPaperProblemById(id);
    }

    /**
     * 查询驾驶员-试卷问题
     *
     * @param id 驾驶员-试卷ID
     * @return 驾驶员-试卷问题集合
     */
    @Override
    public List<DExamPaperProblem> selectDExamPaperProblemByIdPaper(String id)
    {
        return dExamPaperProblemMapper.selectDExamPaperProblemByIdPaper(id);
    }
    /**
     * 查询驾驶员-试卷问题列表
     * 
     * @param dExamPaperProblem 驾驶员-试卷问题
     * @return 驾驶员-试卷问题
     */
    @Override
    public List<DExamPaperProblem> selectDExamPaperProblemList(DExamPaperProblem dExamPaperProblem)
    {
        return dExamPaperProblemMapper.selectDExamPaperProblemList(dExamPaperProblem);
    }

    /**
     * 新增驾驶员-试卷问题
     * 
     * @param dExamPaperProblem 驾驶员-试卷问题
     * @return 结果
     */
    @Override
    public int insertDExamPaperProblem(DExamPaperProblem dExamPaperProblem)
    {
        return dExamPaperProblemMapper.insertDExamPaperProblem(dExamPaperProblem);
    }

    /**
     * 新增驾驶员-试卷问题
     *
     * @param list 驾驶员-试卷问题集合
     * @return 结果
     */
    @Override
    public int insertDExamPaperProblemList(List<DExamPaperProblem> list)
    {
        return dExamPaperProblemMapper.insertDExamPaperProblemList(list);
    }

    /**
     * 修改驾驶员-试卷问题
     * 
     * @param dExamPaperProblem 驾驶员-试卷问题
     * @return 结果
     */
    @Override
    public int updateDExamPaperProblem(DExamPaperProblem dExamPaperProblem)
    {
        return dExamPaperProblemMapper.updateDExamPaperProblem(dExamPaperProblem);
    }

    /**
     * 批量删除驾驶员-试卷问题
     * 
     * @param ids 需要删除的驾驶员-试卷问题ID
     * @return 结果
     */
    @Override
    public int deleteDExamPaperProblemByIds(String[] ids)
    {
        return dExamPaperProblemMapper.deleteDExamPaperProblemByIds(ids);
    }

    /**
     * 删除驾驶员-试卷问题信息
     * 
     * @param id 驾驶员-试卷问题ID
     * @return 结果
     */
    @Override
    public int deleteDExamPaperProblemById(String id)
    {
        return dExamPaperProblemMapper.deleteDExamPaperProblemById(id);
    }

    /**
     *
     */
    @Override
    public List<DExamPaperProblem> selectDExamPaperProblemByPaperId(String id){
        return dExamPaperProblemMapper.selectDExamPaperProblemByPaperId(id);
    }
}
