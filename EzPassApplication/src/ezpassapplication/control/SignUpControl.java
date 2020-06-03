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
        if (UName.equals("") || PsWord.equals("") || PsWord1.equals("") || Name.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        } else if (PsWord.equals(PsWord1) && Acct.signUp()) { //check if password match and attempt to sign up
            JOptionPane.showMessageDialog(null, "Account has been created!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            LoginBO login = new LoginBO(); //display login screen and close sign up
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        } else if (Acct.UsernameTaken()) { // check if username is taken
            JOptionPane.showMessageDialog(null, "Username is taken!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        } else if (!PsWord.equals(PsWord1)) { //check for invalid password matchup
            JOptionPane.showMessageDialog(null, "Account creation failed due to unmatched passwords", "Confirmation", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Account creation failed unexpectly!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
