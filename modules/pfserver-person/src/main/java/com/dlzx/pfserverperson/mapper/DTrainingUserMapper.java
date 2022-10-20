package com.dlzx.pfserverperson.mapper;

import java.util.List;


import com.dlzx.pfserverperson.domain.DTrainingAp;
import com.dlzx.pfserverperson.domain.DTrainingUser;

/**
 * 驾驶员-培训人员关联Mapper接口
 * 
 * @author dlzx
 * @date 2020-11-26
 */
public interface DTrainingUserMapper 
{
    /**
     * 查询驾驶员-培训人员关联
     * 
     * @param id 驾驶员-培训人员关联ID
     * @return 驾驶员-培训人员关联
     */
    public DTrainingUser selectDTrainingUserById(String id);

    public List<DTrainingUser> selectDTrainingUserByTrainingId(String trainingId);
    /**
     * 查询驾驶员-培训人员关联列表
     * 
     * @param dTrainingUser 驾驶员-培训人员关联
     * @return 驾驶员-培训人员关联集合
     */
    public List<DTrainingUser> selectDTrainingUserList(DTrainingUser dTrainingUser);

    public List<DTrainingUser> selectDTrainingUserTwoList(DTrainingUser dTrainingUser);
    /**
     * 新增驾驶员-培训人员关联
     * 
     * @param dTrainingUser 驾驶员-培训人员关联
     * @return 结果
     */
    public int insertDTrainingUser(DTrainingUser dTrainingUser);

    public int insertDTrainingUserS(List<DTrainingUser> userList);
    /**
     * 修改驾驶员-培训人员关联
     * 
     * @param dTrainingUser 驾驶员-培训人员关联
     * @return 结果
     */
    public int updateDTrainingUser(DTrainingUser dTrainingUser);

    public int updateDTrainingStatus(DTrainingAp dTrainingAp);
    /**
     * 删除驾驶员-培训人员关联
     * 
     * @param id 驾驶员-培训人员关联ID
     * @return 结果
     */
    public int deleteDTrainingUserById(String id);

    public int deleteDTrainingUserByTrainingId(String trainingid);
    /**
     * 批量删除驾驶员-培训人员关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDTrainingUserByIds(String[] ids);

    public List<DTrainingAp> selectDTrainingList(DTrainingAp dTrainingAp);
}
