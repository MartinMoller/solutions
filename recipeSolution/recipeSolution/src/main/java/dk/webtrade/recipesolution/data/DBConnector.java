/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.webtrade.recipesolution.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author thomas
 */
public class DBConnector {
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost/recipesDB";
    static final String USERNAME = "root";
    static final String PASSWORD = "root";
    
    public static Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            
        } catch(SQLException | ClassNotFoundException se){
           se.printStackTrace();
        }
        return conn;
    }
    public static void main(String[] args) {
        try {
            String sql = "SELECT id, username, password FROM DB.User";
            Connection conn = DBConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
//                User user = new User
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public static void close(Statement stmt, ResultSet rs, Connection conn){
        try{
            if(rs != null)
                rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se){
            se.printStackTrace();
            
        } catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            try{
              if(stmt!=null)
                 stmt.close();
           }catch(SQLException se2){
               se2.printStackTrace();
           }// nothing we can do
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException se){
              se.printStackTrace();
           }
        }
    }
}
