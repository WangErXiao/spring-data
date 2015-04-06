package com.yao.spring.redis.impl;

import com.yao.spring.redis.RedisBase;
import com.yao.spring.redis.module.People;
import com.yao.spring.redis.share.PipelineClient;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;

import java.util.*;

/**
 * Created by yaozb on 15-4-6.
 */
public class PipelineClientImpl extends RedisBase implements PipelineClient {
    @Override
    public void add(final List<People> peoples) {
        template.executePipelined(new RedisCallback<People>() {
            @Override
            public People doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();
                for (People p:peoples){
                    Map<byte[],byte[]> m=new HashMap<byte[], byte[]>();
                    m.put("id".getBytes(),p.getId().getBytes());
                    m.put("name".getBytes(),p.getName().getBytes());
                    m.put("age".getBytes(),(p.getAge()+"").getBytes());
                    m.put("title".getBytes(),p.getTitle().getBytes());
                    connection.hMSet(p.getId().getBytes(),m);
                }
                return null;
            }
        });
    }
    public List<People>queryAll(final String[]ids) {
        final List<People> r=new ArrayList<People>();
        List<Object> obj = template.executePipelined(new RedisCallback<Map<byte[],byte[]>>() {
            @Override
            public Map<byte[],byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();
                for (String id:ids) {
                    connection.hGetAll(id.getBytes());
                }
                return null;
            }
        });
        for (Object o:obj){
            LinkedHashMap<String,String>map=(LinkedHashMap<String, String>)o;
            for (Map.Entry<String,String> m:map.entrySet()){
                People people=new People();
                if(m.getKey().equals("id")){
                    people.setId(m.getValue());
                };
                if(m.getKey().equals("name")){
                    people.setName(m.getValue());
                };
                if(m.getKey().equals("title")){
                    people.setTitle(m.getValue());
                };
                if(m.getKey().equals("age")){
                    people.setAge(Integer.parseInt(m.getValue()));
                };
                r.add(people);
            }
        }
        return r;
    }
}
