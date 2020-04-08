package ezpassapplication;

import java.awt.*;     
import java.awt.event.*;
import javax.swing.*;

class RechargePanel extends JPanel implements ActionListener {

    private JButton OpenButton;
    private JTextField CIDField, CardField, NameField, ExpField, CVVField, CurrentBalField, NewBalField;
    private String CustomerID, CurrentBalance, CardNumber, ExpirationDate, CVV, Name, AddBalance;

    public RechargePanel(String CID, float Bal) {
        OpenButton = new JButton("Recharge"); //recharge button
        CustomerID = CID;

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
        NewBalField = new JTextField(15);
        CardField = new JTextField(15);
        NameField = new JTextField(15);
        ExpField = new JTextField(15);
        CVVField = new JTextField(15);

        //labels
        JLabel NewBalLabel = new JLabel("Add to Balance:");
        JLabel CardLabel = new JLabel("Card Number:");
        JLabel NameLabel = new JLabel("Name on Card:");
        JLabel EXPLabel = new JLabel("Expiration Date:");
        JLabel CVVLabel = new JLabel("CVV:");

        //add textfields and labels to respective panels
        JPanel NewBalPane = new JPanel();
        NewBalPane.add(NewBalLabel);
        NewBalPane.add(NewBalField);
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

        JPanel NorthPanel = new JPanel();
        NorthPanel.add(CIDPane);
        NorthPanel.add(BalPane);

        JPanel CreditPanel = new JPanel();
        CreditPanel.add(NewBalPane);
        CreditPanel.add(CardPane);
        CreditPanel.add(NamePane);
        CreditPanel.add(EXPPane);
        CreditPanel.add(CVVPane);
        CreditPanel.add(OpenButton);
        setLayout(new BorderLayout());
        add(NorthPanel, BorderLayout.NORTH); //northpanel contain the Customer ID and balance
        add(CreditPanel, BorderLayout.CENTER); //contains the credit card information
    
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
            AddBalance = NewBalField.getText();
            RechargeControl RC_CTRL = new RechargeControl(CustomerID, CardNumber, Name, ExpirationDate, CVV, AddBalance, CurrentBalance);
        }
    }

}

public class RechargeBO extends JFrame {

    private RechargePanel CP_Panel;

    public RechargeBO(String CID, float Bal) {
        setTitle("Recharge");
        setSize(600, 600);

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
