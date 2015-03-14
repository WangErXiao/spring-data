package com.yao.spring.redis.share;

import java.nio.channels.SelectionKey;
import java.util.Map;
import java.util.Set;

/**
 * Created by root on 15-3-14.
 */
public interface FirstClient {

    public Object getObj(String key);

    public void setObj(String key,String obj);

    public Map<String,String> getHMap(String key);

    public void setHMap(String key,Map<String,String>map);

    public Set<String> getHSet(String key);

    public void setHSet(String key,Set<String> set);

    public void addSortedSet(String key,String value);

    public Set<String> listSorteSet(String key);
}
