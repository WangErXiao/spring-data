package com.yao.spring.mongo.dao;

import com.yao.spring.mongo.model.UserStaticModel;
import com.yao.spring.mongo.model.UserVisitRecord;

/**
 * Created by yao on 15/6/16.
 */
public interface UserDao {

    /**
     * 插入用户访问记录
      * @param record
     */
    void insertRecord(UserVisitRecord record);

    /**
     * 统计用户PV UV
     * @param date
     */
    void statisUserPvUv(String date);

    /**
     * 查询统计结果
     * @param date
     * @return
     */
    UserStaticModel findStatic(String date);

}
