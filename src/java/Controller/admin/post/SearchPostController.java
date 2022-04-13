/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin.post;

import Controller.admin.CommonFuture.AdminHomeController;
import Dao.BlogDao;
import Model.BlogCategory;
import Model.BlogDetail;
import Model.BlogList;
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
 * @author Duy Hiep
 */
@WebServlet(name = "SearchPostController", urlPatterns = {"/admin/searchpost"})
public class SearchPostController extends AdminHomeController {

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
        BlogDao bd = new BlogDao();
        String keyword = request.getParameter("keyword");
        String index = request.getParameter("index");
        String status = request.getParameter("status");
        String category = request.getParameter("category");
        String author = request.getParameter("author");
        String sort = request.getParameter("sort");
        
        if(status == null){
            status = "-1";
        }
        if(category == null){
            category = "-1";
        }
        if(author == null){
            author = "-1";
        }
        if(sort == null){
            sort = "title";
        }
        if(index == null || index.length() == 0){
            index = "0";
        }
        int totalPageSearch = bd.countPostSearch(keyword, status, Integer.parseInt(category), Integer.parseInt(author), sort);
        int endPage = totalPageSearch / 4;
        if(totalPageSearch % 4 != 0){
            endPage++;
        }
        ArrayList<User> authors = bd.getAuthor();
        ArrayList<BlogCategory> blcategory = bd.getBlogCategory();
        ArrayList<BlogList> searchBlogList = bd.getSearchPaginationBlogList(keyword, Integer.parseInt(index),status,Integer.parseInt(category),Integer.parseInt(author),sort);
        request.setAttribute("current_index", index);
        request.setAttribute("endPage", endPage);
        request.setAttribute("status", status);
        request.setAttribute("category", category);
        request.setAttribute("author", author);
        request.setAttribute("sort", sort);
        request.setAttribute("keyword", keyword);
        request.setAttribute("authors", authors);
        request.setAttribute("blcategory", blcategory);
        request.setAttribute("searchBlogList", searchBlogList);
        
        super.loadHeaderAndAsideRight(request, response);
        String title_value = "Post Search";
        request.setAttribute("title_value", title_value);
        request.setAttribute("pageInclude", "../admin/searchPost.jsp");
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
