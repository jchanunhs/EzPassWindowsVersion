package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Vehicle;

@WebServlet(name = "RemoveVehicleControl", urlPatterns = {"/RemoveVehicleControl"})
public class RemoveVehicleControl extends HttpServlet {

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(); 
        String CID = (String) session.getAttribute("CID");
        String license = request.getParameter("LicensePlateNumber");
        Vehicle vehicle = new Vehicle(CID, license);
        if (vehicle.removeVehicle()) { //attempt to remove vehicle
            response.sendRedirect("RemoveVehicle.jsp?message=Vehicle removed successfully!");
        }
        else{
            response.sendRedirect("RemoveVehicle.jsp?message=Vehicle removed failed because the license plate entered is invalid!");
        }
    }

  
}
