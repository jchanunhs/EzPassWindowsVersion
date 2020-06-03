package ezpassapplication.view;

import ezpassapplication.control.PayTollControl;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.*;

class PayTollPanel extends JPanel implements ActionListener {

    private JButton SubmitButton;
    private JTextField TagCodeField, TollPlazaField, TollLaneNumberField, TollAmountField;
    private String TagCode, TollPlaza, TollLaneNumber, TollAmount, CustomerID, Username;

    public PayTollPanel(String CID,String User) {
        CustomerID = CID; //set customer id
        Username = User;
        SubmitButton = new JButton("Submit"); //submit button

        //textfields
        TagCodeField = new JTextField(15);
        TollPlazaField = new JTextField(15);
        TollLaneNumberField = new JTextField(15);
        TollAmountField = new JTextField(15);

        //labels
        JLabel TagCodeLabel = new JLabel("Tag Code: ");
        JLabel TollPlazaLabel = new JLabel("Toll Plaza: ");
        JLabel TollLaneNumberLabel = new JLabel("Toll Lane: ");
        JLabel TollAmountLabel = new JLabel("Toll Amount: ");
       

        //panels
        JPanel TagCodePanel = new JPanel();
        JPanel TollPlazaPanel = new JPanel();
        JPanel TollLaneNumberPanel = new JPanel();
        JPanel TollAmountPanel = new JPanel();
        

        //add labels and textfields to their respective panels
        TagCodePanel.add(TagCodeLabel);
        TagCodePanel.add(TagCodeField);
        TollPlazaPanel.add(TollPlazaLabel);
        TollPlazaPanel.add(TollPlazaField);
        TollLaneNumberPanel.add(TollLaneNumberLabel);
        TollLaneNumberPanel.add(TollLaneNumberField);
        TollAmountPanel.add(TollAmountLabel);
        TollAmountPanel.add(TollAmountField);

        SubmitButton.addActionListener(this);

        //vertical box
        Box NorthPanel = Box.createVerticalBox();
        NorthPanel.add(TagCodePanel);
        NorthPanel.add(TollPlazaPanel);
        NorthPanel.add(TollLaneNumberPanel);
        NorthPanel.add(TollAmountPanel);
        NorthPanel.add(SubmitButton);
        setLayout(new BorderLayout());
        add(NorthPanel, BorderLayout.NORTH);
       
    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        //Object source = evt.getSource(); //get who generates this event
        String arg = evt.getActionCommand();
        if (arg.equals("Submit")) {
            //get user inputs 
            TagCode = TagCodeField.getText();
            TollPlaza = TollPlazaField.getText();
            TollLaneNumber = TollLaneNumberField.getText();
            int intTollLaneNum = Integer.parseInt(String.valueOf(TollLaneNumber)); //Lane number in DB is int
            TollAmount = TollAmountField.getText();
            float TollAmt = Float.parseFloat(String.valueOf(TollAmount)); //Toll amount is float in DB
            PayTollControl PT_CTRL = new PayTollControl(evt, TagCode, TollPlaza, intTollLaneNum, TollAmt, CustomerID); //pass to control object

        }
    }

}

public class PayTollBO extends JFrame {

    private PayTollPanel PT_Panel;

    public PayTollBO(String CID,String User) {
        setTitle("Pay Toll");
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
        PT_Panel = new PayTollPanel(CID,User);
        contentPane.add(PT_Panel);
        setVisible(true);
    }

}
