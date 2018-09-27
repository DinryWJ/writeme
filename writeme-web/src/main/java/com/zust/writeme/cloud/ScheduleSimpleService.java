package com.zust.writeme.cloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "writeme-service-provider")
public interface ScheduleSimpleService {
    @RequestMapping(value = "/key",method = RequestMethod.POST)
    UserInfo getMS(@RequestParam(value = "id") int id);

}
