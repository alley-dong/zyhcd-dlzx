package com.dlzx.pfservercar.service.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.REtcCost;
import com.dlzx.pfservercar.mapper.CVehicleMapper;
import com.dlzx.pfservercar.service.ICVehicleService;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.RInformationSystemServiceCostMapper;
import com.dlzx.pfservercar.domain.RInformationSystemServiceCost;
import com.dlzx.pfservercar.service.IRInformationSystemServiceCostService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 统计报-信息系统服务费统计Service业务层处理
 *
 * @author dlzx
 * @date 2020-12-22
 */
@Service
public class RInformationSystemServiceCostServiceImpl implements IRInformationSystemServiceCostService {
    @Autowired
    private RInformationSystemServiceCostMapper rInformationSystemServiceCostMapper;
    @Autowired
    private CVehicleMapper cVehicleMapper;
    @Autowired
    private ICVehicleService cVehicleService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询统计报-信息系统服务费统计
     *
     * @param id 统计报-信息系统服务费统计ID
     * @return 统计报-信息系统服务费统计
     */
    @Override
    public RInformationSystemServiceCost selectRInformationSystemServiceCostById(String id) {
        return rInformationSystemServiceCostMapper.selectRInformationSystemServiceCostById(id);
    }

    /**
     * 查询统计报-信息系统服务费统计列表
     *
     * @param rInformationSystemServiceCost 统计报-信息系统服务费统计
     * @return 统计报-信息系统服务费统计
     */
    @Override
    public List<RInformationSystemServiceCost> selectRInformationSystemServiceCostList(RInformationSystemServiceCost rInformationSystemServiceCost) {
        return rInformationSystemServiceCostMapper.selectRInformationSystemServiceCostList(rInformationSystemServiceCost);
    }

    /**
     * 新增统计报-信息系统服务费统计
     *
     * @param rInformationSystemServiceCost 统计报-信息系统服务费统计
     * @return 结果
     */
    @Override
    public int insertRInformationSystemServiceCost(RInformationSystemServiceCost rInformationSystemServiceCost) {
        rInformationSystemServiceCost.setCreateTime(DateUtils.getNowDate());
        return rInformationSystemServiceCostMapper.insertRInformationSystemServiceCost(rInformationSystemServiceCost);
    }

    /**
     * 修改统计报-信息系统服务费统计
     *
     * @param rInformationSystemServiceCost 统计报-信息系统服务费统计
     * @return 结果
     */
    @Override
    public int updateRInformationSystemServiceCost(RInformationSystemServiceCost rInformationSystemServiceCost) {
        rInformationSystemServiceCost.setUpdateTime(DateUtils.getNowDate());
        return rInformationSystemServiceCostMapper.updateRInformationSystemServiceCost(rInformationSystemServiceCost);
    }

    /**
     * 批量删除统计报-信息系统服务费统计
     *
     * @param ids 需要删除的统计报-信息系统服务费统计ID
     * @return 结果
     */
    @Override
    public int deleteRInformationSystemServiceCostByIds(String[] ids) {
        return rInformationSystemServiceCostMapper.deleteRInformationSystemServiceCostByIds(ids);
    }

    /**
     * 删除统计报-信息系统服务费统计信息
     *
     * @param id 统计报-信息系统服务费统计ID
     * @return 结果
     */
    @Override
    public int deleteRInformationSystemServiceCostById(String id) {
        return rInformationSystemServiceCostMapper.deleteRInformationSystemServiceCostById(id);
    }

    @Transactional
    @Override
    public Map<String, String> importUser(List<RInformationSystemServiceCost> recordList) {
        Map<String, String> result = new HashMap<>();

        if (StringUtils.isNull(recordList) || recordList.size() == 0) {
            throw new CustomException("导入数据为空！");
        }
        //需要新增的数据
        List<RInformationSystemServiceCost> insList = new ArrayList<>();
        //需要修改的数据
        List<RInformationSystemServiceCost> updList = new ArrayList<>();

        RInformationSystemServiceCost rInformationSystemServiceCost = new RInformationSystemServiceCost();
        List<RInformationSystemServiceCost> listA = rInformationSystemServiceCostMapper.selectRInformationSystemServiceCostList(rInformationSystemServiceCost);
        List<CVehicle> listB = cVehicleMapper.selectCVehicleListAll();

        StringBuilder errorInfo = new StringBuilder();
        StringBuilder errorInfo1 = new StringBuilder();
        StringBuilder errorInfo2 = new StringBuilder();
        StringBuilder errorInfo3 = new StringBuilder();
        StringBuilder errorInfo4 = new StringBuilder();
        StringBuilder errorInfo5 = new StringBuilder();
        StringBuilder errorInfo6 = new StringBuilder();

        boolean hasError = false;
        int countFlag = 0;

        try {
            for (int i = 1; i < recordList.size(); i++) {
                boolean f = recordList.get(i).equals();

                if (f) {
                    break;
                }
                countFlag = i;
            }
            for (int i = 0; i <= countFlag; i++) {
                RInformationSystemServiceCost pro = recordList.get(i);
                Date StartTime = pro.getStartTime();
                Date CloseTime = pro.getCloseTime();
                String LicensePlate = pro.getLicensePlate();
                String SystemName = pro.getSystemName();
                BigDecimal Amount = pro.getAmount();

                if (StringUtils.isEmpty(LicensePlate)) {
                    errorInfo.append((i + 2) + "行");
                    hasError = true;
                    break;
                }

                if (StringUtils.isEmpty(SystemName)) {
                    errorInfo1.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                if (Amount == null) {
                    errorInfo2.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                if (StartTime == null) {
                    errorInfo3.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                if (CloseTime == null) {
                    errorInfo4.append((i + 2) + "行");
                    hasError = true;
                    break;
                }

                List<CVehicle> listCar = listB.stream().filter(b -> Objects.equals(LicensePlate, b.getLicensePlate())).collect(Collectors.toList());
                if (listCar.size() == 0) {
                    errorInfo5.append((i + 2) + "行");
                    hasError = true;
                    break;
                }

                List<RInformationSystemServiceCost> listCost = recordList.stream().filter(b -> Objects.equals(LicensePlate, b.getLicensePlate()) && b.getStartTime() == StartTime && b.getCloseTime() == CloseTime).collect(Collectors.toList());
                if (listCost.size() > 1) {
                    errorInfo6.append((i + 2) + "行");
                    hasError = true;
                    break;
                }
                if (hasError) {
                    continue;
                }

                List<RInformationSystemServiceCost> listPai = listA.stream().filter(a -> Objects.equals(LicensePlate, a.getLicensePlate()) && Objects.equals(SystemName, a.getSystemName()) && Objects.equals(StartTime, a.getStartTime()) && Objects.equals(CloseTime, a.getCloseTime()) ).collect(Collectors.toList());

                CVehicle cVehicle = new CVehicle();
                cVehicle.setLicensePlate(LicensePlate);
                List<CVehicle> list = cVehicleService.selectCVehicleList(cVehicle);
                String id = list.get(0).getId();

                LoginUser userInfo = tokenService.getLoginUser();

                if (listPai.size() == 0) {
                    String Id = UUID.randomUUID().toString();
                    RInformationSystemServiceCost ppo = new RInformationSystemServiceCost();
                    ppo.setId(Id);
                    ppo.setCreateBy(userInfo.getUsername());
                    ppo.setAmount(pro.getAmount());
                    ppo.setStartTime(StartTime);
                    ppo.setCloseTime(CloseTime);
                    ppo.setLicensePlate(LicensePlate);
                    ppo.setVehicleId(id);
                    ppo.setSystemName(SystemName);
                    ppo.setDeleteFlag(Long.valueOf(1));
                    insList.add(ppo);

                } else if (listPai.size() > 0 && listPai.get(0).getDeleteFlag() == 2) {
                    String Id = UUID.randomUUID().toString();
                    RInformationSystemServiceCost ppo = new RInformationSystemServiceCost();
                    ppo.setId(Id);
                    ppo.setCreateBy(userInfo.getUsername());
                    ppo.setAmount(pro.getAmount());
                    ppo.setStartTime(StartTime);
                    ppo.setCloseTime(CloseTime);
                    ppo.setLicensePlate(LicensePlate);
                    ppo.setVehicleId(id);
                    ppo.setSystemName(SystemName);
                    ppo.setDeleteFlag(Long.valueOf(1));
                    insList.add(ppo);
                } else if (listPai.size() > 0 && listPai.get(0).getDeleteFlag() == 1) {
                    RInformationSystemServiceCost ppo = new RInformationSystemServiceCost();
                    ppo.setId(listPai.get(0).getId());
                    ppo.setUpdateBy(userInfo.getUsername());
                    ppo.setAmount(pro.getAmount());
                    ppo.setStartTime(StartTime);
                    ppo.setCloseTime(CloseTime);
                    ppo.setLicensePlate(LicensePlate);
                    ppo.setVehicleId(id);
                    ppo.setSystemName(SystemName);
                    ppo.setDeleteFlag(Long.valueOf(1));
                    updList.add(ppo);
                }
            }

            String str = errorInfo.length() > 0 ? "导入的第" + errorInfo.toString() + "的位置车牌号没有填写;":"";
            String str1 = errorInfo1.length() > 0 ? "导入的第" + errorInfo1.toString() + "的位置系统名称没有填写;":"";
            String str2 = errorInfo2.length() > 0 ? "导入的第" + errorInfo2.toString() + "的位置金额没有填写;":"";
            String str3 = errorInfo3.length() > 0 ? "导入的第" + errorInfo3.toString() + "的位置开始时间没有填写;":"";
            String str4 = errorInfo4.length() > 0 ? "导入的第" + errorInfo4.toString() + "的位置截止时间没有填写;":"";
            String str5 = errorInfo5.length() > 0 ? "导入的第" + errorInfo5.toString() + "的位置车牌号不存在;":"";
            String str6 = errorInfo6.length() > 0 ? "导入的第" + errorInfo6.toString() + "的位置车牌,开始时间及截止时间在导入内容中存在多条;" : "";


            if (hasError) {
//                String test = ((str == null || "".equals(str)) ? "" : (str + "<br>")) + ((str1 == null || "".equals(str1)) ? "" : (str1 + "<br>")) + ((str2 == null || "".equals(str2)) ? "" : (str2 + "<br>")) + ((str3 == null || "".equals(str3)) ? "" : (str3 + "<br>")) + ((str4 == null || "".equals(str4)) ? "" : (str4 + "<br>")) +
//                        ((str5 == null || "".equals(str5)) ? "" : (str5 + "<br>")) + ((str6 == null || "".equals(str6)) ? "" : (str6 + "<br>"));
                String test = ((str == null || "".equals(str)) ? "" : (str + "")) + ((str1 == null || "".equals(str1)) ? "" : (str1 + "")) + ((str2 == null || "".equals(str2)) ? "" : (str2 + "")) + ((str3 == null || "".equals(str3)) ? "" : (str3 + "<br>")) + ((str4 == null || "".equals(str4)) ? "" : (str4 + "")) +
                        ((str5 == null || "".equals(str5)) ? "" : (str5 + "")) + ((str6 == null || "".equals(str6)) ? "" : (str6 + ""));
                result.put("code", "-1");
                result.put("msg", test);
                return result;
            }
            if (insList.size() > 0) {
                insList.forEach(r -> {
                    this.insertRInformationSystemServiceCost(r);
                });
            }
            if (updList.size() > 0) {
                updList.forEach(r -> {
                    this.updateRInformationSystemServiceCost(r);
                });
            }

        } catch (Exception e) {
            result.put("code", "-1");
            result.put("msg", "系统异常");
        }
        result.put("code", "0");
        result.put("msg", "导入成功");
        return result;
    }


}
