package ezpassapplication;

import javax.swing.JOptionPane;

public class PayTollControl {

    Transaction trans;

    public PayTollControl(String TagCode, String TollPlaza,int intTollLaneNum, float TollAmt, String Date, String Time, String CID) {
        Transaction trans = new Transaction(TagCode, Date, Time, TollAmt, TollPlaza, intTollLaneNum, CID); //transaction object
        Customer cus = new Customer(); //customer object
        cus.setCID(CID);//set the customer id so we can charge the account
        cus.setData();
        float oldBal = cus.getBalance();
        float newBal = oldBal - TollAmt;
        EzTag tag = new EzTag(TagCode, CID);
        if(TagCode.equals("") || TollPlaza.equals("") || intTollLaneNum == 0 || TollAmt == 0 || Date.equals("") || Time.equals("")){
            
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
