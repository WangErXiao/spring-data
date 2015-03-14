package redis;

import com.yao.spring.redis.share.FirstClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by root on 15-3-14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-redis.xml" })
public class TestFirstClient {
    @Autowired
    private FirstClient firstClient;
    @Test
    public void testSetGetStr(){
        firstClient.setObj("name","wangerxiao");
        System.out.println(firstClient.getObj("name"));
    }

    @Test
    public void testHMap(){
        Map<String,String> xx=new HashMap<String, String>();
        xx.put("name","wangerxiao");
        xx.put("sex","m");
        firstClient.setHMap("erxiao",xx);
        Map<String,String>rel=firstClient.getHMap("erxiao");
        for (Map.Entry entry:rel.entrySet()){
            System.out.println(entry.getKey()+" :" +entry.getValue());
        }
    }
    @Test
    public void testHSet(){
        Set<String> colors=new HashSet<String>(){{
            add("red");
            add("green");
            add("blue");
        }};
        firstClient.setHSet("colors",colors);
        Set<String>rels=firstClient.getHSet("colors");
        for (String r:rels){
            System.out.println(r);
        }
    }

    @Test
    public void testZSet(){
        firstClient.addSortedSet("zset","value1");
        firstClient.addSortedSet("zset","value2");
        firstClient.addSortedSet("zset","value3");
        firstClient.addSortedSet("zset","value4");
        firstClient.addSortedSet("zset","value2");
        firstClient.addSortedSet("zset","value1");

        Set<String> rel=firstClient.listSorteSet("zset");
        for (String r:rel){
            System.out.println(r);
        }
    }
}
