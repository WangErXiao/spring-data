package com.yao.spring.redis.impl;

import com.yao.spring.redis.RedisBase;
import com.yao.spring.redis.share.FirstClient;
import org.springframework.data.redis.core.BoundValueOperations;

/**
 * Created by root on 15-3-14.
 */
public class FirstClientImpl extends RedisBase implements FirstClient {
    @Override
    public String getObj(String key) {
        BoundValueOperations boundValueOperations= template.boundValueOps(key);
        return (String)boundValueOperations.get();
    }
    @Override
    public void setObj(String key,String obj) {
        template.boundValueOps(key).set(obj);
    }
}
