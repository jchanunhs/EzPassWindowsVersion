package ezpassapplication.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class EzTagPanel extends JPanel implements ActionListener {

    private JButton AddButton, RemoveButton;
    private JTextField CustomerIDField;
    private String CustomerID, Username;

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
        JPanel button = new JPanel();

        CustomerIDPanel.add(CustomerIDLabel);
        CustomerIDPanel.add(CustomerIDField);
        button.add(AddButton);
        button.add(RemoveButton);

        //Register event listener
        AddButton.addActionListener(this);
        RemoveButton.addActionListener(this);

        //Center panel will contain all the other panels
        JPanel MainPanel = new JPanel();
        MainPanel.add(CustomerIDPanel);
        MainPanel.add(button);

        setLayout(new BorderLayout());
        add(MainPanel, BorderLayout.CENTER);

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
