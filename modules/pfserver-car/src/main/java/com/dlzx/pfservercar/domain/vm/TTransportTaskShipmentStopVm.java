package com.dlzx.pfservercar.domain.vm;

import com.dlzx.pfservercar.domain.TTransportTaskShipmentStop;

/**
 * 类描述：
 * @className TTransportTaskShipmentStopVm
 * company 赛拓信息技术有限公司
 * @author DongCL
 * @date 2021-01-05 17:29
 */
public class TTransportTaskShipmentStopVm extends TTransportTaskShipmentStop {
    private static final long serialVersionUID = 1L;

    /** 地址*/
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
