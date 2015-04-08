package com.yao.spring.redis.share;

/**
 * Created by yaozb on 15-4-8.
 */
public interface ZsetClient {

    public String getCityId(long ip);

    public String getCityInfo(String cityId);
}
