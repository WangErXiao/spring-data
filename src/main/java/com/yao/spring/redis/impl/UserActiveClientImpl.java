package com.yao.spring.redis.impl;

import com.yao.spring.redis.jedis.RedisUtil;
import com.yao.spring.redis.share.UserActiveClient;
import com.yao.spring.redis.utils.BitSetUtils;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yaozb on 15-4-10.
 */
public class UserActiveClientImpl implements UserActiveClient {
    @Override
    public void activeUser(long userId, String dateKey) {
        Jedis jedis= RedisUtil.getResource();
        try{
            jedis.setbit(dateKey,userId,true);
        }finally {
            RedisUtil.returnResource(jedis);
        }
    }
    @Override
    public boolean isActiveUser(long userId, String dateKey) {
        Jedis jedis= RedisUtil.getResource();
        try{
            return jedis.getbit(dateKey,userId);
        }finally {
            RedisUtil.returnResource(jedis);
        }
    }
    @Override
    public long totalCountUsers(String dateKey) {
        Jedis jedis= RedisUtil.getResource();
        try{
            return jedis.bitcount(dateKey);
        }finally {
            RedisUtil.returnResource(jedis);
        }
    }
    @Override
    public Set<Long> getActiveUsers(String dateKey) {
        Jedis jedis= RedisUtil.getResource();
        try{
            return null;

        }finally {
            RedisUtil.returnResource(jedis);
        }
    }

    @Override
    public List<Integer> continueActiveUserCount(String... dateKeys) {
        Jedis jedis= RedisUtil.getResource();
        try{
            BitSet all = null;
            for (String key:dateKeys){
                 if(jedis.get(key)==null){
                     continue;
                 }
                 BitSet set= BitSetUtils.byteArray2BitSet(jedis.get(key).getBytes());
                 if(all==null){
                     all=set;
                 }
                 System.out.println(set.size());
                 all.and(set);
            }
            List<Integer>list=new ArrayList<Integer>();
            for (int i=0;i<all.size();i++){
                if(all.get(i)){
                    list.add(i);
                }
            }
            return list;
        }finally {
            RedisUtil.returnResource(jedis);
        }
    }
}
