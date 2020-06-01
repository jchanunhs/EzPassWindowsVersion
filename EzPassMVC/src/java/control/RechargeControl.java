package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CreditCard;
import model.Customer;


@WebServlet(name = "RechargeControl", urlPatterns = {"/RechargeControl"})
public class RechargeControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String CID = (String) session.getAttribute("CID");
        String CN = request.getParameter("CardNumber");
        String NM = request.getParameter("Name");
        String EXPDate = request.getParameter("EXP");
        String CVV = request.getParameter("CVV");
        String Credit = request.getParameter("Credit");
        float Credit_FLT = Float.parseFloat(Credit); 
        CreditCard card = new CreditCard(CN, NM, EXPDate, CVV, CID, Credit_FLT); // add payment information
        Customer cus = new Customer(CID); // get current balance
        cus.setData();//data set based on customer id
        float oldBal = cus.getBalance();
        float newBal = oldBal + Credit_FLT; //add the balance together
        if (cus.recharge(newBal) && card.addCreditCard()) {
            response.sendRedirect("Recharge.jsp?message=Account recharge was successful! Your new balance: " + newBal);
            session.setAttribute("Balance", newBal);
        }
        else{
            response.sendRedirect("Recharge.jsp?message=Recharge failed unexpectly! Please try again later.");
        }

    }
}
