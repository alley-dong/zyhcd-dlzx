package com.dlzx.pfservercar.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.EtcCardImport;
import com.dlzx.pfservercar.mapper.CVehicleMapper;
import com.dlzx.pfservercar.service.ICVehicleService;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.EtcCardMapper;
import com.dlzx.pfservercar.domain.EtcCard;
import com.dlzx.pfservercar.service.IEtcCardService;
import org.springframework.transaction.annotation.Transactional;

/**
 * ETC管理-ETC卡Service业务层处理
 *
 * @author dlzx
 * @date 2020-12-01
 */
@Service
public class EtcCardServiceImpl implements IEtcCardService {
    @Autowired
    private EtcCardMapper etcCardMapper;

    @Autowired
    private CVehicleMapper cVehicleMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ICVehicleService cVehicleService;

    /**
     * 查询ETC管理-ETC卡
     *
     * @param id ETC管理-ETC卡ID
     * @return ETC管理-ETC卡
     */
    @Override
    public EtcCard selectEtcCardById(String id) {
        return etcCardMapper.selectEtcCardById(id);
    }

    /**
     * 查询ETC管理-ETC卡列表
     *
     * @param etcCard ETC管理-ETC卡
     * @return ETC管理-ETC卡
     */
    @Override
    public List<EtcCard> selectEtcCardList(EtcCard etcCard) {
        return etcCardMapper.selectEtcCardList(etcCard);
    }

    @Override
    public List<EtcCard> selectEtcCardListPaiHao(String licensePlate) {
        return etcCardMapper.selectEtcCardListPaiHao(licensePlate);
    }

    @Override
    public List<EtcCard> selectEtcCardListKaHao(String etcCardNumber) {
        return etcCardMapper.selectEtcCardListKaHao(etcCardNumber);
    }

    @Override
    public List<EtcCard> selectEtcCardListAll() {
        return etcCardMapper.selectEtcCardListAll();
    }

    /**
     * 新增ETC管理-ETC卡
     *
     * @param etcCard ETC管理-ETC卡
     * @return 结果
     */
    @Override
    public int insertEtcCard(EtcCard etcCard) {
        etcCard.setCreateTime(DateUtils.getNowDate());
        return etcCardMapper.insertEtcCard(etcCard);
    }

    /**
     * 修改ETC管理-ETC卡
     *
     * @param etcCard ETC管理-ETC卡
     * @return 结果
     */
    @Override
    public int updateEtcCard(EtcCard etcCard) {
        etcCard.setUpdateTime(DateUtils.getNowDate());
        return etcCardMapper.updateEtcCard(etcCard);
    }

    /**
     * 批量删除ETC管理-ETC卡
     *
     * @param ids 需要删除的ETC管理-ETC卡ID
     * @return 结果
     */
    @Override
    public int deleteEtcCardByIds(String[] ids) {
        return etcCardMapper.deleteEtcCardByIds(ids);
    }

    /**
     * 删除ETC管理-ETC卡信息
     *
     * @param id ETC管理-ETC卡ID
     * @return 结果
     */
    @Override
    public int deleteEtcCardById(String id) {
        return etcCardMapper.deleteEtcCardById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, String> importUser(List<EtcCardImport> etcCardList) {
        Map<String, String> result = new HashMap<>();

        if (StringUtils.isNull(etcCardList) || etcCardList.size() == 0) {
            throw new CustomException("导入数据为空！");
        }
        //需要新增的数据
        List<EtcCard> insList = new ArrayList<>();
        //需要修改的数据
        List<EtcCard> updList = new ArrayList<>();

        List<EtcCard> listA = etcCardMapper.selectEtcCardListAll();
        List<CVehicle> listB = cVehicleMapper.selectCVehicleListAll();

        StringBuilder errorInfo = new StringBuilder();
        StringBuilder errorInfo1 = new StringBuilder();
        StringBuilder errorInfo2 = new StringBuilder();
        StringBuilder errorInfo3 = new StringBuilder();

        boolean hasError = false;
        int countFlag = 0;

        try {
            for (int i = 0; i < etcCardList.size(); i++) {
                boolean f = etcCardList.get(i).equals();

                if (f) {
                    break;
                }
                countFlag = i+1;
            }
            for (int i = 0; i < countFlag; i++) {
                EtcCardImport pro = etcCardList.get(i);

                String LicensePlate = pro.getLicensePlate();
                String EtcCardNumber = pro.getEtcCardNumber();
                String OrderNumber = pro.getOrderNumber();
                Date OrderTime = pro.getDingDanTime();
                String Status = pro.getDingDanStatus();
                String Method = pro.getMethod();
                String Obu = pro.getObu();


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


                if (StringUtils.isNotEmpty(Status)) {
                    if (!Status.equals("已发货")) {
                        if (!Status.equals("未发货")) {
                            errorInfo2.append((i + 2) + "行");
                            hasError = true;
                            break;
                        }
                    }
                }
                List<CVehicle> listCar = listB.stream().filter(b -> Objects.equals(LicensePlate, b.getLicensePlate())).collect(Collectors.toList());
                if (listCar.size() == 0) {
                    errorInfo3.append((i + 2) + "行");
                    hasError = true;
                    break;
                }

                List<EtcCard> list3 = listA.stream().filter(a -> Objects.equals(LicensePlate, a.getLicensePlate()) ).collect(Collectors.toList());
                if (hasError) {
                    continue;
                }

                CVehicle cVehicle = new CVehicle();
                cVehicle.setLicensePlate(LicensePlate);
                List<CVehicle> list = cVehicleService.selectCVehicleList(cVehicle);
                String id = list.get(0).getId();
                LoginUser userInfo = tokenService.getLoginUser();

                if (list3.size() == 0) {
                    String Id = UUID.randomUUID().toString();
                    EtcCard ppo = new EtcCard();
                    ppo.setId(Id);
                    ppo.setCreateBy(userInfo.getUsername());
//                    DateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
//                    if (StringUtils.isNotEmpty(OrderTime)) {
                        ppo.setOrderTime(OrderTime);
//                    }
                    if (StringUtils.isNotEmpty(OrderNumber)) {
                        ppo.setOrderNumber(OrderNumber);
                    }
                    if (StringUtils.isNotEmpty(Method)) {
                        ppo.setMethod(Method);
                    }
                    if (StringUtils.isNotEmpty(Obu)) {
                        ppo.setObu(Obu);
                    }
                    if (Status.equals("已发货")) {
                        ppo.setStatus(Long.valueOf(1));
                    } else if (Status.equals("未发货")) {
                        ppo.setStatus(Long.valueOf(2));
                    }
                    ppo.setLicensePlate(LicensePlate);
                    ppo.setEtcCardNumber(EtcCardNumber);
                    ppo.setDeleteFlag(Long.valueOf(1));
                    ppo.setVehicleId(id);
                    insList.add(ppo);

                } else if (list3.size() >0 && list3.get(0).getDeleteFlag()==2){
                    String Id = UUID.randomUUID().toString();
                    EtcCard ppo = new EtcCard();
                    ppo.setId(Id);
                    ppo.setCreateBy(userInfo.getUsername());
//                    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//                    if (StringUtils.isNotEmpty(OrderTime)) {
//                        ppo.setOrderTime(format1.parse(pro.getDingDanTime()));
//                    }
//                    if (StringUtils.isNotEmpty(OrderNumber)) {
                        ppo.setOrderNumber(OrderNumber);
//                    }
                    if (StringUtils.isNotEmpty(Method)) {
                        ppo.setOrderNumber(Method);
                    }
                    if (StringUtils.isNotEmpty(Obu)) {
                        ppo.setOrderNumber(Obu);
                    }
                    if (Status.equals("已发货")) {
                        ppo.setStatus(Long.valueOf(1));
                    } else if (Status.equals("未发货")) {
                        ppo.setStatus(Long.valueOf(2));
                    }
                    ppo.setLicensePlate(LicensePlate);
                    ppo.setEtcCardNumber(EtcCardNumber);
                    ppo.setDeleteFlag(Long.valueOf(1));
                    ppo.setVehicleId(id);
                    insList.add(ppo);
                }
                else if (list3.size() >0 && list3.get(0).getDeleteFlag()==1){
                    EtcCard ppo = new EtcCard();
                    ppo.setId(list3.get(0).getId());
                    ppo.setUpdateBy(userInfo.getUsername());
//                    DateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
//                    if (StringUtils.isNotEmpty(OrderTime)) {
                    ppo.setOrderTime(OrderTime);
//                    }
                    if (StringUtils.isNotEmpty(OrderNumber)) {
                        ppo.setOrderNumber(OrderNumber);
                    }
                    if (StringUtils.isNotEmpty(Method)) {
                        ppo.setMethod(Method);
                    }
                    if (StringUtils.isNotEmpty(Obu)) {
                        ppo.setObu(Obu);
                    }
                    if (Status.equals("已发货")) {
                        ppo.setStatus(Long.valueOf(1));
                    } else if (Status.equals("未发货")) {
                        ppo.setStatus(Long.valueOf(2));
                    }
                    ppo.setLicensePlate(LicensePlate);
                    ppo.setEtcCardNumber(EtcCardNumber);
                    ppo.setDeleteFlag(Long.valueOf(1));
                    ppo.setVehicleId(id);
                    updList.add(ppo);
                }
            }

            String str = errorInfo.length() > 0 ? "导入的第" + errorInfo.toString() + "的位置车牌号没有填写;" : "";
            String str1 = errorInfo1.length() > 0 ? "导入的第" + errorInfo1.toString() + "的位置ETC卡号没有填写;" : "";
            String str2 = errorInfo2.length() > 0 ? "导入的第" + errorInfo2.toString() + "的位置订单状态请填写'已发货'或'未发货';" : "";
            String str3 = errorInfo3.length() > 0 ? "导入的第" + errorInfo3.toString() + "的位置车牌号不存在;" : "";


            if (hasError) {
                String test = ((str == null || "".equals(str)) ? "" : (str + "<br>")) + ((str1 == null || "".equals(str1)) ? "" : (str1 + "<br>")) + ((str2 == null || "".equals(str2)) ? "" : (str2 + "<br>")) + ((str3 == null || "".equals(str3)) ? "" : (str3 + "<br>"));

                result.put("code", "-1");
                result.put("msg", test);
                return result;
            }
            if (insList.size() > 0) {
                insList.forEach(r -> {
                    this.insertEtcCard(r);
                });
            }
            if (updList.size() > 0) {
                updList.forEach(r -> {
                    this.updateEtcCard(r);
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

    @Override
    public void initEmpTravelStatus(){
        EtcCard etcCard = new EtcCard();
        etcCard.setId(UUID.randomUUID().toString());
        etcCard.setDeleteFlag(1L);
        etcCard.setLicensePlate("TEST测试任务");
        etcCard.setEtcCardNumber("123456789");
        etcCard.setCreateTime(new Date());
        etcCard.setCreateBy("冯迪");
        etcCardMapper.insertEtcCard(etcCard);
    }
}
