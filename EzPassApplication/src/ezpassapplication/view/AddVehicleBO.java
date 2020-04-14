package ezpassapplication.view;

import ezpassapplication.control.AddVehicleControl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class AddVehiclePanel extends JPanel implements ActionListener {

    private JButton OpenButton;
    private JTextField LicensePlateNumberField, MakeField, ModelField, YearField, ColorField, TagCodeField, CustomerIDField;
    private String LicensePlate, Make, Model, Year, Color, TagCode, Email, Balance, CustomerID;

    public AddVehiclePanel(String CID) {
        OpenButton = new JButton("Submit"); //submit button
        CustomerID = CID; //set customer id
        
        //set jtextfields. customer id will not be editable by user
        CustomerIDField = new JTextField(15);
        CustomerIDField.setText(CustomerID);
        CustomerIDField.setEditable(false);
        LicensePlateNumberField = new JTextField(15);
        MakeField = new JTextField(15);
        ModelField = new JTextField(15);
        YearField = new JTextField(15);
        ColorField = new JTextField(15);
        TagCodeField = new JTextField(15);
        
        //labels for textfield
        JLabel CustomerIDLabel = new JLabel("Customer ID:");
        JLabel LicensePlateNumberLabel = new JLabel("License Plate:");
        JLabel MakeLabel = new JLabel("Make: ");
        JLabel ModelLabel = new JLabel("Model: ");
        JLabel YearLabel = new JLabel("Year: ");
        JLabel ColorLabel = new JLabel("Color: ");
        JLabel TagCodeLabel = new JLabel("TagCode: ");
        
        //each panel contains a label and a text field
        JPanel CustomerIDPanel = new JPanel();
        JPanel LicensePlateNumberPanel = new JPanel();
        JPanel MakePanel = new JPanel();
        JPanel ModelPanel = new JPanel();
        JPanel YearPanel = new JPanel();
        JPanel ColorPanel = new JPanel();
        JPanel TagCodePanel = new JPanel();
        JPanel EmailPanel = new JPanel();
        JPanel BalancePanel = new JPanel();

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

        OpenButton.addActionListener(this); //event listener registration
        
        //The panels will be placed in the centerpanel to set it to the center of windows
        JPanel CenterPanel = new JPanel();
        CenterPanel.add(CustomerIDPanel);
        CenterPanel.add(LicensePlateNumberPanel);
        CenterPanel.add(MakePanel);
        CenterPanel.add(ModelPanel);
        CenterPanel.add(YearPanel);
        CenterPanel.add(ColorPanel);
        CenterPanel.add(TagCodePanel);
        ;
        CenterPanel.add(OpenButton);
        setLayout(new BorderLayout());
        add(CenterPanel, BorderLayout.CENTER);
        //add(OpenButton, BorderLayout.SOUTH);//add the one button on to this panel
    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        //Object source = evt.getSource(); //get who generates this event
        String arg = evt.getActionCommand();
        if (arg.equals("Submit")) { //determine which button is clicked
            //when clicked, get values input by textfield and pass it to control object
            LicensePlate = LicensePlateNumberField.getText();
            Make = MakeField.getText();
            Model = ModelField.getText();
            Year = YearField.getText();
            Color = ColorField.getText();
            TagCode = TagCodeField.getText();
            AddVehicleControl CP_CTRL = new AddVehicleControl(LicensePlate, Make, Model, Year, Color, TagCode, CustomerID);


        }
    }

}

public class AddVehicleBO extends JFrame {

    private AddVehiclePanel CP_Panel;

    public AddVehicleBO(String CID) {
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
        CP_Panel = new AddVehiclePanel(CID);
        contentPane.add(CP_Panel);
        show();
    }

  
}
