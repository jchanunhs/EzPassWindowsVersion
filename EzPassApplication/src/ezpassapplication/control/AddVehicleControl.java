package ezpassapplication.control;

import ezpassapplication.dao.EzTagDAO;
import ezpassapplication.dao.VehicleDAO;
import ezpassapplication.model.EzTag;
import ezpassapplication.model.Vehicle;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class AddVehicleControl {

    public AddVehicleControl(ActionEvent evt, String LicensePlate, String Make, String Model, String Year, String Color, String TagCode, String CID) {
        //check tag
        EzTagDAO eztagdao = new EzTagDAO();
        EzTag eztag = new EzTag();
        eztag.setCustomerID(CID);
        eztag.setTagCode(TagCode);
        
        //add vehicle
        VehicleDAO vehicledao = new VehicleDAO();
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlateNumber(LicensePlate);
        vehicle.setMake(Make);
        vehicle.setModel(Model);
        vehicle.setYear(Year);
        vehicle.setColor(Color);
        vehicle.setTagCode(TagCode);
        vehicle.setCustomerID(CID);
        if (eztagdao.checkTag(eztag)) { //check if eztag belongs to user
            if (vehicledao.addVehicle(vehicle)) { //attempt to add vehicle
                JOptionPane.showMessageDialog(null, "Vehicle was added successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error: Add vehicle failed! Vehicle already exist in our database!", "Confirmation", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: Add vehicle failed! Tag code is invalid!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
