package com.dlzx.system.client;

import com.dlzx.common.core.domain.R;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.security.handler.FeignSupportConfig;
import com.dlzx.system.domain.DDriver;
import com.dlzx.system.domain.DRecruitPlan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.util.List;

@Component
@FeignClient(value = "pfserver-person",configuration = FeignSupportConfig.class) //注册中心服务的名字
//@FeignClient(value = "pfserver-person",url = "http://localhost:9500/",configuration = FeignSupportConfig.class) //注册中心服务的名字
public interface PersonClient {


    /**
     * App -- 管理员端 驾驶员档案列表
     * @param dDriver
     * @return
     */
    @RequestMapping(path ="/person/driver/A24List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public TableDataInfo A09List(@RequestBody DDriver dDriver);

    /**
     * App -- 管理员端驾驶员档案详情 教育
     */
    @RequestMapping(path ="/person/driver/A11DriverEducationList/{id}",method = RequestMethod.GET)
    public AjaxResult A11DriverEducationList(@RequestParam(value = "id",required = false) String id);

    /**
     * App -- 管理员端驾驶员档案详情 家庭
     */
    @RequestMapping(path ="/person/driver/A11DriverFamilyList/{id}",method = RequestMethod.GET)
    public AjaxResult A11DriverFamilyList(@RequestParam(value = "id",required = false) String id);

    /**
     * App -- 管理员端驾驶员档案详情 事故
     */
    @RequestMapping(path ="/person/driver/A11DriverAccidentList/{id}",method = RequestMethod.GET)
    public AjaxResult A11DriverAccidentList(@RequestParam(value = "id",required = false) String id);


    /**
     * App -- 管理员端驾驶员档案详情 驾龄
     */
    @RequestMapping(path ="/person/driver/A11DriverDriverList/{id}",method = RequestMethod.GET)
    public AjaxResult A11DriverDriverList(@RequestParam(value = "id",required = false) String id);

    /**
     * App -- 管理员端驾驶员档案详情
     */
    @RequestMapping(path ="/person/driver/A11Info/{id}",method = RequestMethod.GET)
    public AjaxResult A11Info(@RequestParam(value = "id",required = false) String id);

    /**
     * App -- 管理员端驾驶员档案详情
     */
    @RequestMapping(path ="/person/driver/DriverInfo/{id}",method = RequestMethod.GET)
    public DDriver DriverInfo(@RequestParam(value = "id",required = false) String id);

    /**
     * 消息推送反查驾驶员id
     */
    @RequestMapping(path ="/person/driver/DriverId/{name}",method = RequestMethod.GET)
    public DDriver DriverId(@RequestParam(value = "name",required = false) String name);

    @RequestMapping(path = "/person/driver/A10EditSave",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult A10EditSave(@RequestBody DDriver dDriver);

    /**
     * 获取招聘计划列表
     */
    @RequestMapping(path = "/person/dRecruitPlan/queryRecruitPlanByIds",method = RequestMethod.POST)
    public List<DRecruitPlan> queryRecruitPlanByIds(@RequestParam(value = "relationIdList",required = false) List<String> relationIdList);
}