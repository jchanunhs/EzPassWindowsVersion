package ezpassapplication.view;

import ezpassapplication.control.AddTagControl;
import ezpassapplication.entity.EzTag;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class AddTagPanel extends JPanel implements ActionListener {

    private JButton SubmitButton, BackButton;
    private JTextField TagCodeField, CustomerIDField;
    private String TagCode, TagType, CustomerID, Username;
    private JComboBox TagTypeGroup;

    public AddTagPanel(String CID, String User) {
        CustomerID = CID;
        Username = User;

        JPanel CustomerIDPanel = new JPanel();
        JLabel CustomerIDLabel = new JLabel("CustomerID: ");
        CustomerIDField = new JTextField(15);
        CustomerIDField.setText(CustomerID);
        CustomerIDField.setEditable(false);
        CustomerIDPanel.add(CustomerIDLabel);
        CustomerIDPanel.add(CustomerIDField);

        JPanel TagCodePanel = new JPanel();
        JLabel TagCodeLabel = new JLabel("Tag Code: ");
        TagCodeField = new JTextField(15);
        TagCodePanel.add(TagCodeLabel);
        TagCodePanel.add(TagCodeField);

        JPanel TagTypePanel = new JPanel();
        JLabel TagTypeLabel = new JLabel("Tag Type: ");
        String[] Options = {"", "Car", "Truck", "Overload"};
        TagTypeGroup = new JComboBox(Options); //ComboBox for tag type
        TagTypePanel.add(TagTypeLabel);
        TagTypePanel.add(TagTypeGroup);

        JPanel ButtonPanel = new JPanel();
        SubmitButton = new JButton("Submit");
        BackButton = new JButton("Back");
        SubmitButton.addActionListener(this);
        BackButton.addActionListener(this);
        ButtonPanel.add(SubmitButton);
        ButtonPanel.add(BackButton);

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
            TagType = (String) TagTypeGroup.getSelectedItem();
            if (TagCode.isEmpty() || TagType.isEmpty()) {
                JOptionPane.showMessageDialog(null, "One or more fields are empty! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
            } else {
                EzTag eztag = new EzTag();
                eztag.setTagCode(TagCode);
                eztag.setTagType(TagType);
                eztag.setCustomerID(CustomerID);
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
