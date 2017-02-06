package com.eminem.service;

import com.eminem.mapper.BussinessMapper;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 17-2-5.
 */
@Service
public class BussinessService {
    @Autowired
    BussinessMapper bussinessMapper;

    public List<Map<String,Object>> selectAll(){
        Map<String,String> params= new HashMap<>();
        List<Map<String,Object>> all=new ArrayList<Map<String, Object>>();
        all = bussinessMapper.findAll(params);
        return all;
    }
}
