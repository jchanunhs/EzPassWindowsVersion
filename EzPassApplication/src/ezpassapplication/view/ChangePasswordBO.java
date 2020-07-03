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

        ChangeButton = new JButton("Change");
        BackButton = new JButton("Back");
        CustomerID = CID;
        Username = User;

        //JLabels
        JLabel UsernameLabel = new JLabel("Username:");
        JLabel OldPWLabel = new JLabel("Enter Old Password:");
        JLabel NewPWLabel = new JLabel("Enter New Password:");
        JLabel REnterLabel = new JLabel("Re-Enter New Password:");

        //JTextFields
        UsernameField = new JTextField(15);
        UsernameField.setText(Username);
        UsernameField.setEditable(false);
        OldPassword = new JTextField(15);
        NewPassword = new JTextField(15);
        Retype = new JTextField(15);

        //JPanels
        JPanel UserPane = new JPanel();
        JPanel Old = new JPanel();
        JPanel New = new JPanel();
        JPanel Re = new JPanel();
        JPanel ButtonPanel = new JPanel();

        //Add Labels and TextFields to Panels
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

        //register event listener
        ChangeButton.addActionListener(this);
        BackButton.addActionListener(this);

        //Vertical design
        Box MainPanel = Box.createVerticalBox();
        MainPanel.add(UserPane);
        MainPanel.add(Old);
        MainPanel.add(New);
        MainPanel.add(Re);
        MainPanel.add(ButtonPanel);
        setLayout(new BorderLayout());
        add(MainPanel, BorderLayout.NORTH);

    }

    public void actionPerformed(ActionEvent evt) {
        String arg = evt.getActionCommand();
        if (arg.equals("Change")) { //get inputs and pass to control
            String oldPW = OldPassword.getText();
            String newPW = NewPassword.getText();
            String newPW1 = Retype.getText();
            if (oldPW.equals("") || newPW.equals("") || newPW1.equals("")) {
                JOptionPane.showMessageDialog(null, "One or more fields are empty! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
            } else if (!newPW.equals(newPW1)) { //check if password match
                JOptionPane.showMessageDialog(null, "Error: Unmatched new password!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                ChangePasswordControl CP_CTRL = new ChangePasswordControl(evt, Username, oldPW, newPW);
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
