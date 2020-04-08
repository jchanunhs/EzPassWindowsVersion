package ezpassapplication;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CreateProfilePanel extends JPanel implements ActionListener {

    private JButton OpenButton;
    private JTextField NameField, StreetField, CityField, StateField, ZipField, PhoneField, EmailField, BalanceField;
    private String Name, Street, City, State, Zip, Phone, Email, Balance, UName;

    public CreateProfilePanel(String UName, String NM) {
        OpenButton = new JButton("Submit");

        this.UName = UName;//set username, customer id does not exist right now

        //textfields to get user information
        //user entered name during signup so we set name for user
        NameField = new JTextField(15);
        NameField.setText(NM);
        NameField.setEditable(false);
        StreetField = new JTextField(15);
        CityField = new JTextField(15);
        StateField = new JTextField(15);
        ZipField = new JTextField(15);
        PhoneField = new JTextField(15);
        EmailField = new JTextField(15);
        BalanceField = new JTextField(15);
        BalanceField.setText("0.0");

        //Labels for the textfields
        JLabel NameLabel = new JLabel("Customer Name:");
        JLabel StreetLabel = new JLabel("Street: ");
        JLabel CityLabel = new JLabel("City: ");
        JLabel StateLabel = new JLabel("State: ");
        JLabel ZipLabel = new JLabel("Zip: ");
        JLabel PhoneLabel = new JLabel("Phone: ");
        JLabel EmailLabel = new JLabel("Email: ");
        JLabel BalanceLabel = new JLabel("Opening Deposit:");

        //the panels to place respective textfield and labels
        JPanel NamePanel = new JPanel();
        JPanel StreetPanel = new JPanel();
        JPanel CityPanel = new JPanel();
        JPanel StatePanel = new JPanel();
        JPanel ZipPanel = new JPanel();
        JPanel PhonePanel = new JPanel();
        JPanel EmailPanel = new JPanel();
        JPanel BalancePanel = new JPanel();

        NamePanel.add(NameLabel);
        NamePanel.add(NameField);
        StreetPanel.add(StreetLabel);
        StreetPanel.add(StreetField);
        CityPanel.add(CityLabel);
        CityPanel.add(CityField);
        StatePanel.add(StateLabel);
        StatePanel.add(StateField);
        ZipPanel.add(ZipLabel);
        ZipPanel.add(ZipField);
        PhonePanel.add(PhoneLabel);
        PhonePanel.add(PhoneField);
        EmailPanel.add(EmailLabel);
        EmailPanel.add(EmailField);
        BalancePanel.add(BalanceLabel);
        BalancePanel.add(BalanceField);

        OpenButton.addActionListener(this); //event listener registration

        //User another jpanel to center the design
        JPanel CenterPanel = new JPanel();
        CenterPanel.add(NamePanel);
        CenterPanel.add(StreetPanel);
        CenterPanel.add(CityPanel);
        CenterPanel.add(StatePanel);
        CenterPanel.add(ZipPanel);
        CenterPanel.add(PhonePanel);
        CenterPanel.add(EmailPanel);
        CenterPanel.add(BalancePanel);
        CenterPanel.add(OpenButton);
        setLayout(new BorderLayout());
        add(CenterPanel, BorderLayout.CENTER);
        //add(OpenButton, BorderLayout.SOUTH);//add the one button on to this panel
    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        //Object source = evt.getSource(); //get who generates this event
        String arg = evt.getActionCommand();
        if (arg.equals("Submit")) {
            //get text input by user and pass to control
            Name = NameField.getText();
            Street = StreetField.getText();
            City = CityField.getText();
            State = StateField.getText();
            Zip = ZipField.getText();
            Phone = PhoneField.getText();
            Email = EmailField.getText();
            Balance = BalanceField.getText();
            CreateProfileControl CP_CTRL = new CreateProfileControl(Name, Street, City, State, Zip, Phone, Email, Balance, UName);

        }
    }

}

public class CreateProfileBO extends JFrame {

    private CreateProfilePanel CP_Panel;

    public CreateProfileBO(String UName, String NM) {
        setTitle("Create Your Profile");
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
        CP_Panel = new CreateProfilePanel(UName, NM);
        contentPane.add(CP_Panel);
        show();
    }

}
