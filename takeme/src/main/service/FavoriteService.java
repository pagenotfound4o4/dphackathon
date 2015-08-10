package main.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.dao.FavoriteDao;
import main.dao.HiberManager;
import main.model.Favorite;
import main.utils.ExFavorite;
import main.utils.WGS2GCJ;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;

@Path("/favo")
public class FavoriteService {
	private FavoriteDao fdao;
	public FavoriteService() {
		this.fdao = new FavoriteDao();
	}
	
	@GET
	@Path("/users/{userId}/favorite/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ExFavorite> favo(@PathParam("userId") String userId, @PathParam("type") String type) {
		if (type.equals("mine")) {
			return fdao.favoMine(userId);
		} else if (type.equals("friend")) {
			return fdao.favoFriend(userId);
		} else {
			return null;
		}
	}
	
	
	@GET
	@Path("/users/{userId}/favorite/{type}/filter/price/{min}/{max}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ExFavorite> favoFilterTime(@PathParam("userId") String userId, @PathParam("type") String type, 
										   @PathParam("min") String min, @PathParam("max") String max) {
		int pmin = Integer.parseInt(min), pmax = Integer.parseInt(max);
		List<ExFavorite> lst = favo(userId, type), retlst = new ArrayList<ExFavorite>();
		for (ExFavorite f : lst) {
			int val = f.getAvg_price();
			if (val >= pmin && val <= pmax) retlst.add(f);
		}
		return retlst;
	}
	
	@GET
	@Path("/users/{userId}/favorite/{type}/sort/time/{order}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ExFavorite> favoSortTime(@PathParam("userId") String userId, @PathParam("type") String type, 
										 @PathParam("order") String order) {
		List<ExFavorite> lst = favo(userId, type);
		Collections.sort(lst, new CompTime(((order != null && order.equals("asc"))?1:-1)));
		return lst;
	}
	
	@GET
	@Path("/users/{userId}/favorite/{type}/sort/price/{order}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ExFavorite> favoSortPrice(@PathParam("userId") String userId, @PathParam("type") String type, 
										  @PathParam("order") String order) {
		List<ExFavorite> lst = favo(userId, type);
		Collections.sort(lst, new CompPrice(((order != null && order.equals("asc"))?1:-1)));
		return lst;
	}
	
	@GET
	@Path("/users/{userId}/favorite/{type}/sort/location/{lng}/{lat}/{order}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ExFavorite> favoSortLocation(@PathParam("userId") String userId, @PathParam("type") String type, 
											 @PathParam("lng") String lng, @PathParam("lat") String lat, @PathParam("order") String order) {
		List<ExFavorite> lst = favo(userId, type);
		Collections.sort(lst, new CompLocation(lng, lat, ((order != null && order.equals("asc"))?1:-1)));
		return lst;
	}
	
	
}
class CompTime implements Comparator<ExFavorite> {
	private int isAsc;
	CompTime(int asc) { isAsc = asc; }
	@Override
	public int compare(ExFavorite o1, ExFavorite o2) {
		return isAsc * o1.getInitTime().compareTo(o2.getInitTime());
	}
}

class CompPrice implements Comparator<ExFavorite> {
	private int isAsc;
	CompPrice(int asc) { isAsc = asc; }
	@Override
	public int compare(ExFavorite o1, ExFavorite o2) {
		return (int) (isAsc * (o1.getPrice() - o2.getPrice()));
	}
}
class CompLocation implements Comparator<ExFavorite> {
	private double lat, lng;
	private int isAsc;
	/**
	 * we assume (lng, lat) are GaoDe coordination
	 * */
	CompLocation(String lng, String lat, int asc) {
		this.lat = Double.valueOf(lat);
		this.lng = Double.valueOf(lng);
		this.isAsc = asc;
	}
	@Override
	public int compare(ExFavorite o1, ExFavorite o2) {
		return isAsc * (cmpLoc(o1.getLat(), o1.getLng()) - cmpLoc(o2.getLat(), o2.getLng()));
	}
	private int cmpLoc(double lat, double lng) {
		return WGS2GCJ.getDistance(lat, lng, this.lat, this.lng);
	}
}


