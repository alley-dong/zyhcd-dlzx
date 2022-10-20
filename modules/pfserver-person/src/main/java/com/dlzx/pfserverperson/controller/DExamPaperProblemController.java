package com.dlzx.pfserverperson.controller;

import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverperson.domain.DExamPaper;
import com.dlzx.pfserverperson.domain.DExamProblem;
import com.dlzx.pfserverperson.service.IDExamPaperService;
import com.dlzx.pfserverperson.service.IDExamProblemService;
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
import com.dlzx.pfserverperson.domain.DExamPaperProblem;
import com.dlzx.pfserverperson.service.IDExamPaperProblemService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 驾驶员-试卷问题Controller
 *
 * @author dlzx
 * @date 2020-11-19
 */
@RestController
@RequestMapping("/person/dExamPaperProblem")
public class DExamPaperProblemController extends BaseController {
    @Autowired
    private IDExamPaperProblemService dExamPaperProblemService;
    @Autowired
    private IDExamPaperService dExamPaperService;
    @Autowired
    private IDExamProblemService dExamProblemService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询驾驶员-试卷问题列表
     */
    @PreAuthorize(hasPermi = "person:dExamPaperProblem:list")
    @GetMapping("/A20List")
    public TableDataInfo list(DExamPaperProblem dExamPaperProblem) {
        startPage(dExamPaperProblem.getPageNum(), dExamPaperProblem.getPageSize());
        List<DExamPaperProblem> list = dExamPaperProblemService.selectDExamPaperProblemList(dExamPaperProblem);
        return getDataTable(list);
    }

    /**
     * 导出驾驶员-试卷问题列表
     */
    @PreAuthorize(hasPermi = "person:dExamPaperProblem:export")
    @Log(title = "驾驶员-试卷问题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DExamPaperProblem dExamPaperProblem) throws IOException {
        List<DExamPaperProblem> list = dExamPaperProblemService.selectDExamPaperProblemList(dExamPaperProblem);
        ExcelUtil<DExamPaperProblem> util = new ExcelUtil<DExamPaperProblem>(DExamPaperProblem.class);
        util.exportExcel(response, list, "problem");
    }

    /**
     * 获取驾驶员-试卷问题详细信息
     */
    @PreAuthorize(hasPermi = "person:dExamPaperProblem:query")
    @GetMapping(value = "/A18GetInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(dExamPaperProblemService.selectDExamPaperProblemByIdPaper(id));
    }

    /**
     * 新增驾驶员-试卷问题
     */
    @PreAuthorize(hasPermi = "person:dExamPaperProblem:add")
    @Log(title = "驾驶员-试卷问题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DExamPaperProblem dExamPaperProblem) {

        return toAjax(dExamPaperProblemService.insertDExamPaperProblem(dExamPaperProblem));
    }

    @PreAuthorize(hasPermi = "person:dExamPaperProblem:add")
    @Log(title = "驾驶员-试卷问题", businessType = BusinessType.INSERT)
    @PostMapping("/add2")
    public AjaxResult add2(@RequestBody List list) {

        return toAjax(dExamPaperProblemService.insertDExamPaperProblemList(list));
    }

    @PreAuthorize(hasPermi = "person:dExamPaperProblem:add")
    @Log(title = "驾驶员-试卷问题", businessType = BusinessType.INSERT)
    @PostMapping("/A21Add1")
    public AjaxResult add1(@RequestBody Map<String, String> abc) {

        String a = abc.get("name");
        if (StringUtils.isEmpty(a)){
            return AjaxResult.error("试卷名称不能为空");
        }
        DExamPaper dExamPaper =new DExamPaper();
        List<DExamPaper> listPap = dExamPaperService.selectDExamPaperList(dExamPaper);
        List<DExamPaper> listPa = listPap.stream().filter(m -> Objects.equals(a,m.getName())).collect(Collectors.toList());
        if (listPa.size()> 0){
            return AjaxResult.error("试卷名称已存在");
        }
        String b = abc.get("ids");
        String Id = UUID.randomUUID().toString();

        LoginUser userInfo = tokenService.getLoginUser();
        DExamPaper c = new DExamPaper();
        c.setId(Id);
        c.setName(a);
        c.setCreateBy(userInfo.getUsername());
        c.setDeleteFlag(Long.valueOf(1));
        dExamPaperService.insertDExamPaper(c);
        List<DExamPaper> k = dExamPaperService.selectDExamPaperList(c);
        String q = k.get(0).getId();
        DExamProblem dExamProblem = new DExamProblem();
        List<DExamProblem> listProblem = dExamProblemService.selectDExamProblemList(dExamProblem);
        List<DExamPaperProblem> list = new ArrayList();
        String[] m = b.split(",");
        for (int i = 0; i < m.length; i++) {
            String s = m[i];
            List<DExamProblem> list1 = listProblem.stream().filter(x -> Objects.equals(s, x.getId())).collect(Collectors.toList());
            String uuId = UUID.randomUUID().toString();
            DExamPaperProblem p = new DExamPaperProblem();
            p.setId(uuId);
            p.setPaperId(q);
            p.setProblemId(s);
            p.setName(list1.get(0).getName());
            p.setType(list1.get(0).getType());
            p.setOptionA(list1.get(0).getOptionA());
            p.setOptionB(list1.get(0).getOptionB());
            p.setOptionC(list1.get(0).getOptionC());
            p.setOptionD(list1.get(0).getOptionD());
            p.setAnswer(list1.get(0).getAnswer());
            p.setScore(list1.get(0).getScore());
            list.add(p);
        }
        int msg= 1;
        if (list.size()>0) {
            msg= dExamPaperProblemService.insertDExamPaperProblemList(list);
        }
        return toAjax(msg);
    }

    /**
     * 修改驾驶员-试卷问题
     */
    @PreAuthorize(hasPermi = "system:problem:edit")
    @Log(title = "驾驶员-试卷问题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DExamPaperProblem dExamPaperProblem) {
        return toAjax(dExamPaperProblemService.updateDExamPaperProblem(dExamPaperProblem));
    }

    /**
     * 删除驾驶员-试卷问题
     */
    @PreAuthorize(hasPermi = "system:problem1:remove")
    @Log(title = "驾驶员-试卷问题", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(dExamPaperProblemService.deleteDExamPaperProblemByIds(ids));
    }
}
