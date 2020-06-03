package ezpassapplication.view;

import ezpassapplication.control.AddVehicleControl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class AddVehiclePanel extends JPanel implements ActionListener {

    private JButton SubmitButton, BackButton;
    private JTextField LicensePlateNumberField, MakeField, ModelField, YearField, ColorField, TagCodeField, CustomerIDField;
    private String LicensePlate, Make, Model, Year, Color, TagCode, Email, Balance, CustomerID, Username;

    public AddVehiclePanel(String CID, String User) {

        SubmitButton = new JButton("Submit");
        BackButton = new JButton("Back");
        CustomerID = CID; //set customer id
        Username = User;

        //JTextFields
        CustomerIDField = new JTextField(15);
        CustomerIDField.setText(CustomerID);
        CustomerIDField.setEditable(false);
        LicensePlateNumberField = new JTextField(15);
        MakeField = new JTextField(15);
        ModelField = new JTextField(15);
        YearField = new JTextField(15);
        ColorField = new JTextField(15);
        TagCodeField = new JTextField(15);

        //JLabels
        JLabel CustomerIDLabel = new JLabel("Customer ID:");
        JLabel LicensePlateNumberLabel = new JLabel("License Plate:");
        JLabel MakeLabel = new JLabel("Make: ");
        JLabel ModelLabel = new JLabel("Model: ");
        JLabel YearLabel = new JLabel("Year: ");
        JLabel ColorLabel = new JLabel("Color: ");
        JLabel TagCodeLabel = new JLabel("TagCode: ");

        //JPanels
        JPanel CustomerIDPanel = new JPanel();
        JPanel LicensePlateNumberPanel = new JPanel();
        JPanel MakePanel = new JPanel();
        JPanel ModelPanel = new JPanel();
        JPanel YearPanel = new JPanel();
        JPanel ColorPanel = new JPanel();
        JPanel TagCodePanel = new JPanel();
        JPanel EmailPanel = new JPanel();
        JPanel BalancePanel = new JPanel();
        JPanel ButtonPanel = new JPanel();

        //Add Labels and TextFields to Panels
        CustomerIDPanel.add(CustomerIDLabel);
        CustomerIDPanel.add(CustomerIDField);
        LicensePlateNumberPanel.add(LicensePlateNumberLabel);
        LicensePlateNumberPanel.add(LicensePlateNumberField);
        MakePanel.add(MakeLabel);
        MakePanel.add(MakeField);
        ModelPanel.add(ModelLabel);
        ModelPanel.add(ModelField);
        YearPanel.add(YearLabel);
        YearPanel.add(YearField);
        ColorPanel.add(ColorLabel);
        ColorPanel.add(ColorField);
        TagCodePanel.add(TagCodeLabel);
        TagCodePanel.add(TagCodeField);
        ButtonPanel.add(SubmitButton);
        ButtonPanel.add(BackButton);

        //register event listener
        SubmitButton.addActionListener(this);
        BackButton.addActionListener(this);

        //Vertical design 
        Box MainPanel = Box.createVerticalBox();
        MainPanel.add(CustomerIDPanel);
        MainPanel.add(LicensePlateNumberPanel);
        MainPanel.add(MakePanel);
        MainPanel.add(ModelPanel);
        MainPanel.add(YearPanel);
        MainPanel.add(ColorPanel);
        MainPanel.add(TagCodePanel);
        MainPanel.add(ButtonPanel);
        setLayout(new BorderLayout());
        add(MainPanel, BorderLayout.NORTH);
    }

    public void actionPerformed(ActionEvent evt) {
        String arg = evt.getActionCommand();
        if (arg.equals("Submit")) {  //get inputs and forward to control
            LicensePlate = LicensePlateNumberField.getText();
            Make = MakeField.getText();
            Model = ModelField.getText();
            Year = YearField.getText();
            Color = ColorField.getText();
            TagCode = TagCodeField.getText();
            AddVehicleControl AV_CTRL = new AddVehicleControl(evt, LicensePlate, Make, Model, Year, Color, TagCode, CustomerID);
        } else if (arg.equals("Back")) { //return to main windows and close add vehicle window
            MainWindowsBO main = new MainWindowsBO(CustomerID, Username);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        }
    }

}

public class AddVehicleBO extends JFrame {

    private AddVehiclePanel AV_Panel;

    public AddVehicleBO(String CID, String User) {
        setTitle("Add Vehicle");
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
        AV_Panel = new AddVehiclePanel(CID, User);
        contentPane.add(AV_Panel);
        setVisible(true);
    }

}
