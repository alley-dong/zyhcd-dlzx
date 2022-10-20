package com.dlzx.pfservercar.service.impl.Commonlmpl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.pfservercar.domain.Common.SysDictionary;
import com.dlzx.pfservercar.mapper.CommonMapper.SysDictionaryCarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.service.CommonService.ISysDictionaryService;

/**
 * 字典多级联动Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-11-24
 */
@Service
public class SysDictionaryServiceImpl implements ISysDictionaryService 
{
    @Autowired
    private SysDictionaryCarMapper sysDictionaryMapper;

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

    /**
     * 查询一级列表
     */
    @Override
    public List<SysDictionary> selectSysDictionaryDriverType(String id){
        return sysDictionaryMapper.selectSysDictionaryDriverType(id);
    }

    @Override
    public List<SysDictionary> selectAll(){
        return sysDictionaryMapper.selectAll();
    }

    @Override
    public List<SysDictionary> selectRepairAll(){
        return sysDictionaryMapper.selectRepairAll();
    }
}
