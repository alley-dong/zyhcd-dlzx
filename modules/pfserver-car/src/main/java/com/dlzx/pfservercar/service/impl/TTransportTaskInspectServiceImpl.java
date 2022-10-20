package com.dlzx.pfservercar.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.dlzx.common.core.domain.R;
import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.pfservercar.domain.model.TTransportTaskInspectItemModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskInspectModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskInspectItemVm;
import com.dlzx.pfservercar.domain.vm.TTransportTaskInspectVm;
import com.dlzx.pfservercar.domain.vm.TTransportTaskVm;
import com.dlzx.pfservercar.mapper.TTransportTaskInspectItemMapper;
import com.dlzx.pfservercar.mapper.TTransportTaskMapper;
import com.dlzx.system.api.model.LoginDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.TTransportTaskInspectMapper;
import com.dlzx.pfservercar.domain.TTransportTaskInspect;
import com.dlzx.pfservercar.service.ITTransportTaskInspectService;

/**
 * 任务管理-运输任务点检Service业务层处理
 *
 * @author dcl
 * @date 2020-12-18
 */
@Service
public class TTransportTaskInspectServiceImpl implements ITTransportTaskInspectService
{
    @Autowired
    private TTransportTaskInspectMapper tTransportTaskInspectMapper;

    @Autowired
    private TTransportTaskMapper tTransportTaskMapper;

    @Autowired
    private TTransportTaskInspectItemMapper tTransportTaskInspectItemMapper;

    @Autowired
    private DriverTokenService driverTokenService;

    /**
     * 查询任务管理-运输任务点检
     *
     * @param id 任务管理-运输任务点检ID
     * @return 任务管理-运输任务点检
     */
    @Override
    public TTransportTaskInspectVm selectTTransportTaskInspectById(String id)
    {
        // 获取点检信息
        TTransportTaskInspectVm tTransportTaskInspectVm = tTransportTaskInspectMapper.selectTTransportTaskInspectById(id);

        // 根据点检ID查询检查项
        TTransportTaskInspectItemModel tTransportTaskInspectItemModel = new TTransportTaskInspectItemModel();
        tTransportTaskInspectItemModel.setInspectId(tTransportTaskInspectVm.getId());
        List<TTransportTaskInspectItemVm> tTransportTaskInspectItemVmList = tTransportTaskInspectItemMapper.selectTTransportTaskInspectItemList(tTransportTaskInspectItemModel);
        if (tTransportTaskInspectItemVmList.size()>0){
            for (TTransportTaskInspectItemVm tTransportTaskInspectItemVm : tTransportTaskInspectItemVmList) {
                if (!StringUtils.isEmpty(tTransportTaskInspectItemVm.getFiles())){
                    tTransportTaskInspectItemVm.setFileList(tTransportTaskInspectItemVm.getFiles().split(","));
                }
            }
            tTransportTaskInspectVm.settTransportTaskInspectItemVms(tTransportTaskInspectItemVmList);
        }
        return tTransportTaskInspectVm;
    }

    /**
     * 查询任务管理-运输任务点检列表
     *
     * @param tTransportTaskInspectModel 任务管理-运输任务点检
     * @return 任务管理-运输任务点检
     */
    @Override
    public List<TTransportTaskInspectVm> selectTTransportTaskInspectList(TTransportTaskInspectModel tTransportTaskInspectModel)
    {
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        if (loginDriver== null){
            throw new CustomException("用户信息不能为空");
        }
        tTransportTaskInspectModel.setDriverId(loginDriver.getUserid());
        return tTransportTaskInspectMapper.selectTTransportTaskInspectList(tTransportTaskInspectModel);
    }

    /**
     * 新增任务管理-运输任务点检
     *
     * @param tTransportTaskInspectModel 任务管理-运输任务点检
     * @return 结果
     */
    @Override
    public int insertTTransportTaskInspect(TTransportTaskInspectModel tTransportTaskInspectModel)
    {
        tTransportTaskInspectModel.setCreateTime(DateUtils.getNowDate());
        return tTransportTaskInspectMapper.insertTTransportTaskInspect(tTransportTaskInspectModel);
    }

    /**
     * 修改任务管理-运输任务点检
     *
     * @param tTransportTaskInspectModel 任务管理-运输任务点检
     * @return 结果
     */
    @Override
    public int updateTTransportTaskInspect(TTransportTaskInspectModel tTransportTaskInspectModel)
    {
        tTransportTaskInspectModel.setUpdateTime(DateUtils.getNowDate());
        return tTransportTaskInspectMapper.updateTTransportTaskInspect(tTransportTaskInspectModel);
    }

    /**
     * 批量删除任务管理-运输任务点检
     *
     * @param ids 需要删除的任务管理-运输任务点检ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskInspectByIds(String[] ids)
    {
        return tTransportTaskInspectMapper.deleteTTransportTaskInspectByIds(ids);
    }

    /**
     * 删除任务管理-运输任务点检信息
     *
     * @param id 任务管理-运输任务点检ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskInspectById(String id)
    {
        return tTransportTaskInspectMapper.deleteTTransportTaskInspectById(id);
    }

    /**
     * 查询任务管理-运输任务点检列表
     *
     * @param tTransportTaskModel 任务管理-运输任务点检列表
     * @return 任务管理-运输任务点检集合
     */
    @Override
    public List<TTransportTaskVm> queryTTransportTaskInspectList(TTransportTaskModel tTransportTaskModel) {
        List<TTransportTaskVm> tTransportTaskVms = tTransportTaskMapper.selectTTransportTaskList(tTransportTaskModel);
        for (TTransportTaskVm tTransportTaskVm : tTransportTaskVms) {
            tTransportTaskVm.setStartInspect(tTransportTaskVm.getStartInspectCount() > 0 ? "已上报" : "未上报");
            tTransportTaskVm.setEndInspect(tTransportTaskVm.getEndInspectCount() > 0 ? "已上报" : "未上报");
            tTransportTaskVm.setProcessInspect(tTransportTaskVm.getProcessInspectCount() > 0 ? "已上报" : "未上报");
            tTransportTaskVm.setProcessInspect(tTransportTaskVm.getProcessInspect() + " " + tTransportTaskVm.getProcessInspectCount()+"/"+tTransportTaskVm.getProcessInspectMinCount());
        }
        return tTransportTaskVms;
    }

    /**
     * 查询任务管理-运输任务点检详情
     *
     * @param taskId 任务管理-运输任务点检详情
     * @return 任务管理-运输任务点检集合
     */
    @Override
    public TTransportTaskVm queryTTransportTaskInspectByTaskId(String taskId) {

        // 新建返回任务实体
        TTransportTaskVm tTransportTaskVm = new TTransportTaskVm();

        // 任务下的点检列表
        List<TTransportTaskInspectVm> tTransportTaskInspectVms = tTransportTaskInspectMapper.queryTTransportTaskInspectByTaskId(taskId);

        // 点检检查项目列表
        List<String> tTransportTaskInspectIds = tTransportTaskInspectVms.stream()
                .map(TTransportTaskInspect::getId)
                .collect(Collectors.toList());

        if (tTransportTaskInspectIds.size()>0){
            List<TTransportTaskInspectItemVm>tTransportTaskInspectItemVmList = tTransportTaskInspectItemMapper.queryTransportTaskInspectItemList(tTransportTaskInspectIds);
            for (TTransportTaskInspectVm tTransportTaskInspectVm : tTransportTaskInspectVms) {
                tTransportTaskInspectVm.settTransportTaskInspectItemVms(
                        tTransportTaskInspectItemVmList.stream()
                                .filter(t->t.getInspectId().equals(tTransportTaskInspectVm.getId()))
                                .collect(Collectors.toList())
                );

                // 文件分割
                for (TTransportTaskInspectItemVm gettTransportTaskInspectItemVm : tTransportTaskInspectVm.gettTransportTaskInspectItemVms()) {
                    if (!StringUtils.isEmpty(gettTransportTaskInspectItemVm.getFiles())){
                        String[] split = gettTransportTaskInspectItemVm.getFiles().split(",");
                        gettTransportTaskInspectItemVm.setFileList(split);
                    }
                }
            }
        }
        tTransportTaskVm.settTransportTaskInspectVms(tTransportTaskInspectVms);
        return tTransportTaskVm;
    }
}
