package com.dlzx.pfserverparts.mapper;


import com.dlzx.pfserverparts.domain.GGoodsReceivingRecord;

import java.util.List;

/**
 * 库存管理-物品领用记录Mapper接口
 *
 * @author dlzx
 * @date 2020-12-09
 */
public interface GGoodsReceivingRecordMapper
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
    // 根据ID 查询备件item
    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordByGGRRIId(String id);

    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordByGoodsTypeId(String goodsTypeId);

    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordByGoodsId(String goodsId);

    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordByGoodsIdOrDate(String goodsId);

    public GGoodsReceivingRecord selectGGoodsReceivingRecordInfo(String id);

    // m11下拉选
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
     * 修改库存管理-物品领用记录
     *
     * @param gGoodsReceivingRecord 库存管理-物品领用记录
     * @return 结果
     */
    public int updateGGoodsReceivingRecord(GGoodsReceivingRecord gGoodsReceivingRecord);

    public int checkCInsuranceVehicle(GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * 删除库存管理-物品领用记录
     *
     * @param id 库存管理-物品领用记录ID
     * @return 结果
     */
    public int deleteGGoodsReceivingRecordById(String id);

    /**
     * 批量删除库存管理-物品领用记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGGoodsReceivingRecordByIds(String[] ids);

    /**
     * @describe 物品领用列表
     * @author DongCL
     * @date 2021-01-20 11:41
     * @param
     * @return
     */
    List<GGoodsReceivingRecord> queryGGoodsReceivingRecordList(GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * @describe 详情
     * @author DongCL
     * @date 2021-01-20 13:17
     * @param
     * @return
     */
    GGoodsReceivingRecord queryGGoodsReceivingRecordById(String id);
}
