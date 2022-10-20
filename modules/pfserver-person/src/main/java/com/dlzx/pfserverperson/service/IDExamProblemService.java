package com.dlzx.pfserverperson.service;

import java.util.List;
import java.util.Map;

import com.dlzx.pfserverperson.domain.DDriverExam;
import com.dlzx.pfserverperson.domain.DExamProblem;

/**
 * 驾驶员-题库Service接口
 * 
 * @author dlzx
 * @date 2020-11-18
 */
public interface IDExamProblemService 
{
    /**
     * 查询驾驶员-题库
     * 
     * @param id 驾驶员-题库ID
     * @return 驾驶员-题库
     */
    public DExamProblem selectDExamProblemById(String id);

    /**
     * 查询驾驶员-题库列表
     * 
     * @param dExamProblem 驾驶员-题库
     * @return 驾驶员-题库集合
     */
    public List<DExamProblem> selectDExamProblemList(DExamProblem dExamProblem);

    /**
     * 查询驾驶员-题库列表
     *
     * @return 驾驶员-题库集合
     */
    public List<DExamProblem> selectDExamProblemList1();

    /**
     * 新增驾驶员-题库
     * 
     * @param dExamProblem 驾驶员-题库
     * @return 结果
     */
    public int insertDExamProblem(DExamProblem dExamProblem);

    /**
     * 修改驾驶员-题库
     * 
     * @param dExamProblem 驾驶员-题库
     * @return 结果
     */
    public int updateDExamProblem(DExamProblem dExamProblem);

    /**
     * 批量删除驾驶员-题库
     * 
     * @param ids 需要删除的驾驶员-题库ID
     * @return 结果
     */
    public int deleteDExamProblemByIds(String[] ids);

    /**
     * 删除驾驶员-题库信息
     * 
     * @param id 驾驶员-题库ID
     * @return 结果
     */
    public int deleteDExamProblemById(String id);

    /**
     * 导入驾驶员-题库信息
     *
     * @param problemList 试题集合
     * @return 结果
     */
    public Map<String,String> importUser(List<DExamProblem> problemList);
}
