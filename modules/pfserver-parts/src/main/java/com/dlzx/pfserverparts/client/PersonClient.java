package com.dlzx.pfserverparts.client;

import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.security.handler.FeignSupportConfig;
import com.dlzx.pfserverparts.domain.DDriver;
import com.dlzx.pfserverparts.domain.DDriverType;
import com.dlzx.pfserverparts.domain.DPerformanceOvertimeRecord;
import com.dlzx.pfserverparts.domain.DPerformanceRecord;
import com.dlzx.pfserverparts.domain.DPerformanceShortHaulRecord;
import com.dlzx.pfserverparts.domain.DVacationDay;
import com.dlzx.pfserverparts.domain.ReviewSubmit;
import com.dlzx.pfserverparts.domain.SysExamineConfigProcess;
import com.dlzx.pfserverparts.domain.SysExamineTask;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Component
//@FeignClient(value = "pfserver-person",url = "http://localhost:9500/",configuration = FeignSupportConfig.class) //注册中心服务的名字
@FeignClient(value = "pfserver-person",configuration = FeignSupportConfig.class) //注册中心服务的名字
public interface PersonClient {

    /**
     * 薪酬管理--长途司机list
     * @return
     */
    @RequestMapping(path ="/person/driver/longDdriverList",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public List<DDriver> longDdriverList();

    /**
     * 查出所有司机
     * @return
     */
    @RequestMapping(path ="/person/driver/allDrivers",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public List<DDriver> allDrivers();

    /**
     * 查出所有外包司机
     * @return
     */
    @RequestMapping(path ="/person/driver/driversList",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public List<DDriver> driversList();

    /**
     * 薪酬管理--短途司机list
     * @return
     */
    @RequestMapping(path ="/person/driver/shortDdriverList",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public List<DDriver> shortDdriverList();

    /**
     * 驾驶员的每月底薪
     */
    @RequestMapping(path ="/person/vacationDay/daySalary",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public List<DVacationDay> daySalary(@RequestBody DVacationDay dVacationDay);

    /**
     * 统计所有长途司机的绩效工资
     * @param dPerformanceRecord
     * @return
     */
    @RequestMapping(path ="/person/performanceRecord/longPerMoney",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public List<DPerformanceRecord> longPerMoney(@RequestBody DPerformanceRecord dPerformanceRecord);

    /**
     * 统计所有短途司机的绩效工资
     * @param dPerformanceShortHaulRecord
     * @return
     */
    @RequestMapping(path ="/person/performanceShortHaulRecord/shortPerMoney",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public List<DPerformanceShortHaulRecord> shortPerMoney(@RequestBody DPerformanceShortHaulRecord dPerformanceShortHaulRecord);

    @RequestMapping(path ="/person/performanceOvertimeRecord/overMoney",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public List<DPerformanceOvertimeRecord> overMoney(@RequestBody DPerformanceOvertimeRecord dPerformanceOvertimeRecord);

    @RequestMapping(path ="/person/driverType/driverTypeGetInfo",method = RequestMethod.GET)
    DDriverType queryDriverTypeById(@RequestParam("id") String id);
}