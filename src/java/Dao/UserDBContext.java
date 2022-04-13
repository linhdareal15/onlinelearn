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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Century
 */
public class UserDBContext extends DBContext {

    public User getUser(String email, String password) {
        try {
            String sql = "SELECT * FROM onlinelearn.user u\n"
                    + "join onlinelearn.role r\n"
                    + "on u.Role_Id = r.role_id\n"
                    + "WHERE u.email = ? AND u.password = ? and status = 2";
            // status 2 là active nhé mn
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            User user = null;
            while (rs.next()) {
                if (user == null) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setAddress(rs.getString("address"));
                    user.setUsername(rs.getString("username"));
                    user.setFullname(rs.getString("fullname"));
                    user.setGender(rs.getBoolean("gender"));
                    user.setPhonenumber(rs.getString("phonenumber"));
                    user.setStatus(rs.getInt("status"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(password);
                    user.setEmail(email);
                    int role_id = rs.getInt("Role_id");
                    Role r = new Role();
                    r.setRole_id(role_id);
                    r.setRole_name(rs.getString("role_name"));
                    user.setRole(r);
                }
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public User getUserById(int id) {
        try {
            String sql = "SELECT * FROM onlinelearn.user u\n"
                    + "join onlinelearn.role r\n"
                    + "on u.Role_Id = r.role_id\n"
                    + "WHERE u.id = ? and status = 2";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            User user =null;
            //int id, String username, String password, String fullname, boolean gender, String address, String email, String phonenumber, int status, int role
            while (rs.next()) {
                    user =new User();
                    user.setId(rs.getInt("id"));
                    user.setAddress(rs.getString("address"));
                    user.setUsername(rs.getString("username"));
                    user.setFullname(rs.getString("fullname"));
                    user.setGender(rs.getBoolean("gender"));
                    user.setPhonenumber(rs.getString("phonenumber"));
                    user.setStatus(rs.getInt("status"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    int role_id = rs.getInt("role_id");
                    Role r = new Role();
                    r.setRole_id(role_id);
                    r.setRole_name(rs.getString("role_name"));
                    user.setRole(r);
            }
            return user;
        }
         catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<User> getAllAuthor() {
        try {
            ArrayList<User> users = new ArrayList<>();
            String sql = "SELECT * FROM onlinelearn.user AS u JOIN onlinelearn.role r on u.Role_Id = r.role_id WHERE u.role_id = 6;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setAddress(rs.getString("address"));
                user.setUsername(rs.getString("username"));
                user.setFullname(rs.getString("fullname"));
                user.setGender(rs.getBoolean("gender"));
                user.setPhonenumber(rs.getString("phonenumber"));
                user.setStatus(rs.getInt("status"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                int role_id = rs.getInt("role_id");
                Role r = new Role();
                r.setRole_id(role_id);
                user.setRole(r);
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int updateProfileUser(int id,String username,String fullname, Boolean gender,String address, String phone, String avatar) {
        String sql = "UPDATE `onlinelearn`.`user`\n"
                + "SET\n"
                + "`username` = ?,\n"
                + "`fullname` = ?,\n"
                + "`gender` = ?,\n"
                + "`address` = ?,\n"
                + "`phonenumber` = ?,\n"
                + "`avatar` = ?\n"
                + "WHERE `id` = ?;";
        try{
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, fullname);
            ps.setBoolean(3, gender);
            ps.setString(4, address);
            ps.setString(5, phone);
            ps.setString(6, avatar);
            ps.setInt(7, id);
            return ps.executeUpdate();
            
        }
        catch (SQLException ex){
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage()+"loi o day");
        }
        return 0;
    }
    public User getUserNotLogin(String email) {
        try {
            String sql = "SELECT * FROM onlinelearn.user u\n"
                    + "join onlinelearn.role r\n"
                    + "on u.Role_Id = r.role_id\n"
                    + "WHERE u.email = ?";
            // status 2 là active nhé mn
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            User user = null;
            while (rs.next()) {
                if (user == null) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setAddress(rs.getString("address"));
                    user.setUsername(rs.getString("username"));
                    user.setFullname(rs.getString("fullname"));
                    user.setGender(rs.getBoolean("gender"));
                    user.setPhonenumber(rs.getString("phonenumber"));
                    user.setStatus(rs.getInt("status"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(email);
                    int role_id = rs.getInt("role_id");
                    Role r = new Role();
                    r.setRole_id(role_id);
                    r.setRole_name(rs.getString("role_name"));
                    user.setRole(r);
                }
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        UserDBContext userDBContext = new UserDBContext();
        User user = userDBContext.getUser("phongpro3639@gmail.com", "12345");
        System.out.println(user.getFullname());
    }
}
