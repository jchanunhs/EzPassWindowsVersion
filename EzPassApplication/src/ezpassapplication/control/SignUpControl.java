package ezpassapplication.control;

import ezpassapplication.model.Account;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class SignUpControl {

    private Account Acct;

    public SignUpControl(ActionEvent evt, String UName, String PsWord, String PsWord1, String Name) {
        Acct = new Account(UName, PsWord, PsWord1, Name);
        if (UName.equals("") || PsWord.equals("") || PsWord1.equals("") || Name.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        } else if (PsWord.equals(PsWord1) && Acct.signUp()) { //if passwords match 
            JOptionPane.showMessageDialog(null, "Account has been created!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        } else if (Acct.UsernameTaken()) {
            JOptionPane.showMessageDialog(null, "Username is taken!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Account creation failed due to unmatched passwords", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
