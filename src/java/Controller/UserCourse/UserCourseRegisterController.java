/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.UserCourse;

import Controller.CommonFeature.Home;
import Dao.CourseDAO;
import Dao.UserCourseDAO;
import Model.Course;
import Model.Registration;
import Model.RegistrationStatus;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Quang
 */
@WebServlet(name = "UserCourseRegisterController", urlPatterns = {"/course/user-register"})
public class UserCourseRegisterController extends Home {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.loadHeaderAndAsideRight(request, response);
        User u = (User) request.getSession().getAttribute("user");
        UserCourseDAO userCourseDAO = new UserCourseDAO();
        int courseId = Integer.parseInt(request.getParameter("id"));
        String notification = "";
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
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
        request.getSession().setAttribute("user", u);
        super.loadHeaderAndAsideRight(request, response);
        response.sendRedirect("../course");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
