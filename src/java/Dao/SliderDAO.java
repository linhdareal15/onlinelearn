/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Slider;
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
 * @author Century
 */
public class SliderDAO extends DBContext {

    public ArrayList<Slider> getAllSliders() {
        ArrayList<Slider> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM onlinelearn.slider";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider s = new Slider();
                s.setId(rs.getInt("id"));
                s.setTitle(rs.getString("title"));
                s.setImage(rs.getString("image"));
                s.setBacklink(rs.getString("backlink"));
                s.setStatus(rs.getInt("status"));
                s.setNote(rs.getString("note"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

//    public ArrayList<Slider> getSlidersByTitleOrBackLink(String keyword) {
//        ArrayList<Slider> list = new ArrayList<>();
//        try {
//            String sql = "SELECT * FROM onlinelearn.slider\n"
//                    + "where id = 2";
//            PreparedStatement stm = connection.prepareStatement(sql);
////            stm.setString(1, keyword);
////            stm.setString(2, keyword);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                Slider s = new Slider();
//                s.setId(rs.getInt("id"));
//                s.setTitle(rs.getString("title"));
//                s.setImage(rs.getString("image"));
//                s.setBacklink(rs.getString("backlink"));
//                s.setStatus(rs.getInt("status"));
//                s.setNote(rs.getString("note"));
//                list.add(s);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
    public List<Slider> getSlidersByTitleOrBackLink(String keyword) {
        try {
            List<Slider> list = new ArrayList<>();
            String sql = "SELECT * FROM onlinelearn.slider\n"
                    + "where backlink like '%' + ? + '%'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, keyword);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Slider s = new Slider();
                s.setId(rs.getInt("id"));
                s.setTitle(rs.getString("title"));
                s.setImage(rs.getString("image"));
                s.setBacklink(rs.getString("backlink"));
                s.setStatus(rs.getInt("status"));
                s.setNote(rs.getString("note"));
                list.add(s);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public ArrayList<Slider> getSlidersByStatus(int status, String keyword) {
        ArrayList<Slider> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM onlinelearn.slider WHERE (1=1)";
            int paramIndex = 0;
            HashMap<Integer, Object[]> params = new HashMap<>();
            if (status != -1) {
                sql += " and status = ?";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = Integer.class.getName();
                param[1] = status;
                params.put(paramIndex, param);
            }
            if (keyword != null) {
                sql += " and title like ?";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getName();
                param[1] = "%" + keyword + "%";
                params.put(paramIndex, param);
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object[]> entry : params.entrySet()) {
                Integer index = entry.getKey();
                Object[] value = entry.getValue();
                String type = value[0].toString();
                if (type.equals(Integer.class.getName())) {
                    stm.setInt(index, (Integer) value[1]);
                } else if (type.equals(String.class.getName())) {
                    stm.setString(index, value[1].toString());
                }
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider s = new Slider();
                s.setId(rs.getInt("id"));
                s.setTitle(rs.getString("title"));
                s.setImage(rs.getString("image"));
                s.setBacklink(rs.getString("backlink"));
                s.setStatus(rs.getInt("status"));
                s.setNote(rs.getString("note"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Slider GetSliderByID(String id) {
        String query = "SELECT * FROM onlinelearn.slider where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Slider(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertSlider(String title, String image, String backlink, int status, String note) {
        String query = "INSERT INTO onlinelearn.slider\n"
                + "(title,\n"
                + "image,\n"
                + "backlink,\n"
                + "status,\n"
                + "note)\n"
                + "VALUES\n"
                + "(?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?);";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, image);
            ps.setString(3, backlink);
            ps.setInt(4, status);
            ps.setString(5, note);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateSlider(int id, String title, String image, String backlink, int status, String note) {
        String query = "UPDATE onlinelearn.slider\n"
                + "SET\n"
                + "title = ?,\n"
                + "image = ?,\n"
                + "backlink = ?,\n"
                + "status = ?,\n"
                + "note = ?\n"
                + "WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, image);
            ps.setString(3, backlink);
            ps.setInt(4, status);
            ps.setString(5, note);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }

    public int getTotalSliders() {
        try {
            String sql = "SELECT count(*) FROM onlinelearn.slider";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingsListDBContext.class.getName()).log(Level.SEVERE, null, ex);

        }
        return 0;
    }

    public void changeStatus(int id, int status) {
        try {
            String sql = "UPDATE onlinelearn.slider\n"
                    + "SET\n"
                    + "status = ?\n"
                    + "WHERE id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            if (status == 0) {
                stm.setInt(1, 1);
            } else {
                stm.setInt(1, 0);
            }
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
