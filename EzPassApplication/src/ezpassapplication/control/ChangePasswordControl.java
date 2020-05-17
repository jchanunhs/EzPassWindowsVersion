package ezpassapplication.control;

import ezpassapplication.model.Account;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ChangePasswordControl {

    public ChangePasswordControl(ActionEvent evt, String UName, String OldPass, String NewPass, String NewPass1) {
        Account acct = new Account(UName, OldPass); //delcare account object with old password   
        if (OldPass.equals("") || NewPass.equals("") || NewPass1.equals("")) { // check if fields are empty
            JOptionPane.showMessageDialog(null, "One or more fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!NewPass.equals(NewPass1)) { //check if password match
            JOptionPane.showMessageDialog(null, "Password doesnt match!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (acct.changePassword(NewPass)) {
            JOptionPane.showMessageDialog(null, "Change password is successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Change password failed! Old Password is incorrect!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }

    }
}
