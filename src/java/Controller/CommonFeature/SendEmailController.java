/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.CommonFeature;

import Dao.RegisterDao;
import Model.Token;
import Model.User;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Duy Hiep
 */
public class SendEmailController extends HttpServlet {

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
        try {
            EncryptionDecryptionAES es = (EncryptionDecryptionAES) request.getServletContext().getAttribute("es");
            String verifyCode = es.decrypt(request.getParameter("key1"));
            String user_id = es.decrypt(request.getParameter("key2"));
            RegisterDao rao =  new RegisterDao();
            User u = (User) request.getServletContext().getAttribute("User");
            String verify = (String)request.getServletContext().getAttribute("verify");
            Token t = rao.getToken(Integer.parseInt(user_id));
            Long datetime = System.currentTimeMillis();
            Timestamp now = new Timestamp(datetime);
            
            if((verifyCode.equals(verify) && now.before(t.getExpired())) || (verifyCode.equals(verify + 0) && now.before(t.getExpired()))){
                rao.changestatusUser(u);
                response.sendRedirect("client/login");
                
            }
            else{
                SendEmail sendEmail = new SendEmail();
                String verifycode = verifyCode + 0;
                String subject = "Email Verification Link";
                String message = "Click this link to confirm your email address and complete setup for your account.\n"
                        + "Verification Link: " + "http://localhost:8080/g4/verify?key1=" + es.encrypt(verifycode) + "&&key2=" + es.encrypt(Integer.toString(u.getId()));
                sendEmail.send(u.getEmail(), subject, message);
                t.setCode(verifycode);
                t.setUpdatedAt(now);
                Timestamp after = new Timestamp(now.getTime() + (5 * 60 * 1000));
                t.setExpired(after);
                rao.updateToken(t);
                response.sendRedirect("client/verifyagain.jsp");
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
            Logger.getLogger(SendEmailController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SendEmailController.class.getName()).log(Level.SEVERE, null, ex);
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
