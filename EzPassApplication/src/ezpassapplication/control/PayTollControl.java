package ezpassapplication.control;

import ezpassapplication.model.Customer;
import ezpassapplication.model.EzTag;
import ezpassapplication.model.Transaction;
import ezpassapplication.model.Vehicle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class PayTollControl {

    public PayTollControl(ActionEvent evt, String LicensePlateNumber, String TagCode, String TollPlaza, int intTollLaneNum, float TollAmt, String CID) {
        Transaction trans = new Transaction(TagCode, TollAmt, TollPlaza, intTollLaneNum, CID); //create transaction
        Vehicle vehicle = new Vehicle(LicensePlateNumber, TagCode, CID); //check if vehicle matches tag code
        Customer cus = new Customer(CID);
        float oldBal = cus.getBalance(); //get current balance from customer
        float newBal = oldBal - TollAmt;
        EzTag tag = new EzTag(TagCode, CID);

        if (tag.checkTag()) { //check if tag code belongs to customer
            if (vehicle.checkVehicle()) { // check if vehicle belongs to tag code
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
            } else { //vehicle and tag code dont match
                JOptionPane.showMessageDialog(null, "Error: The Tag Code is registered to your account, but this vehicle is not associated with this tag code.", "Confirmation", JOptionPane.ERROR_MESSAGE);
            }

        } else {//invalid tag
            JOptionPane.showMessageDialog(null, "Error: Pay toll failed because Tag Code was invalid!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }

}
