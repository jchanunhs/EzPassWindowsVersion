package ezpassapplication.view;

import ezpassapplication.model.CreditCard;
import ezpassapplication.control.RechargeControl;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class RechargePanel extends JPanel implements ActionListener {

    private JButton OpenButton;
    private JTextField CIDField, CardField, NameField, ExpField, CVVField, CurrentBalField, AddBalField;
    private String CustomerID, CurrentBalance, CardNumber, ExpirationDate, CVV, Name, AddBalance;
    private DefaultTableModel model = new DefaultTableModel();
    private JTable table; //table
    private String[] columnName = {"CreditID", "CardNumber", "Date", "Time", "CreditAmount"};
    private CreditCard credit;

    public RechargePanel(String CID, float Bal) {
        OpenButton = new JButton("Recharge"); //recharge button
        CustomerID = CID;
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
        AddBalPane.add(OpenButton); // Last panel for form so we put button next to addbalance
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

        OpenButton.addActionListener(this); //event listener registration

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
        scroll.setPreferredSize(new Dimension(600, 600));
        JLabel listLabel = new JLabel("Credit List");

        JPanel p1 = new JPanel(); //contains the label only
        JPanel p2 = new JPanel();//tables

        p1.add(listLabel);
        p2.add(scroll);
        
        //Credit card form in the top, show credit list in the bottom
        Box b = Box.createVerticalBox();
        b.add(CIDPane);
        b.add(BalPane);
        b.add(CardPane);
        b.add(EXPPane);
        b.add(CVVPane);
        b.add(NamePane);
        b.add(AddBalPane);
        b.add(p1);
        b.add(p2);
        add(b);

    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        //Object source = evt.getSource(); //get who generates this event
        String arg = evt.getActionCommand();
        if (arg.equals("Recharge")) {
            //get input from user and send it to control object
            CardNumber = CardField.getText();
            Name = NameField.getText();
            ExpirationDate = ExpField.getText();
            CVV = CVVField.getText();
            float add_bal = Float.parseFloat(AddBalField.getText()); //convert string to float
            RechargeControl RC_CTRL = new RechargeControl(CustomerID, CardNumber, Name, ExpirationDate, CVV, add_bal);
        }
    }

}

public class RechargeBO extends JFrame {

    private RechargePanel CP_Panel;

    public RechargeBO(String CID, float Bal) {
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
        CP_Panel = new RechargePanel(CID, Bal);
        contentPane.add(CP_Panel);
        show();
    }

}
