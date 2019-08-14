package com.order.exception;

import com.order.enums.ResultEnum;

/**
 * @author ：clt
 * @Date ：Created in 18:30 2019/8/9
 */
public class OperationFailedException extends RuntimeException {

    private Integer code;

    private String message;

    public OperationFailedException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public OperationFailedException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
