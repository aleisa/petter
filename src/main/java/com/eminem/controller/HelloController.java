package com.eminem.controller;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by ysen6 on 2017/1/31.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping
    public String hello(){
        List<Integer> users= Lists.newArrayList(1,2,3,4);
        users = users.stream().filter(user -> user <3 ).collect(Collectors.toList());
        logger.info("asdfasdf");
        logger.debug("asdfasdf");
        logger.error("asdfasdf");
         return "hello";

    }

}
