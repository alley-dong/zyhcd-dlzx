package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.FOilCard;
import org.apache.ibatis.annotations.Param;

/**
 * 油卡管理Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-01
 */
public interface FOilCardMapper 
{
    /**
     * 查询油卡管理
     * 
     * @param id 油卡管理ID
     * @return 油卡管理
     */
    public FOilCard selectFOilCardById(String id);

    /**
     * 查询油卡管理列表
     * 
     * @param fOilCard 油卡管理
     * @return 油卡管理集合
     */
    public List<FOilCard> selectFOilCardList(FOilCard fOilCard);

    /**
     * 新增油卡管理
     * 
     * @param fOilCard 油卡管理
     * @return 结果
     */
    public int insertFOilCard(FOilCard fOilCard);

    /**
     * 修改油卡管理
     * 
     * @param fOilCard 油卡管理
     * @return 结果
     */
    public int updateFOilCard(FOilCard fOilCard);

    /**
     * 删除油卡管理
     * 
     * @param id 油卡管理ID
     * @return 结果
     */
    public int deleteFOilCardById(String id);

    /**
     * 批量删除油卡管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFOilCardByIds(String[] ids);


    /**
     * 批量插入
    * */
    int insertList(List<FOilCard> fOilCards);

    /**
     * 根据卡号去获取油卡信息
    * */
    FOilCard selectFOilCardByCardNumber(String oilCardNumber);


    List<FOilCard> queryFOilCardList(FOilCard fOilCard);

    List<FOilCard> importList(FOilCard foilCardByCard);
}
