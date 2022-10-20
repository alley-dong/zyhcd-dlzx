package com.dlzx.pfserverperson.controller;

import java.util.List;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.security.service.TokenService;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.dlzx.pfserverperson.domain.DExamPaper;
import com.dlzx.pfserverperson.service.IDExamPaperService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 驾驶员-试卷Controller
 * 
 * @author dlzx
 * @date 2020-11-18
 */
@RestController
@RequestMapping("/person/dExamPaper")
public class DExamPaperController extends BaseController
{
    @Autowired
    private IDExamPaperService dExamPaperService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询驾驶员-试卷列表
     */
    @PreAuthorize(hasPermi = "person:dExamPaper:list")
    @PostMapping("/A18List")
    public TableDataInfo list(@RequestBody DExamPaper dExamPaper)
    {
        startPage(dExamPaper.getPageNum(), dExamPaper.getPageSize());
        List<DExamPaper> list = dExamPaperService.selectDExamPaperList(dExamPaper);
        return getDataTable(list);
    }

    /**
     * 导出驾驶员-试卷列表
     */
    @PreAuthorize(hasPermi = "person:dExamPaper:export")
    @Log(title = "驾驶员-试卷", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DExamPaper dExamPaper) throws IOException
    {
        List<DExamPaper> list = dExamPaperService.selectDExamPaperList(dExamPaper);
        ExcelUtil<DExamPaper> util = new ExcelUtil<DExamPaper>(DExamPaper.class);
        util.exportExcel(response, list, "paper");
    }

    /**
     * 获取驾驶员-试卷详细信息
     */
    @PreAuthorize(hasPermi = "person:dExamPaper:getInfo")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(dExamPaperService.selectDExamPaperById(id));
    }

    /**
     * 新增驾驶员-试卷
     */
    @PreAuthorize(hasPermi = "person:dExamPaper:add")
    @Log(title = "驾驶员-试卷", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DExamPaper dExamPaper)
    {
        return toAjax(dExamPaperService.insertDExamPaper(dExamPaper));
    }


    /**
     * 修改驾驶员-试卷
     */
    @PreAuthorize(hasPermi = "person:dExamPaper:edit")
    @Log(title = "驾驶员-试卷", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DExamPaper dExamPaper)
    {
        return toAjax(dExamPaperService.updateDExamPaper(dExamPaper));
    }

    /**
     * 删除驾驶员-试卷
     */
    @PreAuthorize(hasPermi = "person:dExamPaper:delete")
    @Log(title = "驾驶员-试卷", businessType = BusinessType.DELETE)
	@GetMapping("/A18Delete/{id}")
    public AjaxResult remove(@PathVariable ("id")String id)
    {
        LoginUser userInfo = tokenService.getLoginUser();
        DExamPaper dExamPaper=dExamPaperService.selectDExamPaperById(id);
        dExamPaper.setDeleteFlag(Long.valueOf(2));
        dExamPaper.setUpdateBy(userInfo.getUsername());
        return toAjax(dExamPaperService.updateDExamPaper(dExamPaper));
    }
}
