package com.dlzx.pfserverperson.mapper;

import java.util.List;
import com.dlzx.pfserverperson.domain.DExamProblem;

/**
 * 驾驶员-题库Mapper接口
 * 
 * @author dlzx
 * @date 2020-11-18
 */
public interface DExamProblemMapper 
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
     * 删除驾驶员-题库
     * 
     * @param id 驾驶员-题库ID
     * @return 结果
     */
    public int deleteDExamProblemById(String id);

    /**
     * 批量删除驾驶员-题库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDExamProblemByIds(String[] ids);
}
