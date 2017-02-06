package com.eminem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ysen6 on 2017/1/31.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping
    public String hello(){
        logger.info("asdfasdf");
        logger.debug("asdfasdf");
        logger.error("asdfasdf");
         return "hello";

    }

}
