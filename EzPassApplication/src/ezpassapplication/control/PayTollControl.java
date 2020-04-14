package ezpassapplication.control;

import ezpassapplication.model.Customer;
import ezpassapplication.model.EzTag;
import ezpassapplication.model.Transaction;
import javax.swing.JOptionPane;

public class PayTollControl {

    Transaction trans;

    public PayTollControl(String TagCode, String TollPlaza,int intTollLaneNum, float TollAmt, String CID) {
        Transaction trans = new Transaction(TagCode, TollAmt, TollPlaza, intTollLaneNum, CID); //transaction object
        Customer cus = new Customer(CID); //customer object
        cus.setData();
        float oldBal = cus.getBalance();
        float newBal = oldBal - TollAmt;
        EzTag tag = new EzTag(TagCode, CID);
        if(TagCode.equals("") || TollPlaza.equals("") || intTollLaneNum == 0 || TollAmt == 0 ){
            
        }
        else if (tag.checkTag()) { // Prevent user from stealing someone elses tag code
            if(trans.recordTransaction() && cus.charge(newBal)){ //record transaction and charge account. 
                 JOptionPane.showMessageDialog(null, "Transaction successful! Your total amount: " + newBal + ". Have a nice trip!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                 System.exit(0); //Close program when user pays toll because they're driving. 
            }
        } else {
            JOptionPane.showMessageDialog(null, "Create transaction failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
