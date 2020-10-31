package com.mall.service.impl;

import com.mall.common.ResponseCode;
import com.mall.common.ServerResponse;
import com.mall.common.Static;
import com.mall.dao.UserMapper;
import com.mall.pojo.User;
import com.mall.service.UserService;
import com.mall.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String userName, String userPassword) {
        User user=userMapper.loginByUsernameAndPassword(userName,MD5Util.MD5EncodeUtf8(userPassword));
        if(user==null){
            return ServerResponse.createErrorMessage("用户名或密码错误");
        }
        return ServerResponse.createSuccess("登录成功",user);
    }

    @Override
    public ServerResponse<String> register(String userNickname, String userPassword, String userName, String userSex, String userTel, Integer userRole) {
        if(userMapper.selectCountByNickname(userNickname)>0){
            return ServerResponse.createErrorMessage("用户名已经存在");
        }
        if(userMapper.selectCountByUserTel(userTel)>0){
            return ServerResponse.createErrorMessage("该手机号码已经注册");
        }

        User user=new User();

        user.setUserNickname(userNickname);
        user.setUserPassword(MD5Util.MD5EncodeUtf8(userPassword));
        user.setUserName(userName);
        user.setUserSex(userSex);
        user.setUserTel(userTel);
        user.setUserRole(Static.Role.ROLE_CUSTOMER);

        if(userMapper.insert(user)>0){
            return ServerResponse.createSuccessMessage("注册成功");
        }
        return ServerResponse.createErrorMessage("注册失败");
    }
}
