package com.yao.spring.redis;

import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisBase {
    protected StringRedisTemplate template;
    private Long expireTime;
    public RedisBase() {
    }
    public RedisBase(StringRedisTemplate template) {
        this.template = template;
    }
    public StringRedisTemplate getTemplate() {
        return template;
    }
    public void setTemplate(StringRedisTemplate template) {
        this.template = template;
    }
    public Long getExpireTime() {
        return expireTime;
    }
    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
