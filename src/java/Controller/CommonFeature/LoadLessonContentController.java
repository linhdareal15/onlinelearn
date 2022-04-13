/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.CommonFeature;

import Dao.CourseDAO;
import Dao.LessonDAO;
import Dao.QuizDAO;
import Model.Lesson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Louis
 */
@WebServlet(name = "LoadLessonContentController", urlPatterns = {"/loadlesson"})
public class LoadLessonContentController extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        CourseDAO dao = new CourseDAO();
        LessonDAO lDao = new LessonDAO();
        QuizDAO qdao = new QuizDAO();
        
        try{
            HttpSession ses =request.getSession();
            String paramFromAjax = request.getParameter("type");
            String[] type = paramFromAjax.split("-");
            if(type[0].equalsIgnoreCase("Lesson")){
                int lesson_id = Integer.parseInt(type[1].trim());
                //set active lesson session
                Lesson l = lDao.GetALessonByID(lesson_id);
                out.write("<div class='pt-5'><center><iframe width=\"860\" height=\"515\" "
                       + "src='" + l.getVideolink()+ "'"
                        + " title=\"YouTube video player\" frameborder=\"0\" "
                        + "allow=\"accelerometer; autoplay; clipboard-write; "
                        + "encrypted-media; gyroscope; picture-in-picture\" allowfullscreen>"
                        + "</iframe></center></div>");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
//        try {
//            
//            
//            if (type[0].equalsIgnoreCase("Video")) {
//                int sublessonId = Integer.parseInt(type[1].trim());
//                Sub_Lesson sub = dao.getSubLesson(sublessonId);
                
//            } //xu li quiz
//            else if (type[0].equalsIgnoreCase("Quiz")) {
//                int quiz_id = Integer.parseInt(type[1].trim());
//                System.out.println("QuizId = "+quiz_id);
//                HashMap<String, ArrayList<String>> hm = qdao.getAllQuizAnswerByQuizID(quiz_id);
//                int count = 0;
//                out.write("<form action=\"quiz\" method=\"post\">");
//                for (Map.Entry<String, ArrayList<String>> entry : hm.entrySet()) {
//                    
//                    String key = entry.getKey();
//                    System.out.println(key);
//                    String[] arr = key.split("-");
//                    String id = arr[0].trim();
//                    String question = arr[1].trim();
//                    ArrayList<String> value = entry.getValue();
//                    count++;
//                    out.write("<div>\n"
//                            + "                <h3>Question " + count + "</h3>\n"
//                            + "                <p>" + question + "</p>\n"
//                            + "                <feildset id=\"question" + count + "\">");
//                    for (String ans : value) {
//                        out.write("\n");
//                        out.write("                <p><input type=\"radio\" required name=\"question");
//                        out.print(count);
//                        out.write("\" value=\"");
//                        out.print(id + "-");
//                        out.print(ans);
//                        out.write("\"> &nbsp;");
//                        out.print(ans);
//                        out.write("</p>\n");
//                        out.write("                \n");
//                        out.write("                ");
//                    }
//                    out.write("\n");
//                    out.write("                </feildset>\n");
//                    out.write("\n");
//                    out.write("            </div>\n");
//                    out.write("\n");
//                    out.write("            ");
//                }
//                out.write("<input type=\"hidden\" name=\"totalQuestion\" value=\""+count+"\">");
//                out.write("</form>");
//
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

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
