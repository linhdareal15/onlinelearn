/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.UserCourse;

import Dao.QuizDAO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thanh Phong
 */
@WebServlet(name = "UserQuizController", urlPatterns = {"/quiz"})
public class UserQuizController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        QuizDAO dao = new QuizDAO();
        HashMap<String, ArrayList<String>> hm = dao.getAllQuizAnswer(id);
        String quiz = "let questions = [\n";
            if(hm!=null){
                int i= 1;
                int j=1;
                for (Map.Entry<String, ArrayList<String>> entry : hm.entrySet()) {
                    String[]key = entry.getKey().split("-");
                    int id_ques = Integer.parseInt(key[0]);
                    String ques = key[1];
                    String ans = dao.getAnswer(id_ques);
                    ArrayList<String> value = entry.getValue();
                    quiz+="{\n";
                    
                    quiz+="numb:"+i+",\n";
                    quiz+="question:"+'"'+ques+'"'+",\n";
                    quiz+="answer:"+'"'+ans+'"'+",\n";
                    quiz+="options: [\n";
                    for (String string : value) {
                        quiz+='"'+string.trim()+'"';
                        if(j!=4) quiz+=",\n";
                        j++;
                    }
                    quiz+="\n]";
                    quiz+="\n},";
                    i++;
                }
                i=0;
            }
        quiz += "\n];";
        
//        Writer out = new BufferedWriter(new OutputStreamWriter(
//                new FileOutputStream("C:\\Users\\Louis\\Desktop\\SWP\\Iter5\\g4\\web\\client\\js\\questions.js"), "UTF-8"));
//        try {
//            out.write(quiz);
//        } finally {
//            out.close();
//        }

        request.setAttribute("link", quiz);
        request.getRequestDispatcher("client/Quiz.jsp").forward(request, response);

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
//        processRequest(request, response);
        int lesson_id = Integer.parseInt(request.getParameter("lid"));
        int totalQuestion = Integer.parseInt(request.getParameter("totalQuestion"));
        QuizDAO dao = new QuizDAO();
//from user
        //jsp gui ve theo kieu name = question+i ; value="quiz_id-ans"
        HashMap<Integer, String> listAnswerFromUser = new HashMap<>();
        for (int i = totalQuestion; i >= 1; i--) {
            String ans = request.getParameter("question" + i).toString();
            String[] arr = ans.split("-");
            int id = Integer.parseInt(arr[0].trim());
            String answer = arr[1].trim();
            listAnswerFromUser.put(id, answer);
        }
        HashMap<Integer, String> hmCorrectAns = dao.getCorrectAnswer(lesson_id);
        int mark = 0;
        //ktra dap an va cau tra loi
        for (Map.Entry<Integer, String> entry : hmCorrectAns.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            for (Map.Entry<Integer, String> entry1 : listAnswerFromUser.entrySet()) {
                Integer key1 = entry1.getKey();
                String value1 = entry1.getValue();
                if (key1 == key && value.equals(value1)) {
                    mark++;
                }
            }
        }
        System.out.println("Your total = " + mark);
        String returnURL = request.getParameter("returnUrl");
        System.out.println(returnURL);
        request.getSession().setAttribute("mark", mark);
        dao.getScore(mark, mark, mark, mark);
        String quiz_id = request.getParameter("quiz_id");
        response.sendRedirect(returnURL + "?id=" + quiz_id);

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
