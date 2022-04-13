/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Phong
 */
public class ChangePasswordDAO extends DBContext {

    public void changePasswordByEmail(String email, String password) {
        String query = "UPDATE `onlinelearn`.`user`\n"
                + "SET\n"
                + "`password` = ?\n"
                + "WHERE email=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, email);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SettingsListDBContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }
    public static void main(String[] args) {
        ChangePasswordDAO dao=new ChangePasswordDAO();
        dao.changePasswordByEmail("cc@gmail.com", "phongdeptrai");
    }
//    public String randomCaptcha() {
//        char data[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
//        char index[] = new char[8];
//        String captcha = "";
//        Random r = new Random();
//        int i = 0;
//
//        for (i = 0; i < (index.length); i++) {
//            int ran = r.nextInt(data.length);
//            index[i] = data[ran];
//            captcha = String.valueOf(index);
//
//        }
//        return captcha;
//
//    }
    
    public void changePasswordByPhone(String phone, String password) {

        String query = "UPDATE `onlinelearn`.`user`\n"
                + "SET\n"
                + "`password` = ?\n"
                + "WHERE phonenumber=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, phone);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SettingsListDBContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }
    
}
