package ezpassapplication;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.*;

class AddTagPanel extends JPanel implements ActionListener {

    private JButton OpenButton;
    private JTextField TagCodeField, TagTypeField, IssueDateField, CustomerIDField;
    private String TagCode, TagType, IssueDate, CustomerID;

    public AddTagPanel(String CID) {
        OpenButton = new JButton("Submit"); //submit button

        CustomerID = CID; //set customer id
        
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

        CustomerIDPanel.add(CustomerIDLabel);
        CustomerIDPanel.add(CustomerIDField);
        IssueDatePanel.add(IssueDateLabel);
        IssueDatePanel.add(IssueDateField);
        TagCodePanel.add(TagCodeLabel);
        TagCodePanel.add(TagCodeField);
        TagTypePanel.add(TagTypeLabel);
        TagTypePanel.add(TagTypeField);

        OpenButton.addActionListener(this); //event listener registration

        JPanel CenterPanel = new JPanel();
        CenterPanel.add(CustomerIDPanel);
        CenterPanel.add(IssueDatePanel);
        CenterPanel.add(TagCodePanel);
        CenterPanel.add(TagTypePanel);
        CenterPanel.add(OpenButton);
        setLayout(new BorderLayout());
        add(CenterPanel, BorderLayout.CENTER);
       
    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        
        String arg = evt.getActionCommand();
        if (arg.equals("Submit")) { 
            //get inputs and pass to tag control
            TagCode = TagCodeField.getText();
            TagType = TagTypeField.getText();
            IssueDate = IssueDateField.getText();
            CustomerID = CustomerIDField.getText();

            AddTagControl CP_CTRL = new AddTagControl(TagCode, TagType, IssueDate, CustomerID);

        }
    }

}

public class AddTagBO extends JFrame {

    private AddTagPanel CP_Panel;

    public AddTagBO(String CID) {
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
        CP_Panel = new AddTagPanel(CID);
        contentPane.add(CP_Panel);
        show();
    }

  
}
