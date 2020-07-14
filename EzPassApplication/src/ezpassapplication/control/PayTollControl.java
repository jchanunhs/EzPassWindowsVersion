package ezpassapplication.control;

import ezpassapplication.model.Customer;
import ezpassapplication.model.EzTag;
import ezpassapplication.model.Transaction;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class PayTollControl {

    public PayTollControl(ActionEvent evt, String TagCode, String TollPlaza, int intTollLaneNum, float TollAmt, String CID) {
        Transaction trans = new Transaction(TagCode, TollAmt, TollPlaza, intTollLaneNum, CID);
        Customer cus = new Customer(CID);
        float oldBal = cus.getBalance(); //get current balance from customer
        float newBal = oldBal - TollAmt;
        EzTag tag = new EzTag(TagCode, CID);

        if (tag.checkTag()) {
            if (trans.recordTransaction()) { //record transaction first
                if (cus.updateBalance(newBal)) {
                    JOptionPane.showMessageDialog(null, "Pay toll was successful! Your Transaction ID is " + trans.getTransactionID() + " and your new balance is: " + newBal + ". Have a nice trip!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    JComponent component = (JComponent) evt.getSource();//close window when customer is charged successfully
                    Window win = SwingUtilities.getWindowAncestor(component);
                    win.dispose();
                }
            } else { //record transaction will fail if generated transaction id is taken
                JOptionPane.showMessageDialog(null, "Error: Unable to process payments at this time. If this occurs multiple times please contact help desk.", "Confirmation", JOptionPane.ERROR_MESSAGE);
            }
        } else {//invalid tag
            JOptionPane.showMessageDialog(null, "Error: Pay toll failed because Tag Code was invalid!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }

}
