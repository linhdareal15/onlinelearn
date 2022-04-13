/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.CommonFeature;

import Controller.admin.CommonFuture.AdminHomeController;
import Dao.UserDBContext;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Century
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        if (request.getParameter("id") != null && request.getSession().getAttribute("user")!=null
                ) {
            String action =request.getParameter("action");
            if(action.equals("xvyz")){
            System.out.println(action);
                int id = Integer.parseInt(request.getParameter("id"));
                UserDBContext udb = new UserDBContext();
                User u = udb.getUserById(id);
                request.getSession().setAttribute("user", u);
                response.sendRedirect("../home");}
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
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
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        UserDBContext udb = new UserDBContext();
        User user = udb.getUser(email, pass);
        if (user == null) {
//            request.getSession().setAttribute("user", null);
            String message1 = "Wrong email or password. Please try again!";
            request.setAttribute("message1", message1);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (user != null && (user.getRole().getRole_id() != 1 || user.getRole().getRole_id() != 6) && user.getRole().getRole_id() != 4) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/admin/home");
        } else if (user != null && (user.getRole().getRole_id() == 1 || user.getRole().getRole_id() == 6)) {
                request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        } else {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("../home");

        }
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
