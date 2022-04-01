package com.zhj6422.databaseDemo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HikariMethod {

  public static void main(String[] args) {
    // 初始化连接池
    HikariConfig config = new HikariConfig("/hikari.properties");
    HikariDataSource dataSource = new HikariDataSource(config);

    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    try{
      //创建connection
      con = dataSource.getConnection();
      st = con.createStatement();
      rs = st.executeQuery("select * from user");

      if(rs.next()){
        System.out.println(rs.getString("id") + " - " + rs.getString("username"));
      }
      rs.close();
      st.close();
      con.close();
    }catch(SQLException e){
      e.printStackTrace();
    }finally{
      try{
        if(rs != null){
          rs.close();
        }
        if(st != null){
          st.close();
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
