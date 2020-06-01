package control;

import model.Account;
import model.Customer;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "LoginControl", urlPatterns = {"/LoginControl"})
public class LoginControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String User = request.getParameter("Username");
        String PW = request.getParameter("Password");
        HttpSession session = request.getSession();    
        Account acct = new Account(User, PW);
        Customer cus = new Customer();
        RequestDispatcher requestdispatcher;
        if(acct.signIn()&&cus.checkExist(User)){ //set session attributes and redirect to user main page
            cus = new Customer(acct.getCustomerID());
            cus.setData();
            session.setAttribute("Username", User); //for change password
            session.setAttribute("CID", cus.getCustomerID());
            session.setAttribute("Name", cus.getName());
            session.setAttribute("Street", cus.getStreet());
            session.setAttribute("City", cus.getCity());
            session.setAttribute("State", cus.getState());
            session.setAttribute("Zip", cus.getZip());
            session.setAttribute("Phone", cus.getPhone());
            session.setAttribute("Email", cus.getEmail());
            session.setAttribute("Balance", cus.getBalance());
            response.sendRedirect("Main.jsp");
        }
        else if(acct.signIn()){
            session.setAttribute("Username", User);
            session.setAttribute("Name", acct.getName());
            response.sendRedirect("CreateProfile.jsp");
        }
        else{
            request.setAttribute("message", "Invalid Username or Password. Please try again!");
            requestdispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            requestdispatcher.forward(request, response);
        }
        
    }

}
