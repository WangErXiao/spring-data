package redis;

import com.yao.spring.redis.counter.Counter;
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
public class TestCounter {
    @Autowired
    private Counter counter;
    @Test
    public void testSetGetStr(){
        new Thread(){
            @Override
            public void run() {
                for (int i=0;i<100000;i++){
                   counter.incre("counter");
                }
            }

            {

        }}.start();
        new Thread(){
            @Override
            public void run() {
                for (int i=0;i<100000;i++){

                    counter.incre("counter");
                }
            }

            {

            }}.start();
        for (int i=0;i<100000;i++){
            System.out.println(counter.incre("counter"));
        }
        System.out.println("aaaaaaa");
    }

}
