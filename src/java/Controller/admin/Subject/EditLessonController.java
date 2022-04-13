/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin.Subject;

import Controller.admin.CommonFuture.AdminHomeController;
import Dao.LessonDAO;
import Model.Topic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Century
 */
@WebServlet(name = "EditLessonController", urlPatterns = {"/admin/edit-lesson"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB 
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100)   	// 100 MB
public class EditLessonController extends AdminHomeController {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditLessonController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditLessonController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        super.loadHeaderAndAsideRight(request, response);
        String id = request.getParameter("id");
        response.getWriter().print(id);
        LessonDAO ld = new LessonDAO();
        ArrayList<Topic> topics = ld.getAllTopics();
        
        request.setAttribute("topics", topics);
        request.setAttribute("lessonById", ld.GetALessonByID(Integer.parseInt(id)));
        String title_value = "Edit Lesson";
        request.setAttribute("title_value", title_value);
        request.setAttribute("pageInclude", "../admin/EditLesson.jsp");
        request.getRequestDispatcher("../admin/home.jsp").forward(request, response);
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
        int lesson_id = Integer.parseInt(request.getParameter("lesson_id"));
        String title = request.getParameter("title");
        int type = Integer.parseInt(request.getParameter("type"));
        int belongtotopic = Integer.parseInt(request.getParameter("belongtotopic"));
        String order = request.getParameter("order");
        boolean status = request.getParameter("status").equals("active");
        String videolink = request.getParameter("videolink");
        String content = request.getParameter("content");
        LessonDAO ld = new LessonDAO();

        ld.updateLesson(lesson_id, title, type, belongtotopic, order, status, videolink, content);
        response.sendRedirect("SubjectLesson");
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
