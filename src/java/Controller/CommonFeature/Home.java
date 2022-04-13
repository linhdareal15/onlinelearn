/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.CommonFeature;

import Dao.BlogDao;
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
public class Home extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void loadHeaderAndAsideRight(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login_href_value = "";
        String logout_href = "";
        if (request.getSession().getAttribute("user") == null) {

        } else {
            User user = (User) request.getSession().getAttribute("user");
            login_href_value += user.getFullname();
        }
        request.setAttribute("login_href_value", login_href_value);
        request.setAttribute("logout_href", logout_href);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {       
        BlogDao dao = new BlogDao();
        ArrayList<BlogList> list = dao.getLastNBlog(3);
        request.setAttribute("blog", list);
        
        loadHeaderAndAsideRight(request, response);
        loadHeaderAndAsideRight(request, response);
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user", user);
        String title_value = "ECOURSES - Online Courses";
        request.setAttribute("title_value", title_value);
        request.setAttribute("pageInclude", "/client/clientHomePage.jsp");
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
