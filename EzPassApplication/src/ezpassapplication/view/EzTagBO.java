package ezpassapplication.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class EzTagPanel extends JPanel implements ActionListener {

    private JButton AddButton, RemoveButton;
    private JTextField CustomerIDField;
    private String CustomerID, Username;

    public EzTagPanel(String CID, String User) {
        //customerid is set to textfield. do not allow user to change it
        //customer has option to add tag or remove tag
        AddButton = new JButton("Add Tag");
        RemoveButton = new JButton("Remove Tag");
        CustomerID = CID;
        Username = User;
        CustomerIDField = new JTextField(15);
        CustomerIDField.setText(CustomerID);
        CustomerIDField.setEditable(false);

        JLabel CustomerIDLabel = new JLabel("CustomerID: ");

        JPanel CustomerIDPanel = new JPanel();
        JPanel button = new JPanel();

        CustomerIDPanel.add(CustomerIDLabel);
        CustomerIDPanel.add(CustomerIDField);
        button.add(AddButton);
        button.add(RemoveButton);

        AddButton.addActionListener(this);
        RemoveButton.addActionListener(this); //event listener registration

        JPanel CenterPanel = new JPanel();
        CenterPanel.add(CustomerIDPanel);
        CenterPanel.add(button);

        setLayout(new BorderLayout());
        add(CenterPanel, BorderLayout.CENTER);

    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        //Object source = evt.getSource(); //get who generates this event
        String arg = evt.getActionCommand();
        if (arg.equals("Add Tag")) { //if user wants to add tag, declare an add tag BO
            AddTagBO add = new AddTagBO(CustomerID,Username);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        } else if (arg.equals("Remove Tag")) { //if user wants to remove tag, declare an remove tag BO
            RemoveTagBO remove = new RemoveTagBO(CustomerID,Username);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        }
    }

}

public class EzTagBO extends JFrame {

    private EzTagPanel ET_Panel;

    public EzTagBO(String CID, String User) {
        setTitle("Add or Remove EzTag to Your Account");
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
        ET_Panel = new EzTagPanel(CID,User);
        contentPane.add(ET_Panel);
        setVisible(true);
    }

}
