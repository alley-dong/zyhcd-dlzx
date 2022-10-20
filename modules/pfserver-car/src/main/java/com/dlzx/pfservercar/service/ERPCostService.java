package com.dlzx.pfservercar.service;

import com.dlzx.common.core.web.domain.ESBResult;
import com.dlzx.pfservercar.domain.model.ERPCostModel;

/**
 * 类描述：
 * @className ERPCostService
 * company 赛拓信息技术有限公司
 * @author DongCL
 * @date 2021-02-21 15:39
 */
public interface ERPCostService {

    /**
     * @describe ERP 车辆费用
     * @author DongCL
     * @date 2021-02-21 15:34
     * @param
     * @return
     */
    ESBResult queryERPCostList(ERPCostModel erpCostModel);

}
