/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin.post;

import Dao.BlogDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Duy Hiep
 */
@WebServlet(name = "ChangePostPropertyController", urlPatterns = {"/post/change"})
public class ChangePostPropertyController extends HttpServlet {

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
        String items = request.getParameter("items");
        String property = request.getParameter("property");
        String index = request.getParameter("index");
        String keyword = request.getParameter("keyword");
        String status = request.getParameter("status");
        String category = request.getParameter("category");
        String author = request.getParameter("author");
        String sort = request.getParameter("sort");
        BlogDao bd = new BlogDao();
        if(items.equals("status")){
            bd.updateStatus(id, property);
        }
        else{
            bd.updateFeature(id,property);
        }
        if(keyword == null){
            response.sendRedirect("../admin/postlist?index=" + index + "&&status=" + status + "&&category=" + category + "&&author=" + author + "&&sort=" + sort);
        }
        else{
            response.sendRedirect("../admin/searchpost?index=" + index + "&&keyword=" + keyword + "&&status=" + status + "&&category=" + category + "&&author=" + author + "&&sort=" + sort);
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
