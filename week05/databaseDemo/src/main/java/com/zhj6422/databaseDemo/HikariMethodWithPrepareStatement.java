package com.zhj6422.databaseDemo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HikariMethodWithPrepareStatement {

  public static void main(String[] args) {
    // 初始化连接池
    HikariConfig config = new HikariConfig("/hikari.properties");
    HikariDataSource dataSource = new HikariDataSource(config);

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    try{
      //创建connection
      con = dataSource.getConnection();
      String sql = "INSERT INTO user (username) " + "VALUES(?)";
      String sql2 = "DELETE FROM user WHERE username=?";
      String sql3 = "SELECT * FROM user";
      pst = con.prepareStatement(sql);
      con.setAutoCommit(false);

      pst.setString(1, "Curry");
      pst.addBatch();

      pst.setString(1,"Durant");
      pst.addBatch();

      pst.setString(1,"James");
      pst.addBatch();

      int[] count = pst.executeBatch();

      System.out.println(count);
      con.commit();

//      pst = con.prepareStatement(sql2);
//      con.setAutoCommit(false);
//      pst.setString(1,"Curry");
//      pst.addBatch();
//
//      pst.setString(1,"James");
//      pst.addBatch();
//
//      pst.setString(1,"Durant");
//      pst.addBatch();
//
//      pst.executeBatch();
//      con.commit();

      pst = con.prepareStatement(sql3);
      con.setAutoCommit(false);
      rs = pst.executeQuery();
      while(rs.next()){
        System.out.println(rs.getObject("id") + " - " + rs.getObject("username"));
      }
      con.commit();


      pst.close();
      con.close();

    }catch(SQLException e){
      e.printStackTrace();
    }finally{
      try{
        if(rs != null){
          rs.close();
        }
        if(pst != null){
          pst.close();
        }
        if(con != null){
          con.close();
        }
      }catch(SQLException e){
        e.printStackTrace();
      }
    }
    if(!dataSource.isClosed()){
      dataSource.close();
    }
  }
}
