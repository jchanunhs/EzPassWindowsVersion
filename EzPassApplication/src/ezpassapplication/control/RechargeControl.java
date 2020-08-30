package ezpassapplication.control;

import ezpassapplication.dao.CreditCardDAO;
import ezpassapplication.dao.CustomerDAO;
import ezpassapplication.service.CreditCardService;
import ezpassapplication.service.CustomerService;
import ezpassapplication.model.CreditCard;
import ezpassapplication.model.Customer;
import ezpassapplication.view.MainWindowsBO;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class RechargeControl {

    public RechargeControl(ActionEvent evt, String CID, String User, String CNumber, String NM, String EXPDate, String CVV, float CreditAMT) {
        //get customer current balance
        CustomerDAO customerdao = new CustomerService();
        Customer customer = customerdao.getCustomerInformation(CID);
        float oldBal = customer.getBalance();
        float newBal = oldBal + CreditAMT;
        
        //add transaction
        CreditCardDAO creditcarddao = new CreditCardService();
        CreditCard creditcard = new CreditCard();
        creditcard.setCardNumber(CNumber);
        creditcard.setName(NM);
        creditcard.setExpirationDate(EXPDate);
        creditcard.setCVV(CVV);
        creditcard.setCustomerID(CID);
        creditcard.setCreditAmount(CreditAMT);

        if (customerdao.updateBalance(customer, newBal)) {
            String transactionresult = creditcarddao.addCreditCardTransaction(creditcard); //if record transaction successful, return transaction id. else return null
            JOptionPane.showMessageDialog(null, "Recharge successful! Your Transaction ID is " + transactionresult + " and your new balance is: " + newBal, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            MainWindowsBO main = new MainWindowsBO(CID, User); //redirect to main windows and close recharge window
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        } else { //add credit card will fail if generated credit id is taken
            JOptionPane.showMessageDialog(null, "Error: Recharge failed unexpectly! If this occurs multiple times, please contact help desk.", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }

    }
}
