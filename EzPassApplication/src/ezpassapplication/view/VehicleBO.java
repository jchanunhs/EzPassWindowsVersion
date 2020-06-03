package ezpassapplication.view;

import ezpassapplication.model.Vehicle;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class VehiclePanel extends JPanel implements ListSelectionListener, ActionListener {

    private Vehicle vehicle;
    private String CustomerID, clickedString, Username;
    private int clickedInt;

    public VehiclePanel(String CID, String User) {
        CustomerID = CID;
        Username = User;
        selectedVehicle = new JTextField(15);
        selectedVehicle.setEditable(false);
        JLabel textFieldLabel = new JLabel("Selected Vehicle:");
        //selectedVehiclePanel contains label and textfield for selected vehicle
        JPanel selectedVehiclePanel = new JPanel();
        selectedVehiclePanel.add(textFieldLabel);
        selectedVehiclePanel.add(selectedVehicle);

        vehicle = new Vehicle(CustomerID); //vehicle can get information from CID
        //initializing a list
        VehicleList = new JList(vehicle.getVehicles()); //jlist contains vehicle list from Vehicle Object 
        VehicleList.setVisibleRowCount(5); //set the visible rows
        VehicleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        VehicleList.addListSelectionListener(this);
        
        JPanel vehicleListPanel = new JPanel();
        vehicleListPanel.add(VehicleList);

        //declare and initialize a SCROLL PANE
        JScrollPane listScroll = new JScrollPane(VehicleList);
        listScroll.setPreferredSize(new Dimension(110, 200));
        vehicleListPanel.add(listScroll);

        //Buttons for either adding vehicle or removing them from the table
        JPanel ButtonPanel = new JPanel();
        JButton add = new JButton("Add Vehicle");
        add.addActionListener(this);
        JButton remove = new JButton("Remove Vehicle");
        remove.addActionListener(this);
        ButtonPanel.add(add);
        ButtonPanel.add(remove);
        
        JLabel list_label = new JLabel("Vehicle List");
        JPanel label_pane = new JPanel(new FlowLayout()); //center label for table
        label_pane.add(list_label);

        Box MainPanel = Box.createVerticalBox();
        MainPanel.add(selectedVehiclePanel);
        MainPanel.add(label_pane); // list label will be on top of the vehicle list 
        MainPanel.add(vehicleListPanel);
        MainPanel.add(ButtonPanel);
        setLayout(new BorderLayout());
        add(MainPanel, BorderLayout.NORTH);

    }

    //method for ListSelectionEvent handling
    public void valueChanged(ListSelectionEvent evt) {
        JList source = (JList) evt.getSource();
        String tempString = (String) source.getSelectedValue();
        int tempInt = source.getSelectedIndex();
        clickedString = tempString;
        clickedInt = tempInt;
        selectedVehicle.setText(tempString);
    }

    private JTextField selectedVehicle;
    private JList VehicleList;

    @Override
    public void actionPerformed(ActionEvent evt) {
        String arg = evt.getActionCommand();
        if (arg.equals("Add Vehicle")) { //open add vehicle window and close vehicle window
            AddVehicleBO user = new AddVehicleBO(CustomerID, Username);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        } else if (arg.equals("Remove Vehicle")) { //remove vehicle based on what user clicked from list
            Vehicle vehicle = new Vehicle(CustomerID, clickedString);
            if (vehicle.removeVehicle()) {
                JOptionPane.showMessageDialog(null, "Remove vehicle success. Please logout to see changes!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                ((DefaultListModel) VehicleList.getModel()).remove(clickedInt);
            } else {
                JOptionPane.showMessageDialog(null, "Remove vehicle failed!", "Confirmation", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

public class VehicleBO extends JFrame {

    public VehicleBO(String CID, String User) {
        setTitle("Your Vehicles");
        setSize(480, 240);
        //get screen size and set the location of the frame
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        setLocation(screenWidth / 2 - 180, screenHeight / 4);

        addWindowListener(new WindowAdapter() //handle window closing event
        {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        JPanel testVehiclePanel = new VehiclePanel(CID, User);
        Container contentPane = getContentPane(); //add a panel to a frame
        contentPane.add(testVehiclePanel);
    }

}
