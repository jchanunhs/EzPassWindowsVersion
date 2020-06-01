package control;

import model.Customer;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Jason
 */
@WebServlet(name = "CreateProfileControl", urlPatterns = {"/CreateProfileControl"})
public class CreateProfileControl extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String UName = (String) session.getAttribute("Username");
        String Name = request.getParameter("Name");
        String Street = request.getParameter("Street");
        String City = request.getParameter("City");
        String State = request.getParameter("State");
        String Zip = request.getParameter("Zip");
        String Phone = request.getParameter("Phone");
        String Email = request.getParameter("Email");
        String Balance = request.getParameter("Balance");
        float bal = Float.parseFloat(Balance);
        Customer cus = new Customer(Name, Street, City, State, Zip, Phone, Email, bal, UName);
        if (cus.createProfile()) { //create the profile 
            response.sendRedirect("index.jsp?message=Created profile successfully! Please relog to your account!");
        } else {
            response.sendRedirect("index.jsp?message=Created profile failed unexpectly!");
        }
        
    }

  

}
