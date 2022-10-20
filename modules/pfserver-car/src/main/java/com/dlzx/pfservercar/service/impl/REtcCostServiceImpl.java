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
import com.dlzx.pfservercar.service.ICVehicleService;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.REtcCostMapper;
import com.dlzx.pfservercar.service.IREtcCostService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 统计报-ETC费用统计Service业务层处理
 *
 * @author dlzx
 * @date 2020-12-17
 */
@Service
public class REtcCostServiceImpl implements IREtcCostService {
    @Autowired
    private REtcCostMapper rEtcCostMapper;
    @Autowired
    private CVehicleMapper cVehicleMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ICVehicleService cVehicleService;

    /**
     * 查询统计报-ETC费用统计
     *
     * @param id 统计报-ETC费用统计ID
     * @return 统计报-ETC费用统计
     */
    @Override
    public REtcCost selectREtcCostById(String id) {
        return rEtcCostMapper.selectREtcCostById(id);
    }

    /**
     * 查询统计报-ETC费用统计列表
     *
     * @param rEtcCost 统计报-ETC费用统计
     * @return 统计报-ETC费用统计
     */
    @Override
    public List<REtcCost> selectREtcCostList(REtcCost rEtcCost) {
        return rEtcCostMapper.selectREtcCostList(rEtcCost);
    }

    /**
     * 新增统计报-ETC费用统计
     *
     * @param rEtcCost 统计报-ETC费用统计
     * @return 结果
     */
    @Override
    public int insertREtcCost(REtcCost rEtcCost) {
        rEtcCost.setCreateTime(DateUtils.getNowDate());
        return rEtcCostMapper.insertREtcCost(rEtcCost);
    }

    /**
     * 修改统计报-ETC费用统计
     *
     * @param rEtcCost 统计报-ETC费用统计
     * @return 结果
     */
    @Override
    public int updateREtcCost(REtcCost rEtcCost) {
        rEtcCost.setUpdateTime(DateUtils.getNowDate());
        return rEtcCostMapper.updateREtcCost(rEtcCost);
    }

    /**
     * 批量删除统计报-ETC费用统计
     *
     * @param ids 需要删除的统计报-ETC费用统计ID
     * @return 结果
     */
    @Override
    public int deleteREtcCostByIds(String[] ids) {
        return rEtcCostMapper.deleteREtcCostByIds(ids);
    }

    /**
     * 删除统计报-ETC费用统计信息
     *
     * @param id 统计报-ETC费用统计ID
     * @return 结果
     */
    @Override
    public int deleteREtcCostById(String id) {
        return rEtcCostMapper.deleteREtcCostById(id);
    }

    @Transactional
    @Override
    public Map<String, String> importUser(List<REtcCost> recordList) {
        Map<String, String> result = new HashMap<>();

        if (StringUtils.isNull(recordList) || recordList.size() == 0) {
            throw new CustomException("导入数据为空！");
        }
        //需要新增的数据
        List<REtcCost> insList = new ArrayList<>();
        //需要修改的数据
        List<REtcCost> updList = new ArrayList<>();

        REtcCost rEtcCost = new REtcCost();
        List<REtcCost> listA = rEtcCostMapper.selectREtcCostList(rEtcCost);
        List<CVehicle> listB = cVehicleMapper.selectCVehicleListAll();

        StringBuilder errorInfo = new StringBuilder();
        StringBuilder errorInfo1 = new StringBuilder();

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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            for (int i = 0; i < countFlag; i++) {
                REtcCost pro = recordList.get(i);

                String LicensePlate = pro.getLicensePlate();

                if (StringUtils.isEmpty(LicensePlate)) {
                    errorInfo.append((i + 2) + "行");
                    hasError = true;
                    break;
                }

                List<CVehicle> listCar = listB.stream().filter(b -> Objects.equals(LicensePlate, b.getLicensePlate())).collect(Collectors.toList());
                if (listCar.size() == 0) {
                    errorInfo1.append((i + 2) + "行");
                    hasError = true;
                    break;
                }

                if (hasError) {
                    continue;
                }

                List<REtcCost> listPai =listA.stream().filter(a -> Objects.equals(LicensePlate, a.getLicensePlate())&& equalsData(simpleDateFormat.format(a.getReimburseTime()),simpleDateFormat.format(pro.getReimburseTime()))).collect(Collectors.toList());
//                List<REtcCost> listPai = new ArrayList<>();
//                for (REtcCost etcCost : listA) {
//                    listPai = recordList.stream().filter(r->r.getLicensePlate().equals(pro.getLicensePlate()) && r.getReimburseTime().getTime()==pro.getReimburseTime().getTime()).collect(Collectors.toList());
//                }

                CVehicle cVehicle = new CVehicle();
                cVehicle.setLicensePlate(LicensePlate);
                List<CVehicle> list = cVehicleService.selectCVehicleList(cVehicle);
                if (list.size()<=0){
                    result.put("code", "-1");
                    result.put("msg", "车辆不存在或已被禁用！");
                    return result;
                }
                String id = list.get(0).getId();

                LoginUser userInfo = tokenService.getLoginUser();

                if (listPai.size() == 0) {
                    String Id = UUID.randomUUID().toString();
                    REtcCost ppo = new REtcCost();
                    ppo.setId(Id);
                    ppo.setCreateBy(userInfo.getUsername());
                    ppo.setAmount(pro.getAmount());
                    ppo.setTaxAmount(pro.getTaxAmount());
                    ppo.setTax(pro.getTax());
                    ppo.setLicensePlate(pro.getLicensePlate());
                    ppo.setVehicleId(id);
                    ppo.setReimburseTime(pro.getReimburseTime());
                    ppo.setDeleteFlag(Long.valueOf(1));
                    insList.add(ppo);

                } else if (listPai.size() > 0 && listPai.get(0).getDeleteFlag() == 2) {
                    String Id = UUID.randomUUID().toString();
                    REtcCost ppo = new REtcCost();
                    ppo.setId(Id);
                    ppo.setCreateBy(userInfo.getUsername());
                    ppo.setAmount(pro.getAmount());
                    ppo.setTaxAmount(pro.getTaxAmount());
                    ppo.setTax(pro.getTax());
                    ppo.setLicensePlate(pro.getLicensePlate());
                    ppo.setVehicleId(id);
                    ppo.setReimburseTime(pro.getReimburseTime());
                    ppo.setDeleteFlag(Long.valueOf(1));
                    insList.add(ppo);
                } else if (listPai.size() > 0 && listPai.get(0).getDeleteFlag() == 1) {
                    REtcCost ppo = new REtcCost();
                    ppo.setId(listPai.get(0).getId());
                    ppo.setUpdateBy(userInfo.getUsername());
                    ppo.setAmount(pro.getAmount());
                    ppo.setTaxAmount(pro.getTaxAmount());
                    ppo.setTax(pro.getTax());
                    ppo.setLicensePlate(pro.getLicensePlate());
                    ppo.setVehicleId(id);
                    ppo.setReimburseTime(pro.getReimburseTime());
                    ppo.setDeleteFlag(Long.valueOf(1));
                    updList.add(ppo);
                }
            }

            String str = errorInfo.length() > 0 ? "导入的第" + errorInfo.toString() + "的位置车牌号没有填写" : "";
            String str1 = errorInfo1.length() > 0 ? "导入的第" + errorInfo1.toString() + "的位置车牌号不存在" : "";


            if (hasError) {
                String test = ((str == null || "".equals(str)) ? "" : (str + "")) + ((str1 == null || "".equals(str1)) ? "" : (str1 + ""));
                result.put("code", "-1");
                result.put("msg", test);
                return result;
            }
            if (insList.size() > 0) {
                insList.forEach(r -> {
                    this.insertREtcCost(r);
                });
            }
            if (updList.size() > 0) {
                updList.forEach(r -> {
                    this.updateREtcCost(r);
                });
            }
            result.put("code", "0");
            result.put("msg", "导入成功");
        } catch (Exception e) {
            result.put("code", "-1");
            result.put("msg", "系统异常");
        }
        return result;
    }

    private boolean equalsData(String date ,String date2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(date);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() == dt2.getTime()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }

}


