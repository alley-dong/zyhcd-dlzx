package com.dlzx.pfserverperson.service.impl;

import java.util.Date;
import java.util.List;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.pfserverperson.domain.DPerformanceChang;
import com.dlzx.pfserverperson.domain.DPerformanceDuan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DPerformanceMapper;
import com.dlzx.pfserverperson.domain.DPerformance;
import com.dlzx.pfserverperson.service.IDPerformanceService;

/**
 * 驾驶员-绩效Service业务层处理
 *
 * @author dlzx
 * @date 2020-11-30
 */
@Service
public class DPerformanceServiceImpl implements IDPerformanceService {
    @Autowired
    private DPerformanceMapper dPerformanceMapper;

    /**
     * 查询驾驶员-绩效
     *
     * @param id 驾驶员-绩效ID
     * @return 驾驶员-绩效
     */
    @Override
    public DPerformance selectDPerformanceById(String id) {
        return dPerformanceMapper.selectDPerformanceById(id);
    }

    @Override
    public DPerformance selectDPerformanceByName(String name) {
        return dPerformanceMapper.selectDPerformanceByName(name);
    }

    /**
     * 查询驾驶员-绩效列表
     *
     * @param dPerformance 驾驶员-绩效
     * @return 驾驶员-绩效
     */
    @Override
    public List<DPerformance> selectDPerformanceList(DPerformance dPerformance) {
        return dPerformanceMapper.selectDPerformanceList(dPerformance);
    }

    //    长途绩效下拉选
    @Override
    public List<DPerformance> selectDPerformanceListJiXiao(DPerformance dPerformance) {
        return dPerformanceMapper.selectDPerformanceListJiXiao(dPerformance);
    }

    /**
     * 新增驾驶员-绩效
     *
     * @param dPerformance 驾驶员-绩效
     * @return 结果
     */
    @Override
    public int insertDPerformance(DPerformance dPerformance) {
        dPerformance.setCreateTime(DateUtils.getNowDate());
        return dPerformanceMapper.insertDPerformance(dPerformance);
    }

    @Override
    public int insertDPerformanceC(DPerformanceChang dPerformanceChang) {
        dPerformanceChang.setCreateTime(DateUtils.getNowDate());
        return dPerformanceMapper.insertDPerformanceC(dPerformanceChang);
    }

    @Override
    public int insertDPerformanceD(DPerformanceDuan dPerformanceDuan) {
        dPerformanceDuan.setCreateTime(DateUtils.getNowDate());
        return dPerformanceMapper.insertDPerformanceD(dPerformanceDuan);
    }

    /**
     * 修改驾驶员-绩效
     *
     * @param dPerformance 驾驶员-绩效
     * @return 结果
     */
    @Override
    public int updateDPerformance(DPerformance dPerformance) {
        dPerformance.setUpdateTime(DateUtils.getNowDate());
        return dPerformanceMapper.updateDPerformance(dPerformance);
    }

    @Override
    public int updateDPerformanceC(DPerformanceChang dPerformanceChang) {
        dPerformanceChang.setUpdateTime(DateUtils.getNowDate());
        return dPerformanceMapper.updateDPerformanceC(dPerformanceChang);
    }

    @Override
    public int updateDPerformanceD(DPerformanceDuan dPerformanceDuan) {
        dPerformanceDuan.setUpdateTime(DateUtils.getNowDate());
        return dPerformanceMapper.updateDPerformanceD(dPerformanceDuan);
    }

    /**
     * 批量删除驾驶员-绩效
     *
     * @param ids 需要删除的驾驶员-绩效ID
     * @return 结果
     */
    @Override
    public int deleteDPerformanceByIds(String[] ids) {
        return dPerformanceMapper.deleteDPerformanceByIds(ids);
    }

    /**
     * 删除驾驶员-绩效信息
     *
     * @param id 驾驶员-绩效ID
     * @return 结果
     */
    @Override
    public int deleteDPerformanceById(String id) {
        return dPerformanceMapper.deleteDPerformanceById(id);
    }


    /**
     * @describe car服务调用 - 获取绩效信息
     * @author DongCL
     * @date 2021-01-08 10:25
     * @param vehicleType 车型
     * @param routeId 线路ID
     * @param isVice 是否有对班
     * @return
     */
    @Override
    public List<DPerformance> queryPerformance(String vehicleType, String routeId, Long isVice) {
        return dPerformanceMapper.queryPerformance(vehicleType,routeId,isVice);
    }
}
