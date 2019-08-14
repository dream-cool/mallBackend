package com.order.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ Author   ：clt.
 * @ Date     ：Created in 12:35 2019/8/9
 * @author Mrchen
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Email implements Serializable {

    private static final long serialVersionUID = -2116367492649751914L;

    /**
     *邮件接收人
     */
    private String recipient;

    /**
     *邮件主题
     */

    private String subject;

    /**
     *邮件内容
     */

    private String content;

}
