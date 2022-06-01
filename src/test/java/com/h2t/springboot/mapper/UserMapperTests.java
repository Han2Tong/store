package com.h2t.springboot.mapper;


import com.h2t.springboot.entity.User;
import com.h2t.springboot.service.IUserService;
import com.h2t.springboot.service.implement.UserServiceImpl;
import lombok.ToString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;

import java.util.Date;

@SpringBootTest
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void testinsert() {
        User user = new User();
        user.setUsername("123");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println("rows=" + rows);
    }


    @DisplayName("testFindUserByusername")
    @Test
    public void testFindUserByusername() {
        User tom = userMapper.findByUsername("Tom");
        System.out.println(tom);
    }

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void login() {
        User user = userService.login("h2t", "awds ");
        System.out.println(user);
    }

    @Test
    public void changePassage(){
    userService.changePassword(18,"123","321","123");
    }

    @Test
    public void findByUid() {
        User user = userMapper.findByUid(19);
        System.out.println(user);
    }

    @Test
    public void updataUserInfoByuid(){
        User user = new User();
        user.setUid(15);
        user.setPhone("13209497692");
        user.setEmail("h2t@gmail.com");
        user.setGender(1);
        userMapper.updataUserInfoByuid(user);
    }


    @Test
    public void getByUid(){
        User user = userService.getByUid(15);
        System.out.println(user);
    }

    @Test
    public void changeInfo(){
        User user = new User();
        user.setPhone("12112212");
        user.setEmail("1233@issj.com");
        user.setGender(0);
        userService.changeInfo(user,15,"h2t");
    }
    @Test
    public void updateAvatarByUidAfter(){
        Integer use = userMapper.updateAvatarByUid(15, "ji.png", "管理员", new Date());
        System.out.println(use);
    }
    @Test
    public  void updataAvatar(){
        userService.updataAvatar(16,"as.jpg","管理员");
    }
}
