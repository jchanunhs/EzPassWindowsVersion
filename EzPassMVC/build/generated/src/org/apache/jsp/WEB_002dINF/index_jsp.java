package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("    <head>\n");
      out.write("        <title>Ez Pass Web Application</title>\n");
      out.write("        <link rel=\"stylesheet\" href = \"styles/content.css\">\n");
      out.write("        <link rel=\"shortcut icon\" href=\"images/favicon.ico\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <meta name=\"author\" content=\"Jason Chan\">\n");
      out.write("        <meta name=\"description\" content=\"Web implementation of the EzPassApplication\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"wrapper\">\n");
      out.write("            <header>Ez Pass Web Application</header>\n");
      out.write("            <div class = \"flexHorizontal\">\n");
      out.write("\n");
      out.write("                <div>\n");
      out.write("                    <aside>\n");
      out.write("                        <div class = \"flexVertical\">\n");
      out.write("                            <div class = \"links\">Website Directories</div>\n");
      out.write("                            <a href = \"./index.jsp\" class = \"active-link\">Login</a>\n");
      out.write("                            <a href = \"./SignUp.jsp\">Sign Up</a>\n");
      out.write("                            \n");
      out.write("                        </div>\n");
      out.write("                    </aside>\n");
      out.write("                </div>\n");
      out.write("                <div id = \"space\"> </div>\n");
      out.write("                <main> \n");
      out.write("                    <h1 align =\"center\">Sign In</h1>\n");
      out.write("                    <form name=\"SignIn\" action=\"./LoginControl\" method =\"get\"> \n");
      out.write("                        <label for=\"Username\">Username: </label>\n");
      out.write("                        <input type=\"text\" name=\"Username\"><br>\n");
      out.write("                        <label for=\"Password\">Password: </label>\n");
      out.write("                        <input type=\"password\" name=\"Password\"><br>\n");
      out.write("\n");
      out.write("                        <input type=\"button\" value=\"Login\" onClick=\"checkInputs()\">\n");
      out.write("                        <input type=\"reset\" value=\"Reset\">\n");
      out.write("                    </form>\n");
      out.write("                    ");
 if(request.getAttribute("message")!=null){
      out.write("\n");
      out.write("                    <div id=\"message\">");
      out.print(request.getAttribute("message"));
      out.write("</div>    \n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                    \n");
      out.write("                    <div id = \"date\"> </div>\n");
      out.write("\n");
      out.write("                </main>\n");
      out.write("            </div>\n");
      out.write("            <footer><small><em>\n");
      out.write("                        <br>Copyright © 2020 EzPassWebApplication</br>\n");
      out.write("                        <a href = \"mailto:jchanunh@student.fdu.edu\">jchanunh@student.fdu.edu</a>\n");
      out.write("                    </em></small></footer>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <script language=\"JavaScript\">\n");
      out.write("\n");
      out.write("            function checkInputs()\n");
      out.write("            {\n");
      out.write("                var Prompts = \"\";\n");
      out.write("                Username = document.SignIn.Username.value;\n");
      out.write("                Password = document.SignIn.Password.value;\n");
      out.write("\n");
      out.write("                if (Username == \"\" || Password == \"\") {\n");
      out.write("                    if (Username == \"\")\n");
      out.write("                        Prompts += \"Please enter your username!\\n\";\n");
      out.write("                    if (Password == \"\")\n");
      out.write("                        Prompts += \"Please enter your password!\\n\";\n");
      out.write("                    if (Prompts != \"\")\n");
      out.write("                        window.alert(Prompts);\n");
      out.write("                } else {\n");
      out.write("                    document.SignIn.submit();\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        </script>\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("            var d = new Date();\n");
      out.write("            document.getElementById(\"date\").innerHTML = d;\n");
      out.write("        </script>\n");
      out.write("    </body> \n");
      out.write("</html>");
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
