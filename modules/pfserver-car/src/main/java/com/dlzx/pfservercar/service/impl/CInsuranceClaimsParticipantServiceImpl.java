package com.dlzx.pfservercar.service.impl;

import java.util.List;
import java.util.UUID;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.CInsuranceClaimsParticipantMapper;
import com.dlzx.pfservercar.domain.CInsuranceClaimsParticipant;
import com.dlzx.pfservercar.service.ICInsuranceClaimsParticipantService;

/**
 * 车辆管理-理赔参与方Service业务层处理
 * 
 * @author dongchanglong
 * @date 2021-03-12
 */
@Service
public class CInsuranceClaimsParticipantServiceImpl implements ICInsuranceClaimsParticipantService 
{
    @Autowired
    private CInsuranceClaimsParticipantMapper cInsuranceClaimsParticipantMapper;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询车辆管理-理赔参与方
     * 
     * @param id 车辆管理-理赔参与方ID
     * @return 车辆管理-理赔参与方
     */
    @Override
    public CInsuranceClaimsParticipant selectCInsuranceClaimsParticipantById(String id)
    {
        return cInsuranceClaimsParticipantMapper.selectCInsuranceClaimsParticipantById(id);
    }

    /**
     * 查询车辆管理-理赔参与方列表
     * 
     * @param cInsuranceClaimsParticipant 车辆管理-理赔参与方
     * @return 车辆管理-理赔参与方
     */
    @Override
    public List<CInsuranceClaimsParticipant> selectCInsuranceClaimsParticipantList(CInsuranceClaimsParticipant cInsuranceClaimsParticipant)
    {
        return cInsuranceClaimsParticipantMapper.selectCInsuranceClaimsParticipantList(cInsuranceClaimsParticipant);
    }

    /**
     * 新增车辆管理-理赔参与方
     * 
     * @param cInsuranceClaimsParticipant 车辆管理-理赔参与方
     * @return 结果
     */
    @Override
    public int insertCInsuranceClaimsParticipant(CInsuranceClaimsParticipant cInsuranceClaimsParticipant)
    {
        cInsuranceClaimsParticipant.setCreateTime(DateUtils.getNowDate());
        cInsuranceClaimsParticipant.setId(UUID.randomUUID()+"");
        cInsuranceClaimsParticipant.setCreateBy(tokenService.getLoginUser().getUsername());
        return cInsuranceClaimsParticipantMapper.insertCInsuranceClaimsParticipant(cInsuranceClaimsParticipant);
    }

    /**
     * 修改车辆管理-理赔参与方
     * 
     * @param cInsuranceClaimsParticipant 车辆管理-理赔参与方
     * @return 结果
     */
    @Override
    public int updateCInsuranceClaimsParticipant(CInsuranceClaimsParticipant cInsuranceClaimsParticipant)
    {
        cInsuranceClaimsParticipant.setUpdateTime(DateUtils.getNowDate());
        cInsuranceClaimsParticipant.setUpdateBy(tokenService.getLoginUser().getUsername());
        return cInsuranceClaimsParticipantMapper.updateCInsuranceClaimsParticipant(cInsuranceClaimsParticipant);
    }

    /**
     * 批量删除车辆管理-理赔参与方
     * 
     * @param ids 需要删除的车辆管理-理赔参与方ID
     * @return 结果
     */
    @Override
    public int deleteCInsuranceClaimsParticipantByIds(String[] ids)
    {
        return cInsuranceClaimsParticipantMapper.deleteCInsuranceClaimsParticipantByIds(ids);
    }

    /**
     * 删除车辆管理-理赔参与方信息
     * 
     * @param id 车辆管理-理赔参与方ID
     * @return 结果
     */
    @Override
    public int deleteCInsuranceClaimsParticipantById(String id)
    {
        return cInsuranceClaimsParticipantMapper.deleteCInsuranceClaimsParticipantById(id);
    }
}
