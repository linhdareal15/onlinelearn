/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin.Subject;

import Controller.admin.CommonFuture.AdminHomeController;
import Dao.CourseDAO;
import Dao.UserDBContext;
import Model.Course;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Quang
 */
@WebServlet(name = "SubjectListController", urlPatterns = {"/admin/subjectlist"})
public class CourseSubjectListController extends AdminHomeController {

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
        response.setContentType("text/html;charset=UTF-8");
        String raw_author_id = request.getParameter("author");
        String raw_subject_status_id = request.getParameter("status");
        String subject_title = request.getParameter("subject_title");
        String index = request.getParameter("index");

        if (raw_author_id == null || raw_author_id.length() == 0) {
            raw_author_id = "-1";
        }
        if (raw_subject_status_id == null || raw_subject_status_id.length() == 0) {
            raw_subject_status_id = "-1";
        }
        if (index == null || index.length() == 0) {
            index = "0";
        }

        int subject_status = Integer.parseInt(raw_subject_status_id);
        int author = Integer.parseInt(raw_author_id);
        //get all author
        UserDBContext userDBContext = new UserDBContext();
        ArrayList<User> authors = userDBContext.getAllAuthor();
        request.setAttribute("authors", authors);
        //get all coure
        CourseDAO courseDAO = new CourseDAO();
        int totalPageSearch = courseDAO.getRowcount(subject_status, author, subject_title);
        int endPage = totalPageSearch / 3;
        if (totalPageSearch % 3 != 0) {
            endPage++;
        }
        ArrayList<Course> courses = courseDAO.getAllCourseSubject(Integer.parseInt(index), subject_status, author, subject_title);
        request.setAttribute("current_index", index);
        request.setAttribute("endPage", endPage);
        request.setAttribute("courses", courses);

        request.setAttribute("subject_title", subject_title);
        request.setAttribute("subject_status", subject_status);
        request.setAttribute("author_id", author);

        //load common feature
        super.loadHeaderAndAsideRight(request, response);
        String title_value = "Subject";
        request.setAttribute("title_value", title_value);
        request.setAttribute("pageInclude", "../admin/subjectList.jsp");
        request.getRequestDispatcher("../admin/home.jsp").forward(request, response);
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
