package com.dlzx.pfservercar.service.impl;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.FOilCard;
import com.dlzx.pfservercar.mapper.CVehicleMapper;
import com.dlzx.pfservercar.mapper.FOilCardMapper;
import com.dlzx.pfservercar.mapper.SysDictDataMapper;
import com.dlzx.pfservercar.service.IFOilCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 油卡管理Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-01
 */
@Service
public class FOilCardServiceImpl implements IFOilCardService 
{
    @Autowired
    private FOilCardMapper fOilCardMapper;
    @Autowired
    private CVehicleMapper cVehicleMapper;
    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 查询油卡管理
     * 
     * @param id 油卡管理ID
     * @return 油卡管理
     */
    @Override
    public FOilCard selectFOilCardById(String id)
    {
        FOilCard fOilCard = fOilCardMapper.selectFOilCardById(id);
        fOilCard.setOilsEnum(sysDictDataMapper.selectDictLabel("f_oil_type",fOilCard.getOils().toString()));
        return fOilCard;
    }

    /**
     * 查询油卡管理列表
     * 
     * @param fOilCard 油卡管理
     * @return 油卡管理
     */
    @Override
    public List<FOilCard> selectFOilCardList(FOilCard fOilCard)
    {
        return fOilCardMapper.selectFOilCardList(fOilCard);
    }

/**
     * 查询油卡管理列表
     *
     * @param fOilCard 油卡管理
     * @return 油卡管理
     */
    @Override
    public List<FOilCard> queryFOilCardList(FOilCard fOilCard)
    {
        return fOilCardMapper.queryFOilCardList(fOilCard);
    }

    /**
     * 新增油卡管理
     * 
     * @param fOilCard 油卡管理
     * @return 结果
     */
    @Override
    public int insertFOilCard(FOilCard fOilCard)
    {
        fOilCard.setCreateTime(DateUtils.getNowDate());
        return fOilCardMapper.insertFOilCard(fOilCard);
    }

    /**
     * 修改油卡管理
     * 
     * @param fOilCard 油卡管理
     * @return 结果
     */
    @Override
    public int updateFOilCard(FOilCard fOilCard)
    {
        fOilCard.setUpdateTime(DateUtils.getNowDate());
        return fOilCardMapper.updateFOilCard(fOilCard);
    }

    /**
     * 批量删除油卡管理
     * 
     * @param ids 需要删除的油卡管理ID
     * @return 结果
     */
    @Override
    public int deleteFOilCardByIds(String[] ids)
    {
        return fOilCardMapper.deleteFOilCardByIds(ids);
    }

    /**
     * 删除油卡管理信息
     * 
     * @param id 油卡管理ID
     * @return 结果
     */
    @Override
    public int deleteFOilCardById(String id)
    {
        return fOilCardMapper.deleteFOilCardById(id);
    }

    /**
     * dueTime比date多的天数
     *
     * @param date
     * @param dueTime
     * @return
     */
    @Override
    public int calcDayOffset(Date date, Date dueTime) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(dueTime);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        //同一年
        if (year1 != year2) {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    //闰年
                    timeDistance += 366;
                } else {
                    //不是闰年
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else {
            //不同年
            return day2 - day1;
        }
    }

    /**
     * excel导入数据判断数据内容是否符合要求
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Integer> importVechicle(List<FOilCard>fOilCards){

        if (StringUtils.isEmpty(fOilCards) || fOilCards.size()==0){
            throw new CustomException("导入数据为空！");
        }
        // 校验导入车牌号是否存在
        List<String> licensePlates = fOilCards.stream().map(FOilCard::getLicensePlate).collect(Collectors.toList());
        List<CVehicle> cVehicles = cVehicleMapper.selectCVehicleAll();
        List<String> cVehiclesName = cVehicles.stream().map(CVehicle::getLicensePlate).collect(Collectors.toList());
        for (String licensePlate : licensePlates) {
            if (!cVehiclesName.contains(licensePlate)){
                throw new CustomException("导入失败," +licensePlate+"车牌号不存在");
            }
        }
        Date date = new Date();
        for (FOilCard fOilCard : fOilCards) {
            fOilCard.setId(UUID.randomUUID()+"");
            fOilCard.setCreateTime(date);
            String dictValue = sysDictDataMapper.querySysDictLabelData(fOilCard.getOilsEnum(), "f_oil_type");

            long count = fOilCards.stream().filter(f -> f.getLicensePlate().equals(fOilCard.getLicensePlate())).count();
            if (count>=2){
                throw new CustomException("车牌号："+ fOilCard.getLicensePlate() +"重复");
            }

            FOilCard foilCardByPlate = new FOilCard();
            foilCardByPlate.setLicensePlate(fOilCard.getLicensePlate());
            List<FOilCard> list = fOilCardMapper.selectFOilCardList(foilCardByPlate);
            if (list.size()>0){
                throw new CustomException("车牌号："+ fOilCard.getLicensePlate() +"已绑定油卡，不能重复绑定");
            }

            FOilCard foilCardByCard = new FOilCard();
            foilCardByCard.setOilCardNumber(fOilCard.getOilCardNumber());
            List<FOilCard> list1 = fOilCardMapper.importList(foilCardByCard);
            if (list1.size()>0){
                throw new CustomException("油卡号："+ fOilCard.getOilCardNumber() +"已被绑定，不能重复绑定");
            }

            if (StringUtils.isEmpty(dictValue)){
                throw new CustomException("导入失败," +fOilCard.getOilsEnum()+"油品不存在");
            }
            if (StringUtils.isEmpty(fOilCard.getOilCardNumber())){
                throw new CustomException("导入失败,油卡卡号不能为空");
            }
            if (StringUtils.isEmpty(fOilCard.getCardTime().toString())){
                throw new CustomException("导入失败,办卡时间不能为空");
            }
            if (StringUtils.isEmpty(fOilCard.getDueTime().toString())){
                throw new CustomException("导入失败,到期时间不能为空");
            }
            // 获取字典值
            fOilCard.setOils(Long.valueOf(dictValue));
            CVehicle cVehicle = cVehicleMapper.queryCVehicleByLicensePlate(fOilCard.getLicensePlate());
            fOilCard.setVehicleId(cVehicle==null ? "" :cVehicle.getId());
        }

        Integer result= null;
        try {
            result = fOilCardMapper.insertList(fOilCards);
        } catch (Exception e) {
            throw e;
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("code",result);
        return map;
    }
}
