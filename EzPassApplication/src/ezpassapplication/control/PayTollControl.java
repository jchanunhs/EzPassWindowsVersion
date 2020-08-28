package ezpassapplication.control;

import ezpassapplication.dao.CustomerDAO;
import ezpassapplication.dao.EzTagDAO;
import ezpassapplication.dao.TransactionDAO;
import ezpassapplication.dao.VehicleDAO;
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

    public PayTollControl(ActionEvent evt, String LicensePlateNumber, String TagCode, String TollPlaza, int TollLaneNumber, float TollAmt, String CID) {
        //process customer balance
        CustomerDAO customerdao = new CustomerDAO();
        Customer customer = customerdao.getCustomerInformation(CID);
        float oldBal = customer.getBalance(); //get current balance from customer
        float newBal = oldBal - TollAmt;

        //check if tag input is valid
        EzTagDAO eztagdao = new EzTagDAO();
        EzTag eztag = new EzTag();
        eztag.setCustomerID(CID);
        eztag.setTagCode(TagCode);

        //check if vehicle input is valid
        VehicleDAO vehicledao = new VehicleDAO();
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlateNumber(LicensePlateNumber);
        vehicle.setTagCode(TagCode);
        vehicle.setCustomerID(CID);

        //record transaction
        TransactionDAO transactiondao = new TransactionDAO();
        Transaction transaction = new Transaction();
        transaction.setTagCode(TagCode);
        transaction.setTollAmount(TollAmt);
        transaction.setTollPlaza(TollPlaza);
        transaction.setTollLaneNumber(TollLaneNumber);
        transaction.setCustomerID(CID);

        if (eztagdao.checkTag(eztag)) { //check if tag code belongs to customer
            if (vehicledao.checkVehicle(vehicle)) { // check if vehicle belongs to tag code
                if (customerdao.updateBalance(customer, newBal)) {
                    String transactionresult = transactiondao.recordTransaction(transaction); //if record transaction successful, return transaction id. else return null
                    JOptionPane.showMessageDialog(null, "Pay toll was successful! Your Transaction ID is " + transactionresult + " and your new balance is: " + newBal + ". Have a nice trip!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    JComponent component = (JComponent) evt.getSource();//close window when customer is charged successfully
                    Window win = SwingUtilities.getWindowAncestor(component);
                    win.dispose();
                } else { //update balance failed
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
