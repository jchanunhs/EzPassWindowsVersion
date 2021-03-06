package ezpassapplication.view;

import ezpassapplication.control.RemoveTagControl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class RemoveTagPanel extends JPanel implements ActionListener {

    private JButton RemoveButton, BackButton;
    private JTextField TagCodeField, CustomerIDField;
    private String TagCode, CustomerID, Username;

    public RemoveTagPanel(String CID, String User) {
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
        JLabel TagCodeLabel = new JLabel("Tag Code to remove: ");
        TagCodeField = new JTextField(15);
        TagCodePanel.add(TagCodeLabel);
        TagCodePanel.add(TagCodeField);

        JPanel ButtonPanel = new JPanel();
        RemoveButton = new JButton("Remove");
        BackButton = new JButton("Back");
        RemoveButton.addActionListener(this);
        BackButton.addActionListener(this);
        ButtonPanel.add(RemoveButton);
        ButtonPanel.add(BackButton);

        Box MainPanel = Box.createVerticalBox();
        MainPanel.add(CustomerIDPanel);
        MainPanel.add(TagCodePanel);
        MainPanel.add(ButtonPanel);
        setLayout(new BorderLayout());
        add(MainPanel, BorderLayout.NORTH);
    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        //Object source = evt.getSource(); //get who generates this event
        String arg = evt.getActionCommand();
        if (arg.equals("Remove")) {//takes user input and forward to control
            TagCode = TagCodeField.getText();
            if (TagCode.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a tag code!", "Confirmation", JOptionPane.ERROR_MESSAGE);
            } else {
                RemoveTagControl RT_CTRL = new RemoveTagControl(evt, TagCode, CustomerID);
            }
        } else if (arg.equals("Back")) { // open main windows and close remove tag window
            MainWindowsBO main = new MainWindowsBO(CustomerID, Username);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        }
    }
}

public class RemoveTagBO extends JFrame {

    private RemoveTagPanel RT_Panel;

    public RemoveTagBO(String CID, String User) {
        setTitle("Renove Tag");
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
        RT_Panel = new RemoveTagPanel(CID, User);
        contentPane.add(RT_Panel);
        setVisible(true);
    }

}
