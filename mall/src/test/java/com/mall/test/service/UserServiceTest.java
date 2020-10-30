package com.mall.test.service;

import com.mall.common.ServerResponse;
import com.mall.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-service.xml"})
public class UserServiceTest {

    @Resource(name="userServiceImpl")
    private UserService userService;

    @Test
    public void loginTest(){
        ServerResponse serverResponse=userService.login("Nick","123456");
        System.out.println(serverResponse);
    }

    @Test
    public void registerTest(){
        System.out.println(userService.register("Nick","cjs1999","蔡建树","男","17856500109",1));
    }

}
