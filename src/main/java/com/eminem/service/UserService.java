package com.eminem.service;

import com.eminem.entity.User;
import com.eminem.service.intf.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by ysen6 on 2017/2/8.
 */
@Service
public class UserService{
    @Autowired
    private UserRepository repository;

    public User createUser(Map<String,Object> resultMap){
       User user=new User();
        user.setUsername(resultMap.get("username").toString());
        user.setPassword(resultMap.get("password").toString());
        repository.save(user);
        return user;
    }

    public User findById(String id){
       User user = repository.findOne(id);
        return user;
    }

    public User findUserByName(String name,String password){
        User user = repository.findUserByName(name,password);
        return user;
    }


}
