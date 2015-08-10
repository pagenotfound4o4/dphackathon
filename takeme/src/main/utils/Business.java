package main.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Business {
	JSONObject json;
	
	public Business (JSONObject json) {
		this.json = json;
	}
	
	public static List<Business> genBlist(String str) {
		JSONObject jobj = new JSONObject(str);
		JSONArray rlst = jobj.getJSONArray("businesses");
		JSONObject obj;
		List<Business> blst = new ArrayList<Business>();
		for (int i = 0; i < rlst.length(); ++i) {
			obj = rlst.getJSONObject(i);
			blst.add(new Business(obj));
		}
		return blst;
	}
}
