package com.yao.spring.redis.impl;

import com.yao.spring.redis.RedisBase;
import com.yao.spring.redis.share.ZsetClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundZSetOperations;

import java.util.Set;

/**
 * Created by yaozb on 15-4-8.
 */
public class ZsetClientImpl extends RedisBase implements ZsetClient {
    @Override
    public String getCityId(long ip) {
        BoundZSetOperations<String ,String > boundZSetOperations=template.boundZSetOps("ipToCity");
        Set<String> ips=boundZSetOperations.reverseRangeByScore(ip,ip+255);
        return ips.size()>0?ips.iterator().next():null;
    }
    @Override
    public String getCityInfo(String cityId) {
        if(StringUtils.isBlank(cityId)){
           return null;
        }
        BoundHashOperations<String,String,String>boundHashOperations=template.boundHashOps("cityInfo");
        return boundHashOperations.get(cityId);
    }
}
