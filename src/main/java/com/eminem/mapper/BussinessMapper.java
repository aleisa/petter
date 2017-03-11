package com.eminem.mapper;

import com.eminem.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by wang on 17-2-5.
 */
public interface BussinessMapper {
    List<Map<String,Object>> findAll(Map<String,String> params);
}
