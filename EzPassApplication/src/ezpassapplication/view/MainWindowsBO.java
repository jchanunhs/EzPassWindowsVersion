package ezpassapplication.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MainWindowsPanel extends JPanel {

    //main windows to show customer informations
    public MainWindowsPanel(String CID,String User) {
        UserProfilePanel user = new UserProfilePanel(CID,User); //User Profile
        VehiclePanel vehicle = new VehiclePanel(CID,User);  //Display Vehicles
        EzTagPanel eztag = new EzTagPanel(CID,User); //EZ tag
        PayTollPanel paytoll = new PayTollPanel(CID,User); // PayToll
        TransactionPanel trans = new TransactionPanel(CID,User); //View transactions
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

    private MainWindowsPanel MW_Panel;

    public MainWindowsBO(String CID,String User) {
        setTitle("Main Windows");
        setSize(630, 800);

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
        MW_Panel = new MainWindowsPanel(CID,User);
        contentPane.add(MW_Panel);
        setVisible(true);
    }

}
