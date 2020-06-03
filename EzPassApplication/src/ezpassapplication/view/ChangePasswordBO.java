package ezpassapplication.view;

import ezpassapplication.control.ChangePasswordControl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ChangePasswordPanel extends JPanel implements ActionListener {

    private JButton ChangeButton, BackButton;
    private JTextField UsernameField, OldPassword, NewPassword, Retype;
    private String CustomerID, Username;

    public ChangePasswordPanel(String CID, String User) {
        ChangeButton = new JButton("Change"); //change button
        BackButton = new JButton("Back"); //change button
        CustomerID = CID;    //set customer id
        Username = User;
        
        //jlabels setup
        JLabel UsernameLabel = new JLabel("Username:");
        JLabel OldPWLabel = new JLabel("Enter Old Password:");
        JLabel NewPWLabel = new JLabel("Enter New Password:");
        JLabel REnterLabel = new JLabel("Re-Enter New Password:");
        
        //jtextfields, username was passed to BO so dont allow user to modify it
        UsernameField = new JTextField(15);
        UsernameField.setText(Username);
        UsernameField.setEditable(false);
        OldPassword = new JTextField(15);
        NewPassword = new JTextField(15);
        Retype = new JTextField(15);
        
        //jpanel setup, each jpanel consist of label and textfield
        JPanel UserPane = new JPanel();
        JPanel Old = new JPanel();
        JPanel New = new JPanel();
        JPanel Re = new JPanel();
        JPanel ButtonPanel = new JPanel();

        UserPane.add(UsernameLabel);
        UserPane.add(UsernameField);
        Old.add(OldPWLabel);
        Old.add(OldPassword);
        New.add(NewPWLabel);
        New.add(NewPassword);
        Re.add(REnterLabel);
        Re.add(Retype);
        ButtonPanel.add(ChangeButton);
        ButtonPanel.add(BackButton);
        
        ChangeButton.addActionListener(this); //event listener registration
        BackButton.addActionListener(this);
        
        //layout to north
        Box CenterPanel = Box.createVerticalBox();
        CenterPanel.add(UserPane);
        CenterPanel.add(Old);
        CenterPanel.add(New);
        CenterPanel.add(Re);
        CenterPanel.add(ButtonPanel);
        setLayout(new BorderLayout());
        add(CenterPanel, BorderLayout.NORTH);
        
    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        //Object source = evt.getSource(); //get who generates this event
        String arg = evt.getActionCommand();
        if (arg.equals("Change")) { 
            //get inputs and pass to change pass control
            String oldPW = OldPassword.getText();
            String newPW = NewPassword.getText();
            String newPW1 = Retype.getText();
            ChangePasswordControl CP_CTRL = new ChangePasswordControl(evt, Username, oldPW, newPW, newPW1);
            }
        else if (arg.equals("Back")) {
            MainWindowsBO main = new MainWindowsBO(CustomerID, Username);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        }
        }
    }



public class ChangePasswordBO extends JFrame {

    private ChangePasswordPanel CP_Panel;

    public ChangePasswordBO(String CID, String Username) {
        setTitle("Change Password");
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
        CP_Panel = new ChangePasswordPanel(CID, Username);
        contentPane.add(CP_Panel);
        setVisible(true);
    }

 
}
