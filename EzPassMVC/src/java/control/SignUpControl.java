package control;

import model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SignUpControl", urlPatterns = {"/SignUpControl"})
public class SignUpControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Username = request.getParameter("Username");
        String Password = request.getParameter("Password");
        String Password1 = request.getParameter("Password1");
        String Name = request.getParameter("Name");
        Account acct = new Account(Username, Password,Password1, Name);
        if(acct.UsernameTaken()){
            response.sendRedirect("SignUp.jsp?message=Username is taken. Please try another username.");
        }
        else if(acct.signUp()){
            response.sendRedirect("index.jsp?message=Account creation was successful! Please login to your new account!");
        }
        else{
            response.sendRedirect("SignUp.jsp?message=Signup failed unexpectedly");
        }
    }

}
