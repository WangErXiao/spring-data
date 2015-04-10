/*
 * Project: trunk
 * 
 * File Created at 2014/12/26
 * 
 * Copyright 2014 Greenline.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Greenline Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Greenline.com.
 */
package com.yao.spring.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class RedisUtil {
    static {
        initPool();
    }
    private static volatile JedisPool jedisPool;
    private static ResourceBundle resourceBundle;
    public static Jedis getResource() {
        return jedisPool.getResource();
    }
    public static void returnResource(Jedis jedis) {
        jedisPool.returnResource(jedis);
    }
    public static void initPool() {
            if(jedisPool != null){
                return;
            }
            loadProperties();
            String host = resourceBundle.getString("redis.host");
            int port = Integer.parseInt(resourceBundle.getString("redis.port"));
            JedisPoolConfig config = config();
            jedisPool = new JedisPool(config,host,port,60,null);
    }
    private static void loadProperties() {
        resourceBundle = ResourceBundle.getBundle("config/redis-config");
    }
    private static JedisPoolConfig config() {
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }
    public static void main(String[]args){
       Jedis jedis= RedisUtil.getResource();
        RedisUtil.returnResource(jedis);
    }
}
