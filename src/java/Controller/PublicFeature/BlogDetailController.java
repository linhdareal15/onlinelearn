/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.PublicFeature;

import Controller.CommonFeature.Home;
import Dao.BlogDao;
import Model.BlogCategory;
import Model.BlogDetail;
import Model.BlogList;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Duy Hiep
 */
public class BlogDetailController extends Home {

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
        int id = Integer.parseInt(request.getParameter("id"));
        BlogDao bd = new BlogDao();
        ArrayList<BlogCategory> blogCategory = bd.getBlogCategory();
        BlogDetail blogDetail = bd.getBlogDetailById(id);
        ArrayList<BlogList> highlightPost = bd.getHighLightBlog();
        request.setAttribute("highlightPost", highlightPost);
        request.setAttribute("blogCategory", blogCategory);
        request.setAttribute("blogDetail", blogDetail);
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user", user);
        super.loadHeaderAndAsideRight(request, response);
        String title_value = "ECOURSES - BLOG DETAIL";
        request.setAttribute("title_value", title_value);
        request.setAttribute("pageInclude", "/client/blogdetail.jsp");
        request.getRequestDispatcher("/client/index.jsp").forward(request, response);

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
