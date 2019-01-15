package com.edu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.edu.entity.Card;

public class DaoHibernate<T> implements IBaseDao<T>{
	
	//注入SessionFactory
	@Resource
	private SessionFactory sessionFactory;
	
	private T t;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	//插入一个实体
	public int insert(T o) {
		return (Integer)sessionFactory.getCurrentSession().save(o);
	}

	//插入一个实体集
	public int insertList(List<T> list) {
		for (T t: list) {
			insert(t);
		}
		return list.size();
	}

	//更新一个实体
	public int update(T o) {
		sessionFactory.getCurrentSession().update(o);
		return 1;
	}

	//批量删除实体
	public int deleteList(int...ids) {
		for (int id : ids) {
			delete(id);
		}
		return ids.length;
	}

	//删除某个实体类
	public int delete(T o) {
		sessionFactory.getCurrentSession().delete(o);
		return 1;
	}

	//根据实体类的主键删除实体类
	public int delete(int id) {	
		Session s = sessionFactory.getCurrentSession();
		s.delete(s.load(t.getClass(),id));
		return 1;
	}

	//根据id查找执行的实体
	public T findById(int id) {
		return (T)sessionFactory.getCurrentSession().get(t.getClass(), id);
	}

	//根据自定义语句查找一个实体
	public T findOne(String sql, String[] params) {
		Session s = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = getSqlQuery(s, sql, params);
		return (T)sqlQuery.uniqueResult();
	}

	//根据自定义语句查找实体集
	public List<T> find(String sql, String[] params) {
		Session s = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = getSqlQuery(s, sql, params);
		List<T> list = sqlQuery.list();
		return list;
	}

	//分页查找实体集
	public List<T> findPage(String sql, String[] params, int page, int size) {
		Session s = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = getSqlQuery(s, sql, params);
		sqlQuery.setFirstResult((page-1)*size);
		sqlQuery.setMaxResults(size);
		List<T> list = sqlQuery.list();
		return list;
	}

	//模糊查找实体集
	public List<T> findByFields(String sql, String[] fields, String[] params, String condition, int page, int size) {
		String findSql = sql;
		if (fields!=null && condition!=null && fields.length>0 && !condition.equals("")) {
			findSql = findSql + " and (";
			for (int i=0; i<fields.length-1; i++) {
				findSql += fields[i] + " like '%" + condition + "%' or ";
			}
			findSql += fields[fields.length-1] + " like '%" + condition + "%')";
		}
		Session s = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = getSqlQuery(s, findSql, params);
		if (page>0) {
			sqlQuery.setFirstResult((page-1)*size);
			sqlQuery.setMaxResults(size);
		}
		List<T> list = sqlQuery.list();
		return list;
	}
	
	//取得初始化后的SQLQuery
	private SQLQuery getSqlQuery(Session session, String sql, String[] params) {
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.addEntity(t.getClass());
		if (params!=null) {
			for (int i=0; i<params.length; i++) 
				sqlQuery.setParameter(i, params[i]);
		}
		return sqlQuery;
	}

	//执行SQL
	public int ExeSql(String sql,String[] params) {
		Session s = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = getSqlQuery(s, sql, params);
		sqlQuery.executeUpdate();
		return 1;
	}

}
