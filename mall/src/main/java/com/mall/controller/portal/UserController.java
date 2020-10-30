package com.mall.controller.portal;

import com.mall.common.ServerResponse;
import com.mall.common.Static;
import com.mall.pojo.User;
import com.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param username
     * @param password
     * @param httpSession
     * @return
     */
    @RequestMapping(value="/login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession httpSession){
        ServerResponse serverResponse=userService.login(username,password);
        if(serverResponse.isSuccess()){
            httpSession.setAttribute(Static.CURRENT_USER,serverResponse.getData());
        }
        return serverResponse;
    }
}
