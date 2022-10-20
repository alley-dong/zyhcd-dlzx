package com.dlzx.pfserverperson.service;

import java.util.List;
import com.dlzx.pfserverperson.domain.DExamPaperProblem;

/**
 * 驾驶员-试卷问题Service接口
 * 
 * @author dlzx
 * @date 2020-11-19
 */
public interface IDExamPaperProblemService 
{
    /**
     * 查询驾驶员-试卷问题
     * 
     * @param id 驾驶员-试卷问题ID
     * @return 驾驶员-试卷问题
     */
    public DExamPaperProblem selectDExamPaperProblemById(String id);

    /**
     * 查询驾驶员-试卷问题
     *
     * @param id 驾驶员-试卷ID
     * @return 驾驶员-试卷问题集合
     */
    public List<DExamPaperProblem> selectDExamPaperProblemByIdPaper(String id);

    /**
     * 查询驾驶员-试卷问题列表
     * 
     * @param dExamPaperProblem 驾驶员-试卷问题
     * @return 驾驶员-试卷问题集合
     */
    public List<DExamPaperProblem> selectDExamPaperProblemList(DExamPaperProblem dExamPaperProblem);

    /**
     * 新增驾驶员-试卷问题
     * 
     * @param dExamPaperProblem 驾驶员-试卷问题
     * @return 结果
     */
    public int insertDExamPaperProblem(DExamPaperProblem dExamPaperProblem);

    /**
     * 新增驾驶员-试卷问题
     *
     * @param list 驾驶员-试卷问题集合
     * @return 结果
     */
    public int insertDExamPaperProblemList(List<DExamPaperProblem> list);
    /**
     * 修改驾驶员-试卷问题
     * 
     * @param dExamPaperProblem 驾驶员-试卷问题
     * @return 结果
     */
    public int updateDExamPaperProblem(DExamPaperProblem dExamPaperProblem);

    /**
     * 批量删除驾驶员-试卷问题
     * 
     * @param ids 需要删除的驾驶员-试卷问题ID
     * @return 结果
     */
    public int deleteDExamPaperProblemByIds(String[] ids);

    /**
     * 删除驾驶员-试卷问题信息
     * 
     * @param id 驾驶员-试卷问题ID
     * @return 结果
     */
    public int deleteDExamPaperProblemById(String id);

    /**
     *
     */
    public List<DExamPaperProblem> selectDExamPaperProblemByPaperId(String id);
}
