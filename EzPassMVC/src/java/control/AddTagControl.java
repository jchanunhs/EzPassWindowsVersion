package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.EzTag;

@WebServlet(name = "AddTagControl", urlPatterns = {"/AddTagControl"})
public class AddTagControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String CID = (String) session.getAttribute("CID");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String IssueDate = formatter.format(date);
        String TC = request.getParameter("TagCode");
        String TT = request.getParameter("TagType");
        EzTag ez = new EzTag(TC, TT, IssueDate, CID);
        if (ez.addTag()) {
            response.sendRedirect("AddTag.jsp?message=Ez Tag was added successfully!");
          
        } else {
            response.sendRedirect("AddTag.jsp?message=The Ez Tag that you entered is invalid. Please try again.");
        }

    }
}
