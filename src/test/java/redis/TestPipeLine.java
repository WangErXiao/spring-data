package redis;

import com.alibaba.fastjson.JSONObject;
import com.yao.spring.redis.module.People;
import com.yao.spring.redis.share.FirstClient;
import com.yao.spring.redis.share.PipelineClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaozb on 15-4-6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-redis.xml" })
public class TestPipeLine {
    @Autowired
    private PipelineClient pipelineClient;
    @Test
    public void testQueryAll(){
        String[] ids={"6","7","8","9","10"};
        List<People>r= pipelineClient.queryAll(ids);
        System.out.println(JSONObject.toJSON(r));
        System.out.println("-----------------------OK");
    }
    @Test
    public void testAddAll(){
        List<People> peoples=new ArrayList<People>();
        for (int i=0;i<10;i++){
            People people=new People();
            people.setId(6+i+"");
            people.setName("wang"+i);
            people.setTitle("pro");
            people.setAge(i);
            peoples.add(people);
        }
        pipelineClient.add(peoples);
        System.out.println("-----------------------OK");
    }
}
