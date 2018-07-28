package com.zust.writeme.controller;

import com.zust.writeme.cloud.ScheduleSimpleService;
import com.zust.writeme.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private ScheduleSimpleService scheduleSimpleService;

    @RequestMapping (value = "/key",method = RequestMethod.POST)
    public UserInfo getMS(@RequestParam int id){
        return scheduleSimpleService.getMS(id);
    }
}
