package org.emnets.dianping.model;

public class Business {
    private String name;
    private int fid;
    private String bid;
    private int uid;
    private long initTime;
    private int avg_price;
    private double lng, lat;
    private String city;
    private String s_photo_url;
    private String rating_s_img_url;
    private int bstate;

    public Business() {
        this.bstate = 0;
    }

    @Override
    public String toString() {
        return String.format("Person[id=%d, uid=%d, bid=%s, starttime=%d]", fid, uid, bid, initTime);
    }

    public int getFid() {
        return this.fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
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

    public int getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(int avg_price) {
        this.avg_price = avg_price;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getS_photo_url() {
        return s_photo_url;
    }

    public void setS_photo_url(String s_photo_url) {
        this.s_photo_url = s_photo_url;
    }

    public String getRating_s_img_url() {
        return rating_s_img_url;
    }

    public void setRating_s_img_url(String rating_s_img_url) {
        this.rating_s_img_url = rating_s_img_url;
    }

    public int getBstate() {
        return bstate;
    }

    public void setBstate(int bstate) {
        this.bstate = bstate;
    }
}
