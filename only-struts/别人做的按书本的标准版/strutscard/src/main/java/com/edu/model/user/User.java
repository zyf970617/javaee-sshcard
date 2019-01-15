package com.edu.model.user;

public class User{
private Integer userId;
private String userName;
private String userPassword;
private String userRealName;
public User(){}
public User(String userName, String userPassword,String userRealName){

this.userName=userName;
this.userPassword=userPassword;
this.userRealName=userRealName;
}
public Integer getUserId() {
	return userId;
}
public void setUserId(Integer userId) {
	this.userId = userId;
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

}