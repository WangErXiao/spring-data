package com.yao.spring.redis.share;

import com.yao.spring.redis.module.People;

import java.util.List;

/**
 * Created by yaozb on 15-4-6.
 */
public interface PipelineClient {
    public void add(List<People> peoples);
    public List<People>queryAll(String []ids);
}
