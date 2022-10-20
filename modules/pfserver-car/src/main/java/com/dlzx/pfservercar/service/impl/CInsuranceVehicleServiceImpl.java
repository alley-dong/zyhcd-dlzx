package com.dlzx.pfservercar.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.client.SystemClient;
import com.dlzx.pfservercar.domain.CInsuranceTypeItem;
import com.dlzx.pfservercar.domain.CInsuranceVehicleItem;
import com.dlzx.pfservercar.domain.RRepair;
import com.dlzx.pfservercar.domain.ReviewSubmit;
import com.dlzx.pfservercar.mapper.CInsuranceTypeItemMapper;
import com.dlzx.pfservercar.mapper.CInsuranceVehicleItemMapper;
import com.dlzx.pfservercar.mapper.SysDictDataMapper;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.CInsuranceVehicleMapper;
import com.dlzx.pfservercar.domain.CInsuranceVehicle;
import com.dlzx.pfservercar.service.ICInsuranceVehicleService;

/**
 * 车辆管理-车辆保险Service业务层处理
 * 
 * @author dlzx
 * @date 2020-11-24
 */
@Service
public class CInsuranceVehicleServiceImpl implements ICInsuranceVehicleService 
{
    @Autowired
    private CInsuranceVehicleMapper cInsuranceVehicleMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Autowired
    private SystemClient systemClient;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private CInsuranceVehicleItemMapper cInsuranceVehicleItemMapper;
    /**
     * 查询车辆管理-车辆保险
     * 
     * @param id 车辆管理-车辆保险ID
     * @return 车辆管理-车辆保险
     */
    @Override
    public CInsuranceVehicle selectCInsuranceVehicleById(String id)
    {
        CInsuranceVehicle cInsuranceVehicle = cInsuranceVehicleMapper.selectCInsuranceVehicleById(id);
        cInsuranceVehicle.setInsuranceType(sysDictDataMapper.selectDictLabel("d_Driver_status", cInsuranceVehicle.getType().toString()));
        List<CInsuranceVehicleItem> cInsuranceVehicleItems = cInsuranceVehicleItemMapper.selectCInsuranceItemByInsuranceId(id);
        cInsuranceVehicle.setcInsuranceVehicleItems(cInsuranceVehicleItems);
        return cInsuranceVehicle;
    }

    /**
     * 查询车辆管理-车辆保险列表
     * 
     * @param cInsuranceVehicle 车辆管理-车辆保险
     * @return 车辆管理-车辆保险
     */
    @Override
    public List<CInsuranceVehicle> selectCInsuranceVehicleList(CInsuranceVehicle cInsuranceVehicle)
    {
        return cInsuranceVehicleMapper.selectCInsuranceVehicleList(cInsuranceVehicle);
    }

    /**
     * 根据ID获取详情
     * @param id
     * @return
     */
    @Override
    public List<CInsuranceVehicle> selectCInsuranceVehicleInfo(String id) {
        return cInsuranceVehicleMapper.selectCInsuranceVehicleInfo(id);
    }

    @Override
    public List<CInsuranceVehicle> selectCInsuranceVehicleORCIVG(CInsuranceVehicle cInsuranceVehicle) {
        return cInsuranceVehicleMapper.selectCInsuranceVehicleORCIVG(cInsuranceVehicle);
    }

    /**
     * 新增车辆管理-车辆保险
     * 
     * @param cInsuranceVehicle 车辆管理-车辆保险
     * @return 结果
     */
    @Override
    public int insertCInsuranceVehicle(CInsuranceVehicle cInsuranceVehicle)
    {
        cInsuranceVehicle.setCreateTime(DateUtils.getNowDate());
        return cInsuranceVehicleMapper.insertCInsuranceVehicle(cInsuranceVehicle);
    }

    /**
     * 修改车辆管理-车辆保险
     * 
     * @param cInsuranceVehicle 车辆管理-车辆保险
     * @return 结果
     */
    @Override
    public int updateCInsuranceVehicle(CInsuranceVehicle cInsuranceVehicle)
    {
        cInsuranceVehicle.setUpdateTime(DateUtils.getNowDate());
        return cInsuranceVehicleMapper.updateCInsuranceVehicle(cInsuranceVehicle);
    }

    @Override
    public int checkCInsuranceVehicle(CInsuranceVehicle cInsuranceVehicle) {
        // 审核校验
        ReviewSubmit reviewSubmit = new ReviewSubmit();
        reviewSubmit.setRelationId(cInsuranceVehicle.getId());
        reviewSubmit.setReviewRemarks(cInsuranceVehicle.getReviewRemarks());
        reviewSubmit.setUserId(tokenService.getLoginUser().getUserid()+"");
        reviewSubmit.setStatus(cInsuranceVehicle.getStatus());
        Map map = systemClient.reviewCommon(reviewSubmit);
        if (map.get("code").equals(1) || map.get("code").equals(2)){
            throw new CustomException(map.get("msg").toString());
        }
        // 已通过
        if (map.get("code").equals(3)){
            cInsuranceVehicle.setStatus(2L);
        }
        // 已驳回
        if (map.get("code").equals(4)){
            cInsuranceVehicle.setStatus(3L);
        }
        return cInsuranceVehicleMapper.checkCInsuranceVehicle(cInsuranceVehicle);
    }

    /**
     * 批量删除车辆管理-车辆保险
     * 
     * @param ids 需要删除的车辆管理-车辆保险ID
     * @return 结果
     */
    @Override
    public int deleteCInsuranceVehicleByIds(String[] ids)
    {
        return cInsuranceVehicleMapper.deleteCInsuranceVehicleByIds(ids);
    }

    /**
     * 删除车辆管理-车辆保险信息
     * 
     * @param id 车辆管理-车辆保险ID
     * @return 结果
     */
    @Override
    public int deleteCInsuranceVehicleById(String id)
    {
        return cInsuranceVehicleMapper.deleteCInsuranceVehicleById(id);
    }

    /**
     * E01List
     * @param cInsuranceVehicle
     * @return
     */
    @Override
    public List<CInsuranceVehicle> selectCInsuranceVe(CInsuranceVehicle cInsuranceVehicle){
        return cInsuranceVehicleMapper.selectCInsuranceVe(cInsuranceVehicle);
    }

    /**
     * E06lIST
     */
    @Override
    public List<CInsuranceVehicle> selectCInsuranceGe(CInsuranceVehicle cInsuranceVehicle){
        return cInsuranceVehicleMapper.selectCInsuranceGe(cInsuranceVehicle);
    }

    /**
     * 审核
     */
    @Override
    public AjaxResult checkReview(ReviewSubmit reviewSubmit){
        Map map = systemClient.reviewCommon(reviewSubmit);
        // 判断返回结构-是否需要操作业务表
        int code = (int) map.get("code");
        if (code == 3 || code == 4){
            LoginUser userInfo = tokenService.getLoginUser();
            // 审核校验
            CInsuranceVehicle cInsuranceVehicle = cInsuranceVehicleMapper.selectCInsuranceVehicleById(reviewSubmit.getRelationId());
            cInsuranceVehicle.setStatus(code==3?2L:3L);
            cInsuranceVehicle.setReviewRemarks(reviewSubmit.getReviewRemarks());
            cInsuranceVehicle.setReviewTime(new Date());
            cInsuranceVehicle.setReviewUser(userInfo.getUsername());
            int result = this.updateCInsuranceVehicle(cInsuranceVehicle);
            if (result<= 0){
                return AjaxResult.error("操作失败");
            }
        }
        //返回msg
        String msg = (String) map.get("msg");
        if (code == 1){
            return AjaxResult.error(msg);
        }
        return AjaxResult.success(msg);
    }

    @Override
    public CInsuranceVehicle selectCInsuranceLastList(String id){
        return cInsuranceVehicleMapper.selectCInsuranceLastList(id);
    }

    @Override
    public List<CInsuranceVehicleItem> selectCInsuranceItemByInsuranceId(String id){
        return cInsuranceVehicleItemMapper.selectCInsuranceItemByInsuranceId(id);
    }

    @Override
    public List<CInsuranceVehicle> selectCostIn(CInsuranceVehicle cInsuranceVehicle){
        return cInsuranceVehicleMapper.selectCostIn(cInsuranceVehicle);
    }
}
