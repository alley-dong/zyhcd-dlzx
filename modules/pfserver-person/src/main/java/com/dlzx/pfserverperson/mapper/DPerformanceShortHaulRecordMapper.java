package com.dlzx.pfserverperson.mapper;

import java.util.List;

import com.dlzx.pfserverperson.domain.DPerformanceShortHaulRecord;

/**
 * 驾驶员-短途绩效记录Mapper接口
 *
 * @author dlzx
 * @date 2020-12-21
 */
public interface DPerformanceShortHaulRecordMapper {
    /**
     * 查询驾驶员-短途绩效记录
     *
     * @param id 驾驶员-短途绩效记录ID
     * @return 驾驶员-短途绩效记录
     */
    public DPerformanceShortHaulRecord selectDPerformanceShortHaulRecordById(String id);

    /**
     * 查询驾驶员-短途绩效记录列表
     *
     * @param dPerformanceShortHaulRecord 驾驶员-短途绩效记录
     * @return 驾驶员-短途绩效记录集合
     */
    public List<DPerformanceShortHaulRecord> selectDPerformanceShortHaulRecordList(DPerformanceShortHaulRecord dPerformanceShortHaulRecord);

    /**
     * 新增驾驶员-短途绩效记录
     *
     * @param dPerformanceShortHaulRecord 驾驶员-短途绩效记录
     * @return 结果
     */
    public int insertDPerformanceShortHaulRecord(DPerformanceShortHaulRecord dPerformanceShortHaulRecord);

    //
    public int insertDPerformanceShortHaulRecordList(List<DPerformanceShortHaulRecord> list);

    /**
     * 修改驾驶员-短途绩效记录
     *
     * @param dPerformanceShortHaulRecord 驾驶员-短途绩效记录
     * @return 结果
     */
    public int updateDPerformanceShortHaulRecord(DPerformanceShortHaulRecord dPerformanceShortHaulRecord);

    /**
     * 删除驾驶员-短途绩效记录
     *
     * @param id 驾驶员-短途绩效记录ID
     * @return 结果
     */
    public int deleteDPerformanceShortHaulRecordById(String id);

    /**
     * 批量删除驾驶员-短途绩效记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDPerformanceShortHaulRecordByIds(String[] ids);

    /**
     * 统计所有短途司机的绩效工资
     * @param dPerformanceShortHaulRecord
     * @return
     */
    public List<DPerformanceShortHaulRecord> selectDPerShortSalary(DPerformanceShortHaulRecord dPerformanceShortHaulRecord);
}
