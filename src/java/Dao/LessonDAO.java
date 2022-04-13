/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Lesson;
import Model.Topic;
import context.DBContext;
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
 * @author Louis
 */
public class LessonDAO extends DBContext {

    public List<Lesson> getListLessonByCourseId(int cid) {
        List<Lesson> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM onlinelearn.lesson WHERE course_id= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson(
                        rs.getInt("lesson_id"),
                        rs.getString("title"),
                        rs.getInt("type"),
                        rs.getInt("belongtotopic"),
                        rs.getString("order"),
                        rs.getBoolean("status"),
                        rs.getString("videolink"),
                        rs.getString("content")
                );
                list.add(l);
            }
        } catch (Exception e) {
            System.out.println("Error at getListLessonByCourseId");
            System.out.println(e.getMessage());
        }

        return list;
    }

    public List<Topic> getListTopicByCourseId(int cid) {
        List<Topic> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM onlinelearn.topic WHERE course_id= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Topic t = new Topic();
                t.setId(rs.getInt("topic_id"));
                t.setTopic_title(rs.getString("topic_title"));
                t.setQuiz_id(rs.getInt("quiz_id"));
                t.setCourse_id(rs.getInt("course_id"));
                list.add(t);
            }
        } catch (Exception e) {
            System.out.println("Error at getListTopicByCourseId");
            System.out.println(e.getMessage());
        }

        return list;
    }

    public int changeLessonStatus(int lesson_id, boolean lesson_status) {
        try {
            String sql = "UPDATE `onlinelearn`.`lesson`\n"
                    + "SET\n"
                    + "`status` = ? \n"
                    + "WHERE `lesson_id` = ? ;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, lesson_status);
            stm.setInt(2, lesson_id);
            return stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Change lesson status err: ");
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public List<Lesson> getLessonByTopicId(int tid) {

        List<Lesson> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM onlinelearn.lesson WHERE belongtotopic=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, tid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson(
                        rs.getInt("lesson_id"),
                        rs.getString("title"),
                        rs.getInt("type"),
                        rs.getInt("belongtotopic"),
                        rs.getString("order"),
                        rs.getBoolean("status"),
                        rs.getString("videolink"),
                        rs.getString("content")
                );
                list.add(l);
            }
        } catch (Exception e) {
            System.out.println("Error at getLessonByTopicId");
            System.out.println(e.getMessage());
        }

        return list;
    }

    public List<Topic> GetListTopicByCourseID(int cid) {
        List<Topic> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM onlinelearn.topic WHERE course_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Topic t = new Topic(
                        rs.getInt("topic_id"),
                        rs.getString("topic_title"),
                        rs.getInt("quiz_id"),
                        rs.getInt("course_id"));
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            System.out.println("Error at GetListTopicByCourseID: ");
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Lesson GetALessonByID(int lid) {
        try {
            String sql = "SELECT * FROM onlinelearn.lesson WHERE lesson_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson(
                        rs.getInt("lesson_id"),
                        rs.getString("title"),
                        rs.getInt("type"),
                        rs.getInt("belongtotopic"),
                        rs.getString("order"),
                        rs.getBoolean("status"),
                        rs.getString("videolink"),
                        rs.getString("content")
                );
                return l;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<Topic> getAllTopics() {
        ArrayList<Topic> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM onlinelearn.topic";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Topic t = new Topic();
                t.setId(rs.getInt("topic_id"));
                t.setTopic_title(rs.getString("topic_title"));
                list.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void addLesson(String title, int type, int belongtotopic, String order,
            boolean status, String videolink, String content) {
        String query = "INSERT INTO `onlinelearn`.`lesson`\n"
                + "(`title`,\n"
                + "`type`,\n"
                + "`belongtotopic`,\n"
                + "`order`,\n"
                + "`status`,\n"
                + "`videolink`,\n"
                + "`content`)\n"
                + "VALUES\n"
                + "(?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?);";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setInt(2, type);
            ps.setInt(3, belongtotopic);
            ps.setString(4, order);
            ps.setBoolean(5, status);
            ps.setString(6, videolink);
            ps.setString(7, content);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateLesson(int id, String title, int type, int belongtotopic, String order,
            boolean status, String videolink, String content) {
        String query = "UPDATE `onlinelearn`.`lesson`\n"
                + "SET\n"
                + "`title` = ?,\n"
                + "`type` = ?,\n"
                + "`belongtotopic` = ?,\n"
                + "`order` = ?,\n"
                + "`status` = ?,\n"
                + "`videolink` = ?,\n"
                + "`content` = ?\n"
                + "WHERE `lesson_id` = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setInt(2, type);
            ps.setInt(3, belongtotopic);
            ps.setString(4, order);
            ps.setBoolean(5, status);
            ps.setString(6, videolink);
            ps.setString(7, content);
            ps.setInt(8, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        LessonDAO dao = new LessonDAO();
        int i = dao.changeLessonStatus(1, false);
        System.out.println(i);
    }
}
