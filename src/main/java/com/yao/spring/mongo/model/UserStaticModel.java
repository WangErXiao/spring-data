package com.yao.spring.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by yao on 15/6/16.
 */
@Document
public class UserStaticModel {

    @Id
    private String id;
    private long uv;
    private long pv;
    private String date;

    private List<String>userIds;

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public long getUv() {
        return uv;
    }

    public void setUv(long uv) {
        this.uv = uv;
    }

    public long getPv() {
        return pv;
    }

    public void setPv(long pv) {
        this.pv = pv;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
