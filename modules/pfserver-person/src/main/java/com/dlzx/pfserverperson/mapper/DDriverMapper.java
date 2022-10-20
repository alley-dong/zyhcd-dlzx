package com.dlzx.pfserverperson.mapper;

import java.util.List;

import com.dlzx.pfserverperson.domain.DDriver;
import com.dlzx.pfserverperson.domain.DDriver;
import com.dlzx.pfserverperson.domain.model.OTMDriverModel;
import org.apache.ibatis.annotations.Param;

/**
 * 驾驶员-司机档案Mapper接口
 *
 * @author yangluhe
 * @date 2020-11-18
 */
public interface DDriverMapper {
    /**
     * 查询驾驶员-司机档案
     *
     * @param id 驾驶员-司机档案ID
     * @return 驾驶员-司机档案
     */
    public DDriver selectDDriverById(String id);

    /**
     * 链表查询是否存在
     * @param driverTypeId
     * @return
     */
    public List<DDriver> selectDDriverByDriverTypeId(String driverTypeId);

    /**
     * 查询驾驶员-司机档案列表
     *
     * @param dDriver 驾驶员-司机档案
     * @return 驾驶员-司机档案集合
     */
    public List<DDriver> selectDDriverList(DDriver dDriver);

    /**
     * 查询所有的id
     * @param
     * @return
     */
    public List<DDriver> selectDDriverOfIdsList();

    //
    public List<DDriver> selectDDriverListQi(DDriver dDriver);

    //    下拉选
    public List<DDriver> selectDDriverListName();

    //    通过的
    public List<DDriver> selectDDriverListTong(DDriver dDriver);

    //    进行中
    public List<DDriver> selectDDriverListZhong(DDriver dDriver);

    public List<DDriver> selectDDriverListS(List<String> dDriverId);

    /**
     * 新增驾驶员-司机档案
     *
     * @param dDriver 驾驶员-司机档案
     * @return 结果
     */
    public int insertDDriver(DDriver dDriver);

    /**
     * 修改驾驶员-司机档案
     *
     * @param dDriver 驾驶员-司机档案
     * @return 结果
     */
    public int updateDDriver(DDriver dDriver);

    /**
     * 删除驾驶员-司机档案
     *
     * @param id 驾驶员-司机档案ID
     * @return 结果
     */
    public int deleteDDriverById(String id);

    /**
     * 批量删除驾驶员-司机档案
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDDriverByIds(String[] ids);

    /**
     * 校验身份证号是否重复
     */
    public int selectDDriverByIdCard(String idCard);

    /**
     * 长途司机档案List
     */
    public List<DDriver> selectDDriverLongD();

    /**
     * 短途司机档案list
     * @return
     */
    public List<DDriver> selectDDriverShortD();

    /**
     * 查出所有司机
     * @return
     */
    public List<DDriver> selectDDriverAll();

    /**
     * 驾驶员档案列表
     */
    public List<DDriver> selectDDriverListFile(DDriver dDriver);

    /**
     * OTM-查出所有司机
     * */
    List<OTMDriverModel> selectOTMDriverAll();

    /**
     * @describe
     * @author DongCL
     * @date 2021-01-08 16:03
     * @param contact
     * @return DDriver
     */
    DDriver queryDriverByContact(String contact);

    /**
     * @describe App更新用户密码
     * @author DongCL
     * @date 2021-02-23 16:53
     * @param
     * @return
     */
    int updateDDriverPassword(@Param("id") String id, @Param("encryptPassword")String encryptPassword);

    /**
     * 消息推送通过createByf反查驾驶员id
     */
    DDriver selectDDriverId(String name);

    /**
     * 校验手机号是否重复
     */
    int selectDDriverByIdContact(String contact);

    List<DDriver> selectDDriverStaff();

}
