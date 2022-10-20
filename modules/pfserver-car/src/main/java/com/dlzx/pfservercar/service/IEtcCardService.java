package com.dlzx.pfservercar.service;

import java.util.List;
import java.util.Map;

import com.dlzx.pfservercar.domain.EtcCard;
import com.dlzx.pfservercar.domain.EtcCardImport;

/**
 * ETC管理-ETC卡Service接口
 * 
 * @author dlzx
 * @date 2020-12-01
 */
public interface IEtcCardService 
{
    /**
     * 查询ETC管理-ETC卡
     * 
     * @param id ETC管理-ETC卡ID
     * @return ETC管理-ETC卡
     */
    public EtcCard selectEtcCardById(String id);

    /**
     * 查询ETC管理-ETC卡列表
     * 
     * @param etcCard ETC管理-ETC卡
     * @return ETC管理-ETC卡集合
     */
    public List<EtcCard> selectEtcCardList(EtcCard etcCard);

    public List<EtcCard> selectEtcCardListPaiHao(String licensePlate);

    public List<EtcCard> selectEtcCardListKaHao(String etcCardNumber);

    public List<EtcCard> selectEtcCardListAll();
    /**
     * 新增ETC管理-ETC卡
     * 
     * @param etcCard ETC管理-ETC卡
     * @return 结果
     */
    public int insertEtcCard(EtcCard etcCard);

    /**
     * 修改ETC管理-ETC卡
     * 
     * @param etcCard ETC管理-ETC卡
     * @return 结果
     */
    public int updateEtcCard(EtcCard etcCard);

    /**
     * 批量删除ETC管理-ETC卡
     * 
     * @param ids 需要删除的ETC管理-ETC卡ID
     * @return 结果
     */
    public int deleteEtcCardByIds(String[] ids);

    /**
     * 删除ETC管理-ETC卡信息
     * 
     * @param id ETC管理-ETC卡ID
     * @return 结果
     */
    public int deleteEtcCardById(String id);

    public Map<String,String> importUser(List<EtcCardImport> etcCardList);

    public void initEmpTravelStatus();
}
