/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin.settingslist;

import Controller.admin.CommonFuture.AdminHomeController;
import Dao.SettingsListDBContext;
import Model.Setting;
import Model.SettingStatus;
import Model.SettingType;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Quang
 */
public class EditController extends AdminHomeController {

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
        String eid = request.getParameter("sid");
        SettingsListDBContext dao = new SettingsListDBContext();
        Setting s = dao.getSettingById(eid);
        request.setAttribute("st", s);
        ArrayList<SettingType> ltype = dao.listSettingType();
        request.setAttribute("ltype", ltype);

        ArrayList<SettingStatus> ls = dao.listSettingStatus();
        request.setAttribute("ls", ls);
        super.loadHeaderAndAsideRight(request, response);
        String title_value = "Edit Setting";
        request.setAttribute("title_value", title_value);
        request.setAttribute("pageInclude", "edit.jsp");
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
        String eid = request.getParameter("id");
        String ename = request.getParameter("name");
        int estatus = Integer.parseInt(request.getParameter("status"));
        String evalue = request.getParameter("value");
        String edescription = request.getParameter("description");
        int etype = Integer.parseInt(request.getParameter("type"));
        SettingsListDBContext dao = new SettingsListDBContext();

        dao.updateSetting(eid, ename, edescription, etype, evalue, estatus);
        response.sendRedirect("settingslist");
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
