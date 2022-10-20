package com.dlzx.pfservercar.controller.driver_app;

import com.dlzx.common.core.domain.R;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.pfservercar.client.PersonClient;
import com.dlzx.pfservercar.client.SystemClient;
import com.dlzx.pfservercar.domain.DVacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 驾驶员-考勤Controller
 * 
 * @author dcl
 * @date 2020-11-30
 */
@RestController
@RequestMapping("/driver-api/vacation")
public class DVacationController extends BaseController
{
    @Autowired
    private PersonClient personClient;
    @Autowired
    private SystemClient systemClient;

    /**
     * App 考勤-驾驶员请假列表
     */
    @RequestMapping("/E01VacationList")
    @ResponseBody
    @Log(title = "考勤-驾驶员请假列表")
    public TableDataInfo list(@RequestBody DVacation dVacation)
    {
        return personClient.E01VacationList(dVacation);
    }

    /**
     * App 考勤-请假详情（区分四种状态）
     */
    @RequestMapping("/E02VacationDetail")
    @ResponseBody
    @Log(title = "App 考勤-请假详情（区分四种状态）")
    public AjaxResult e02VacationDetail(@RequestParam("id") String id)
    {
        return personClient.E02VacationDetail(id);
    }

    /**
     * App 考勤-新增请假记录
     */
    @RequestMapping("/E04VacationAdd")
    @Log(title = "App 考勤-新增请假记录")
    public AjaxResult add(@RequestBody DVacation dVacation)
    {
        if (StringUtils.isNull(dVacation.getType())){
            return AjaxResult.error("请输入请假类型");
        }
        if (StringUtils.isNull(dVacation.getvStartTime())){
            return AjaxResult.error("请输入开始时间");
        }
        if (StringUtils.isNull(dVacation.getvEndTime())){
            return AjaxResult.error("请输入结束时间");
        }
        if (StringUtils.isNull(dVacation.getDuration())){
            return AjaxResult.error("请输入请假时长");
        }
        if (StringUtils.isNull(dVacation.getRemarks())){
            return AjaxResult.error("请输入请假事由");
        }
        return personClient.E04VacationAdd(dVacation);
    }

    /**
     * App 考勤-请假申请编辑接口
     */
    @RequestMapping("/E04VacationEditSave")
    @Log(title = "App 考勤-请假申请编辑接口")
    public AjaxResult update(@RequestBody DVacation dVacation)
    {
        if (StringUtils.isNull(dVacation.getType())){
            return AjaxResult.error("请输入请假类型");
        }
        if (StringUtils.isNull(dVacation.getvStartTime())){
            return AjaxResult.error("请输入开始时间");
        }
        if (StringUtils.isNull(dVacation.getvEndTime())){
            return AjaxResult.error("请输入结束时间");
        }
        if (StringUtils.isNull(dVacation.getDuration())){
            return AjaxResult.error("请输入请假时长");
        }
        if (StringUtils.isNull(dVacation.getRemarks())){
            return AjaxResult.error("请输入请假事由");
        }
        return personClient.E04VacationEditSave(dVacation);
    }

    /**
     * App 考勤-撤销请假申请接口
     */
    @RequestMapping("/E03UndoVacation")
    @Log(title = "App 考勤-撤销请假申请接口")
    public AjaxResult undo(@RequestBody DVacation dVacation)
    {
        systemClient.deleteExamineTaskByRelationId(dVacation.getId());
        return personClient.E03UndoVacation(dVacation);
    }

    /**
     * App 考勤-考勤类型列表
     */
    @RequestMapping(path = "/E03VacationTypeList",method = RequestMethod.POST)
    @Log(title = "App 考勤-撤销请假申请接口")
    public TableDataInfo vacationTypeList()
    {
        return personClient.E03VacationTypeList();
    }
}
