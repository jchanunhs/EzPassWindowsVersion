package ezpassapplication.view;

import ezpassapplication.control.ChangePasswordControl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ChangePasswordPanel extends JPanel implements ActionListener {

    private JButton OpenButton;
    private JTextField Username, OldPassword, NewPassword, Retype;
    private String CustomerID;

    public ChangePasswordPanel(String CID, String UName) {
        OpenButton = new JButton("Change"); //change button
        CustomerID = CID;    //set customer id
        
        //jlabels setup
        JLabel UNameLabel = new JLabel("Username:");
        JLabel OldPWLabel = new JLabel("Enter Old Password:");
        JLabel NewPWLabel = new JLabel("Enter New Password:");
        JLabel REnterLabel = new JLabel("Re-Enter New Password:");
        
        //jtextfields, username was passed to BO so dont allow user to modify it
        Username = new JTextField(15);
        Username.setText(UName);
        Username.setEditable(false);
        OldPassword = new JTextField(15);
        NewPassword = new JTextField(15);
        Retype = new JTextField(15);
        
        //jpanel setup, each jpanel consist of label and textfield
        JPanel User = new JPanel();
        JPanel Old = new JPanel();
        JPanel New = new JPanel();
        JPanel Re = new JPanel();

        User.add(UNameLabel);
        User.add(Username);
        Old.add(OldPWLabel);
        Old.add(OldPassword);
        New.add(NewPWLabel);
        New.add(NewPassword);
        Re.add(REnterLabel);
        Re.add(Retype);

        OpenButton.addActionListener(this); //event listener registration
        
        //layout to center 
        JPanel CenterPanel = new JPanel();
        CenterPanel.add(User);
        CenterPanel.add(Old);
        CenterPanel.add(New);
        CenterPanel.add(Re);
        CenterPanel.add(OpenButton);
        setLayout(new BorderLayout());
        add(CenterPanel, BorderLayout.CENTER);
        
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
            String UName = Username.getText();
            ChangePasswordControl CP_CTRL = new ChangePasswordControl(UName, oldPW, newPW, newPW1);
            }
        }
    }



public class ChangePasswordBO extends JFrame {

    private ChangePasswordPanel CP_Panel;

    public ChangePasswordBO(String CID, String UName) {
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
        CP_Panel = new ChangePasswordPanel(CID, UName);
        contentPane.add(CP_Panel);
        show();
    }

 
}
