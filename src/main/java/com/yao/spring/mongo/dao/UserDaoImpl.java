package com.yao.spring.mongo.dao;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MapReduceOutput;
import com.yao.spring.mongo.model.UserStaticModel;
import com.yao.spring.mongo.model.UserVisitRecord;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by yao on 15/6/16.
 */
@Component
public class UserDaoImpl extends MongoBaseDao implements UserDao {
    public void insertRecord(UserVisitRecord record) {
        getMongoTemplate().insert(record);
    }
    public void statisUserPvUv(String date) {

        String map = "function() {                                        "
                + "   if(this.date=='"+date+"'){                          "
                + "      emit(this.date ,{uv:1,pv:1,userIds:this.userId}) "
                + "     }"
                + "   } ";

        String reduce = "function(key, values) {            "
                + "   var temp = new Array();               "
                + "   var userIds= new Array();             "
                + "   for(i = 0; i < values.length; i++) {  "
                + "      userIds=userIds.concat(values[i].userIds);"
                + "   }        "
                + "   userIds.sort();         "
                + "   for(i = 0; i < userIds.length; i++) {"
                + "         if( userIds[i] == userIds[i+1]) { continue;}"
                + "         temp[temp.length]=userIds[i];"
                + "   }        "
                + "   return {uv:temp.length,pv:userIds.length,userIds:userIds};"
                + " }";

        MapReduceOutput mapReduceOutput = getMongoTemplate().getCollection("userVisitRecord").mapReduce(map,reduce,"tmp",null);
        DBCollection resultColl = mapReduceOutput.getOutputCollection();
        try {
            DBCursor cursor = resultColl.find();
            while (cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                if (dbObject.get("value") != null) {
                    UserStaticModel userStaticModel=new UserStaticModel();
                    userStaticModel.setUv(Math.round((double)((DBObject) dbObject.get("value")).get("uv")));
                    userStaticModel.setPv(Math.round((double) ((DBObject) dbObject.get("value")).get("pv")));
                    List<String>userIds=(List) ((DBObject) dbObject.get("value")).get("userIds");
                    Set<String> idSet=new HashSet<>(userIds);
                    userStaticModel.setUserIds(new ArrayList(idSet));
                    userStaticModel.setDate(date);
                    getMongoTemplate().insert(userStaticModel);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            resultColl.drop();
        }
    }

    public UserStaticModel findStatic(String date) {
        Query query=new Query();
        query.addCriteria(Criteria.where("date").is(date));
        return getMongoTemplate().findOne(query,UserStaticModel.class);
    }
}
