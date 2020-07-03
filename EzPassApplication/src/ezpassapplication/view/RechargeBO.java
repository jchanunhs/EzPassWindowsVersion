package ezpassapplication.view;

import ezpassapplication.model.CreditCard;
import ezpassapplication.control.RechargeControl;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class RechargePanel extends JPanel implements ActionListener {

    private JButton RechargeButton, BackButton;
    private JTextField CIDField, CardField, NameField, ExpField, CVVField, CurrentBalField, AddBalField;
    private String CustomerID, CurrentBalance, CardNumber, ExpirationDate, CVV, Name, AddBalance, Username;
    private DefaultTableModel model = new DefaultTableModel();
    private JTable table; //table
    private String[] columnName = {"CreditID", "CardNumber", "Date", "Time", "CreditAmount"};
    private CreditCard credit;

    public RechargePanel(String CID, String User, float Bal) {
        RechargeButton = new JButton("Recharge"); //recharge button
        BackButton = new JButton("Back");
        CustomerID = CID;
        Username = User;
        credit = new CreditCard(CustomerID);
        CurrentBalance = String.valueOf(Bal); //show current balance

        JLabel CustomerIDLabel = new JLabel("Customer ID:");
        JLabel BalanceLabel = new JLabel("Current Balance:");

        //dont allow user to change customer id and current balance
        CIDField = new JTextField(15);
        CIDField.setText(CustomerID);
        CIDField.setEditable(false);
        CurrentBalField = new JTextField(15);
        CurrentBalField.setText(CurrentBalance);
        CurrentBalField.setEditable(false);

        //add them to jpanels
        JPanel CIDPane = new JPanel();
        CIDPane.add(CustomerIDLabel);
        CIDPane.add(CIDField);
        JPanel BalPane = new JPanel();
        BalPane.add(BalanceLabel);
        BalPane.add(CurrentBalField);

        //textfields
        AddBalField = new JTextField(15);
        CardField = new JTextField(15);
        NameField = new JTextField(15);
        ExpField = new JTextField(15);
        CVVField = new JTextField(15);

        //labels
        JLabel AddBalLabel = new JLabel("Add to Balance:");
        JLabel CardLabel = new JLabel("Card Number:");
        JLabel NameLabel = new JLabel("Name on Card:");
        JLabel EXPLabel = new JLabel("Expiration Date:");
        JLabel CVVLabel = new JLabel("CVV:");

        //add textfields and labels to respective panels
        JPanel AddBalPane = new JPanel();
        AddBalPane.add(AddBalLabel);
        AddBalPane.add(AddBalField);
        AddBalPane.add(RechargeButton); // Last panel for form so we put button next to addbalance
        AddBalPane.add(BackButton);
        JPanel CardPane = new JPanel();
        CardPane.add(CardLabel);
        CardPane.add(CardField);
        JPanel NamePane = new JPanel();
        NamePane.add(NameLabel);
        NamePane.add(NameField);

        JPanel EXPPane = new JPanel();
        EXPPane.add(EXPLabel);
        EXPPane.add(ExpField);
        JPanel CVVPane = new JPanel();
        CVVPane.add(CVVLabel);
        CVVPane.add(CVVField);

        RechargeButton.addActionListener(this); //event listener registration
        BackButton.addActionListener(this);

        //populate table with all credit transactions as default
        model.setColumnIdentifiers(columnName); //column titles
        ArrayList<String> CreditID_list = credit.getAllTransactions("CreditID");
        ArrayList<String> CN_list = credit.getAllTransactions("CardNumber");
        ArrayList<String> date_list = credit.getAllTransactions("Date");
        ArrayList<String> time_list = credit.getAllTransactions("Time");
        ArrayList<String> CDAmt_list = credit.getAllTransactions("CreditAmount");
        for (int i = 0; i < CreditID_list.size(); i++) {
            String Credit_ID = CreditID_list.get(i);
            String Card_Number = CN_list.get(i);
            String Date = date_list.get(i);
            String Time = time_list.get(i);
            String Credit_AMT = CDAmt_list.get(i);
            model.addRow(new Object[]{Credit_ID, Card_Number, Date, Time, Credit_AMT});
        }

        //initializing a table and scroll pane
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(600, 500));
        JLabel list_label = new JLabel("Credit List");
        JPanel label_pane = new JPanel(new FlowLayout()); //center label for table
        label_pane.add(list_label);

        JPanel credit_list = new JPanel();//tables for credit list
        credit_list.add(scroll);

        //Credit card form in the top, show credit list in the bottom
        Box MainPanel = Box.createVerticalBox();
        MainPanel.add(CIDPane);
        MainPanel.add(BalPane);
        MainPanel.add(CardPane);
        MainPanel.add(EXPPane);
        MainPanel.add(CVVPane);
        MainPanel.add(NamePane);
        MainPanel.add(AddBalPane);
        MainPanel.add(label_pane); // list label will be on top of the credit list
        MainPanel.add(credit_list);
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
                if (CardNumber.equals("") || Name.equals("") || ExpirationDate.equals("") || CVV.equals("") || AddBalance.equals("")) {
                    JOptionPane.showMessageDialog(null, "One or more fields are empty! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
                } else {
                    float add_bal = Float.parseFloat(AddBalance); //convert string to float
                    RechargeControl RC_CTRL = new RechargeControl(evt, CustomerID, Username, CardNumber, Name, ExpirationDate, CVV, add_bal);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Balance must be a number!", "Confirmation", JOptionPane.ERROR_MESSAGE);
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

    public RechargeBO(String CID, String User, float Bal) {
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
        CP_Panel = new RechargePanel(CID, User, Bal);
        contentPane.add(CP_Panel);
        setVisible(true);
    }

}
