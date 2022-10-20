package com.dlzx.common.core.web.domain;

import java.util.List;

public class TMSMsgBody {

    private List<TMSDriverDto> driverDto;

    private List<TMSCarDto> equipmentDto;


    public List<TMSCarDto> getEquipmentDto() {
        return equipmentDto;
    }

    public void setEquipmentDto(List<TMSCarDto> equipmentDto) {
        this.equipmentDto = equipmentDto;
    }

    public List<TMSDriverDto> getDriverDto() {
        return driverDto;
    }

    public void setDriverDto(List<TMSDriverDto> driverDto) {
        this.driverDto = driverDto;
    }
}
