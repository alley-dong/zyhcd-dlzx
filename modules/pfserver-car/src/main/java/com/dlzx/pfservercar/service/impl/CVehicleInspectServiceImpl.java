package com.dlzx.pfservercar.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.client.SystemClient;
import com.dlzx.pfservercar.domain.CInsuranceVehicle;
import com.dlzx.pfservercar.domain.CVehicleInspectMonthly;
import com.dlzx.pfservercar.domain.CVehicleInspectMonthlyItem;
import com.dlzx.pfservercar.domain.RRepair;
import com.dlzx.pfservercar.domain.ReviewSubmit;
import com.dlzx.pfservercar.domain.ReviewSubmitR;
import com.dlzx.pfservercar.mapper.CVehicleInspectMonthlyItemMapper;
import com.dlzx.system.api.model.LoginUser;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.CVehicleInspectMapper;
import com.dlzx.pfservercar.domain.CVehicleInspect;
import com.dlzx.pfservercar.service.ICVehicleInspectService;

/**
 * 车辆管理-车辆定检记录Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-08
 */
@Service
public class CVehicleInspectServiceImpl implements ICVehicleInspectService 
{
    @Autowired
    private CVehicleInspectMapper cVehicleInspectMapper;
    @Autowired
    private SystemClient systemClient;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CVehicleInspectMonthlyServiceImpl icVehicleInspectMonthlyService;
    @Autowired
    private CVehicleInspectMonthlyItemMapper cVehicleInspectMonthlyItemMapper;
    /**
     * 查询车辆管理-车辆定检记录
     * 
     * @param id 车辆管理-车辆定检记录ID
     * @return 车辆管理-车辆定检记录
     */
    @Override
    public CVehicleInspect selectCVehicleInspectById(String id)
    {
        return cVehicleInspectMapper.selectCVehicleInspectById(id);
    }

    @Override
    public List<CVehicleInspect> selectCVehicleInspectInfo(String monthlyId) {
        return cVehicleInspectMapper.selectCVehicleInspectInfo(monthlyId);
    }

    /**
     * 查询车辆管理-车辆定检记录列表
     * 
     * @param cVehicleInspect 车辆管理-车辆定检记录
     * @return 车辆管理-车辆定检记录
     */
    @Override
    public List<CVehicleInspect> selectCVehicleInspectList(CVehicleInspect cVehicleInspect)
    {
        return cVehicleInspectMapper.selectCVehicleInspectList(cVehicleInspect);
    }

    @Override
    public List<CVehicleInspect> selectCVehicleInspectListApp(CVehicleInspect cVehicleInspect) {
        return cVehicleInspectMapper.selectCVehicleInspectListApp(cVehicleInspect);
    }

    /**
     * 新增车辆管理-车辆定检记录
     * 
     * @param cVehicleInspect 车辆管理-车辆定检记录
     * @return 结果
     */
    @Override
    public int insertCVehicleInspect(CVehicleInspect cVehicleInspect)
    {
        cVehicleInspect.setCreateTime(DateUtils.getNowDate());
        return cVehicleInspectMapper.insertCVehicleInspect(cVehicleInspect);
    }

    /**
     * 修改车辆管理-车辆定检记录
     * 
     * @param cVehicleInspect 车辆管理-车辆定检记录
     * @return 结果
     */
    @Override
    public int updateCVehicleInspect(CVehicleInspect cVehicleInspect)
    {
        cVehicleInspect.setUpdateTime(DateUtils.getNowDate());
        return cVehicleInspectMapper.updateCVehicleInspect(cVehicleInspect);
    }

    /**
     * 批量删除车辆管理-车辆定检记录
     * 
     * @param ids 需要删除的车辆管理-车辆定检记录ID
     * @return 结果
     */
    @Override
    public int deleteCVehicleInspectByIds(String[] ids)
    {
        return cVehicleInspectMapper.deleteCVehicleInspectByIds(ids);
    }

    /**
     * 删除车辆管理-车辆定检记录信息
     * 
     * @param id 车辆管理-车辆定检记录ID
     * @return 结果
     */
    @Override
    public int deleteCVehicleInspectById(String id)
    {
        return cVehicleInspectMapper.deleteCVehicleInspectById(id);
    }

    @Override
    public AjaxResult checkCInsuranceVehicle(ReviewSubmitR reviewSubmit) {
        ReviewSubmit reviewSubmit1 = new ReviewSubmit();
        reviewSubmit1.setUserId(reviewSubmit.getUserId());
        reviewSubmit1.setReviewRemarks(reviewSubmit.getReviewRemarks());
        reviewSubmit1.setRelationId(reviewSubmit.getRelationId());
        reviewSubmit1.setStatus(reviewSubmit.getStatus());
        Map map = systemClient.reviewCommon(reviewSubmit1);
        // 判断返回结构-是否需要操作业务表
        int code = (int) map.get("code");
        if (code == 3 || code == 4){
            LoginUser userInfo = tokenService.getLoginUser();
            // 审核校验
            CVehicleInspect cVehicleInspect = cVehicleInspectMapper.selectCVehicleInspectById(reviewSubmit.getRelationId());
            cVehicleInspect.setStatus(code==3?2L:3L);
            cVehicleInspect.setReviewRemarks(reviewSubmit.getReviewRemarks());
            cVehicleInspect.setReviewTime(new Date());
            cVehicleInspect.setReviewUser(userInfo.getUsername());
            int result = this.updateCVehicleInspect(cVehicleInspect);
            if (result<= 0){
                return AjaxResult.error("操作失败");
            }
        }

        if (code == 3){
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            String format = simpleDateFormat.format(date);
            List<CVehicleInspectMonthly> cVehicleInspectMonthlies = icVehicleInspectMonthlyService.selectCVehicleInspectMonthlyByInspectDate(format);
            if (cVehicleInspectMonthlies.size()>0 ) {
                // 通过 完成+1 未完成-1
                for (CVehicleInspectMonthly cVehicleInspectMonthly1 : cVehicleInspectMonthlies) {
                    Date inspectDate = cVehicleInspectMonthly1.getInspectDate();
                    // sql实现+1操作
                    icVehicleInspectMonthlyService.updateCVehicleInspectMonthlyDate(inspectDate);
                }

            }
            CVehicleInspect cVehicleInspect = cVehicleInspectMapper.selectCVehicleInspectById(reviewSubmit.getRelationId());
            CVehicleInspectMonthlyItem cVehicleInspectMonthlyItem = new CVehicleInspectMonthlyItem();
            cVehicleInspectMonthlyItem.setVehicleId(reviewSubmit.getVehicleId());
            cVehicleInspectMonthlyItem.setMonthlyId(cVehicleInspectMonthlies.get(0).getId());
            cVehicleInspectMonthlyItem.setInspect(format);
            List<CVehicleInspectMonthlyItem> cVehicleInspectMonthlyItemList = cVehicleInspectMonthlyItemMapper.selectItemByCVid(cVehicleInspectMonthlyItem);
            // update by 3.22 出现了下标越界
            if (cVehicleInspectMonthlyItemList != null && cVehicleInspectMonthlyItemList.size()>0){
                cVehicleInspectMonthlyItemList.get(0).setStatus(2L);
                cVehicleInspectMonthlyItemList.get(0).setDriverName(cVehicleInspect.getCreateBy());
                cVehicleInspectMonthlyItemList.get(0).setDriverId(cVehicleInspect.getDriverId());
                cVehicleInspectMonthlyItemList.get(0).setInspectDate(new Date());
                cVehicleInspect.setMonthlyItemId(cVehicleInspectMonthlyItemList.get(0).getId());
                cVehicleInspectMonthlyItemMapper.updateCVehicleInspectMonthlyItem(cVehicleInspectMonthlyItemList.get(0));
            }
            cVehicleInspectMapper.updateCVehicleInspect(cVehicleInspect);
        }
        //返回msg
        String msg = (String) map.get("msg");
        if (code == 1){
            return AjaxResult.error(msg);
        }
        return AjaxResult.success(msg);
    }

    @Override
    public CVehicleInspect selectCVehicleInspectByMonthlyItemId(String id){
        return cVehicleInspectMapper.selectCVehicleInspectByMonthlyItemId(id);
    }
}
