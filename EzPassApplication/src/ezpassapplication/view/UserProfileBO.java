package ezpassapplication.view;

import ezpassapplication.model.Customer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class UserProfilePanel extends JPanel implements ActionListener {

    Customer cus;
    private JButton ChangePassword, Recharge, LogOut;
    private JTextField NameField, StreetField, CityField, StateField, ZipField, PhoneField, EmailField, BalanceField;
    private String Name, Street, City, State, Zip, Phone, Email, Balance, Username, CustomerID;

    public UserProfilePanel(String CID,String User) {

        //create customer object and get their data
        cus = new Customer(CID);
        cus.setData();
        
        CustomerID = CID;
        Username = User;
        
        ChangePassword = new JButton("Change Password");
        Recharge = new JButton("Recharge");
        LogOut = new JButton("LogOut");
        ChangePassword.addActionListener(this);
        Recharge.addActionListener(this);
        LogOut.addActionListener(this);

     
        NameField = new JTextField(15);
        NameField.setText(cus.getName());
        NameField.setEditable(false);

        StreetField = new JTextField(15);
        StreetField.setText(cus.getStreet());
        StreetField.setEditable(false);

        CityField = new JTextField(15);
        CityField.setText(cus.getCity());
        CityField.setEditable(false);

        StateField = new JTextField(15);
        StateField.setText(cus.getState());
        StateField.setEditable(false);

        ZipField = new JTextField(15);
        ZipField.setText(cus.getZip());
        ZipField.setEditable(false);

        PhoneField = new JTextField(15);
        PhoneField.setText(cus.getPhone());
        PhoneField.setEditable(false);

        EmailField = new JTextField(15);
        EmailField.setText(cus.getEmail());
        EmailField.setEditable(false);

        BalanceField = new JTextField(15);
        BalanceField.setText(String.valueOf(cus.getBalance()));
        BalanceField.setEditable(false);

        //JLabel TypeLabel = new JLabel("Choose Account Type: ");
        JLabel NameLabel = new JLabel("Customer Name:");
        JLabel StreetLabel = new JLabel("Street: ");
        JLabel CityLabel = new JLabel("City: ");
        JLabel StateLabel = new JLabel("State: ");
        JLabel ZipLabel = new JLabel("Zip: ");
        JLabel PhoneLabel = new JLabel("Phone: ");
        JLabel EmailLabel = new JLabel("Email: ");
        JLabel BalanceLabel = new JLabel("Opening Deposit:");

        JPanel NamePanel = new JPanel();
        JPanel StreetPanel = new JPanel();
        JPanel CityPanel = new JPanel();
        JPanel StatePanel = new JPanel();
        JPanel ZipPanel = new JPanel();
        JPanel PhonePanel = new JPanel();
        JPanel EmailPanel = new JPanel();
        JPanel BalancePanel = new JPanel();
        JPanel ButtonPanel = new JPanel();

        NamePanel.add(NameLabel);
        NamePanel.add(NameField);
        StreetPanel.add(StreetLabel);
        StreetPanel.add(StreetField);
        CityPanel.add(CityLabel);
        CityPanel.add(CityField);
        StatePanel.add(StateLabel);
        StatePanel.add(StateField);
        ZipPanel.add(ZipLabel);
        ZipPanel.add(ZipField);
        PhonePanel.add(PhoneLabel);
        PhonePanel.add(PhoneField);
        EmailPanel.add(EmailLabel);
        EmailPanel.add(EmailField);
        BalancePanel.add(BalanceLabel);
        BalancePanel.add(BalanceField);
        ButtonPanel.add(ChangePassword);
        ButtonPanel.add(Recharge);
        ButtonPanel.add(LogOut);

        Box NorthPanel = Box.createVerticalBox();
        NorthPanel.add(NamePanel);
        NorthPanel.add(StreetPanel);
        NorthPanel.add(CityPanel);
        NorthPanel.add(StatePanel);
        NorthPanel.add(ZipPanel);
        NorthPanel.add(PhonePanel);
        NorthPanel.add(EmailPanel);
        NorthPanel.add(BalancePanel);
        NorthPanel.add(ButtonPanel);
        setLayout(new BorderLayout());
        add(NorthPanel, BorderLayout.NORTH);
        
    }

    //If user logout, then we exit program
    //if user clicks change password or recharge, declare respective BO
    @Override
    public void actionPerformed(ActionEvent evt) {
        float Bal = Float.parseFloat(BalanceField.getText());
        String arg = evt.getActionCommand();
        if (arg.equals("Change Password")) {
            ChangePasswordBO CPBO = new ChangePasswordBO(CustomerID, Username);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        }
        if (arg.equals("Recharge")) {
            RechargeBO RCBO = new RechargeBO(CustomerID, Username, Bal);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        }
        if (arg.equals("LogOut")) {
            System.exit(0);

        }

    }
}

public class UserProfileBO extends JFrame {

    private UserProfilePanel UP_Panel;

    public UserProfileBO(String CID, String User) {
        setTitle("Personal Profile");
        setSize(450, 450);

        //get screen size and set the location of the frame
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        setLocation(screenWidth / 3, screenHeight / 4);

        addWindowListener(new WindowAdapter() //handle window event
        {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        Container contentPane = getContentPane(); //add a panel to a frame
        UP_Panel = new UserProfilePanel(CID,User);
        contentPane.add(UP_Panel);
        setVisible(true);
    }

}
