package com.dlzx.pfserverperson.mapper;

import java.util.List;
import com.dlzx.pfserverperson.domain.DDriverExam;

/**
 * 驾驶员-司机考试Mapper接口
 * 
 * @author yangluhe
 * @date 2020-11-18
 */
public interface DDriverExamMapper 
{
    /**
     * 查询驾驶员-司机考试
     * 
     * @param id 驾驶员-司机考试ID
     * @return 驾驶员-司机考试
     */
    public DDriverExam selectDDriverExamById(String id);

    /**
     * 查询驾驶员-司机考试列表
     * 
     * @param dDriverExam 驾驶员-司机考试
     * @return 驾驶员-司机考试集合
     */
    public List<DDriverExam> selectDDriverExamList(DDriverExam dDriverExam);

    /**
     * 查询驾驶员-司机考试列表
     *
     * @param dDriverExam 驾驶员-司机考试
     * @return 驾驶员-司机考试集合
     */
    public List<DDriverExam> selectDDriverExamListByPaperId(DDriverExam dDriverExam);
    /**
     * 新增驾驶员-司机考试
     * 
     * @param dDriverExam 驾驶员-司机考试
     * @return 结果
     */
    public int insertDDriverExam(DDriverExam dDriverExam);

    /**
     * 新增驾驶员-司机考试
     *
     * @param list 驾驶员-司机考试集合
     * @return 结果
     */
    public int insertDDriverExamList(List<DDriverExam> list);
    /**
     * 修改驾驶员-司机考试
     * 
     * @param dDriverExam 驾驶员-司机考试
     * @return 结果
     */
    public int updateDDriverExam(DDriverExam dDriverExam);

    /**
     * 删除驾驶员-司机考试
     * 
     * @param id 驾驶员-司机考试ID
     * @return 结果
     */
    public int deleteDDriverExamById(String id);

    /**
     * 批量删除驾驶员-司机考试
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDDriverExamByIds(String[] ids);

    public int deleteDDriverExamByIdList(List<String> list);
    /**
     * 查看笔试记录分数
     */
    public DDriverExam selectDDriverExamByDriverId(String id);
    /**
     * 查驾驶员所对应的距离当前时间最近的试卷
     */
    public DDriverExam selectDDriverExamByDriverIdNearQ(String userId);
}
