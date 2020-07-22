package ezpassapplication.view;

import ezpassapplication.control.PayTollControl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PayTollPanel extends JPanel implements ActionListener {

    private JButton SubmitButton;
    private JTextField LicensePlateField, TagCodeField, TollPlazaField, TollLaneNumberField, TollAmountField;
    private String LicensePlateNumber, TagCode, TollPlaza, TollLaneNumber, TollAmount, CustomerID, Username;

    public PayTollPanel(String CID, String User) {
        CustomerID = CID;
        Username = User;

        JPanel LicensePlatePanel = new JPanel();
        JLabel LicensePlateLabel = new JLabel("License Plate Number: ");
        LicensePlateField = new JTextField(15);
        LicensePlatePanel.add(LicensePlateLabel);
        LicensePlatePanel.add(LicensePlateField);

        JPanel TagCodePanel = new JPanel();
        JLabel TagCodeLabel = new JLabel("Tag Code: ");
        TagCodeField = new JTextField(15);
        TagCodePanel.add(TagCodeLabel);
        TagCodePanel.add(TagCodeField);

        JPanel TollPlazaPanel = new JPanel();
        JLabel TollPlazaLabel = new JLabel("Toll Plaza: ");
        TollPlazaField = new JTextField(15);
        TollPlazaPanel.add(TollPlazaLabel);
        TollPlazaPanel.add(TollPlazaField);

        JPanel TollLaneNumberPanel = new JPanel();
        JLabel TollLaneNumberLabel = new JLabel("Toll Lane Number: ");
        TollLaneNumberField = new JTextField(15);
        TollLaneNumberPanel.add(TollLaneNumberLabel);
        TollLaneNumberPanel.add(TollLaneNumberField);

        JPanel TollAmountPanel = new JPanel();
        JLabel TollAmountLabel = new JLabel("Toll Amount: ");
        TollAmountField = new JTextField(15);
        TollAmountPanel.add(TollAmountLabel);
        TollAmountPanel.add(TollAmountField);

        JPanel ButtonPanel = new JPanel();
        SubmitButton = new JButton("Submit");
        SubmitButton.addActionListener(this);
        ButtonPanel.add(SubmitButton);

        //vertical box
        Box MainPanel = Box.createVerticalBox();
        MainPanel.add(LicensePlatePanel);
        MainPanel.add(TagCodePanel);
        MainPanel.add(TollPlazaPanel);
        MainPanel.add(TollLaneNumberPanel);
        MainPanel.add(TollAmountPanel);
        MainPanel.add(ButtonPanel);
        setLayout(new BorderLayout());
        add(MainPanel, BorderLayout.NORTH);

    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        String arg = evt.getActionCommand();
        if (arg.equals("Submit")) { //get user input and pass to control
            try {
                LicensePlateNumber = LicensePlateField.getText();
                TagCode = TagCodeField.getText();
                TollPlaza = TollPlazaField.getText();
                TollLaneNumber = TollLaneNumberField.getText();
                TollAmount = TollAmountField.getText();
                if (LicensePlateNumber.equals("") || TagCode.equals("") || TollPlaza.equals("") || TollLaneNumber.equals("") || TollAmount.equals("")) {
                    JOptionPane.showMessageDialog(null, "One or more fields are empty! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
                } else {
                    int intTollLaneNum = Integer.parseInt(String.valueOf(TollLaneNumber)); //Lane number in DB is int
                    float TollAmt = Float.parseFloat(String.valueOf(TollAmount)); //Toll amount is float in DB
                    PayTollControl PT_CTRL = new PayTollControl(evt, LicensePlateNumber, TagCode, TollPlaza, intTollLaneNum, TollAmt, CustomerID); //pass to control object
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Toll Lane and Toll Amount must be a number!", "Confirmation", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}

public class PayTollBO extends JFrame {

    private PayTollPanel PT_Panel;

    public PayTollBO(String CID, String User) {
        setTitle("Pay Toll");
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
        PT_Panel = new PayTollPanel(CID, User);
        contentPane.add(PT_Panel);
        setVisible(true);
    }

}
