package com.eminem.service;

import com.eminem.service.intf.RedisMQ;
import com.eminem.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by wang on 17-3-8.
 */
public class RedisMQService implements RedisMQ {
    @Autowired
    private  RedisUtil redisUtil;

    public void send(String topic,Object event){
        String type=getType(topic);
         if(!type.equals("ERROR")){
             redisUtil.leftPush(type,event);
         }
    }

    public String getType(String topic){
        if(StringUtils.isNotEmpty(topic)){
            if ("ADDUSER".equals(topic)){
                return "1";
            }
        }
            return "ERROR";
    }


}

