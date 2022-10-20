package com.dlzx.pfserverperson.controller;

import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverperson.domain.*;
import com.dlzx.pfserverperson.service.IDDriverExamService;
import com.dlzx.pfserverperson.service.IDExamPaperProblemService;
import com.dlzx.pfserverperson.service.IDExamProblemService;
import com.dlzx.system.api.model.LoginDriver;
import com.dlzx.system.api.model.LoginUser;
import feign.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfserverperson.service.IDDriverExamProblemService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 驾驶员-司机试卷问题Controller
 * 
 * @author yangluhe
 * @date 2020-11-18
 */
@RestController
@RequestMapping("/person/dDriverExamProblem")
public class DDriverExamProblemController extends BaseController
{
    @Autowired
    private IDDriverExamService driverExamService;
    @Autowired
    private IDDriverExamProblemService driverExamProblemService;
    @Autowired
    private IDExamPaperProblemService dExamPaperProblemService;
    @Autowired
    private IDExamProblemService dExamProblemService;
    @Autowired
    private IDDriverExamProblemService dDriverExamProblemService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private DriverTokenService driverTokenService;

    /**
     * 查询驾驶员-司机试卷问题列表
     */
    @PreAuthorize(hasPermi = "person:dDriverExamProblem:list")
    @GetMapping("/list")
    public TableDataInfo list(DDriverExamProblem dDriverExamProblem)
    {
        startPage();
        List<DDriverExamProblem> list = dDriverExamProblemService.selectDDriverExamProblemList(dDriverExamProblem);
        return getDataTable(list);
    }

    /**
     * 导出驾驶员-司机试卷问题列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:problem:export")
    @Log(title = "驾驶员-司机试卷问题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DDriverExamProblem dDriverExamProblem) throws IOException
    {
        List<DDriverExamProblem> list = dDriverExamProblemService.selectDDriverExamProblemList(dDriverExamProblem);
        ExcelUtil<DDriverExamProblem> util = new ExcelUtil<DDriverExamProblem>(DDriverExamProblem.class);
        util.exportExcel(response, list, "problem");
    }

    /**
     * 获取驾驶员-司机试卷问题详细信息
     */
    @PreAuthorize(hasPermi = "pfserverperson:problem:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(dDriverExamProblemService.selectDDriverExamProblemById(id));
    }

    /**
     * 新增驾驶员-司机试卷问题
     */
    @PreAuthorize(hasPermi = "pfserverperson:problem:add")
    @Log(title = "驾驶员-司机试卷问题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DDriverExamProblem dDriverExamProblem)
    {
        return toAjax(dDriverExamProblemService.insertDDriverExamProblem(dDriverExamProblem));
    }

    /**
     * 修改驾驶员-司机试卷问题
     */
    @PreAuthorize(hasPermi = "pfserverperson:problem:edit")
    @Log(title = "驾驶员-司机试卷问题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DDriverExamProblem dDriverExamProblem)
    {
        return toAjax(dDriverExamProblemService.updateDDriverExamProblem(dDriverExamProblem));
    }

    /**
     * 删除驾驶员-司机试卷问题
     */
    @PreAuthorize(hasPermi = "pfserverperson:problem:remove")
    @Log(title = "驾驶员-司机试卷问题", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(dDriverExamProblemService.deleteDDriverExamProblemByIds(ids));
    }

    @RequestMapping(path = "/B01List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Log(title = "驾驶员考试试题列表")
    public TableDataInfo B01List() {
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        DDriverExam dDriverExam1 = new DDriverExam();
        dDriverExam1.setDriverId(loginDriver.getUserid());
        DDriverExam dDriverExam = driverExamService.selectDDriverExamByDriverIdNearQ(loginDriver.getUserid());
        if (dDriverExam == null){
            List list=new ArrayList();
            return getDataTable(list);
        }
        if (dDriverExam.getStatus() == 2) {
            DExamPaperProblem dExamPaperProblem = new DExamPaperProblem();
            dExamPaperProblem.setPaperId(dDriverExam.getPaperId());
            List<DExamPaperProblem> list = dExamPaperProblemService.selectDExamPaperProblemList(dExamPaperProblem);
            return getDataTable(list);
        } else {
            DDriverExamProblem dDriverExamProblem = new DDriverExamProblem();
            dDriverExamProblem.setDriverId(loginDriver.getUserid());
            dDriverExamProblem.setExamId(dDriverExam.getId());
            List<DDriverExamProblem> list1 = dDriverExamProblemService.selectDDriverExamProblemList(dDriverExamProblem);
            Long totalSellerDan = list1.stream().filter(m -> m.getType() == 1).map(DDriverExamProblem::getScore).reduce(new Long(0), Long::sum);
            Long totalSellerDuo = list1.stream().filter(m -> m.getType() == 2).map(DDriverExamProblem::getScore).reduce(new Long(0), Long::sum);
            Long totalSellerPan = list1.stream().filter(m -> m.getType() == 3).map(DDriverExamProblem::getScore).reduce(new Long(0), Long::sum);
            Long scoreHe = totalSellerDan + totalSellerDuo + totalSellerPan;
            List<DProblem> list2 = new ArrayList<>();
            DProblem dProblem = new DProblem();
            dProblem.setList(list1);
            dProblem.setStatus(dDriverExam.getStatus());
            dProblem.setScoreDan(totalSellerDan);
            dProblem.setScoreDuo(totalSellerDuo);
            dProblem.setScorePan(totalSellerPan);
            dProblem.setScoreHe(scoreHe);
            list2.add(dProblem);
            return getDataTable(list2);
        }

    }

    @RequestMapping(path = "/B02List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Headers({"acceptEncoding: gzip","contentType: application/json"})
    @Log(title = "驾驶员考试试题列表")
    public TableDataInfo B02List(@RequestBody List<DExamPaperDaAn> list) {
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        DDriverExam dDriverExam = driverExamService.selectDDriverExamByDriverIdNearQ(loginDriver.getUserid());
        dDriverExam.setStatus(1L);
        driverExamService.updateDDriverExam(dDriverExam);
        DExamProblem dExamProblem = new DExamProblem();
        List<DExamProblem> listProblem = dExamProblemService.selectDExamProblemList(dExamProblem);
        List<DDriverExamProblem> listExam = new ArrayList<>();
        for (DExamPaperDaAn dExamPaperDaAn : list) {
            Long type = dExamPaperDaAn.getType();
            String answer = dExamPaperDaAn.getAnswer();
            String problemId = dExamPaperDaAn.getProblemId();
            int score = 0;
            if (type == 1 || type == 3) {
                List<DExamProblem> listScore = listProblem.stream().filter(b -> Objects.equals(problemId, b.getId()) && Objects.equals(answer, b.getAnswer())).collect(Collectors.toList());
                if (listScore.size() > 0) {
                    score = listScore.get(0).getScore();
                }
            }
            if (type ==2) {
                List<DExamProblem> listScore = listProblem.stream().filter(b -> Objects.equals(problemId, b.getId()) ).collect(Collectors.toList());
                String ans = listScore.get(0).getAnswer();
                String[] result = ans.split(",");
                Arrays.sort(result);

                String xuans =dExamPaperDaAn.getAnswer();
                if (StringUtils.isNotEmpty(xuans)) {
                    String[] xuanResult = xuans.split(",");
                    Arrays.sort(xuanResult);
                  if (Arrays.equals(result,xuanResult)) {
                      score = 2;
                  }
                }
            }
            List<DExamProblem> listP = listProblem.stream().filter(b -> Objects.equals(problemId, b.getId())).collect(Collectors.toList());
            DDriverExamProblem dDriverExamProblem = new DDriverExamProblem();
            dDriverExamProblem.setDriverId(loginDriver.getUserid());
            dDriverExamProblem.setAnswer(answer);
            dDriverExamProblem.setProblemId(problemId);
            dDriverExamProblem.setExamId(dDriverExam.getId());
            dDriverExamProblem.setName(listP.get(0).getName());
            dDriverExamProblem.setType(listP.get(0).getType());
            dDriverExamProblem.setOptionA(listP.get(0).getOptionA());
            dDriverExamProblem.setOptionB(listP.get(0).getOptionB());
            dDriverExamProblem.setOptionC(listP.get(0).getOptionC());
            dDriverExamProblem.setOptionD(listP.get(0).getOptionD());
            dDriverExamProblem.setScore(Long.valueOf(score));
            dDriverExamProblem.setTrueAnswer(listP.get(0).getAnswer());
            String Id = UUID.randomUUID().toString();
            dDriverExamProblem.setId(Id);
            listExam.add(dDriverExamProblem);
        }
        Long totalSellerDan = listExam.stream().filter(m -> m.getType() == 1).map(DDriverExamProblem::getScore).reduce(new Long(0), Long::sum);
        Long totalSellerDuo = listExam.stream().filter(m -> m.getType() == 2).map(DDriverExamProblem::getScore).reduce(new Long(0), Long::sum);
        Long totalSellerPan = listExam.stream().filter(m -> m.getType() == 3).map(DDriverExamProblem::getScore).reduce(new Long(0), Long::sum);
        Long scoreHe = totalSellerDan + totalSellerDuo + totalSellerPan;
        dDriverExamProblemService.insertDDriverExamProblemList(listExam);
        List<DProblem> list2 = new ArrayList<>();
        DProblem dProblem = new DProblem();
        dProblem.setList(listExam);
        dProblem.setScoreDan(totalSellerDan);
        dProblem.setScoreDuo(totalSellerDuo);
        dProblem.setScorePan(totalSellerPan);
        dProblem.setScoreHe(scoreHe);
        list2.add(dProblem);
        return getDataTable(list2);
    }
}
