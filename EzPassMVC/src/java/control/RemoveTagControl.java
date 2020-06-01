package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.EzTag;

@WebServlet(name = "RemoveTagControl", urlPatterns = {"/RemoveTagControl"})
public class RemoveTagControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String CID = (String) session.getAttribute("CID");

        String TC = request.getParameter("TagCode");
        EzTag ez = new EzTag(TC, CID);
        if (ez.checkTag() && ez.removeTag()) {
            response.sendRedirect("RemoveTag.jsp?message=EzTag was removed successfully!");
        }
        else{
            response.sendRedirect("RemoveTag.jsp?message=The Ez Tag that you entered is invalid. Please try again.");
        }

    }
}
