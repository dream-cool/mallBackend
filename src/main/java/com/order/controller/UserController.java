package com.order.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.order.exception.OperationFailedException;
import com.order.pojo.User;
import com.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.order.constant.UserConstant.*;

/**
 * @author ：clt
 * @Date ：Created in 16:30 2019/8/9
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/reg")
    public String userReg() {
        return "/user/Register";
    }

    @RequestMapping("/upd")
    public String userUpd() {
        return "/user/UserUpdate";
    }

    @RequestMapping("/man")
    public String userMan() {
        return "/user/UserManager";
    }

    @RequestMapping("/register")
    @ResponseBody
    public Map<String, Object> userRegister(@RequestBody User user) {
        user.setRegTime(new Date());
        user.setLastLoginTime(new Date());
        Map<String, Object> map = new HashMap<>(16);
        map.put("code", OPERATION_SUCCESS);
        try {
            userService.insertOneUser(user);
        } catch (OperationFailedException e) {
            map.put("code", OPERATION_FAILED);
            e.printStackTrace();
        }
        map.put("user", user);
        return map;
    }

    @RequestMapping("/registerCheck")
    @ResponseBody
    public Map<String, Object> registerCheck(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>(16);
        if (null == user || "".equals(user.getUserName())){
            map.put("code", REGISTER_USERNAME_NULL);
            return map;
        }
        List<User> users = userService.findAllUser();
        map.put("code", REGISTER_USERNAME_AVAILABLE);
        for (User u : users) {
            if (u.getUserName().equals(user.getUserName())) {
                map.put("code", REGISTER_USERNAME_REPEAT);
                break;
            }
        }
        return map;
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<User> userList(String currentPage,String pageSize) {

        int pn = Integer.parseInt(currentPage);
        int size = Integer.parseInt(pageSize);
        PageHelper.startPage(pn, size);
        List<User> userList = userService.findAllUser();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> userUpdate( @RequestBody User user) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("code", OPERATION_SUCCESS);
        try {
            userService.updateOneUser(user);
        } catch (OperationFailedException e) {
            map.put("code", OPERATION_FAILED);
            e.printStackTrace();
        }
        map.put("user", user);
        return map;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> userDelete(@RequestParam("userId") Integer userId) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("code", OPERATION_SUCCESS);
        try {
            userService.deleteOneUser(userId);
        } catch (OperationFailedException e) {
            map.put("code", OPERATION_FAILED);
            e.printStackTrace();
        }
        if (OPERATION_SUCCESS == (Integer) map.get("code")) {
            PageHelper.startPage(1, 10);
            List<User> userList = userService.findAllUser();
            PageInfo<User> userpageInfo = new PageInfo<>(userList);
            map.put("PageInfo", userpageInfo);
        }
        return map;
    }

    @RequestMapping("/findUser")
    public String findUser(@RequestParam("userId") Integer userId, Model model){
        User user=userService.findByUserId(userId);
        model.addAttribute("userId",userId);
        return "/user/UserUpdate";
    }

    @RequestMapping("/finduser")
    @ResponseBody
    public User test(@RequestParam("userId") Integer userId){
        User user=userService.findByUserId(userId);
        return user;
    }

//    @RequestMapping("/update")
//    @ResponseBody
//    public User update(User user,String uId){
//        User u;
//        if(uId!=null){
//            u=userService.selectByuserId(Integer.parseInt(uId));
//            System.out.println(uId+"#####################");
//        }else{
//            userService.updateUser(user);
//            u=userService.selectByuserId(user.getUserId());
//            System.out.println(user+"#####################");
//        }
//        return u;
//    }

}
