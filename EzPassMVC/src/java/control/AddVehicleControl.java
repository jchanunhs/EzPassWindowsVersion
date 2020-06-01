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
import model.Vehicle;

@WebServlet(name = "AddVehicleControl", urlPatterns = {"/AddVehicleControl"})
public class AddVehicleControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String CID = (String) session.getAttribute("CID");
        String license = request.getParameter("LicensePlateNumber");
        String make = request.getParameter("Make");
        String model = request.getParameter("Model");
        String year = request.getParameter("Year");
        String color = request.getParameter("Color");
        String tagcode = request.getParameter("TagCode");
        Vehicle vehicle = new Vehicle(license, make, model, year, color, tagcode, CID);
        EzTag tag = new EzTag(tagcode, CID);
        if (tag.checkTag()) { //check if CID owns this tag
            if (vehicle.addVehicle()) { //attempt to add vehicle to db
                response.sendRedirect("AddVehicle.jsp?message=Vehicle was added successfully!");
            }
            else{ //vehicle fails to add to db
                response.sendRedirect("AddVehicle.jsp?message=Add vehicle failed! Vehicle already exist in our database!");
            }
        }
        else{ //invalid tagcode
            response.sendRedirect("AddVehicle.jsp?message=Add vehicle failed! Tag code is invalid!");
        }
    }

}
