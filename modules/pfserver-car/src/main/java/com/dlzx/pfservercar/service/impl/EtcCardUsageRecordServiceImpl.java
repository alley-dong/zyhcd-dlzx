package com.dlzx.pfservercar.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.*;
import com.dlzx.pfservercar.mapper.CVehicleMapper;
import com.dlzx.pfservercar.mapper.EtcCardMapper;
import com.dlzx.pfservercar.mapper.TTransportTaskMapper;
import com.dlzx.pfservercar.service.ICVehicleService;
import com.dlzx.pfservercar.service.IEtcCardService;
import com.dlzx.system.api.model.LoginUser;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.EtcCardUsageRecordMapper;
import com.dlzx.pfservercar.service.IEtcCardUsageRecordService;
import org.springframework.transaction.annotation.Transactional;

/**
 * ETC管理-ETC卡使用记录Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-02
 */
@Service
public class EtcCardUsageRecordServiceImpl implements IEtcCardUsageRecordService 
{
    @Autowired
    private EtcCardUsageRecordMapper etcCardUsageRecordMapper;
    @Autowired
    private CVehicleMapper cVehicleMapper;
    @Autowired
    private EtcCardMapper etcCardMapper;
    @Autowired
    private TTransportTaskMapper tTransportTaskMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ICVehicleService cVehicleService;
    @Autowired
    private IEtcCardService etcCardService;
    /**
     * 查询ETC管理-ETC卡使用记录
     * 
     * @param id ETC管理-ETC卡使用记录ID
     * @return ETC管理-ETC卡使用记录
     */
    @Override
    public EtcCardUsageRecord selectEtcCardUsageRecordById(String id)
    {
        return etcCardUsageRecordMapper.selectEtcCardUsageRecordById(id);
    }

    /**
     * 查询ETC管理-ETC卡使用记录列表
     * 
     * @param etcCardUsageRecord ETC管理-ETC卡使用记录
     * @return ETC管理-ETC卡使用记录
     */
    @Override
    public List<EtcCardUsageRecord> selectEtcCardUsageRecordList(EtcCardUsageRecord etcCardUsageRecord)
    {
        return etcCardUsageRecordMapper.selectEtcCardUsageRecordList(etcCardUsageRecord);
    }

    @Override
    public List<EtcCardUsageRecord> selectEtcCardUsageRecordListTime(EtcCardUsageRecord etcCardUsageRecord)
    {
        return etcCardUsageRecordMapper.selectEtcCardUsageRecordListTime(etcCardUsageRecord);
    }

    @Override
    public List<EtcCardUsageRecord> selectEtcCardUsageRecordListAll()
    {
        return etcCardUsageRecordMapper.selectEtcCardUsageRecordListAll();
    }
    /**
     * 新增ETC管理-ETC卡使用记录
     * 
     * @param etcCardUsageRecord ETC管理-ETC卡使用记录
     * @return 结果
     */
    @Override
    public int insertEtcCardUsageRecord(EtcCardUsageRecord etcCardUsageRecord)
    {
        etcCardUsageRecord.setCreateTime(DateUtils.getNowDate());
        return etcCardUsageRecordMapper.insertEtcCardUsageRecord(etcCardUsageRecord);
    }

    /**
     * 修改ETC管理-ETC卡使用记录
     * 
     * @param etcCardUsageRecord ETC管理-ETC卡使用记录
     * @return 结果
     */
    @Override
    public int updateEtcCardUsageRecord(EtcCardUsageRecord etcCardUsageRecord)
    {
        etcCardUsageRecord.setUpdateTime(DateUtils.getNowDate());
        return etcCardUsageRecordMapper.updateEtcCardUsageRecord(etcCardUsageRecord);
    }

    /**
     * 批量删除ETC管理-ETC卡使用记录
     * 
     * @param ids 需要删除的ETC管理-ETC卡使用记录ID
     * @return 结果
     */
    @Override
    public int deleteEtcCardUsageRecordByIds(String[] ids)
    {
        return etcCardUsageRecordMapper.deleteEtcCardUsageRecordByIds(ids);
    }

    /**
     * 删除ETC管理-ETC卡使用记录信息
     * 
     * @param id ETC管理-ETC卡使用记录ID
     * @return 结果
     */
    @Override
    public int deleteEtcCardUsageRecordById(String id)
    {
        return etcCardUsageRecordMapper.deleteEtcCardUsageRecordById(id);
    }

    @Transactional
    @Override
    public Map<String, String> importUser(List<EtcCardUsageRecordImport> recordList) {
        Map<String, String> result = new HashMap<>();

        if (StringUtils.isNull(recordList) || recordList.size() == 0) {
            throw new CustomException("导入数据为空！");
        }
        //需要新增的数据
        List<EtcCardUsageRecord> insList = new ArrayList<>();
        //需要修改的数据
        List<EtcCardUsageRecord> updList = new ArrayList<>();

        List<EtcCardUsageRecord> listA = etcCardUsageRecordMapper.selectEtcCardUsageRecordListAll();
        List<CVehicle> listB = cVehicleMapper.selectCVehicleListAll();
        List<EtcCard> listC = etcCardMapper.selectEtcCardListAll();
        TTransportTask tTransportTask=new TTransportTask();
        List<TTransportTask> listD=tTransportTaskMapper.selectTTransportTaskListEtcUse(tTransportTask);

        StringBuilder errorInfo = new StringBuilder();
        StringBuilder errorInfo1 = new StringBuilder();
        StringBuilder errorInfo2 = new StringBuilder();
        StringBuilder errorInfo3 = new StringBuilder();
        StringBuilder errorInfo4 = new StringBuilder();
        StringBuilder errorInfo5 = new StringBuilder();
        StringBuilder errorInfo6 = new StringBuilder();
        StringBuilder errorInfo7 = new StringBuilder();
        StringBuilder errorInfo8 = new StringBuilder();
        StringBuilder errorInfo9 = new StringBuilder();
        boolean hasError = false;
        int countFlag = 0;

        try {
            for (int i = 0; i < recordList.size(); i++) {
                boolean f = recordList.get(i).equals();

                if (f) {
                    break;
                }
                countFlag = i+1;
            }
            for (int i = 0; i < countFlag; i++) {
                EtcCardUsageRecordImport pro = recordList.get(i);

                String LicensePlate = pro.getLicensePlate();
                String EtcCardNumber = pro.getEtcCardNumber();
                BigDecimal Amount = pro.getAmount();
                Date InboundTime = pro.getInboundTime();
                Date OutboundTime = pro.getOutboundTime();
                String RoadSection = pro.getRoadSection();



                if (StringUtils.isEmpty(LicensePlate)) {
                    errorInfo.append((i + 2) + "行");
                    hasError = true;
                    break;
                }

                if (StringUtils.isEmpty(EtcCardNumber)) {
                    errorInfo1.append((i + 2) + "行");
                    hasError = true;
                    break;
                }

                if (Amount == null) {
                    errorInfo2.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                if (InboundTime == null) {
                    errorInfo3.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                if (OutboundTime == null) {
                    errorInfo4.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                if (StringUtils.isEmpty(RoadSection)) {
                    errorInfo5.append((i + 2) + "行");
                    hasError = true;
                    break;
                }

                int x=InboundTime.compareTo(OutboundTime);
                if (x>0) {
                    errorInfo6.append((i + 2) + "行");
                    hasError = true;
                    break;
                }

                List<CVehicle> listCar = listB.stream().filter(b -> Objects.equals(LicensePlate, b.getLicensePlate())).collect(Collectors.toList());
                if (listCar.size() == 0) {
                    errorInfo7.append((i + 2) + "行");
                    hasError = true;
                    break;
                }

                List<EtcCard> list5 = listC.stream().filter(a -> Objects.equals(LicensePlate, a.getLicensePlate()) ).collect(Collectors.toList());
                if (list5.size() == 0) {
                    errorInfo9.append((i + 2) + "行");
                    hasError = true;
                    break;
                }

                List<EtcCard> list3 = listC.stream().filter(a -> Objects.equals(LicensePlate, a.getLicensePlate()) && Objects.equals(EtcCardNumber, a.getEtcCardNumber())).collect(Collectors.toList());
                if (list3.size() == 0) {
                    errorInfo8.append((i + 2) + "行");
                    hasError = true;
                    break;
                }

                if (hasError) {
                    continue;
                }

                List<TTransportTask> listRenWu =listD.stream().filter(a -> Objects.equals(LicensePlate, a.getMainLicensePlate()) && InboundTime.compareTo(a.getTaskStartTime()) > 0 && InboundTime.compareTo(a.getTaskEndTime()) <0 && a.getDeleteFlag()==1 ).collect(Collectors.toList());

                List<EtcCardUsageRecord> list4 = listA.stream().filter(a -> Objects.equals(LicensePlate, a.getLicensePlate()) && Objects.equals(EtcCardNumber, a.getEtcCardNumber()) && a.getAmount().compareTo(Amount) ==0
                        && Objects.equals(InboundTime, a.getInboundTime()) && Objects.equals(OutboundTime, a.getOutboundTime()) && Objects.equals(RoadSection, a.getRoadSection())).collect(Collectors.toList());

                CVehicle cVehicle = new CVehicle();
                cVehicle.setLicensePlate(LicensePlate);
                List<CVehicle> list = cVehicleService.selectCVehicleList(cVehicle);
                String id = list.get(0).getId();

                EtcCard etcCard =new EtcCard();
                etcCard.setLicensePlate(LicensePlate);
                List<EtcCard> list1 =etcCardService.selectEtcCardList(etcCard);
                String eId =list1.get(0).getId();
                LoginUser userInfo = tokenService.getLoginUser();

                if (list4.size() == 0) {
                    String Id = UUID.randomUUID().toString();
                    EtcCardUsageRecord ppo = new EtcCardUsageRecord();
                    ppo.setId(Id);
                    ppo.setCreateBy(userInfo.getUsername());
                    if (listRenWu.size()>0){
                        ppo.setTaskId(listRenWu.get(0).getId());
                    }
//                    BigDecimal money=new BigDecimal(Amount);
                    ppo.setAmount(Amount);
                    ppo.setLicensePlate(LicensePlate);
                    ppo.setEtcCardNumber(EtcCardNumber);
                    ppo.setInboundTime(InboundTime);
                    ppo.setOutboundTime(OutboundTime);
                    ppo.setRoadSection(RoadSection);
                    ppo.setDeleteFlag(Long.valueOf(1));
                    ppo.setVehicleId(id);
                    ppo.setEtcCardId(eId);
                    insList.add(ppo);

                } else if (list4.size() >0 && list4.get(0).getDeleteFlag()==2){
                    String Id = UUID.randomUUID().toString();
                    EtcCardUsageRecord ppo = new EtcCardUsageRecord();
                    ppo.setId(Id);
                    ppo.setCreateBy(userInfo.getUsername());
                    if (listRenWu.size()>0){
                        ppo.setTaskId(listRenWu.get(0).getId());
                    }
//                    BigDecimal money=new BigDecimal(Amount);
                    ppo.setAmount(Amount);
                    ppo.setLicensePlate(LicensePlate);
                    ppo.setEtcCardNumber(EtcCardNumber);
                    ppo.setInboundTime(InboundTime);
                    ppo.setOutboundTime(OutboundTime);
                    ppo.setRoadSection(RoadSection);
                    ppo.setDeleteFlag(Long.valueOf(1));
                    ppo.setVehicleId(id);
                    ppo.setEtcCardId(eId);
                    insList.add(ppo);
                }
                else if (list4.size() >0 && list4.get(0).getDeleteFlag()==1){
                    EtcCardUsageRecord ppo = new EtcCardUsageRecord();
                    ppo.setId(list3.get(0).getId());
                    ppo.setUpdateBy(userInfo.getUsername());
                    if (listRenWu.size()>0){
                        ppo.setTaskId(listRenWu.get(0).getId());
                    }
//                    BigDecimal money=new BigDecimal(Amount);
                    ppo.setAmount(Amount);
                    ppo.setLicensePlate(LicensePlate);
                    ppo.setEtcCardNumber(EtcCardNumber);
                    ppo.setInboundTime(InboundTime);
                    ppo.setOutboundTime(OutboundTime);
                    ppo.setRoadSection(RoadSection);
                    ppo.setDeleteFlag(Long.valueOf(1));
                    ppo.setEtcCardId(eId);
                    updList.add(ppo);
                }
            }

            String str = errorInfo.length() > 0 ? "导入的第" + errorInfo.toString() + "的位置车牌号没有填写;" : "";
            String str1 = errorInfo1.length() > 0 ? "导入的第" + errorInfo1.toString() + "的位置ETC卡号没有填写;" : "";
            String str2 = errorInfo2.length() > 0 ? "导入的第" + errorInfo2.toString() + "的位置消费金额没有填写;" : "";
            String str3 = errorInfo3.length() > 0 ? "导入的第" + errorInfo3.toString() + "的位置入站时间没有填写;" : "";
            String str4 = errorInfo4.length() > 0 ? "导入的第" + errorInfo4.toString() + "的位置出站时间没有填写;" : "";
            String str5 = errorInfo5.length() > 0 ? "导入的第" + errorInfo5.toString() + "的位置路段地点没有填写;" : "";
            String str6 = errorInfo6.length() > 0 ? "导入的第" + errorInfo6.toString() + "的位置入站时间大于出站时间;" : "";
            String str7 = errorInfo7.length() > 0 ? "导入的第" + errorInfo7.toString() + "的位置车牌号不存在;" : "";
            String str8 = errorInfo8.length() > 0 ? "导入的第" + errorInfo8.toString() + "的位置ETC卡号不存在或与车牌号不匹配;" : "";
            String str9 = errorInfo9.length() > 0 ? "导入的第" + errorInfo9.toString() + "的位置车牌号没有办理ETC;" : "";

            if (hasError) {
                String test = ((str==null || "".equals(str))?"":(str +"<br>"))+ ((str1==null || "".equals(str1))?"":(str1 +"<br>")) + ((str2==null || "".equals(str2))?"":(str2 +"<br>")) + ((str3==null || "".equals(str3))?"":(str3 +"<br>"))+((str4==null || "".equals(str4))?"":(str4 +"<br>")) +
                        ((str5==null || "".equals(str5))?"":(str5 +"<br>")) +((str6==null || "".equals(str6))?"":(str6 +"<br>")) +((str7==null || "".equals(str7))?"":(str7 +"<br>"))+((str8==null || "".equals(str8))?"":(str8 +"<br>"))+((str9==null || "".equals(str9))?"":(str9 +"<br>")) ;

                result.put("code", "-1");
                result.put("msg", test);
                return result;
            }
            if (insList.size() > 0) {
                insList.forEach(r -> {
                    this.insertEtcCardUsageRecord(r);
                });
            }
            if (updList.size() > 0) {
                updList.forEach(r -> {
                    this.updateEtcCardUsageRecord(r);
                });
            }
//            if (hasError) {
//                String test = ((str==null || "".equals(str))?"":(str +"<br>"))+((str4==null || "".equals(str4))?"":(str4 +"<br>")) + ((str1==null || "".equals(str1))?"":(str1 +"<br>")) + ((str2==null || "".equals(str2))?"":(str2 +"<br>")) + ((str3==null || "".equals(str3))?"":(str3 +"<br>")) ;
//
//                result.put("code", "-1");
//                result.put("msg", test);
//                return result;
//            }

        } catch (Exception e) {
            result.put("code", "-1");
            result.put("msg", "系统异常");
        }
        result.put("code", "0");
        result.put("msg", "导入成功");
        return result;
    }

}

