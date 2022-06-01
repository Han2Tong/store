package com.h2t.springboot.mapper;

import com.h2t.springboot.entity.User;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Date;

public interface UserMapper {

    /**
     * 插入用户数据
     *
     * @param user
     * @return
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户数据
     *
     * @param username 用户名
     * @return 匹配的用户数据，如果没有匹配的数据，则返回null
     */
    User findByUsername(String username);

    /**
     * 根据uid更新用户的密码
     *
     * @param uid          用户的id
     * @param password     新密码
     * @param modifiedUser 最后修改执行人
     * @param modifiedTime 最后修改时间
     * @return 受影响的行数
     */
    Integer updatePasswordByUid(
            @Param("uid") Integer uid,
            @Param("password") String password,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据用户ID查找用户数据
     *
     * @param uid
     * @return 返回用户数据如果查到，否则返回null
     */
    User findByUid(Integer uid);

    /**
     *更改用户信息
     * @param
     * @return 收影响的行数
     */
    Integer updataUserInfoByuid(User user);

    /**
     * 根据uid修改用户头像
     * @param uid
     * @return
     */
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                                   @Param("avatar") String avatat,
                                   @Param("modifiedUser") String modifiedUser,
                                   @Param("modifiedTime") Date modifiedTime);
}