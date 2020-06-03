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

    Transaction trans;

    public PayTollControl(ActionEvent evt, String TagCode, String TollPlaza, int intTollLaneNum, float TollAmt, String CID) {
        Transaction trans = new Transaction(TagCode, TollAmt, TollPlaza, intTollLaneNum, CID); //transaction object
        Customer cus = new Customer(CID); //customer object
        cus.setData();
        float oldBal = cus.getBalance();
        float newBal = oldBal - TollAmt;
        EzTag tag = new EzTag(TagCode, CID);
        if (TagCode.equals("") || TollPlaza.equals("") || intTollLaneNum == 0 || TollAmt == 0) {
            JOptionPane.showMessageDialog(null, "Create transaction failed! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        } else if (tag.checkTag()) { // Prevent user from stealing someone elses tag code
            if (trans.recordTransaction() && cus.charge(newBal)) { //record transaction and charge account. 
                JOptionPane.showMessageDialog(null, "Transaction successful! Your total amount: " + newBal + ". Have a nice trip!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                JComponent component = (JComponent) evt.getSource();
                Window win = SwingUtilities.getWindowAncestor(component);
                win.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Create transaction failed unexpectly!", "Confirmation", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Create transaction failed due to invalid tag code", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }

}
