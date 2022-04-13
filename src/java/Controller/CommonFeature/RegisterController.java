/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.CommonFeature;

import Dao.RegisterDao;
import Model.Role;
import Model.Token;
import Model.User;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Duy Hiep
 */
public class RegisterController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RegisterDao rao = new RegisterDao();
        ArrayList<String> userNameExist = rao.getUserNameExist();
        ArrayList<String> emailExist = rao.getEmailExist();
        request.setAttribute("username", userNameExist);
        request.setAttribute("email", emailExist);
        request.getRequestDispatcher("client/register.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = -1;
            String fullname = request.getParameter("name");
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            EncryptionDecryptionAES eS = new EncryptionDecryptionAES();
            password = eS.encrypt(password);
            
            boolean gender = request.getParameter("gender").toLowerCase().equals("male");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            int status = 3;
            Role role = new Role();
            role.setRole_id(4);
            
            RegisterDao rao = new RegisterDao();
            
            
            
            User u = new User(id, username, password, fullname, gender, address, email, phone, status, role);
            rao.insert(u);
            u = rao.getUser(username, email);
            
            request.getServletContext().setAttribute("User", u);
            SendEmail sendEmail = new SendEmail();
            String verifyCode = sendEmail.getRandom();
            String subject = "Email Verification Link";
            String message = "Click this link to confirm your email address and complete setup for your account.\n"
                    + "Verification Link: " + "http://localhost:8080/g4/verify?key1=" + eS.encrypt(verifyCode) + "&&key2=" + eS.encrypt(Integer.toString(u.getId()));
            sendEmail.send(email, subject, message);
            Token t = new Token();
            t.setUser_id(u);
            t.setCode(verifyCode);
            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            t.setCreatedAt(timestamp);
            t.setUpdatedAt(timestamp);
            Timestamp after = new Timestamp(timestamp.getTime() + (5*60)*1000);
            t.setExpired(after);
            rao.insertToken(t);
            request.getServletContext().setAttribute("es", eS);
            request.getServletContext().setAttribute("verify", verifyCode);
            request.getRequestDispatcher("client/verifyemail.jsp").forward(request, response);    
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
   
}
