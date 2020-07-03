package ezpassapplication.control;

import ezpassapplication.model.Account;
import ezpassapplication.view.LoginBO;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class SignUpControl {

    public SignUpControl(ActionEvent evt, String UName, String PsWord, String PsWord1, String Name) {
        Account Acct = new Account(UName, PsWord, PsWord1, Name);
        if (Acct.signUp()) { //check if password match and attempt to sign up
            JOptionPane.showMessageDialog(null, "Account creation was successful! Please login to your new account!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            LoginBO login = new LoginBO(); //display login screen and close sign up
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        } else if (Acct.UsernameTaken()) { // check if username is taken
            JOptionPane.showMessageDialog(null, "Error: Username is taken. Please try another username.", "Confirmation", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error: Signup failed unexpectedly. If this occurs multiple times please contact help desk.", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
