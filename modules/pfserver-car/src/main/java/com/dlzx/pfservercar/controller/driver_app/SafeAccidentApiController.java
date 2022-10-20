package com.dlzx.pfservercar.controller.driver_app;

import java.util.List;
import java.util.stream.Collectors;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.text.UUID;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.service.ICVehicleService;
import com.dlzx.pfservercar.service.impl.CVehicleServiceImpl;
import com.dlzx.system.api.model.LoginDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.pfservercar.domain.SafeAccident;
import com.dlzx.pfservercar.service.ISafeAccidentService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;

import javax.validation.constraints.NotBlank;

/**
 * 安全管理-事故Controller
 *
 * @author dlzx
 * @date 2020-12-08
 */
@RestController
@RequestMapping("/driver-api/accident")
public class SafeAccidentApiController extends BaseController
{
    @Autowired
    private ISafeAccidentService safeAccidentService;

    @Autowired
    private DriverTokenService driverTokenService;

    @Autowired
    private ICVehicleService icVehicleService;

    /**
     * App 交通事故-事故记录列表
     */
    @RequestMapping("/J01SafeAccidentList")
    @ResponseBody
    @Log(title = "App 交通事故-事故记录列表")
    public TableDataInfo safeAccidentList(@RequestBody SafeAccident safeAccident)
    {
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        if (loginDriver==null){
            throw new CustomException("用户信息为空");
        }
        startPage(safeAccident.getPageNum(),safeAccident.getPageSize());
        // 司机id
        safeAccident.setDriverId(loginDriver.getUserid());
        List<SafeAccident>safeAccidents = safeAccidentService.querySafeAccidentList(safeAccident);
        return getDataTable(safeAccidents);
    }

    /**
     * App 交通事故-事故详情接口
     */
    @RequestMapping("/J02SafeAccidentInfo")
    @ResponseBody
    @Log(title = "App 交通事故-事故详情接口")
    public AjaxResult safeAccidentInfo(@RequestBody SafeAccident safeAccident)
    {
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        if (loginDriver==null){
            return AjaxResult.error("用户信息为空");
        }
        SafeAccident safeAccidents = safeAccidentService.selectSafeAccidentById(safeAccident.getId());
        return AjaxResult.success(safeAccidents);
    }

    /**
     * App 交通事故-车牌下拉选
     */
    @RequestMapping("/J02CarVehicleNameList")
    @ResponseBody
    @Log(title = "App 交通事故-车牌下拉选")
    public AjaxResult carVehicleNameList()
    {
        List<CVehicle>list = safeAccidentService.carVehicleNameList();
        List<@NotBlank(message = "车牌号不能为空") String> carList = list.stream().map(CVehicle::getLicensePlate).collect(Collectors.toList());
        return AjaxResult.success(carList);
    }

    /**
     * App 交通事故-事故上报
     */
    @RequestMapping("/J02SafeAccidentReport")
    @ResponseBody
    @Log(title = "App 交通事故-事故上报")
    public AjaxResult safeAccidentReport(@RequestBody SafeAccident safeAccident)
    {
        if (StringUtils.isNull(safeAccident.getAccidentTime())){
            return AjaxResult.error("事故发生时间不能为空");
        }

        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        if (loginDriver==null){
            return AjaxResult.error("用户信息为空");
        }
        // 驾驶员姓名
        safeAccident.setDriverName(loginDriver.getUsername());
        safeAccident.setDriverId(loginDriver.getUserid());

        if (StringUtils.isNull(safeAccident.getVehicleName())){
            return AjaxResult.error("车牌号不能为空");
        }
        // 车辆信息
        CVehicle cVehicle = icVehicleService.queryCVehicleByLicensePlate(safeAccident.getVehicleName());
        if (cVehicle == null){
            return AjaxResult.error("车牌不存在");
        }
        // 车辆ID
        safeAccident.setVehicleId(cVehicle.getId());

        if (StringUtils.isNull(safeAccident.getAccidentPhotos())){
            return AjaxResult.error("事故照片不能为空");
        }
        if (StringUtils.isNull(safeAccident.getAccidentTime())){
            return AjaxResult.error("事故发生时间不能为空");
        }
        if (StringUtils.isNull(safeAccident.getAccidentPlace())){
            return AjaxResult.error("事故发生地点不能为空");
        }
        if (StringUtils.isNull(safeAccident.getOneSideDriveLicenceCardJust()) &&
                StringUtils.isNull(safeAccident.getOneSideDriveLicenceCardAgainst()) &&
                StringUtils.isNull(safeAccident.getTheOtherDriveLicenceCardJust()) &&
                StringUtils.isNull(safeAccident.getTheOtherDriveLicenceCardAgainst())
        ) {
            return AjaxResult.error("双方驾驶证不能为空");
        }
        if (StringUtils.isNull(safeAccident.getOneSideTravelLicenceCardJust()) &&
                StringUtils.isNull(safeAccident.getOneSideTravelLicenceCardAgainst()) &&
                StringUtils.isNull(safeAccident.getTheOtherTravelLicenceCardJust()) &&
                StringUtils.isNull(safeAccident.getTheOtherTravelLicenceCardAgainst())
        ) {
            return AjaxResult.error("双方行驶证不能为空");
        }
        if (StringUtils.isNull(safeAccident.getCarTheInsuredIdCardJust()) &&
                StringUtils.isNull(safeAccident.getCarTheInsuredIdCardAgainst())
        ) {
            return AjaxResult.error("车辆被保险人身份证不能为空");
        }

        if (StringUtils.isNull(safeAccident.getDriverIdCardJust()) &&
                StringUtils.isNull(safeAccident.getDriverIdCardAgainst())
        ) {
            return AjaxResult.error("驾驶员身份证不能为空");
        }
        if (StringUtils.isNull(safeAccident.getLiabilityStatement())) {
            return AjaxResult.error("事故责任认定书");
        }

        safeAccident.setId(UUID.randomUUID()+"");
        // 待审核
        safeAccident.setStatus(1L);
        int result = safeAccidentService.insertSafeAccident(safeAccident);
        return AjaxResult.success(result);
    }


    /**
     * App 交通事故-新增事故上报
     */
    @RequestMapping("/J02SafeAccidentAgainReport")
    @ResponseBody
    @Log(title = "App 交通事故-事故再次上报")
    public AjaxResult safeAccidenAgaintReport(@RequestBody SafeAccident safeAccident)
    {
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        if (loginDriver==null){
            return AjaxResult.error("用户信息为空");
        }
        // 驾驶员姓名
        safeAccident.setDriverName(loginDriver.getUsername());
        safeAccident.setDriverId(loginDriver.getUserid());

        if (StringUtils.isNull(safeAccident.getVehicleName())){
            return AjaxResult.error("车牌号不能为空");
        }
        // 车辆信息
        CVehicle cVehicle = icVehicleService.queryCVehicleByLicensePlate(safeAccident.getVehicleName());
        if (cVehicle == null){
            return AjaxResult.error("车牌不存在");
        }
        // 车辆ID
        safeAccident.setVehicleId(cVehicle.getId());

        if (StringUtils.isNull(safeAccident.getAccidentPhotos())){
            return AjaxResult.error("事故照片不能为空");
        }
        if (StringUtils.isNull(safeAccident.getAccidentTime())){
            return AjaxResult.error("事故发生时间不能为空");
        }
        if (StringUtils.isNull(safeAccident.getAccidentPlace())){
            return AjaxResult.error("事故发生地点不能为空");
        }
        if (StringUtils.isNull(safeAccident.getOneSideDriveLicenceCardJust()) &&
                StringUtils.isNull(safeAccident.getOneSideDriveLicenceCardAgainst()) &&
                StringUtils.isNull(safeAccident.getTheOtherDriveLicenceCardJust()) &&
                StringUtils.isNull(safeAccident.getTheOtherDriveLicenceCardAgainst())
        ) {
            return AjaxResult.error("双方驾驶证不能为空");
        }
        if (StringUtils.isNull(safeAccident.getOneSideTravelLicenceCardJust()) &&
                StringUtils.isNull(safeAccident.getOneSideTravelLicenceCardAgainst()) &&
                StringUtils.isNull(safeAccident.getTheOtherTravelLicenceCardJust()) &&
                StringUtils.isNull(safeAccident.getTheOtherTravelLicenceCardAgainst())
        ) {
            return AjaxResult.error("双方行驶证不能为空");
        }
        if (StringUtils.isNull(safeAccident.getCarTheInsuredIdCardJust()) &&
                StringUtils.isNull(safeAccident.getCarTheInsuredIdCardAgainst())
        ) {
            return AjaxResult.error("车辆被保险人身份证不能为空");
        }

        if (StringUtils.isNull(safeAccident.getDriverIdCardJust()) &&
                StringUtils.isNull(safeAccident.getDriverIdCardAgainst())
        ) {
            return AjaxResult.error("驾驶员身份证不能为空");
        }
        if (StringUtils.isNull(safeAccident.getLiabilityStatement())) {
            return AjaxResult.error("事故责任认定书");
        }

        // 待审核
        safeAccident.setStatus(1L);
        int result = safeAccidentService.updateSafeAccident(safeAccident);
        return AjaxResult.success(result);
    }
}
