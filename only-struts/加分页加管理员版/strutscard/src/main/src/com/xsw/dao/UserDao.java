package com.xsw.dao;

import com.xsw.entity.Card;
import com.xsw.entity.User;
import com.xsw.util.IBaseDao;
import com.xsw.util.JdbcPoolUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 徐森威
 * @date 2018/4/17
 */
public class UserDao implements IBaseDao<User> {

    public int insert(User user) {
        String sql = "insert into user(userName,userPassword,userRealName,userType) values(?,?,?,?)";
        Object params[] = {user.getUserName(),user.getUserPassword(),user.getUserRealName(),user.getUserType()};
        return JdbcPoolUtils.dbCUD(sql,params);
    }

    public User find(String name) {
        Connection con = null;
        String sql = "select * from user where userName = ?";
        ResultSet rs = null;
        User user2 = null;
        Object params[] = {name};
        try {
            con = JdbcPoolUtils.getConnection();
            rs = JdbcPoolUtils.query(con,sql,params);
            if (rs.next()) {
                user2 = new User();
                user2.setUserId(rs.getInt("id"));
                user2.setUserName(rs.getString("userName"));
                user2.setUserPassword(rs.getString("userPassword"));
                user2.setUserRealName(rs.getString("userRealName"));
                user2.setUserType(rs.getString("userType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcPoolUtils.close(rs,null,con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user2;
    }

    public List<User> findAll() {
        String sql = "select * from user";
        Object params[] = null;
        return getUsers(sql, params);
    }

    public List<User> findByPage(String flag, int start, int len) {
        String sql = "select * from user limit ?,?";
        Object params[] = {start,len};
        return getUsers(sql,params);
    }

    public List<User> search(String key,String flag,int start, int len) {
        String sql = "select * from user where userName like ? order by id asc limit ?,?";
        Object params[] = {key,start,len};
        return getUsers(sql,params);
    }

    private List<User> getUsers(String sql, Object params[]) {
        Connection con = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<User>();
        try {
            con = JdbcPoolUtils.getConnection();
            rs = JdbcPoolUtils.query(con,sql,params);
            while (rs.next()) {
                User user2 = new User();
                user2.setUserId(rs.getInt("id"));
                user2.setUserName(rs.getString("userName"));
                user2.setUserPassword(rs.getString("userPassword"));
                user2.setUserRealName(rs.getString("userRealName"));
                user2.setUserType(rs.getString("userType"));
                users.add(user2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcPoolUtils.close(rs,null,con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    //flag暂时不用，添加回收站后再用，先放着
    public int findSum(String flag) {
        Connection con = null;
        String sql = "select COUNT(*) from user";
        ResultSet rs = null;
        Object params[] = null;
        int count = 0;
        try {
            con = JdbcPoolUtils.getConnection();
            rs = JdbcPoolUtils.query(con,sql,params);
            if (rs.next()) {
                count=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcPoolUtils.close(rs,null,con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public int findSum1(String flag,String key) {
        Connection con = null;
        String sql = "select COUNT(*) from user where userName like ?";
        ResultSet rs = null;
        Object params[] = {key};
        int count = 0;
        try {
            con = JdbcPoolUtils.getConnection();
            rs = JdbcPoolUtils.query(con,sql,params);
            if (rs.next()) {
                count=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcPoolUtils.close(rs,null,con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public int delete(int id) {
        String sql = "delete from user where id = ?";
        Object params[] = {id};
        return JdbcPoolUtils.dbCUD(sql,params);
    }

    public int batchDeleteMsg(String ids) {
        String sql = "delete from user where id in ("+ids+")";
        Object params[] = null;
        return JdbcPoolUtils.dbCUD(sql,params);
    }

    public int update (User user) {
        String sql = "update user set userName=?,userPassword=?,userRealName=?,userType=? where id = ?";
        Object params[] = {user.getUserName(),user.getUserPassword(),user.getUserRealName(),user.getUserType(),user.getUserId()};
        return JdbcPoolUtils.dbCUD(sql,params);
    }
}
