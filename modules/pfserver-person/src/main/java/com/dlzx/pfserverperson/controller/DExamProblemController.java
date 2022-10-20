package com.dlzx.pfserverperson.controller;

import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.poi.ExcelUtilh;
import com.dlzx.common.security.service.TokenService;
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
import com.dlzx.pfserverperson.domain.DExamProblem;
import com.dlzx.pfserverperson.service.IDExamProblemService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 驾驶员-题库Controller
 *
 * @author dlzx
 * @date 2020-11-18
 */
@RestController
@RequestMapping("/person/dExamProblem")
public class DExamProblemController extends BaseController {
    @Autowired
    private IDExamProblemService dExamProblemService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询驾驶员-题库列表
     */
    @PreAuthorize(hasPermi = "person:dExamProblem:list")
    @PostMapping("/A22List")
    public TableDataInfo list(@RequestBody DExamProblem dExamProblem) {
        startPage(dExamProblem.getPageNum(), dExamProblem.getPageSize());
        List<DExamProblem> list = dExamProblemService.selectDExamProblemList(dExamProblem);
        return getDataTable(list);
    }

    @PreAuthorize(hasPermi = "person:dExamProblem:listS")
    @PostMapping("/A21ListS")
    public TableDataInfo listS(@RequestBody DExamProblem dExamProblem) {
        List<DExamProblem> list = dExamProblemService.selectDExamProblemList(dExamProblem);
        return getDataTable(list);
    }

    @PreAuthorize(hasPermi = "system:problem:list1")
    @GetMapping("/List1")
    public TableDataInfo list1() {
        startPage();
        List<DExamProblem> list = dExamProblemService.selectDExamProblemList1();
        return getDataTable(list);
    }

    /**
     * 导出驾驶员-题库列表
     */
    @PreAuthorize(hasPermi = "system:problem:export")
    @Log(title = "驾驶员-题库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DExamProblem dExamProblem) throws IOException {
        List<DExamProblem> list = dExamProblemService.selectDExamProblemList(dExamProblem);
        ExcelUtil<DExamProblem> util = new ExcelUtil<DExamProblem>(DExamProblem.class);
        util.exportExcel(response, list, "problem");
    }

    /**
     * 导入列表
     */
    @ApiOperation("导入列表")
    @PreAuthorize(hasPermi = "person:dExamProblem:import")
    @Log(title = "驾驶员-题库", businessType = BusinessType.IMPORT)
    @PostMapping("/A22ImportData")
    public AjaxResult importData(@Validated MultipartFile file) throws Exception {
        ExcelUtil<DExamProblem> util = new ExcelUtil<DExamProblem>(DExamProblem.class);
        List<DExamProblem> problemList = new ArrayList<>();
        try {
            problemList = util.importExcel(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return AjaxResult.error("数据格式有误");
        }

        Map<String, String> message = dExamProblemService.importUser(problemList);
        String code = message.get("code");
        if ("0".equals(code)) {
            return AjaxResult.success(message.get("msg"));
        } else {
            return AjaxResult.error(message.get("msg"));
        }
    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {

        ExcelUtilh<DExamProblem> util = new ExcelUtilh<DExamProblem>(DExamProblem.class);

        return util.importTemplateExcel("题表模板");

    }

    /**
     * 获取驾驶员-题库详细信息
     */
    @PreAuthorize(hasPermi = "person:dExamProblem:getInfo")
    @GetMapping(value = "/A22GetInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(dExamProblemService.selectDExamProblemById(id));
    }


    /**
     * 新增驾驶员-题库
     */
    @PreAuthorize(hasPermi = "person:dExamProblem:add")
    @Log(title = "驾驶员-题库", businessType = BusinessType.INSERT)
    @PostMapping("/A22Add")
    public AjaxResult add(@RequestBody DExamProblem dExamProblem) {
        List<DExamProblem> list = dExamProblemService.selectDExamProblemList(dExamProblem);
        if (list.size() == 0) {
            LoginUser userInfo = tokenService.getLoginUser();
            String Id = UUID.randomUUID().toString();
            dExamProblem.setId(Id);
            dExamProblem.setCreateBy(userInfo.getUsername());
            if (dExamProblem.getType() != 1L) {
                dExamProblem.setScore(2);
            } else {
                dExamProblem.setScore(1);
            }
            dExamProblem.setDeleteFlag(Long.valueOf(1));

            return toAjax(dExamProblemService.insertDExamProblem(dExamProblem));
        } else {
            return AjaxResult.error("该题已存在");
        }

    }

    /**
     * 修改驾驶员-题库
     */
    @PreAuthorize(hasPermi = "person:dExamProblem:edit")
    @Log(title = "驾驶员-题库", businessType = BusinessType.UPDATE)
    @PostMapping("/A22Edit")
    public AjaxResult edit(@RequestBody DExamProblem dExamProblem) {
        String Id=dExamProblem.getId();
        DExamProblem dExamProblem1 = new DExamProblem();
        dExamProblem1.setName(dExamProblem.getName());
        dExamProblem1.setOptionA(dExamProblem.getOptionA());
        dExamProblem1.setOptionB(dExamProblem.getOptionB());
        dExamProblem1.setOptionC(dExamProblem.getOptionC());
        dExamProblem1.setOptionD(dExamProblem.getOptionD());
        dExamProblem1.setType(dExamProblem.getType());
        dExamProblem1.setAnswer(dExamProblem.getAnswer());
        List<DExamProblem> list = dExamProblemService.selectDExamProblemList(dExamProblem1);
        if (list.size() == 0) {
            LoginUser userInfo = tokenService.getLoginUser();
            dExamProblem.setUpdateBy(userInfo.getUsername());
            if (dExamProblem.getType() != 1) {
                dExamProblem.setScore(2);
            } else {
                dExamProblem.setScore(1);
            }
            return toAjax(dExamProblemService.updateDExamProblem(dExamProblem));
        } else {
            List<DExamProblem> list1= list.stream().filter(a ->Objects.equals(Id, a.getId())).collect(Collectors.toList());
            if (list1.size()>0) {
                return toAjax(dExamProblemService.updateDExamProblem(dExamProblem));
            } else {
                return AjaxResult.error("该题已存在");
            }
        }
    }

    /**
     * 删除驾驶员-题库
     */
    @PreAuthorize(hasPermi = "person:dExamProblem:delete")
    @Log(title = "驾驶员-题库", businessType = BusinessType.DELETE)
    @GetMapping("/A22Delete/{id}")
    public AjaxResult remove(@PathVariable("id") String id) {
        DExamProblem dExamProblem = dExamProblemService.selectDExamProblemById(id);
        dExamProblem.setDeleteFlag(Long.valueOf(2));
        return toAjax(dExamProblemService.updateDExamProblem(dExamProblem));
    }
}
