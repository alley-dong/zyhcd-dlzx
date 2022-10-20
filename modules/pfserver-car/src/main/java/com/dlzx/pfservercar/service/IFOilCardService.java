package com.dlzx.pfservercar.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dlzx.pfservercar.domain.FOilCard;
import org.springframework.web.multipart.MultipartFile;

/**
 * 油卡管理Service接口
 * 
 * @author dlzx
 * @date 2020-12-01
 */
public interface IFOilCardService 
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
     * 批量删除油卡管理
     * 
     * @param ids 需要删除的油卡管理ID
     * @return 结果
     */
    public int deleteFOilCardByIds(String[] ids);

    /**
     * 删除油卡管理信息
     * 
     * @param id 油卡管理ID
     * @return 结果
     */
    public int deleteFOilCardById(String id);

    /**
     * dueTime比date多的天数
     *
     * @param date
     * @param dueTime
     * @return
     */
    int calcDayOffset(Date date, Date dueTime);

    /**
    * excel导入数据判断数据内容是否符合要求
    * */
    Map<String, Integer> importVechicle(List<FOilCard>fOilCards);

    List<FOilCard> queryFOilCardList(FOilCard foilCardByPlate);
}
