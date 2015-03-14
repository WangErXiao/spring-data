package redis;

import com.yao.spring.redis.share.FirstClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

}
