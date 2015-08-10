package main.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import main.model.Favorite;
import main.model.Timeline;
import main.utils.ConfirmRet;
import main.utils.ExFavorite;
import main.utils.ExTimeline;

public class TimelineDao {
	public int saveTimeline(Timeline timeline) {
		Session session = HiberManager.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(timeline);
			
			tx.commit();
			return 1;
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return 0;
	}
	
	public List<Timeline> findByHid(Integer hid, Integer bid) {
		Session session = HiberManager.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Timeline> list = session.createQuery(
					"from Timeline tl where tl.hid = " + hid + " and tl.bid = " + bid + " order by tl.time desc").list();
			tx.commit();
			return list;
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	public List<Timeline> findByGid(Integer gid) {
		Session session = HiberManager.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Timeline> list = session.createQuery(
					"from Timeline tl where tl.gid = " + gid + " order by tl.time desc").list();
			tx.commit();
			return list;
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	public ConfirmRet confirm(String gid, String bid) {
		String sql = "update timeline set flag = 1 where gid = :gid and bid = :bid and flag = 0";
		Session session = HiberManager.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Timeline.class);
			query.setParameter("gid", gid);
			query.setParameter("bid", bid);
			query.executeUpdate();
			tx.commit();
			return new ConfirmRet(0, "OK", 0);
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			return new ConfirmRet(-1, e.getMessage(), 0);
		} finally {
			session.close();
		}
	}
	
	public List<ExTimeline> getAll(String id) {
		String sql = "select * from timeline where hid = :id or gid = :id";
		Session session = HiberManager.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Timeline.class);
			query.setParameter("id", id);
			List<ExTimeline> favos = ExTimeline.extend(query.list());
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
