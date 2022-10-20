package com.dlzx.pfservercar.service;

import java.util.List;
import com.dlzx.pfservercar.domain.CInsuranceClaimsParticipant;

/**
 * 车辆管理-理赔参与方Service接口
 * 
 * @author dongchanglong
 * @date 2021-03-12
 */
public interface ICInsuranceClaimsParticipantService 
{
    /**
     * 查询车辆管理-理赔参与方
     * 
     * @param id 车辆管理-理赔参与方ID
     * @return 车辆管理-理赔参与方
     */
    public CInsuranceClaimsParticipant selectCInsuranceClaimsParticipantById(String id);

    /**
     * 查询车辆管理-理赔参与方列表
     * 
     * @param cInsuranceClaimsParticipant 车辆管理-理赔参与方
     * @return 车辆管理-理赔参与方集合
     */
    public List<CInsuranceClaimsParticipant> selectCInsuranceClaimsParticipantList(CInsuranceClaimsParticipant cInsuranceClaimsParticipant);

    /**
     * 新增车辆管理-理赔参与方
     * 
     * @param cInsuranceClaimsParticipant 车辆管理-理赔参与方
     * @return 结果
     */
    public int insertCInsuranceClaimsParticipant(CInsuranceClaimsParticipant cInsuranceClaimsParticipant);

    /**
     * 修改车辆管理-理赔参与方
     * 
     * @param cInsuranceClaimsParticipant 车辆管理-理赔参与方
     * @return 结果
     */
    public int updateCInsuranceClaimsParticipant(CInsuranceClaimsParticipant cInsuranceClaimsParticipant);

    /**
     * 批量删除车辆管理-理赔参与方
     * 
     * @param ids 需要删除的车辆管理-理赔参与方ID
     * @return 结果
     */
    public int deleteCInsuranceClaimsParticipantByIds(String[] ids);

    /**
     * 删除车辆管理-理赔参与方信息
     * 
     * @param id 车辆管理-理赔参与方ID
     * @return 结果
     */
    public int deleteCInsuranceClaimsParticipantById(String id);
}
