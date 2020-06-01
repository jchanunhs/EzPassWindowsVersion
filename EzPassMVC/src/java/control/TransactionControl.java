package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TransactionControl", urlPatterns = {"/TransactionControl"})
public class TransactionControl extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String before = request.getParameter("before");
        String after = request.getParameter("after");
        response.sendRedirect("ViewDateTransactions.jsp?before=" + before + "&after=" + after);
    }
}
