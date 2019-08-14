package com.order.controller;

import com.order.pojo.Email;
import com.order.pojo.User;
import com.order.service.UserService;
import com.order.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Mrchen
 * @ Author   ：clt.
 * @ Date     ：Created in 12:46 2019/8/9
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private MailUtil mailUtil;

    @RequestMapping("/passwordLogin")
    public String loginPassword() {
        return "/login/PasswordLogin";
    }

    @RequestMapping("/verificationLogin")
    public String loginVerification() {
        return "/login/VerificationLogin";
    }

    @RequestMapping("/verificationLoginCheck")
    @ResponseBody
    public Map<String, String> verificationLoginCheck(@RequestBody User user) {
        Map<String, String> map = new HashMap<>(16);
        String code = null;
        String temp;
        String password = (String) template.opsForHash().get(user.getUserName(), "password");
        if (password != null) {
            temp = (String) template.opsForHash().get(user.getUserName(), "count");
            ;
            if (Integer.parseInt(temp) >= 2) {
                map.put("code", "400");
                template.delete(user.getUserName());
            } else if (user.getPassWord().equals(password)) {
                template.opsForValue().set(user.getUserName() + "check", "0");
                map.put("code", "100");
            } else {
                template.opsForHash().increment(user.getUserName(), "count", 1);
                map.put("code", "200");
            }
        } else {
            map.put("code", "404");
        }
        return map;
    }

    @RequestMapping("/sendVerificationLogin")
    @ResponseBody
    public Map<String, String> send(@RequestBody User user) {
        Random random = new Random();
        Map<String, String> rmap = new HashMap<>(16);
        Map<String, String> map = new HashMap<>(16);
        Date date = new Date();
        String content = "";
        long retime = (24 - date.getHours()) * 3600 - (date.getMinutes() * 60) - (date.getSeconds());
        for (int i = 0; i < 6; i++) {
            int temp = random.nextInt(10);
            content += temp;
        }
        String key = user.getUserName() + "check";
        String check = template.opsForValue().get(key);
        if (check != null) {
            if (Integer.parseInt(check) >= 3) {
                template.expire(key, (int) retime, TimeUnit.SECONDS);
                map.put("check", "200");
                return map;
            } else {
                template.opsForValue().increment(key);
            }
        } else {
            template.opsForValue().set(key, "1");
        }
        rmap.put("password", content);
        rmap.put("count", "0");
        template.opsForHash().putAll(user.getUserName(), rmap);
        mailUtil.sendSimpleMail(new Email(user.getUserName(), "验证码邮件", content));
        template.expire(user.getUserName(), 60, TimeUnit.SECONDS);
        map.put("code", "500");
        return map;
    }

    @RequestMapping("/passwordLoginCheck")
    @ResponseBody
    public Map<String,String> passwordLoginCheck( User user ){
        Map<String,String> map =new HashMap<>();
        String code="2000";
        User u=userService.findByUserNameAndPassWord(user.getUserName(), user.getPassWord());
        if(u==null){
//            密码或用户名错误
            code="400";
        }
        if (user.getPassWord()==""||user.getPassWord()==null){
            code="500";
        }
        map.put("code",code);
        return map;
    }
}
