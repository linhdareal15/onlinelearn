/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin.user;

import Controller.admin.CommonFuture.AdminHomeController;
import Dao.SliderDAO;
import Dao.UserDAO;
import Model.Role;
import Model.Slider;
import Model.Status;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Century
 */
public class UserController extends AdminHomeController {

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
        String role = request.getParameter("role");
        String status = request.getParameter("status");
        String sort = request.getParameter("sort");

        if (role == null) {
            role = "-1";
        }
        if (status == null) {
            status = "-1";
        }
        if (sort == null) {
            sort = "id";
        }
        if (index == null || index.length() == 0) {
            index = "0";
        }

        //get data
        UserDAO u = new UserDAO();
        //get all roles
        ArrayList<Role> roles = u.getAllRoles();
        request.setAttribute("roles", roles);
        //get all status
        ArrayList<Status> statuses = u.getAllStatus();
        request.setAttribute("statuses", statuses);
        //get all users
        int total_user = u.countPagintionUserList(Integer.parseInt(role), Integer.parseInt(status), sort);
        int endPage = total_user / 5;
        if (total_user % 5 != 0) {
            endPage++;
        }
        List<User> users = u.getPagintionUserList(Integer.parseInt(index), Integer.parseInt(role), Integer.parseInt(status), sort);
        request.setAttribute("users", users);
        request.setAttribute("sort", sort);
        request.setAttribute("role", role);
        request.setAttribute("status", status);
        request.setAttribute("current_index", index);
        request.setAttribute("endPage", endPage);
        //load common feature
        super.loadHeaderAndAsideRight(request, response);
        String title_value = "User List";
        request.setAttribute("title_value", title_value);
        request.setAttribute("pageInclude", "../admin/userList.jsp");
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
