package ezpassapplication.control;

import ezpassapplication.dao.CustomerDAO;
import ezpassapplication.entity.Customer;
import ezpassapplication.view.LoginBO;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class CreateProfileControl {

    public CreateProfileControl(ActionEvent evt, String Name, String Street, String City, String State, String Zip, String Phone, String Email, String UName) {
        CustomerDAO customerdao = new CustomerDAO();
        Customer customer = new Customer();
        customer.setName(Name);
        customer.setStreet(Street);
        customer.setCity(City);
        customer.setState(State);
        customer.setZip(Zip);
        customer.setPhone(Phone);
        customer.setEmail(Email);
        if (customerdao.createProfile(customer, UName)) { //attempt to create profile 
            JOptionPane.showMessageDialog(null, "Create profile is successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            LoginBO login = new LoginBO(); //open login window and close create profile window
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        } else { //create profile fails if the generated customer id is already taken.
            JOptionPane.showMessageDialog(null, "Error: Created profile failed unexpectly! If this occurs multiple times please contact help desk.", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
