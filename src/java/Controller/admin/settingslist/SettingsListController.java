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
import Model.User;
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
public class SettingsListController extends AdminHomeController {

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
        String raw_type = request.getParameter("type");
        String raw_status = request.getParameter("status");
        String raw_setting_value = request.getParameter("setting_value");
        String setting_value;
        if (raw_setting_value != null) {
            setting_value = raw_setting_value;
        } else {
            setting_value = null;
        }
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "0";
        }
        int index = Integer.parseInt(indexPage);
        if (raw_type == null || raw_type.length() == 0) {
            raw_type = "-1";
        }
        if (raw_status == null || raw_status.length() == 0) {
            raw_status = "-1";
        }
        int type = Integer.parseInt(raw_type);
        int status = Integer.parseInt(raw_status);

        SettingsListDBContext sldbc = new SettingsListDBContext();

        int count = sldbc.getTotalSettings(type, status, setting_value);
        int endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }
        request.setAttribute("current_index", index);
        request.setAttribute("endPage", endPage);

        TypeConfigController tcc = new TypeConfigController();
        ArrayList<SettingType> settingTypes = tcc.getTypesList();
        request.setAttribute("settingTypesList", settingTypes);

        ArrayList<SettingStatus> settingStatus = sldbc.listSettingStatus();
        request.setAttribute("settingStatusList", settingStatus);

        request.setAttribute("type_id", type);
        request.setAttribute("status_id", status);

        ArrayList<Setting> listBySearch = sldbc.getListSettingBySearch(index, type, status, setting_value);
        request.setAttribute("listBySearch", listBySearch);

        super.loadHeaderAndAsideRight(request, response);
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user", user);
        String title_value = "Settings List";
        request.setAttribute("setting_value", setting_value);
        request.setAttribute("title_value", title_value);
        request.setAttribute("pageInclude", "../admin/settingsList.jsp");
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
