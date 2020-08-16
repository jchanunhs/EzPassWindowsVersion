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
        Username = UName;

        JPanel NamePanel = new JPanel();
        JLabel NameLabel = new JLabel("Customer Name:");
        NameField = new JTextField(15);
        NameField.setText(NM);
        NameField.setEditable(false);
        NamePanel.add(NameLabel);
        NamePanel.add(NameField);

        JPanel StreetPanel = new JPanel();
        JLabel StreetLabel = new JLabel("Street: ");
        StreetField = new JTextField(15);
        StreetPanel.add(StreetLabel);
        StreetPanel.add(StreetField);

        JPanel CityPanel = new JPanel();
        JLabel CityLabel = new JLabel("City: ");
        CityField = new JTextField(15);
        CityPanel.add(CityLabel);
        CityPanel.add(CityField);

        JPanel StatePanel = new JPanel();
        JLabel StateLabel = new JLabel("State: ");
        StateField = new JTextField(15);
        StatePanel.add(StateLabel);
        StatePanel.add(StateField);

        JPanel ZipPanel = new JPanel();
        JLabel ZipLabel = new JLabel("Zip: ");
        ZipField = new JTextField(15);
        ZipPanel.add(ZipLabel);
        ZipPanel.add(ZipField);

        JPanel PhonePanel = new JPanel();
        JLabel PhoneLabel = new JLabel("Phone: ");
        PhoneField = new JTextField(15);
        PhonePanel.add(PhoneLabel);
        PhonePanel.add(PhoneField);

        JPanel EmailPanel = new JPanel();
        JLabel EmailLabel = new JLabel("Email: ");
        EmailField = new JTextField(15);
        EmailPanel.add(EmailLabel);
        EmailPanel.add(EmailField);

        JPanel ButtonPanel = new JPanel();
        CreateButton = new JButton("Submit");
        CreateButton.addActionListener(this);
        ButtonPanel.add(CreateButton);

        //Main Panel 
        Box MainPanel = Box.createVerticalBox();
        MainPanel.add(NamePanel);
        MainPanel.add(StreetPanel);
        MainPanel.add(CityPanel);
        MainPanel.add(StatePanel);
        MainPanel.add(ZipPanel);
        MainPanel.add(PhonePanel);
        MainPanel.add(EmailPanel);
        MainPanel.add(ButtonPanel);
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

            if (Name.isEmpty() || Street.isEmpty() || City.isEmpty() || State.isEmpty() || Zip.isEmpty() || Phone.isEmpty() || Email.isEmpty()) {
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
