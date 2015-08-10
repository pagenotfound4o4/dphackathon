package main.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.model.Favorite;

import org.json.JSONObject;

public class ExFavorite {
	Favorite f;
	Business b;
	public ExFavorite(Favorite f, Business b) {
		this.f = f;
		this.b = b;
	}
	
	public static List<ExFavorite> extend(List<Favorite> flst) {
		List<Business> blst = Business.genBlist(getBusinesses(flst));
		List<ExFavorite> elst = new ArrayList<ExFavorite>();
		for (int i = 0; i < flst.size(); ++i) {
			elst.add(new ExFavorite(flst.get(i), blst.get(i)));
		}
		return elst;
	}
	
	public static String getBusinesses(List<Favorite> list) {
		StringBuilder sb = new StringBuilder();
		
		for (Favorite fav : list) {
			sb.append(fav.getBid());
			sb.append(",");
		}
		return GetByAPI.getBusinesses(sb);
	}
	
	public Date getInitTime() {
		return f.getInitTime();
	}

	public void setInitTime(Date initTime) {
		f.setInitTime(initTime);
	}

	public int getFid() {
		return f.getId();
	}

	public int getUid() {
		return f.getUid();
	}

	public String getBid() {
		return f.getBid();
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

