package com.yao.spring.redis.share;

/**
 * Created by root on 15-3-14.
 */
public interface FirstClient {

    public Object getObj(String key);

    public void setObj(String key,String obj);

}
