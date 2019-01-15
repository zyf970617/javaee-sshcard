package com.edu.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.edu.entity.User;
import com.edu.services.IUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class UserAction extends ActionSupport{
	
	@Resource
	private IUserService userService;
	
	//用户信息实体
	private User user;  
	
	private String veriCode;

	//错误提示
	private String msg;
	
	//确认密码
	private String re_password;
	
	//旧密码
	private String old_password;
	
	//用户搜索关键词
	private String content;
	
	//取得用户的信息实体
	private List<User> userList;
	
	//action执行成功返回success
	private final String SUCCESS = "success";
	
	//action执行失败返回error
	private final String ERROR = "error";
	
	//每页默认显示的数量为10
	private final int SIZE = 10;
	
	//当前页码
	private int page;
	
	//查找到的用户数量
	private int sum;
	
	//id的集合
	private String ids;
	
	/**
	 * 进入页面的类型
	 * 1：用户管理子系统
	 * 2：名片管理子系统
	 */
	private String type;
	
	/**
	 * 分页显示用户信息
	 * sum表示所有注册的用户数量
	 * @return
	 */
	public String userList() {	
		if (content!=null&&content.length()>0) {
			sum = userService.findUserNumberByCondition(content);
			userList = userService.findUserByCondition(content, page, SIZE);
		} else {
			sum = userService.findUserNumber();
			userList = userService.findUserByPage(page, SIZE);
		}
		return SUCCESS;
	}
	
	/**
	 * 批量删除用户
	 * @return
	 */
	public String deleteUsers() {
		Map<String,Object> output = ActionContext.getContext().getSession();
		String name = (String) output.get("user_name");
		//当前登录用户的用户数据
		User u1 = userService.findUserByName(name);
		List<Integer> intList  = JSON.parseArray(ids,Integer.TYPE);
		String ss = intList.toString();
		//待删除的id集合中含有超级管理员的数量
		int cot = userService.findBySuperAdmin(ss.substring(1,ss.length()-1), "超级管理员");
		int[] d = new int[intList.size()];
		for(int i = 0;i<intList.size();i++){
			//待删除的id集合中是否包含自己
			if (intList.get(i).equals(u1.getId())) {
				cot = -1;
			}
		    d[i] = intList.get(i);
		}
		if (cot==0) {
			userService.deleteUserList(d);
		} else if (cot==-1){
			return "error1";
		} else if (cot>0) {
			return "error2";
		}
		return SUCCESS;
	}
	
	//单个删除用户
	public String deleteUser() {
		Map<String,Object> output = ActionContext.getContext().getSession();
		String name = (String) output.get("user_name");
		//当前登录用户的用户数据
		User u1 = userService.findUserByName(name);
		User u2 = userService.findUserById(user.getId());
		if (u1.getId()==user.getId()) {  //删除自己
			return "error1";
		} else if (u2.getUserType().equals("超级管理员")) {   //删除超级管理员
			return "error2";
		} else {
			userService.deleteUser(user.getId());
			return SUCCESS;
		}
	}
	
	/**
	 * 修改用户密码
	 * @return
	 */
	public String changePass() {
		User u = userService.findUserById(user.getId());
		if (!re_password.equals(user.getUserPassword())) {
			return "error1";
		} else if (!u.getUserPassword().equals(old_password)) {
			return "error2";
		} else {
			u.setUserPassword(user.getUserPassword());
			userService.updateUserPass(u);
			return SUCCESS;
		}
	}
	
	/**
	 * 验证用户是否登录
	 * 用户登录后将用户登录信息存储的session的key：user_name中
	 * 通过判断user_name中是否有值来判断用户是否登录
	 * @return
	 */
	public String checkIn() {
		String forward = null;
		Map<String,Object> output = ActionContext.getContext().getSession();
		String name = (String) output.get("user_name");
		if (name==null) {
			msg = "您还未登录，请先登录";
			forward = ERROR;
		} else {
			if ("1".equals(type)) {
				forward = "userPage";
			} else {
				forward = "cardPage";
			}
		}
		return forward;
	}

	/**
	 * 用户登录，登录后将用户名存储到session中
	 * @return
	 * @throws Exception
	 */
	public String userLogin() throws Exception{
		String forward=null;
		Map<String,Object> output = ActionContext.getContext().getSession();
		User u = userService.findUserByLogin(user.getUserName(), user.getUserPassword());
		if (u==null) {
			msg = "用户名或密码错误";
			forward = ERROR;
		} else {
			output.put("user_name", u.getUserName());
			forward = SUCCESS;
		}
		return forward;
	}
	
	/**
	 * 用户注册，veriCode是用户提交的验证码
	 * v_code是存储在session中的正确的验证码
	 * @return
	 */
	public String userRegister() {
		Map<String,Object> output = ActionContext.getContext().getSession();
		String ve_code = (String)output.get("v_code");
		User u = userService.findUserByName(user.getUserName());
		if (ve_code==null || !ve_code.equalsIgnoreCase(veriCode)) {
			msg = "验证码错误";
			return ERROR;
		}
		if (u==null) {
			user.setUserType("普通管理员");
			userService.save(user);
			return SUCCESS;
		} else {
			msg = "此用户已存在";
			return ERROR;
		}
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRe_password() {
		return re_password;
	}

	public void setRe_password(String re_password) {
		this.re_password = re_password;
	}

	public String getVeriCode() {
		return veriCode;
	}

	public void setVeriCode(String veriCode) {
		this.veriCode = veriCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOld_password() {
		return old_password;
	}

	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
