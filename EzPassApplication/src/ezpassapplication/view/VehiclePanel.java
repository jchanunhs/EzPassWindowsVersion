package ezpassapplication.view;

import ezpassapplication.model.Vehicle;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class VehiclePanel extends JPanel implements ListSelectionListener, ActionListener {

    Vehicle vehicle;
    private String CustomerID, clickedString;

    public VehiclePanel(String CID) {
        CustomerID = CID; //set Customer ID
        selectedVehicle = new JTextField(14);
        selectedVehicle.setEditable(false);
        JLabel textFieldLabel = new JLabel("Selected Vehicle:");
        //p1 contains label and textfield for selected vehicle
        JPanel p1 = new JPanel();
        p1.add(textFieldLabel);
        p1.add(selectedVehicle);
        vehicle = new Vehicle(CustomerID); //vehicle can get information from CID

        //initializing a list
        VehicleList = new JList(vehicle.getVehicles()); //jlist contains vehicle list from Vehicle Object 
        VehicleList.setVisibleRowCount(5); //set the visible rows
        VehicleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JLabel listLabel = new JLabel("Vehicle List by license plate:");
        JPanel p2 = new JPanel();
        p2.add(listLabel);
        p2.add(VehicleList);

        //declare and initialize a SCROLL PANE
        JScrollPane listScroll = new JScrollPane(VehicleList);
        p2.add(listScroll);

        //Buttons for either adding vehicle or removing them from the table
        JPanel p3 = new JPanel();
        JButton add = new JButton("Add Vehicle");
        add.addActionListener(this);
        JButton remove = new JButton("Remove Vehicle");
        remove.addActionListener(this);
        p3.add(add);
        p3.add(remove);

        Box b = Box.createVerticalBox();
        b.add(p1);
        b.add(p2);
        b.add(p3);
        add(b);
        VehicleList.addListSelectionListener(this);
    }

    //method for ListSelectionEvent handling
    public void valueChanged(ListSelectionEvent evt) {
        JList source = (JList) evt.getSource();
        String tempString = (String) source.getSelectedValue();
        clickedString = tempString;
        selectedVehicle.setText(tempString);
    }

    private JTextField selectedVehicle;
    private JList VehicleList;

    @Override
    public void actionPerformed(ActionEvent evt) {
        String arg = evt.getActionCommand();
        if (arg.equals("Add Vehicle")) { //pass to AddVehicleBO to add vehicles
            AddVehicleBO user = new AddVehicleBO(CustomerID);
        } else if (arg.equals("Remove Vehicle")) { //remove vehicle based on what user clicked
            Vehicle vehicle = new Vehicle(CustomerID, clickedString);
            if (vehicle.removeVehicle()) {
                JOptionPane.showMessageDialog(null, "Remove vehicle success. Please logout to see changes!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Remove vehicle failed!", "Confirmation", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

class VehicleFrame extends JFrame {

    public VehicleFrame(String CID) {
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
        JPanel testVehiclePanel = new VehiclePanel(CID);
        Container contentPane = getContentPane(); //add a panel to a frame
        contentPane.add(testVehiclePanel);
    }

}
