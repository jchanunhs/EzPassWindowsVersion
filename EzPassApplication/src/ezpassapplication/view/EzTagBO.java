package ezpassapplication.view;

import ezpassapplication.model.EzTag;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class EzTagPanel extends JPanel implements ActionListener {

    private EzTag ez;
    private JButton AddButton, RemoveButton;
    private JTextField CustomerIDField;
    private String CustomerID, Username;
    private JList EzList;
    private DefaultListModel list_model; //list model for EzTagList

    public EzTagPanel(String CID, String User) {

        AddButton = new JButton("Add Tag");
        RemoveButton = new JButton("Remove Tag");
        CustomerID = CID;
        Username = User;

        //Display Customer ID
        CustomerIDField = new JTextField(15);
        CustomerIDField.setText(CustomerID);
        CustomerIDField.setEditable(false);
        JLabel CustomerIDLabel = new JLabel("CustomerID: ");
        
        JPanel CustomerIDPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();

        CustomerIDPanel.add(CustomerIDLabel);
        CustomerIDPanel.add(CustomerIDField);
        ButtonPanel.add(AddButton);
        ButtonPanel.add(RemoveButton);

        //Register event listener
        AddButton.addActionListener(this);
        RemoveButton.addActionListener(this);
        
        ez = new EzTag(CustomerID); //vehicle can get information from CID
        ArrayList<String> list_getEz = ez.getTags();
        list_model = new DefaultListModel();
        for (String object : list_getEz) { //add all vehicles to list model
            list_model.addElement(object);
        }
        //initializing a list
        EzList = new JList(list_model); //jlist contains list model that recieved all license number
        EzList.setVisibleRowCount(5); //set the visible rows

        JPanel EzListPanel = new JPanel();
        EzListPanel.add(EzList);

        //declare and initialize a SCROLL PANE
        JScrollPane listScroll = new JScrollPane(EzList);
        listScroll.setPreferredSize(new Dimension(110, 200));
        EzListPanel.add(listScroll);
        
        JLabel list_label = new JLabel("Your Ez Tags");
        JPanel label_pane = new JPanel(new FlowLayout()); //center label for table
        label_pane.add(list_label);

        //Center panel will contain all the other panels
        Box MainPanel = Box.createVerticalBox();
        MainPanel.add(CustomerIDPanel);
        MainPanel.add(ButtonPanel);
        MainPanel.add(label_pane);
        MainPanel.add(EzListPanel);

        setLayout(new BorderLayout());
        add(MainPanel, BorderLayout.NORTH);

    }

    public void actionPerformed(ActionEvent evt) {
        String arg = evt.getActionCommand();
        if (arg.equals("Add Tag")) { //open add tag window and close eztag window
            AddTagBO add = new AddTagBO(CustomerID, Username);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        } else if (arg.equals("Remove Tag")) { //open remove tag window and close eztag window
            RemoveTagBO remove = new RemoveTagBO(CustomerID, Username);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        }
    }

}

public class EzTagBO extends JFrame {

    private EzTagPanel ET_Panel;

    public EzTagBO(String CID, String User) {
        setTitle("Add or Remove EzTag to Your Account");
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
        ET_Panel = new EzTagPanel(CID, User);
        contentPane.add(ET_Panel);
        setVisible(true);
    }

}
