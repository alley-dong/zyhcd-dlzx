package com.dlzx.pfserverperson.service;

import java.util.List;
import com.dlzx.pfserverperson.domain.SysDictionary;

/**
 * 字典多级联动Service接口
 * 
 * @author yangluhe
 * @date 2020-11-30
 */
public interface ISysDictionaryService 
{
    /**
     * 查询字典多级联动
     * 
     * @param id 字典多级联动ID
     * @return 字典多级联动
     */
    public SysDictionary selectSysDictionaryById(String id);

    /**
     * 查询字典多级联动列表
     * 
     * @param sysDictionary 字典多级联动
     * @return 字典多级联动集合
     */
    public List<SysDictionary> selectSysDictionaryList(SysDictionary sysDictionary);

    public List<SysDictionary> selectSysDictionaryListCar();

    public List<SysDictionary> selectSysDictionaryListCarZhu();

    public List<SysDictionary> selectSysDictionaryListCarGua();
    /**
     * 新增字典多级联动
     * 
     * @param sysDictionary 字典多级联动
     * @return 结果
     */
    public int insertSysDictionary(SysDictionary sysDictionary);

    /**
     * 修改字典多级联动
     * 
     * @param sysDictionary 字典多级联动
     * @return 结果
     */
    public int updateSysDictionary(SysDictionary sysDictionary);

    /**
     * 批量删除字典多级联动
     * 
     * @param ids 需要删除的字典多级联动ID
     * @return 结果
     */
    public int deleteSysDictionaryByIds(String[] ids);

    /**
     * 删除字典多级联动信息
     * 
     * @param id 字典多级联动ID
     * @return 结果
     */
    public int deleteSysDictionaryById(String id);
}
