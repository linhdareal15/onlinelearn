/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin.Subject;

import Controller.admin.CommonFuture.AdminHomeController;
import Dao.CourseDAO;
import Model.Course;
import Model.Registration;
import Model.RegistrationStatus;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Louis
 */
@WebServlet(name = "RegistrationsListController", urlPatterns = {"/admin/RegistrationsList"})
public class RegistrationsListController extends AdminHomeController {

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
        CourseDAO dao = new CourseDAO();
        super.loadHeaderAndAsideRight(request, response);
        try {

            int cid = 0;
            String email = "";
            String from = "";
            String to = "";
            int status = 0;
            String name ="";
            if (request.getParameter("cid") != null) {
                cid = Integer.parseInt(request.getParameter("cid"));
            }
            if (request.getParameter("name") != null) {
                name = request.getParameter("name");
            }
            if (request.getParameter("email") != null) {
                email = request.getParameter("email");
            }
            if (request.getParameter("status") != null) {
                status = Integer.parseInt(request.getParameter("status"));
            }
            if (request.getParameter("from") != null && request.getParameter("to") != null) {
                from = request.getParameter("from");
                to = request.getParameter("to");
            }
            final int PAGE_SIZE = 5;
            int page = 1;
            int totalRegistration = dao.totalRegistration(cid, email, from, to, status);
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            int totalPage = totalRegistration / PAGE_SIZE;
            if (totalRegistration % PAGE_SIZE != 0) {
                totalPage++;
            }
            int start = (page - 1) * PAGE_SIZE;
            List<Registration> list = dao.getRegistrationList(cid, email, from, to, status, start, PAGE_SIZE);
            List<Course> listCourse = dao.getAllCourse();
            List<RegistrationStatus> listRegisStatus = dao.getAllRegisStatus();
            request.setAttribute("ListStatus", listRegisStatus);
            request.setAttribute("ListCourse", listCourse);
            request.setAttribute("ListRegistration", list);
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            String title_value = "Registration List";
            request.setAttribute("url", "RegistrationsList?cid="+cid+"&email="+email+"&from="+from+"&to="+to+"&status="+status);
            request.setAttribute("title_value", title_value);
            request.setAttribute("pageInclude", "../admin/RegistrationList.jsp");
            request.getRequestDispatcher("../admin/home.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.sendRedirect("../admin/home");
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
