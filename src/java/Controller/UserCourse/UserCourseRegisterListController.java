/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.UserCourse;

import Controller.CommonFeature.Home;
import Dao.UserCourseDAO;
import Model.Registration;
import Model.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Quang
 */
@WebServlet(name = "UserCourseRegisterListController", urlPatterns = {"/wishlist"})
public class UserCourseRegisterListController extends Home {

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
        User user = (User) request.getSession().getAttribute("user");
        UserCourseDAO userCourseDAO = new UserCourseDAO();
        String courseName = request.getParameter("coursename");
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "0";
        }
        int index = Integer.parseInt(indexPage);
        //get course register by user
        int count = userCourseDAO.getTotalWishList(user.getId(), courseName);
        int endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }
        request.setAttribute("current_index", index);
        request.setAttribute("endPage", endPage);
        ArrayList<Registration> list = userCourseDAO.getWithListBySearch(index, user.getId(), courseName);
        request.setAttribute("list", list);

        super.loadHeaderAndAsideRight(request, response);
        request.setAttribute("user", user);
        String title_value = "ECOURSES - MY WISHLIST";
        request.setAttribute("title_value", title_value);
        request.setAttribute("pageInclude", "/client/wishList.jsp");
        request.getRequestDispatcher("/client/index.jsp").forward(request, response);
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
