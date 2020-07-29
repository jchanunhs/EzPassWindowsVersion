package ezpassapplication.view;

import ezpassapplication.control.SignUpControl;
import ezpassapplication.model.Account;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SignUpPanel extends JPanel implements ActionListener {

    private JButton RegisterButton, LoginButton;
    private JTextField UsernameField, NameField;
    private JPasswordField PasswordField, PasswordField1;
    private String UName, PsWord, PsWord1, Name;
    private Account Acct;

    public SignUpPanel() {
        RegisterButton = new JButton("Register"); //initializing two button references

        UsernameField = new JTextField(15);
        PasswordField = new JPasswordField(15);
        PasswordField1 = new JPasswordField(15);
        NameField = new JTextField(15);

        JLabel UsernameLabel = new JLabel("Username: ");
        JLabel PasswordLabel = new JLabel("Password: ");
        JLabel PasswordLabel1 = new JLabel("Re-enter Password");
        JLabel NameLabel = new JLabel("Name");

        JPanel UsernamePanel = new JPanel();
        JPanel PasswordPanel = new JPanel();
        JPanel PasswordPanel1 = new JPanel();
        JPanel NamePanel = new JPanel();

        UsernamePanel.add(UsernameLabel);
        UsernamePanel.add(UsernameField);
        PasswordPanel.add(PasswordLabel);
        PasswordPanel.add(PasswordField);
        PasswordPanel1.add(PasswordLabel1);
        PasswordPanel1.add(PasswordField1);
        NamePanel.add(NameLabel);
        NamePanel.add(NameField);

        add(UsernamePanel);
        add(PasswordPanel);
        add(PasswordPanel1);
        add(NamePanel);

        add(RegisterButton);
        RegisterButton.addActionListener(this); //event listener registration
    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        //Object source = evt.getSource(); //get who generates this event
        String arg = evt.getActionCommand();
        if (arg.equals("Register")) {
            UName = UsernameField.getText(); //take actions
            PsWord = PasswordField.getText();
            PsWord1 = PasswordField1.getText();
            Name = NameField.getText();
            if (UName.equals("") || PsWord.equals("") || PsWord1.equals("") || Name.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
            } else if (!PsWord.equals(PsWord1)) { //check for invalid password matchup
                JOptionPane.showMessageDialog(null, "Account creation failed due to unmatched passwords", "Confirmation", JOptionPane.ERROR_MESSAGE);
            } else {
                SignUpControl SU_CTRL = new SignUpControl(evt, UName, PsWord, Name);
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
