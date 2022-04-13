/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin.Subject;

import Controller.admin.CommonFuture.AdminHomeController;
import Dao.CourseDAO;
import Dao.LessonDAO;
import Dao.UserDBContext;
import Model.Course;
import Model.CourseCategory;
import Model.Topic;
import Model.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Quang
 */
@WebServlet(name = "AddLessonController", urlPatterns = {"/admin/add-lesson"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB 
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100)   	// 100 MB
public class AddLessonController extends AdminHomeController {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        UserDBContext userDBContext = new UserDBContext();
        CourseDAO courseDAO = new CourseDAO();
        LessonDAO lessonDAO = new LessonDAO();

        ArrayList<User> authors = userDBContext.getAllAuthor();
        ArrayList<Topic> topics = lessonDAO.getAllTopics();
        super.loadHeaderAndAsideRight(request, response);

        request.setAttribute("authors", authors);
        request.setAttribute("topics", topics);
        String title_value = "Add Subject";
        request.setAttribute("title_value", title_value);
        request.setAttribute("pageInclude", "../admin/AddLesson.jsp");

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");
        int type = Integer.parseInt(request.getParameter("type"));
        int belongtotopic = Integer.parseInt(request.getParameter("belongtotopic"));
        String order = request.getParameter("order");
        boolean status = request.getParameter("status").equals("active");
        String videolink = request.getParameter("videolink");
        String content = request.getParameter("content");
        
        LessonDAO ld = new LessonDAO();
        ld.addLesson(title, type, belongtotopic, order, status, videolink, content);
        response.sendRedirect("SubjectLesson");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    private String extractFileName(Part part) {
        // form-data; name="file"; filename="C:\file1.zip"
        // form-data; name="file"; filename="C:\Note\file2.zip"
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                // C:\file1.zip
                // C:\Note\file2.zip
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                // file1.zip
                // file2.zip
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
