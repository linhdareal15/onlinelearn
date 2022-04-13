/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin.Subject;

import Dao.CourseDAO;
import Dao.UserCourseDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Quang
 */
@WebServlet(name = "DowloadDocumentController", urlPatterns = {"/dowload/document"})
public class DowloadDocumentController extends HttpServlet {

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
        CourseDAO courseDAO = new CourseDAO();
        Blob blob = courseDAO.getCourseById(id).getDocument();
        String filename = courseDAO.getCourseById(id).getFilename();
        byte[] buffer;
        try {
            if (blob != null) {
                int bytesRead = -1;
                InputStream inputStream = blob.getBinaryStream();
                response.reset();
                buffer = blob.getBytes(1, (int) blob.length());
                OutputStream os = response.getOutputStream();
                response.setContentType("aplication/octet-stream");
                response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
                request.setAttribute("filename", filename);
                ServletOutputStream out = response.getOutputStream();
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, (int) blob.length());
                }
                
                inputStream.close();
                os.close();
            } else {
                response.sendRedirect("../admin/editcoursesubject?coursesubjectid=" + id);
            }

        } catch (SQLException ex) {

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
