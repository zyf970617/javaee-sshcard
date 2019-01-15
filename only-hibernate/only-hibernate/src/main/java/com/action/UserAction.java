package com.action;

import java.util.Date;
import com.dao.ThemeDao;
import com.entity.Theme;
import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	
	//用户信息实体
	private User user;  
	
	//错误提示
	private String msg;
	

	public String userLogin() throws Exception{
		ThemeDao themeDao = new ThemeDao();
		Theme theme = new Theme("这是标题","这是内容","徐森威",4,new Date());
		themeDao.save(theme);
	
		String forward=null;
		if(!"徐森威".equals(user.getName())){
			msg = "用户不存在";
			forward = "error";
		} else if (!"123456".equals(user.getPassword())) {
			msg = "用户名或密码错误";
		    forward = "error";
		} else {
			forward = "success";
		}
		return forward;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
