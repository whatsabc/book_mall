package com.mall.dao;

import com.mall.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**
     *
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 传入多个参数，最好给每个参数命名
     * @param userNickname
     * @param userPassword
     * @return
     */
    User loginByUsernameAndPassword(@Param("userNickname") String userNickname, @Param("userPassword") String userPassword);
    Integer selectCountByNickname(String userNickname);
    Integer selectCountByUserTel(String userTel);
}
