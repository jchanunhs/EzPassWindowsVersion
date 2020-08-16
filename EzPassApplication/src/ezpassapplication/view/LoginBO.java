package ezpassapplication.view;

import ezpassapplication.control.LoginControl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginBO extends JFrame implements ActionListener {

    private JButton SignUpButton, LoginButton;  //Instance variables
    private JTextField UsernameField;
    private JPasswordField PasswordField;

    public LoginBO() {
        setTitle("Login");
        setSize(300, 200);

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

        //user can choose to make new account or login with existing account
        SignUpButton = new JButton("Sign Up");
        LoginButton = new JButton("Login");

        UsernameField = new JTextField(15);
        PasswordField = new JPasswordField(15);
        PasswordField.setActionCommand("Login");

        //labels
        JLabel FirstTimeUserLabel = new JLabel("First time user? Click Sign Up to register!");
        JLabel UsernameLabel = new JLabel("Username: ");
        JLabel PasswordLabel = new JLabel("Password: ");
        //panels
        JPanel UsernamePanel = new JPanel();
        JPanel PasswordPanel = new JPanel();

        UsernamePanel.add(UsernameLabel);
        UsernamePanel.add(UsernameField);
        PasswordPanel.add(PasswordLabel);
        PasswordPanel.add(PasswordField);

        JPanel LoginPanel = new JPanel();
        LoginPanel.add(UsernamePanel);
        LoginPanel.add(PasswordPanel);

        LoginPanel.add(LoginButton);
        LoginPanel.add(FirstTimeUserLabel);
        LoginPanel.add(SignUpButton);

        SignUpButton.addActionListener(this);
        LoginButton.addActionListener(this);
        PasswordField.addActionListener(this);

        Container contentPane = getContentPane(); //add a panel to a frame
        contentPane.add(LoginPanel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        String arg = evt.getActionCommand();
        if (arg.equals("Sign Up")) {
            SignUpBO SUC = new SignUpBO();//open signup and close login
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        }
        if (arg.equals("Login")) {
            String Username = UsernameField.getText();
            String Password = PasswordField.getText();
            if (Username.isEmpty() || Password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "One or more fields are empty! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
            } else {
                LoginControl LoginC = new LoginControl(evt, Username, Password);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new LoginBO(); //initialize a JFrame object
    }
}
