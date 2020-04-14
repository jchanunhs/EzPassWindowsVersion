package ezpassapplication.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MainWindowsPanel extends JPanel {

    //main windows to show customer informations
    public MainWindowsPanel(String UName, String CID) {
        UserProfilePanel user = new UserProfilePanel(UName); //User Profile
        VehiclePanel vehicle = new VehiclePanel(CID);  //Display Vehicles
        PayTollPanel paytoll = new PayTollPanel(CID); // PayToll
        EzTagPanel eztag = new EzTagPanel(CID); //EZ tag
        TransactionPanel trans = new TransactionPanel(CID); //View transactions
        JTabbedPane mp = new JTabbedPane();

        //add panels to tabs
        mp.add("Profile", user);
        mp.add("Vehicles", vehicle);
        mp.add("Ez Tags", eztag);
        mp.add("Pay Tolls", paytoll);
        mp.add("Transaction", trans);
        //center the panel
        setLayout(new BorderLayout());
        add(mp, BorderLayout.CENTER);

    }

}

public class MainWindowsBO extends JFrame {

    private MainWindowsPanel CP_Panel;

    public MainWindowsBO(String UName, String CID) {
        setTitle("Main Windows");
        setSize(720, 720);

        setVisible(true);

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
        CP_Panel = new MainWindowsPanel(UName, CID);
        contentPane.add(CP_Panel);
        show();
    }

}
