package com.edu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="userName")
	private String userName;
	
	@Column(name="userPassword")
	private String userPassword;
	
	@Column(name="userRealName")
	private String userRealName;
	
	/**
	 * 用户类型：普通管理员/超级管理员
	 */
	@Column(name="userType")
	private String userType;
	
	public User(){}
	
	public User(String userName, String userPassword,String userRealName){
		this.userName=userName;
		this.userPassword=userPassword;
		this.userRealName=userRealName;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getUserRealName() {
		return userRealName;
	}
	
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}