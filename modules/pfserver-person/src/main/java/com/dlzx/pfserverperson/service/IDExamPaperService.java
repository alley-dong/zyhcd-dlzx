package com.dlzx.pfserverperson.service;

import java.util.List;
import com.dlzx.pfserverperson.domain.DExamPaper;

/**
 * 驾驶员-试卷Service接口
 * 
 * @author dlzx
 * @date 2020-11-18
 */
public interface IDExamPaperService 
{
    /**
     * 查询驾驶员-试卷
     * 
     * @param id 驾驶员-试卷ID
     * @return 驾驶员-试卷
     */
    public DExamPaper selectDExamPaperById(String id);

    /**
     * 查询驾驶员-试卷列表
     * 
     * @param dExamPaper 驾驶员-试卷
     * @return 驾驶员-试卷集合
     */
    public List<DExamPaper> selectDExamPaperList(DExamPaper dExamPaper);

    /**
     * 新增驾驶员-试卷
     * 
     * @param dExamPaper 驾驶员-试卷
     * @return 结果
     */
    public int insertDExamPaper(DExamPaper dExamPaper);

    /**
     * 修改驾驶员-试卷
     * 
     * @param dExamPaper 驾驶员-试卷
     * @return 结果
     */
    public int updateDExamPaper(DExamPaper dExamPaper);

    /**
     * 批量删除驾驶员-试卷
     * 
     * @param ids 需要删除的驾驶员-试卷ID
     * @return 结果
     */
    public int deleteDExamPaperByIds(String[] ids);

    /**
     * 删除驾驶员-试卷信息
     * 
     * @param id 驾驶员-试卷ID
     * @return 结果
     */
    public int deleteDExamPaperById(String id);
}
