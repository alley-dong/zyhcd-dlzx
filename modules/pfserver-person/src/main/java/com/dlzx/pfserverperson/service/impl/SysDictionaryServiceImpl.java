package com.dlzx.pfserverperson.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.SysDictionaryMapper;
import com.dlzx.pfserverperson.domain.SysDictionary;
import com.dlzx.pfserverperson.service.ISysDictionaryService;

/**
 * 字典多级联动Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-11-30
 */
@Service
public class SysDictionaryServiceImpl implements ISysDictionaryService 
{
    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;

    /**
     * 查询字典多级联动
     * 
     * @param id 字典多级联动ID
     * @return 字典多级联动
     */
    @Override
    public SysDictionary selectSysDictionaryById(String id)
    {
        return sysDictionaryMapper.selectSysDictionaryById(id);
    }

    /**
     * 查询字典多级联动列表
     * 
     * @param sysDictionary 字典多级联动
     * @return 字典多级联动
     */
    @Override
    public List<SysDictionary> selectSysDictionaryList(SysDictionary sysDictionary)
    {
        return sysDictionaryMapper.selectSysDictionaryList(sysDictionary);
    }

    @Override
    public List<SysDictionary> selectSysDictionaryListCar()
    {
        return sysDictionaryMapper.selectSysDictionaryListCar();
    }


    @Override
    public List<SysDictionary> selectSysDictionaryListCarZhu()
    {
        return sysDictionaryMapper.selectSysDictionaryListCarZhu();
    }

    @Override
    public List<SysDictionary> selectSysDictionaryListCarGua()
    {
        return sysDictionaryMapper.selectSysDictionaryListCarGua();
    }

    /**
     * 新增字典多级联动
     * 
     * @param sysDictionary 字典多级联动
     * @return 结果
     */
    @Override
    public int insertSysDictionary(SysDictionary sysDictionary)
    {
        sysDictionary.setCreateTime(DateUtils.getNowDate());
        return sysDictionaryMapper.insertSysDictionary(sysDictionary);
    }

    /**
     * 修改字典多级联动
     * 
     * @param sysDictionary 字典多级联动
     * @return 结果
     */
    @Override
    public int updateSysDictionary(SysDictionary sysDictionary)
    {
        sysDictionary.setUpdateTime(DateUtils.getNowDate());
        return sysDictionaryMapper.updateSysDictionary(sysDictionary);
    }

    /**
     * 批量删除字典多级联动
     * 
     * @param ids 需要删除的字典多级联动ID
     * @return 结果
     */
    @Override
    public int deleteSysDictionaryByIds(String[] ids)
    {
        return sysDictionaryMapper.deleteSysDictionaryByIds(ids);
    }

    /**
     * 删除字典多级联动信息
     * 
     * @param id 字典多级联动ID
     * @return 结果
     */
    @Override
    public int deleteSysDictionaryById(String id)
    {
        return sysDictionaryMapper.deleteSysDictionaryById(id);
    }
}
