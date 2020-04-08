package ezpassapplication;

import javax.swing.JOptionPane;

public class ChangePasswordControl {

    public ChangePasswordControl(String UName, String CID, String OldPass, String NewPass) {
        Account acct = new Account(CID); //delcare account object

        //Check if the user/pass is correct
        if (acct.checkValid(UName, OldPass)) {
            //if account is valid, then change password
            if (acct.changePassword(NewPass)) {
                JOptionPane.showMessageDialog(null, "Change password is successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Change password failed! Old Password is incorrect!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
