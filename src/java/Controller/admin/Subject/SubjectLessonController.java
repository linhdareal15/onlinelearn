/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin.Subject;

import Controller.admin.CommonFuture.AdminHomeController;
import Dao.CourseDAO;
import Dao.LessonDAO;
import Model.Course;
import Model.Topic;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Louis
 */
@WebServlet(name = "SubjectLessonController", urlPatterns = {"/admin/SubjectLesson"})
public class SubjectLessonController extends AdminHomeController {

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
        response.setContentType("text/html;charset=UTF-8");
        super.loadHeaderAndAsideRight(request, response);
        int cid = 0;
        LessonDAO lDao = new LessonDAO();
        CourseDAO cDao = new CourseDAO();
        List<Course> CourseList = cDao.getAllCourse();
        String title_value = "Subject Lesson";
        request.setAttribute("title_value", title_value);
        request.setAttribute("ListCourse", CourseList);
        request.setAttribute("pageInclude", "../admin/SubjectLesson.jsp");
        
        try {
            
            if (request.getParameter("cid") != null) {
                cid = Integer.parseInt(request.getParameter("cid"));
                List<Topic> listTopic = lDao.getListTopicByCourseId(cid);
                if (!listTopic.isEmpty()) {
                    request.setAttribute("ListTopic", listTopic);
                    request.getRequestDispatcher("../admin/home.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("../admin/home.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("../admin/home.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("ListCourse", CourseList);
            request.setAttribute("pageInclude", "../admin/SubjectLesson.jsp");
            System.out.println("Error at SubjectLessonController: ");
            request.getRequestDispatcher("../admin/home.jsp").forward(request, response);
            System.out.println(e.getMessage());
        }
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
