package com.h2t.springboot.service.implement;

import java.util.UUID;
import com.h2t.springboot.entity.User;
import com.h2t.springboot.mapper.UserMapper;
import com.h2t.springboot.service.IUserService;
import com.h2t.springboot.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.UUID;

/**
 * 处理用户数据层的实现类
 */
/** 处理用户数据的业务层实现类 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        // 根据参数user对象获取注册的用户名
        String username = user.getUsername();
        User result = userMapper.findByUsername(username);
        if (result != null) {
            throw new UsernameDuplicateException("尝试注册的用户名[" + username + "]已经被占用");
        }

        // 创建当前时间对象
        Date now = new Date();
        // 补全数据：加密后的密码
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMd5Password(user.getPassword(), salt);
        user.setPassword(md5Password);
        // 补全数据：盐值
        user.setSalt(salt);
        // 补全数据：isDelete(0)
        user.setIsDelete(0);
        // 补全数据：4项日志属性
        user.setCreatedUser(username);
        user.setCreatedTime(now);
        user.setModifiedUser(username);
        user.setModifiedTime(now);
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("添加用户数据出现未知错误，请联系系统管理员");
        }
    }

    /**
     * 执行密码加密
     * @param password 原始密码
     * @param salt 盐值
     * @return 加密后的密文
     */
    private String getMd5Password(String password, String salt) {
        /*
         * 加密规则：
         * 1、无视原始密码的强度
         * 2、使用UUID作为盐值，在原始密码的左右两侧拼接
         * 3、循环加密3次
         */
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);
        if (result == null) {
            throw new UserNotFoundException("用户数据不存在的错误");
        }

        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在的错误");
        }

        // 从查询结果中获取盐值
        String salt = result.getSalt();
        // 调用getMd5Password()方法，将参数password和salt结合起来进行加密
        String md5Password = getMd5Password(password, salt);
        // 判断查询结果中的密码，与以上加密得到的密码是否不一致
        if (!result.getPassword().equals(md5Password)) {
            // 是：抛出PasswordNotMatchException异常
            throw new PasswordNotMatchException("密码验证失败的错误");
        }

        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldpassword, String newpassword) {
        User user = userMapper.findByUid(uid);
        if (user==null && user.getIsDelete()==1){
            throw  new UserNotFoundException("用户数据不存在");
        }
        String oldmd5Password = getMd5Password(oldpassword, user.getSalt());
        if (!user.getPassword().equals(oldmd5Password)){
            throw  new PasswordNotMatchException("密码错误");
        }
        String newmd5Password = getMd5Password(newpassword, user.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newmd5Password, username, new Date());
        if (rows!=1){
            throw new UpdataException("用户资料更新时异常");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        User user = userMapper.findByUid(uid);
        if (user==null && user.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());
        user1.setGender(user.getGender());
        return user1;
    }

    @Override
    public void changeInfo(User user, Integer uid, String username) {
        User result = userMapper.findByUid(uid);
        if (result==null && result.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updataUserInfoByuid(user);
        if (rows!=1){
            throw new UpdataException("用户信息修改时异常");
        }
    }

    @Override
    public void updataAvatar(Integer uid, String avatar, String username) {

        User result = userMapper.findByUid(uid);
        if (result==null && result.getIsDelete().equals(1)){
            throw  new UserNotFoundException("用户不存在");
        }

        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if (rows!=1){
            throw  new UpdataException("用户头像修改时异常");
        }
    }


}