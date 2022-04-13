/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.CommonFeature;

import Dao.ChangePasswordDAO;
import Dao.ForgetPasswordDAO;
import Model.Email;
import Model.EmailUtils;
import Model.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thanh Phong
 */
@WebServlet(name = "ForgetPasswordController", urlPatterns = {"/forgetpassword"})
public class ForgetPasswordController extends HttpServlet {

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

        request.getRequestDispatcher("client/resetPassword.jsp").forward(request, response);

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
        String email = request.getParameter("email");
        ForgetPasswordDAO dao = new ForgetPasswordDAO();

        User user = dao.findUserByEmail(email);
        ChangePasswordDAO dao1 = new ChangePasswordDAO();

        if (user == null) {
            request.getSession().setAttribute("messager", "Your email is incorrect. Please try again!");
            response.sendRedirect("forgetpassword");
        } else {
            try {
                String link = "http://localhost:8080/g4/resetpassword?email=" + user.getEmail();
                Email e = new Email();
                e.setFrom("phongpro3639@gmail.com");
                e.setFromPassword("phongpro369");
                e.setTo(email);
                e.setSubject("Online Learing Shop");
                StringBuilder sb = new StringBuilder();
                sb.append("Dear my bro").append("<br>");
                sb.append("You are used the forgot password function. <br>");
                sb.append("Please click this link to reset your password: ").append(link);
                sb.append("<br>Regards<br>");
                sb.append("Adminstrator");

                e.setContent(sb.toString());
                EmailUtils.send(e);
                HttpSession session = request.getSession();
                session.setAttribute("email", user.getEmail());
                session.setAttribute("messager", "Email sent to your email address."
                        + " Please check and get your password!");

                response.sendRedirect("forgetpassword");

            } catch (Exception ex) {
                Logger.getLogger(ForgetPasswordController.class.getName()).log(Level.SEVERE, null, ex);

            }

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
