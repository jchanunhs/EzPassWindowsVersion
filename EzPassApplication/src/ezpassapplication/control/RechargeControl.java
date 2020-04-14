package ezpassapplication.control;

import ezpassapplication.model.CreditCard;
import ezpassapplication.model.Customer;
import javax.swing.JOptionPane;

public class RechargeControl {

    public RechargeControl(String CID, String CNumber, String NM, String EXPDate, String CVV, float AddBal) {
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
            }
        } else {
            JOptionPane.showMessageDialog(null, "Recharge failed! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }

    }
}
