package com.yao.spring.redis.impl;

import com.yao.spring.redis.RedisBase;
import com.yao.spring.redis.share.FirstClient;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.BoundZSetOperations;

import java.util.Map;
import java.util.Set;

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

    @Override
    public Map<String, String> getHMap(String key) {
        BoundHashOperations boundHashOperations=template.boundHashOps(key);
        return boundHashOperations.entries();
    }
    @Override
    public void setHMap(String key, Map<String, String> map) {
       BoundHashOperations boundHashOperations=template.boundHashOps(key);
       boundHashOperations.putAll(map);
    }
    @Override
    public Set<String> getHSet(String key) {
        BoundSetOperations boundSetOperations=template.boundSetOps(key);
        return boundSetOperations.members();
    }
    @Override
    public void setHSet(String key, Set<String> set) {
        BoundSetOperations boundSetOperations=template.boundSetOps(key);
        boundSetOperations.add(set.toArray());
    }

    @Override
    public void addSortedSet(String key, String value) {
        BoundZSetOperations boundZSetOperations=template.boundZSetOps(key);
        boundZSetOperations.add(value,Math.random()*10);
    }

    @Override
    public Set<String> listSorteSet(String key) {
        BoundZSetOperations boundZSetOperations=template.boundZSetOps(key);
        return boundZSetOperations.range(1,5);
    }
}
