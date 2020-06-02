package ezpassapplication.control;

import ezpassapplication.model.CreditCard;
import ezpassapplication.model.Customer;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class RechargeControl {

    public RechargeControl(ActionEvent evt, String CID, String CNumber, String NM, String EXPDate, String CVV, float AddBal) {
        Customer cus = new Customer(CID);
        CreditCard card = new CreditCard(CNumber, NM, EXPDate, CVV, CID, AddBal);
        cus.setData();
        float oldBal = cus.getBalance();
        float newBal = oldBal + AddBal; //add the balance together
        if (CNumber.equals("") || NM.equals("") || EXPDate.equals("") || CVV.equals("")) {
            JOptionPane.showMessageDialog(null, "Recharge failed! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        } else if (card.addCreditCard()) { //add card to table, then we charge account if successful
            if (cus.recharge(newBal)) {
                JOptionPane.showMessageDialog(null, "Recharge successful! New balance is: " + newBal, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                JComponent component = (JComponent) evt.getSource();
                Window win = SwingUtilities.getWindowAncestor(component);
                win.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Recharge failed! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }

    }
}
