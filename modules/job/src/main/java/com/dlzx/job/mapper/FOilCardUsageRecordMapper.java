package com.dlzx.job.mapper;

import com.dlzx.job.domain.FOilCardUsageRecord;

import java.util.List;

/**
 * 燃料管理-油卡使用记录Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-02
 */
public interface FOilCardUsageRecordMapper 
{
    public List<FOilCardUsageRecord> selectFOilCardUsageRecordListNum(FOilCardUsageRecord fOilCardUsageRecord);

}
