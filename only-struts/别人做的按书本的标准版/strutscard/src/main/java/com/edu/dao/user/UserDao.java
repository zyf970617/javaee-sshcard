package com.edu.dao.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.edu.dao.IBaseDao;
import com.edu.db_util.JdbcPoolUtils;
import com.edu.model.user.User;
public class UserDao implements IBaseDao<User> {
    public  int insert(User user){
        String sql = "insert into user(userName,userPassword,userRealName)values(?,?,?)";
        Object params[]={user.getUserName(),user.getUserPassword(),user.getUserRealName()};
        System.out.print("***"+user.getUserName()+"*******"+user.getUserPassword()+"***"+user.getUserRealName());
        return JdbcPoolUtils.dbCUD(sql,params);
    }

    public int insertList(List<User> list) {
        return 0;
    }

    public int update(User user) {
        return 0;
    }

    public int deleteList(int... ids) {
        return 0;
    }

    public int delete(User user) {
        return 0;
    }

    public int delete(int id) {
        return 0;
    }

    public User findById(int id) {
        return null;
    }

    public User find(User user) {
        Connection conn;
        String sql = "select * from user where userName=? and userPassword=?";
        ResultSet rs = null;
        User user1 = null;
        Object params[] = {user.getUserName(),user.getUserPassword()};
        try {
            conn = JdbcPoolUtils.getConnection();
            rs=JdbcPoolUtils.query(conn,sql,params);
            if (rs.next()){
                user1 = new User();
                user1.setUserId(rs.getInt("id"));
                user1.setUserName(rs.getString("userName"));
                user1.setUserPassword(rs.getString("userPassword"));
                user1.setUserRealName(rs.getString("userRealName"));
            }
            JdbcPoolUtils.close(rs,null,conn);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user1;
    }

    public List<User> findAll() {
        return null;
    }

    public List<User> findByCondition(String Condition) {
        return null;
    }
}
