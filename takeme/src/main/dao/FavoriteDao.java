package main.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.model.Favorite;
import main.utils.Business;
import main.utils.ExFavorite;
import main.utils.GetByAPI;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;

public class FavoriteDao {
	private String fsql = "select f.* from favorite as f, user as u where u.lover = f.uid and u.id = :userId order by f.init_time desc",
			msql = "select f.* from favorite as f, user as u where u.id = f.uid and u.id = :userId order by f.init_time desc";

	public List<ExFavorite> favoMine(String userId) {
		return favo(msql, userId);
	}
	public List<ExFavorite> favoFriend(String userId) {
		return favo(fsql, userId);
	}
	
	private List<ExFavorite> favo(String sql, String userId) {
		Session session = HiberManager.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Favorite.class);
			query.setParameter("userId", userId);
			List<ExFavorite> favos = ExFavorite.extend(query.list());
			tx.commit();
			return favos;
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	
	
}
