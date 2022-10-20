package com.dlzx.system.controller.administrator_app;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.system.client.PersonClient;
import com.dlzx.system.domain.DDriver;
import com.dlzx.system.domain.DDriverApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ylh
 * @program:dlzx
 * @description：驾驶员档案
 * @create:2020-12-15 10-57
 */
@RestController
@RequestMapping("/administrator-app/driver")
public class DriverArchivesController {


    @Autowired
    private PersonClient personClient;

    /**
     * App --管理员端 驾驶员档案列表
     * @param dDriver
     * @return
     */
    @RequestMapping("/archives")
    @ResponseBody
    @Log(title = "驾驶员档案列表")
    public AjaxResult archives(@RequestBody DDriver dDriver){
        return AjaxResult.success(personClient.A09List(dDriver));
    }

    @GetMapping(value = "/info/{id}")
    public AjaxResult info(@PathVariable("id")String id){
        return personClient.A11Info(id);
    }

    //教育
    @GetMapping(value = "/S02EL/{id}")
    public AjaxResult S02EL(@PathVariable("id")String id){
        return personClient.A11DriverEducationList(id);
    }

    //事故
    @GetMapping(value = "/S02AL/{id}")
    public AjaxResult S02AL(@PathVariable("id")String id){
        return personClient.A11DriverAccidentList(id);
    }

    //驾龄
    @GetMapping(value = "/S02DL/{id}")
    public AjaxResult S02DL(@PathVariable("id")String id){
        return personClient.A11DriverDriverList(id);
    }

    //家庭
    @GetMapping(value = "/S02FL/{id}")
    public AjaxResult S02FL(@PathVariable("id")String id){
        return personClient.A11DriverFamilyList(id);
    }

}
