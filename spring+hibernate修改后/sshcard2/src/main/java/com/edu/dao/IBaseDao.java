package com.edu.dao;

import java.util.List;

public interface IBaseDao<T> {
	
	/**
	 * 插入对象o到数据库内
	 * @param o 实体类
	 * @return 插入的记录数量
	 */
	public int insert(T o);  
	
	/**
	 * 将对象集合插入到数据库内
	 * @param list 对象集合
	 * @return 插入的记录数量
	 */
	public int insertList(List<T> list);   
	
	/**
	 * 利用对象o修改当前记录
	 * @param o 对象o
	 * @return 被修改的记录数量
	 */
	public int update(T o);
	
	/**
	 * 利用id的集合删除该集合中对应的id的记录
	 * @param c 对象类型
	 * @param ids 对应的id集合
	 * @return 删除的记录数量
	 */
	public int deleteList(int...ids);
	
	/**
	 * 从数据库中删除一个记录o
	 * @param o 对象o
	 * @return 删除的记录数量
	 */
	public int delete(T o);
	
	/**
	 * 从数据库中删除一个对应id的记录
	 * @param id 制定的id
	 * @return 删除成功的数量
	 */
	public int delete(int id);
	
	/**
	 * 利用id查找一条记录
	 * @param id 对应的id
	 * @return 查找到的实体集
	 */
	public T findById(int id);
	
	/**
	 * 查询单条记录
	 * @param sql 待执行的sql语句
	 * @param params 需要设置的参数
	 * @return 查找到的实体集
	 */
	public T findOne(String sql, String[] params);
	
	/**
	 * 按条件查找多条记录
	 * @param sql 待执行的sql语句
	 * @param params 需要设置的参数
	 * @return 查找到的实体集
	 */
	public List<T> find(String sql, String[] params);
	
	/**
	 * 分页查找对象
	 * @param sql 待执行的sql语句
	 * @param param 需要设置的参数
	 * @param page 页码
	 * @param size 每页的记录数量
	 * @return 查找到的记录数量
	 */
	public List<T> findPage(String sql, String[] param, int page, int size);
	
	/**
	 * 模糊查找
	 * @param sql 待执行的sql
	 * @param fields 需要查找的字段
	 * @param condition 查询条件
	 * @return 查找到的记录
	 */
	public List<T> findByFields(String sql, String fields[],String[] params, String condition,int page, int size);
	
	/**
	 * 动态执行sql
	 * @return
	 */
	public int ExeSql(String sql, String[] params);
	
}
