package com.dlzx.pfserverperson.domain.model;

import lombok.Data;

/**
 * 类描述：
 * @ClassName OTMDriverModel
 * Company: 赛拓信息技术有限公司
 * @author：DongCL
 * @Date 2020-12-24 11:38
 */
@Data
public class OTMDriverModel {

    /**
     * 司机姓名
     */
    private String NAME;

    /**
     * 手机号
     */
    private String PHONE;

    /**
     * 身份证号
     */
    private String ID_NUMBER;

    /**
     * 从业资格证号
     */
    private String QUALIFICATION_CERTIFICATE;

    /**
     * 是否生效
     */
    private String ENABLED_FLAG;

    /**
     * 准驾车型
     */
    private String VEHICLE_CLASS;


}
