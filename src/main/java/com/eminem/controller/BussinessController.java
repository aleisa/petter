package com.eminem.controller;

import com.eminem.service.BussinessService;
import com.eminem.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 17-2-5.
 */
@RestController
@RequestMapping("/bussiness")
public class BussinessController {
    @Autowired
    BussinessService bussinessService;
    @Autowired
    UserService userService;


    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @RequestMapping
    public List<Map<String,Object>> findAll(){
        List<Map<String,Object>> all=new ArrayList<Map<String, Object>>();
        all=bussinessService.selectAll();
        Map<String,Object> user =new HashMap<>();
        user.put("username","wang");
        user.put("password","123");
        userService.createUser(user);
        return all;
    }
}
