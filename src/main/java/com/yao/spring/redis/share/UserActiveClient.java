package com.yao.spring.redis.share;

import java.util.List;
import java.util.Set;

/**
 * Created by yaozb on 15-4-10.
 */
public interface UserActiveClient {
    //用户某天活跃
    public void activeUser(long userId,String dateKey);
    //判断某天用户是否活跃
    public boolean isActiveUser(long userId,String dateKey);
    //某天活跃的人数
    public long totalCountUsers(String dateKey);
    //某天活跃的人数id
    public Set<Long> getActiveUsers(String dateKey);

    //连续活跃的人数
    public List<Integer> continueActiveUserCount(String... dateKeys);
}
