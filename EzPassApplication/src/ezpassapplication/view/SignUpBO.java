package ezpassapplication.view;

import ezpassapplication.control.SignUpControl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SignUpPanel extends JPanel implements ActionListener {

    private JButton RegisterButton, LoginButton;
    private JTextField UsernameField;
    private JPasswordField PasswordField, PasswordField1;
    private String Username, Password, Password1;

    public SignUpPanel() {
        RegisterButton = new JButton("Register"); //initializing two button references
        
        JPanel UsernamePanel = new JPanel();
        JLabel UsernameLabel = new JLabel("Username: ");
        UsernameField = new JTextField(15);
        UsernamePanel.add(UsernameLabel);
        UsernamePanel.add(UsernameField);
        
        JPanel PasswordPanel = new JPanel();
        JLabel PasswordLabel = new JLabel("Password: ");
        PasswordField = new JPasswordField(15);
        PasswordPanel.add(PasswordLabel);
        PasswordPanel.add(PasswordField);
        
        JPanel PasswordPanel1 = new JPanel();
        JLabel PasswordLabel1 = new JLabel("Re-enter Password");
        PasswordField1 = new JPasswordField(15);
        PasswordPanel1.add(PasswordLabel1);
        PasswordPanel1.add(PasswordField1);
        
        add(UsernamePanel);
        add(PasswordPanel);
        add(PasswordPanel1);

        add(RegisterButton);
        RegisterButton.addActionListener(this); //event listener registration
    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        //Object source = evt.getSource(); //get who generates this event
        String arg = evt.getActionCommand();
        if (arg.equals("Register")) {
            Username = UsernameField.getText(); //take actions
            Password = PasswordField.getText();
            Password1 = PasswordField1.getText();
            
            if (Username.isEmpty() || Password.isEmpty() || Password1.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
            } else if (!Password.equals(Password1)) { //check for invalid password matchup
                JOptionPane.showMessageDialog(null, "Account creation failed due to unmatched passwords", "Confirmation", JOptionPane.ERROR_MESSAGE);
            } else {
                SignUpControl SU_CTRL = new SignUpControl(evt, Username, Password);
            }

        }
    }

}

public class SignUpBO extends JFrame {

    private SignUpPanel SU_Panel;

    public SignUpBO() {
        setTitle("Sign Up");
        setSize(400, 400);

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
        SU_Panel = new SignUpPanel();
        contentPane.add(SU_Panel);
        setVisible(true);
    }

}
