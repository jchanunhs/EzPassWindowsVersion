package ezpassapplication.control;

import ezpassapplication.model.Account;
import javax.swing.JOptionPane;

public class ChangePasswordControl {

    public ChangePasswordControl(String UName, String OldPass, String NewPass, String NewPass1) {
        Account acct = new Account(UName, OldPass); //delcare account object with old password   
        if (OldPass.equals("") || NewPass.equals("") || NewPass1.equals("")) { // check if fields are empty
            JOptionPane.showMessageDialog(null, "One or more fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!NewPass.equals(NewPass1)) { //check if password match
            JOptionPane.showMessageDialog(null, "Password doesnt match!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (acct.changePassword(NewPass)) {
            JOptionPane.showMessageDialog(null, "Change password is successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Change password failed! Old Password is incorrect!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }

    }
}
