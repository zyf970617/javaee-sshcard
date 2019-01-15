package com.edu.services;

import java.util.List;

import com.edu.entity.User;

public interface IUserService {

	/**
	 * 用户登录验证
	 * @param name 用户名
	 * @param pass 密码
	 * @return
	 */
	public abstract User findUserByLogin(String name, String pass);
	
	/**
	 * 用户注册
	 * @param user 用户实体
	 * @return
	 */
	public abstract int save(User user);
	
	/**
	 * 根据用户名查找用户数据
	 * @param name
	 * @return
	 */
	public abstract User findUserByName(String name);
	
	/**
	 * 分页显示用户数据
	 * @param page 当前页码
	 * @param size 每页的大小
	 * @return
	 */
	public abstract List<User> findUserByPage(int page, int size);
	
	/**
	 * 返回已经注册的用户数量
	 * @return
	 */
	public abstract int findUserNumber();
	
	/**
	 * 单个删除用户
	 * @return
	 */
	public abstract int deleteUser(int id);
	
	/**
	 * 批量删除用户
	 * @return 
	 */
	public abstract int deleteUserList(int...ids);
	
	/**
	 * 查找指定id集合中的超级管理员的数量
	 * @param ids 集合
	 * @param userType 管理员的类型
	 * @return 查找到的数量集
	 */
	public abstract int findBySuperAdmin(String ids, String userType);
	
	/**
	 * 根据用户id查找用户记录
	 * @param id 用户id
	 * @return
	 */
	public abstract User findUserById(int id);
	
	/**
	 * 修改用户密码
	 * @param u 用户实体
 	 * @return
	 */
	public abstract int updateUserPass(User u);
	
	/**
	 * 返回模糊查找用户的数量
	 * @param condition 模糊查找关键词
	 * @return
	 */
	public abstract int findUserNumberByCondition(String condition);
	
	/**
	 * 返返回模糊查找用户的结果集
	 * @param condition 模糊查找关键词
	 * @return
	 */
	public abstract List<User> findUserByCondition(String condition, int page, int size);
}
