package com.zhj6422.databaseDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrepareStatementMethod {

  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost:3306/test";
  static final String USER = "root";
  static final String PASS = "123456";

  public static void main(String[] args) {

    Connection con = null;
    PreparedStatement pst = null;
    try{
      Class.forName(JDBC_DRIVER);
      con = DriverManager.getConnection(DB_URL,USER,PASS);
      String sql = "INSERT INTO user (username) " + "VALUES(?)";
      String sql2 = "DELETE FROM user WHERE username=?";
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

      pst = con.prepareStatement(sql2);
      con.setAutoCommit(false);
      pst.setString(1,"Curry");
      pst.addBatch();

      pst.setString(1,"James");
      pst.addBatch();

      pst.setString(1,"Durant");
      pst.addBatch();

      pst.executeBatch();
      con.commit();

      pst.close();
      con.close();


    }catch(SQLException e){
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }finally{
      try{
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
  }
}
