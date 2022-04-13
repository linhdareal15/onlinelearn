/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin.user;

import Controller.CommonFeature.EncryptionDecryptionAES;
import Controller.CommonFeature.SendEmail;
import Controller.admin.CommonFuture.AdminHomeController;
import Dao.RegisterDao;
import Dao.UserDAO;
import Model.Role;
import Model.Token;
import Model.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Century
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB 
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100)   	// 100 MB
public class AddUserController extends AdminHomeController {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddUserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddUserController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        UserDAO ud = new UserDAO();
        List<Role> roles = ud.getAllRoles();
        request.setAttribute("roles", roles);

        super.loadHeaderAndAsideRight(request, response);
        String title_value = "Add User";
        request.setAttribute("title_value", title_value);
        request.setAttribute("pageInclude", "../admin/addUser.jsp");
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

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String oldPassword = request.getParameter("password");

            EncryptionDecryptionAES eS = new EncryptionDecryptionAES();
            password = eS.encrypt(password);

            String fullname = request.getParameter("fullname");
            boolean gender = request.getParameter("gender").equals("male");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phonenumber = request.getParameter("phonenumber");
            int status = Integer.parseInt(request.getParameter("status"));
            int role_id = Integer.parseInt(request.getParameter("role_id"));
            Part filePart = request.getPart("avatar"); // Retrieves <input type="file" name="thumbnail">
            InputStream fileContent = filePart.getInputStream();

            UserDAO ud = new UserDAO();
            RegisterDao rao = new RegisterDao();

            Role r = new Role();
            r.setRole_id(role_id);
            User u = new User(-1, username, password, fullname,
                    gender, address, email, phonenumber, status, r);
            ud.addUser(u, fileContent);
            u = rao.getUser(username, email);

            request.getServletContext().setAttribute("User", u);
            SendEmail sendEmail = new SendEmail();
            String verifyCode = sendEmail.getRandom();
            String subject = "Email Verification Link";
            String message = "Username and password you just generate at website Onlinelearn:\n"
                    + "Username: " + username + "\n"
                    + "Password: " + oldPassword;
            sendEmail.send(email, subject, message);
            Token t = new Token();
            t.setUser_id(u);
            t.setCode(verifyCode);
            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            t.setCreatedAt(timestamp);
            t.setUpdatedAt(timestamp);
            Timestamp after = new Timestamp(timestamp.getTime() + (5 * 60) * 1000);
            t.setExpired(after);
            rao.insertToken(t);
            request.getServletContext().setAttribute("es", eS);
            request.getServletContext().setAttribute("verify", verifyCode);

            response.sendRedirect("user");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
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
