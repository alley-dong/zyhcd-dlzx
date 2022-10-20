package com.dlzx.pfservercar.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.CInsuranceClaimsParticipant;
import com.dlzx.pfservercar.mapper.CInsuranceClaimsParticipantMapper;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.CInsuranceClaimsMapper;
import com.dlzx.pfservercar.domain.CInsuranceClaims;
import com.dlzx.pfservercar.service.ICInsuranceClaimsService;

/**
 * 车辆管理-理赔Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-03
 */
@Service
public class CInsuranceClaimsServiceImpl implements ICInsuranceClaimsService 
{
    @Autowired
    private CInsuranceClaimsMapper cInsuranceClaimsMapper;
    @Autowired
    private CInsuranceClaimsParticipantMapper cInsuranceClaimsParticipantMapper;

    /**
     * 查询车辆管理-理赔
     * 
     * @param id 车辆管理-理赔ID
     * @return 车辆管理-理赔
     */
    @Override
    public CInsuranceClaims selectCInsuranceClaimsById(String id)
    {
        CInsuranceClaims cInsuranceClaims = cInsuranceClaimsMapper.selectCInsuranceClaimsById(id);

        CInsuranceClaimsParticipant cInsuranceClaimsParticipant = new CInsuranceClaimsParticipant();
        cInsuranceClaimsParticipant.setInsuranceClaimsId(cInsuranceClaims.getId());
        List<CInsuranceClaimsParticipant> cInsuranceClaimsParticipants = cInsuranceClaimsParticipantMapper.selectCInsuranceClaimsParticipantList(cInsuranceClaimsParticipant);
        cInsuranceClaims.setOnOurPartList(cInsuranceClaimsParticipants.stream()
                .filter(our->our.getUserType()==1)
                .collect(Collectors.toList()));

        cInsuranceClaims.setOppositeSideList(cInsuranceClaimsParticipants.stream()
                .filter(our->our.getUserType()==2)
                .collect(Collectors.toList()));

        cInsuranceClaims.setThirdPartyList(cInsuranceClaimsParticipants.stream()
                .filter(our->our.getUserType()==3)
                .collect(Collectors.toList()));

        return cInsuranceClaims;
    }

    /**
     * 查询车辆管理-理赔列表
     * 
     * @param cInsuranceClaims 车辆管理-理赔
     * @return 车辆管理-理赔
     */
    @Override
    public List<CInsuranceClaims> selectCInsuranceClaimsList(CInsuranceClaims cInsuranceClaims)
    {
        return cInsuranceClaimsMapper.selectCInsuranceClaimsList(cInsuranceClaims);
    }

    /**
     * 新增车辆管理-理赔
     * 
     * @param cInsuranceClaims 车辆管理-理赔
     * @return 结果
     */
    @Override
    public int insertCInsuranceClaims(CInsuranceClaims cInsuranceClaims)
    {
        cInsuranceClaims.setCreateTime(DateUtils.getNowDate());
        cInsuranceClaimsMapper.insertCInsuranceClaims(cInsuranceClaims);

        // 创建默认的我方信息
        CInsuranceClaimsParticipant cInsuranceClaimsParticipant = new CInsuranceClaimsParticipant();
        cInsuranceClaimsParticipant.setInsuranceClaimsId(cInsuranceClaims.getId());
        cInsuranceClaimsParticipant.setCreateTime(new Date());
        cInsuranceClaimsParticipant.setCreateBy(cInsuranceClaims.getCreateBy());
        cInsuranceClaimsParticipant.setId(UUID.randomUUID()+"");
        cInsuranceClaimsParticipant.setName("专业运营部");
        cInsuranceClaimsParticipant.setUserType(1L);
        return cInsuranceClaimsParticipantMapper.insertCInsuranceClaimsParticipant(cInsuranceClaimsParticipant);
    }

    /**
     * 修改车辆管理-理赔
     * 
     * @param cInsuranceClaims 车辆管理-理赔
     * @return 结果
     */
    @Override
    public int updateCInsuranceClaims(CInsuranceClaims cInsuranceClaims)
    {
        cInsuranceClaims.setUpdateTime(DateUtils.getNowDate());
        return cInsuranceClaimsMapper.updateCInsuranceClaims(cInsuranceClaims);
    }

    /**
     * 批量删除车辆管理-理赔
     * 
     * @param ids 需要删除的车辆管理-理赔ID
     * @return 结果
     */
    @Override
    public int deleteCInsuranceClaimsByIds(String[] ids)
    {
        return cInsuranceClaimsMapper.deleteCInsuranceClaimsByIds(ids);
    }

    /**
     * 删除车辆管理-理赔信息
     * 
     * @param id 车辆管理-理赔ID
     * @return 结果
     */
    @Override
    public int deleteCInsuranceClaimsById(String id)
    {
        return cInsuranceClaimsMapper.deleteCInsuranceClaimsById(id);
    }
}
