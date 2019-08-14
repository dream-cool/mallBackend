package com.order.util;

/**
 * @ Author   ：clt.
 * @ Date     ：Created in 12:39 2019/8/9
 */
import com.order.pojo.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author Mrchen
 */
@Component
public class MailUtil {

    /**
     * 邮件发送者
     */
    @Value("${spring.mail.username}")
    private String MAIL_SENDER;

    @Autowired
    private JavaMailSender javaMailSender;


    private Logger logger = LoggerFactory.getLogger(MailUtil.class);

    /**
     * 发送文本邮件
     *
     * @param Email
     */
    public  void sendSimpleMail(Email Email) {
        try {
            SimpleMailMessage mailMessage= new SimpleMailMessage();
            mailMessage.setFrom(MAIL_SENDER);
            mailMessage.setTo(Email.getRecipient());
            mailMessage.setSubject(Email.getSubject());
            mailMessage.setText(Email.getContent());
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }
}


