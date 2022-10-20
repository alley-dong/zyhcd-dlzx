package com.dlzx.pfserverperson.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfserverperson.domain.PContract;
import com.dlzx.pfserverperson.service.IPContractService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 合作方管理-合同Controller
 * 
 * @author dlzx
 * @date 2020-11-26
 */
@RestController
@RequestMapping("/contract")
public class PContractController extends BaseController
{
    @Autowired
    private IPContractService pContractService;

    @Autowired
    private TokenService tokenService;

    /**
     * 导出合作方管理-合同列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:contract:export")
    @Log(title = "合作方管理-合同", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PContract pContract) throws IOException
    {
        List<PContract> list = pContractService.selectPContractList(pContract);
        ExcelUtil<PContract> util = new ExcelUtil<PContract>(PContract.class);
        util.exportExcel(response, list, "contract");
    }



    /**
     * 查询合作方管理-合同列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:contract:list")
    @PostMapping("/F01CustomerContractList")
    public TableDataInfo list(@RequestBody PContract pContract)
    {
        // 客户合同和供应商合同这里是共用一张表，对type字段进行区分。1客户合同 2供应商合同
        pContract.setType(1L);
        List<PContract> list = pContractService.selectPContractList(pContract);

        // 定义计算时间的格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        list.forEach(item->{

            // 计算合同到期时间
            try {
                pContractService.checkMonth(item, df, now);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        List<PContract> collectList = list.stream().filter(t->t.getMathMonth()!=0).sorted(Comparator.comparing(PContract::getMathMonth)).collect(Collectors.toList());
        List<PContract> collect = list.stream().filter(t->t.getMathMonth()==0).collect(Collectors.toList());
        collectList.addAll(collect);
        return getDataTableList(startPageContract(collectList,pContract.getPageNum(),pContract.getPageSize()),list);
    }


    /**
     * 查询供应商管理-供应商合同列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:contract:carrierslist")
    @PostMapping("/F05CarriersContractList")
    public TableDataInfo F05CarriersContractList(@RequestBody PContract pContract)
    {
        // 客户合同和供应商合同这里是共用一张表，对type字段进行区分。1客户合同 2供应商合同
        pContract.setType(2L);
        List<PContract> list = pContractService.selectPContractList(pContract);
        // 定义计算时间的格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        list.forEach(item->{

            // 计算合同到期时间
            try {
                pContractService.checkMonth(item, df, now);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        List<PContract> collectList = list.stream().filter(t->t.getMathMonth()!=0).sorted(Comparator.comparing(PContract::getMathMonth)).collect(Collectors.toList());
        List<PContract> collect = list.stream().filter(t->t.getMathMonth()==0).collect(Collectors.toList());
        collectList.addAll(collect);
        return getDataTableList(startPageContract(collectList,pContract.getPageNum(),pContract.getPageSize()),list);
    }


    /**
     * 获取合作方管理-客户/供应商合同详细信息（客户和供应商共用一个接口）
     */
    @PreAuthorize(hasPermi = "pfserverperson:contract:info")
    @GetMapping(value = "/F04CustomerContractInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(pContractService.selectPContractById(id));
    }

    /**
     * 获取合作方管理-客户/供应商合同标记/取消预警（客户和供应商共用一个接口）
     */
    @PreAuthorize(hasPermi = "pfserverperson:contract:status")
    @PostMapping(value = "/F01CustomerContractStatus")
    public AjaxResult f01CustomerContractStatus(@RequestBody PContract pContract)
    {
        return AjaxResult.success(pContractService.updateCustomerContractStatus(pContract));
    }

    /**
     * 新增合作方管理-客户合同
     */
    @PreAuthorize(hasPermi = "pfserverperson:contract:add")
    @Log(title = "合作方管理-合同", businessType = BusinessType.INSERT)
    @PostMapping("/F02CustomerContractAdd")
    public AjaxResult add(@RequestBody PContract pContract)
    {
        if (StringUtils.isEmpty(pContract.getName())){
            return AjaxResult.error("合同名称不能为空");
        }
        if (StringUtils.isEmpty(pContract.getCarrierName())){
            return AjaxResult.error("客户名称不能为空");
        }
        if (StringUtils.isEmpty(pContract.getPaymentMethod())){
            return AjaxResult.error("付款方式不能为空");
        }
        if (StringUtils.isNull(pContract.getContractType())){
            return AjaxResult.error("客户合同类型不能为空");
        }
        if (StringUtils.isNull(pContract.getContractStartTime())){
            return AjaxResult.error("合同开始日期不能为空");
        }
        if (StringUtils.isNull(pContract.getContractEndTiem())){
            return AjaxResult.error("合同结束日期不能为空");
        }
        if (pContract.getContractStartTime().getTime()>pContract.getContractEndTiem().getTime()){
            return AjaxResult.error("合同结束日期必须大于开始日期");
        }

        // 1客户合同 2供应商合同
        pContract.setType(1L);
        // 预警状态 1正常 2预警
        pContract.setFlag(1L);
        //生成uuid
        UUID uuid = UUID.randomUUID();
        pContract.setId(uuid+"");

        LoginUser userInfo = tokenService.getLoginUser();
        pContract.setCreateBy(userInfo.getUsername());
        return toAjax(pContractService.insertPContract(pContract));
    }

    /**
     * 新增合作方管理-供应商合同
     */
    @PreAuthorize(hasPermi = "pfserverperson:contract:carriersadd")
    @Log(title = "合作方管理-供应商合同", businessType = BusinessType.INSERT)
    @PostMapping("/F06CarriersContractAdd")
    public AjaxResult F06CarriersContractAdd(@RequestBody PContract pContract)
    {
        if (StringUtils.isEmpty(pContract.getName())){
            return AjaxResult.error("合同名称不能为空");
        }
        if (StringUtils.isEmpty(pContract.getCarrierId()) && StringUtils.isEmpty(pContract.getCarrierName())){
            return AjaxResult.error("供应商名称不能为空");
        }
        if (StringUtils.isEmpty(pContract.getPaymentMethod())){
            return AjaxResult.error("付款方式不能为空");
        }
        if (StringUtils.isNull(pContract.getContractStartTime())){
            return AjaxResult.error("合同开始日期不能为空");
        }
        if (StringUtils.isNull(pContract.getContractEndTiem())){
            return AjaxResult.error("合同结束日期不能为空");
        }
        if (pContract.getContractStartTime().getTime()>pContract.getContractEndTiem().getTime()){
            return AjaxResult.error("合同结束日期必须大于开始日期");
        }

        // 1客户合同 2供应商合同
        pContract.setType(2L);
        // 预警状态 1正常 2预警
        pContract.setFlag(1L);
        //生成uuid
        UUID uuid = UUID.randomUUID();
        pContract.setId(uuid+"");

        LoginUser userInfo = tokenService.getLoginUser();
        pContract.setCreateBy(userInfo.getUsername());
        return toAjax(pContractService.insertPContract(pContract));
    }


    /**
     * 修改合作方管理-客户合同
     */
    @PreAuthorize(hasPermi = "pfserverperson:contract:edit")
    @Log(title = "合作方管理-客户合同", businessType = BusinessType.UPDATE)
    @PostMapping("/F03CustomerContractEditSave")
    public AjaxResult edit(@RequestBody PContract pContract)
    {
        if (StringUtils.isEmpty(pContract.getName())){
            return AjaxResult.error("合同名称不能为空");
        }
        if (StringUtils.isEmpty(pContract.getCarrierName())){
            return AjaxResult.error("客户名称不能为空");
        }
        if (StringUtils.isEmpty(pContract.getPaymentMethod())){
            return AjaxResult.error("付款方式不能为空");
        }
        if (StringUtils.isNull(pContract.getContractType())){
            return AjaxResult.error("客户合同类型不能为空");
        }
        if (StringUtils.isNull(pContract.getContractStartTime())){
            return AjaxResult.error("合同开始日期不能为空");
        }
        if (StringUtils.isNull(pContract.getContractEndTiem())){
            return AjaxResult.error("合同结束日期不能为空");
        }
        if (pContract.getContractStartTime().getTime()>pContract.getContractEndTiem().getTime()){
            return AjaxResult.error("合同结束日期必须大于开始日期");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        pContract.setUpdateBy(userInfo.getUsername());
        return toAjax(pContractService.updatePContract(pContract));
    }

    /**
     * 修改合作方管理-供应商合同
     */
    @PreAuthorize(hasPermi = "pfserverperson:contract:carriersedit")
    @Log(title = "合作方管理-供应商合同", businessType = BusinessType.UPDATE)
    @PostMapping("/F07CarriersContractEditSave")
    public AjaxResult F07CarriersContractEditSave(@RequestBody PContract pContract)
    {
        if (StringUtils.isEmpty(pContract.getName())){
            return AjaxResult.error("合同名称不能为空");
        }
        if (StringUtils.isEmpty(pContract.getCarrierId()) && StringUtils.isEmpty(pContract.getCarrierName())){
            return AjaxResult.error("供应商名称不能为空");
        }
        if (StringUtils.isEmpty(pContract.getPaymentMethod())){
            return AjaxResult.error("付款方式不能为空");
        }
        if (StringUtils.isNull(pContract.getContractStartTime())){
            return AjaxResult.error("合同开始日期不能为空");
        }
        if (StringUtils.isNull(pContract.getContractEndTiem())){
            return AjaxResult.error("合同结束日期不能为空");
        }
        if (pContract.getContractStartTime().getTime()>pContract.getContractEndTiem().getTime()){
            return AjaxResult.error("合同结束日期必须大于开始日期");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        pContract.setUpdateBy(userInfo.getUsername());
        return toAjax(pContractService.updatePContract(pContract));
    }


    /**
     * 删除合作方管理-客户/供应商合同（客户和供应商共用一个接口）
     */
    @PreAuthorize(hasPermi = "pfserverperson:contract:delete")
    @Log(title = "合作方管理-合同", businessType = BusinessType.DELETE)
	@GetMapping("/F01CustomerContractDelete/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pContractService.deletePContractByIds(ids));
    }


    /**
     * 开始分页
     * @param list
     * @param pageNum 页码
     * @param pageSize 每页多少条数据
     * @return
     */
    public static List startPageContract(List list, Integer pageNum,
                                 Integer pageSize) {
        if (list == null) {
            return new ArrayList();
        }
        if (list.size() == 0) {
            return new ArrayList();
        }

        Integer count = list.size(); // 记录总数
        Integer pageCount = 0; // 页数
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        int fromIndex = 0; // 开始索引
        int toIndex = 0; // 结束索引

        if (pageNum != pageCount) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }

        List pageList = list.subList(fromIndex, toIndex);

        return pageList;
    }
}
