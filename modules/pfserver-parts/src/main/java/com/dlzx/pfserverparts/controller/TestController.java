package com.dlzx.pfserverparts.controller;

import com.dlzx.common.core.domain.R;
import com.dlzx.pfserverparts.client.VodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private VodClient vodClient;


    @PostMapping("/{userId}")
    public R deleteVideo(@PathVariable String userId){

        vodClient.getUser(userId);

        return R.ok();
    }
}
