package redis;

import com.yao.spring.redis.share.UserActiveClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by yaozb on 15-4-10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/spring-redis.xml")
public class TestUserActive {
    @Autowired
    private UserActiveClient userActiveClient;
    @Test
    public void testActiveUser(){
        String dateKey="20150410";
        for (int i=1;i<10000;i+=2){
            userActiveClient.activeUser(i,dateKey);
        }
        dateKey="20150409";
        for (int i=1;i<10000;i+=4){
            userActiveClient.activeUser(i,dateKey);
        }
        dateKey="20150408";
        for (int i=1;i<10000;i+=6){
            userActiveClient.activeUser(i,dateKey);
        }
    }
    @Test
    public void testTotalCountUsers(){
        String dateKey="20150410";
        System.out.println(userActiveClient.totalCountUsers(dateKey));
    }
    @Test
    public void testContinueCountUsers(){
        String []dateKey=new String[]{"20150408","20150409","20150410"};
        System.out.println(userActiveClient.continueActiveUserCount(dateKey).size());
    }
}
