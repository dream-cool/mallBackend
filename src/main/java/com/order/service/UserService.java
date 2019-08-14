package com.order.service;

import com.order.pojo.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ Author   ：clt.
 * @ Date     ：Created in 15:15 2019/8/9
 * @author Mrchen
 */

public interface UserService {

    /**
     * @param userId 用户id
     *根据用户Id查询唯一用户
     */
    User findByUserId(Integer userId);

    /**
     * 查询所有用户
     */
    List<User> findAllUser();

    /**
     * 根据用户名查询用户
     */
    List<User> findByUserName(String userName);

    /**
     * 根据用户名和用户密码查询用户，即验证用户登录
     */
    User findByUserNameAndPassWord(String userName, String passWord);

    /**
     * 增加一个用户，即用户注册
     */
    int insertOneUser(User user);

    /**
     * 删除一个用户，即用户根据用户id删除用户
     */
    int deleteOneUser(Integer userId);

    /**
     * 根据用户ID修改用户信息
     */
    int updateOneUser(User user);

    /**
     * 根据用户ID修改用户最后登录时间
     */
    int updateUserlastLoginTime(Integer userId, Date lastLoginTime);

    /**
     * 根据用户ID修改用户余额
     */
    int updateUserMoney(Integer userId, BigDecimal bigDecimal);

    /**
     * 根据用户ID修改用户余额
     * @param state 用户状态 是否被锁定
     */
    int updateUserState(Integer userId, int state);

    /**
     * 根据用户ID和旧密码修改新密码
     * @param oldPassWord 旧密码
     * @param newPassWord 新密码
     */
    int updateUserPassWord(Integer userId, String oldPassWord, String newPassWord);
}
