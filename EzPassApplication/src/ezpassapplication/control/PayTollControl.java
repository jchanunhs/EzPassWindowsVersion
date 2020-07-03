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
        cus.setData();
        float oldBal = cus.getBalance(); //get current balance from customer
        float newBal = oldBal - TollAmt;
        EzTag tag = new EzTag(TagCode, CID);
        if (tag.checkTag()) { //check if tag belongs to customer
            if (trans.recordTransaction() && cus.charge(newBal)) { //record transaction and charge account. 
                JOptionPane.showMessageDialog(null, "Pay toll was successful! Your new balance is: " + newBal + ". Have a nice trip!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                JComponent component = (JComponent) evt.getSource();//close window when customer is charged successfully
                Window win = SwingUtilities.getWindowAncestor(component);
                win.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to process payments at this time. If this occurs multiple times please contact help desk.", "Confirmation", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: Pay toll failed because Tag Code was invalid!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }

}
