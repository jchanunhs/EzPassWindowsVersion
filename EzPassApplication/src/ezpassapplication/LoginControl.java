package ezpassapplication;

import javax.swing.*;

public class LoginControl {

    public LoginControl(String Username, String Password) {
        Account Acct = new Account(Username, Password); //Account object with user name and password
        Customer Cust = new Customer(Username); //customer with username
        Cust.setData(Username); //set data based on username
        String CID = Cust.getCustomerID(); // get the customer id 
        if(Username.equals("") || Password.equals("")){
            JOptionPane.showMessageDialog(null, "Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
        //if customer profile already made and user sign in successfully, pass to main windows
        else if (Cust.checkExist() && Acct.signIn()) {
            MainWindowsBO main = new MainWindowsBO(Username, CID);
            //if user log in successfully but no customer profile, make them create one
        } else if (Acct.signIn()) {

            CreateProfileBO createprof = new CreateProfileBO(Username, Acct.getName()); //pass user name and the name of person who owns account
        } else {
            JOptionPane.showMessageDialog(null, "Login failed because of invalid username or password.", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
