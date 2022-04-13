/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin.dashboard;

import Controller.admin.CommonFuture.AdminHomeController;
import Dao.DashboardDao;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Duy Hiep
 */
@WebServlet(name = "DashBoardController", urlPatterns = {"/admin/dashboard"})
public class DashBoardController extends AdminHomeController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        if(from == null){
            from = LocalDate.now().minusDays(7).toString();
        }
        if(to == null){
            to = LocalDate.now().toString();
        }
        DashboardDao dao = new DashboardDao();
        int totalCustomers = dao.getTotalCustomers();
        int totalCourses = dao.getTotalCourses();
        int totalRevenues = dao.getTotalRevenues();
        int newsubject = dao.getNewSubject(from, to);
        TreeMap<String, TreeMap<String, Integer>> revenuesByCourseCategory = dao.getRevenuesByCourseCategory(from, to);
        ArrayList<Integer> Marketing = processRevenues(revenuesByCourseCategory, "Marketing");
        ArrayList<Integer> Devlopment = processRevenues(revenuesByCourseCategory, "Devlopment");
        ArrayList<Integer> Program = processRevenues(revenuesByCourseCategory, "Programing Language");
        ArrayList<Integer> Seo = processRevenues(revenuesByCourseCategory, "SEO");
        ArrayList<Integer> Desgin = processRevenues(revenuesByCourseCategory, "Web Design");
        TreeMap<String, Integer> newRegistration = dao.getNewRegistration(from, to);
        HashMap<String, Integer> newCustomerRegistration = dao.getNewCustomerRegistration(from, to);
        ArrayList<TreeMap<String, Integer>> trendOrderCount = dao.getTrendOrderCount(from, to);
        TreeMap<String, Integer> successOrder = trendOrderCount.get(0);
        TreeMap<String, Integer> allOrder = trendOrderCount.get(1);
        ArrayList<String> keydifference = new ArrayList<>();
        for (String a : allOrder.keySet()) {
            if(!successOrder.containsKey(a)){
                keydifference.add(a);
            }
        }
        for (String string : keydifference) {
            successOrder.put(string, 0);
        }
        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("newsubject", newsubject);
        request.setAttribute("totalRevenues", totalRevenues);
        request.setAttribute("totalCourses", totalCourses);
        request.setAttribute("totalCustomers", totalCustomers);
        request.setAttribute("newRegistration", newRegistration);
        request.setAttribute("revenuesByCourseCategory", revenuesByCourseCategory);
        request.setAttribute("newCustomerRegistration", newCustomerRegistration);
        request.setAttribute("successOrder", successOrder);
        request.setAttribute("allOrder", allOrder);
        request.setAttribute("Marketing", Marketing);
        request.setAttribute("Devlopment", Devlopment);
        request.setAttribute("Program", Program);
        request.setAttribute("Seo", Seo);
        request.setAttribute("Desgin", Desgin);
        super.loadHeaderAndAsideRight(request, response);
        String title_value = "DashBoard";
        request.setAttribute("title_value", title_value);
        request.setAttribute("pageInclude", "../admin/dashboard.jsp");
        request.getRequestDispatcher("../admin/home.jsp").forward(request, response);
    }
    
    private ArrayList<Integer> processRevenues(TreeMap<String, TreeMap<String, Integer>> revenues,String course_category){
        ArrayList<Integer> res = new ArrayList<>();
        for (TreeMap<String, Integer> entry : revenues.values()) {
            if(entry.containsKey(course_category)){
                res.add(entry.get(course_category));
            }
        }
        return res;
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
