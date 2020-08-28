package ezpassapplication.control;

import ezpassapplication.dao.AccountDAO;
import ezpassapplication.model.Account;
import ezpassapplication.view.CreateProfileBO;
import ezpassapplication.view.MainWindowsBO;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class LoginControl {

    public LoginControl(ActionEvent evt, String Username, String Password) {
        AccountDAO accountdao = new AccountDAO();
        Account account = accountdao.getAccountInformation(Username, Password); //get account information from dao.

        //if customer profile already made and user sign in successfully, pass to main windows
        if (account.getUsername()!= null && account.getPassword() != null && account.getCustomerID() != null) {
            MainWindowsBO main = new MainWindowsBO(account.getCustomerID(), Username);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
            //if user log in successfully but no customer profile, make them create one
        } else if (account.getUsername()!= null && account.getPassword() != null && account.getCustomerID() == null) {
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
            CreateProfileBO createprof = new CreateProfileBO(Username); //pass user name and the name of person who owns account
        } else {
            JOptionPane.showMessageDialog(null, "Error: Invalid Username and/or Password. Please try again.", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
