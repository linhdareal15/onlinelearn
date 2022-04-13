/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Course;
import Model.Registration;
import Model.RegistrationStatus;
import Model.User;
import Model.UserCourse;
import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quang
 */
public class UserCourseDAO extends DBContext {

    public boolean registerCourse(User u, Course c, Registration r) {
        try {
            connection.setAutoCommit(false);
            String sql_registration = "INSERT INTO `onlinelearn`.`registrations`\n"
                    + "(`course_id`,\n"
                    + "`registration_time`,\n"
                    + "`totalcost`,\n"
                    + "`status_id`,\n"
                    + "`update_time`)\n"
                    + "VALUES\n"
                    + "(?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?);";
            PreparedStatement stm_registration = connection.prepareCall(sql_registration);
            stm_registration.setInt(1, c.getCid());
            stm_registration.setTimestamp(2, r.getRegistrationTime());
            stm_registration.setDouble(3, c.getListprice());
            stm_registration.setInt(4, r.getRegistrationStatus().getId());
            stm_registration.setTimestamp(5, r.getUpdateTime());
            stm_registration.executeUpdate();

            String sql_get_identity = "SELECT @@IDENTITY AS registration_id";
            PreparedStatement stm_get_identity = connection.prepareCall(sql_get_identity);
            ResultSet rs_get_identity = stm_get_identity.executeQuery();
            if (rs_get_identity.next()) {
                r.setId(rs_get_identity.getInt("registration_id"));
            }

            String sql_register_course = "INSERT INTO `onlinelearn`.`user_registrations_course`\n"
                    + "(`registration_id`,\n"
                    + "`user_id`)\n"
                    + "VALUES\n"
                    + "(?,\n"
                    + "?);";
            PreparedStatement stm_register_course = connection.prepareCall(sql_register_course);
            stm_register_course.setInt(1, r.getId());
            stm_register_course.setInt(2, u.getId());
            stm_register_course.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(UserCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public int getTotalWishList(int id, String courseName) {
        try {
            String sql = "SELECT COUNT(*) AS Total FROM onlinelearn.user AS u \n"
                    + "JOIN onlinelearn.user_registrations_course AS urc ON u.id = urc.user_id\n"
                    + "LEFT JOIN onlinelearn.registrations AS r ON urc.registration_id = r.id\n"
                    + "LEFT JOIN onlinelearn.registrations_status AS rs ON r.status_id = rs.registration_status_id\n"
                    + "LEFT JOIN onlinelearn.course AS c ON r.course_id = c.cid \n"
                    + "WHERE (1=1) AND rs.registration_status_id = 3";
            if (courseName != null) {
                sql += "AND c.title LIKE '%" + courseName + "%'";
            }
            sql += " AND u.id = '" + id + "'";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Registration> getWithListBySearch(int indexPage, int id, String courseName) {
        ArrayList<Registration> list = new ArrayList<>();
        try {
            String sql = "SELECT c.cid, c.title, r.id, r.registration_time, c.listprice, rs.registration_status_id , rs.registrations_status_name FROM onlinelearn.user AS u \n"
                    + "JOIN onlinelearn.user_registrations_course AS urc ON u.id = urc.user_id\n"
                    + "LEFT JOIN onlinelearn.registrations AS r ON urc.registration_id = r.id\n"
                    + "LEFT JOIN onlinelearn.registrations_status AS rs ON r.status_id = rs.registration_status_id\n"
                    + "LEFT JOIN onlinelearn.course AS c ON r.course_id = c.cid \n"
                    + "WHERE (1=1) AND rs.registration_status_id = 3";
            if (courseName != null) {
                sql += "AND c.title LIKE '%" + courseName + "%'";
            }
            sql += " AND u.id = '" + id + "'";
            sql += " ORDER BY c.cid LIMIT 5 OFFSET ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, indexPage * 5);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Registration r = new Registration();
                r.setId(rs.getInt("r.id"));
                r.setRegistrationTime(rs.getTimestamp("r.registration_time"));
                Course c = new Course();
                c.setCid(rs.getInt("c.cid"));
                c.setTitle(rs.getString("c.title"));
                c.setListprice(rs.getDouble("c.listprice"));
                RegistrationStatus rst = new RegistrationStatus();
                rst.setId(rs.getInt("rs.registration_status_id"));
                rst.setName(rs.getString("rs.registrations_status_name"));
                r.setRegistrationStatus(rst);
                r.setCourse(c);
                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public int getTotalMyCourse(int id, String courseName) {
        try {
            String sql = "SELECT COUNT(*) AS Total FROM onlinelearn.user AS u \n"
                    + "JOIN onlinelearn.user_registrations_course AS urc ON u.id = urc.user_id\n"
                    + "LEFT JOIN onlinelearn.registrations AS r ON urc.registration_id = r.id\n"
                    + "LEFT JOIN onlinelearn.registrations_status AS rs ON r.status_id = rs.registration_status_id\n"
                    + "LEFT JOIN onlinelearn.course AS c ON r.course_id = c.cid \n"
                    + "WHERE (1=1) AND rs.registration_status_id = 1";
            if (courseName != null) {
                sql += "AND c.title LIKE '%" + courseName + "%'";
            }
            sql += " AND u.id = '" + id + "'";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Registration> getMyCourseBySearch(int indexPage, int id, String courseName) {
        ArrayList<Registration> list = new ArrayList<>();
        try {
            String sql = "SELECT c.cid, c.title, r.id, r.registration_time, c.listprice, rs.registration_status_id , rs.registrations_status_name FROM onlinelearn.user AS u \n"
                    + "JOIN onlinelearn.user_registrations_course AS urc ON u.id = urc.user_id\n"
                    + "LEFT JOIN onlinelearn.registrations AS r ON urc.registration_id = r.id\n"
                    + "LEFT JOIN onlinelearn.registrations_status AS rs ON r.status_id = rs.registration_status_id\n"
                    + "LEFT JOIN onlinelearn.course AS c ON r.course_id = c.cid \n"
                    + "WHERE (1=1) AND rs.registration_status_id = 1";
            if (courseName != null) {
                sql += "AND c.title LIKE '%" + courseName + "%'";
            }
            sql += " AND u.id = '" + id + "'";
            sql += " ORDER BY c.cid LIMIT 5 OFFSET ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, indexPage * 5);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Registration r = new Registration();
                r.setId(rs.getInt("r.id"));
                r.setRegistrationTime(rs.getTimestamp("r.registration_time"));
                Course c = new Course();
                c.setCid(rs.getInt("c.cid"));
                c.setTitle(rs.getString("c.title"));
                c.setListprice(rs.getDouble("c.listprice"));
                RegistrationStatus rst = new RegistrationStatus();
                rst.setId(rs.getInt("rs.registration_status_id"));
                rst.setName(rs.getString("rs.registrations_status_name"));
                r.setRegistrationStatus(rst);
                r.setCourse(c);
                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    
    public boolean cancelCourse(Registration r) {
        try {
            String sql = "UPDATE `onlinelearn`.`registrations`\n"
                    + "SET\n"
                    + "`status_id` = 4, "
                    + "`update_time` =  ?,"
                    + "`lastupdateby` = ?\n"
                    + "WHERE `id` = ?;";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setTimestamp(1, r.getUpdateTime());
            stm.setString(2, r.getRegisBy().getFullname());
            stm.setInt(3, r.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
