package com.dlzx.pfserverperson.service.impl;

import java.util.List;

import com.dlzx.pfserverperson.domain.*;
import com.dlzx.pfserverperson.mapper.DTrainingUserRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DTrainingUserMapper;
import com.dlzx.pfserverperson.service.IDTrainingUserService;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 驾驶员-培训人员关联Service业务层处理
 *
 * @author dlzx
 * @date 2020-11-26
 */
@Service
public class DTrainingUserServiceImpl implements IDTrainingUserService {
    @Autowired
    private DTrainingUserMapper dTrainingUserMapper;

    @Autowired
    private DTrainingUserRecordMapper dTrainingUserRecordMapper;
    // 事务管理器
    @Autowired
    private DataSourceTransactionManager transactionManager;

    /**
     * 查询驾驶员-培训人员关联
     *
     * @param id 驾驶员-培训人员关联ID
     * @return 驾驶员-培训人员关联
     */
    @Override
    public DTrainingUser selectDTrainingUserById(String id) {
        return dTrainingUserMapper.selectDTrainingUserById(id);
    }

    @Override
    public List<DTrainingUser> selectDTrainingUserByTrainingId(String trainingId) {
        return dTrainingUserMapper.selectDTrainingUserByTrainingId(trainingId);
    }

    /**
     * 查询驾驶员-培训人员关联列表
     *
     * @param dTrainingUser 驾驶员-培训人员关联
     * @return 驾驶员-培训人员关联
     */
    @Override
    public List<DTrainingUser> selectDTrainingUserList(DTrainingUser dTrainingUser) {
        return dTrainingUserMapper.selectDTrainingUserList(dTrainingUser);
    }

    @Override
    public List<DTrainingUser> selectDTrainingUserTwoList(DTrainingUser dTrainingUser) {
        return dTrainingUserMapper.selectDTrainingUserTwoList(dTrainingUser);
    }

    /**
     * 新增驾驶员-培训人员关联
     *
     * @param dTrainingUser 驾驶员-培训人员关联
     * @return 结果
     */
    @Override
    public int insertDTrainingUser(DTrainingUser dTrainingUser) {
        return dTrainingUserMapper.insertDTrainingUser(dTrainingUser);
    }

    @Override
    public int insertDTrainingUserS(List<DTrainingUser> userList) {
        return dTrainingUserMapper.insertDTrainingUserS(userList);
    }

    /**
     * 新增公告-事务
     *
     * @param userList       人员信息
     * @param userRecordList 人员记录信息
     * @return
     */
    @Override
    @Transactional
    public int insertDTrainingUserSOrRecord(List<DTrainingUser> userList, List<DTrainingUserRecord> userRecordList) {


        int result = 1;
        try {
            if (userList.size() > 0) {
                result = this.insertDTrainingUserS(userList);
            }
            if (userRecordList.size() > 0) {
                result = dTrainingUserRecordMapper.insertDTrainingUserRecordT(userRecordList);
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    /**
     * 修改驾驶员-培训人员关联
     *
     * @param dTrainingUser 驾驶员-培训人员关联
     * @return 结果
     */
    @Override
    public int updateDTrainingUser(DTrainingUser dTrainingUser) {
        return dTrainingUserMapper.updateDTrainingUser(dTrainingUser);
    }

    @Override
    public int updateDTrainingStatus(DTrainingAp dTrainingAp) {
        return dTrainingUserMapper.updateDTrainingStatus(dTrainingAp);
    }
    /**
     * 批量删除驾驶员-培训人员关联
     *
     * @param ids 需要删除的驾驶员-培训人员关联ID
     * @return 结果
     */
    @Override
    public int deleteDTrainingUserByIds(String[] ids) {
        return dTrainingUserMapper.deleteDTrainingUserByIds(ids);
    }

    /**
     * 删除驾驶员-培训人员关联信息
     *
     * @param id 驾驶员-培训人员关联ID
     * @return 结果
     */
    @Override
    public int deleteDTrainingUserById(String id) {
        return dTrainingUserMapper.deleteDTrainingUserById(id);
    }

    @Override
    public int deleteDTrainingUserByTrainingId(String trainingId) {
        return dTrainingUserMapper.deleteDTrainingUserByTrainingId(trainingId);
    }

    @Override
    public List<DTrainingAp> selectDTrainingList(DTrainingAp dTrainingAp) {
        return dTrainingUserMapper.selectDTrainingList(dTrainingAp);
    }
}
