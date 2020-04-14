package ezpassapplication.control;

import ezpassapplication.model.Customer;
import javax.swing.JOptionPane;

public class CreateProfileControl {

    public CreateProfileControl(String Name, String Street, String City, String State, String Zip, String Phone, String Email, float Balance, String UName) {
        float Bal = Float.parseFloat(String.valueOf(Balance));
        Customer cus = new Customer(Name, Street, City, State, Zip, Phone, Email, Bal, UName); //declare customer object with information
        if(Name.equals("") || Street.equals("") || City.equals("") || State.equals("") || Zip.equals("") || Phone.equals("") || Email.equals("")){
            JOptionPane.showMessageDialog(null, "Create profile failed! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
        else if (cus.createProfile()) { //create the profile 
            JOptionPane.showMessageDialog(null, "Create profile is successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Create profile failed!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
