package com.edu.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.dao.UserDao;
import com.edu.entity.User;
import com.edu.services.IUserService;

@Service
@Transactional
public class UserService implements IUserService{

	@Resource
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public User findUserByLogin(String name, String pass) {
		return userDao.findUserByLogin(name, pass);
	}

	public int save(User user) {
		return userDao.saveUser(user);
	}

	public User findUserByName(String name) {
		return userDao.findUserByName(name);
	}

	public List<User> findUserByPage(int page, int size) {
		return userDao.findUserByPage(page, size);
	}

	public int findUserNumber() {
		return userDao.findUserNumber();
	}

	public int deleteUser(int id) {
		return userDao.delete(id);
	}

	public int deleteUserList(int...ids) {
		return userDao.deleteUserList(ids);
	}

	public int findBySuperAdmin(String ids, String userType) {
		return userDao.findBySuperAdmin(ids, userType);
	}

	public User findUserById(int id) {
		return userDao.findByUserId(id);
	}

	public int updateUserPass(User u) {
		return userDao.updateUserPass(u);
	}

	public int findUserNumberByCondition(String condition) {
		return userDao.findConditionNumber(condition);
	}

	public List<User> findUserByCondition(String condition,int page, int size) {
		return userDao.findConditionByPage(condition, page, size);
	}
	
}
