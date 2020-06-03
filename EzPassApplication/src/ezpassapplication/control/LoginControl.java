package ezpassapplication.control;

import ezpassapplication.model.Account;
import ezpassapplication.model.Customer;
import ezpassapplication.view.CreateProfileBO;
import ezpassapplication.view.MainWindowsBO;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class LoginControl {

    public LoginControl(ActionEvent evt, String Username, String Password) {
        Account Acct = new Account(Username, Password); 
        Customer Cust = new Customer(Acct.getCustomerID()); //customer with username
        Cust.setData(); //set data based on username
        String CID = Cust.getCustomerID(); // get the customer id 
        if (Username.equals("") || Password.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        } //if customer profile already made and user sign in successfully, pass to main windows
        else if (Cust.checkExist(Username) && Acct.signIn()) {
            MainWindowsBO main = new MainWindowsBO(CID,Username);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
            //if user log in successfully but no customer profile, make them create one
        } else if (Acct.signIn()) {
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
            CreateProfileBO createprof = new CreateProfileBO(Username, Acct.getName()); //pass user name and the name of person who owns account
        } else {
            JOptionPane.showMessageDialog(null, "Login failed because of invalid username or password.", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
