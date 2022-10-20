package com.dlzx.pfservercar.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.service.ICVehicleService;
import com.dlzx.system.api.model.LoginUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfservercar.domain.FOilCard;
import com.dlzx.pfservercar.service.IFOilCardService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 油卡管理Controller
 * 
 * @author dlzx
 * @date 2020-12-01
 */
@RestController
@RequestMapping("/car/oilCard")
public class FOilCardController extends BaseController
{
    @Autowired
    private IFOilCardService fOilCardService;

    @Autowired
    private ICVehicleService cVehicleService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询油卡管理列表
     */
    @PreAuthorize(hasPermi = "pfservercar:card:list")
    @PostMapping("/I01OilCardList")
    public TableDataInfo list(@RequestBody FOilCard fOilCard)
    {
        startPage(fOilCard.getPageNum(),fOilCard.getPageSize());
        List<FOilCard> list = fOilCardService.selectFOilCardList(fOilCard);
        Date date = new Date();
        for (FOilCard oilCard : list) {
            if (oilCard.getDueTime().getTime()>date.getTime()){
                // 到期时间和 当前时间 的时间差（天）
                int mathDays = fOilCardService.calcDayOffset(date, oilCard.getDueTime());
                if (mathDays<=3){
                    // 标红
                    oilCard.setMathDays(1);
                }
            }else {
                oilCard.setMathDays(1);
            }
        }
        return getDataTable(list);
    }

    /**
     * 油卡管理导入
     */
    @ApiOperation("油卡管理导入")
    @PreAuthorize(hasPermi = "pfservercar:card:import")
    @Log(title = "油卡管理导入", businessType = BusinessType.IMPORT)
    @PostMapping("/I01OilCardImport")
    public AjaxResult I01OilCardImport(@Validated MultipartFile file){
        //excel导入判断数据格式和表格式
        ExcelUtil<FOilCard> util = new ExcelUtil<FOilCard>(FOilCard.class);
        List<FOilCard> fOilCards = new ArrayList<>();
        try {
            fOilCards = util.importExcel(file.getInputStream());
        } catch (Exception e){
            e.printStackTrace();
            e.getMessage();
            return AjaxResult.error("数据格式有误");
        }

        //excel导入数据判断数据内容是否符合要求
        Map<String,Integer> message = fOilCardService.importVechicle(fOilCards);
        if(message.get("code")>0){
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    /**
     * 获取油卡管理详细信息
     */
    @PreAuthorize(hasPermi = "pfservercar:card:info")
    @GetMapping(value = "/I01OilCardInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(fOilCardService.selectFOilCardById(id));
    }

    /**
     * 新增油卡管理
     */
    @PreAuthorize(hasPermi = "pfservercar:card:add")
    @Log(title = "油卡管理", businessType = BusinessType.INSERT)
    @PostMapping("/I01OilCardAdd")
    public AjaxResult add(@RequestBody FOilCard fOilCard)
    {
        FOilCard foilCardByPlate = new FOilCard();
        foilCardByPlate.setLicensePlate(fOilCard.getLicensePlate());
        List<FOilCard> list = fOilCardService.queryFOilCardList(foilCardByPlate);
        if (list.size()>0){
            return AjaxResult.error("车牌已绑定油卡，不能重复绑定");
        }

        FOilCard foilCardByCard = new FOilCard();
        foilCardByCard.setOilCardNumber(fOilCard.getOilCardNumber());
        List<FOilCard> fOilCards = fOilCardService.queryFOilCardList(foilCardByCard);
        if (fOilCards.size()>0){
            return AjaxResult.error("油卡已被绑定，不能重复绑定");
        }

        CVehicle cVehicle = cVehicleService.queryCVehicleByLicensePlate(fOilCard.getLicensePlate());
        if (cVehicle==null){
            return AjaxResult.error("车牌号不存在");
        }
        if(StringUtils.isEmpty(cVehicle.getId())){
            return AjaxResult.error("车辆信息异常");
        }
        if(StringUtils.isNull(fOilCard.getOils())){
            return AjaxResult.error("油品不能为空");
        }
        if(StringUtils.isNull(fOilCard.getCardTime())){
            return AjaxResult.error("办卡日期不能为空");
        }
        if(StringUtils.isNull(fOilCard.getDueTime())){
            return AjaxResult.error("到期日期不能为空");
        }
        //生成uuid
        UUID uuid = UUID.randomUUID();
        fOilCard.setId(uuid+"");
        fOilCard.setVehicleId(cVehicle.getId());

        LoginUser userInfo = tokenService.getLoginUser();
        fOilCard.setCreateBy(userInfo.getUsername());
        return toAjax(fOilCardService.insertFOilCard(fOilCard));
    }

    /**
     * 修改油卡管理
     */
    @PreAuthorize(hasPermi = "pfservercar:card:edit")
    @Log(title = "油卡管理", businessType = BusinessType.UPDATE)
    @PostMapping("/I01OilCardEditSave")
    public AjaxResult edit(@RequestBody FOilCard fOilCard)
    {
        FOilCard foilCardByPlate = new FOilCard();
        foilCardByPlate.setLicensePlate(fOilCard.getLicensePlate());
        List<FOilCard> list = fOilCardService.queryFOilCardList(foilCardByPlate);
        List<FOilCard> collect = list.stream().filter(l -> !l.getId().equals(fOilCard.getId())).collect(Collectors.toList());
        if (collect.size()>0){
            return AjaxResult.error("车牌已绑定油卡，不能重复绑定");
        }
        FOilCard foilCardByCard = new FOilCard();
        foilCardByCard.setOilCardNumber(fOilCard.getOilCardNumber());
        List<FOilCard> fOilCards = fOilCardService.queryFOilCardList(foilCardByCard);
        List<FOilCard> collect1 = fOilCards.stream().filter(l -> !l.getId().equals(fOilCard.getId())).collect(Collectors.toList());
        if (collect1.size()>0){
            return AjaxResult.error("油卡已被绑定，不能重复绑定");
        }

        CVehicle cVehicle = cVehicleService.queryCVehicleByLicensePlate(fOilCard.getLicensePlate());
        if (cVehicle==null){
            return AjaxResult.error("车牌号不存在");
        }
        if(StringUtils.isEmpty(cVehicle.getId())){
            return AjaxResult.error("车辆信息异常");
        }
        if(StringUtils.isNull(fOilCard.getOils())){
            return AjaxResult.error("油品不能为空");
        }
        if(StringUtils.isNull(fOilCard.getCardTime())){
            return AjaxResult.error("办卡日期不能为空");
        }
        if(StringUtils.isNull(fOilCard.getDueTime())){
            return AjaxResult.error("到期日期不能为空");
        }
        fOilCard.setVehicleId(cVehicle.getId());

        LoginUser userInfo = tokenService.getLoginUser();
        fOilCard.setUpdateBy(userInfo.getUsername());
        return toAjax(fOilCardService.updateFOilCard(fOilCard));
    }

    /**
     * 删除油卡管理
     */
    @PreAuthorize(hasPermi = "pfservercar:card:delete")
    @Log(title = "油卡管理", businessType = BusinessType.DELETE)
	@GetMapping("/I01OilCardDelete/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(fOilCardService.deleteFOilCardByIds(ids));
    }

    /**
     * 导出油卡管理列表
     */
    @PreAuthorize(hasPermi = "pfservercar:card:export")
    @Log(title = "油卡管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FOilCard fOilCard) throws IOException
    {
        List<FOilCard> list = fOilCardService.selectFOilCardList(fOilCard);
        ExcelUtil<FOilCard> util = new ExcelUtil<FOilCard>(FOilCard.class);
        util.exportExcel(response, list, "card");
    }

}
