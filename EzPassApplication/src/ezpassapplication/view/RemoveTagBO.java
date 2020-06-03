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
        RemoveButton = new JButton("Remove"); //remove tag
        BackButton = new JButton("Back");
        
        CustomerID = CID; //set cid
        Username = User;
        //user can enter tag code that they want removed
        TagCodeField = new JTextField(15);
        CustomerIDField = new JTextField(15);
        CustomerIDField.setText(CustomerID);
        CustomerIDField.setEditable(false); //customer id not allowed to be changed

        JLabel TagCodeLabel = new JLabel("Tag Code to remove: ");
        JLabel CustomerIDLabel = new JLabel("CustomerID: ");

        //set up panels
        JPanel TagCodePanel = new JPanel();
        JPanel CustomerIDPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        
        //add labels and textfields
        CustomerIDPanel.add(CustomerIDLabel);
        CustomerIDPanel.add(CustomerIDField);
        TagCodePanel.add(TagCodeLabel);
        TagCodePanel.add(TagCodeField);
        ButtonPanel.add(RemoveButton);
        ButtonPanel.add(BackButton);
        
        RemoveButton.addActionListener(this); //event listener registration
        BackButton.addActionListener(this);
        
        Box CenterPanel = Box.createVerticalBox();
        CenterPanel.add(CustomerIDPanel);
        CenterPanel.add(TagCodePanel);
        CenterPanel.add(ButtonPanel);
        setLayout(new BorderLayout());
        add(CenterPanel, BorderLayout.NORTH); //center the jpanel
    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        //Object source = evt.getSource(); //get who generates this event
        String arg = evt.getActionCommand();
        if (arg.equals("Remove")) {
            //take user input for tag code and send to control object
            TagCode = TagCodeField.getText();
            RemoveTagControl CP_CTRL = new RemoveTagControl(evt, TagCode, CustomerID);
        } else if (arg.equals("Back")) {
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
        RT_Panel = new RemoveTagPanel(CID, User);
        contentPane.add(RT_Panel);
        setVisible(true);
    }

}
