package ezpassapplication.view;

import ezpassapplication.control.ChangePasswordControl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ChangePasswordPanel extends JPanel implements ActionListener {

    private JButton ChangeButton, BackButton;
    private JTextField UsernameField;
    private JPasswordField OldPasswordField, NewPasswordField, RetypeField;
    private String CustomerID, Username, OldPassword, NewPassword, NewPassword1;

    public ChangePasswordPanel(String CID, String User) {

        ChangeButton = new JButton("Change");
        BackButton = new JButton("Back");
        CustomerID = CID;
        Username = User;

        JPanel UsernamePanel = new JPanel();
        JLabel UsernameLabel = new JLabel("Username:");
        UsernameField = new JTextField(15);
        UsernameField.setText(Username);
        UsernameField.setEditable(false);
        UsernamePanel.add(UsernameLabel);
        UsernamePanel.add(UsernameField);

        JPanel OldPasswordPanel = new JPanel();
        JLabel OldPasswordLabel = new JLabel("Enter Old Password:");
        OldPasswordField = new JPasswordField(15);
        OldPasswordPanel.add(OldPasswordLabel);
        OldPasswordPanel.add(OldPasswordField);

        JPanel NewPasswordPanel = new JPanel();
        JLabel NewPasswordLabel = new JLabel("Enter New Password:");
        NewPasswordField = new JPasswordField(15);
        NewPasswordPanel.add(NewPasswordLabel);
        NewPasswordPanel.add(NewPasswordField);

        JPanel ReEnterPasswordPanel = new JPanel();
        JLabel ReEnterLabel = new JLabel("Re-Enter New Password:");
        RetypeField = new JPasswordField(15);
        ReEnterPasswordPanel.add(ReEnterLabel);
        ReEnterPasswordPanel.add(RetypeField);

        JPanel ButtonPanel = new JPanel();
        ChangeButton = new JButton("Change");
        BackButton = new JButton("Back");
        ChangeButton.addActionListener(this);
        BackButton.addActionListener(this);
        ButtonPanel.add(ChangeButton);
        ButtonPanel.add(BackButton);

        //Vertical design
        Box MainPanel = Box.createVerticalBox();
        MainPanel.add(UsernamePanel);
        MainPanel.add(OldPasswordPanel);
        MainPanel.add(NewPasswordPanel);
        MainPanel.add(ReEnterPasswordPanel);
        MainPanel.add(ButtonPanel);
        setLayout(new BorderLayout());
        add(MainPanel, BorderLayout.NORTH);

    }

    public void actionPerformed(ActionEvent evt) {
        String arg = evt.getActionCommand();
        if (arg.equals("Change")) { //get inputs and pass to control
            OldPassword = OldPasswordField.getText();
            NewPassword = NewPasswordField.getText();
            NewPassword1 = RetypeField.getText();
            if (OldPassword.isEmpty() || NewPassword.isEmpty() || NewPassword1.isEmpty()) {
                JOptionPane.showMessageDialog(null, "One or more fields are empty! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
            } else if (!NewPassword.equals(NewPassword1)) { //check if password match
                JOptionPane.showMessageDialog(null, "Error: Unmatched new password!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                ChangePasswordControl CP_CTRL = new ChangePasswordControl(evt, Username, OldPassword, NewPassword);
            }
        } else if (arg.equals("Back")) { //return to mainwindows and close change password window
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
        CP_Panel = new ChangePasswordPanel(CID, Username);
        contentPane.add(CP_Panel);
        setVisible(true);
    }

}
