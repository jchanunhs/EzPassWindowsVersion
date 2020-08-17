package ezpassapplication.control;

import ezpassapplication.dao.AccountDAO;
import ezpassapplication.entity.Account;
import ezpassapplication.view.LoginBO;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class SignUpControl {

    public SignUpControl(ActionEvent evt, String UName, String PsWord) {
        AccountDAO accountdao = new AccountDAO();
        Account account = new Account();
        account.setUsername(UName);
        account.setPassword(PsWord);


        if (accountdao.signUp(account)) { 
            JOptionPane.showMessageDialog(null, "Account creation was successful! Please login to your new account!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            LoginBO login = new LoginBO(); //display login screen and close sign up
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error: Username is taken. Please try another username.", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
