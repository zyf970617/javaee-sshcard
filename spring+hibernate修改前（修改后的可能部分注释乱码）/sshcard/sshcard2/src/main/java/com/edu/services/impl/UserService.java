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

	@Override
	public User findUserByLogin(String name, String pass) {
		return userDao.findUserByLogin(name, pass);
	}

	@Override
	public int save(User user) {
		return userDao.saveUser(user);
	}

	@Override
	public User findUserByName(String name) {
		return userDao.findUserByName(name);
	}

	@Override
	public List<User> findUserByPage(int page, int size) {
		return userDao.findUserByPage(page, size);
	}

	@Override
	public int findUserNumber() {
		return userDao.findUserNumber();
	}

	@Override
	public int deleteUser(int id) {
		return userDao.delete(id);
	}

	@Override
	public int deleteUserList(int...ids) {
		return userDao.deleteUserList(ids);
	}

	@Override
	public int findBySuperAdmin(String ids, String userType) {
		return userDao.findBySuperAdmin(ids, userType);
	}

	@Override
	public User findUserById(int id) {
		return userDao.findByUserId(id);
	}

	@Override
	public int updateUserPass(User u) {
		return userDao.updateUserPass(u);
	}

	@Override
	public int findUserNumberByCondition(String condition) {
		return userDao.findConditionNumber(condition);
	}

	@Override
	public List<User> findUserByCondition(String condition,int page, int size) {
		return userDao.findConditionByPage(condition, page, size);
	}
	
}
