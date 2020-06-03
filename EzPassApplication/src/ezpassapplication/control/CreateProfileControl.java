package ezpassapplication.control;

import ezpassapplication.model.Customer;
import ezpassapplication.view.LoginBO;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class CreateProfileControl {

    public CreateProfileControl(ActionEvent evt, String Name, String Street, String City, String State, String Zip, String Phone, String Email, float Balance, String UName) {
        Customer cus = new Customer(Name, Street, City, State, Zip, Phone, Email, Balance, UName); 
        if (Name.equals("") || Street.equals("") || City.equals("") || State.equals("") || Zip.equals("") || Phone.equals("") || Email.equals("")) {
            JOptionPane.showMessageDialog(null, "Create profile failed! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        } else if (cus.createProfile()) { //attempt to create profile 
            JOptionPane.showMessageDialog(null, "Create profile is successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            LoginBO login = new LoginBO(); //open login window and close create profile window
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Create profile failed unexpectly!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
