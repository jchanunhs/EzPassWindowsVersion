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
        CustomerID = CID;
        Username = User;

        JPanel CustomerIDPanel = new JPanel();
        JLabel CustomerIDLabel = new JLabel("CustomerID: ");
        CustomerIDField = new JTextField(15);
        CustomerIDField.setText(CustomerID);
        CustomerIDField.setEditable(false);
        CustomerIDPanel.add(CustomerIDLabel);
        CustomerIDPanel.add(CustomerIDField);

        JPanel ButtonPanel = new JPanel();
        AddButton = new JButton("Add Tag");
        RemoveButton = new JButton("Remove Tag");
        AddButton.addActionListener(this);
        RemoveButton.addActionListener(this);
        ButtonPanel.add(AddButton);
        ButtonPanel.add(RemoveButton);

        JPanel LabelPanel = new JPanel(new FlowLayout()); //center label for table
        JLabel ListLabel = new JLabel("Your Ez Tags");
        LabelPanel.add(ListLabel);

        JPanel EzListPanel = new JPanel();
        ez = new EzTag(CustomerID); //vehicle can get information from CID
        ArrayList<String> list_getEz = ez.getTags();
        list_model = new DefaultListModel();
        for (String object : list_getEz) { //add all vehicles to list model
            list_model.addElement(object);
        }
        EzList = new JList(list_model); //jlist contains list model that recieved all license number
        //declare and initialize a SCROLL PANE
        JScrollPane listScroll = new JScrollPane(EzList);
        listScroll.setPreferredSize(new Dimension(200, 200));
        EzListPanel.add(listScroll);

        //Center panel will contain all the other panels
        Box MainPanel = Box.createVerticalBox();
        MainPanel.add(CustomerIDPanel);
        MainPanel.add(ButtonPanel);
        //ez tag list
        MainPanel.add(LabelPanel);
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
