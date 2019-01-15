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

/**
 * @auther 徐森威
 * @date 2018/4/17
 */
public class CardDao implements IBaseDao<Card> {

    public int insert(Card card) {
        String sql = "insert into card(name,sex,department,mobile,phone,email,address,flag) values(?,?,?,?,?,?,?,?)";
        Object params[] = {card.getName(),card.getSex(),card.getDepartment(),card.getMobile(),card.getPhone(),card.getEmail(),card.getAddress(),card.getFlag()};
        return JdbcPoolUtils.dbCUD(sql,params);
    }

    public int findSum(String flag) {
        Connection con = null;
        String sql = "select COUNT(*) from card where flag = ?";
        ResultSet rs = null;
        Object params[] = {flag};
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
        String sql = "select COUNT(*) from card where flag = ? and name like ?";
        ResultSet rs = null;
        Object params[] = {flag,key};
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

    public Card find(String name) {
        String sql = "select * from card where name = ?";
        return findBy(name,sql);
    }

    private Card findBy(Object ob,String sql) {
        Connection con = null;
        ResultSet rs = null;
        Card card2 = null;
        Object params[] = {ob};
        try {
            con = JdbcPoolUtils.getConnection();
            rs = JdbcPoolUtils.query(con,sql,params);
            if (rs.next()) {
                card2 = new Card();
                card2.setId(rs.getInt("id"));
                card2.setAddress(rs.getString("address"));
                card2.setDepartment(rs.getString("department"));
                card2.setEmail(rs.getString("email"));
                card2.setFlag(rs.getString("flag"));
                card2.setSex(rs.getString("sex"));
                card2.setPhone(rs.getString("phone"));
                card2.setName(rs.getString("name"));
                card2.setMobile(rs.getString("mobile"));
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
        return card2;
    }

    public Card findById(int id) {
        String sql = "select * from card where id = ?";
        return findBy(id,sql);
    }

    public List<Card> findAll() {
        String sql = "select * from card";
        Object params[] = null;
        return getCards(sql, params);
    }

    public List<Card> findByPage(String flag, int start, int len) {
        String sql = "select * from card where flag = ? limit ?,?";
        Object params[] = {flag,start,len};
        return getCards(sql, params);
    }

    public List<Card> search(String key,String flag,int start, int len) {
        String sql = "select * from card where flag = ? and name like ? order by id asc limit ?,?";
        Object params[] = {flag,key,start,len};
        return getCards(sql,params);
    }

    private List<Card> getCards(String sql, Object params[]) {
        Connection con = null;
        List<Card> cards = new ArrayList<Card>();
        try {
            con = JdbcPoolUtils.getConnection();
            ResultSet rs = null;
            rs = JdbcPoolUtils.query(con,sql,params);
            while (rs.next()) {
                Card card2 = new Card();
                card2.setId(rs.getInt("id"));
                card2.setName(rs.getString("name"));
                card2.setSex(rs.getString("sex"));
                card2.setDepartment(rs.getString("department"));
                card2.setMobile(rs.getString("mobile"));
                card2.setPhone(rs.getString("phone"));
                card2.setEmail(rs.getString("email"));
                card2.setAddress(rs.getString("address"));
                card2.setFlag(rs.getString("flag"));
                cards.add(card2);
            }
            JdbcPoolUtils.close(rs,null,con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }

    public int batchMoveMsg(String flag, String ids) {
        String sql = "update card set flag = ? where id in ("+ids+")";
        Object params[] = {flag};
        return JdbcPoolUtils.dbCUD(sql,params);
    }

    public int batchDeleteMsg(String ids) {
        String sql = "delete from card where id in ("+ids+")";
        Object params[] = null;
        return JdbcPoolUtils.dbCUD(sql,params);
    }

    public int delete(int id) {
        String sql = "delete from card where id = ?";
        Object params[] = {id};
        return JdbcPoolUtils.dbCUD(sql,params);
    }

    public int update (Card card) {
        String sql = "update card set name=?,sex=?,department=?,mobile=?,phone=?,email=?,address=? where id = ?";
        Object params[] = {card.getName(),card.getSex(),card.getDepartment(),card.getMobile(),card.getPhone(),card.getEmail(),card.getAddress(),card.getId()};
        return JdbcPoolUtils.dbCUD(sql,params);
    }

}
