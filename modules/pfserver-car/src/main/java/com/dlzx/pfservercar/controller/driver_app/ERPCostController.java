package com.dlzx.pfservercar.controller.driver_app;

import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.ESBParams;
import com.dlzx.common.core.web.domain.ESBResult;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.pfservercar.domain.model.ERPCostModel;
import com.dlzx.pfservercar.domain.model.OTMTransportTaskOrderPartsModel;
import com.dlzx.pfservercar.service.ERPCostService;
import com.dlzx.pfservercar.service.ITTransportTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类描述：
 * @className ERPCostController
 * company: 赛拓信息技术有限公司
 * @author：DongCL
 * @date 2021-02-21 13:25
 */
@RestController
@RequestMapping("/erp-api/erpCost")
public class ERPCostController extends BaseController {


    @Autowired
    private ERPCostService erpCostService;

    /**
     * @describe
     * @author DongCL
     * @date 2021-02-21 15:30
     * @param esbParams
     * @return
     */
    @RequestMapping("/queryERPCostList")
    @ResponseBody
    @Log(title = "获取车辆费用")
    public ESBResult queryERPCostList(@RequestBody ESBParams<ERPCostModel> esbParams)
    {
        return erpCostService.queryERPCostList(esbParams.getMsgBody());
    }


}
