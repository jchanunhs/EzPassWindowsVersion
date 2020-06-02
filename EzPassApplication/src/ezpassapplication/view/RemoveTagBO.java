package ezpassapplication.view;

import ezpassapplication.control.RemoveTagControl;
import java.awt.*;     
import java.awt.event.*;
import javax.swing.*;

class RemoveTagPanel extends JPanel implements ActionListener {

    private JButton OpenButton;
    private JTextField TagCodeField, CustomerIDField;
    private String TagCode, CustomerID;

    public RemoveTagPanel(String CID) {
        OpenButton = new JButton("Remove"); //remove tag

        CustomerID = CID; //set cid

        //user can enter tag code that they want removed
        TagCodeField = new JTextField(15);
        CustomerIDField = new JTextField(15);
        CustomerIDField.setText(CustomerID);
        CustomerIDField.setEditable(false); //customer id not allowed to be changed

        JLabel TagCodeLabel = new JLabel("Tag Code to remove: ");
        JLabel CustomerIDLabel = new JLabel("CustomerID: ");

        //set up panels
        JPanel TagCodePanel = new JPanel();
        JPanel CustomerIDPanel = new JPanel();

        //add labels and textfields
        CustomerIDPanel.add(CustomerIDLabel);
        CustomerIDPanel.add(CustomerIDField);

        TagCodePanel.add(TagCodeLabel);
        TagCodePanel.add(TagCodeField);

        OpenButton.addActionListener(this); //event listener registration

        JPanel CenterPanel = new JPanel();
        CenterPanel.add(CustomerIDPanel);
        CenterPanel.add(TagCodePanel);
        CenterPanel.add(OpenButton);
        setLayout(new BorderLayout());
        add(CenterPanel, BorderLayout.CENTER); //center the jpanel
        //add(OpenButton, BorderLayout.SOUTH);//add the one button on to this panel
    }

    public void actionPerformed(ActionEvent evt) //event handling
    {
        //Object source = evt.getSource(); //get who generates this event
        String arg = evt.getActionCommand();
        if (arg.equals("Remove")) {
            //take user input for tag code and send to control object

            TagCode = TagCodeField.getText();
            CustomerID = CustomerIDField.getText();

            RemoveTagControl CP_CTRL = new RemoveTagControl(evt, TagCode, CustomerID);

        }
    }

}

public class RemoveTagBO extends JFrame {

    private RemoveTagPanel CP_Panel;

    public RemoveTagBO(String CID) {
        setTitle("Renove Tag");
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
        CP_Panel = new RemoveTagPanel(CID);
        contentPane.add(CP_Panel);
        show();
    }

}
