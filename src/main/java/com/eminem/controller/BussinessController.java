package com.eminem.controller;

import com.eminem.service.BussinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @RequestMapping
    public List<Map<String,Object>> findAll(){
        List<Map<String,Object>> all=new ArrayList<Map<String, Object>>();
        all=bussinessService.selectAll();
        return all;
    }
}
