package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.EzTag;
import model.Transaction;

@WebServlet(name = "PayTollControl", urlPatterns = {"/PayTollControl"})
public class PayTollControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String CID = (String) session.getAttribute("CID");
        String TC = request.getParameter("TagCode");
        String TP = request.getParameter("TollPlaza");
        String TL = request.getParameter("TollLane");
        String TA = request.getParameter("TollAmount");

        int TL_INT = Integer.parseInt(TL); //Toll lane is int in database
        float TA_FLT = Float.parseFloat(TA); // Toll amount is float
        Transaction tran = new Transaction(TC, TA_FLT, TP, TL_INT, CID);
        EzTag tag = new EzTag(TC, CID); //check if tag code belongs to customer
        Customer cus = new Customer(CID);
        cus.setData();
        float oldBal = cus.getBalance();
        float newBal = oldBal - TA_FLT; //subtract old balance with charge toll amount
        if (tag.checkTag()) {
            
            if (tran.recordTransaction() && cus.charge(newBal)) {
                response.sendRedirect("PayTolls.jsp?message=Pay toll was successful! Your new balance is: " + newBal);
                session.setAttribute("Balance", newBal);
            }
            else{ //transaction failed
                response.sendRedirect("PayTolls.jsp?message=Unable to process payments at this time");
            }

        }
        else{//invalid tag
            response.sendRedirect("PayTolls.jsp?message=Pay toll failed because Tag Code was invalid!");
        }

    }
}
