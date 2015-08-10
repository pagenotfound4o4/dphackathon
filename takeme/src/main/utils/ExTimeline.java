package main.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.model.Favorite;
import main.model.Timeline;

public class ExTimeline {
	Timeline t;
	Business b;
	
	public ExTimeline(Timeline t, Business b) {
		this.t = t;
		this.b = b;
	}
	
	public static List<ExTimeline> extend(List<Timeline> lst) {
		List<Business> blst = Business.genBlist(getBusinesses(lst));
		List<ExTimeline> elst = new ArrayList<ExTimeline>();
		for (int i = 0; i < lst.size(); ++i) {
			elst.add(new ExTimeline(lst.get(i), blst.get(i)));
		}
		return elst;
	}
	public static String getBusinesses(List<Timeline> list) {
		StringBuilder sb = new StringBuilder();
		
		for (Timeline fav : list) {
			sb.append(fav.getBid());
			sb.append(",");
		}
		return GetByAPI.getBusinesses(sb);
	}
	public int getId() {
		return t.getId();
	}

	public void setId(int id) {
		t.setId(id);
	}

	public int getHid() {
		return t.getHid();
	}

	public void setHid(int hid) {
		t.setHid(hid);
	}

	public int getGid() {
		return t.getGid();
	}

	public void setGid(int gid) {
		t.setGid(gid);
	}

	public String getBid() {
		return t.getBid();
	}

	public void setBid(String bid) {
		t.setBid(bid);
	}

	public int getFlag() {
		return t.getFlag();
	}

	public void setFlag(int flag) {
		t.setFlag(flag);
	}

	public Date getTime() {
		return t.getTime();
	}

	public void setTime(Date time) {
		t.setTime(time);
	}
	
	public double getLat() {
		return b.json.getDouble("latitude");
	}
	public double getLng() {
		return b.json.getDouble("longitude");
	}
	public double getPrice() {
		return b.json.getDouble("avg_price");
	}
	public String getName() {
		return b.json.getString("name");
	}
	public String getBranchName() {
		return b.json.getString("branch_name");
	}
	
	public String getAddress() {
		return b.json.getString("address");
	}
	public String getTelephone() {
		return b.json.getString("telephone");
	}
	public String getCity() {
		return b.json.getString("city");
	}
	
	public String getRating_img_url() {
		return b.json.getString("rating_img_url");
	}
	public String getRating_s_img_url() {
		return b.json.getString("rating_s_img_url");
	}
	public int getAvg_price() {
		return b.json.getInt("avg_price");
	}
	public int getReview_count() {
		return b.json.getInt("review_count");
	}
	public String getBusiness_url() {
		return b.json.getString("business_url");
	}
	public String getPhoto_url() {
		return b.json.getString("photo_url");
	}
	public String getS_photo_url() {
		return b.json.getString("s_photo_url");
	}
}
