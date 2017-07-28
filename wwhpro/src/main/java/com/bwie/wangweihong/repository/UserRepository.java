package com.bwie.wangweihong.repository;

import com.bwie.wangweihong.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Wangweihong on 2017/7/28.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    //登录
    public User findUserByNameAndPassword(String name, String password);

    //通过ID查询单条用户信息
    public User queryUserById(Integer id);

    //通过用户名单条查询用户信息
    public User findUserByName(String name);


}
