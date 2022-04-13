/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Role;
import Model.Token;
import Model.User;
import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Duy Hiep
 */
public class RegisterDao extends DBContext{

    public void insert(User u) {
        try {
            String sql = "INSERT INTO `onlinelearn`.`user`\n" +
                        "(\n" +
                        "`username`,\n" +
                        "`password`,\n" +
                        "`fullname`,\n" +
                        "`gender`,\n" +
                        "`address`,\n" +
                        "`email`,\n" +
                        "`phonenumber`,\n" +
                        "`status`,\n" +
                        "`Role_Id`)\n" +
                        "VALUES\n" +
                        "(\n" +
                        "?,\n" +
                        "?,\n" +
                        "?,\n" +
                        "?,\n" +
                        "?,\n" +
                        "?,\n" +
                        "?,\n" +
                        "?,\n" +
                        "?);";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, u.getUsername());
            stm.setString(2, u.getPassword());
            stm.setString(3, u.getFullname());
            stm.setBoolean(4, u.isGender());
            stm.setString(5, u.getAddress());
            stm.setString(6, u.getEmail());
            stm.setString(7, u.getPhonenumber());
            stm.setInt(8, u.getStatus());
            stm.setInt(9, u.getRole().getRole_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public User getUser(String username,String email){
        try {
            String sql = "SELECT * FROM onlinelearn.user\n" +
                    "where user.username = ? and user.email = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, email);
            ResultSet rs = stm.executeQuery();
            User u = new User();
            if (rs.next()) {                
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setFullname(rs.getString("fullname"));
                u.setGender(rs.getBoolean("gender"));
                u.setAddress(rs.getString("address"));
                u.setEmail(rs.getString("email"));
                u.setPhonenumber(rs.getString("phonenumber"));
//                Status s = new Status();
//                s.setStatus_id(rs.getInt("status"));
                u.setStatus(rs.getInt("status"));
                Role r = new Role();
                r.setRole_id(rs.getInt("Role_Id"));
                u.setRole(r);
            }
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<String> getUserNameExist(){
        ArrayList<String> list = new ArrayList<>();
        try {
            String sql = "SELECT `user`.`username`" +
                                "FROM `onlinelearn`.`user`";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                list.add(rs.getString("username"));
            } 
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }
    
    public ArrayList<String> getEmailExist(){
        ArrayList<String> list = new ArrayList<>();
        try {
            String sql = "SELECT `user`.`email`\n" +
                                "FROM `onlinelearn`.`user`";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                list.add(rs.getString("email"));
            } 
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }
    
    public void changestatusUser(User u) {
        try {
            String sql = "UPDATE `onlinelearn`.`user`\n" +
                    "SET\n" +
                    "`status` = 2\n" +
                    "WHERE user.username = ?;";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, u.getUsername());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void insertToken(Token t){
        try {
            String sql = "INSERT INTO `onlinelearn`.`token`\n" +
                    "(\n" +
                    "`User_id`,\n" +
                    "`code`,\n" +
                    "`createdAt`,\n" +
                    "`updatedAt`,\n" +
                    "`expired`)\n" +
                    "VALUES\n" +
                    "(\n" +
                    "?,\n" +
                    "?,\n" +
                    "?,\n" +
                    "?,\n" +
                    "?);";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, t.getUser_id().getId());
            stm.setString(2, t.getCode());
            stm.setTimestamp(3, t.getCreatedAt());
            stm.setTimestamp(4, t.getUpdatedAt());
            stm.setTimestamp(5, t.getExpired());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Token getToken(int user_id){
        try {
            String sql = "SELECT * FROM onlinelearn.token\n" +
                        "where token.User_id = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, user_id);
            ResultSet rs = stm.executeQuery();
            Token t =  new Token();
            if (rs.next()) {
                t.setId(rs.getInt("id"));
                User u = new User();
                u.setId(rs.getInt("User_id"));
                t.setUser_id(u);
                t.setCode(rs.getString("code"));
                t.setCreatedAt(rs.getTimestamp("createdAt"));
                t.setUpdatedAt(rs.getTimestamp("updatedAt"));
                t.setExpired(rs.getTimestamp("expired"));     
            }
            return t;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void updateToken(Token t){
        try {
            String sql = "UPDATE `onlinelearn`.`token`\n" +
                        "SET\n" +
                        "`code` = ?,\n" +
                        "`updatedAt` = ?,\n" +
                        "`expired` = ?\n" +
                        "WHERE `id` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, t.getCode());
            stm.setTimestamp(2, t.getUpdatedAt());
            stm.setTimestamp(3, t.getExpired());
            stm.setInt(4, t.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public static void main(String[] args) {
//        RegisterDao rdb = new RegisterDao();
//        User u = rdb.getUser("Admin", "admin@gmail.com");
//        System.out.println(u.toString());
//    }

    
}
