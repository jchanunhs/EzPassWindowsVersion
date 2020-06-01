package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

@WebServlet(name = "ChangePasswordControl", urlPatterns = {"/ChangePasswordControl"})
public class ChangePasswordControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String Username = (String) session.getAttribute("Username");
        String old = request.getParameter("Old");
        String NewPass = request.getParameter("New");
        Account acct = new Account(Username, old);
        if (acct.changePassword(NewPass)) { //change password
            session.invalidate();
            response.sendRedirect("index.jsp?message=Your password was changed successfully. Please relog with your new password.");
            
        }
        else{
            response.sendRedirect("ChangePassword.jsp?message=Change password failed! Your old password is invalid. Please try again.");
        }
    }

}
