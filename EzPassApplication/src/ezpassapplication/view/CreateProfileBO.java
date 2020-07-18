package ezpassapplication.view;

import ezpassapplication.control.CreateProfileControl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CreateProfilePanel extends JPanel implements ActionListener {

    private JButton CreateButton;
    private JTextField NameField, StreetField, CityField, StateField, ZipField, PhoneField, EmailField;
    private String Name, Street, City, State, Zip, Phone, Email, Username;

    public CreateProfilePanel(String UName, String NM) {
        CreateButton = new JButton("Submit");

        Username = UName;

        //JTextFields
        NameField = new JTextField(15);
        NameField.setText(NM);
        NameField.setEditable(false);
        StreetField = new JTextField(15);
        CityField = new JTextField(15);
        StateField = new JTextField(15);
        ZipField = new JTextField(15);
        PhoneField = new JTextField(15);
        EmailField = new JTextField(15);

        //JLabels
        JLabel NameLabel = new JLabel("Customer Name:");
        JLabel StreetLabel = new JLabel("Street: ");
        JLabel CityLabel = new JLabel("City: ");
        JLabel StateLabel = new JLabel("State: ");
        JLabel ZipLabel = new JLabel("Zip: ");
        JLabel PhoneLabel = new JLabel("Phone: ");
        JLabel EmailLabel = new JLabel("Email: ");

        //JPanels
        JPanel NamePanel = new JPanel();
        JPanel StreetPanel = new JPanel();
        JPanel CityPanel = new JPanel();
        JPanel StatePanel = new JPanel();
        JPanel ZipPanel = new JPanel();
        JPanel PhonePanel = new JPanel();
        JPanel EmailPanel = new JPanel();

        //Add JTextFields and Labels to panels
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

        //Register event listener
        CreateButton.addActionListener(this);

        //Main Panel 
        Box MainPanel = Box.createVerticalBox();
        MainPanel.add(NamePanel);
        MainPanel.add(StreetPanel);
        MainPanel.add(CityPanel);
        MainPanel.add(StatePanel);
        MainPanel.add(ZipPanel);
        MainPanel.add(PhonePanel);
        MainPanel.add(EmailPanel);
        MainPanel.add(CreateButton);
        setLayout(new BorderLayout());
        add(MainPanel, BorderLayout.NORTH);
    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        String arg = evt.getActionCommand();
        if (arg.equals("Submit")) { //get input from user and pass to control

            Name = NameField.getText();
            Street = StreetField.getText();
            City = CityField.getText();
            State = StateField.getText();
            Zip = ZipField.getText();
            Phone = PhoneField.getText();
            Email = EmailField.getText();

            if (Name.equals("") || Street.equals("") || City.equals("") || State.equals("") || Zip.equals("") || Phone.equals("") || Email.equals("")) {
                JOptionPane.showMessageDialog(null, "One or more fields are empty! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
            } else {
                CreateProfileControl CP_CTRL = new CreateProfileControl(evt, Name, Street, City, State, Zip, Phone, Email, Username);
            }

        }
    }
}

public class CreateProfileBO extends JFrame {

    private CreateProfilePanel CP_Panel;

    public CreateProfileBO(String UName, String NM) {
        setTitle("Create Your Profile");
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
        CP_Panel = new CreateProfilePanel(UName, NM);
        contentPane.add(CP_Panel);
        setVisible(true);
    }

}
