package com.shopmanage.entity;

public class Session {
    private Integer uid;
    private String sid;

    public Session() {
    }

    public Session(Integer uid, String sid) {
        this.uid = uid;
        this.sid = sid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {

        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Session{" +
                "uid=" + uid +
                ", sid='" + sid + '\'' +
                '}';
    }
}
