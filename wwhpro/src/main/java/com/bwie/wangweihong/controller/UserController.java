package com.bwie.wangweihong.controller;

import com.bwie.wangweihong.entity.User;
import com.bwie.wangweihong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wangweihong on 2017/7/28.
 */
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

//    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());


    @GetMapping("index")
    public String index() {
        return "index";
    }

    //登录
    @RequestMapping("login")
    public String login(String name, String password,HttpSession httpSession) {
        User u = userRepository.findUserByNameAndPassword(name, password);
        httpSession.setAttribute("users", u.getName());
        if(u==null){
            return "index";
        }
        return "forward:select";

    }

    //注册
    @RequestMapping("insert")
    public String save(User user) {
        userRepository.save(user);
        return "index";
    }

    //删除
    @RequestMapping("delete")
    public String delete(User user) {
        userRepository.delete(user);
        return "forward:select";
    }

    //查询单条用户信息
    @RequestMapping("queryUserById")
    public String queryUserById(Integer id, Model model) {
        System.out.printf("用户id" + id);
        User u = userRepository.findOne(id);
        model.addAttribute("u", u);
        return "update";
    }

    //修改用户
    @RequestMapping("update")
    public String update(User user) {
        User u = userRepository.save(user);
        return "forward:select";
    }


    //查询所有用户
    @RequestMapping("select")
    public String queryAll(Model model) {
        List<User> list = userRepository.findAll();
        model.addAttribute("list", list);
        return "select";
    }

    //通过用户名查询单条用户信息
    @RequestMapping("queryByName")
    public String findUserByName(String name,Model model){
        User us = userRepository.findUserByName(name);
        model.addAttribute("us",us);
        return "selectbyname";

    }




}
