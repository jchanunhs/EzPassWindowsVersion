package ezpassapplication;

import javax.swing.JOptionPane;

class RechargeControl {

    public RechargeControl(String CID, String CNumber, String NM, String EXPDate, String CVV, String AddBal, String CurrentBal) {
        Customer cus = new Customer();
        CreditCard card = new CreditCard(CNumber, NM, EXPDate, CVV, CID);
        cus.setCID(CID);//used to get current balance
        cus.setData();
        float currentBal = cus.getBalance();
        float addBalance = Float.parseFloat(AddBal);
        float newBal = currentBal + addBalance; //add the balance together
        if (CNumber.equals("") && NM.equals("") && EXPDate.equals("") && CVV.equals("") && CID.equals("")) {
            JOptionPane.showMessageDialog(null, "Recharge failed! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        } //else if valeus are not null, recharge account with new balance and add credit card to db 
        else if (cus.recharge(newBal) && card.addCreditCard()) {
            
            JOptionPane.showMessageDialog(null, "Recharge successful! New balance is: " + newBal, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
}
