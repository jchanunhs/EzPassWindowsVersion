package ezpassapplication.control;

import ezpassapplication.model.EzTag;
import ezpassapplication.model.Vehicle;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class AddVehicleControl {

    public AddVehicleControl(ActionEvent evt, String LicensePlate, String Make, String Model, String Year, String Color, String TagCode, String CID) {
        EzTag ez = new EzTag(TagCode, CID);
        Vehicle vehicle = new Vehicle(LicensePlate, Make, Model, Year, Color, TagCode, CID);
        if (ez.checkTag()) { //check if tag belongs to user
            if (vehicle.addVehicle()) { //attempt to add vehicle
                JOptionPane.showMessageDialog(null, "Vehicle was added successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error: Add vehicle failed! Vehicle already exist in our database!", "Confirmation", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: Add vehicle failed! Tag code is invalid!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
