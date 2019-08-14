package com.order.enums;

import lombok.Getter;

/**
 * @author ：clt
 * @Date ：Created in 18:39 2019/8/9
 */
@Getter
public enum ResultEnum {

    USER_SELECT_FAILED(100,"没有找到用户"),

    USER_SELECT_BY_ID_FAILED(100,"该用户id不存在"),

    USER_SELECT_BY_USERNAME_FAILED(100,"该用户名不存在"),

    USER_SELECT_BY_USERNAME_AND_PASSWORD_FAILED(100,"用户验证失败"),

    USER_REGISTER_FAILED(101, "用户注册失败"),

    USER_DELETE_FAILED(102,"用户删除失败"),

    USER_UPDATE_FAILED(103,"用户修改失败"),

    USER_LOGIN_FAILED(104,"用户密码错误"),

    USER_UPDATE_PASSWORD_FAILED(105,"用户密码修改失败")
    ;


    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
