package com.dlzx.job.mapper;

import com.dlzx.job.domain.DTrainingAp;
/**
 * 驾驶员-培训人员关联Mapper接口
 * 
 * @author dlzx
 * @date 2020-11-26
 */
public interface DTrainingUserMapper 
{
    public int updateDTrainingStatus(DTrainingAp dTrainingAp);
}
