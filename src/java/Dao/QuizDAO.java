/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Quiz;
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
 * @author Thanh Phong
 */
public class QuizDAO extends DBContext {

    public List<Quiz> getAllQuiz() {
        try {
            List<Quiz> list = new ArrayList<>();
            String sql = "SELECT * FROM onlinelearn.quiz ";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Quiz q = new Quiz();
                q.setQuizId(rs.getInt("quiz_id"));
                q.setQuizQuestion(rs.getString("quiz_question"));
                q.setQuizAnswer(rs.getString("quiz_answer"));
                q.setCorrectAnswer("quiz_correct_answer");
                q.setSubLessonId(rs.getInt("sublesson_id"));
                q.setLessonId(rs.getInt("lesson_id"));
                q.setCourseId(rs.getInt("course_id"));
                list.add(q);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Quiz> getAllQuizByQuizID(int qid) {
        try {
            List<Quiz> list = new ArrayList<>();
            String sql = "SELECT * FROM onlinelearn.quiz where quiz_id =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, qid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Quiz q = new Quiz();
                q.setQuizId(rs.getInt("quiz_id"));
                q.setQuizQuestion(rs.getString("quiz_question"));
                q.setQuizAnswer(rs.getString("quiz_answer"));
                q.setCorrectAnswer("quiz_correct_answer");
                q.setSubLessonId(rs.getInt("sublesson_id"));
                q.setLessonId(rs.getInt("lesson_id"));
                q.setCourseId(rs.getInt("course_id"));
                list.add(q);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public HashMap<String, ArrayList<String>> getAllQuizAnswer(int lid) {
        HashMap<String, ArrayList<String>> hm = new HashMap<>();
        try {
            
            String sql = "SELECT * FROM onlinelearn.quiz where lesson_id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String id = Integer.toString(rs.getInt(1));
                String question = rs.getString("quiz_question");
                String answer = rs.getString("quiz_answer");
                hm.put(id+"-"+question, splitAnswer(answer));
            }
            return hm;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public HashMap<String, ArrayList<String>> getAllQuizAnswerByQuizID(int qid) {
        HashMap<String, ArrayList<String>> hm = new HashMap<>();
        try {
            String sql = "SELECT * FROM onlinelearn.quiz where quiz_id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, qid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String id = Integer.toString(rs.getInt(1));
                String question = rs.getString("quiz_question");
                String answer = rs.getString("quiz_answer");
                hm.put(id + "-" + question, splitAnswer(answer));
            }
            return hm;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public HashMap<String, ArrayList<String>> getAllQuizAnswerByCourseId(int cid) {
        HashMap<String, ArrayList<String>> hm = new HashMap<>();
        try {
            List<Quiz> list = new ArrayList<>();
            String sql = "SELECT * FROM onlinelearn.quiz where course_id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String id = Integer.toString(rs.getInt(1));
                String question = rs.getString("quiz_question");
                String answer = rs.getString("quiz_answer");
                hm.put(question, splitAnswer(answer));
            }
            return hm;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public HashMap<Integer, String> getCorrectAnswer(int id) {
        try {
            HashMap<Integer, String> hm = new HashMap<>();
            String sql = "SELECT * FROM onlinelearn.quiz where lesson_id=" + id;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                hm.put(rs.getInt("id"), rs.getString("quiz_correct_answer"));
            }
            return hm;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public String getAnswer(int id){
        try {
            String sql = "SELECT * FROM onlinelearn.quiz where id=?";
            PreparedStatement stm =connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return rs.getString("quiz_correct_answer");
            }
        } catch (Exception e) {
        }
        return null;
    }
    public void getScore(int id, float score, int sid, int qid) {
        String sql = "INSERT INTO `onlinelearn`.`course_user`\n"
                + "(\n"
                + "`course_user_id`,\n"
                + "`course_user_score`,\n"
                + "`sublesson_id`,\n"
                + "`quiz_id`)\n"
                + "VALUES\n"
                + "(?,?,?,?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setFloat(2, score);
            ps.setInt(3, sid);
            ps.setInt(4, qid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingsListDBContext.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public static void main(String[] args) {
        QuizDAO dao = new QuizDAO();
        HashMap<String, ArrayList<String>> hm = dao.getAllQuizAnswerByQuizID(1);
        for (Map.Entry<String, ArrayList<String>> entry : hm.entrySet()) {
            String key = entry.getKey();
            System.out.println(key);
            ArrayList<String> value = entry.getValue();
            
        }
    }

    public ArrayList<String> splitAnswer(String answer) {
        ArrayList<String> list = new ArrayList<>();
        try {
            String arr[] = answer.split("-");
            for (String string : arr) {
                list.add(string);
            }
            return list;

        } catch (Exception e) {

        }
        return null;

    }

//    public static void main(String[] args) {
//        List<Quiz> list = new ArrayList<>();
//        HashMap<String, ArrayList<String>> hm = new HashMap<>();
//        QuizDAO dao = new QuizDAO();
//        hm=dao.getAllQuizAnswer(3);
//        for (Map.Entry<String, ArrayList<String>> entry : hm.entrySet()) {
//            String key = entry.getKey();
//            ArrayList<String> value = entry.getValue();
//            System.out.println(key);
//
//        }
//    }
}
