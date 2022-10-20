package com.dlzx.pfserverperson.controller;

import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverperson.domain.DExamPaper;
import com.dlzx.pfserverperson.service.IDExamPaperService;
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
import com.dlzx.pfserverperson.domain.DDriverExam;
import com.dlzx.pfserverperson.service.IDDriverExamService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 驾驶员-司机考试Controller
 *
 * @author yangluhe
 * @date 2020-11-18
 */
@RestController
@RequestMapping("/person/dDriverExam")
public class DDriverExamController extends BaseController {
    @Autowired
    private IDDriverExamService dDriverExamService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private IDExamPaperService dExamPaperService;

    /**
     * 查询驾驶员-司机考试列表
     */
    @PreAuthorize(hasPermi = "person:dDriverExam:list")
    @GetMapping("/list")
    public TableDataInfo list(DDriverExam dDriverExam) {
        startPage();
        List<DDriverExam> list = dDriverExamService.selectDDriverExamList(dDriverExam);
        return getDataTable(list);
    }
    /**
     * 查询驾驶员-司机考试列表
     */
    @PreAuthorize(hasPermi = "person:dDriverExam:listDriver")
    @PostMapping("/A19ListDriver")
    public TableDataInfo listT(@RequestBody DDriverExam dDriverExam) {
        List<DDriverExam> list = dDriverExamService.selectDDriverExamListByPaperId(dDriverExam);
        return getDataTable(list);
    }

    /**
     * 导出驾驶员-司机考试列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:exam:export")
    @Log(title = "驾驶员-司机考试", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DDriverExam dDriverExam) throws IOException {
        List<DDriverExam> list = dDriverExamService.selectDDriverExamList(dDriverExam);
        ExcelUtil<DDriverExam> util = new ExcelUtil<DDriverExam>(DDriverExam.class);
        util.exportExcel(response, list, "exam");
    }

    /**
     * 获取驾驶员-司机考试详细信息
     */
    @PreAuthorize(hasPermi = "pfserverperson:exam:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(dDriverExamService.selectDDriverExamById(id));
    }

    /**
     * 新增驾驶员-司机考试
     */
    @PreAuthorize(hasPermi = "pfserverperson:exam:add")
    @Log(title = "驾驶员-司机考试", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DDriverExam dDriverExam) {
        return toAjax(dDriverExamService.insertDDriverExam(dDriverExam));
    }

    /**
     * 新增驾驶员-司机考试
     */
    @PreAuthorize(hasPermi = "pfserverperson:exam:add")
    @Log(title = "驾驶员-司机考试", businessType = BusinessType.INSERT)
    @PostMapping("/add2")
    public AjaxResult add2(@RequestBody List<DDriverExam> list) {
        return toAjax(dDriverExamService.insertDDriverExamList(list));
    }

    /**
     * 新增驾驶员-司机考试
     */
    @PreAuthorize(hasPermi = "person:dDriverExam:add")
    @Log(title = "驾驶员-司机考试", businessType = BusinessType.INSERT)
    @PostMapping("/A19Add1")
    public AjaxResult add1(@RequestBody Map<String, String> abc) {

        String a = abc.get("paperId");
        String b = abc.get("driverId");
        String c = abc.get("redriverId");

        String[] m = b.split(",");
        String[] n = c.split(",");
//删掉取消勾选的人
        DDriverExam dDriverExam = new DDriverExam();
        List<DDriverExam> listM = dDriverExamService.selectDDriverExamList(dDriverExam);
        List<String> listId = new ArrayList<>();
        for (int i = 0; i < n.length; i++) {
            String s = n[i];
            if (s != "") {
                List<DDriverExam> list1 = listM.stream().filter(x -> Objects.equals(a, x.getPaperId()) && Objects.equals(s, x.getDriverId())).collect(Collectors.toList());
                if (list1.size() > 0) {
                    listId.add(list1.get(0).getId());
                }
            }
        }
        if (listId.size() > 0) {
            dDriverExamService.deleteDDriverExamByIdList(listId);
        }
        DDriverExam e = new DDriverExam();
        e.setPaperId(a);
        List<DDriverExam> listE = dDriverExamService.selectDDriverExamList(e);
        List<String> list1 = new ArrayList<>();
        for (int j = 0; j < listE.size(); j++) {
            list1.add(listE.get(j).getDriverId());
        }
        LoginUser userInfo = tokenService.getLoginUser();
//获得试卷名字
        DExamPaper na = dExamPaperService.selectDExamPaperById(a);
        String paN = na.getName();
        List<DDriverExam> list = new ArrayList();
        for (int z = 0; z < m.length; z++) {
            String y = m[z];
            if (list1.contains(y)) {

            } else {
                DDriverExam p = new DDriverExam();
                String Id = UUID.randomUUID().toString();
                p.setId(Id);
                p.setPaperId(a);
                p.setPaperName(paN);
                p.setDriverId(y);
                p.setStatus(Long.valueOf(2));
                p.setCreateBy(userInfo.getUsername());
                p.setCreateTime(DateUtils.getNowDate());
                list.add(p);
            }
        }
        int mg = 1;
        if (list.size() > 0) {
            mg = dDriverExamService.insertDDriverExamList(list);
        }
        return toAjax(mg);
    }

    /**
     * 修改驾驶员-司机考试
     */
    @PreAuthorize(hasPermi = "pfserverperson:exam:edit")
    @Log(title = "驾驶员-司机考试", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DDriverExam dDriverExam) {
        return toAjax(dDriverExamService.updateDDriverExam(dDriverExam));
    }

    /**
     * 删除驾驶员-司机考试
     */
    @PreAuthorize(hasPermi = "pfserverperson:exam:remove")
    @Log(title = "驾驶员-司机考试", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(dDriverExamService.deleteDDriverExamByIds(ids));
    }


}
