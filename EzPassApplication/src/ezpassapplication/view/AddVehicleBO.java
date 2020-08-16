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
        CustomerID = CID; //set customer id
        Username = User;

        JPanel CustomerIDPanel = new JPanel();
        JLabel CustomerIDLabel = new JLabel("Customer ID:");
        CustomerIDField = new JTextField(15);
        CustomerIDField.setText(CustomerID);
        CustomerIDField.setEditable(false);
        CustomerIDPanel.add(CustomerIDLabel);
        CustomerIDPanel.add(CustomerIDField);

        JPanel LicensePlateNumberPanel = new JPanel();
        JLabel LicensePlateNumberLabel = new JLabel("License Plate:");
        LicensePlateNumberField = new JTextField(15);
        LicensePlateNumberPanel.add(LicensePlateNumberLabel);
        LicensePlateNumberPanel.add(LicensePlateNumberField);

        JPanel MakePanel = new JPanel();
        JLabel MakeLabel = new JLabel("Make: ");
        MakeField = new JTextField(15);
        MakePanel.add(MakeLabel);
        MakePanel.add(MakeField);

        JPanel ModelPanel = new JPanel();
        JLabel ModelLabel = new JLabel("Model: ");
        ModelField = new JTextField(15);
        ModelPanel.add(ModelLabel);
        ModelPanel.add(ModelField);

        JPanel YearPanel = new JPanel();
        JLabel YearLabel = new JLabel("Year: ");
        YearField = new JTextField(15);
        YearPanel.add(YearLabel);
        YearPanel.add(YearField);

        JPanel ColorPanel = new JPanel();
        JLabel ColorLabel = new JLabel("Color: ");
        ColorField = new JTextField(15);
        ColorPanel.add(ColorLabel);
        ColorPanel.add(ColorField);

        JPanel TagCodePanel = new JPanel();
        JLabel TagCodeLabel = new JLabel("TagCode: ");
        TagCodeField = new JTextField(15);
        TagCodePanel.add(TagCodeLabel);
        TagCodePanel.add(TagCodeField);

        JPanel ButtonPanel = new JPanel();
        SubmitButton = new JButton("Submit");
        BackButton = new JButton("Back");
        SubmitButton.addActionListener(this);
        BackButton.addActionListener(this);
        ButtonPanel.add(SubmitButton);
        ButtonPanel.add(BackButton);

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
            if (LicensePlate.isEmpty() || Make.isEmpty() || Model.isEmpty() || Year.isEmpty() || Color.isEmpty() || TagCode.isEmpty()) {
                JOptionPane.showMessageDialog(null, "One or more fields are empty! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
            } else {
                AddVehicleControl AV_CTRL = new AddVehicleControl(evt, LicensePlate, Make, Model, Year, Color, TagCode, CustomerID);
            }

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
        AV_Panel = new AddVehiclePanel(CID, User);
        contentPane.add(AV_Panel);
        setVisible(true);
    }

}
