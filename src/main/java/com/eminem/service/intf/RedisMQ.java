package com.eminem.service.intf;

/**
 * Created by wang on 17-3-8.
 */
public interface RedisMQ {
    public void send(String topic,Object event);
}
