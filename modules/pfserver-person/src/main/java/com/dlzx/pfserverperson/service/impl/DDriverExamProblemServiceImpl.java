package com.dlzx.pfserverperson.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DDriverExamProblemMapper;
import com.dlzx.pfserverperson.domain.DDriverExamProblem;
import com.dlzx.pfserverperson.service.IDDriverExamProblemService;

/**
 * 驾驶员-司机试卷问题Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-11-18
 */
@Service
public class DDriverExamProblemServiceImpl implements IDDriverExamProblemService 
{
    @Autowired
    private DDriverExamProblemMapper dDriverExamProblemMapper;

    /**
     * 查询驾驶员-司机试卷问题
     * 
     * @param id 驾驶员-司机试卷问题ID
     * @return 驾驶员-司机试卷问题
     */
    @Override
    public DDriverExamProblem selectDDriverExamProblemById(String id)
    {
        return dDriverExamProblemMapper.selectDDriverExamProblemById(id);
    }

    /**
     * 查询驾驶员-司机试卷问题列表
     * 
     * @param dDriverExamProblem 驾驶员-司机试卷问题
     * @return 驾驶员-司机试卷问题
     */
    @Override
    public List<DDriverExamProblem> selectDDriverExamProblemList(DDriverExamProblem dDriverExamProblem)
    {
        return dDriverExamProblemMapper.selectDDriverExamProblemList(dDriverExamProblem);
    }

    /**
     * 新增驾驶员-司机试卷问题
     * 
     * @param dDriverExamProblem 驾驶员-司机试卷问题
     * @return 结果
     */
    @Override
    public int insertDDriverExamProblem(DDriverExamProblem dDriverExamProblem)
    {
        return dDriverExamProblemMapper.insertDDriverExamProblem(dDriverExamProblem);
    }

    /**
     * 新增驾驶员-司机试卷问题
     *
     * @param listExam 驾驶员-司机试卷问题集合
     * @return 结果
     */
    @Override
    public int insertDDriverExamProblemList(List<DDriverExamProblem> listExam)
    {
        return dDriverExamProblemMapper.insertDDriverExamProblemList(listExam);
    }

    /**
     * 修改驾驶员-司机试卷问题
     * 
     * @param dDriverExamProblem 驾驶员-司机试卷问题
     * @return 结果
     */
    @Override
    public int updateDDriverExamProblem(DDriverExamProblem dDriverExamProblem)
    {
        return dDriverExamProblemMapper.updateDDriverExamProblem(dDriverExamProblem);
    }

    /**
     * 批量删除驾驶员-司机试卷问题
     * 
     * @param ids 需要删除的驾驶员-司机试卷问题ID
     * @return 结果
     */
    @Override
    public int deleteDDriverExamProblemByIds(String[] ids)
    {
        return dDriverExamProblemMapper.deleteDDriverExamProblemByIds(ids);
    }

    /**
     * 删除驾驶员-司机试卷问题信息
     * 
     * @param id 驾驶员-司机试卷问题ID
     * @return 结果
     */
    @Override
    public int deleteDDriverExamProblemById(String id)
    {
        return dDriverExamProblemMapper.deleteDDriverExamProblemById(id);
    }

    /**
     * 查看笔试记录试题
     */
    @Override
    public List<DDriverExamProblem> selectDDriverExamProblemByExamId(String id){
        return dDriverExamProblemMapper.selectDDriverExamProblemByExamId(id);
    }
}
