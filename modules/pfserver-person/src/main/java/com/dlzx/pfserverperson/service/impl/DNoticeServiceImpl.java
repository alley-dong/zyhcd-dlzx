package com.dlzx.pfserverperson.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DNoticeMapper;
import com.dlzx.pfserverperson.domain.DNotice;
import com.dlzx.pfserverperson.service.IDNoticeService;

/**
 * 驾驶员-公告Service业务层处理
 * 
 * @author dlzx
 * @date 2020-11-24
 */
@Service
public class DNoticeServiceImpl implements IDNoticeService 
{
    @Autowired
    private DNoticeMapper dNoticeMapper;

    /**
     * 查询驾驶员-公告
     * 
     * @param id 驾驶员-公告ID
     * @return 驾驶员-公告
     */
    @Override
    public DNotice selectDNoticeById(String id)
    {
        return dNoticeMapper.selectDNoticeById(id);
    }

    /**
     * 查询驾驶员-公告列表
     * 
     * @param dNotice 驾驶员-公告
     * @return 驾驶员-公告
     */
    @Override
    public List<DNotice> selectDNoticeList(DNotice dNotice)
    {
        return dNoticeMapper.selectDNoticeList(dNotice);
    }

    /**
     * 新增驾驶员-公告
     * 
     * @param dNotice 驾驶员-公告
     * @return 结果
     */
    @Override
    public int insertDNotice(DNotice dNotice)
    {
        dNotice.setCreateTime(DateUtils.getNowDate());
        return dNoticeMapper.insertDNotice(dNotice);
    }

    /**
     * 新增驾驶员-公告
     *
     * @param dNotice 驾驶员-公告
     * @return 结果
     */
    @Override
    public int insertDNoticeBC(DNotice dNotice)
    {
        dNotice.setCreateTime(DateUtils.getNowDate());
        return dNoticeMapper.insertDNoticeBC(dNotice);
    }

    /**
     * 修改驾驶员-公告
     * 
     * @param dNotice 驾驶员-公告
     * @return 结果
     */
    @Override
    public int updateDNotice(DNotice dNotice)
    {
        return dNoticeMapper.updateDNotice(dNotice);
    }

    /**
     * 批量删除驾驶员-公告
     * 
     * @param ids 需要删除的驾驶员-公告ID
     * @return 结果
     */
    @Override
    public int deleteDNoticeByIds(String[] ids)
    {
        return dNoticeMapper.deleteDNoticeByIds(ids);
    }

    /**
     * 删除驾驶员-公告信息
     * 
     * @param id 驾驶员-公告ID
     * @return 结果
     */
    @Override
    public int deleteDNoticeById(String id)
    {
        return dNoticeMapper.deleteDNoticeById(id);
    }
}
