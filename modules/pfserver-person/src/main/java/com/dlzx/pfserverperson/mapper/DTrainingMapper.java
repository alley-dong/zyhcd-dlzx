package com.dlzx.pfserverperson.mapper;

import java.util.List;
import com.dlzx.pfserverperson.domain.DTraining;
import com.dlzx.pfserverperson.domain.DTrainingUser;

/**
 * 驾驶员-培训Mapper接口
 * 
 * @author dlzx
 * @date 2020-11-26
 */
public interface DTrainingMapper 
{
    /**
     * 查询驾驶员-培训
     * 
     * @param id 驾驶员-培训ID
     * @return 驾驶员-培训
     */
    public DTraining selectDTrainingById(String id);

    /**
     * 查询驾驶员-培训列表
     * 
     * @param dTraining 驾驶员-培训
     * @return 驾驶员-培训集合
     */
    public List<DTraining> selectDTrainingList(DTraining dTraining);


    /**
     * 新增驾驶员-培训
     * 
     * @param dTraining 驾驶员-培训
     * @return 结果
     */
    public int insertDTraining(DTraining dTraining);


    /**
     * 新增驾驶员-培训
     *
     * @param dTraining 驾驶员-培训
     * @return 结果
     */
    public int insertDTrainingBC(DTraining dTraining);

    /**
     * 修改驾驶员-培训
     * 
     * @param dTraining 驾驶员-培训
     * @return 结果
     */
    public int updateDTraining(DTraining dTraining);

    /**
     * 删除驾驶员-培训
     * 
     * @param id 驾驶员-培训ID
     * @return 结果
     */
    public int deleteDTrainingById(String id);

    /**
     * 批量删除驾驶员-培训
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDTrainingByIds(String[] ids);
}
