package ezpassapplication.view;

import ezpassapplication.control.RechargeControl;
import ezpassapplication.service.CreditCardService;
import ezpassapplication.service.CustomerService;
import ezpassapplication.model.CreditCard;
import ezpassapplication.model.Customer;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class RechargePanel extends JPanel implements ActionListener {

    private JButton RechargeButton, BackButton;
    private JTextField CIDField, CardField, NameField, ExpField, CVVField, CurrentBalField, AddBalField;
    private String CustomerID, CurrentBalance, CardNumber, ExpirationDate, CVV, Name, AddBalance, Username;
    private DefaultTableModel model = new DefaultTableModel();
    private JTable table; //table
    private String[] columnName = {"CreditID", "Date", "Time", "CreditAmount"};
    CreditCardService creditcarddao;
    private CreditCard credit;
    private Customer customer;

    public RechargePanel(String CID, String User) {
        CustomerID = CID;
        Username = User;

        JPanel CIDPanel = new JPanel();
        JLabel CustomerIDLabel = new JLabel("Customer ID:");
        CIDField = new JTextField(15);
        CIDField.setText(CustomerID);
        CIDField.setEditable(false);
        CIDPanel.add(CustomerIDLabel);
        CIDPanel.add(CIDField);

        JPanel BalPanel = new JPanel();
        JLabel BalanceLabel = new JLabel("Current Balance:");
        CustomerService customerdao = new CustomerService();
        customer = customerdao.getCustomerInformation(CustomerID);
        CurrentBalance = String.valueOf(customer.getBalance()); //show current balance
        CurrentBalField = new JTextField(15);
        CurrentBalField.setText(CurrentBalance);
        CurrentBalField.setEditable(false);
        BalPanel.add(BalanceLabel);
        BalPanel.add(CurrentBalField);

        JPanel CardPanel = new JPanel();
        JLabel CardLabel = new JLabel("Card Number:");
        CardField = new JTextField(15);
        CardPanel.add(CardLabel);
        CardPanel.add(CardField);

        JPanel EXPPanel = new JPanel();
        JLabel EXPLabel = new JLabel("Expiration Date:");
        ExpField = new JTextField(15);
        EXPPanel.add(EXPLabel);
        EXPPanel.add(ExpField);

        JPanel CVVPanel = new JPanel();
        JLabel CVVLabel = new JLabel("CVV:");
        CVVField = new JTextField(15);
        CVVPanel.add(CVVLabel);
        CVVPanel.add(CVVField);

        JPanel NamePanel = new JPanel();
        JLabel NameLabel = new JLabel("Name on Card:");
        NameField = new JTextField(15);
        NamePanel.add(NameLabel);
        NamePanel.add(NameField);

        JPanel AddBalPanel = new JPanel();
        JLabel AddBalLabel = new JLabel("Add to Balance:");
        AddBalField = new JTextField(15);
        AddBalPanel.add(AddBalLabel);
        AddBalPanel.add(AddBalField);

        JPanel ButtonPanel = new JPanel();
        RechargeButton = new JButton("Recharge");
        BackButton = new JButton("Back");
        RechargeButton.addActionListener(this);
        BackButton.addActionListener(this);
        ButtonPanel.add(RechargeButton);
        ButtonPanel.add(BackButton);

        JPanel LabelPanel = new JPanel(new FlowLayout()); //center label for table
        JLabel ListLabel = new JLabel("Credit List");
        LabelPanel.add(ListLabel);

        JPanel CreditTable = new JPanel();//tables for credit list

        creditcarddao = new CreditCardService();
        List <CreditCard> CreditCardList = creditcarddao.getAllTransactions(CustomerID); //get all credit card object from customer
        model.setColumnIdentifiers(columnName); //column titles
        for (CreditCard creditcard : CreditCardList) { //add credit card information to model
            model.addRow(new Object[]{creditcard.getCreditID(), creditcard.getDate(), creditcard.getTime(), creditcard.getCreditAmount()});
        }

        //initializing a table and scroll pane
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table); //add scroll bar to table
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(600, 450));
        CreditTable.add(scroll);

        //Credit card form in the top, show credit list in the bottom
        Box MainPanel = Box.createVerticalBox();
        MainPanel.add(CIDPanel);
        MainPanel.add(BalPanel);
        MainPanel.add(CardPanel);
        MainPanel.add(EXPPanel);
        MainPanel.add(CVVPanel);
        MainPanel.add(NamePanel);
        MainPanel.add(AddBalPanel);
        MainPanel.add(ButtonPanel);
        //credit list
        MainPanel.add(LabelPanel);
        MainPanel.add(CreditTable);
        setLayout(new BorderLayout());
        add(MainPanel, BorderLayout.NORTH);

    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        String arg = evt.getActionCommand();
        if (arg.equals("Recharge")) {//get input from user and forward to control   
            try {
                CardNumber = CardField.getText();
                Name = NameField.getText();
                ExpirationDate = ExpField.getText();
                CVV = CVVField.getText();
                AddBalance = AddBalField.getText();
                if (CardNumber.isEmpty() || Name.isEmpty() || ExpirationDate.isEmpty() || CVV.isEmpty() || AddBalance.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "One or more fields are empty! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
                } else {
                    float add_bal = Float.parseFloat(AddBalance); //convert string to float
                    RechargeControl RC_CTRL = new RechargeControl(evt, CustomerID, Username, CardNumber, Name, ExpirationDate, CVV, add_bal);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Add Balance must be a number!", "Confirmation", JOptionPane.ERROR_MESSAGE);
            }

        } else if (arg.equals("Back")) { // open main window and close recharge window
            MainWindowsBO main = new MainWindowsBO(CustomerID, Username);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        }
    }

}

public class RechargeBO extends JFrame {

    private RechargePanel CP_Panel;

    public RechargeBO(String CID, String User) {
        setTitle("Recharge");
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
        CP_Panel = new RechargePanel(CID, User);
        contentPane.add(CP_Panel);
        setVisible(true);
    }

}
