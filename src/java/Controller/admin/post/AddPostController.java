/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin.post;

import Controller.CommonFeature.FileUploadHelper;
import Controller.admin.CommonFuture.AdminHomeController;
import Dao.BlogDao;
import Model.BlogCategory;
import Model.BlogDetail;
import Model.BlogList;
import Model.User;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Duy Hiep
 */
@WebServlet(name = "AddPostController", urlPatterns = {"/admin/addpost"})
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100)   	// 100 MB
public class AddPostController extends AdminHomeController {

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
        BlogDao bd =  new BlogDao();
        ArrayList<BlogCategory> blcategory = bd.getBlogCategory();
        request.setAttribute("category", blcategory);
        super.loadHeaderAndAsideRight(request, response);
        String title_value = "Add Post";
        request.setAttribute("title_value", title_value);
        request.setAttribute("pageInclude", "../admin/addPost.jsp");
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
        BlogDao blogdao = new BlogDao();
        String message;
        String title = request.getParameter("title");
        String brief = request.getParameter("brief");
        String content = request.getParameter("content");
        int category_id = Integer.parseInt(request.getParameter("category"));
        boolean status = request.getParameter("status").equals("Active");
        boolean feature = request.getParameter("feature").equals("On");
        //get thumbnail

        //get thumbnail
        FileUploadHelper helper = new FileUploadHelper();
        final String path = "C:\\Users\\Duy Hiep\\OneDrive\\Máy tính\\SWP301\\g4\\web\\client\\img\\blog";
        Part filePart = request.getPart("thumbnail"); // Retrieves <input type="file" name="thumbnail">
        String fileName = helper.getFileName(filePart); // getFilename from file part
        helper.getFileContent(fileName, filePart, path);
        File fileUpload = new File(path + "\\" + fileName);
        String thumbnail = helper.getUrlCloudinaryForAdd(fileUpload,fileName);  
        //set bloglist, blog detail
        BlogDetail bd = new BlogDetail();
        BlogList bl = new BlogList();
        User u = (User) request.getSession().getAttribute("user");
        bd.setAuthor(u);
        bd.setPost_content(content);
        long millis= System.currentTimeMillis(); 
        Date updated_date = new Date(millis);
        bl.setPostdate(updated_date);
        bd.setUpdated_date(updated_date);
        bd.setBloglist_id(bl);
        bl.setTitle(title.toUpperCase());
        BlogCategory bc = new BlogCategory();
        bc.setId(category_id);
        bl.setCategory_id(bc);
        bl.setBrief_info(brief);
        bl.setStatus(status);
        bl.setFeature(feature);
        int row = blogdao.insertPost(bl,bd,thumbnail);

        if(row == 0){
            message = "Add Unsuccessfull";
        }
        else{
            message = "Add Successfull";
        }
        request.getSession().setAttribute("message", message);
        response.sendRedirect("addpost");
        
        
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
