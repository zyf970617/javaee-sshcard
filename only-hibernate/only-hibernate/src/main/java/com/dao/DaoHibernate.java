package com.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.util.HibernateUtil;

public class DaoHibernate<T> implements IBaseDao<T>{
	
	private Transaction ts = null;
	
	private Session session = null;
	
	private T t;
	
	public DaoHibernate (T t){
		this.t = t;
    }

	public int insert(T o) {
		try {
			session = HibernateUtil.getThreadLocalSession();
			ts = session.beginTransaction();
			session.save(o);
			ts.commit();
		} catch(Exception e) {
			if (ts!=null) {
				ts.rollback();
			}
		} finally {
			HibernateUtil.closeSession();
		}
		return 1;
	}

	public int insertList(List<T> list) {
		for (T t: list) {
			insert(t);
		}
		return list.size();
	}

	public int update(T o) {
		try {
			session = HibernateUtil.getThreadLocalSession();
			ts = session.beginTransaction();
			session.update(o);
			ts.commit();
		} catch(Exception e) {
			if (ts!=null) {
				ts.rollback();
			}
		} finally {
			HibernateUtil.closeSession();
		}
		return 1;
	}

	public int deleteList(int...ids) {
		for (int id : ids) {
			delete(id);
		}
		return ids.length;
	}

	public int delete(T o) {
		try {
			session = HibernateUtil.getThreadLocalSession();
			ts = session.beginTransaction();
			session.delete(o);
			ts.commit();
		} catch(Exception e) {
			if (ts!=null) {
				ts.rollback();
			}
		} finally {
			HibernateUtil.closeSession();
		}
		return 1;
	}

	public int delete(int id) {	
		try {
			session = HibernateUtil.getThreadLocalSession();
			ts = session.beginTransaction();
			session.delete(session.load(t.getClass(), id));
			ts.commit();
		} catch(Exception e) {
			if (ts!=null) {
				ts.rollback();
			}
		} finally {
			HibernateUtil.closeSession();
		}
		return 1;
	}

	public T findById(int id) {
		T t1 = null;
		try {
			session = HibernateUtil.getThreadLocalSession();
			ts = session.beginTransaction();
			t1 = (T)session.get(t.getClass(), id);
			ts.commit();
		} catch(Exception e) {
			if (ts!=null) {
				ts.rollback();
			}
		} finally {
			HibernateUtil.closeSession();
		}
		return t1;
	}

	public T findOne(String sql, String[] params) {
		T t1 = null;
		try {
			SQLQuery sqlQuery = getSqlQuery(sql, params);
			t1 = (T)sqlQuery.uniqueResult();
			ts.commit();
		} catch(Exception e) {
			if (ts!=null) {
				ts.rollback();
			}
		} finally {
			HibernateUtil.closeSession();
		}
		return t1;
	}

	public List<T> find(String sql, String[] params) {
		List<T> list = null;
		try {
			SQLQuery sqlQuery = getSqlQuery(sql, params);
			list = sqlQuery.list();
			ts.commit();
		} catch (Exception e) {
			if (ts!=null) {
				ts.rollback();
			}
		} finally {
			HibernateUtil.closeSession();
		}
		return list;
	}

	public List<T> findPage(String sql, String[] params, int page, int size) {
		List<T> list = null;
		try {
			SQLQuery sqlQuery = getSqlQuery(sql, params);
			sqlQuery.setFirstResult((page-1)*size);
			sqlQuery.setMaxResults(size);
			list = sqlQuery.list();
			ts.commit();
		} catch (Exception e) {
			if (ts!=null) {
				ts.rollback();
			}
		} finally {
			HibernateUtil.closeSession();
		}
		return list;
	}

	public int getCount(String sql, String[] params) {
		int sum = 0;
		try {
			SQLQuery sqlQuery = getSqlQuery(sql, params);
			sum = Integer.parseInt(sqlQuery.iterate().next().toString());
			ts.commit();
		} catch (Exception e) {
			if (ts!=null) {
				ts.rollback();
			}
		} finally {
			HibernateUtil.closeSession();
		}
		return sum;
	}

	public List<T> findByFields(String sql, String[] fields, String condition) {
		String findSql = sql;
		if (fields!=null && condition!=null && fields.length>0 && !condition.equals("")) {
			findSql = findSql + " where 1=1 and (";
			for (int i=0; i<fields.length-1; i++) {
				findSql += fields[i] + " like '%" + condition + "%' or ";
			}
			findSql += fields[fields.length-1] + " like '%" + condition + "%')";
		}
		List<T> list = null;
		try {
			SQLQuery sqlQuery = getSqlQuery(findSql,null);
			list = sqlQuery.list();
			ts.commit();
		} catch (Exception e) {
			if (ts!=null) {
				ts.rollback();
			}
		} finally {
			HibernateUtil.closeSession();
		}
		return list;
	}
	
	private SQLQuery getSqlQuery(String sql, String[] params) {
		session = HibernateUtil.getThreadLocalSession();
		ts = session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.addEntity(t.getClass());
		if (params!=null) {
			for (int i=0; i<params.length; i++) 
				sqlQuery.setParameter(i, params[i]);
		}
		return sqlQuery;
	}

}
