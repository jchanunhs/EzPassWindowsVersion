package ezpassapplication.control;

import ezpassapplication.model.EzTag;
import ezpassapplication.model.Vehicle;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class AddVehicleControl {

    public AddVehicleControl(ActionEvent evt, String LicensePlate, String Make, String Model, String Year, String Color, String TagCode, String CID) {
        EzTag ez = new EzTag(TagCode, CID);
        Vehicle vehicle = new Vehicle(LicensePlate, Make, Model, Year, Color, TagCode, CID); //declare vehicle object
        if (LicensePlate.equals("") || Make.equals("") || Model.equals("") || Year.equals("") || Color.equals("") || TagCode.equals("")) {
            JOptionPane.showMessageDialog(null, "Add vehicle failed! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        } else if (ez.checkTag()) { //add vehicle to db and check if tag code belongs to this customer
            if (vehicle.addVehicle()) {
                JOptionPane.showMessageDialog(null, "Add vehicle is successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Add vehicle failed! Vehicle is already in the database", "Confirmation", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Add vehicle failed! Tag code is invalid", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
