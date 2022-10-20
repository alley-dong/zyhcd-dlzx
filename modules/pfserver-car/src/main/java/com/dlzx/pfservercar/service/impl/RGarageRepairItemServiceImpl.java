package com.dlzx.pfservercar.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.text.UUID;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.Common.SysDictionary;
import com.dlzx.pfservercar.mapper.CommonMapper.SysDictionaryCarMapper;
import com.dlzx.system.api.model.LoginUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.RGarageRepairItemMapper;
import com.dlzx.pfservercar.domain.RGarageRepairItem;
import com.dlzx.pfservercar.service.IRGarageRepairItemService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 维修保养-维修站-维修明细Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-12-02
 */
@Service
public class RGarageRepairItemServiceImpl implements IRGarageRepairItemService 
{
    @Autowired
    private RGarageRepairItemMapper rGarageRepairItemMapper;
    @Autowired
    private SysDictionaryCarMapper sysDictionaryMapper;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询维修保养-维修站-维修明细
     * 
     * @param id 维修保养-维修站-维修明细ID
     * @return 维修保养-维修站-维修明细
     */
    @Override
    public RGarageRepairItem selectRGarageRepairItemById(String id)
    {
        return rGarageRepairItemMapper.selectRGarageRepairItemById(id);
    }

    /**
     * 查询维修保养-维修站-维修明细列表
     * 
     * @param rGarageRepairItem 维修保养-维修站-维修明细
     * @return 维修保养-维修站-维修明细
     */
    @Override
    public List<RGarageRepairItem> selectRGarageRepairItemList(RGarageRepairItem rGarageRepairItem)
    {
        return rGarageRepairItemMapper.selectRGarageRepairItemList(rGarageRepairItem);
    }

    /**
     * 新增维修保养-维修站-维修明细
     * 
     * @param rGarageRepairItem 维修保养-维修站-维修明细
     * @return 结果
     */
    @Override
    public int insertRGarageRepairItem(RGarageRepairItem rGarageRepairItem)
    {
        rGarageRepairItem.setCreateTime(DateUtils.getNowDate());
        return rGarageRepairItemMapper.insertRGarageRepairItem(rGarageRepairItem);
    }

    /**
     * 修改维修保养-维修站-维修明细
     * 
     * @param rGarageRepairItem 维修保养-维修站-维修明细
     * @return 结果
     */
    @Override
    public int updateRGarageRepairItem(RGarageRepairItem rGarageRepairItem)
    {
        rGarageRepairItem.setUpdateTime(DateUtils.getNowDate());
        return rGarageRepairItemMapper.updateRGarageRepairItem(rGarageRepairItem);
    }

    /**
     * 批量删除维修保养-维修站-维修明细
     * 
     * @param ids 需要删除的维修保养-维修站-维修明细ID
     * @return 结果
     */
    @Override
    public int deleteRGarageRepairItemByIds(String[] ids)
    {
        return rGarageRepairItemMapper.deleteRGarageRepairItemByIds(ids);
    }

    /**
     * 删除维修保养-维修站-维修明细信息
     * 
     * @param id 维修保养-维修站-维修明细ID
     * @return 结果
     */
    @Override
    public int deleteRGarageRepairItemById(String id)
    {
        return rGarageRepairItemMapper.deleteRGarageRepairItemById(id);
    }

    /**
     * 导入维修明细
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String,String> importRarageRepairItem(List<RGarageRepairItem> rGarageRepairItemList,Boolean isUpdateSupport,String garageId){
        LoginUser userInfo = tokenService.getLoginUser();
        Map<String, String> result = new HashMap<>();
        //判断数据流是否为空
        if (StringUtils.isNull(rGarageRepairItemList) || rGarageRepairItemList.size() == 0) {
            throw new CustomException("导入数据为空！");
        }
        //需要新增的数据
        List<RGarageRepairItem> insList = new ArrayList<>();
        //需要修改的数据
        List<RGarageRepairItem> updList = new ArrayList<>();
        //查询数据表里数据
        List<RGarageRepairItem> rGarageRepairItems = rGarageRepairItemMapper.selectRGarageRepairItemAll();
        List<SysDictionary> sysDictionaryList = sysDictionaryMapper.selectSysDictionaryListByAll(new SysDictionary());
        List<SysDictionary> vehicleTypeAll = new ArrayList<>();
        for(SysDictionary sysDictionary:sysDictionaryList){
            if (sysDictionary.getType()==2&&sysDictionary.getLevel()==2){
                vehicleTypeAll.add(sysDictionary);
            }
        }
        //创建错误信息对象
        StringBuilder errorInfoTypeName = new StringBuilder();
        StringBuilder errorInfoName = new StringBuilder();
        StringBuilder errorInfoUnit = new StringBuilder();
        StringBuilder errorInfoDuration = new StringBuilder();
        StringBuilder errorInfoUnitPrice = new StringBuilder();


        boolean hasError = false;
        int countFlag = 0;
        //判断是否符合要求
        try {
            for (int i = 0; i < rGarageRepairItemList.size(); ++i) {
                boolean f = rGarageRepairItemList.get(i).equals();

                if (f) {
                    break;
                }
                countFlag = i+1;
            }
            for (int i = 0; i < countFlag; i++){
                RGarageRepairItem rgr = rGarageRepairItemList.get(i);
                rgr.setDeleteFlag(Long.valueOf(1));
                String vehicleModelName = rgr.getVehicleModelName();
                //校验
                if (vehicleTypeAll != null){
                SysDictionary sysDictionaryCheckType = vehicleTypeAll.stream().filter(o ->(Objects.nonNull(o.getName()))&& (Objects.equals(o.getName(),vehicleModelName))).findFirst().orElse(null);
                if (null == sysDictionaryCheckType){
                    errorInfoTypeName.append((i + 1) + "行");
                    hasError = true;
                    break;
                }
                else {
                    rgr.setVehicleModel(sysDictionaryCheckType.getId());
                }
                }
                String name = rgr.getName();
                String unit = rgr.getUnit();
                BigDecimal duration = rgr.getDuration();
                BigDecimal unitPrice = rgr.getUnitPrice();

                if (StringUtils.isEmpty(name)){
                    errorInfoName.append((i + 1) + "行");
                    hasError = true;
                    break;
                }
                if (StringUtils.isEmpty(unit)){
                    errorInfoUnit.append((i + 1)+"行");
                    hasError = true;
                    break;
                }
                if (duration ==null){
                    errorInfoDuration.append((i + 1) + "行");
                    hasError = true;
                    break;
                }
                if (unitPrice == null){
                    errorInfoUnitPrice.append((i + 1) + "行");
                    hasError = true;
                    break;
                }
                //判断是否重复
                List<RGarageRepairItem> rGarageRepairItem = rGarageRepairItems.stream().filter(a -> Objects.equals(a.getName(),name)&&Objects.equals(a.getVehicleModelName(),rgr.getVehicleModelName())&&Objects.equals(a.getGarageId(),garageId))
                        .collect(Collectors.toList());
                if (hasError) {
                    continue;
                }
                if (rGarageRepairItem.size()==0){
                    //新增
                    rgr.setId(UUID.randomUUID().toString());
                    rgr.setGarageId(garageId);
                    rgr.setCreateBy(userInfo.getUsername());
                    rgr.setCreateTime(new Date());
                    insList.add(rgr);
                }
                else {
                    rgr.setGarageId(rGarageRepairItem.get(0).getGarageId());
                    rgr.setId(rGarageRepairItem.get(0).getId());
                    rgr.setName(rGarageRepairItem.get(0).getName());
                    rgr.setUpdateBy(userInfo.getUsername());
                    rgr.setUpdateTime(new Date());
                    updList.add(rgr);
                }
            }
            String str = errorInfoName.length() > 0 ? "导入的第" + errorInfoName.toString() + "的位置名称没有填写;" : "";
            String str1 = errorInfoUnit.length() > 0 ? "导入的第" + errorInfoUnit.toString() + "的位置单位没有填写;" : "";
            String str2 = errorInfoDuration.length() > 0 ? "导入的第" + errorInfoDuration.toString() + "的位置工时没有填写;" : "";
            String str3 = errorInfoUnitPrice.length() > 0 ? "导入的第" + errorInfoUnitPrice.toString() + "的位置单价没有填写;" : "";
            String str4 = errorInfoTypeName.length() > 0 ? "导入的第" + errorInfoTypeName.toString() + "的位置车辆类型名称没有填写;" : "";

            if (hasError) {
                String test = ((str==null || "".equals(str))?"":(str +"<br>"))+ ((str1==null || "".equals(str1))?"":(str1 +"<br>")) + ((str2==null || "".equals(str2))?"":(str2 +"<br>")) + ((str3==null || "".equals(str3))?"":(str3 +"<br>"))+((str4==null || "".equals(str4))?"":(str4 +"<br>"));

                result.put("code", "-1");
                result.put("msg", test);
                return result;
            }
            if (insList.size() > 0) {
                insList.forEach(r -> {
                    this.insertRGarageRepairItem(r);
                });
            }
            if (updList.size() > 0) {
                updList.forEach(r -> {
                    this.updateRGarageRepairItem(r);
                });
            }
        }
        catch (Exception e){
            result.put("code", "-1");
            result.put("msg", "系统异常");
            return result;
        }
        result.put("code", "0");
        result.put("msg", "导入成功" );
        return result;
    }

    @Override
    public List<RGarageRepairItem> selectRGarageRepairItemByGarageId(String id,String name){
        return rGarageRepairItemMapper.selectRGarageRepairItemByGarageId(id,name);
    }

    @Override
    public List<RGarageRepairItem> selectVehicleModelByGarage(String id){
        return rGarageRepairItemMapper.selectVehicleModelByGarage(id);
    }
}
