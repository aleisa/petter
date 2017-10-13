package com.eminem.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by wangxinyu on 2017/5/18
 *
 * 封装restTemplate做http请求.
 */
@Component
public class RESTClient {
   private RestTemplate restTemplate = new RestTemplate();

    private static final Logger log = LoggerFactory.getLogger(RESTClient.class);

    /**
     * 发送GET请求
     * @param url  http请求地址
     * @param params 请求参数
     * @return
     */
   public <T> String sendGet(String url,T params){
       try {
           if(StringUtils.isEmpty(url)){
            throw new RuntimeException("http请求的url为空");

           }
         String result =  restTemplate.getForObject(url,String.class, params.getClass());
        return result;
       }catch (Exception e){
           log.error("失败：请求访问{}，请求参数：{}", url, params, e);
           throw new RestClientException("RESTClient exception", e);
       }
   }

    /**
     * 发送post请求
     * @param url  http请求地址
     * @param params  请求参数
     * @return
     */
   public <T> String sendPost(String url, T params){
       try {
           if(StringUtils.isEmpty(url)){
               throw new RuntimeException("http请求的url为空");

           }
           String result =  restTemplate.postForObject(url,params,String.class);
           return result;
       }catch (Exception e){
           log.error("失败：请求访问{}，请求参数：{}", url, params, e);
           throw new RestClientException("RESTClient exception", e);
       }
   }

    /**
     *
     *通用方法，put delete有返回值
     * @param url
     * @param params
     * @param method  请求的类型 HttpMethod.PUT
     * @return
     */
   public <T> String exchange(String url,T params,HttpMethod method){
       try {
           if(StringUtils.isEmpty(url)){
               throw new RuntimeException("http请求的url为空");

           }
           ObjectMapper mapper = new ObjectMapper();
           String jsonp = null;
           try {
             if (params!=null){
                 jsonp=mapper.writeValueAsString(params);
             }
           }catch (Exception e){
                log.error("http请求参数转换错误");
                    e.printStackTrace();
           }
           HttpHeaders headers = new HttpHeaders();
           headers.add("Content-Type", "application/json");
           headers.add("Accept", "*/*");

           HttpEntity<String> requestEntity = new HttpEntity<String>(jsonp, headers);
           ResponseEntity<String> responseEntity =  restTemplate.exchange(url,method,requestEntity,String.class);
           String result = requestEntity.getBody();
           return result;
       }catch (Exception e){
           log.error("失败：请求访问{}，请求参数：{}", url, params, e);
           throw new RestClientException("RESTClient exception", e);
       }
   }


}
