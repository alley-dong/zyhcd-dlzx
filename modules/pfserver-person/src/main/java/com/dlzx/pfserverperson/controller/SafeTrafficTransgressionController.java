package com.dlzx.pfserverperson.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverperson.domain.SafeTrafficTransgressionAp;
import com.dlzx.pfserverperson.domain.SysFile;
import com.dlzx.pfserverperson.domain.TrafficUrl;
import com.dlzx.pfserverperson.mapper.SysFileMapper;
import com.dlzx.pfserverperson.service.ISafeTrafficTransgressionApService;
import com.dlzx.pfserverperson.service.ISysFileService;
import com.dlzx.system.api.model.LoginDriver;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfserverperson.domain.SafeTrafficTransgression;
import com.dlzx.pfserverperson.service.ISafeTrafficTransgressionService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 安全管理-违章记录Controller
 *
 * @author dlzx
 * @date 2020-12-10
 */
@RestController
@RequestMapping("/person/safeTrafficTransgression")
public class SafeTrafficTransgressionController extends BaseController {
    @Autowired
    private ISafeTrafficTransgressionService safeTrafficTransgressionService;
    @Autowired
    private ISafeTrafficTransgressionApService safeTrafficTransgressionApService;
    @Autowired
    private SysFileMapper sysFileMapper;
    @Autowired
    private DriverTokenService driverTokenService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询安全管理-违章记录列表
     */
    @PreAuthorize(hasPermi = "person:safeTrafficTransgression:list")
    @PostMapping("/L05List")
    public TableDataInfo list(@RequestBody SafeTrafficTransgression safeTrafficTransgression) {
        startPage(safeTrafficTransgression.getPageNum(), safeTrafficTransgression.getPageSize());
        List<SafeTrafficTransgression> list = safeTrafficTransgressionService.selectSafeTrafficTransgressionList(safeTrafficTransgression);
        return getDataTable(list);
    }

    // 查询驾驶员档案-违章记录表
    @PreAuthorize(hasPermi = "pfserverperson:driver:info")
    @PostMapping("/A32List")
    public TableDataInfo listT(@RequestBody SafeTrafficTransgression safeTrafficTransgression) {

        List<SafeTrafficTransgression> list = safeTrafficTransgressionService.selectSafeTrafficTransgressionListPerson(safeTrafficTransgression);
        return getDataTable(list);
    }

    /**
     * 导出安全管理-违章记录列表
     */
    @PreAuthorize(hasPermi = "system:transgression:export")
    @Log(title = "安全管理-违章记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SafeTrafficTransgression safeTrafficTransgression) throws IOException {
        List<SafeTrafficTransgression> list = safeTrafficTransgressionService.selectSafeTrafficTransgressionList(safeTrafficTransgression);
        ExcelUtil<SafeTrafficTransgression> util = new ExcelUtil<SafeTrafficTransgression>(SafeTrafficTransgression.class);
        util.exportExcel(response, list, "transgression");
    }

    /**
     * 获取安全管理-违章记录详细信息
     */
    @PreAuthorize(hasPermi = "person:safeTrafficTransgression:getInfo")
    @GetMapping(value = "/L05GetInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        SafeTrafficTransgression safeTrafficTransgression = safeTrafficTransgressionService.selectSafeTrafficTransgressionById(id);
        return AjaxResult.success(safeTrafficTransgression);
    }

    /**
     * 新增安全管理-违章记录
     */
    @PreAuthorize(hasPermi = "person:safeTrafficTransgression:add")
    @Log(title = "安全管理-违章记录", businessType = BusinessType.INSERT)
    @PostMapping("/L06Add")
    public AjaxResult add(@RequestBody SafeTrafficTransgression safeTrafficTransgression) {
        LoginUser userInfo = tokenService.getLoginUser();
        String Id = UUID.randomUUID().toString();
        safeTrafficTransgression.setId(Id);
        safeTrafficTransgression.setCreateBy(userInfo.getUsername());
        safeTrafficTransgression.setStatus(Long.valueOf(1));
        return toAjax(safeTrafficTransgressionService.insertSafeTrafficTransgressionUrl(safeTrafficTransgression,Id,userInfo));

    }

    /**
     * 修改安全管理-违章记录
     */
    @PreAuthorize(hasPermi = "person:safeTrafficTransgression:edit")
    @Log(title = "安全管理-违章记录", businessType = BusinessType.UPDATE)
    @PostMapping("/L09Edit")
    public AjaxResult edit(@RequestBody SafeTrafficTransgression safeTrafficTransgression) {
        LoginUser userInfo = tokenService.getLoginUser();
        safeTrafficTransgression.setUpdateBy(userInfo.getUsername());
        return toAjax(safeTrafficTransgressionService.updateSafeTrafficTransgression(safeTrafficTransgression));
    }



    /**
     * 驳回安全管理-违章记录
     */
    @PreAuthorize(hasPermi = "person:safeTrafficTransgression:change")
    @Log(title = "安全管理-驳回处理", businessType = BusinessType.UPDATE)
    @GetMapping("/L10Change/{id}")
    public AjaxResult change(@PathVariable("id") String id) {
        SafeTrafficTransgression safeTrafficTransgression = safeTrafficTransgressionService.selectSafeTrafficTransgressionById(id);
        safeTrafficTransgression.setStatus(1L);
        return toAjax(safeTrafficTransgressionService.updateSafeTrafficTransgressionStatus(safeTrafficTransgression));
    }

    /**
     * 删除安全管理-违章记录
     */
    @PreAuthorize(hasPermi = "system:transgression:remove")
    @Log(title = "安全管理-违章记录删除", businessType = BusinessType.DELETE)
    @GetMapping("/L05Delete/{id}")
    public AjaxResult L05Delete(@PathVariable("id")String id) {
        SafeTrafficTransgression safeTrafficTransgression = safeTrafficTransgressionService.selectSafeTrafficTransgressionById(id);
        safeTrafficTransgression.setDeleteFlag(2L);
        return toAjax(safeTrafficTransgressionService.updateSafeTrafficTransgression(safeTrafficTransgression));
    }


    @RequestMapping(path = "/K01List", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Log(title = "驾驶员交通违章列表")
    public TableDataInfo K01List(@RequestBody SafeTrafficTransgressionAp safeTrafficTransgression) {
        startPage(safeTrafficTransgression.getPageNum(), safeTrafficTransgression.getPageSize());
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        safeTrafficTransgression.setDriverId(loginDriver.getUserid());
        List<SafeTrafficTransgressionAp> list = safeTrafficTransgressionApService.selectSafeTrafficTransgressionListDriver(safeTrafficTransgression);
        return getDataTable(list);
    }

    @RequestMapping("/K02GetInfo")
    @ResponseBody
    @Log(title = "驾驶员违章详情")
    public AjaxResult K02GetInfo(@RequestParam("id") String id) {
        SafeTrafficTransgressionAp safeTrafficTransgression = safeTrafficTransgressionApService.selectSafeTrafficTransgressionById(id);
        List<SysFile> list = sysFileMapper.queryFilesByRelationId(id);
        if (list.size() > 0) {
            // 违章图片
            safeTrafficTransgression.setFileList(list.stream().filter(l->l.getType()==7).collect(Collectors.toList()));
            // 处理凭证
            safeTrafficTransgression.setVoucherFileList(list.stream().filter(l->l.getType()==8).collect(Collectors.toList()));
        }
        return AjaxResult.success(safeTrafficTransgression);
    }

    @RequestMapping(path = "/K02Shang", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Log(title = "驾驶员违章处理完成")
    public AjaxResult K02Shang(@RequestBody TrafficUrl trafficUrl) {
        String[] url = trafficUrl.getUrl();
        if (url.length == 0) {
            return AjaxResult.error("请先上传凭证");
        }
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        List<SysFile> list=new ArrayList<>();
        for (int i=0;i<url.length;i++) {
            SysFile sysFile = new SysFile();
            sysFile.setFilePath(url[i]);
            sysFile.setType(Long.valueOf(8));
            sysFile.setRelationId(trafficUrl.getId());
            String Id = UUID.randomUUID().toString();
            sysFile.setId(Id);
            sysFile.setDeleteFlag(Long.valueOf(1));
            sysFile.setCreateTime(DateUtils.getNowDate());
            sysFile.setCreateUser(loginDriver.getUsername());
            list.add(sysFile);
        }
        SafeTrafficTransgressionAp safeTrafficTransgression = safeTrafficTransgressionApService.selectSafeTrafficTransgressionById(trafficUrl.getId());
        safeTrafficTransgression.setStatus(Long.valueOf(2));
        return toAjax(safeTrafficTransgressionApService.updateSafeTrafficTransgressionUrl(safeTrafficTransgression,list));
    }
}
