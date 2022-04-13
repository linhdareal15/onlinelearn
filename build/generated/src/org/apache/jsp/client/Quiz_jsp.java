package org.apache.jsp.client;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Quiz_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <title>Awesome Quiz App | CodingNepal</title>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"../client/css/style_quiz.css\">\r\n");
      out.write("    <!-- FontAweome CDN Link for Icons-->\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\"/>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <!-- start Quiz button -->\r\n");
      out.write("    <div class=\"start_btn\"><button>Start Quiz</button></div>\r\n");
      out.write("\r\n");
      out.write("    <!-- Info Box -->\r\n");
      out.write("    <div class=\"info_box\">\r\n");
      out.write("        <div class=\"info-title\"><span>Some Rules of this Quiz</span></div>\r\n");
      out.write("        <div class=\"info-list\">\r\n");
      out.write("            <div class=\"info\">1. You will have only <span>15 seconds</span> per each question.</div>\r\n");
      out.write("            <div class=\"info\">2. Once you select your answer, it can't be undone.</div>\r\n");
      out.write("            <div class=\"info\">3. You can't select any option once time goes off.</div>\r\n");
      out.write("            <div class=\"info\">4. You can't exit from the Quiz while you're playing.</div>\r\n");
      out.write("            <div class=\"info\">5. You'll get points on the basis of your correct answers.</div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"buttons\">\r\n");
      out.write("            <button class=\"quit\">Exit Quiz</button>\r\n");
      out.write("            <button class=\"restart\">Continue</button>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <!-- Quiz Box -->\r\n");
      out.write("    <div class=\"quiz_box\">\r\n");
      out.write("        <header>\r\n");
      out.write("            <div class=\"title\">Awesome Quiz Application</div>\r\n");
      out.write("            <div class=\"timer\">\r\n");
      out.write("                <div class=\"time_left_txt\">Time Left</div>\r\n");
      out.write("                <div class=\"timer_sec\">15</div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"time_line\"></div>\r\n");
      out.write("        </header>\r\n");
      out.write("        <section>\r\n");
      out.write("            <div class=\"que_text\">\r\n");
      out.write("                <!-- Here I've inserted question from JavaScript -->\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"option_list\">\r\n");
      out.write("                <!-- Here I've inserted options from JavaScript -->\r\n");
      out.write("            </div>\r\n");
      out.write("        </section>\r\n");
      out.write("\r\n");
      out.write("        <!-- footer of Quiz Box -->\r\n");
      out.write("        <footer>\r\n");
      out.write("            <div class=\"total_que\">\r\n");
      out.write("                <!-- Here I've inserted Question Count Number from JavaScript -->\r\n");
      out.write("            </div>\r\n");
      out.write("            <button class=\"next_btn\">Next Que</button>\r\n");
      out.write("        </footer>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <!-- Result Box -->\r\n");
      out.write("    <div class=\"result_box\">\r\n");
      out.write("        <div class=\"icon\">\r\n");
      out.write("            <i class=\"fas fa-crown\"></i>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"complete_text\">You've completed the Quiz!</div>\r\n");
      out.write("        <div class=\"score_text\">\r\n");
      out.write("            <!-- Here I've inserted Score Result from JavaScript -->\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"buttons\">\r\n");
      out.write("            <button class=\"restart\">Replay Quiz</button>\r\n");
      out.write("            <button class=\"quit\">Quit Quiz</button>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <!-- Inside this JavaScript file I've inserted Questions and Options only -->\r\n");
      out.write("    <script src=\"../client/js/questions.js\"></script>\r\n");
      out.write("\r\n");
      out.write("    <!-- Inside this JavaScript file I've coded all Quiz Codes -->\r\n");
      out.write("    <script src=\"../client/js/script_quiz.js\"></script>\r\n");
      out.write("    <input hidden type=\"button\" value=\"0\" id=\"btn1\">\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("    var x = document.getElementById(\"btn1\").value;\r\n");
      out.write("    console.log(x);\r\n");
      out.write("</script>\r\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
