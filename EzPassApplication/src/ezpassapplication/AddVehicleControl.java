package ezpassapplication;

import javax.swing.JOptionPane;

public class AddVehicleControl {

    public AddVehicleControl(String LicensePlate, String Make, String Model, String Year, String Color, String TagCode, String CID) {
        EzTag ez = new EzTag(TagCode, CID);
        Vehicle vehicle = new Vehicle(LicensePlate, Make, Model, Year, Color, TagCode, CID); //declare vehicle object
        if (LicensePlate.equals("") || Make.equals("") || Model.equals("") || Year.equals("") || Color.equals("") || TagCode.equals("")) {
            JOptionPane.showMessageDialog(null, "Add vehicle failed! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        } else if (ez.checkTag()) { //add vehicle to db and check if tag code belongs to this customer
            vehicle.addVehicle();
            JOptionPane.showMessageDialog(null, "Add vehicle is successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(null, "Add vehicle failed!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
