package ezpassapplication.control;

import ezpassapplication.model.Account;
import javax.swing.JOptionPane;

public class ChangePasswordControl {

    public ChangePasswordControl(String UName, String CID, String OldPass, String NewPass) {
        Account acct = new Account(UName, OldPass); //delcare account object with old password
        //if account is valid, then change password
        if (acct.changePassword(NewPass)) {
            JOptionPane.showMessageDialog(null, "Change password is successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Change password failed! Old Password is incorrect!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
