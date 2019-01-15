package com.xsw.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xsw.dao.UserDao;
import com.xsw.entity.Card;
import com.xsw.entity.User;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author 徐森威
 * @date 2018/4/17
 */
@ParentPackage("struts-default")
@Namespace(value = "/xsw")
public class UserAction extends ActionSupport {

    private static final String USER_JUMP  = "user";

    private static final String CARD_JUMP  = "card";

    private User user;

    private String jumpTo;

    private String msg;

    private String type;

    private String start;

    private String key;

    private String ids;

    private UserDao userDao = new UserDao();

    public UserAction() {}

    public UserAction(User user, String jumpTo, String msg) {
        this.user = user;
        this.jumpTo = jumpTo;
        this.msg = msg;
    }

    @Action(
            value = "login",
            results = {
                    @Result(name = "success",location = "/index.jsp",type = "redirect"),
                    @Result(name = "error",location = "/login.jsp",type = "redirect")
            }
    )
    public String userLogin() {
        Map<String,Object> output = ActionContext.getContext().getSession();
        User u = userDao.find(user.getUserName());
        if (u==null || !u.getUserPassword().equals(user.getUserPassword())) {
            output.put("error_msg", "用户名不存在或密码错误，登录失败");
            return "error";
        } else {
            output.put("user_name",user.getUserName());
            output.remove("error_msg");
            return "success";
        }
    }

    @Action(
            value = "userList",
            results = {
                    @Result(name = "success",location = "../user/userManage.jsp",type = "redirect")
            }
    )
    public String userList() {
        if (type==null || !type.equals("search")) {
            getList("0", Integer.parseInt(this.start));
        } else {
            getSearch(key,"0", Integer.parseInt(this.start));
        }
        return "success";
    }

    @Action(
            value = "submitForUpdate",
            results = {
                    @Result(name = "success",location = "/user/userManage.jsp",type = "redirect"),
            }
    )
    public String submitForUpdate() {
        userDao.update(user);
        getList("0", 1);
        return "success";
    }

    private void getList(String flag,int start) {
        List<User> optList  = userDao.findByPage(flag,(start-1)*15,15);
        int sum = userDao.findSum(flag);
        Map<String,Object> output = ActionContext.getContext().getSession();
        output.put("user_list",optList);
        output.put("page_sum",sum);
        int t = start;
        output.put("page_start",t);
        output.put("card_type","normal");
        output.put("card_key","");
    }

    private void getSearch(String key,String flag,int start) {
        Map<String,Object> output = ActionContext.getContext().getSession();
        output.put("card_key",key);
        key = "%" + key + "%";
        List<User> optList  = userDao.search(key,flag,(start-1)*15,15);
        int sum = userDao.findSum1(flag,key);
        output.put("user_list",optList);
        output.put("page_sum",sum);
        int t = start;
        output.put("page_start",t);
        output.put("card_type","search");
    }

    @Action(value = "changeUserMsg")
    public void changeUserMsg() {
        HttpServletResponse response = ServletActionContext.getResponse();
        Map<String,Object> output = ActionContext.getContext().getSession();
        String login_user = (String)output.get("user_name");
        User u = userDao.find(login_user);
        User u1 = userDao.find(user.getUserName());
        try {
            if (!u.getUserType().equals("超级管理员")) {
                response.getWriter().print("no_auth");
            } else {
                output.put("update_user",u1);
                response.getWriter().print("ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Action(value = "deleteUserById")
    public void deleteById() {
        HttpServletResponse response = ServletActionContext.getResponse();
        Map<String,Object> output = ActionContext.getContext().getSession();
        String login_user = (String)output.get("user_name");
        User u = userDao.find(login_user);
        try {
            if (!u.getUserType().equals("超级管理员")) {
                response.getWriter().print("no_auth");
            } else {
                userDao.delete(user.getUserId());
                response.getWriter().print("ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Action(value = "batchDelete")
    public void batchDelete() {
        HttpServletResponse response = ServletActionContext.getResponse();
        Map<String,Object> output = ActionContext.getContext().getSession();
        String login_user = (String)output.get("user_name");
        User u = userDao.find(login_user);
        try {
            if (!u.getUserType().equals("超级管理员")) {
                response.getWriter().print("no_auth");
            } else {
                List<Integer> li = JSON.parseArray(ids, Integer.TYPE);
                String ss = li.toString();
                int t = userDao.batchDeleteMsg(ss.substring(1, ss.length() - 1));
                response.getWriter().print("ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Action(
            value = "checkIn",
            results = {
                    @Result(name = "user",location = "userList?start=1",type = "redirect"),
                    @Result(name = "card",location = "../cards/careList?start=1",type = "redirect"),
                    @Result(name = "login",location = "/login.jsp",type = "redirect")
            }
    )
    public String checkIn() {
        Map<String,Object> output = ActionContext.getContext().getSession();
        String login_user = (String)output.get("user_name");
        if (login_user==null || login_user.equals("")) {
            return "login";
        }else if (USER_JUMP.equals(jumpTo)) {
            return "user";
        } else {
            return "card";
        }
    }

    @Action(
            value = "registerUser"
    )
    public void registerUser() {
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            User uu = userDao.find(user.getUserName());
            if (uu==null) {
                user.setUserType("二级管理员");
                userDao.insert(user);
                response.getWriter().print("ok");
            } else {
                response.getWriter().print("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getJumpTo() {
        return this.jumpTo;
    }

    public void setJumpTo(String jumpTo) {
        this.jumpTo = jumpTo;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
