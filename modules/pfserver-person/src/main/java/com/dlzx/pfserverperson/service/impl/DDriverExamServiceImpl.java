package com.dlzx.pfserverperson.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DDriverExamMapper;
import com.dlzx.pfserverperson.domain.DDriverExam;
import com.dlzx.pfserverperson.service.IDDriverExamService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 驾驶员-司机考试Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-11-18
 */
@Service
public class DDriverExamServiceImpl implements IDDriverExamService 
{
    @Autowired
    private DDriverExamMapper dDriverExamMapper;

    /**
     * 查询驾驶员-司机考试
     * 
     * @param id 驾驶员-司机考试ID
     * @return 驾驶员-司机考试
     */
    @Override
    public DDriverExam selectDDriverExamById(String id)
    {
        return dDriverExamMapper.selectDDriverExamById(id);
    }

    /**
     * 查询驾驶员-司机考试列表
     * 
     * @param dDriverExam 驾驶员-司机考试
     * @return 驾驶员-司机考试
     */
    @Override
    public List<DDriverExam> selectDDriverExamList(DDriverExam dDriverExam)
    {
        return dDriverExamMapper.selectDDriverExamList(dDriverExam);
    }

    /**
     * 查询驾驶员-司机考试列表
     *
     * @param dDriverExam 驾驶员-司机考试
     * @return 驾驶员-司机考试集合
     */
    @Override
    public List<DDriverExam> selectDDriverExamListByPaperId(DDriverExam dDriverExam)
    {
        return dDriverExamMapper.selectDDriverExamListByPaperId(dDriverExam);
    }

    /**
     * 新增驾驶员-司机考试
     * 
     * @param dDriverExam 驾驶员-司机考试
     * @return 结果
     */
    @Override
    public int insertDDriverExam(DDriverExam dDriverExam)
    {
        dDriverExam.setCreateTime(DateUtils.getNowDate());
        return dDriverExamMapper.insertDDriverExam(dDriverExam);
    }

    /**
     * 新增驾驶员-司机考试
     *
     * @param list 驾驶员-司机考试集合
     * @return 结果
     */
    @Override
    public int insertDDriverExamList(List<DDriverExam> list)
    {

        return dDriverExamMapper.insertDDriverExamList(list);
    }

    /**
     * 修改驾驶员-司机考试
     * 
     * @param dDriverExam 驾驶员-司机考试
     * @return 结果
     */
    @Override
    public int updateDDriverExam(DDriverExam dDriverExam)
    {
        dDriverExam.setUpdateTime(DateUtils.getNowDate());
        return dDriverExamMapper.updateDDriverExam(dDriverExam);
    }

    /**
     * 批量删除驾驶员-司机考试
     * 
     * @param ids 需要删除的驾驶员-司机考试ID
     * @return 结果
     */
    @Override
    public int deleteDDriverExamByIds(String[] ids)
    {
        return dDriverExamMapper.deleteDDriverExamByIds(ids);
    }

    @Override
    public int deleteDDriverExamByIdList(List<String> list)
    {
        return dDriverExamMapper.deleteDDriverExamByIdList(list);
    }
    /**
     * 删除驾驶员-司机考试信息
     * 
     * @param id 驾驶员-司机考试ID
     * @return 结果
     */
    @Override
    public int deleteDDriverExamById(String id)
    {
        return dDriverExamMapper.deleteDDriverExamById(id);
    }

    /**
     * 查看笔试分数
     */
    @Override
    public DDriverExam selectDDriverExamByDriverId(String id){
        return dDriverExamMapper.selectDDriverExamByDriverId(id);
    }
    /**
     * 查驾驶员所对应的距离当前时间最近的试卷
     */
    @Override
    public DDriverExam selectDDriverExamByDriverIdNearQ(String userId){
        return dDriverExamMapper.selectDDriverExamByDriverIdNearQ(userId);
    }

}
