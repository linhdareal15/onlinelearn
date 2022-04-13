/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Setting;
import Model.SettingStatus;
import Model.SettingType;
import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phong
 */
public class SettingsListDBContext extends DBContext {

    public ArrayList<Setting> list() {
        ArrayList<Setting> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM onlinelearn.settingslist s inner join onlinelearn.settingslist_type  st \n"
                    + "on s.type = st.type_id left join onlinelearn.settingslist_status  ss on s.status = ss.status_id";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setSettingslist_id(rs.getInt("settingslist_id"));
                s.setSettingsName(rs.getString("settingsname"));
                s.setDescription(rs.getString("description"));
                SettingType st = new SettingType();
                st.setSetting_type_id(rs.getInt("type_id"));
                st.setSetting_type_name(rs.getString("type_name"));
                s.setType(st);
                s.setValue(rs.getString("value"));
                SettingStatus ss = new SettingStatus();
                ss.setSetting_status_id(rs.getInt("status_id"));
                ss.setSetting_status_name(rs.getString("status_name"));
                s.setStatus(ss);
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingsListDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<SettingType> listSettingType() {
        ArrayList<SettingType> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM onlinelearn.settingslist_type;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SettingType settingType = new SettingType();
                settingType.setSetting_type_id(rs.getInt("type_id"));
                settingType.setSetting_type_name(rs.getString("type_name"));
                list.add(settingType);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SettingsListDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<SettingStatus> listSettingStatus() {
        ArrayList<SettingStatus> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM onlinelearn.settingslist_status;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SettingStatus settingStatus = new SettingStatus();
                settingStatus.setSetting_status_id(rs.getInt("status_id"));
                settingStatus.setSetting_status_name(rs.getString("status_name"));
                list.add(settingStatus);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SettingsListDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Setting> getListSettingBySearch(int indexPage, int type, int status, String setting_value) {
        ArrayList<Setting> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM onlinelearn.settingslist s \n"
                    + "INNER JOIN onlinelearn.settingslist_type st ON s.type = st.type_id \n"
                    + "LEFT JOIN onlinelearn.settingslist_status ss ON s.status = ss.status_id \n"
                    + "WHERE (1=1)";
            if (type != -1) {
                sql += " AND s.type = '" + type + "'";
            }
            if (status != -1) {
                sql += " AND s.status = '" + status + "'";
            }
            if (setting_value != null) {
                sql += " AND s.value like '%" + setting_value + "%'";
            }
            sql += " order by settingslist_id LIMIT 5 OFFSET ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, indexPage * 5);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setSettingslist_id(rs.getInt("settingslist_id"));
                s.setSettingsName(rs.getString("settingsname"));
                s.setDescription(rs.getString("description"));
                SettingType st = new SettingType();
                st.setSetting_type_id(rs.getInt("type_id"));
                st.setSetting_type_name(rs.getString("type_name"));
                s.setType(st);
                s.setValue(rs.getString("value"));
                SettingStatus ss = new SettingStatus();
                ss.setSetting_status_id(rs.getInt("status_id"));
                ss.setSetting_status_name(rs.getString("status_name"));
                s.setStatus(ss);
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingsListDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean activeSetting(int id) {
        try {
            String sql = "UPDATE `onlinelearn`.`settingslist`\n"
                    + "SET\n"
                    + "`status` = ?\n"
                    + "WHERE `settingslist_id` = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, 1);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingsListDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean deactiveSetting(int id) {
        try {
            String sql = "UPDATE `onlinelearn`.`settingslist`\n"
                    + "SET\n"
                    + "`status` = ?\n"
                    + "WHERE `settingslist_id` = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, 2);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingsListDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public int getTotalSettings(int type, int status, String setting_value) {
        try {
            String sql = "SELECT count(*) AS total FROM onlinelearn.settingslist AS s\n"
                    + "INNER JOIN onlinelearn.settingslist_type st ON s.type = st.type_id \n"
                    + "LEFT JOIN onlinelearn.settingslist_status ss ON s.status = ss.status_id \n"
                    + "WHERE (1=1)";
            if (type != -1) {
                sql += " AND s.type = '" + type + "'";
            }
            if (status != -1) {
                sql += " AND s.status = '" + status + "'";
            }
            if (setting_value != null) {
                sql += " AND s.value like '%" + setting_value + "%'";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingsListDBContext.class.getName()).log(Level.SEVERE, null, ex);

        }
        return 0;
    }

    public void insertSetting(String settingsName, String description, int type, String value, int status) {
        String query = "INSERT INTO `onlinelearn`.`settingslist`\n"
                + "(`settingsname`,\n"
                + "`description`,\n"
                + "`type`,\n"
                + "`value`,\n"
                + "`status`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?);";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, settingsName);
            ps.setString(2, description);
            ps.setInt(3, type);
            ps.setString(4, value);
            ps.setInt(5, status);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SettingsListDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Setting getSettingById(String id) {
        String query = "SELECT * FROM onlinelearn.settingslist s inner join onlinelearn.settingslist_type  st \n"
                + "                    on s.type = st.type_id left join onlinelearn.settingslist_status  ss on s.status = ss.status_id\n"
                + "                    where settingslist_id=? ";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Setting(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingsListDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateSetting(String id, String name, String description, int type, String value,
            int status) {

        String query = "UPDATE `onlinelearn`.`settingslist`\n"
                + "SET\n"
                + "`settingsname` = ?,\n"
                + "`description` = ?,\n"
                + "`type` = ?,\n"
                + "`value` = ?,\n"
                + "`status` = ?\n"
                + "WHERE `settingslist_id` = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, type);
            ps.setString(4, value);
            ps.setInt(5, status);
            ps.setString(6, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SettingsListDBContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SettingsListDBContext sldbc = new SettingsListDBContext();
        int count = sldbc.getTotalSettings(1, 2, "role");
        System.out.println(count);
    }
}
