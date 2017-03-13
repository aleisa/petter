package com.eminem.controller;

import com.eminem.entity.User;
import com.eminem.service.BussinessService;
import com.eminem.service.UserService;
import io.swagger.annotations.*;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@Api("bussiness相关API")
public class BussinessController {
    @Autowired
    BussinessService bussinessService;
    @Autowired
    UserService userService;
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParams({@ApiImplicitParam(paramType="query",name="id",dataType = "String",required = true,value = "用户的ID",defaultValue = "1" ),
            @ApiImplicitParam(paramType="query",name="username",dataType = "String",required = true,value = "用户的username",defaultValue = "1" ),
            @ApiImplicitParam(paramType="query",name="password",dataType = "String",required = true,value = "用户的password",defaultValue = "1" )
    })
    @RequestMapping(value = "/user" , method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    public List<Map<String,Object>> findAll( String id,String username,String password){
        List<Map<String,Object>> all=new ArrayList<Map<String, Object>>();
        all=bussinessService.selectAll();
        Map<String,Object> user =new HashMap<>();
        user.put("username",username);
        user.put("password",password);
        userService.createUser(user);
        return all;
    }

    @ApiOperation(value="查询用户", notes="根据username查询用户")
    @ApiImplicitParams({@ApiImplicitParam(paramType="query",name="username",dataType = "String",required = true,value = "被查找用户的username",defaultValue = "1" ),
            @ApiImplicitParam(paramType="query",name="password",dataType = "String",required = true,value = "被查找用户的password",defaultValue = "1" )
    })
    @RequestMapping(value = "/findUser" , method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    public User findUser(String username,String password){
       User user = userService.findUserByName(username ,password);
        return  user;
    }




}
