/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Course;
import Model.CourseCategory;
import Model.CourseRegistration;
import Model.Registration;
import Model.RegistrationStatus;
import Model.User;
import context.DBContext;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Louis
 */
public class CourseDAO extends DBContext {
//    Client-Start

    public Course getCourse(int course_id) {
        String sql_Course = "SELECT * FROM onlinelearn.course WHERE cid = ?";
        Course c = new Course();
        try {
            PreparedStatement stm_Course = connection.prepareStatement(sql_Course);
            stm_Course.setInt(1, course_id);
            ResultSet rs = stm_Course.executeQuery();
            while (rs.next()) {
                c.setCid(rs.getInt(1));
                c.setTitle(rs.getString("title"));
                c.setThumbnail(rs.getBlob("thumbnail"));
                c.setBriefinfo(rs.getString("briefinfo"));
                c.setFeatureflag(rs.getBoolean("featureflag"));
                c.setIntroduction(rs.getString("introduction"));
                c.setListprice(rs.getDouble("listprice"));
                c.setSaleprice(rs.getDouble("saleprice"));
                c.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public List<Course> getAllCourse() {
        List<Course> list = new ArrayList<>();
        try {
            String sql = "SELECT  c.cid, c.title, c.thumbnail , c.briefinfo, c.introduction, c.listprice, c.saleprice, c.status, c.featureflag, c.updatedate, u.fullname, c.status\n"
                    + "FROM onlinelearn.course as c join onlinelearn.user as u ON c.author = u.id "
                    + "AND c.status=1 ";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setCid(rs.getInt(1));
                c.setTitle(rs.getString("title"));
                c.setThumbnail(rs.getBlob("thumbnail"));
                c.setBriefinfo(rs.getString("briefinfo"));
                c.setFeatureflag(rs.getBoolean("featureflag"));
                User u = new User();
                u.setFullname(rs.getString("fullname"));
                c.setAuthor(u);
                c.setIntroduction(rs.getString("introduction"));
                c.setListprice(rs.getDouble("listprice"));
                c.setSaleprice(rs.getDouble("saleprice"));
                c.setStatus(rs.getBoolean("status"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("Error at getAllCourse: ");
            System.out.println(e.getMessage());
        }

        return list;
    }

    public List<Course> getAllCoursePagging(String name, int pageIndex, int pageSize) {
        List<Course> list = new ArrayList<>();
        try {
            String sql = "SELECT  c.cid, c.title, c.thumbnail , c.briefinfo, c.introduction, c.listprice, c.saleprice, c.status, c.featureflag, c.updatedate, u.fullname, c.status\n"
                    + "FROM onlinelearn.course as c join onlinelearn.user as u ON c.author = u.id "
                    + "AND c.status=1 ";
            if (name != "") {
                sql += "AND c.title LIKE '%" + name + "%' \n";
            }
            sql += "order by c.cid LIMIT " + pageIndex + "," + pageSize;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setCid(rs.getInt(1));
                c.setTitle(rs.getString("title"));
                c.setThumbnail(rs.getBlob("thumbnail"));
                c.setBriefinfo(rs.getString("briefinfo"));
                c.setFeatureflag(rs.getBoolean("featureflag"));
                User u = new User();
                u.setFullname(rs.getString("fullname"));
                c.setAuthor(u);
                c.setIntroduction(rs.getString("introduction"));
                c.setListprice(rs.getDouble("listprice"));
                c.setSaleprice(rs.getDouble("saleprice"));
                c.setStatus(rs.getBoolean("status"));
                list.add(c);
            }
            return list;
        } catch (Exception e) {
            System.out.println("Error at pagging course");
            System.out.println(e.getMessage());
        }
        return null;
    }
//    

    public int totalCourse(String name) {
        try {
            String sql = "select count(cid) as total from onlinelearn.course ";
            if (name != "") {
                sql += "WHERE course.title LIKE '%" + name + "%'";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
//    public CourseRegistration CheckCourseRegistedByCidAndUserID(int cid, int userId){
//        try {
//            String sql = "Select distinct c.*, r.status_id\n"
//                    + "From onlinelearn.user_registrations_course as urc, onlinelearn.registrations as r,\n"
//                    + "onlinelearn.course as c\n"
//                    + "where urc.registration_id = r.id\n"
//                    + "and r.course_id = ?\n"
//                    + "and urc.user_id = ? ;";
//            PreparedStatement stm = connection.prepareStatement(sql);
//            stm.setInt(1, cid);
//            stm.setInt(2, userId);
//            ResultSet rs = stm.executeQuery();
//            CourseRegistration cr =null;
//            while (rs.next()) {
//                Course c = new Course();
//                c.setCid(rs.getInt(1));
//                c.setTitle(rs.getString("title"));
//                c.setThumbnail(rs.getBlob("thumbnail"));
//                c.setBriefinfo(rs.getString("briefinfo"));
//                c.setFeatureflag(rs.getBoolean("featureflag"));
//                c.setIntroduction(rs.getString("introduction"));
//                c.setListprice(rs.getDouble("listprice"));
//                c.setSaleprice(rs.getDouble("saleprice"));
//                c.setStatus(rs.getBoolean("status"));
//                int status_id = rs.getInt("status_id");
//                cr=new CourseRegistration(c, status_id);
//            }
//            if(cr!=null) return cr;
//        } catch (Exception e) {
//        }
//        return null;
//    }
    
    public List<CourseRegistration> GetListCourseRegistedByUserID(int userId) {
        List<CourseRegistration> list = new ArrayList<>();

        try {
            String sql = "Select distinct c.*, r.status_id\n"
                    + "From onlinelearn.user_registrations_course as urc, onlinelearn.registrations as r,\n"
                    + "onlinelearn.course as c\n"
                    + "where urc.registration_id = r.id\n"
                    + "and r.course_id = c.cid\n"
                    + "and urc.user_id = ? ;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setCid(rs.getInt(1));
                c.setTitle(rs.getString("title"));
                c.setThumbnail(rs.getBlob("thumbnail"));
                c.setBriefinfo(rs.getString("briefinfo"));
                c.setFeatureflag(rs.getBoolean("featureflag"));
                c.setIntroduction(rs.getString("introduction"));
                c.setListprice(rs.getDouble("listprice"));
                c.setSaleprice(rs.getDouble("saleprice"));
                c.setStatus(rs.getBoolean("status"));
                int status_id = rs.getInt("status_id");
                list.add(new CourseRegistration(c, status_id));
            }
        } catch (Exception e) {
            System.out.println("Error at GetListCourseRegistedByUserID");
            System.out.println(e.getMessage());
        }

        return list;
    }

    //ADMIN-start
    public ArrayList<Course> getAllCourseSubject(int index, int status, int author, String title) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT course.cid,\n"
                    + "		`course`.`title`,\n"
                    + "		u.fullname,\n"
                    + "		`course`.`listprice`,\n"
                    + "		`course`.`saleprice`,\n"
                    + "		`course`.`status`\n"
                    + "FROM `onlinelearn`.`course` AS course "
                    + "JOIN onlinelearn.user "
                    + "AS u ON course.author = u.id";
            if (status != -1) {
                sql += " AND course.status ='" + status + "'";
            }
            if (author != -1) {
                sql += " AND course.author = '" + author + "'";
            }
            if (title != null) {
                sql += " AND course.title like '%" + title + "%'";
            }
            sql += " LIMIT 3 OFFSET ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, index * 3);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setCid(rs.getInt("cid"));
                c.setTitle(rs.getString("title"));
                User u = new User();
                u.setFullname(rs.getString("fullname"));
                c.setAuthor(u);
                c.setListprice(rs.getDouble("listprice"));
                c.setSaleprice(rs.getDouble("saleprice"));
                c.setStatus(rs.getBoolean("status"));
                courses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
    }

    public int getRowcount(int status, int author, String title) {
        try {
            String sql = "SELECT COUNT(*) AS TOTAL FROM onlinelearn.course AS c \n"
                    + "JOIN onlinelearn.user AS u ON c.author = u.id  WHERE (1=1)";
            if (status != -1) {
                sql += " AND c.status ='" + status + "'";
            }
            if (author != -1) {
                sql += " AND c.author = '" + author + "'";
            }
            if (title != null) {
                sql += " AND c.title like '%" + title + "%'";
            }
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("TOTAL");
            }
        } catch (SQLException e) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return -1;
    }

    public void updateStatus(int id, int status) {
        try {
            String sql = "UPDATE `onlinelearn`.`course`\n"
                    + "SET\n"
                    + "`status` = ?\n"
                    + "WHERE `cid` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            if (status == 1) {
                stm.setBoolean(1, false);
            } else {
                stm.setBoolean(1, true);
            }
            stm.setInt(2, id);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int insertSubject(Course c, InputStream fileContent, InputStream fileDocument) {
        int row = 0;
        try {
            String sql = "INSERT INTO `onlinelearn`.`course`\n"
                    + "(`title`,\n"
                    + "`thumbnail`,\n"
                    + "`briefinfo`,\n"
                    + "`featureflag`,\n"
                    + "`author`,\n"
                    + "`introduction`,\n"
                    + "`listprice`,\n"
                    + "`saleprice`,\n"
                    + "`status`,\n"
                    + "`updatedate`,"
                    + "`document`,"
                    + "category_id,"
                    + "filedocumentname)\n"
                    + "VALUES\n"
                    + "(?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, c.getTitle());
            stm.setBlob(2, fileContent);
            stm.setString(3, c.getBriefinfo());
            stm.setBoolean(4, c.isFeatureflag());
            stm.setInt(5, c.getAuthor().getId());
            stm.setString(6, c.getIntroduction());
            stm.setDouble(7, c.getListprice());
            stm.setDouble(8, c.getSaleprice());
            stm.setBoolean(9, c.isStatus());
            stm.setDate(10, c.getUpdatedate());
            stm.setBlob(11, fileDocument);
            stm.setInt(12, c.getCategory().getId());
            stm.setString(13, c.getFilename());
            row = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public Course getCourseById(int id) {
        try {
            String sql = "SELECT * \n"
                    + "FROM `onlinelearn`.`course` WHERE cid = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Course c = new Course();
                c.setCid(rs.getInt("cid"));
                c.setTitle(rs.getString("title"));
                c.setThumbnail(rs.getBlob("thumbnail"));
                c.setBriefinfo(rs.getString("briefinfo"));
                User u = new User();
                u.setId(rs.getInt("author"));
                c.setAuthor(u);
                c.setIntroduction(rs.getString("introduction"));
                c.setListprice(rs.getDouble("listprice"));
                c.setSaleprice(rs.getDouble("saleprice"));
                c.setStatus(rs.getBoolean("status"));
                c.setFeatureflag(rs.getBoolean("featureflag"));
                c.setDocument(rs.getBlob("document"));
                //c.setFilename(rs.getString("filedocumentname"));
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int updateSubject(Course c, InputStream fileContent, InputStream fileDocument) {
        int row = 0;
        try {
            String sql = "UPDATE `onlinelearn`.`course`\n"
                    + "SET\n"
                    + "`title` = ?,\n"
                    + "`briefinfo` = ?,\n"
                    + "`author` = ?,\n"
                    + "`introduction` = ?,\n"
                    + "`listprice` = ?,\n"
                    + "`saleprice` = ?,\n"
                    + "`status` = ?,\n"
                    + "`featureflag` = ?,\n"
                    + "`updatedate` = ?\n";
            if (fileContent != null) {
                sql += ",`thumbnail` = ?\n";
            }
            if (fileDocument != null) {
                sql += ",`document` = ?, filedocumentname = ? \n";
            }
            sql += " WHERE `cid` = ?;";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, c.getTitle());
            stm.setString(2, c.getBriefinfo());
            stm.setInt(3, c.getAuthor().getId());
            stm.setString(4, c.getIntroduction());
            stm.setDouble(5, c.getListprice());
            stm.setDouble(6, c.getSaleprice());
            stm.setBoolean(7, c.isStatus());
            stm.setBoolean(8, c.isFeatureflag());
            stm.setDate(9, c.getUpdatedate());
            if (fileContent != null && fileDocument != null) {
                stm.setBlob(10, fileContent);
                stm.setBlob(11, fileDocument);
                stm.setString(12, c.getFilename());
                stm.setInt(13, c.getCid());
            } else if (fileContent != null && fileDocument == null) {
                stm.setBlob(10, fileContent);
                stm.setInt(11, c.getCid());
            } else if (fileContent == null && fileDocument != null) {
                stm.setBlob(10, fileDocument);
                stm.setString(11, c.getFilename());
                stm.setInt(12, c.getCid());
            } else {
                stm.setInt(10, c.getCid());
            }
            row = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public ArrayList<CourseCategory> getAllCourseCategory() {
        ArrayList<CourseCategory> categorys = new ArrayList<>();
        try {
            String sql = "SELECT `subject_category`.`id`,\n"
                    + "    `subject_category`.`category_name`\n"
                    + "FROM `onlinelearn`.`subject_category`";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CourseCategory c = new CourseCategory();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("category_name"));
                categorys.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorys;
    }

    public int totalRegistration(int cid, String email, String from, String to, int status) {
        int count = 0;
        try {
            String sql = "Select Count(r.id) "
                    + "from onlinelearn.user_registrations_course as ur, onlinelearn.registrations as r, onlinelearn.user as u\n"
                    + "where u.id = ur.user_id AND r.id=registration_id \n";
            if (email != "") {
                sql += "AND u.email = '" + email + "'\n";
            }
            if (cid != 0) {
                sql += "AND r.course_id=" + cid + "\n";
            }
            if (status != 0) {
                sql += "AND r.status_id = " + status + "\n";
            }
            if (from != "" && to != "") {
                sql += "AND r.registration_time >= '" + from + "' and r.registration_time <= '" + to + "'\n";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                count = rs.getInt("Count(r.id)");
            }
            return count;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return count;
    }

    public RegistrationStatus getResgitrationStatus(int id) {
        RegistrationStatus r = new RegistrationStatus();
        try {
            String sql = "SELECT * FROM onlinelearn.registrations_status where registration_status_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                r.setId(rs.getInt("registration_status_id"));
                r.setName("registrations_status_name");
            }
        } catch (Exception e) {
        }
        return r;
    }

    public List<Registration> getRegistrationList(int cid , String email, String from, String to, int status, int pageIndex, int pageSize) {
        List<Registration> list = new ArrayList<>();
        CourseDAO dao = new CourseDAO();
        UserDBContext uDAO = new UserDBContext();
        try {
            String sql = "select r.id,r.course_id,r.registration_time, r.totalcost, r.status_id, r.update_time, r.lastupdateby, u.username,u.email,u.fullname\n"
                    + "from onlinelearn.user_registrations_course as ur, onlinelearn.registrations as r, onlinelearn.user as u\n"
                    + "where u.id = ur.user_id AND r.id=registration_id \n";
            if (email != "") {
                sql += "AND u.email LIKE '%" + email + "%'\n";
            }
            if (cid != 0) {
                sql += "AND r.course_id=" + cid + "\n";
            }
            
            if (status != 0) {
                sql += "AND r.status_id = " + status + "\n";
            }
            if (from != "" && to != "") {
                sql += "AND r.registration_time >= '" + from + "' and r.registration_time <= '" + to + "'\n";
            }
            sql += "order by id limit " + pageIndex + "," + pageSize;
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Registration r = new Registration();
                r.setId(rs.getInt("id"));
                r.setCourse(dao.getCourseById(rs.getInt("course_id")));
                r.setRegistrationTime(rs.getTimestamp("registration_time"));
                r.setTotalCourse(rs.getFloat("totalcost"));
                r.setRegistrationStatus(getResgitrationStatus(rs.getInt("status_id")));
                r.setUpdateTime(rs.getTimestamp("update_time"));
                r.setRegisBy(uDAO.getUserNotLogin(rs.getString("email")));
                r.setUserAccept(rs.getString("lastupdateby"));
                list.add(r);
            }
        } catch (Exception e) {
            System.out.println("Error at: getRegistrationList");
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<RegistrationStatus> getAllRegisStatus() {
        List<RegistrationStatus> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM onlinelearn.registrations_status;";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                RegistrationStatus r = new RegistrationStatus();
                r.setId(rs.getInt("registration_status_id"));
                r.setName(rs.getString("registrations_status_name"));
                list.add(r);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public int ChangeRegistrationStatus(int id, int status, User u) {
        try {
            String sql = "UPDATE `onlinelearn`.`registrations`\n"
                    + "SET\n"
                    + "`status_id` = ?,\n"
                    + "`update_time` = ?,\n"
                    + "`lastupdateby` = ?\n"
                    + "WHERE `id` = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, status);
            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            stm.setTimestamp(2, timestamp);
            stm.setString(3, u.getFullname());
            stm.setInt(4, id);
            int check = stm.executeUpdate();
            return check;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

//ADMIN END
//    public static void main(String[] args) {
//        CourseDAO courseDAO = new CourseDAO();
//        System.out.println(courseDAO.totalRegistration(0, "", "", "", 0));
//    }
}
