package main.dao;

import java.util.List;

import main.model.Favorite;
import main.utils.BindRet;
import main.utils.ExFavorite;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BindDao {
	public BindRet bind(int uid, int binding) {
		Session session = HiberManager.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery("update user set lover = :binding where id = :uid");
			query.setParameter("uid", uid);
			query.setParameter("binding", binding);
			int n = query.executeUpdate();
			query.setParameter("uid", binding);
			query.setParameter("binding", uid);
			n += query.executeUpdate();
			if (n != 2) {
				tx.rollback();
				return new BindRet(-2, "no such user or already binding", -1);
			}
			tx.commit();
			return new BindRet(0, "OK", binding);
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			return new BindRet(-1, e.getMessage(), -1);
		} finally {
			session.close();
		}
	}
}
