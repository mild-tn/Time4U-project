package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ResetPassword_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>Reset Password Page</title>\r\n");
      out.write("        <link rel=\"icon\" type=\"image/png\" sizes=\"64x64\" href=\"images/oie_transparent.png\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"include/css/ResetPass.style.css\"/>\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" />\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"include/css/style-page.css\"/>\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.4.2/css/all.css\"/>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        \r\n");
      out.write("        <form action=\"ResetPassword\" method=\"post\">\r\n");
      out.write("            <div id=\"overlay\"></div>\r\n");
      out.write("            <div class=\"resetbox\">\r\n");
      out.write("                <h1>Forgot Password?</h1><br>\r\n");
      out.write("                <input type=\"text\" name=\"emailre\" placeholder=\"Email\"><br>\r\n");
      out.write("                <input type=\"password\" name=\"passre\" placeholder=\"Current Password\"><br>\r\n");
      out.write("                <input type=\"password\" name=\"newpass\" placeholder=\"New Password\"><br>\r\n");
      out.write("                <input type=\"password\" name=\"newpasscf\" placeholder=\"Confirm New Password\"><br>\r\n");
      out.write("                <!--<a href=\"Login\">Back?</a><br>-->\r\n");
      out.write("                <input type=\"submit\">\r\n");
      out.write("                </form>\r\n");
      out.write("            </div>\r\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "include/footer.jsp", out, false);
      out.write("\r\n");
      out.write("    </body>\r\n");
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
