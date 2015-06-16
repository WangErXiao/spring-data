package com.yao.spring.mongo.dao;

import com.alibaba.fastjson.JSONObject;
import com.yao.spring.mongo.model.UserStaticModel;
import com.yao.spring.mongo.model.UserVisitRecord;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

/**
 * Created by yao on 15/6/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-mongo.xml" })
public class UserDaoImplTest extends TestCase {

    @Autowired
    private UserDao userDao;

    @Test
    public void testInsertRecord() throws Exception {

        String date="2015-06-16";
        for (int i=0;i<10000;i++){
            UserVisitRecord userVisitRecord=new UserVisitRecord();
            userVisitRecord.setDate(date);
            userVisitRecord.setUserId(new Random().nextInt(100) + "");
            userDao.insertRecord(userVisitRecord);
        }
    }

    @Test
    public void testStatisUserPvUv() throws Exception {
        String date="2015-06-16";
        userDao.statisUserPvUv(date);
    }

    @Test
    public void testFindStatic() throws Exception {
        String date="2015-06-16";
        UserStaticModel userStaticModel= userDao.findStatic(date);
        System.out.println(JSONObject.toJSONString(userStaticModel));
    }
}