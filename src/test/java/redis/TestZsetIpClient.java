package redis;

import com.yao.spring.redis.share.ZsetClient;
import com.yao.spring.redis.utils.IpUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yaozb on 15-4-8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/spring-redis.xml")
public class TestZsetIpClient {
    @Autowired
    private ZsetClient zsetClient;
    @Test
    public void testIpToCityId(){
        String cityId =zsetClient.getCityId(IpUtils.ipToInt("47.153.128.0"));
        System.out.println(zsetClient.getCityInfo(cityId));
    }
}
