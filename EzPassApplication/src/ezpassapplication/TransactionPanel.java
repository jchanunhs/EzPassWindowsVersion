package ezpassapplication;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class TransactionPanel extends JPanel implements ActionListener {

    Transaction transaction;
    private String CustomerID;
    private JTextField before, after;
    private JButton getlist;

    private DefaultTableModel model = new DefaultTableModel();
    private JTable table; //table
    private String[] columnName = {"TransactionID", "TagCode", "TransactionDate", "TransactionTime", "TollPlaza", "TollLaneNumber", "TollAmount"};

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
        ArrayList<String> TID_list = transaction.getAllTransactions("TransactionID");
        ArrayList<String> TC_list = transaction.getAllTransactions("TagCode");
        ArrayList<String> TD_list = transaction.getAllTransactions("TransactionDate");
        ArrayList<String> TT_list = transaction.getAllTransactions("TransactionTime");
        ArrayList<String> TP_list = transaction.getAllTransactions("TollPlaza");
        ArrayList<String> TLN_list = transaction.getAllTransactions("TollLaneNumber");
        ArrayList<String> TA_list = transaction.getAllTransactions("TollAmount");

        for (int i = 0; i < TID_list.size(); i++) {
            String TID = TID_list.get(i);
            String TC = TC_list.get(i);
            String TDate = TD_list.get(i);
            String TTime = TT_list.get(i);
            String TP = TP_list.get(i);
            String TLN = TLN_list.get(i);
            String TAmt = "-" + TA_list.get(i);
            model.addRow(new Object[]{TID, TC, TDate, TTime, TP, TLN, TAmt});

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
        if (arg.equals("Populate list")) { //populate list based on date
            ArrayList<String> TID_list = transaction.getTransactions(before.getText(), after.getText(), "TransactionID");
            ArrayList<String> TC_list = transaction.getTransactions(before.getText(), after.getText(), "TagCode");
            ArrayList<String> TD_list = transaction.getTransactions(before.getText(), after.getText(), "TransactionDate");
            ArrayList<String> TT_list = transaction.getTransactions(before.getText(), after.getText(), "TransactionTime");
            ArrayList<String> TP_list = transaction.getTransactions(before.getText(), after.getText(), "TollPlaza");
            ArrayList<String> TLN_list = transaction.getTransactions(before.getText(), after.getText(), "TollLaneNumber");
            ArrayList<String> TA_list = transaction.getTransactions(before.getText(), after.getText(), "TollAmount");

            for (int i = 0; i < TID_list.size(); i++) {
                String TID = TID_list.get(i);
                String TC = TC_list.get(i);
                String TDate = TD_list.get(i);
                String TTime = TT_list.get(i);
                String TP = TP_list.get(i);
                String TLN = TLN_list.get(i);
                String TAmt = "-" + TA_list.get(i);
                model.addRow(new Object[]{TID, TC, TDate, TTime, TP, TLN, TAmt});

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
