package com.zust.writemeservice;

import com.zust.writeme.model.User;
import com.zust.writeme.service.userService.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class userServiceImplTest extends WritemeServiceApplicationTests{

    @Autowired
    private UserService userService;
    @Test
    public void testGetUserInfoById(){
        User user = userService.getUserById(9);
        Assert.assertEquals("0",user.getStatus());
    }
}
