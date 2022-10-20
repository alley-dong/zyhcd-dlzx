package com.dlzx.pfserverparts.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.domain.SysExamineConfigParams;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverparts.client.PersonClient;
import com.dlzx.pfserverparts.client.SystemClient;
import com.dlzx.pfserverparts.domain.DDriver;
import com.dlzx.pfserverparts.domain.DDriverType;
import com.dlzx.pfserverparts.domain.DPerformanceOvertimeRecord;
import com.dlzx.pfserverparts.domain.DPerformanceRecord;
import com.dlzx.pfserverparts.domain.DPerformanceShortHaulRecord;
import com.dlzx.pfserverparts.domain.DSalaryRecords;
import com.dlzx.pfserverparts.domain.DVacationDay;
import com.dlzx.pfserverparts.domain.ReviewSubmit;
import com.dlzx.pfserverparts.domain.SubmmitVo;
import com.dlzx.pfserverparts.domain.TotalMoney;
import com.dlzx.pfserverparts.mapper.DSalaryRecordsMapper;
import com.dlzx.system.api.model.LoginUser;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverparts.mapper.DSalaryDetailedRecordsMapper;
import com.dlzx.pfserverparts.domain.DSalaryDetailedRecords;
import com.dlzx.pfserverparts.service.IDSalaryDetailedRecordsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 驾驶员-薪酬结算-详细记录Service业务层处理
 *
 * @author yangluhe
 * @date 2020-12-21
 */
@Service
public class DSalaryDetailedRecordsServiceImpl implements IDSalaryDetailedRecordsService {
    @Autowired
    private DSalaryDetailedRecordsMapper dSalaryDetailedRecordsMapper;
    @Autowired
    private PersonClient personClient;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private DSalaryRecordsMapper dSalaryRecordsMapper;
    @Autowired
    private SystemClient systemClient;

    /**
     * 查询驾驶员-薪酬结算-详细记录
     *
     * @param id 驾驶员-薪酬结算-详细记录ID
     * @return 驾驶员-薪酬结算-详细记录
     */
    @Override
    public DSalaryDetailedRecords selectDSalaryDetailedRecordsById(String id) {
        return dSalaryDetailedRecordsMapper.selectDSalaryDetailedRecordsById(id);
    }

    /**
     * 查询驾驶员-薪酬结算-详细记录列表
     *
     * @param dSalaryDetailedRecords 驾驶员-薪酬结算-详细记录
     * @return 驾驶员-薪酬结算-详细记录
     */
    @Override
    public List<DSalaryDetailedRecords> selectDSalaryDetailedRecordsList(DSalaryDetailedRecords dSalaryDetailedRecords) {
        return dSalaryDetailedRecordsMapper.selectDSalaryDetailedRecordsList(dSalaryDetailedRecords);
    }

    /**
     * 新增驾驶员-薪酬结算-详细记录
     *
     * @param dSalaryDetailedRecords 驾驶员-薪酬结算-详细记录
     * @return 结果
     */
    @Override
    public int insertDSalaryDetailedRecords(DSalaryDetailedRecords dSalaryDetailedRecords) {
        dSalaryDetailedRecords.setCreateTime(DateUtils.getNowDate());
        return dSalaryDetailedRecordsMapper.insertDSalaryDetailedRecords(dSalaryDetailedRecords);
    }

    /**
     * 修改驾驶员-薪酬结算-详细记录
     *
     * @param dSalaryDetailedRecords 驾驶员-薪酬结算-详细记录
     * @return 结果
     */
    @Override
    public int updateDSalaryDetailedRecords(DSalaryDetailedRecords dSalaryDetailedRecords) {
        dSalaryDetailedRecords.setUpdateTime(DateUtils.getNowDate());
        return dSalaryDetailedRecordsMapper.updateDSalaryDetailedRecords(dSalaryDetailedRecords);
    }

    /**
     * 批量删除驾驶员-薪酬结算-详细记录
     *
     * @param ids 需要删除的驾驶员-薪酬结算-详细记录ID
     * @return 结果
     */
    @Override
    public int deleteDSalaryDetailedRecordsByIds(String[] ids) {
        return dSalaryDetailedRecordsMapper.deleteDSalaryDetailedRecordsByIds(ids);
    }

    /**
     * 删除驾驶员-薪酬结算-详细记录信息
     *
     * @param id 驾驶员-薪酬结算-详细记录ID
     * @return 结果
     */
    @Override
    public int deleteDSalaryDetailedRecordsById(String id) {
        return dSalaryDetailedRecordsMapper.deleteDSalaryDetailedRecordsById(id);
    }

    /**
     * 工资核算获取短途和长途列表
     *
     * @return
     */



    @Transactional
    @Override
    public AjaxResult selectDSalaryRecordsTypeList() {
        DSalaryRecords dSalaryRecords = new DSalaryRecords();
        dSalaryRecords.setId(UUID.randomUUID().toString());
        Date date = new Date();
        //所有司机档案
        List<DDriver> dDriverList = personClient.driversList();
        //创建空list
        List<DSalaryDetailedRecords> salaryList = new ArrayList<>();
        //获取当前月份的16号和上月15号
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 1, 15);
        calendar1.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 16);
        Date firstTime = calendar.getTime();
        Date nextTime = calendar1.getTime();
        dSalaryRecords.setVacationDate(nextTime);
        //所有驾驶员考勤的每月底薪
        DVacationDay dVacationDay = new DVacationDay();
        dVacationDay.setFirstSalaryDate(firstTime);
        dVacationDay.setNextSalaryDate(nextTime);
        List<DVacationDay> dVacationDayList = personClient.daySalary(dVacationDay);
        //获取长途司机的绩效工资
        DPerformanceRecord dPerformanceRecord = new DPerformanceRecord();
        dPerformanceRecord.setTaskStartTime(firstTime);
        dPerformanceRecord.setTaskEndTime(nextTime);
        // todo 设置了时间区间查询，当前月份的16号和上月15号 ，那如果任务时间不在这个区间就啥也查不到（这里绝逼有问题）
        List<DPerformanceRecord> dPerformanceRecordList = personClient.longPerMoney(dPerformanceRecord);
        //获取短途司机的绩效工资
        DPerformanceShortHaulRecord dPerformanceShortHaulRecord = new DPerformanceShortHaulRecord();
        dPerformanceShortHaulRecord.setShortStartTime(firstTime);
        dPerformanceShortHaulRecord.setShortEndTime(nextTime);
        List<DPerformanceShortHaulRecord> dPerformanceShortHaulRecordList = personClient.shortPerMoney(dPerformanceShortHaulRecord);
        //获取所有司机加班费用
        DPerformanceOvertimeRecord dPerformanceOvertimeRecord = new DPerformanceOvertimeRecord();
        dPerformanceOvertimeRecord.setbTime(firstTime);
        dPerformanceOvertimeRecord.seteTime(nextTime);
        List<DPerformanceOvertimeRecord> dPerformanceOvertimeRecordList = personClient.overMoney(dPerformanceOvertimeRecord);
        for (DDriver driver : dDriverList) {
            //检测驾驶员是否分配驾驶员类型
            if (driver.getPerformanceType() == null){
                return AjaxResult.error(driver.getName()+"未分配驾驶员类型，请分配后进行核算");
            }

            DSalaryDetailedRecords dSalaryDetailedRecords = new DSalaryDetailedRecords();
            dSalaryDetailedRecords.setVactionTime(nextTime);
            dSalaryDetailedRecords.setId(UUID.randomUUID().toString());
            dSalaryDetailedRecords.setDriverId(driver.getId());
            dSalaryDetailedRecords.setDriverName(driver.getName());
            dSalaryDetailedRecords.setType(driver.getPerformanceType());
            dSalaryDetailedRecords.setCreateBy(tokenService.getLoginUser().getUsername());
            dSalaryDetailedRecords.setCreateTime(new Date());
            dSalaryDetailedRecords.setDeleteFlag(1L);
            dSalaryDetailedRecords.setSalaryId(dSalaryRecords.getId());
            //底薪
            if (dVacationDayList.size() == 0) {
                dSalaryDetailedRecords.setBasicSalary(BigDecimal.ZERO);
            } else {
//                DVacationDay day = dVacationDayList.stream().filter(a -> (Objects.nonNull(a.getDriverId())) && (Objects.equals(a.getDriverId(), dSalaryDetailedRecords.getDriverId()))).findFirst().orElse(null);

                // update by 2021 4.7 判断驾驶员的考勤记录中有没有请假的
                List<DVacationDay> dVacationDays = dVacationDayList.stream().filter(d -> d.getDriverId().equals(driver.getId())).filter(day -> day.getStatus()==3).collect(Collectors.toList());
                DDriverType dDriverType = personClient.queryDriverTypeById(driver.getDriverTypeId());
                if (dVacationDays.size()>0 && dDriverType.getBasicSalary()!= null) {
                    if (dDriverType.getBasicSalary()!= null && dDriverType.getBasicSalary().compareTo(BigDecimal.ZERO)>0){
                        // 如果有请假 根据金额是不是0，来判断是否是有薪，取出无薪的数量
                        long count = dVacationDays.stream().filter(dVacation -> dVacation.getBasicSalary().compareTo(BigDecimal.ZERO)==0).count();
                        if (count>0){
                            // 减去无薪请假扣去金额
                            BigDecimal multiply = dDriverType.getBasicSalary().divide(new BigDecimal(30)).multiply(new BigDecimal(count));
                            dSalaryDetailedRecords.setBasicSalary(dDriverType.getBasicSalary().subtract(multiply));
                        }
                    }
                } else {
                    dSalaryDetailedRecords.setBasicSalary(dDriverType.getBasicSalary()==null?BigDecimal.ZERO:dDriverType.getBasicSalary());
                }
            }
            //判断司机是否有加班
            for (DPerformanceOvertimeRecord performanceOvertimeRecord : dPerformanceOvertimeRecordList) {
                if (performanceOvertimeRecord.getDriverId() == driver.getId()) {
                    //获取加班费
                    DPerformanceOvertimeRecord performanceOvertimeRecordOne = dPerformanceOvertimeRecordList.stream().filter(a -> (Objects.nonNull(a.getDriverId())) && (Objects.equals(a.getDriverId(), dSalaryDetailedRecords.getDriverId()))).findFirst().orElse(null);
                    if (performanceOvertimeRecordOne == null) {
                        dSalaryDetailedRecords.setOvertimePay(BigDecimal.ZERO);
                    } else {
                        if (performanceOvertimeRecordOne.getAmo() == null) {
                            dSalaryDetailedRecords.setOvertimePay(BigDecimal.ZERO);
                        } else {
                            dSalaryDetailedRecords.setOvertimePay(performanceOvertimeRecordOne.getAmo());
                        }
                    }
                }
            }
            //工龄工资
            if (driver.getDntryTime() != null) {
                //Long s = ((date.getTime() - driver.getDntryTime().getTime()) / (24 * 60 * 60 * 1000)) / 365;
                Calendar calendarNow = Calendar.getInstance();
                int yearNow = calendarNow.get(Calendar.YEAR);
                calendarNow.setTime(driver.getDntryTime());
                int s = yearNow - calendarNow.get(Calendar.YEAR);
                int k = 5 + s*5;
                dSalaryDetailedRecords.setWageForSeniority(new BigDecimal(k));
            }
            if (driver.getPerformanceType() != null) {


                if (driver.getPerformanceType() == 1) {//长途司机表明细单赋值绩效工资
                    if (dPerformanceRecordList.size() == 0) {
                        dSalaryDetailedRecords.setMeritSalary(BigDecimal.ZERO);
                        // 篷布补助
                        dSalaryDetailedRecords.setTarpaulin(BigDecimal.ZERO);
                    } else {
                        // update by 4.29 如果绩效记录分别有主驾驶员和对班驾驶员，要分别处理
                        // 主驾驶员
                        DPerformanceRecord performanceRecord = dPerformanceRecordList.stream()
                                .filter(a -> (Objects.nonNull(a.getDriverId())) && (Objects.equals(a.getDriverId(), dSalaryDetailedRecords.getDriverId())))
                                .findFirst().orElse(null);
                        // 如果该驾驶员在绩效记录中不是主，则根据对班驾驶员再去查询
                        if (performanceRecord == null){
                            performanceRecord = dPerformanceRecordList.stream()
                                    .filter(a -> (Objects.nonNull(a.getViceDriverId())) && (Objects.equals(a.getViceDriverId(), dSalaryDetailedRecords.getDriverId())))
                                    .findFirst().orElse(null);
                        }
                        if (performanceRecord != null) {
                            dSalaryDetailedRecords.setMeritSalary(performanceRecord.getAmo());
                            // 篷布补助
                            dSalaryDetailedRecords.setTarpaulin(performanceRecord.getTarpaulin());
                        } else {
                            dSalaryDetailedRecords.setMeritSalary(BigDecimal.ZERO);
                            // 篷布补助
                            dSalaryDetailedRecords.setTarpaulin(BigDecimal.ZERO);
                        }
                    }
                    //实发工资
                    BigDecimal Salary = this.getBigDecimalSum(dSalaryDetailedRecords.getBasicSalary(), dSalaryDetailedRecords.getMeritSalary(), dSalaryDetailedRecords.getWageForSeniority(), dSalaryDetailedRecords.getOvertimePay(),dSalaryDetailedRecords.getTarpaulin());
                    dSalaryDetailedRecords.setSalary(Salary);
                } else { //短途司机表明细单赋值绩效工资
                    if (dPerformanceShortHaulRecordList.size() == 0) {
                        dSalaryDetailedRecords.setMeritSalary(BigDecimal.ZERO);
                    } else {
                        DPerformanceShortHaulRecord performanceShortHaulRecord = dPerformanceShortHaulRecordList.stream().filter(a -> (Objects.nonNull(a.getDriverId())) && (Objects.equals(a.getDriverId(), dSalaryDetailedRecords.getDriverId()))).findFirst().orElse(null);
                        if (performanceShortHaulRecord != null) {
                            dSalaryDetailedRecords.setMeritSalary(performanceShortHaulRecord.getAmo());
                        } else {
                            dSalaryDetailedRecords.setMeritSalary(BigDecimal.ZERO);
                        }
                    }
                    //实发工资
                    BigDecimal Salary = this.getBigDecimalSum(dSalaryDetailedRecords.getBasicSalary(), dSalaryDetailedRecords.getMeritSalary(), dSalaryDetailedRecords.getWageForSeniority(), dSalaryDetailedRecords.getOvertimePay());
                    dSalaryDetailedRecords.setSalary(Salary);

                }
            }
            salaryList.add(dSalaryDetailedRecords);
        }
        //工资总额
        BigDecimal totalAll = salaryList.stream().map(DSalaryDetailedRecords::getSalary).reduce(BigDecimal.ZERO, DSalaryDetailedRecordsServiceImpl::sum);
        dSalaryRecords.setTotalAmount(totalAll);
        dSalaryRecords.setDeleteFlag(1L);
        dSalaryRecords.setStatus(4L);
        dSalaryRecords.setSalaryStatus(1L);
        int result = 1;
        try {
            if (dSalaryRecords != null) {
                result = dSalaryRecordsMapper.insertDSalaryRecords(dSalaryRecords);
            }
            if (salaryList.size() > 0) {
                result = this.insertBatch(salaryList);
            }
        } catch (Exception e) {
            throw e;
        }
        if (result>0){
            return AjaxResult.success();
        }
        else {
        return AjaxResult.error();
        }
    }


    /**
     * 校验其中的参数为空 lambda
     *
     * @param in
     * @return
     */

    public static BigDecimal ifNullSet0(BigDecimal in) {
        if (in != null) {
            return in;
        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal sum(BigDecimal... in) {
        BigDecimal result = BigDecimal.ZERO;
        for (int i = 0; i < in.length; i++) {
            result = result.add(ifNullSet0(in[i]));
        }
        return result;
    }

    /**
     * 多个BigDecimal数相加和
     *
     * @param i
     * @param arg
     * @return
     */
    public static BigDecimal getBigDecimalSum(BigDecimal i, BigDecimal... arg) {
        BigDecimal sum = i;
        for (BigDecimal b : arg) {
            if (b == null) {
                b = BigDecimal.ZERO;
            }
            sum = sum.add(b);
        }
        return sum;
    }

    /**
     * 批量新增
     */
    public int insertBatch(List<DSalaryDetailedRecords> dSalaryDetailedRecords) {
        return dSalaryDetailedRecordsMapper.insertBatch(dSalaryDetailedRecords);
    }

    /**
     * 工资明细
     *
     * @param id
     * @return
     */
    public Map selectDSalaryDetail(String id) {
        List<DSalaryDetailedRecords> dSalaryDetailedRecordsList = dSalaryDetailedRecordsMapper.selectDSalaryDetailForm(id);
        List<DSalaryDetailedRecords> longRecordsList = dSalaryDetailedRecordsList.stream().filter(a -> a.getType() == 1).collect(Collectors.toList());
        List<DSalaryDetailedRecords> shortRecordsList = dSalaryDetailedRecordsList.stream().filter(a -> a.getType() == 2).collect(Collectors.toList());
        for (DSalaryDetailedRecords dSalaryDetailedRecords : longRecordsList) {
            BigDecimal salary = this.getBigDecimalSum(dSalaryDetailedRecords.getBasicSalary(), dSalaryDetailedRecords.getMeritSalary(),
                    dSalaryDetailedRecords.getWageForSeniority(), dSalaryDetailedRecords.getOvertimePay(), dSalaryDetailedRecords.getWaitPay(),
                    dSalaryDetailedRecords.getFlowPay(), dSalaryDetailedRecords.getOtherPay(), dSalaryDetailedRecords.getRewardFee(),dSalaryDetailedRecords.getTarpaulin());
            if (dSalaryDetailedRecords.getPenaltyFee() == null) {
                dSalaryDetailedRecords.setSalary(salary);
            } else {
                dSalaryDetailedRecords.setSalary(salary.subtract(dSalaryDetailedRecords.getPenaltyFee()));
            }
        }
        for (DSalaryDetailedRecords dSalaryDetailedRecords : shortRecordsList) {
            BigDecimal salary = this.getBigDecimalSum(dSalaryDetailedRecords.getBasicSalary(), dSalaryDetailedRecords.getMeritSalary(),
                    dSalaryDetailedRecords.getWageForSeniority(), dSalaryDetailedRecords.getOvertimePay()
                    , dSalaryDetailedRecords.getOtherPay(), dSalaryDetailedRecords.getRewardFee());
            if (dSalaryDetailedRecords.getPenaltyFee() == null) {
                dSalaryDetailedRecords.setSalary(salary);
            } else {
                dSalaryDetailedRecords.setSalary(salary.subtract(dSalaryDetailedRecords.getPenaltyFee()));
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("LongRecord", longRecordsList);
        map.put("ShortRecord", shortRecordsList);
        return map;
    }

    /**
     * 明细单
     *
     * @param dSalaryDetailedRecords
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int editSaveDetailRecord(DSalaryDetailedRecords dSalaryDetailedRecords) {
        DSalaryRecords dSalaryRecords = dSalaryRecordsMapper.selectDSalaryRecordsById(dSalaryDetailedRecords.getSalaryId());
        DSalaryDetailedRecords dSalaryDetailedRecordsOne = dSalaryDetailedRecordsMapper.selectDSalaryDetailedRecordsById(dSalaryDetailedRecords.getId());
        if (dSalaryDetailedRecordsOne.getSalary() != null){
            dSalaryRecords.setTotalAmount(dSalaryRecords.getTotalAmount().subtract(dSalaryDetailedRecordsOne.getSalary()));
        }
        dSalaryDetailedRecordsOne.setMeritSalary(dSalaryDetailedRecords.getMeritSalary());
        dSalaryDetailedRecordsOne.setBasicSalary(dSalaryDetailedRecords.getBasicSalary());
        dSalaryDetailedRecordsOne.setOvertimePay(dSalaryDetailedRecords.getOvertimePay());
        dSalaryDetailedRecordsOne.setWageForSeniority(dSalaryDetailedRecords.getWageForSeniority());
        dSalaryDetailedRecordsOne.setFlowPay(dSalaryDetailedRecords.getFlowPay());
        dSalaryDetailedRecordsOne.setOtherPay(dSalaryDetailedRecords.getOtherPay());
        dSalaryDetailedRecordsOne.setPenaltyFee(dSalaryDetailedRecords.getPenaltyFee());
        dSalaryDetailedRecordsOne.setRemarks(dSalaryDetailedRecords.getRemarks());
        dSalaryDetailedRecordsOne.setRewardFee(dSalaryDetailedRecords.getRewardFee());
        dSalaryDetailedRecordsOne.setWaitPay(dSalaryDetailedRecords.getWaitPay());
        dSalaryDetailedRecordsOne.setUpdateBy(tokenService.getLoginUser().getUsername());
        dSalaryDetailedRecordsOne.setUpdateTime(new Date());
        dSalaryDetailedRecordsOne.setTarpaulin(dSalaryDetailedRecords.getTarpaulin());
        BigDecimal salary = getBigDecimalSum(dSalaryDetailedRecordsOne.getBasicSalary(), dSalaryDetailedRecordsOne.getOtherPay(),
                dSalaryDetailedRecordsOne.getWaitPay(), dSalaryDetailedRecordsOne.getRewardFee(),
                dSalaryDetailedRecordsOne.getOvertimePay(), dSalaryDetailedRecordsOne.getWageForSeniority(),
                dSalaryDetailedRecordsOne.getFlowPay(), dSalaryDetailedRecordsOne.getMeritSalary()
        );
        if (dSalaryDetailedRecords.getPenaltyFee() == null) {
            dSalaryDetailedRecordsOne.setSalary(salary);
        } else {
            dSalaryDetailedRecordsOne.setSalary(salary.subtract(dSalaryDetailedRecords.getPenaltyFee()));
        }
        dSalaryRecords.setTotalAmount(dSalaryRecords.getTotalAmount().add(dSalaryDetailedRecordsOne.getSalary()));
        int result = 1 ;
            if (dSalaryRecords != null) {
                dSalaryRecordsMapper.updateDSalaryRecords(dSalaryRecords);
            }
            if (dSalaryDetailedRecordsOne != null) {
                result = dSalaryDetailedRecordsMapper.updateDSalaryDetailedRecords(dSalaryDetailedRecordsOne);
            }
        return result;
    }


    /**
     * 撤销
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int revoke(String id) {
        DSalaryRecords dSalaryRecords = dSalaryRecordsMapper.selectDSalaryRecordsById(id);
        if (dSalaryRecords.getStatus() == 1 || dSalaryRecords.getStatus() == 3) {
            dSalaryRecords.setDeleteFlag(2L);
            List<DSalaryDetailedRecords> dSalaryDetailedRecordsList = dSalaryDetailedRecordsMapper.selectDSalaryDetailForm(id);
            for (DSalaryDetailedRecords dSalaryDetailedRecords : dSalaryDetailedRecordsList) {
                dSalaryDetailedRecords.setDeleteFlag(2L);
            }
            int result = 1;
            try {
                if (dSalaryRecords != null) {
                    result = dSalaryRecordsMapper.updateDSalaryRecords(dSalaryRecords);
                }
                if (dSalaryDetailedRecordsList.size() > 0) {
//                    result = dSalaryDetailedRecordsMapper.updateDSalaryDetailedRecords(dSalaryDetailedRecordsList.get(1));
                    result = dSalaryDetailedRecordsMapper.updateDetailRecordsBatch(id);
                }
            } catch (Exception e) {
                throw e;
            }
            // 撤回删除审批记录
            systemClient.deleteExamineTaskByRelationId(dSalaryRecords.getId());
            return result;
        } else {
            return -1;
        }
    }

    /**
     * 审核
     *
     * @param reviewSubmit
     * @return
     */
    @Override
    public AjaxResult review(ReviewSubmit reviewSubmit) {
        //服务调用
        Map map = systemClient.reviewCommon(reviewSubmit);
        // 审核校验
        int code = (int) map.get("code");
        if (code == 3 || code == 4) {
            DSalaryRecords dSalaryRecords = dSalaryRecordsMapper.selectDSalaryRecordsById(reviewSubmit.getRelationId());
            dSalaryRecords.setStatus(code == 3 ? 2L : 3L);
            dSalaryRecords.setReviewTime(new Date());
            dSalaryRecords.setReviewRemarks(reviewSubmit.getReviewRemarks());
            dSalaryRecords.setReviewUser(tokenService.getLoginUser().getUsername());
            int result = dSalaryRecordsMapper.updateDSalaryRecords(dSalaryRecords);
            if (result <= 0) {
                return AjaxResult.error("操作失败");
            }
        }
        //返回msg
        String msg = (String) map.get("msg");
        if (code == 1) {
            return AjaxResult.error(msg);
        }
        return AjaxResult.success(msg);
    }

    /**
     * 新增提交
     *
     * @param submmitVo
     * @return
     */
    @Override
    public int submmit(SubmmitVo submmitVo) {
        DSalaryRecords dSalaryRecords = dSalaryRecordsMapper.selectDSalaryRecordsById(submmitVo.getRelationId());
        DSalaryDetailedRecords dSalaryDetailedRecords = dSalaryDetailedRecordsMapper.selectDSalaryDetailForm(dSalaryRecords.getId()).stream().findFirst().orElse(null);
        dSalaryRecords.setStatus(1L);
        int result = systemClient.adminSubmitCommon(10L, submmitVo.getRelationId(),dSalaryDetailedRecords==null?"":dSalaryDetailedRecords.getDriverId(),dSalaryDetailedRecords==null?"":dSalaryDetailedRecords.getDriverName());
        if (result == 0) {
            dSalaryRecords.setStatus(2L);
        }
        return dSalaryRecordsMapper.updateDSalaryRecords(dSalaryRecords);
    }

    @Override
    public List<DSalaryDetailedRecords> selectAppList(DSalaryDetailedRecords dSalaryDetailedRecords) {
        List<DSalaryDetailedRecords> dSalaryDetailedRecordsList = dSalaryDetailedRecordsMapper.selectDSalaryDetailApp(dSalaryDetailedRecords);
//        if (dSalaryDetailedRecords.getVactionTime() != null){
//            List<DSalaryDetailedRecords> dSalaryDetailedRecordsList1 = dSalaryDetailedRecordsList.stream().filter(a ->a.getVactionTime().equals(dSalaryDetailedRecords.getVactionTime())).collect(Collectors.toList());
//            return dSalaryDetailedRecordsList1;
//        }
        return dSalaryDetailedRecordsList;
    }
}
