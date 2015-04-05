package redis;

import com.alibaba.fastjson.JSONObject;
import com.yao.spring.redis.module.People;
import com.yao.spring.redis.share.PeopleClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by yaozb on 15-4-5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-redis.xml" })
public class TestPeopleClient {
    @Autowired
    private PeopleClient peopleClient;

    @Test
    public void testAdd(){
        People people=new People(){{
            setId("2");
            setName("wangerxiao2");
            setAge(13);
            setTitle("pro");
        }};
        peopleClient.add(people);
        System.out.println("---------OK");
    }
    @Test
    public void queryPeople(){
        People people=peopleClient.get("3");
        System.out.println(JSONObject.toJSON(people));
        System.out.println("---------OK");

    }
    @Test
    public void queryPeopleByTitle(){
        System.out.println(JSONObject.toJSON(peopleClient.getPeopleByTitle("pro")));
        System.out.println("---------OK");
    }
    @Test
    public void queryPeopleByTitle1(){
        System.out.println(JSONObject.toJSON(peopleClient.getPeopleByTitle1("pro")));
        System.out.println("---------OK");
    }
    @Test
    public void addTempory(){
        People people=new People(){{
            setId("3");
            setName("wangerxiao3");
            setAge(14);
            setTitle("pro");
        }};
        peopleClient.addTemporary(people, 3000, TimeUnit.SECONDS);
        System.out.println("---------OK");
    }
}
