package com.dlzx.pfserverperson.service;

import java.util.List;
import com.dlzx.pfserverperson.domain.DNotice;

/**
 * 驾驶员-公告Service接口
 * 
 * @author dlzx
 * @date 2020-11-24
 */
public interface IDNoticeService 
{
    /**
     * 查询驾驶员-公告
     * 
     * @param id 驾驶员-公告ID
     * @return 驾驶员-公告
     */
    public DNotice selectDNoticeById(String id);

    /**
     * 查询驾驶员-公告列表
     * 
     * @param dNotice 驾驶员-公告
     * @return 驾驶员-公告集合
     */
    public List<DNotice> selectDNoticeList(DNotice dNotice);

    /**
     * 新增驾驶员-公告
     * 
     * @param dNotice 驾驶员-公告
     * @return 结果
     */
    public int insertDNotice(DNotice dNotice);

    /**
     * 新增驾驶员-公告
     *
     * @param dNotice 驾驶员-公告
     * @return 结果
     */
    public int insertDNoticeBC(DNotice dNotice);
    /**
     * 修改驾驶员-公告
     * 
     * @param dNotice 驾驶员-公告
     * @return 结果
     */
    public int updateDNotice(DNotice dNotice);

    /**
     * 批量删除驾驶员-公告
     * 
     * @param ids 需要删除的驾驶员-公告ID
     * @return 结果
     */
    public int deleteDNoticeByIds(String[] ids);

    /**
     * 删除驾驶员-公告信息
     * 
     * @param id 驾驶员-公告ID
     * @return 结果
     */
    public int deleteDNoticeById(String id);
}
