package com.nickchen.thirdparty.sample.springretry.service;

import com.nickchen.thirdparty.sample.springretry.model.User;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author <a href="mailto:chenyuxiong@vpgame.cn">nickChen</a>
 * @date 2019年01月24日
 */
@Service
public class UserServiceImpl implements UserService  {
    Random random = new Random();

    @Override
    public User findUser(String name) throws RuntimeException {
        System.out.println("find user error");
        throw new RuntimeException("error");
//        return new User(name, random.nextInt(100));
    }

    @Override
    public User recover(RuntimeException e, String name) {
        System.out.println("recover: " + name + " e: " + e.getMessage());
        return new User(name, random.nextInt(100));
    }
}
