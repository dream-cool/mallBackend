package com.order.service.impl;

import com.order.mapper.UserMapper;
import com.order.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jws.soap.SOAPBinding;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author ：clt
 * @Date ：Created in 19:44 2019/8/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findByUserId() {
        User user = userMapper.findByUserId(6);
        System.out.println(user);
        Assert.assertNotNull(user);
    }

    @Test
    public void findAllUser() {
        List<User> list = userMapper.findAllUser();
        System.out.println(list);
        Assert.assertNotNull(list);
    }

    @Test
    public void findByUserName() {
        List<User> list = userMapper.findByUserName("chen");
        System.out.println(list);
        Assert.assertNotNull(list);
    }

    @Test
    public void findByUserNameAndPassWord() {
        User user = userMapper.findByUserNameAndPassWord("chen","123456");
        System.out.println(user);
        Assert.assertNotNull(user);
    }

    @Test
    public void insertOneUser() {
        User user = new User();
        user.setUserName("mrchen");
        user.setPassWord("123456");
        user.setLastLoginTime(new Date());
        user.setRegTime(new Date());
        int result = userMapper.insertOneUser(user);
        Assert.assertEquals(1, result);
    }

    @Test
    public void deleteOneUser() {
        int numbersOfResult = userMapper.deleteOneUser(5);
        System.out.println(numbersOfResult);
        Assert.assertEquals(1, numbersOfResult);
    }

    @Test
    public void updateOneUser() {
        User user = new User();
        user.setUserName("mrchen");
        user.setPassWord("11111");
        user.setLastLoginTime(new Date());
        user.setRegTime(new Date());
        int result = userMapper.updateOneUser(user);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateUserlastLoginTime() {
        int result = userMapper.updateUserlastLoginTime(6,new Date());
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateUserMoney() {
        int result = userMapper.updateUserMoney(6,new BigDecimal(10));
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateUserState() {
        int result = userMapper.updateUserState(6,0);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateUserPassWord() {
        int result = userMapper.updateUserPassWord(6,"11111","123456");
        Assert.assertEquals(1, result);
    }
}