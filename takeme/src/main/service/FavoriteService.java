package main.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.dao.HiberManager;
import main.model.Favorite;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Path("/favo")
public class FavoriteService {
	private String fsql = "select f.* from favorite as f, user as u where u.lover = f.uid and u.id = :userId",
			       msql = "select f.* from favorite as f, user as u where u.id = f.uid and u.id = :userId";
	/**
	 * @param userId
	 * @param type
	 * @return
	 */
	@GET
	@Path("/users/{userId}/favorite/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public List print(@PathParam("userId") String userId, @PathParam("type") String type) {
		String sql;
		
		if (type.equals("mine")) {
			sql = msql;
		} else if (type.equals("friend")) {
			sql = fsql;
		} else {
			return null;
		}
		
		Session session = HiberManager.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Favorite.class);
			query.setParameter("userId", userId);
			List users = query.list();
			tx.commit();
			return users;
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
}


