package ezpassapplication;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/* old panel that uses jlist 

class TransactionPanel extends JPanel implements ActionListener {
Transaction transaction;
private String CustomerID;
private JTextField before, after;
private JButton getlist;

DefaultListModel holder; //connected to jlist
DefaultListModel temp; // temp to hold new jlist values to update jlist
    public TransactionPanel(String CID) {
        CustomerID = CID;
        transaction = new Transaction(CustomerID);
        holder = transaction.getAllTransactions(); //show all transactions for now
        temp = new DefaultListModel();
        getlist = new JButton("Populate list");
        getlist.addActionListener(this);
        
        JLabel beforelabel = new JLabel("Date from:");
        JLabel afterlabel = new JLabel("Date to:");
        
        before = new JTextField(15);
        after = new JTextField(15);
        
        JPanel beforepane = new JPanel();
        JPanel afterpane = new JPanel();
        
        beforepane.add(beforelabel);
        beforepane.add(before);
        afterpane.add(afterlabel);
        afterpane.add(after);
        afterpane.add(getlist);
        
        
        //initializing a list
        swingComponentList = new JList(holder);
        swingComponentList.setVisibleRowCount(5); //set the visible rows
        swingComponentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JLabel listLabel = new JLabel("Transaction List:");
        JPanel p2 = new JPanel();
        p2.add(listLabel);
        p2.add(swingComponentList);

        //declare and initialize a SCROLL PANE
        JScrollPane listScroll = new JScrollPane(swingComponentList);
        p2.add(listScroll);
        
        
        
        Box b = Box.createVerticalBox();
        b.add(beforepane);
        b.add(afterpane);
        b.add(p2);
        
        add(b);
      
    }
    
 */
class TransactionPanel extends JPanel implements ActionListener {

    Transaction transaction;
    private String CustomerID;
    private JTextField before, after;
    private JButton getlist;

    DefaultTableModel model = new DefaultTableModel();
    JTable table; //table
    String[] columnName = {"TransactionID", "TagCode", "TransactionDate", "TransactionTime", "TollAmount", "TollPlaza", "TollLaneNumber", "CustomerID"};

    public TransactionPanel(String CID) {
        CustomerID = CID;
        transaction = new Transaction(CustomerID);

        getlist = new JButton("Populate list"); //button
        getlist.addActionListener(this);

        JLabel beforelabel = new JLabel("Date from:");
        JLabel afterlabel = new JLabel("Date to:");

        before = new JTextField(15);
        after = new JTextField(15);
        
        //date panels
        JPanel beforepane = new JPanel();
        JPanel afterpane = new JPanel();
        beforepane.add(beforelabel);
        beforepane.add(before);
        afterpane.add(afterlabel);
        afterpane.add(after);
        afterpane.add(getlist);

        //populate table with all transactions as default
        model.setColumnIdentifiers(columnName); //column titles
        ResultSet Rslt = transaction.getAllTransactions(); //gets all transactions and display them as default
        try { //get all the values from the query
            while (Rslt.next()) {
                String TID = Rslt.getString("TransactionID");
                String TC = Rslt.getString("TagCode");
                String TDate = Rslt.getString("TransactionDate");
                String TTime = Rslt.getString("TransactionTime");
                String TAmt ="-"+ String.valueOf(Rslt.getString("TollAmount"));
                String TP = Rslt.getString("TollPlaza");
                String TLN = String.valueOf(Rslt.getInt("TollLaneNumber"));
                String CusID = Rslt.getString("CustomerID");
                model.addRow(new Object[]{TID, TC, TDate, TTime, TAmt, TP, TLN, CusID});
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                Rslt.close(); // close connection
                transaction.closeAllConn();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }

        //initializing a table and scroll pane
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(600, 600));

        JLabel listLabel = new JLabel("Transaction List");

        JPanel p1 = new JPanel(); //contains the label only
        JPanel p2 = new JPanel();//tables

        p1.add(listLabel);
        p2.add(scroll);

        Box b = Box.createVerticalBox();
        //dates
        b.add(beforepane);
        b.add(afterpane);
        //transaction list
        b.add(p1);
        b.add(p2);
        add(b);

    }

//private JList swingComponentList;
    @Override
    public void actionPerformed(ActionEvent evt) {
        String arg = evt.getActionCommand();
        model.setRowCount(0); //clear the table
        if (arg.equals("Populate list")) {
            ResultSet Rslt = transaction.getTransactions(before.getText(), after.getText()); //get transaction based on dates
            try {
                while (Rslt.next()) {
                    //get transaction information
                    String TID = Rslt.getString("TransactionID");
                    String TC = Rslt.getString("TagCode");
                    String TDate = Rslt.getString("TransactionDate");
                    String TTime = Rslt.getString("TransactionTime");
                    String TAmt = "-"+String.valueOf(Rslt.getString("TollAmount"));
                    String TP = Rslt.getString("TollPlaza");
                    String TLN = String.valueOf(Rslt.getInt("TollLaneNumber"));
                    String CusID = Rslt.getString("CustomerID");
                    model.addRow(new Object[]{TID, TC, TDate, TTime, TAmt, TP, TLN, CusID}); // add new rows to model
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                try {
                    Rslt.close(); //close connection
                    transaction.closeAllConn();
                } catch (SQLException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }

        }
    }
}

class TransactionFrame extends JFrame {

    public TransactionFrame(String CID) {
        setTitle("Your Transactions");
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
        JPanel testTransactionPanel = new TransactionPanel(CID);
        Container contentPane = getContentPane(); //add a panel to a frame
        contentPane.add(testTransactionPanel);
    }

}
