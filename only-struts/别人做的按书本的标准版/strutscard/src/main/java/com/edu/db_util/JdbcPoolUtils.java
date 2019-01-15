package com.edu.db_util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;


public class JdbcPoolUtils {
    private static DataSource dataSource = new ComboPooledDataSource();
    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }
    public static void close(ResultSet rs, Statement stmt,Connection conn)throws SQLException{
        try {
            if(rs!=null){
                rs.close();
            }
        }finally {
            try {
                if(stmt!=null){
                    stmt.close();
                }
            }finally {
                if(conn!=null){
                    conn.close();
                }
            }
        }
    }
    public static int dbCUD(String sql,Object... params){
        Connection conn = null;
        PreparedStatement pstmt = null;
        int row = 0;
        try{
            conn = JdbcPoolUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            if(params!=null){
                for (int i = 0;i<params.length;i++){
                    pstmt.setObject(i+1,params[i]);
                }

                    row = pstmt.executeUpdate();
                    JdbcPoolUtils.close(null,pstmt,conn);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return row;
    }
    public static ResultSet query(Connection conn,String sql,Object... params){
        PreparedStatement pstmt;
        ResultSet rs=null;
        try{
            pstmt=conn.prepareStatement(sql);
            if(params!=null){
                for(int i=0;i<params.length;i++){
                    pstmt.setObject(i+1,params[i]);
                }
                
            }
            rs=pstmt.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rs;
    }
}


