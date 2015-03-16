package com.yao.spring.redis.counter;

import com.yao.spring.redis.RedisBase;
import org.springframework.data.redis.core.BoundValueOperations;

/**
 * Created by root on 15-3-16.
 */
public class Counter extends RedisBase {
    public long incre(String key){
        BoundValueOperations boundValueOperations=template.boundValueOps(key);
        return boundValueOperations.increment(1);
    }


    public Object  get(String key){
        BoundValueOperations boundValueOperations=template.boundValueOps(key);
        return boundValueOperations.get();
    }

}
