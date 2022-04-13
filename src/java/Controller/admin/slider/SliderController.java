/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin.slider;

import Controller.admin.CommonFuture.AdminHomeController;
import Dao.SliderDAO;
import Model.Setting;
import Model.Slider;
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
public class SliderController extends AdminHomeController {

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
            out.println("<title>Servlet SliderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SliderController at " + request.getContextPath() + "</h1>");
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
        SliderDAO sd = new SliderDAO();
        
        String status = request.getParameter("status");
        if (status == null) {
            status = "-1";
        }
        
        String keyword = request.getParameter("keyword");
        List<Slider> searchStatus = sd.getSlidersByStatus(Integer.parseInt(status), keyword);
        request.setAttribute("searchStatus", searchStatus);
        
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        int count = sd.getTotalSliders();
        int endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }
        request.setAttribute("tag", index);
        request.setAttribute("endPage", endPage);
        request.setAttribute("getStatus", status);
        request.setAttribute("getKeyword", keyword);
        
        String idChange = (request.getParameter("idChange"));
        String statusChange = (request.getParameter("statusChange"));
        if (idChange != null && statusChange != null) {
            sd.changeStatus(Integer.parseInt(idChange), Integer.parseInt(statusChange));
        }
        
        super.loadHeaderAndAsideRight(request, response);
        String title_value = "Slider List";
        request.setAttribute("title_value", title_value);
        request.setAttribute("pageInclude", "../admin/sliderList.jsp");
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
