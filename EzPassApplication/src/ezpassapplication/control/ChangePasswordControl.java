package ezpassapplication.control;

import ezpassapplication.dao.AccountDAO;
import ezpassapplication.service.AccountService;
import ezpassapplication.model.Account;
import ezpassapplication.view.LoginBO;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ChangePasswordControl {

    public ChangePasswordControl(ActionEvent evt, String UName, String OldPass, String NewPass) {
        AccountDAO accountdao = new AccountService();
        Account account = new Account();
        account.setUsername(UName);
        account.setPassword(OldPass);
        if (accountdao.updatePassword(account, NewPass)) { //attempt to change password
            JOptionPane.showMessageDialog(null, "Your password was changed successfully. Please relog with your new password.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            LoginBO login = new LoginBO(); //open login windows and close change password window
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error: Change password failed! Your old password is invalid. Please try again.", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }

    }
}
