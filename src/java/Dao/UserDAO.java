/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.BlogList;
import Model.Role;
import Model.Slider;
import Model.Status;
import Model.User;
import context.DBContext;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Century
 */
public class UserDAO extends DBContext {

    public User getUser(String email, String password) {
        try {
            String sql = "SELECT *, r.role_name FROM onlinelearn.user u\n"
                    + "join onlinelearn.role r\n"
                    + "on u.Role_Id = r.role_id\n"
                    + "WHERE u.email = ? AND u.password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
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
                    user.setPassword(password);
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

    public User getUser1(String email, String password) {
        try {
            String sql = "SELECT * FROM onlinelearn.user \n"
                    + "WHERE email = ? AND password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
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
                    user.setPassword(password);
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

    public ArrayList<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM onlinelearn.user join onlinelearn.role\n"
                    + "on onlinelearn.user.Role_Id = onlinelearn.role.role_id;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setFullname(rs.getString("fullname"));
                u.setGender(rs.getBoolean("gender"));
                u.setAddress(rs.getString("address"));
                u.setEmail(rs.getString("email"));
                u.setPhonenumber(rs.getString("phonenumber"));
                u.setStatus(rs.getInt("status"));
                Role r = new Role();
                r.setRole_id(rs.getInt("role_id"));
                r.setRole_name(rs.getString("role_name"));
                u.setRole(r);
                list.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Role> getAllRoles() {
        ArrayList<Role> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM onlinelearn.role";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role r = new Role();
                r.setRole_id(rs.getInt("role_id"));
                r.setRole_name(rs.getString("role_name"));
                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void addUser(User u, InputStream fileContent) {
        try {
            String sql = "INSERT INTO `onlinelearn`.`user`\n"
                    + "(\n"
                    + "`username`,\n"
                    + "`password`,\n"
                    + "`fullname`,\n"
                    + "`gender`,\n"
                    + "`address`,\n"
                    + "`email`,\n"
                    + "`phonenumber`,\n"
                    + "`status`,\n"
                    + "`Role_Id`,\n"
                    + "`avatar`)\n"
                    + "VALUES\n"
                    + "(\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?);";
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
            stm.setBlob(10, fileContent);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateUser(int id, String username, String fullname,
            boolean gender, String address, String email, String phonenumber,
            int status, int role_id, InputStream fileContent) {
        if (fileContent != null) {
            try {
                String sql = "UPDATE `onlinelearn`.`user`\n"
                        + "SET\n"
                        + "`username` = ?,\n"
                        + "`fullname` = ?,\n"
                        + "`gender` = ?,\n"
                        + "`address` = ?,\n"
                        + "`email` = ?,\n"
                        + "`phonenumber` = ?,\n"
                        + "`status` = ?,\n"
                        + "`Role_Id` = ?,\n"
                        + "`avatar` = ?\n"
                        + "WHERE `id` = ?";
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, fullname);
                stm.setBoolean(3, gender);
                stm.setString(4, address);
                stm.setString(5, email);
                stm.setString(6, phonenumber);
                stm.setInt(7, status);
                stm.setInt(8, role_id);
                stm.setBlob(9, fileContent);
                stm.setInt(10, id);
                stm.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                String sql = "UPDATE `onlinelearn`.`user`\n"
                        + "SET\n"
                        + "`username` = ?,\n"
                        + "`fullname` = ?,\n"
                        + "`gender` = ?,\n"
                        + "`address` = ?,\n"
                        + "`email` = ?,\n"
                        + "`phonenumber` = ?,\n"
                        + "`status` = ?,\n"
                        + "`Role_Id` = ?,\n"
                        + "WHERE `id` = ?";
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, fullname);
                stm.setBoolean(3, gender);
                stm.setString(4, address);
                stm.setString(5, email);
                stm.setString(6, phonenumber);
                stm.setInt(7, status);
                stm.setInt(8, role_id);
                stm.setInt(10, id);
                stm.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public User getUserById(int id) {
        try {
            String sql = "SELECT * FROM onlinelearn.user inner join onlinelearn.role\n"
                    + "on onlinelearn.user.Role_Id = onlinelearn.role.role_id\n"
                    + "where onlinelearn.user.id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setFullname(rs.getString("fullname"));
                u.setGender(rs.getBoolean("gender"));
                u.setAddress(rs.getString("address"));
                u.setEmail(rs.getString("email"));
                u.setPhonenumber(rs.getString("phonenumber"));
                u.setStatus(rs.getInt("status"));
                Role r = new Role();
                r.setRole_id(rs.getInt("role_id"));
                r.setRole_name(rs.getString("role_name"));
                u.setAvatar(rs.getBlob("avatar"));
                u.setRole(r);
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public ArrayList<Status> getAllStatus() {
        ArrayList<Status> list = new ArrayList<>();
        try {
            String sql = "SELECT `status`.`status_id`,\n"
                    + "    `status`.`status_name`\n"
                    + "FROM `onlinelearn`.`status`;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Status s = new Status();
                s.setStatus_id(rs.getInt("status_id"));
                s.setStatus_name(rs.getString("status_name"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int countPagintionUserList(int role, int status, String sort) {
        try {
            String sql = "SELECT COUNT(*) AS total FROM onlinelearn.user "
                    + "AS u JOIN onlinelearn.role AS r "
                    + "ON u.Role_Id = r.role_id";
            PreparedStatement stm = null;
            if (role == -1 && status == -1) {
                switch (sort) {
                    case "id":
                        sql += " ORDER BY id ASC";
                        break;
                    case "name":
                        sql += " ORDER BY fullname ASC";
                        break;
                    case "gender":
                        sql += " ORDER BY gender ASC";
                        break;
                    case "email":
                        sql += " ORDER BY email ASC";
                        break;
                    case "moblie":
                        sql += " ORDER BY phonenumber ASC";
                        break;
                    case "role":
                        sql += " ORDER BY Role_id ASC";
                        break;
                    case "status":
                        sql += " ORDER BY status ASC";
                        break;
                }
                stm = connection.prepareStatement(sql);
            }
            if (role != -1 && status == -1) {
                sql += " WHERE u.Role_Id = ?";
                switch (sort) {
                    case "id":
                        sql += " ORDER BY id ASC";
                        break;
                    case "name":
                        sql += " ORDER BY fullname ASC";
                        break;
                    case "gender":
                        sql += " ORDER BY gender ASC";
                        break;
                    case "email":
                        sql += " ORDER BY email ASC";
                        break;
                    case "moblie":
                        sql += " ORDER BY phonenumber ASC";
                        break;
                    case "role":
                        sql += " ORDER BY Role_id ASC";
                        break;
                    case "status":
                        sql += " ORDER BY status ASC";
                        break;
                }
                stm = connection.prepareStatement(sql);
                stm.setInt(1, role);
            } else if (role == -1 && status != -1) {
                sql += " WHERE u.status = ?";
                switch (sort) {
                    case "id":
                        sql += " ORDER BY id ASC";
                        break;
                    case "name":
                        sql += " ORDER BY fullname ASC";
                        break;
                    case "gender":
                        sql += " ORDER BY gender ASC";
                        break;
                    case "email":
                        sql += " ORDER BY email ASC";
                        break;
                    case "moblie":
                        sql += " ORDER BY phonenumber ASC";
                        break;
                    case "role":
                        sql += " ORDER BY Role_id ASC";
                        break;
                    case "status":
                        sql += " ORDER BY status ASC";
                        break;
                }

                stm = connection.prepareStatement(sql);
                stm.setInt(1, status);
            } else if (role != -1 && status != -1) {
                sql += " WHERE u.Role_Id = ? AND u.status = ?";
                switch (sort) {
                    case "id":
                        sql += " ORDER BY id ASC";
                        break;
                    case "name":
                        sql += " ORDER BY fullname ASC";
                        break;
                    case "gender":
                        sql += " ORDER BY gender ASC";
                        break;
                    case "email":
                        sql += " ORDER BY email ASC";
                        break;
                    case "moblie":
                        sql += " ORDER BY phonenumber ASC";
                        break;
                    case "role":
                        sql += " ORDER BY Role_id ASC";
                        break;
                    case "status":
                        sql += " ORDER BY status ASC";
                        break;
                }

                stm = connection.prepareStatement(sql);
                stm.setInt(1, role);
                stm.setInt(2, status);
            }
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public List<User> getPagintionUserList(int index, int role, int status, String sort) {
        ArrayList<User> list = new ArrayList<>();
        try {
            String sql = "SELECT `user`.`id`,\n"
                    + "    `user`.`username`,\n"
                    + "    `user`.`password`,\n"
                    + "    `user`.`fullname`,\n"
                    + "    `user`.`gender`,\n"
                    + "    `user`.`address`,\n"
                    + "    `user`.`email`,\n"
                    + "    `user`.`phonenumber`,\n"
                    + "    `user`.`status`,\n"
                    + "    `user`.`Role_Id`,\n"
                    + "     role.role_name\n"
                    + "FROM `onlinelearn`.`user` AS user \n"
                    + "JOIN onlinelearn.role AS role \n"
                    + "ON user.Role_Id = role.role_id";
            PreparedStatement stm = null;
            if (role == -1 && status == - 1) {
                switch (sort) {
                    case "id":
                        sql += " ORDER BY id ASC";
                        break;
                    case "name":
                        sql += " ORDER BY fullname ASC";
                        break;
                    case "gender":
                        sql += " ORDER BY gender ASC";
                        break;
                    case "email":
                        sql += " ORDER BY email ASC";
                        break;
                    case "moblie":
                        sql += " ORDER BY phonenumber ASC";
                        break;
                    case "role":
                        sql += " ORDER BY Role_id ASC";
                        break;
                    case "status":
                        sql += " ORDER BY status ASC";
                        break;
                }
                sql += " limit 5 offset ?;";
                stm = connection.prepareStatement(sql);
                stm.setInt(1, index * 5);
            }
            if (role != -1 && status == -1) {
                sql += " WHERE user.Role_Id = ?";
                switch (sort) {
                    case "id":
                        sql += " ORDER BY id ASC";
                        break;
                    case "name":
                        sql += " ORDER BY fullname ASC";
                        break;
                    case "gender":
                        sql += " ORDER BY gender ASC";
                        break;
                    case "email":
                        sql += " ORDER BY email ASC";
                        break;
                    case "moblie":
                        sql += " ORDER BY phonenumber ASC";
                        break;
                    case "role":
                        sql += " ORDER BY Role_id ASC";
                        break;
                    case "status":
                        sql += " ORDER BY status ASC";
                        break;
                }
                sql += " limit 5 offset ?;";
                stm = connection.prepareStatement(sql);
                stm.setInt(1, role);
                stm.setInt(2, index * 5);
            } else if (role == -1 && status != -1) {
                sql += " WHERE user.status = ?";
                switch (sort) {
                    case "id":
                        sql += " ORDER BY id ASC";
                        break;
                    case "name":
                        sql += " ORDER BY fullname ASC";
                        break;
                    case "gender":
                        sql += " ORDER BY gender ASC";
                        break;
                    case "email":
                        sql += " ORDER BY email ASC";
                        break;
                    case "moblie":
                        sql += " ORDER BY phonenumber ASC";
                        break;
                    case "role":
                        sql += " ORDER BY Role_id ASC";
                        break;
                    case "status":
                        sql += " ORDER BY status ASC";
                        break;
                }
                sql += " limit 5 offset ?;";
                stm = connection.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setInt(2, index * 5);
            } else if (role != -1 && status != -1) {
                sql += " WHERE user.Role_Id = ? AND user.status = ?";
                switch (sort) {
                    case "id":
                        sql += " ORDER BY id ASC";
                        break;
                    case "name":
                        sql += " ORDER BY fullname ASC";
                        break;
                    case "gender":
                        sql += " ORDER BY gender ASC";
                        break;
                    case "email":
                        sql += " ORDER BY email ASC";
                        break;
                    case "moblie":
                        sql += " ORDER BY phonenumber ASC";
                        break;
                    case "role":
                        sql += " ORDER BY Role_id ASC";
                        break;
                    case "status":
                        sql += " ORDER BY status ASC";
                        break;
                }
                sql += " limit 5 offset ?;";
                stm = connection.prepareStatement(sql);
                stm.setInt(1, role);
                stm.setInt(2, status);
                stm.setInt(3, index * 5);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setFullname(rs.getString("fullname"));
                u.setGender(rs.getBoolean("gender"));
                u.setAddress(rs.getString("address"));
                u.setEmail(rs.getString("email"));
                u.setPhonenumber(rs.getString("phonenumber"));
                u.setStatus(rs.getInt("status"));
                Role r = new Role();
                r.setRole_id(rs.getInt("role_id"));
                r.setRole_name(rs.getString("role_name"));
                u.setRole(r);
                list.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int countPagintionUserSearch(int role, int status, String keyword, String sort) {
        try {
            String sql = "SELECT COUNT(*) AS total FROM onlinelearn.user "
                    + "AS u JOIN onlinelearn.role AS r "
                    + "ON u.Role_Id = r.role_id";
            PreparedStatement stm = null;
            if (role == -1 && status == -1) {
                sql += " WHERE u.fullname LIKE '%" + keyword + "%'  OR u.email LIKE '%" + keyword + "%' OR u.phonenumber LIKE '%" + keyword + "%'";
                switch (sort) {
                    case "id":
                        sql += " ORDER BY id ASC";
                        break;
                    case "name":
                        sql += " ORDER BY fullname ASC";
                        break;
                    case "gender":
                        sql += " ORDER BY gender ASC";
                        break;
                    case "email":
                        sql += " ORDER BY email ASC";
                        break;
                    case "moblie":
                        sql += " ORDER BY phonenumber ASC";
                        break;
                    case "role":
                        sql += " ORDER BY Role_id ASC";
                        break;
                    case "status":
                        sql += " ORDER BY status ASC";
                        break;
                }
                stm = connection.prepareStatement(sql);
            }
            if (role != -1 && status == -1) {
                sql += " WHERE u.fullname LIKE '%" + keyword + "%'  OR u.email LIKE '%" + keyword + "%' OR u.phonenumber LIKE '%" + keyword + "%'";
                sql += " AND u.Role_Id = ?";
                switch (sort) {
                    case "id":
                        sql += " ORDER BY id ASC";
                        break;
                    case "name":
                        sql += " ORDER BY fullname ASC";
                        break;
                    case "gender":
                        sql += " ORDER BY gender ASC";
                        break;
                    case "email":
                        sql += " ORDER BY email ASC";
                        break;
                    case "moblie":
                        sql += " ORDER BY phonenumber ASC";
                        break;
                    case "role":
                        sql += " ORDER BY Role_id ASC";
                        break;
                    case "status":
                        sql += " ORDER BY status ASC";
                        break;
                }
                stm = connection.prepareStatement(sql);
                stm.setInt(1, role);
            } else if (role == -1 && status != -1) {
                sql += " WHERE u.fullname LIKE '%" + keyword + "%'  OR u.email LIKE '%" + keyword + "%' OR u.phonenumber LIKE '%" + keyword + "%'";
                sql += " AND u.status = ?";
                switch (sort) {
                    case "id":
                        sql += " ORDER BY id ASC";
                        break;
                    case "name":
                        sql += " ORDER BY fullname ASC";
                        break;
                    case "gender":
                        sql += " ORDER BY gender ASC";
                        break;
                    case "email":
                        sql += " ORDER BY email ASC";
                        break;
                    case "moblie":
                        sql += " ORDER BY phonenumber ASC";
                        break;
                    case "role":
                        sql += " ORDER BY Role_id ASC";
                        break;
                    case "status":
                        sql += " ORDER BY status ASC";
                        break;
                }

                stm = connection.prepareStatement(sql);
                stm.setInt(1, status);
            } else if (role != -1 && status != -1) {
                sql += " WHERE u.fullname LIKE '%" + keyword + "%'  OR u.email LIKE '%" + keyword + "%' OR u.phonenumber LIKE '%" + keyword + "%'";
                sql += " AND u.Role_Id = ? AND u.status = ?";
                switch (sort) {
                    case "id":
                        sql += " ORDER BY id ASC";
                        break;
                    case "name":
                        sql += " ORDER BY fullname ASC";
                        break;
                    case "gender":
                        sql += " ORDER BY gender ASC";
                        break;
                    case "email":
                        sql += " ORDER BY email ASC";
                        break;
                    case "moblie":
                        sql += " ORDER BY phonenumber ASC";
                        break;
                    case "role":
                        sql += " ORDER BY Role_id ASC";
                        break;
                    case "status":
                        sql += " ORDER BY status ASC";
                        break;
                }

                stm = connection.prepareStatement(sql);
                stm.setInt(1, role);
                stm.setInt(2, status);
            }
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public List<User> getSearchPagintionUserList(int index, int role, int status, String keyword, String sort) {
        ArrayList<User> list = new ArrayList<>();
        try {
            String sql = "SELECT `user`.`id`,\n"
                    + "    `user`.`username`,\n"
                    + "    `user`.`password`,\n"
                    + "    `user`.`fullname`,\n"
                    + "    `user`.`gender`,\n"
                    + "    `user`.`address`,\n"
                    + "    `user`.`email`,\n"
                    + "    `user`.`phonenumber`,\n"
                    + "    `user`.`status`,\n"
                    + "    `user`.`Role_Id`,\n"
                    + "     role.role_name\n"
                    + "FROM `onlinelearn`.`user` AS user \n"
                    + "JOIN onlinelearn.role AS role \n"
                    + "ON user.Role_Id = role.role_id";
            PreparedStatement stm = null;
            if (role == -1 && status == - 1) {
                sql += " WHERE user.fullname LIKE '%" + keyword + "%'  OR user.email LIKE '%" + keyword + "%' OR user.phonenumber LIKE '%" + keyword + "%'";
                switch (sort) {
                    case "id":
                        sql += " ORDER BY id ASC";
                        break;
                    case "name":
                        sql += " ORDER BY fullname ASC";
                        break;
                    case "gender":
                        sql += " ORDER BY gender ASC";
                        break;
                    case "email":
                        sql += " ORDER BY email ASC";
                        break;
                    case "moblie":
                        sql += " ORDER BY phonenumber ASC";
                        break;
                    case "role":
                        sql += " ORDER BY Role_id ASC";
                        break;
                    case "status":
                        sql += " ORDER BY status ASC";
                        break;
                }
                sql += " limit 5 offset ?";
                stm = connection.prepareStatement(sql);
                stm.setInt(1, index * 5);
            }
            if (role != -1 && status == -1) {
                sql += " WHERE user.fullname LIKE '%" + keyword + "%'  OR user.email LIKE '%" + keyword + "%' OR user.phonenumber LIKE '%" + keyword + "%'";
                sql += " AND user.Role_Id = ?";
                switch (sort) {
                    case "id":
                        sql += " ORDER BY id ASC";
                        break;
                    case "name":
                        sql += " ORDER BY fullname ASC";
                        break;
                    case "gender":
                        sql += " ORDER BY gender ASC";
                        break;
                    case "email":
                        sql += " ORDER BY email ASC";
                        break;
                    case "moblie":
                        sql += " ORDER BY phonenumber ASC";
                        break;
                    case "role":
                        sql += " ORDER BY Role_id ASC";
                        break;
                    case "status":
                        sql += " ORDER BY status ASC";
                        break;
                }
                sql += " limit 5 offset ?";
                stm = connection.prepareStatement(sql);
                stm.setInt(1, role);
                stm.setInt(2, index * 5);
            } else if (role == -1 && status != -1) {
                sql += " WHERE user.fullname LIKE '%" + keyword + "%'  OR user.email LIKE '%" + keyword + "%' OR user.phonenumber LIKE '%" + keyword + "%'";
                sql += " AND user.status = ?";
                switch (sort) {
                    case "id":
                        sql += " ORDER BY id ASC";
                        break;
                    case "name":
                        sql += " ORDER BY fullname ASC";
                        break;
                    case "gender":
                        sql += " ORDER BY gender ASC";
                        break;
                    case "email":
                        sql += " ORDER BY email ASC";
                        break;
                    case "moblie":
                        sql += " ORDER BY phonenumber ASC";
                        break;
                    case "role":
                        sql += " ORDER BY Role_id ASC";
                        break;
                    case "status":
                        sql += " ORDER BY status ASC";
                        break;
                }
                sql += " limit 5 offset ?";
                stm = connection.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setInt(2, index * 5);
            } else if (role != -1 && status != -1) {
                sql += " WHERE user.fullname LIKE '%" + keyword + "%'  OR user.email LIKE '%" + keyword + "%' OR user.phonenumber LIKE '%" + keyword + "%'";
                sql += " AND user.Role_Id = ? AND user.status = ?";
                switch (sort) {
                    case "id":
                        sql += " ORDER BY id ASC";
                        break;
                    case "name":
                        sql += " ORDER BY fullname ASC";
                        break;
                    case "gender":
                        sql += " ORDER BY gender ASC";
                        break;
                    case "email":
                        sql += " ORDER BY email ASC";
                        break;
                    case "moblie":
                        sql += " ORDER BY phonenumber ASC";
                        break;
                    case "role":
                        sql += " ORDER BY Role_id ASC";
                        break;
                    case "status":
                        sql += " ORDER BY status ASC";
                        break;
                }
                sql += " limit 5 offset ?";
                stm = connection.prepareStatement(sql);
                stm.setInt(1, role);
                stm.setInt(2, status);
                stm.setInt(3, index * 5);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setFullname(rs.getString("fullname"));
                u.setGender(rs.getBoolean("gender"));
                u.setAddress(rs.getString("address"));
                u.setEmail(rs.getString("email"));
                u.setPhonenumber(rs.getString("phonenumber"));
                u.setStatus(rs.getInt("status"));
                Role r = new Role();
                r.setRole_id(rs.getInt("role_id"));
                r.setRole_name(rs.getString("role_name"));
                u.setRole(r);
                list.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void main(String[] args) {
        UserDAO u = new UserDAO();
        List<User> list = u.getSearchPagintionUserList(0, 1, 1, "hiep", "id");
        for (User user : list) {
            System.out.println(user.getFullname());
        }
//        int count = u.countPagintionUserSearch(-1, -1, "hiep", "id");
//
//        System.out.println(count);
    }
}
