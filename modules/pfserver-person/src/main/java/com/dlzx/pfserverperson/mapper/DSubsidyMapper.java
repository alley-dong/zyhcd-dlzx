package com.dlzx.pfserverperson.mapper;

import java.util.List;
import com.dlzx.pfserverperson.domain.DSubsidy;
import com.dlzx.pfserverperson.domain.DSubsidyAp;

/**
 * 驾驶员-补助Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-08
 */
public interface DSubsidyMapper 
{
    /**
     * 查询驾驶员-补助
     * 
     * @param id 驾驶员-补助ID
     * @return 驾驶员-补助
     */
    public DSubsidy selectDSubsidyById(String id);

    public DSubsidy selectDSubsidyListTongById(String id);
    /**
     * 查询驾驶员-补助列表
     * 
     * @param dSubsidy 驾驶员-补助
     * @return 驾驶员-补助集合
     */
    public List<DSubsidy> selectDSubsidyList(DSubsidy dSubsidy);

    public List<DSubsidy> selectDSubsidyListTong(DSubsidy dSubsidy);
    /**
     * 新增驾驶员-补助
     * 
     * @param dSubsidy 驾驶员-补助
     * @return 结果
     */
    public int insertDSubsidy(DSubsidy dSubsidy);

    /**
     * 修改驾驶员-补助
     * 
     * @param dSubsidy 驾驶员-补助
     * @return 结果
     */
    public int updateDSubsidy(DSubsidy dSubsidy);

    /**
     * 删除驾驶员-补助
     * 
     * @param id 驾驶员-补助ID
     * @return 结果
     */
    public int deleteDSubsidyById(String id);

    /**
     * 批量删除驾驶员-补助
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDSubsidyByIds(String[] ids);

    public int updateDSubsidyStatusById(String id);

    /**
     * @describe 分组查询驾驶员的补助记录
     * @author DongCL
     * @date 2021-04-29 10:40
     * @param
     * @return
     */
    List<DSubsidy> queryDriverSubsidyDate(DSubsidy subsidy);
}
