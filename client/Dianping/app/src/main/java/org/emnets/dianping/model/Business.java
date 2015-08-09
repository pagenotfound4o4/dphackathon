package org.emnets.dianping.model;

public class Business {
    private int id;
    private String bid;
    private int uid;
    private long initTime;

    public Business(int id, String bid, int uid, long initTime) {
        this.id = id;
        this.bid = bid;
        this.uid = uid;
        this.initTime = initTime;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBid() {
        return this.bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public long getInitTime() {
        return this.initTime;
    }

    public void setInitTime(long initTime) {
        this.initTime = initTime;
    }

    @Override
    public String toString() {
        return String.format("Person[id=%d, uid=%d, bid=%s, starttime=%d]", id, uid, bid, initTime);
    }
}
