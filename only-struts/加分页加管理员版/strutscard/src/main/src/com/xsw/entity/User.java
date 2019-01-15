package com.xsw.entity;

/**
 * @auther 徐森威
 * @date 2018/4/17
 */
public class User {

    private int userId;

    private String userName;

    private String userPassword;

    private String userRealName;

    private String userType;

    public User() {}

    public User(String userName, String userPassword, String userRealName,String userType) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRealName = userRealName;
        this.userType = userType;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRealName() {
        return this.userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
