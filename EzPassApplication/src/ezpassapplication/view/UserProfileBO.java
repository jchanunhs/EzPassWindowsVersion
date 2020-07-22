package ezpassapplication.view;

import ezpassapplication.model.Customer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class UserProfilePanel extends JPanel implements ActionListener {

    private Customer cus;
    private JButton ChangePassword, Recharge, LogOut;
    private JTextField NameField, StreetField, CityField, StateField, ZipField, PhoneField, EmailField, BalanceField;
    private String Name, Street, City, State, Zip, Phone, Email, Balance, Username, CustomerID;

    public UserProfilePanel(String CID, String User) {
        CustomerID = CID;
        Username = User;
        //create customer object and get their data
        cus = new Customer(CustomerID);

        JPanel NamePanel = new JPanel();
        JLabel NameLabel = new JLabel("Customer Name:");
        NameField = new JTextField(15);
        NameField.setText(cus.getName());
        NameField.setEditable(false);
        NamePanel.add(NameLabel);
        NamePanel.add(NameField);

        JPanel StreetPanel = new JPanel();
        JLabel StreetLabel = new JLabel("Street: ");
        StreetField = new JTextField(15);
        StreetField.setText(cus.getStreet());
        StreetField.setEditable(false);
        StreetPanel.add(StreetLabel);
        StreetPanel.add(StreetField);

        JPanel CityPanel = new JPanel();
        JLabel CityLabel = new JLabel("City: ");
        CityField = new JTextField(15);
        CityField.setText(cus.getCity());
        CityField.setEditable(false);
        CityPanel.add(CityLabel);
        CityPanel.add(CityField);

        JPanel StatePanel = new JPanel();
        JLabel StateLabel = new JLabel("State: ");
        StateField = new JTextField(15);
        StateField.setText(cus.getState());
        StateField.setEditable(false);
        StatePanel.add(StateLabel);
        StatePanel.add(StateField);

        JPanel ZipPanel = new JPanel();
        JLabel ZipLabel = new JLabel("Zip: ");
        ZipField = new JTextField(15);
        ZipField.setText(cus.getZip());
        ZipField.setEditable(false);
        ZipPanel.add(ZipLabel);
        ZipPanel.add(ZipField);

        JPanel PhonePanel = new JPanel();
        JLabel PhoneLabel = new JLabel("Phone: ");
        PhoneField = new JTextField(15);
        PhoneField.setText(cus.getPhone());
        PhoneField.setEditable(false);
        PhonePanel.add(PhoneLabel);
        PhonePanel.add(PhoneField);

        JPanel EmailPanel = new JPanel();
        JLabel EmailLabel = new JLabel("Email: ");
        EmailField = new JTextField(15);
        EmailField.setText(cus.getEmail());
        EmailField.setEditable(false);
        EmailPanel.add(EmailLabel);
        EmailPanel.add(EmailField);

        JPanel BalancePanel = new JPanel();
        JLabel BalanceLabel = new JLabel("Balance:");
        BalanceField = new JTextField(15);
        BalanceField.setText(String.valueOf(cus.getBalance()));
        BalanceField.setEditable(false);
        BalancePanel.add(BalanceLabel);
        BalancePanel.add(BalanceField);

        JPanel ButtonPanel = new JPanel();
        ChangePassword = new JButton("Change Password");
        Recharge = new JButton("Recharge");
        LogOut = new JButton("LogOut");
        ChangePassword.addActionListener(this);
        Recharge.addActionListener(this);
        LogOut.addActionListener(this);
        ButtonPanel.add(ChangePassword);
        ButtonPanel.add(Recharge);
        ButtonPanel.add(LogOut);

        //Vertical design
        Box MainPanel = Box.createVerticalBox();
        MainPanel.add(NamePanel);
        MainPanel.add(StreetPanel);
        MainPanel.add(CityPanel);
        MainPanel.add(StatePanel);
        MainPanel.add(ZipPanel);
        MainPanel.add(PhonePanel);
        MainPanel.add(EmailPanel);
        MainPanel.add(BalancePanel);
        MainPanel.add(ButtonPanel);
        setLayout(new BorderLayout());
        add(MainPanel, BorderLayout.NORTH);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        float Bal = Float.parseFloat(BalanceField.getText());
        String arg = evt.getActionCommand();
        if (arg.equals("Change Password")) { //close user profile window and open change password window
            ChangePasswordBO CPBO = new ChangePasswordBO(CustomerID, Username);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        }
        if (arg.equals("Recharge")) {// close user profile window and open recharge window
            RechargeBO RCBO = new RechargeBO(CustomerID, Username);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        }
        if (arg.equals("LogOut")) { //close program
            System.exit(0);
        }
    }
}

public class UserProfileBO extends JFrame {

    private UserProfilePanel UP_Panel;

    public UserProfileBO(String CID, String User) {
        setTitle("Personal Profile");
        setSize(800, 800);

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
        UP_Panel = new UserProfilePanel(CID, User);
        contentPane.add(UP_Panel);
        setVisible(true);
    }

}
