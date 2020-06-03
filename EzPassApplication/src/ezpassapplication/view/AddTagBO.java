package ezpassapplication.view;

import ezpassapplication.control.AddTagControl;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.*;

class AddTagPanel extends JPanel implements ActionListener {

    private JButton SubmitButton, BackButton;
    private JTextField TagCodeField, TagTypeField, IssueDateField, CustomerIDField;
    private String TagCode, TagType, IssueDate, CustomerID, Username;

    public AddTagPanel(String CID, String User) {
        SubmitButton = new JButton("Submit"); //submit button
        BackButton = new JButton("Back"); //return to mainwindows
        CustomerID = CID; //set customer id
        Username = User;

        //Set up textfields
        //CustomerID passed to BO and IssueDate is set by date method so dont allow users to edit 
        TagCodeField = new JTextField(15);
        TagTypeField = new JTextField(15);
        IssueDateField = new JTextField(15);
        IssueDateField.setEditable(false);
        CustomerIDField = new JTextField(15);
        CustomerIDField.setText(CustomerID);
        CustomerIDField.setEditable(false);

        JLabel TagCodeLabel = new JLabel("Tag Code: ");
        JLabel TagTypeLabel = new JLabel("Tag Type: ");
        JLabel IssueDateLabel = new JLabel("IssueDate: ");
        JLabel CustomerIDLabel = new JLabel("CustomerID: ");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //date
        Date date = new Date(System.currentTimeMillis()); //get current time
        IssueDateField.setText(formatter.format(date)); //format and set text for textfield

        JPanel TagCodePanel = new JPanel();
        JPanel TagTypePanel = new JPanel();
        JPanel IssueDatePanel = new JPanel();
        JPanel CustomerIDPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();

        CustomerIDPanel.add(CustomerIDLabel);
        CustomerIDPanel.add(CustomerIDField);
        IssueDatePanel.add(IssueDateLabel);
        IssueDatePanel.add(IssueDateField);
        TagCodePanel.add(TagCodeLabel);
        TagCodePanel.add(TagCodeField);
        TagTypePanel.add(TagTypeLabel);
        TagTypePanel.add(TagTypeField);
        ButtonPanel.add(SubmitButton);
        ButtonPanel.add(BackButton);

        SubmitButton.addActionListener(this); //event listener registration
        BackButton.addActionListener(this);

        Box CenterPanel = Box.createVerticalBox();
        CenterPanel.add(CustomerIDPanel);
        CenterPanel.add(IssueDatePanel);
        CenterPanel.add(TagCodePanel);
        CenterPanel.add(TagTypePanel);
        CenterPanel.add(ButtonPanel);
        setLayout(new BorderLayout());
        add(CenterPanel, BorderLayout.NORTH);

    }

    public void actionPerformed(ActionEvent evt) //event handling
    {

        String arg = evt.getActionCommand();
        if (arg.equals("Submit")) {
            //get inputs and pass to tag control
            TagCode = TagCodeField.getText();
            TagType = TagTypeField.getText();
            IssueDate = IssueDateField.getText();
            AddTagControl CP_CTRL = new AddTagControl(evt, TagCode, TagType, IssueDate, CustomerID);
        } else if (arg.equals("Back")) {
            MainWindowsBO main = new MainWindowsBO(CustomerID, Username);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        }
    }

}

public class AddTagBO extends JFrame {

    private AddTagPanel AT_Panel;

    public AddTagBO(String CID, String User) {
        setTitle("Add Tag");
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
        AT_Panel = new AddTagPanel(CID, User);
        contentPane.add(AT_Panel);
        setVisible(true);
    }

}
