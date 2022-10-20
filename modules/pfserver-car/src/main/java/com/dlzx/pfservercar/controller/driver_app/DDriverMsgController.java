package com.dlzx.pfservercar.controller.driver_app;

import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.List;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.domain.R;
import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.pfservercar.client.PersonClient;
import com.dlzx.pfservercar.domain.DDriver;
import com.dlzx.pfservercar.domain.DVacation;
import com.dlzx.system.api.model.LoginDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfservercar.domain.DDriverMsg;
import com.dlzx.pfservercar.service.IDDriverMsgService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 驾驶员-司机消息Controller
 * 
 * @author dlzx
 * @date 2020-12-07
 */
@RestController
@RequestMapping("/driver-api/msg")
public class DDriverMsgController extends BaseController
{
    @Autowired
    private IDDriverMsgService dDriverMsgService;

    @Autowired
    private DriverTokenService driverTokenService;

    @Autowired
    private PersonClient personClient;

    /**
     * App 个人中心-个人信息接口
     */
    @RequestMapping("/A01DriverInfo")
    @ResponseBody
    @Log(title = "个人中心-个人信息接口")
    public AjaxResult info()
    {
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        if (loginDriver==null){
            return AjaxResult.error("用户信息为空");
        }
        return personClient.A01DriverInfo(loginDriver.getUserid());
    }

    /**
     * App 个人消息-消息列表接口
     */
    @RequestMapping("/A03DriverMessageList")
    @ResponseBody
    @Log(title = "App 个人消息-消息列表接口")
    public TableDataInfo msgList(@RequestBody DDriverMsg dDriverMsg)
    {
        startPage(dDriverMsg.getPageNum(),dDriverMsg.getPageSize());
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        if (loginDriver==null){
            throw new CustomException("用户信息为空");
        }
        startPage();
        List<DDriverMsg>dDriverMsgs = dDriverMsgService.queryDriverMsgList(loginDriver.getUserid());
        return getDataTable(dDriverMsgs);
    }

    /**
     * App 个人消息-修改阅读状态接口
     */
    @RequestMapping("/A03DriverReadStatus")
    @ResponseBody
    @Log(title = "App 个人消息-修改阅读状态接口")
    public AjaxResult updateReadStatus(@RequestBody DDriverMsg dDriverMsg)
    {
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        if (loginDriver==null){
            return AjaxResult.error("用户信息为空");
        }
        // 已读
        dDriverMsg.setId(dDriverMsg.getId());
        dDriverMsg.setStatus(2L);
        int result = dDriverMsgService.updateDDriverMsg(dDriverMsg);
        return AjaxResult.success(result);
    }

    /**
     * App 个人消息-消息删除接口
     */
    @RequestMapping("/A03DriverDelMessage")
    @ResponseBody
    @Log(title = "App 个人消息-消息删除接口")
    public AjaxResult delMsg(@RequestBody DDriverMsg dDriverMsg)
    {
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        if (loginDriver==null){
            return AjaxResult.error("用户信息为空");
        }
        // 删除
        dDriverMsg.setId(dDriverMsg.getId());
        dDriverMsg.setDeleteFlag(2L);
        int result = dDriverMsgService.updateDDriverMsg(dDriverMsg);
        return AjaxResult.success(result);
    }

    /**
     * App 个人消息-获取未读提醒接口
     */
    @RequestMapping("/A04QueryUnreadList")
    @ResponseBody
    @Log(title = "App 个人消息-获取未读提醒接口")
    public AjaxResult unreadList(HttpServletRequest request,
                                 HttpServletResponse response, BufferedReader br)
    {
        // header部分
        System.out.print("headerName信息==========="+request.getHeaderNames());
        Enumeration<?> enum1 = request.getHeaderNames();
        while (enum1.hasMoreElements()) {
            String key = (String) enum1.nextElement();
            String value = request.getHeader(key);
            System.out.println("header请求头信息========="+key + "\t" + value);
        }
        // body部分
        String inputLine;
        String str = "";
        try {
            while ((inputLine = br.readLine()) != null) {
                str += inputLine;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        System.out.println("body信息==========:" + str);

        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        if (loginDriver==null){
            return AjaxResult.error("用户信息为空");
        }
        List<DDriverMsg> dDriverMsgs = dDriverMsgService.queryDriverUnreadList(loginDriver.getUserid());
        return AjaxResult.success(dDriverMsgs);
    }


    /**
     * App 个人消息-获取未读提醒接口
     */
    @Log(title = "驾驶员-驾驶员消息")
    @RequestMapping("/addDriverMsg")
    public AjaxResult addMsg(@RequestParam(value = "type",required = false)Long type, @RequestParam(value = "relationId",required = false)String relationId,
                             @RequestParam(value = "driverId",required = false)String driverId, @RequestParam(value = "title",required = false)String title,
                             @RequestParam(value = "content",required = false)String content)
    {
        return toAjax(dDriverMsgService.insertDDriverMsg(type,relationId,driverId,title,content));
    }




    /**
     * 查询驾驶员-司机消息列表
     */
    @PreAuthorize(hasPermi = "system:msg:list")
    @GetMapping("/list")
    public TableDataInfo list(DDriverMsg dDriverMsg)
    {
        startPage();
        List<DDriverMsg> list = dDriverMsgService.selectDDriverMsgList(dDriverMsg);
        return getDataTable(list);
    }

    /**
     * 导出驾驶员-司机消息列表
     */
    @PreAuthorize(hasPermi = "system:msg:export")
    @Log(title = "驾驶员-司机消息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DDriverMsg dDriverMsg) throws IOException
    {
        List<DDriverMsg> list = dDriverMsgService.selectDDriverMsgList(dDriverMsg);
        ExcelUtil<DDriverMsg> util = new ExcelUtil<DDriverMsg>(DDriverMsg.class);
        util.exportExcel(response, list, "msg");
    }

    /**
     * 获取驾驶员-司机消息详细信息
     */
    @PreAuthorize(hasPermi = "system:msg:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(dDriverMsgService.selectDDriverMsgById(id));
    }

    /**
     * 新增驾驶员-司机消息
     */
    @PreAuthorize(hasPermi = "system:msg:add")
    @Log(title = "驾驶员-司机消息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DDriverMsg dDriverMsg)
    {
        return toAjax(dDriverMsgService.insertDDriverMsg(dDriverMsg));
    }

    /**
     * 修改驾驶员-司机消息
     */
    @PreAuthorize(hasPermi = "system:msg:edit")
    @Log(title = "驾驶员-司机消息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DDriverMsg dDriverMsg)
    {
        return toAjax(dDriverMsgService.updateDDriverMsg(dDriverMsg));
    }

    /**
     * 删除驾驶员-司机消息
     */
    @PreAuthorize(hasPermi = "system:msg:remove")
    @Log(title = "驾驶员-司机消息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(dDriverMsgService.deleteDDriverMsgByIds(ids));
    }

}
