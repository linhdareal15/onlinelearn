/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin.settingslist;

import Controller.admin.CommonFuture.AdminHomeController;
import Dao.SettingsListDBContext;
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
public class AddSettingsController extends AdminHomeController {

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
        SettingsListDBContext dao = new SettingsListDBContext();
        TypeConfigController tcc = new TypeConfigController();
        ArrayList<SettingType> ltype = tcc.getTypesList();
        for (SettingType settingType : ltype) {
            System.out.println(settingType);
        }
        request.setAttribute("ltype", ltype);
        super.loadHeaderAndAsideRight(request, response);
        String title_value = "Add Setting";
        request.setAttribute("title_value", title_value);
        request.setAttribute("pageInclude", "add.jsp");
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
        String aname = request.getParameter("name");
        int astatus = Integer.parseInt(request.getParameter("status"));
        String avalue = request.getParameter("value");
        String adescription = request.getParameter("description");
        int atype = Integer.parseInt(request.getParameter("type"));
        SettingsListDBContext dao = new SettingsListDBContext();

        dao.insertSetting(aname, adescription, atype, avalue, astatus);
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
