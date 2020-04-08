package ezpassapplication;

import javax.swing.JOptionPane;

public class CreateProfileControl {

    public CreateProfileControl(String Name, String Street, String City, String State, String Zip, String Phone, String Email, String Balance, String UName) {
        float Bal = Float.parseFloat(String.valueOf(Balance)); //make balance float because balance is float value in db
        Customer cus = new Customer(Name, Street, City, State, Zip, Phone, Email, Bal, UName); //declare customer object with information
        if (cus.createProfile()) { //create the profile 
            JOptionPane.showMessageDialog(null, "Create profile is successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Create profile failed!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
