/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.CustomerCourse;

import Controller.CommonFeature.*;
import Controller.UserCourse.UserCourseRegisterController;
import Dao.CourseDAO;
import Dao.RegisterDao;
import Dao.UserCourseDAO;
import Model.Course;
import Model.Registration;
import Model.RegistrationStatus;
import Model.Role;
import Model.Token;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Quang
 */
@WebServlet(name = "CourseRegisterController", urlPatterns = {"/course/customer-register"})
public class CustomerCourseRegisterController extends Home {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

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
        request.getRequestDispatcher("client/course.jsp").forward(request, response);
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
        //register for customer
        try {
            int id = -1;
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String username = request.getParameter("title");

            //Generate Random passworld
            Random random = new Random();
            String s = "";
            String password = "";
            for (char i = 'a'; i <= 'z'; i++) {
                s = s + i;
            }
            for (char i = 'A'; i <= 'Z'; i++) {
                s = s + i;
            }
            for (char i = '0'; i <= '9'; i++) {
                s = s + i;
            }

            for (int i = 0; i < 8; i++) {
                password += s.charAt(random.nextInt(s.length()));
            }

            EncryptionDecryptionAES eS = new EncryptionDecryptionAES();
            password = eS.encrypt(password);

            String address = request.getParameter("address");
            String phone = request.getParameter("phonenumber");
            int status = 3;
            Role role = new Role();
            role.setRole_id(4);

            RegisterDao rao = new RegisterDao();

            User u = new User(id, username, password, fullname, true, address, email, phone, status, role);
            rao.insert(u);
            u = rao.getUser(username, email);

            request.getServletContext().setAttribute("User", u);
            SendEmail sendEmail = new SendEmail();
            String verifyCode = sendEmail.getRandom();
            String subject = "Email Verification Link";
            String message = "Click this link to confirm your email address and complete setup for your account(this is your password: " + password + ").\n"
                    + "Verification Link: " + "http://localhost:8080/g4/verify?key1=" + eS.encrypt(verifyCode) + "&&key2=" + eS.encrypt(Integer.toString(u.getId()));
            sendEmail.send(email, subject, message);
            Token t = new Token();
            t.setUser_id(u);
            t.setCode(verifyCode);
            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            t.setCreatedAt(timestamp);
            t.setUpdatedAt(timestamp);
            Timestamp after = new Timestamp(timestamp.getTime() + (5 * 60) * 1000);
            t.setExpired(after);
            rao.insertToken(t);
            request.getServletContext().setAttribute("es", eS);
            request.getServletContext().setAttribute("verify", verifyCode);
            
            //register Course
            UserCourseDAO userCourseDAO = new UserCourseDAO();
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            String notification = "";
            CourseDAO courseDAO = new CourseDAO();
            Course c = courseDAO.getCourse(courseId);
            Registration r = new Registration();
            r.setRegistrationTime(timestamp);
            RegistrationStatus rs = new RegistrationStatus();
            rs.setId(3);
            r.setRegistrationStatus(rs);
            r.setUpdateTime(timestamp);
            boolean check = userCourseDAO.registerCourse(u, c, r);
            if (check == true) {
                notification = "Enroll Course Successfully";
            } else {
                notification = "Enroll Fail";
            }
            //load data
            request.getSession().setAttribute("user", u);
            super.loadHeaderAndAsideRight(request, response);
            response.sendRedirect("../course");
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
