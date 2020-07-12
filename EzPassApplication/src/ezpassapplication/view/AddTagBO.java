package ezpassapplication.view;

import ezpassapplication.control.AddTagControl;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.*;

class AddTagPanel extends JPanel implements ActionListener {

    private JButton SubmitButton, BackButton;
    private JTextField TagCodeField, TagTypeField, CustomerIDField;
    private String TagCode, TagType, CustomerID, Username;

    public AddTagPanel(String CID, String User) {

        SubmitButton = new JButton("Submit");
        BackButton = new JButton("Back");
        CustomerID = CID;
        Username = User;

        //JTextFields
        TagCodeField = new JTextField(15);
        TagTypeField = new JTextField(15);
        CustomerIDField = new JTextField(15);
        CustomerIDField.setText(CustomerID);
        CustomerIDField.setEditable(false);

        //JLabels
        JLabel TagCodeLabel = new JLabel("Tag Code: ");
        JLabel TagTypeLabel = new JLabel("Tag Type: ");
        JLabel CustomerIDLabel = new JLabel("CustomerID: ");

        //JPanels
        JPanel TagCodePanel = new JPanel();
        JPanel TagTypePanel = new JPanel();
        JPanel CustomerIDPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();

        //Add Labels and TextFields to Panels
        CustomerIDPanel.add(CustomerIDLabel);
        CustomerIDPanel.add(CustomerIDField);
        TagCodePanel.add(TagCodeLabel);
        TagCodePanel.add(TagCodeField);
        TagTypePanel.add(TagTypeLabel);
        TagTypePanel.add(TagTypeField);
        ButtonPanel.add(SubmitButton);
        ButtonPanel.add(BackButton);

        //register event listener
        SubmitButton.addActionListener(this);
        BackButton.addActionListener(this);

        //Vertical box
        Box MainPanel = Box.createVerticalBox();
        MainPanel.add(CustomerIDPanel);
        MainPanel.add(TagCodePanel);
        MainPanel.add(TagTypePanel);
        MainPanel.add(ButtonPanel);
        setLayout(new BorderLayout());
        add(MainPanel, BorderLayout.NORTH);

    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        String arg = evt.getActionCommand();
        if (arg.equals("Submit")) { //get inputs and forward to control
            TagCode = TagCodeField.getText();
            TagType = TagTypeField.getText();
            if (TagCode.equals("") || TagType.equals("")) {
                JOptionPane.showMessageDialog(null, "One or more fields are empty! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
            } else {
                AddTagControl AT_CTRL = new AddTagControl(evt, TagCode, TagType, CustomerID);
            }
        } else if (arg.equals("Back")) { //return to mainwindows and close add tag window
            MainWindowsBO main = new MainWindowsBO(CustomerID, Username);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        }
    }
}

public class AddTagBO extends JFrame {

    private AddTagPanel AT_Panel;

    public AddTagBO(String CID, String User) {
        setTitle("Add Tag");
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
        AT_Panel = new AddTagPanel(CID, User);
        contentPane.add(AT_Panel);
        setVisible(true);
    }

}
