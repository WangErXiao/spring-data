package com.yao.spring.redis.impl;

import com.yao.spring.redis.RedisBase;
import com.yao.spring.redis.module.People;
import com.yao.spring.redis.share.PeopleClient;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BulkMapper;
import org.springframework.data.redis.core.query.SortQuery;
import org.springframework.data.redis.core.query.SortQueryBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by yaozb on 15-4-5.
 */
public class PeopleClientImpl extends RedisBase implements PeopleClient  {
    private static final String PREFIX = "people:";
    private static final String PREFIX_PEOPLES_TITLE = PREFIX + "title:";
    @Override
    public List<People> getPeopleByTitle(String title) {
        BoundSetOperations<String,String>operations=template.boundSetOps(PREFIX_PEOPLES_TITLE+title);
        Set<String> sets=operations.members();
        List<People>peoples=new ArrayList<People>();
        for(String id:sets){
            peoples.add(get(id));
        }
        return peoples;
    }
    @Override
    public List<People> getPeopleByTitle1(String title) {
        String key = PREFIX_PEOPLES_TITLE+ title;
        String dkey = PREFIX + "*->";
        SortQuery<String> query = SortQueryBuilder.sort(key).by(dkey + "").alphabetical(true)
                .get(dkey + "id").get(dkey + "name").get(dkey + "title")
                .get(dkey + "age").build();
        BulkMapper<People, String> bkm = new BulkMapper<People, String>() {
            @Override
            public People mapBulk(List<String> list) {
                System.out.println("------------------");
                Iterator<String> it = list.iterator();
                People people=new People();
                people.setId(it.next());
                people.setName(it.next());
                people.setTitle(it.next());
                String age=it.next();
                people.setAge(age == null ? null : Integer.parseInt(age));
                return people;
            }
        };
            return template.sort(query,bkm);
    }

    @Override
    public void add(People people) {
        BoundHashOperations<String,String,String> ops=template.boundHashOps(PREFIX + people.getId());
        HashMap<String,String> map=new HashMap<String, String>();
        map.put("id",people.getId());
        map.put("name",people.getName());
        map.put("title",people.getTitle());
        map.put("age",people.getAge().toString());
        ops.putAll(map);
        if(people.getTitle()!=null){
            BoundSetOperations<String,String> setOperations=template.boundSetOps(PREFIX_PEOPLES_TITLE+people.getTitle());
            setOperations.add(people.getId());
        }
    }
    public People get(String peopleId){
        BoundHashOperations<String,String,String> ops=template.boundHashOps(PREFIX + peopleId);
        People people=new People();
        people.setId(peopleId);
        people.setName(ops.get("name"));
        people.setTitle(ops.get("title"));
        people.setAge(ops.get("age") == null ? null : Integer.parseInt(ops.get("age")));
        return people;
    }

    @Override
    public void addTemporary(People people,int time,TimeUnit timeUnit) {
        BoundHashOperations<String,String,String> ops=template.boundHashOps(PREFIX + people.getId());
        HashMap<String,String> map=new HashMap<String, String>();
        map.put("id",people.getId());
        map.put("name",people.getName());
        map.put("title",people.getTitle());
        map.put("age",people.getAge().toString());
        ops.putAll(map);
        if(people.getTitle()!=null){
            BoundSetOperations<String,String> setOperations=template.boundSetOps(PREFIX_PEOPLES_TITLE+people.getTitle());
            setOperations.add(people.getId());
        }
        ops.expire(time,timeUnit);
    }

}
