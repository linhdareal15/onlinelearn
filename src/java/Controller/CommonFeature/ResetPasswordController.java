/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.CommonFeature;

import Dao.ChangePasswordDAO;
import Model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thanh Phong
 */
@WebServlet(name = "ResetPasswordController", urlPatterns = {"/resetpassword"})
public class ResetPasswordController extends HttpServlet {

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
        String email;
        if (request.getParameter("email") != null) {
            email = request.getParameter("email");
            request.setAttribute("email", email);
            request.getRequestDispatcher("client/changePassword1.jsp").forward(request, response);
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
//        HttpSession session = request.getSession();
//        String email = session.getAttribute("email").toString();
        String email=request.getParameter("email");
        String newpass = request.getParameter("newpassword");
        String repass = request.getParameter("repassword");
        ChangePasswordDAO dao = new ChangePasswordDAO();
        User u1 = new User();
        if (email != null) {
             if(!newpass.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")){
                String message="Password must contain minimum 8 characters, at least one uppercase letter, one lowercase letter and one digit and one special characters";
                request.getSession().setAttribute("message1", message);
                response.sendRedirect("http://localhost:8080/g4/resetpassword?email="+email);
            }
             else if (newpass.equals(repass)&&newpass.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")) {
                dao.changePasswordByEmail(email, newpass);
                response.sendRedirect("client/login");
            }
            
            else {
                String message = "Confirm password do not match with new password. Please try again!";
                request.getSession().setAttribute("message1", message);
                response.sendRedirect("http://localhost:8080/g4/resetpassword?email="+email);
            }

        }
//        else{
//            String message="Email does not exist. Please try again!";
//            request.getSession().setAttribute("message1", message);
//            response.sendRedirect("resetpassword");
//            
//        }

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
