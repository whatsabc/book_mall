package com.mall.service;

import com.mall.common.ServerResponse;
import com.mall.pojo.User;

/**
 * service层命名规范
 * 与pojo相关的变量必须名称保持一致
 */
public interface UserService {
    /**
     *
     * @param userName
     * @param userPassword
     * @return
     */
    ServerResponse<User> login(String userName, String userPassword);
    ServerResponse<String> register(String userNickname,String userPassword,String userName,String userSex,String userTel,Integer userRole);

}
