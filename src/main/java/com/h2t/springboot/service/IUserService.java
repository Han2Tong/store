package com.h2t.springboot.service;

import com.h2t.springboot.entity.User;

/**
 * 处理用户数据的业务层接口
 */
public interface IUserService {

    /**
     * 用户注册
     *
     * @param user 用户数据
     */
    void reg(User user);

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录成功的用户数据
     */
    User login(String username, String password);

    /**
     * 根据udi更改用户登录密码
     *
     * @param uid
     * @param username
     * @param oldpassword
     * @param newpassword
     */
    void changePassword(Integer uid, String username, String oldpassword, String newpassword);

    /**
     * 根据与用户id查找
     *
     * @param uid
     * @return
     */
    User getByUid(Integer uid);

    /**
     * 根据uid改变用户信息
     *
     * @param user
     * @param uid
     * @param username
     */
    void changeInfo(User user, Integer uid, String username);

    /**
     * 根据uid修改用户头像
     *
     * @param uid
     * @param avatar
     * @param username
     */
    void updataAvatar(Integer uid, String avatar, String username);
}
