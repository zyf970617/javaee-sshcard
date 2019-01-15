package com.edu.dao.card;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edu.dao.IBaseDao;
import com.edu.db_util.JdbcPoolUtils;
import com.edu.model.card.Card;

public class CardDao implements IBaseDao<Card>{
      public int insert (Card card){String sql="insert into card(name,sex,department,mobile,phone,email,address,flag)values(?,?,?,?,?,?,?,'0')";
Object params[]={card.getName(),card.getSex(),card.getDepartment(),
card.getMobile(),card.getPhone(),card.getEmail(),card.getAddress()};

return JdbcPoolUtils.dbCUD(sql,params);
}

public int insertList(List<Card>list){
for(int i=0;i<list.size();++i){insert(list.get(i));}
return list.size();
}

public int update(Card card){
  String sql="update card set name=?,sex=?,department=?,mobile=?,phone=?,email=?,address=? where id=?";
   Object params[]={card.getName(),card.getSex(),card.getDepartment(),
card.getMobile(),card.getPhone(),card.getEmail(),card.getAddress(),card.getId()};
  return JdbcPoolUtils.dbCUD(sql,params);
}


public int delete(int id){
String sql="delete from card where id=?";
Object params[]={id};
return JdbcPoolUtils.dbCUD(sql,params);
}

public int deleteList(int...ids){
   for(int id:ids){delete(id);}
    return ids.length;
}

public int delete(Card card){int row=delete(card.getId());return row;}
  public Card findById(int id){
Connection con;
String sql="select * from card where id=?";
ResultSet rs=null;
Card card2=null;
Object params[]={id};

try{
   con=JdbcPoolUtils.getConnection();
   rs=JdbcPoolUtils.query(con,sql,params);
   if(rs.next()){
                card2=new Card();
                card2.setId(rs.getInt("id"));
                card2.setName(rs.getString("name"));
                card2.setSex(rs.getString("sex"));
                card2.setDepartment(rs.getString("department"));
                card2.setMobile(rs.getString("mobile"));
                card2.setPhone(rs.getString("phone"));
                card2.setEmail(rs.getString("email"));
                card2.setAddress(rs.getString("address"));
                card2.setFlag(rs.getString("flag"));
                 }
             JdbcPoolUtils.close(rs,null,con);
  }catch(SQLException e){e.printStackTrace();}
         return card2;
}



public List<Card>findAll(){
   Connection con;
   String sql="select * from card";
   ResultSet rs=null;
   List<Card>cards=new ArrayList<Card>();
   Object params[]=null;

try{
   con=JdbcPoolUtils.getConnection();
   rs=JdbcPoolUtils.query(con,sql,params);
   while(rs.next()){
                Card card2=new Card();
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
  }catch(SQLException e){e.printStackTrace();}
         return cards;
}

public List<Card> findByCondition(String condition){
     List<Card>cards =new ArrayList<Card>();
     String sql="select * from card ";
     String fields[]={"name","sex","department","mobile","phone","email","address"};
if(condition != null && !condition.equals("")){
   sql=sql+" where ";
for(int i=0;i<fields.length-1;++i){
         sql=sql+fields[i]+" like '%" +condition+ "%'or ";
   }
    sql=sql+  fields[fields.length-1]+" like '%" +condition+ "%'";
   }   

     Connection con;
   ResultSet rs=null;  
   Object params[]=null;

  try{
   con=JdbcPoolUtils.getConnection();
   rs=JdbcPoolUtils.query(con,sql,params);
   while(rs.next()){
                Card card2=new Card();
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
  }catch(SQLException e){e.printStackTrace();}
         return cards;
}


  public Card find(Card card){
     Card card2=findById(card.getId());
          return card2;
}


public Card findById(int id,String flag){
          Card card2=findById(id);
         if(card2.getFlag().equals(flag)){return card2;}else{return null;}
      }


public Card find(Card card,String flag){
    Card card2=null;
   card2=findById(card.getId(),flag);
return card2;
}


public List<Card>findAll(String flag){
List<Card>cards=findAll();
List<Card>cards2=new ArrayList<Card>();
for(Card card:cards){
   if(card.getFlag().equals(flag)){cards2.add(card);}}
return cards2;
}

 
public List<Card>findByCondition(String condition,String flag){
    List<Card> cards=findByCondition(condition);
    List<Card> cards2=new ArrayList<Card>();
    for(Card card:cards){
       if(card.getFlag().equals(flag)){cards2.add(card);}}
    return cards2;
} 


public int updateFlag(int id,String flag)throws SQLException{
     String sql="update card set flag=? where id=?";
Object params[]={flag,id};
  return JdbcPoolUtils.dbCUD(sql,params);
}


public int retrieve(int...ids)throws SQLException {
   for(int id:ids){updateFlag(id,"1");}
     return ids.length;
}

public int revert(int...ids) throws SQLException {
      for(int id:ids){updateFlag(id,"0");}
     return ids.length;
}



}