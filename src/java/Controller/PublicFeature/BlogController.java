/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.PublicFeature;

import Controller.CommonFeature.Home;
import Dao.BlogDao;
import Model.BlogCategory;
import Model.BlogList;
import Model.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Duy Hiep
 */
public class BlogController extends Home {

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
        String index = request.getParameter("index");
        if(index == null || index.length() == 0){
            index = "0";
        }
        BlogDao bd = new BlogDao();
        ArrayList<BlogCategory> blogCategory = bd.getBlogCategory();
        int total_blog = bd.countBlog(0);
        int endPage = total_blog / 4;
        if(total_blog % 4 != 0){
            endPage++;
        }
        ArrayList<BlogList> highlightPost = bd.getHighLightBlog();
        request.setAttribute("highlightPost", highlightPost);
        ArrayList<BlogList> blogList = bd.getPaginationBlogList(Integer.parseInt(index));       
        request.setAttribute("current_index", index);
        request.setAttribute("endPage", endPage);
        request.setAttribute("blogList", blogList);
        request.setAttribute("blogCategory", blogCategory);
        User user = (User) request.getSession().getAttribute("user");
        String title_value = "ECOURSES - BLOG";
        request.setAttribute("title_value", title_value);
        super.loadHeaderAndAsideRight(request, response);
        request.setAttribute("user", user);
        request.setAttribute("pageInclude", "/client/blog.jsp");
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
