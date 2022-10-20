package com.dlzx.pfserverparts.service;

import java.util.List;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfserverparts.domain.GGoods;
import com.dlzx.pfserverparts.domain.GGoodsCheck;
import com.dlzx.pfserverparts.domain.GGoodsReceivingRecord;
import com.dlzx.pfserverparts.domain.GGoodsType;

/**
 * 库存管理-物品领用记录Service接口
 * 
 * @author dlzx
 * @date 2020-12-09
 */
public interface IGGoodsReceivingRecordService 
{
    /**
     * 查询库存管理-物品领用记录
     * 
     * @param id 库存管理-物品领用记录ID
     * @return 库存管理-物品领用记录
     */
    public GGoodsReceivingRecord selectGGoodsReceivingRecordById(String id);

    // 根据ID 查询
    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordByGGRRId(String id);

    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordByGGRRIId(String id);

    /**
     * 根据goodstypeid查询
     * @param goodsTypeId
     * @return
     */
    public List<GGoods> selectGGoodsReceivingRecordByByGoodsTypeId(String goodsTypeId);

    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordByByGoodsId(String goodsId);

    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordByGoodsIdOrDate(String goodsId);

    public GGoodsReceivingRecord selectGGoodsReceivingRecordInfo(String id);

    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordDrop();

    // k17下拉选
    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordInfoList();

    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordAmount(GGoodsReceivingRecord gGoodsReceivingRecord);
    /**
     * 查询库存管理-物品领用记录列表
     * 
     * @param gGoodsReceivingRecord 库存管理-物品领用记录
     * @return 库存管理-物品领用记录集合
     */
    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordList(GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * 新增库存管理-物品领用记录
     * 
     * @param gGoodsReceivingRecord 库存管理-物品领用记录
     * @return 结果
     */
    public int insertGGoodsReceivingRecord(GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * 库存管理-再次提交物品领用记录
     * 
     * @param gGoodsReceivingRecord 库存管理-物品领用记录
     * @return 结果
     */

    public int submitGGoodsReceivingRecord(GGoodsReceivingRecord gGoodsReceivingRecord);
    /**
     * 修改库存管理-物品领用记录
     *
     * @param gGoodsReceivingRecord 库存管理-物品领用记录
     * @return 结果
     */
    public int updateGGoodsReceivingRecord(GGoodsReceivingRecord gGoodsReceivingRecord);

    // 审批
    public AjaxResult checkCInsuranceVehicle(GGoodsReceivingRecord gGoodsReceivingRecord);
    /**
     * 批量删除库存管理-物品领用记录
     * 
     * @param ids 需要删除的库存管理-物品领用记录ID
     * @return 结果
     */
    public int deleteGGoodsReceivingRecordByIds(String[] ids);

    /**
     * 删除库存管理-物品领用记录信息
     * 
     * @param id 库存管理-物品领用记录ID
     * @return 结果
     */
    public int deleteGGoodsReceivingRecordById(String id);

    /**
     * @describe 撤回
     * @author DongCL
     * @date 2021-01-17 15:57
     * @param
     * @return
     */
    int recallGGoodsReceivingRecord(GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * @describe 默认物品类别
     * @author DongCL 
     * @date 2021-01-19 14:34 
     * @param 
     * @return 
     */
    List<GGoodsType> queryGoodTypeList();

    
    /**
     * @describe 工作餐
     * @author DongCL 
     * @date 2021-01-19 17:04
     * @param 
     * @return 
     */
    int insertGGoodsReceivingRecordDate(GGoodsReceivingRecord gGoodsReceivingRecord);


    /**
     * @describe 轮胎 / 低值易耗品
     * @author DongCL
     * @date 2021-01-19 17:12
     * @param
     * @return
     */
    int insertGGoodsReceivingRecordTyre(GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * @describe 备件
     * @author DongCL
     * @date 2021-01-19 17:21
     * @param
     * @return
     */
    int insertGGoodsReceivingRecordItem(GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * @describe 详情
     * @author DongCL
     * @date 2021-01-20 09:21
     * @param
     * @return
     */
    GGoodsReceivingRecord queryGGoodsReceivingRecordById(String id);

    /**
     * @describe 物品领用列表(驾驶员端)
     * @author DongCL
     * @date 2021-01-20 11:41
     * @param
     * @return
     */
    List<GGoodsReceivingRecord> queryGGoodsReceivingRecordList(GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * @describe 盘点列表
     * @author DongCL
     * @date 2021-01-21 17:42
     * @param
     * @return
     */
    List<GGoodsCheck> queryGGoodsCheckList(GGoodsCheck gGoodsCheck);

    /**
     * @describe 盘点详情
     * @author DongCL
     * @date 2021-01-21 17:42
     * @param
     * @return
     */
    GGoodsCheck queryGGoodsCheckById(GGoodsCheck gGoodsCheck);

    /**
     * @describe 物品领用列表(管理员端)
     * @author DongCL
     * @date 2021-01-20 11:41
     * @param
     * @return
     */
    List<GGoodsReceivingRecord> queryAdminGGoodsReceivingRecordList(GGoodsReceivingRecord gGoodsReceivingRecord);
}
