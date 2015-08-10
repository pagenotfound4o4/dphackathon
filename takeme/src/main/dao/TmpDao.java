package main.dao;

import java.util.List;

import main.model.Favorite;
import main.model.Tmp;
import main.utils.BindRet;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TmpDao {
	public BindRet getSetUid(int uid) {
		Session session = HiberManager.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery("update tmp set uid = :uid where uid is NULL");
			query.setParameter("uid", uid);
			int n = query.executeUpdate();
			if (n != 0) {
				tx.commit();
				return new BindRet(1, "wait for binding", -1);
			}
			
			query = session.createSQLQuery("select uid from tmp where uid is not NULL and uid != :uid");
			query.addEntity(Tmp.class);
			query.setParameter("uid", uid);
			List<Tmp> lst = query.list();
			if (query.list().size() != 0) {
				session.createSQLQuery("update tmp set uid = NULL").executeUpdate();
				tx.commit();
				return new BindDao().bind(uid, lst.get(0).getId());
			}
			return new BindRet(2, "cannot bind to yourself", -1);
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			return new BindRet(3, e.getMessage(), -1);
		} finally {
			session.close();
		}
	}
}
