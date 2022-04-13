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
import Model.CourseCategory;
import Model.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
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
@WebServlet(name = "AddSubject", urlPatterns = {"/admin/addsubject"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB 
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100)   	// 100 MB
public class AddSubjectController extends AdminHomeController {

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
        ArrayList<User> authors = userDBContext.getAllAuthor();
        ArrayList<CourseCategory> categorys = courseDAO.getAllCourseCategory();

        User user = (User) request.getSession().getAttribute("user");
        super.loadHeaderAndAsideRight(request, response);
        request.setAttribute("user", user);
        request.setAttribute("authors", authors);
        request.setAttribute("categorys", categorys);
        String title_value = "Add Subject";
        request.setAttribute("title_value", title_value);
        request.setAttribute("pageInclude", "../admin/addsubject.jsp");
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
        CourseDAO courseDAO = new CourseDAO();
        String message;
        String title = request.getParameter("title");
        int author_id = Integer.parseInt(request.getParameter("authors"));
        String brief = request.getParameter("brief");
        double listprice = Double.parseDouble(request.getParameter("listprice"));
        double saleprice = Double.parseDouble(request.getParameter("saleprice"));
        boolean status = request.getParameter("status").equals("Publish");
        String introduction = request.getParameter("introduction");
//        String filename =  request.getParameter("document");
        int category_id = Integer.parseInt(request.getParameter("categorys"));
        boolean feature;
        if (request.getParameter("feature") != null && request.getParameter("feature").equals("ON")) {
            feature = true;
        } else {
            feature = false;
        }
        long millis = System.currentTimeMillis();
        Date updateDate = new Date(millis);
        //get thumbnail
        Part filePart = request.getPart("thumbnail"); // Retrieves <input type="file" name="thumbnail">
        InputStream fileContent = filePart.getInputStream();
        //get document
        Part fileDocs = request.getPart("file");
        InputStream fileDocument = fileDocs.getInputStream();
        String filename = fileDocs.getSubmittedFileName();

        //set subject
        Course c = new Course();
        c.setTitle(title);
        User author = new User();
        author.setId(author_id);
        c.setAuthor(author);
        c.setBriefinfo(brief);
        c.setListprice(listprice);
        c.setSaleprice(saleprice);
        c.setStatus(status);
        c.setIntroduction(introduction);
        c.setFilename(filename);
        c.setFeatureflag(feature);
        c.setUpdatedate(updateDate);
        CourseCategory category = new CourseCategory();
        category.setId(category_id);
        c.setCategory(category);
        int row = courseDAO.insertSubject(c, fileContent, fileDocument);

        if (row == 0) {
            message = "Add Unsuccessfull";
        } else {
            message = "Add Successfull";
        }
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user", user);
        request.getSession().setAttribute("message", message);
        response.sendRedirect("addsubject");
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
