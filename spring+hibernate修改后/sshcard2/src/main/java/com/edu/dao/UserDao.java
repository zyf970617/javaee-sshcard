package com.edu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edu.entity.Card;
import com.edu.entity.User;

@Repository
public class UserDao extends DaoHibernate<User>{
		
	/**
	 * 将Class的类型统计注入
	 */
	public UserDao() {
		super.setT(new User());
	}
	
	/**
	 * 添加新的用户
	 * @param user 新的用户数据
	 * @return
	 */
	public int saveUser(User user) {
		return this.insert(user);
	}
	
	/**
	 * 根据用户名和密码查找用户
	 * @param name 用户名
	 * @param pass 密码
	 * @return
	 */
	public User findUserByLogin(String name, String pass) {
		String sql = "select * from user where userName = ? and userPassword = ?";
		return this.findOne(sql, new String[]{name,pass});
	}
	
	/**
	 * 根据用户名查找数据
	 * @param name 用户名
	 * @return
	 */
	public User findUserByName(String name) {
		String sql = "select * from user where userName = ?";
		return this.findOne(sql, new String[]{name});
	}
	
	/**
	 * 分页显示用户数据
	 * @param page 当前页码
	 * @param size 每页显示的数量
	 * @return
	 */
	public List<User> findUserByPage(int page, int size) {
		String sql = "select * from user";
		return this.findPage(sql, null, page, size);
	}
	
	/**
	 * 查询用户数量，可以用select count(*) 代替
	 * @return
	 */
	public int findUserNumber() {
		String sql = "select * from user";
		return this.find(sql, null).size();
	}
	
	/**
	 * 根据id删除用户
	 * @param id 用户id
	 * @return
	 */
	public int deleteUser(int id) {
		return this.delete(id);
	}
	
	/**
	 * 批量删除用户
	 * @param ids id的集合
	 * @return
	 */
	public int deleteUserList(int...ids) {
		return this.deleteList(ids);
	}
	
	/**
	 * 查找执行集合中超级管理员的数量
	 * @param ids id的集合
	 * @param type 管理员的类型
	 * @return 返回查找到的记录数量
	 */
	public int findBySuperAdmin(String ids,String type) {
		String sql = "select * from user where userType = ? and id in ("+ids+")";
		return this.find(sql, new String[]{type}).size();
	}
	
	/**
	 * 根据用户id返回用户数据
	 * @param id 用户id
	 * @return
	 */
	public User findByUserId(int id) {
		return this.findById(id);
	}
	
	/**
	 * 修改用户密码
	 * @param u 用户实体数据
	 * @return
	 */
	public int updateUserPass(User u) {
		return this.update(u);
	}
	
	/**
	 * 模糊查找用户数据
	 * @param condition 模糊查询的关键词
	 * @param page 页码
	 * @param size 每页的数量
	 * @return
	 */
	public List<User> findConditionByPage(String condition, int page, int size) {
		String sql = "select * from user where 1=1 ";
		return this.findByFields(sql, new String[]{"userName","userType"},null, condition, page, size);
	}
	
	/**
	 * 取得模糊查询的用户数量
	 * @param condition 模糊查询的关键词
	 * @return
	 */
	public int findConditionNumber(String condition) {
		String sql = "select * from user where 1=1 ";
		List<User> li =  this.findByFields(sql, new String[]{"userName","userType"},null, condition, 0, 10);
		return li.size();
	}
	
}
