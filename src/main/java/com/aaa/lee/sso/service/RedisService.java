package com.aaa.lee.sso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/8/26 23:29
 * @Description
 **/
@Service
public class RedisService {

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * @author Seven Lee
     * @description
     *      通过key来获取redis的value值
     * @param key
     * @date 2019/7/27
     * @return java.lang.String
     * @throws
     **/
    public String get(String key) {
        return jedisCluster.get(key);
    }

    /**
     * @author Seven Lee
     * @description
     *      把数据存入到redis中
     * @param key, value
     * @date 2019/7/27
     * @return java.lang.String
     * @throws
     **/
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    /**
     * @author Seven Lee
     * @description
     *      根据key删除redis中的数据
     * @param key
     * @date 2019/7/27
     * @return java.lang.Long
     * @throws
     **/
    public Long del(String key) {
        return jedisCluster.del(key);
    }

    /**
     * @author Seven Lee
     * @description
     *      设置redis数据的失效时间
     * @param key, seconds
     * @date 2019/7/27
     * @return java.lang.String
     * @throws
     **/
    public Long expire(String key, Integer seconds) {
        return jedisCluster.expire(key, seconds);
    }


}
