/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.helloWeb.Users;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author telugbadebo
 */
public class UserHandler {

    private String userName;
    private PreparedStatement authenticateUserStatement;

    public UserHandler() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/avanteso_master", "root", "");

            authenticateUserStatement = conn.prepareStatement("select * from config_user where userId =? and password = ?");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        userName = null;
    }

    /**
     * @return the userName
     */
    public User authenticateUser(String userName, String Password) {
        User user = null;
        String encryPass = MD5(Password);
        try {
            authenticateUserStatement.setString(1, userName);
            authenticateUserStatement.setString(2, encryPass);
            ResultSet rs = authenticateUserStatement.executeQuery();
            if(rs.next()){
                user = new User(rs.getString(userName),rs.getString(Password));
            }
        } 
        catch(SQLException e)
        {
            
        }
        return user;
    }

    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
