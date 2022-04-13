/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Duy Hiep
 */
public class DashboardDao extends DBContext{
    public int getTotalCustomers(){
        PreparedStatement stm = null;
        try {
            String sql = "SELECT count(*) as total FROM onlinelearn.user where Role_Id = 4 and status = 2;";
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(DashboardDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }
    
    public int getTotalCourses(){
        PreparedStatement stm = null;
        try {
            String sql = "SELECT count(*) as total FROM onlinelearn.course";
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(DashboardDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }
    
    public int getTotalRevenues(){
        PreparedStatement stm = null;
        try {
            String sql = "select sum(r.totalcost) as total from registrations r join course c on r.course_id = c.cid\n" +
            "join registrations_status rs on r.status_id = rs.registration_status_id\n" +
            "where c.status = 1 and  rs.registration_status_id = 1;";
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(DashboardDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }
    
    public int getNewSubject(String from,String to){
        PreparedStatement stm = null;
        try {
            String sql = "Select count(*) as total from course where (course.updatedate between ? and ?) and status = 1;\n" ;
            stm = connection.prepareStatement(sql);
            stm.setString(1, from);
            stm.setString(2, to);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(DashboardDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }
    
    public TreeMap<String,Integer> getNewRegistration(String from,String to){
        TreeMap<String,Integer> registrations = new TreeMap<>();
        PreparedStatement stm = null;
        try {
            connection.setAutoCommit(false);
            String sql = "Select count(*) as success from registrations r join course c on r.course_id = c.cid\n" +
                        "join registrations_status rs on  rs.registration_status_id = r.status_id\n" +
                        "where c.status = 1 and r.status_id = 1 and (r.update_time between ? and ?);" ;
            stm = connection.prepareStatement(sql);
            stm.setString(1, from);
            stm.setString(2, to);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                registrations.put("Success Or Approved", rs.getInt("success"));
            }
            sql = "Select count(*) as rejected from registrations r join course c on r.course_id = c.cid\n" +
                "join registrations_status rs on  rs.registration_status_id = r.status_id\n" +
                "where c.status = 1 and r.status_id = 2 and (r.update_time between ? and ?);" ;
            stm = connection.prepareStatement(sql);
            stm.setString(1, from);
            stm.setString(2, to);
            rs = stm.executeQuery();
            if(rs.next()){
                registrations.put("Rejected", rs.getInt("rejected"));
            }
            sql = "Select count(*) as submitted from registrations r join course c on r.course_id = c.cid\n" +
                    "join registrations_status rs on  rs.registration_status_id = r.status_id\n" +
                    "where c.status = 1 and r.status_id = 3 and (r.registration_time between ? and ?);" ;
            stm = connection.prepareStatement(sql);
            stm.setString(1, from);
            stm.setString(2, to);
            rs = stm.executeQuery();
            if(rs.next()){
                registrations.put("Submitted", rs.getInt("submitted"));
            }
            sql = "Select count(*) as canceled from registrations r join course c on r.course_id = c.cid\n" +
                "join registrations_status rs on  rs.registration_status_id = r.status_id\n" +
                "where c.status = 1 and r.status_id = 4 and (r.update_time between ? and ?);" ;
            stm = connection.prepareStatement(sql);
            stm.setString(1, from);
            stm.setString(2, to);
            rs = stm.executeQuery();
            if(rs.next()){
                registrations.put("Canceled", rs.getInt("canceled"));
            }
            sql = "Select count(*) as total from registrations r join course c on r.course_id = c.cid\n" +
                    "join registrations_status rs on  rs.registration_status_id = r.status_id\n" +
                    "where c.status = 1 and (r.update_time between ? and ?);" ;
            stm = connection.prepareStatement(sql);
            stm.setString(1, from);
            stm.setString(2, to);
            rs = stm.executeQuery();
            if(rs.next()){
                registrations.put("Total Registrations", rs.getInt("total"));
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        //set again the autocomit
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registrations;
    }
    
    public TreeMap<String,TreeMap<String,Integer>> getRevenuesByCourseCategory(String from,String to){
        ArrayList<LocalDate> dayBetween = getDayBetween(from, to);
        TreeMap<String,Integer> revenuesByDay = new TreeMap<>();
        TreeMap<String,TreeMap<String,Integer>> res = new TreeMap<>();
        ArrayList<String> listCategory = new ArrayList<>();
        listCategory.add("Programing Language");
        listCategory.add("Web Design");
        listCategory.add("SEO");
        listCategory.add("Development");
        listCategory.add("Marketing");
        PreparedStatement stm = null;
        try {
            connection.setAutoCommit(false);
            ArrayList<Integer> listId = new ArrayList<>();
            String sql = "SELECT id FROM onlinelearn.course_category;";
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                listId.add(rs.getInt("id"));
            }
            String dayRevenue = "";
            for(LocalDate day : dayBetween){
                for (Integer id : listId) {
                    sql = "Select date_format(r.update_time,\"%M %d \") as Days,cc.category_name as name, sum(r.totalcost) as revenues from registrations r\n" +
                            "join registrations_status rs on r.status_id = rs.registration_status_id\n" +
                            "join course c on r.course_id = c.cid\n" +
                            "join course_category cc on cc.id = c.category_id\n" +
                            "where c.status = 1 and r.status_id = 1 and c.category_id = ? and r.update_time like concat(?,'%');";
                    stm = connection.prepareStatement(sql);
                    stm.setInt(1, id);
                    stm.setString(2, day.toString());
                    rs = stm.executeQuery();
                    while(rs.next()){
                        if(rs.getString("name") != null && rs.getInt("revenues") != 0){
                            revenuesByDay.put(rs.getString("name"), rs.getInt("revenues"));
                            dayRevenue = rs.getString("Days");
                        }            
                    }
                }
                for(String string : listCategory){
                    if(!revenuesByDay.containsKey(string)){
                        revenuesByDay.put(string, 0);
                    }
                }
                res.put(dayRevenue,revenuesByDay);
                revenuesByDay = new TreeMap<>();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        //set again the autocomit
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }
    
    public HashMap<String,Integer> getNewCustomerRegistration(String from,String to){
        HashMap<String,Integer> customers = new HashMap<>();
        PreparedStatement stm = null;
        try {
            connection.setAutoCommit(false);
            String sql = "Select count(*) as newlyregistered from user_registrations_course uc \n" +
            "join registrations r on uc.registration_id = r.id \n" +
            "join course c on r.course_id = c.cid \n" +
            "join registrations_status rs on r.status_id = rs.registration_status_id\n" +
            "where c.status = 1 and rs.registration_status_id = 3 and (r.registration_time between ? and ?);";
            stm = connection.prepareStatement(sql);
            stm.setString(1, from);
            stm.setString(2, to);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                customers.put("Newly Registered", rs.getInt("newlyregistered"));
            }
            sql = "Select count(*) as newlybought from user_registrations_course uc \n" +
            "join registrations r on uc.registration_id = r.id \n" +
            "join course c on r.course_id = c.cid \n" +
            "join registrations_status rs on r.status_id = rs.registration_status_id\n" +
            "where c.status = 1 and rs.registration_status_id = 1 and (r.registration_time between ? and ?);";
            stm = connection.prepareStatement(sql);
            stm.setString(1, from);
            stm.setString(2, to);
            rs = stm.executeQuery();
            while(rs.next()){
                customers.put("Newly Bought", rs.getInt("newlybought"));
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        //set again the autocomit
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return customers;
    }
    
    public ArrayList<TreeMap<String,Integer>> getTrendOrderCount(String from,String to){
        ArrayList<LocalDate> dayBetween = getDayBetween(from, to);
        ArrayList<TreeMap<String,Integer>> customers = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs;
        String sql = "";
        try {
            connection.setAutoCommit(false);
            TreeMap<String,Integer> success = new TreeMap<>();
            TreeMap<String,Integer> all = new TreeMap<>();
            for (LocalDate date : dayBetween) {
                sql = "Select date_format(r.update_time,\"%M %d \") as Days ,count(*) as Success from  registrations r join course c on r.course_id = c.cid\n" +
                    "join registrations_status rs on  rs.registration_status_id = r.status_id\n" +
                    "where c.status = 1 and r.status_id = 1 and r.update_time like concat(?,'%');";
                stm = connection.prepareStatement(sql);
                stm.setString(1, date.toString());
                rs = stm.executeQuery();
                while(rs.next()){
                    if(rs.getString("Days") != null && rs.getInt("Success") != 0){
                        success.put(rs.getString("Days"), rs.getInt("Success"));
                    } 
                }
                
                sql = "Select date_format(r.update_time,\"%M %d \") as Days ,count(*) as allOrder from  registrations r join course c on r.course_id = c.cid\n" +
                    "join registrations_status rs on  rs.registration_status_id = r.status_id\n" +
                    "where c.status = 1 and r.update_time like concat(?,'%');";
                stm = connection.prepareStatement(sql);
                stm.setString(1, date.toString());
                rs = stm.executeQuery();
                while(rs.next()){
                    if(rs.getString("Days") != null && rs.getInt("allOrder") != 0){
                        all.put(rs.getString("Days"), rs.getInt("allOrder"));
                    } 
                }
            }
            customers.add(success);
            customers.add(all);
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        //set again the autocomit
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return customers;
    }
    
    public ArrayList<LocalDate> getDayBetween(String from,String to){
        LocalDate start = LocalDate.parse(from);
        LocalDate end = LocalDate.parse(to);
        ArrayList<LocalDate> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start);
            start = start.plusDays(1);
        }
        return totalDates;
    }
    public static void main(String[] args) {
        DashboardDao d = new DashboardDao();
        TreeMap<String, TreeMap<String, Integer>> revenuesByCourseCategory = d.getRevenuesByCourseCategory("2022-03-10", "2022-03-17");
   
        
        for (Map.Entry<String, TreeMap<String, Integer>> entry : revenuesByCourseCategory.entrySet()) {
            String key = entry.getKey();
            TreeMap<String, Integer> value = entry.getValue();
            System.out.println(key + " - " + value);
//            for (Map.Entry<String, Integer> entry1 : value.entrySet()) {
//                String key1 = entry1.getKey();
//                Integer value1 = entry1.getValue();
//                System.out.println(key + " " + value);
//            }
            System.out.println();
        }
        
//        ArrayList<LocalDate> dayBetween = d.getDayBetween("2022-03-01", "2022-03-23");
//        for (LocalDate localDate : dayBetween) {
//            System.out.println(localDate.toString());
//        }
    }
}
