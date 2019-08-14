package com.order;

import com.order.enums.ResultEnum;
import com.order.exception.OperationFailedException;
import com.order.pojo.Email;
import com.order.util.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailLoginApplicationTests {

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private MailUtil mailUtil;

    @Test
    public void contextLoads() {

    }

    /**
     *测试redis连接正常
     */
    @Test
    public void testRedis(){
        template.opsForValue().set("k1", "v2");
    }


    /**
     *测试redisTemplate常用接口
     */
    @Test
    public void testRedisTemplate(){
        template.opsForValue().set("k1", "v2");
        HashMap map = new HashMap();
        map.put("k4", "k4");
        map.put("k5", "k5");
        template.opsForValue().multiSet(map);
        Collection<String> collection = new ArrayList<>();
        collection.add("k1");
        collection.add("k2");
        collection.add("k3");
        collection.add("k4");
        collection.add("k5");
        System.out.println(template.opsForValue().multiGet(collection));
//        System.out.println(jedis.mget("k1","k2","k3"));
        template.opsForHash().put("h11", "username1", "chen1");
//        jedis.hset("h1","username","chen");
        System.out.println(template.opsForHash().get("h11", "username1"));
//        System.out.println(jedis.hget("h1","username"));
        Map<String ,String > map1=new HashMap<>();
        map1.put("tel","15151515");
        map1.put("email","dssa@qq.com");
        template.opsForHash().putAll("h22", map1);
//        jedis.hmset("h2",map);
//        List<String> list=jedis.hmget("h2","tel","email");
//        for (String s:list
//        ) {
//            System.out.println(s);
//        }
        Collection<Object> collection1 = new ArrayList<>();
        collection1.add("tel");
        collection1.add("email");
        System.out.println(template.opsForHash().get("h22", "tel"));
        System.out.println(template.opsForHash().get("h22", "email"));
        System.out.println(template.opsForHash().multiGet("h22", collection1));

    }

    /**
     *测试发送邮件功能
     */
    @Test
    public void testSendEmail(){
        mailUtil.sendSimpleMail(new Email("1769693979@qq.com",
                "测试邮件发送","这是一份测试邮件"));
    }

    /**
     *测试异常功能
     */
    @Test
    public void testException(){
        try{
            throw new OperationFailedException(ResultEnum.USER_DELETE_FAILED);
        }catch (OperationFailedException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testBean(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getBean("pageHelper");
    }

}
