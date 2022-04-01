package com.zhj6422.databaseDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCMethod {

  public static void main(String[] args) {

    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    try{
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
      if(con == null){
        System.out.println("连接失败");
      }else{
        System.out.println("连接成功");
      }
      st = con.createStatement();


      String sqlStatement = "SELECT * FROM user";

      rs = st.executeQuery(sqlStatement);

      while(rs.next()){
        System.out.println(rs.getObject("id"));
        System.out.println(rs.getObject("username"));
      }

      sqlStatement = "INSERT INTO user (username) VALUES ('Curry')";
      System.out.println("插入条数："+st.executeUpdate(sqlStatement));

      sqlStatement = "UPDATE user SET username='Durant' WHERE id=2";
      System.out.println("更新条数："+st.executeUpdate(sqlStatement));

      sqlStatement = "DELETE FROM user WHERE username='Durant'";
      System.out.println("删除条数："+st.executeUpdate(sqlStatement));

    }catch(SQLException e){
      e.printStackTrace();
    }finally{
      try{
        if(con != null){
          con.close();
        }
        if(st != null){
          st.close();
        }
        if(rs != null){
          rs.close();
        }
      }catch(SQLException e){
        e.printStackTrace();
      }
    }
  }
}
