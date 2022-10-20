package com.dlzx.pfservercar.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.FFuelPlace;
import com.dlzx.pfservercar.domain.FFuelPlaceItem;
import com.dlzx.pfservercar.domain.FOilCard;
import com.dlzx.pfservercar.mapper.CVehicleMapper;
import com.dlzx.pfservercar.mapper.FFuelPlaceItemMapper;
import com.dlzx.pfservercar.mapper.FFuelPlaceMapper;
import com.dlzx.pfservercar.mapper.FOilCardMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.FOilCardUsageRecordMapper;
import com.dlzx.pfservercar.domain.FOilCardUsageRecord;
import com.dlzx.pfservercar.service.IFOilCardUsageRecordService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 燃料管理-油卡使用记录Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-02
 */
@Service
public class FOilCardUsageRecordServiceImpl implements IFOilCardUsageRecordService 
{
    @Autowired
    private FOilCardUsageRecordMapper fOilCardUsageRecordMapper;
    @Autowired
    private FFuelPlaceItemMapper fuelPlaceItemMapper;
    @Autowired
    private FFuelPlaceMapper fFuelPlaceMapper;
    @Autowired
    private FOilCardMapper fOilCardMapper;
    @Autowired
    private CVehicleMapper cVehicleMapper;


    /**
     * 查询燃料管理-油卡使用记录
     * 
     * @param id 燃料管理-油卡使用记录ID
     * @return 燃料管理-油卡使用记录
     */
    @Override
    public FOilCardUsageRecord selectFOilCardUsageRecordById(String id)
    {
        return fOilCardUsageRecordMapper.selectFOilCardUsageRecordById(id);
    }

    /**
     * 查询燃料管理-油卡使用记录列表
     * 
     * @param fOilCardUsageRecord 燃料管理-油卡使用记录
     * @return 燃料管理-油卡使用记录
     */
    @Override
    public List<FOilCardUsageRecord> selectFOilCardUsageRecordList(FOilCardUsageRecord fOilCardUsageRecord)
    {
        return fOilCardUsageRecordMapper.selectFOilCardUsageRecordList(fOilCardUsageRecord);
    }

    @Override
    public List<FOilCardUsageRecord> selectFOilCardUsageRecordListNum(FOilCardUsageRecord fOilCardUsageRecord)
    {
        return fOilCardUsageRecordMapper.selectFOilCardUsageRecordListNum(fOilCardUsageRecord);
    }
    /**
     * 查询燃料管理-油料使用异常记录列表
     *
     * @param fOilCardUsageRecord 燃料管理-油料使用异常记录列表
     * @return 燃料管理-油料使用异常记录列表
     */
    @Override
    public List<FOilCardUsageRecord> selectFOilCardUsageAbnormalRecordList(FOilCardUsageRecord fOilCardUsageRecord)
    {
        return fOilCardUsageRecordMapper.selectFOilCardUsageAbnormalRecordList(fOilCardUsageRecord);
    }

    /**
     * 新增燃料管理-油卡使用记录
     * 
     * @param fOilCardUsageRecord 燃料管理-油卡使用记录
     * @return 结果
     */
    @Override
    public int insertFOilCardUsageRecord(FOilCardUsageRecord fOilCardUsageRecord)
    {
        fOilCardUsageRecord.setCreateTime(DateUtils.getNowDate());
        return fOilCardUsageRecordMapper.insertFOilCardUsageRecord(fOilCardUsageRecord);
    }

    /**
     * 修改燃料管理-油卡使用记录
     * 
     * @param fOilCardUsageRecord 燃料管理-油卡使用记录
     * @return 结果
     */
    @Override
    public int updateFOilCardUsageRecord(FOilCardUsageRecord fOilCardUsageRecord)
    {
        fOilCardUsageRecord.setUpdateTime(DateUtils.getNowDate());
        return fOilCardUsageRecordMapper.updateFOilCardUsageRecord(fOilCardUsageRecord);
    }

    /**
     * 批量删除燃料管理-油卡使用记录
     * 
     * @param ids 需要删除的燃料管理-油卡使用记录ID
     * @return 结果
     */
    @Override
    public int deleteFOilCardUsageRecordByIds(String[] ids)
    {
        return fOilCardUsageRecordMapper.deleteFOilCardUsageRecordByIds(ids);
    }

    /**
     * 删除燃料管理-油卡使用记录信息
     * 
     * @param id 燃料管理-油卡使用记录ID
     * @return 结果
     */
    @Override
    public int deleteFOilCardUsageRecordById(String id)
    {
        return fOilCardUsageRecordMapper.deleteFOilCardUsageRecordById(id);
    }

    /**
     * excel导入数据判断数据内容是否符合要求
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Integer> importFOilCardUsageRecord(List<FOilCardUsageRecord> fOilCardUsageRecords,String routeId) {

        if (StringUtils.isEmpty(fOilCardUsageRecords) || fOilCardUsageRecords.size()==0){
            throw new CustomException("导入数据为空！");
        }
        if (StringUtils.isEmpty(routeId)){
            throw new CustomException("请选择线路！");
        }
        // 根据线路ID 获取此线路下所有加油站地点
        FFuelPlace fuelPlace = fFuelPlaceMapper.queryFFuelPlaceByRouteId(routeId);
        if (fuelPlace==null){
            throw new CustomException("此线路未关联定点");
        }
        // 根据定点ID 获取定点下的所有加油站
        FFuelPlaceItem fFuelPlaceItem = new FFuelPlaceItem();
        fFuelPlaceItem.setPlaceId(fuelPlace.getId());
        List<FFuelPlaceItem> fFuelPlaceItems = fuelPlaceItemMapper.selectFFuelPlaceItemList(fFuelPlaceItem);
        // 把所有加油站地址地址拿出来
        List<String> itemAddressList = fFuelPlaceItems.stream().map(FFuelPlaceItem::getName).collect(Collectors.toList());


        List<FOilCardUsageRecord> fOilCardUsageRecordImportList = new ArrayList<>();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Date date = new Date();
        for (FOilCardUsageRecord fOilCardUsageRecord : fOilCardUsageRecords) {
            FOilCardUsageRecord fOilCardUsageRecordImport = new FOilCardUsageRecord();
            // 正常
            fOilCardUsageRecordImport.setStatus(2L);
            fOilCardUsageRecordImport.setCreateTime(date);
            fOilCardUsageRecordImport.setTransactionAddress(fOilCardUsageRecord.getTransactionAddress());
            String uuid = UUID.randomUUID() + "";
            fOilCardUsageRecordImport.setId(uuid);
            fOilCardUsageRecord.setId(uuid);
            if (StringUtils.isNull(fOilCardUsageRecord.getConsume())){
                throw new CustomException("金额不能为空！");
            }
            fOilCardUsageRecordImport.setConsume(fOilCardUsageRecord.getConsume());
            if (StringUtils.isNull(fOilCardUsageRecord.getLicensePlate())){
                throw new CustomException("车牌号不能为空！");
            }
            fOilCardUsageRecordImport.setLicensePlate(fOilCardUsageRecord.getLicensePlate());
            if (StringUtils.isNull(fOilCardUsageRecord.getSurplus())){
                throw new CustomException("余额不能为空！");
            }
            fOilCardUsageRecordImport.setSurplus(fOilCardUsageRecord.getSurplus());
            if (StringUtils.isNull(fOilCardUsageRecord.getUnit())){
                throw new CustomException("单位不能为空！");
            }
            fOilCardUsageRecordImport.setUnit(fOilCardUsageRecord.getUnit());
            if (StringUtils.isNull(fOilCardUsageRecord.getTransactionStatus())){
                throw new CustomException("交易状态不能为空！");
            }
            fOilCardUsageRecordImport.setTransactionStatus(fOilCardUsageRecord.getTransactionStatus());
            if (StringUtils.isNull(fOilCardUsageRecord.getTransactionTime())){
                throw new CustomException("交易时间不能为空！");
            }
            fOilCardUsageRecordImport.setTransactionTime(fOilCardUsageRecord.getTransactionTime());
            if (StringUtils.isNull(fOilCardUsageRecord.getProductType())){
                throw new CustomException("商品类别不能为空！");
            }
            fOilCardUsageRecordImport.setProductType(fOilCardUsageRecord.getProductType());
            if (StringUtils.isNull(fOilCardUsageRecord.getProductName())){
                throw new CustomException("商品名称不能为空！");
            }
            fOilCardUsageRecordImport.setProductName(fOilCardUsageRecord.getProductName());
            if (StringUtils.isNull(fOilCardUsageRecord.getOilQuantity())){
                throw new CustomException("油量不能为空！");
            }
            fOilCardUsageRecordImport.setOilQuantity(fOilCardUsageRecord.getOilQuantity());
            if (StringUtils.isNull(fOilCardUsageRecord.getOilCardNumber())){
                throw new CustomException("卡号不能为空！");
            }
            fOilCardUsageRecordImport.setOilCardNumber(fOilCardUsageRecord.getOilCardNumber());

            // 校验加油站是否存在此线路中
            if (!itemAddressList.contains(fOilCardUsageRecord.getTransactionAddress())){
                // 异常
                fOilCardUsageRecordImport.setStatus(1L);
                // 非定点加油站
                fOilCardUsageRecordImport.setExceptionType("1");
            }
            // 是否展示 1是 2否
            fOilCardUsageRecordImport.setIsShowType("1");

            // 校验油卡号是否存在
            FOilCard fOilCards = fOilCardMapper.selectFOilCardByCardNumber(fOilCardUsageRecord.getOilCardNumber());
            if (fOilCards == null){
                throw new CustomException("卡号不存在！");
            }
            fOilCardUsageRecordImport.setOilCardId(fOilCards.getId());

            // 校验车辆是否存在
            CVehicle cVehicle = cVehicleMapper.queryCVehicleByLicensePlate(fOilCardUsageRecord.getLicensePlate());
            if (cVehicle == null){
                throw new CustomException("车辆不存在！");
            }
            fOilCardUsageRecordImport.setVehicleId(cVehicle.getId());

            cal1.setTime(fOilCardUsageRecord.getTransactionTime());
            cal1.add(Calendar.HOUR_OF_DAY,-24);

            cal2.setTime(fOilCardUsageRecord.getTransactionTime());
            cal2.add(Calendar.HOUR_OF_DAY,+24);
            long time1 = cal1.getTime().getTime();
            long time2 = cal2.getTime().getTime();
            // 校验车辆是否重复加油
            long allRecord = fOilCardUsageRecords.stream()
                    .filter(f -> f.getLicensePlate().equals(fOilCardUsageRecord.getLicensePlate()))
                    .filter(f -> f.getTransactionAddress().equals(fOilCardUsageRecord.getTransactionAddress()))
                    .filter(f -> f.getProductType().equals(fOilCardUsageRecord.getProductType()))
                    .filter(f -> f.getTransactionTime().getTime()>time1 && f.getTransactionTime().getTime()<time2)
                    .count();
            if (allRecord>1){

                if (fOilCardUsageRecordImport.getExceptionType()!= null && "1".equals(fOilCardUsageRecordImport.getExceptionType())){
                    FOilCardUsageRecord fOilCardUsageRecordImportType = new FOilCardUsageRecord();
                    BeanUtils.copyProperties(fOilCardUsageRecordImport,fOilCardUsageRecordImportType);
                    fOilCardUsageRecordImportType.setId(UUID.randomUUID()+"");
                    // 是否展示 1是 2否
                    fOilCardUsageRecordImportType.setIsShowType("2");
                    fOilCardUsageRecordImportList.add(fOilCardUsageRecordImportType);
                }

                // 异常
                fOilCardUsageRecordImport.setStatus(1L);
                // 24小时内连续加油
                fOilCardUsageRecordImport.setExceptionType("2");
            }
            fOilCardUsageRecordImportList.add(fOilCardUsageRecordImport);
        }
        Integer result= null;
        try {
            result = fOilCardUsageRecordMapper.insertList(fOilCardUsageRecordImportList);
        } catch (Exception e) {
            throw e;
        }
        Long count = fOilCardUsageRecordImportList.stream().filter(f -> f.getStatus() == 1).count();
        Map<String, Integer> map = new HashMap<>();
        map.put("code",result);
        map.put("count",count.intValue());
        return map;
    }
}
