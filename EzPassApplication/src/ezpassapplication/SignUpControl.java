package ezpassapplication;

import javax.swing.JOptionPane;

public class SignUpControl {

    private Account Acct;

    public SignUpControl() {

    }

    SignUpControl(String UName, String PsWord, String PsWord1, String Name) {
        Acct = new Account(UName, PsWord, PsWord1, Name);
        if (PsWord.equals(PsWord1) && Acct.signUp()) { //if passwords match 
            JOptionPane.showMessageDialog(null, "Account has been created!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        } else if (Acct.UsernameTaken()) {
            JOptionPane.showMessageDialog(null, "Username is taken!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Account creation failed due tounmatched passwords", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
