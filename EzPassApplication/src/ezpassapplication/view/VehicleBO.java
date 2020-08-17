package ezpassapplication.view;

import ezpassapplication.dao.VehicleDAO;
import ezpassapplication.entity.Vehicle;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

class VehiclePanel extends JPanel implements ListSelectionListener, ActionListener {

    private VehicleDAO vehicledao;
    private String CustomerID, clickedString, Username;
    private int clickedInt;
    private JTextField SelectedVehicle;
    private JList VehicleList;
    private DefaultListModel list_model; //list model for VehicleList

    public VehiclePanel(String CID, String User) {
        CustomerID = CID;
        Username = User;

        JPanel SelectedVehiclePanel = new JPanel();
        JLabel SelectedVehicleLabel = new JLabel("Selected Vehicle:");
        SelectedVehicle = new JTextField(15);
        SelectedVehicle.setEditable(false);
        SelectedVehiclePanel.add(SelectedVehicleLabel);
        SelectedVehiclePanel.add(SelectedVehicle);

        JPanel LabelPanel = new JPanel(new FlowLayout()); //center label for table
        JLabel ListLabel = new JLabel("Vehicle List");
        LabelPanel.add(ListLabel);

        JPanel VehicleListPanel = new JPanel();
        //get all vehicle information and populate list with only license plate number
        vehicledao = new VehicleDAO();
        ArrayList<Vehicle> vehiclelist = vehicledao.getAllCustomerVehicles(CustomerID);
        list_model = new DefaultListModel();
        for (Vehicle vehicle : vehiclelist) { //add all vehicles to list model
            list_model.addElement(vehicle.getLicensePlateNumber());
        }
        //initializing a list
        VehicleList = new JList(list_model); //jlist contains list model that recieved all license number
        VehicleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        VehicleList.addListSelectionListener(this);
        //declare and initialize a SCROLL PANE
        JScrollPane listScroll = new JScrollPane(VehicleList);
        listScroll.setPreferredSize(new Dimension(200, 200));
        VehicleListPanel.add(listScroll);

        JPanel ButtonPanel = new JPanel();
        JButton AddVehicleButton = new JButton("Add Vehicle");
        JButton RemoveVehicleButton = new JButton("Remove Vehicle");
        AddVehicleButton.addActionListener(this);
        RemoveVehicleButton.addActionListener(this);
        ButtonPanel.add(AddVehicleButton);
        ButtonPanel.add(RemoveVehicleButton);

        Box MainPanel = Box.createVerticalBox();
        MainPanel.add(SelectedVehiclePanel);
        //vehicle list
        MainPanel.add(LabelPanel); // list label will be on top of the vehicle list 
        MainPanel.add(VehicleListPanel);
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
        SelectedVehicle.setText(tempString);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String arg = evt.getActionCommand();
        if (arg.equals("Add Vehicle")) { //open add vehicle window and close vehicle window
            AddVehicleBO user = new AddVehicleBO(CustomerID, Username);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        } else if (arg.equals("Remove Vehicle")) { //remove vehicle based on what user clicked from list
            Vehicle vehicle = new Vehicle();
            vehicle.setLicensePlateNumber(clickedString);
            vehicle.setCustomerID(CustomerID);
            if (vehicledao.removeVehicle(vehicle)) {
                JOptionPane.showMessageDialog(null, "Vehicle was removed successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                list_model.remove(clickedInt);

            } else {
                JOptionPane.showMessageDialog(null, "Error: Something went wrong when removing vehicle. If this occurs multiple times please contact help desk.", "Confirmation", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

public class VehicleBO extends JFrame {

    public VehicleBO(String CID, String User) {
        setTitle("Your Vehicles");
        setSize(800, 800);
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
