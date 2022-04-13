/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Role;
import Model.User;
import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Phong
 */
public class ForgetPasswordDAO extends DBContext {

    public User findUserByEmail(String email) {
        try {
            String sql = "SELECT * FROM onlinelearn.user  WHERE email = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            User user = null;
            while (rs.next()) {
                if (user == null) {
                    user = new User();
                    user.setAddress(rs.getString("address"));
                    user.setUsername(rs.getString("username"));
                    user.setFullname(rs.getString("fullname"));
                    user.setGender(rs.getBoolean("gender"));
                    user.setPhonenumber(rs.getString("phonenumber"));
                    user.setStatus(rs.getInt("status"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(email);
                    Role r = new Role();
                    r.setRole_id((rs.getInt("role_id")));
                    user.setRole(r);
                }
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User findUserByPassword(String pass) {
        try {
            String sql = "SELECT * FROM onlinelearn.user  WHERE password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pass);
            ResultSet rs = stm.executeQuery();
            User user = null;
            while (rs.next()) {
                if (user == null) {
                    user = new User();
                    user.setAddress(rs.getString("address"));
                    user.setUsername(rs.getString("username"));
                    user.setFullname(rs.getString("fullname"));
                    user.setGender(rs.getBoolean("gender"));
                    user.setPhonenumber(rs.getString("phonenumber"));
                    user.setStatus(rs.getInt("status"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(pass);
                    user.setEmail(rs.getString("email"));
                    Role r = new Role();
                    r.setRole_id((rs.getInt("role_id")));
                    user.setRole(r);
                }
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    
//
//    public boolean checkStatus(String email) {
//        try {
//            String sql = "SELECT status FROM onlinelearn.user  WHERE email = ?";
//            PreparedStatement stm = connection.prepareStatement(sql);
//            stm.setString(1, email);
//            ResultSet rs = stm.executeQuery();
//            User1 user = new User1();
//            while (rs.next()) {
//                if ((rs.getInt("status")) == 4) {
//                    return true;
//                }
//            }
//            return false;
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//    public void setStatus(String email, int status) {
//        String query = "UPDATE `onlinelearn`.`user`\n"
//                + "SET\n"
//                + "`status` = ?\n"
//                + "WHERE email=?;";
//        try {
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setInt(1, status);
//            ps.setString(2, email);
//            ps.executeUpdate();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(SettingsListDBContext.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println(ex.getMessage());
//        }
//    }
//    public static void main(String[] args) {
//        ForgetPasswordDAO dao = new ForgetPasswordDAO();
//        dao.setStatus("cc@gmail.com", 4);
//    }
}
