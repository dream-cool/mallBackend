package com.order.service.impl;

import com.order.enums.ResultEnum;
import com.order.exception.OperationFailedException;
import com.order.mapper.UserMapper;
import com.order.pojo.User;
import com.order.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Mrchen
 * @ Author   ：clt.
 * @ Date     ：Created in 15:16 2019/8/9
 */
@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserId(Integer userId) {
        User user = userMapper.findByUserId(userId);
        if (user == null) {
            log.error("【查询用户】没有找到用户");
            throw new OperationFailedException(ResultEnum.USER_SELECT_BY_ID_FAILED);
        }
        return user;
    }

    @Override
    public List<User> findAllUser() {
        List<User> list = userMapper.findAllUser();
        if (list == null || list.size() == 0) {
            log.error("【查询用户】没有找到用户");
            throw new OperationFailedException(ResultEnum.USER_SELECT_FAILED);
        }
        return list;
    }

    @Override
    public List<User> findByUserName(String userName) {
        List<User> list = userMapper.findByUserName(userName);
        if (list == null || list.size() == 0) {
            log.error("【查询用户】没有找到用户");
            throw new OperationFailedException(ResultEnum.USER_SELECT_BY_USERNAME_FAILED);
        }
        return list;
    }

    @Override
    public User findByUserNameAndPassWord(String userName, String passWord) {
        User user = userMapper.findByUserNameAndPassWord(userName,passWord);
        if (user == null) {
            log.error("【查询用户】用户验证失败");
            throw new OperationFailedException(ResultEnum.USER_SELECT_BY_USERNAME_AND_PASSWORD_FAILED);
        }
        return user;
    }

    @Override
    public int insertOneUser(User user) {
        int NumberOfImpacts = userMapper.insertOneUser(user);
        if (NumberOfImpacts != 1) {
            log.error("【增加用户】注册用户失败");
            throw new OperationFailedException(ResultEnum.USER_REGISTER_FAILED);
        }
        return NumberOfImpacts;
    }

    @Override
    public int deleteOneUser(Integer userId) {
        int NumberOfImpacts = userMapper.deleteOneUser(userId);
        if (NumberOfImpacts != 1) {
            log.error("【删除用户】用户删除失败");
            throw new OperationFailedException(ResultEnum.USER_REGISTER_FAILED);
        }
        return NumberOfImpacts;
    }

    @Override
    public int updateOneUser(User user) {
        int NumberOfImpacts = userMapper.updateOneUser(user);
        if (NumberOfImpacts != 1) {
            log.error("【修改用户】用户修改失败");
            throw new OperationFailedException(ResultEnum.USER_UPDATE_FAILED);
        }
        return NumberOfImpacts;
    }

    @Override
    public int updateUserlastLoginTime(Integer userId, Date lastLoginTime) {
        int NumberOfImpacts = userMapper.updateUserlastLoginTime(userId, lastLoginTime);
        if (NumberOfImpacts != 1) {
            log.error("【修改用户】用户最后登录时间修改失败");
            throw new OperationFailedException(ResultEnum.USER_UPDATE_FAILED);
        }
        return NumberOfImpacts;
    }

    @Override
    public int updateUserMoney(Integer userId, BigDecimal money) {
        int NumberOfImpacts = userMapper.updateUserMoney(userId, money);
        if (NumberOfImpacts != 1) {
            log.error("【修改用户】用户余额修改失败");
            throw new OperationFailedException(ResultEnum.USER_UPDATE_FAILED);
        }
        return NumberOfImpacts;
    }

    @Override
    public int updateUserState(Integer userId, int state) {
        int NumberOfImpacts = userMapper.updateUserState(userId, state);
        if (NumberOfImpacts != 1) {
            log.error("【修改用户】用户状态修改失败");
            throw new OperationFailedException(ResultEnum.USER_UPDATE_FAILED);
        }
        return NumberOfImpacts;
    }

    @Override
    public int updateUserPassWord(Integer userId, String oldPassWord, String newPassWord) {
        int NumberOfImpacts = userMapper.updateUserPassWord(userId, oldPassWord, newPassWord);
        if (NumberOfImpacts != 1) {
            log.error("【修改用户】用户密码修改失败");
            throw new OperationFailedException(ResultEnum.USER_UPDATE_PASSWORD_FAILED);
        }
        return NumberOfImpacts;
    }
}
