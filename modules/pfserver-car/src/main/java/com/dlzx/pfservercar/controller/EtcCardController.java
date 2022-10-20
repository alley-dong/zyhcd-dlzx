package com.dlzx.pfservercar.controller;

import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.EtcCardImport;
import com.dlzx.pfservercar.service.ICVehicleService;
import com.dlzx.system.api.model.LoginUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfservercar.domain.EtcCard;
import com.dlzx.pfservercar.service.IEtcCardService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * ETC管理-ETC卡Controller
 *
 * @author dlzx
 * @date 2020-12-01
 */
@RestController
@RequestMapping("/car/etcCard")
public class EtcCardController extends BaseController {
    @Autowired
    private IEtcCardService etcCardService;
    @Autowired
    private ICVehicleService cVehicleService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询ETC管理-ETC卡列表
     */
    @PreAuthorize(hasPermi = "car:etcCard:list")
    @PostMapping("/J01List")
    public TableDataInfo list(@RequestBody EtcCard etcCard) {
        startPage(etcCard.getPageNum(), etcCard.getPageSize());
        List<EtcCard> list = etcCardService.selectEtcCardList(etcCard);
        return getDataTable(list);
    }

    /**
     * 导出ETC管理-ETC卡列表
     */
    @PreAuthorize(hasPermi = "car:etcCard:export")
    @Log(title = "ETC管理-ETC卡", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EtcCard etcCard) throws IOException {
        List<EtcCard> list = etcCardService.selectEtcCardList(etcCard);
        ExcelUtil<EtcCard> util = new ExcelUtil<EtcCard>(EtcCard.class);
        util.exportExcel(response, list, "card");
    }

    /**
     * 导入列表
     */
    @ApiOperation("导入列表")
    @PreAuthorize(hasPermi = "car:etcCard:import")
    @Log(title = "ETC管理-ETC卡", businessType = BusinessType.IMPORT)
    @PostMapping("/J01ImportData")
    public AjaxResult importData(@Validated MultipartFile file) throws Exception {
        ExcelUtil<EtcCardImport> util = new ExcelUtil<EtcCardImport>(EtcCardImport.class);
        List<EtcCardImport> etcCardList = new ArrayList<>();
        try {
            etcCardList = util.importExcel(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return AjaxResult.error("无文件导入");
        }
        Map<String, String> message = etcCardService.importUser(etcCardList);
        String code = message.get("code");
        if ("0".equals(code)) {
            return AjaxResult.success(message.get("msg"));
        } else {
            return AjaxResult.error(message.get("msg"));
        }
    }

    /**
     * 获取ETC管理-ETC卡详细信息
     */
    @PreAuthorize(hasPermi = "car:etcCard:edit")
    @GetMapping(value = "/J01GetInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(etcCardService.selectEtcCardById(id));
    }

    /**
     * 新增ETC管理-ETC卡
     */
    @PreAuthorize(hasPermi = "car:etcCard:add")
    @Log(title = "ETC管理-ETC卡", businessType = BusinessType.INSERT)
    @PostMapping("/J02Add")
    public AjaxResult add(@RequestBody @Validated EtcCard etcCard) {
        String licensePlate = etcCard.getLicensePlate();
        String etcCardNumber = etcCard.getEtcCardNumber();
        List<EtcCard> listPaiHao = etcCardService.selectEtcCardListPaiHao(licensePlate);
        List<EtcCard> listKaHao = etcCardService.selectEtcCardListKaHao(etcCardNumber);
        if (listPaiHao.size() == 0) {
            if (listKaHao.size() == 0) {
                LoginUser userInfo = tokenService.getLoginUser();
                String Id = UUID.randomUUID().toString();
                etcCard.setId(Id);
                etcCard.setDeleteFlag(Long.valueOf(1));
                etcCard.setCreateBy(userInfo.getUsername());
                return toAjax(etcCardService.insertEtcCard(etcCard));
            } else {
                return AjaxResult.error("该卡号已存在");
            }
        } else {
            return AjaxResult.error("该牌号已存在ETC");
        }
    }

    /**
     * 修改ETC管理-ETC卡
     */
    @PreAuthorize(hasPermi = "car:etcCard:edit")
    @Log(title = "ETC管理-ETC卡", businessType = BusinessType.UPDATE)
    @PostMapping("/J03Edit")
    public AjaxResult edit(@RequestBody @Validated EtcCard etcCard) {
        String Id = etcCard.getId();
        String licensePlate = etcCard.getLicensePlate();
        String etcCardNumber = etcCard.getEtcCardNumber();
        List<EtcCard> listPaiHao = etcCardService.selectEtcCardListPaiHao(licensePlate);
        List<EtcCard> listKaHao = etcCardService.selectEtcCardListKaHao(etcCardNumber);
        if (listPaiHao.size() > 0) {
            List<EtcCard> listCar = listPaiHao.stream().filter(b -> Objects.equals(Id, b.getId())).collect(Collectors.toList());
            if (listCar.size() == 0) {
                return AjaxResult.error("该牌号已存在ETC");
            }
        }
        if (listKaHao.size() > 0) {
            List<EtcCard> listKa = listKaHao.stream().filter(b -> Objects.equals(Id, b.getId())).collect(Collectors.toList());
            if (listKa.size() == 0) {
                return AjaxResult.error("该卡号已存在");
            }
        }
        LoginUser userInfo = tokenService.getLoginUser();
        etcCard.setUpdateBy(userInfo.getUsername());
        return toAjax(etcCardService.updateEtcCard(etcCard));

    }

    /**
     * 删除ETC管理-ETC卡
     */
    @PreAuthorize(hasPermi = "car:etcCard:delete")
    @Log(title = "ETC管理-ETC卡", businessType = BusinessType.DELETE)
    @GetMapping("/J01Delete/{id}")
    public AjaxResult remove(@PathVariable String id) {
        LoginUser userInfo = tokenService.getLoginUser();
        EtcCard etcCard = etcCardService.selectEtcCardById(id);
        etcCard.setDeleteFlag(Long.valueOf(2));
        etcCard.setUpdateBy(userInfo.getUsername());
        return toAjax(etcCardService.updateEtcCard(etcCard));
    }


    /**
     * 测试job
     * ylh
     */
    @PostMapping("/TESTETC")
    public void TESTETC(){
        etcCardService.initEmpTravelStatus();
    }
}
