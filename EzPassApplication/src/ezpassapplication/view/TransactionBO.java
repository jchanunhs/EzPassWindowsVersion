package ezpassapplication.view;

import ezpassapplication.dao.TransactionDAO;
import ezpassapplication.entity.Transaction;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class TransactionPanel extends JPanel implements ActionListener {

    private TransactionDAO transactiondao; //access dao object to fetch customers transactions
    ArrayList<Transaction> transactionlist; // arraylist with transactions
    private String CustomerID, Username;
    private JTextField BeforeText, AfterText; //date from - date to
    private JButton GetListButton;
    private DefaultTableModel model = new DefaultTableModel();
    private JTable table;
    private String[] columnName = {"TransactionID", "TagCode", "TransactionDate", "TransactionTime", "TollPlaza", "TollLaneNumber", "TollAmount"};

    public TransactionPanel(String CID, String User) {
        CustomerID = CID;
        Username = User;

        JPanel BeforePanel = new JPanel();
        JLabel BeforeLabel = new JLabel("Date from:");
        BeforeText = new JTextField(15);
        BeforePanel.add(BeforeLabel);
        BeforePanel.add(BeforeText);

        JPanel AfterPanel = new JPanel();
        JLabel AfterLabel = new JLabel("Date to:");
        AfterText = new JTextField(15);
        AfterPanel.add(AfterLabel);
        AfterPanel.add(AfterText);

        JPanel ButtonPanel = new JPanel();
        GetListButton = new JButton("Populate list");
        GetListButton.addActionListener(this);
        ButtonPanel.add(GetListButton);

        JPanel LabelPanel = new JPanel(new FlowLayout()); //center label for table
        JLabel ListLabel = new JLabel("Transaction List");
        LabelPanel.add(ListLabel);

        JPanel TransactionListTable = new JPanel();
        transactiondao = new TransactionDAO();
        transactionlist = transactiondao.getAllTransactions(CustomerID);
        //populate table with all transactions as default
        model.setColumnIdentifiers(columnName); //column titles
        for (Transaction transaction : transactionlist) {
            model.addRow(new Object[]{transaction.getTransactionID(), transaction.getTagCode(), transaction.getTransactionDate(), transaction.getTransactionTime(), transaction.getTollPlaza(), transaction.getTollLaneNumber(), transaction.getTollAmount()});
        }
        //initializing a table and scroll panel
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(700, 550));
        TransactionListTable.add(scroll);

        Box MainPanel = Box.createVerticalBox();
        //date inputs
        MainPanel.add(BeforePanel);
        MainPanel.add(AfterPanel);
        MainPanel.add(ButtonPanel);
        //transaction list
        MainPanel.add(LabelPanel);
        MainPanel.add(TransactionListTable);
        setLayout(new BorderLayout());
        add(MainPanel, BorderLayout.NORTH);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String arg = evt.getActionCommand();
        model.setRowCount(0); //clear the table
        if (arg.equals("Populate list")) { //populate list based on date
            if (!BeforeText.getText().isEmpty() || !AfterText.getText().isEmpty()) {
                transactionlist = transactiondao.getTransactions(CustomerID, BeforeText.getText(), AfterText.getText());
            } else { //if the user enters empty BeforeText and AfterText text, populate with original data
                transactionlist = transactiondao.getAllTransactions(CustomerID);
            }
            //populate table with all transactions as default
            for (Transaction transaction : transactionlist) {
                model.addRow(new Object[]{transaction.getTransactionID(), transaction.getTagCode(), transaction.getTransactionDate(), transaction.getTransactionTime(), transaction.getTollPlaza(), transaction.getTollLaneNumber(), transaction.getTollAmount()});
            }
        }
    }
}

public class TransactionBO extends JFrame {

    TransactionPanel Trans_Panel;

    public TransactionBO(String CID, String User) {
        setTitle("Your Transactions");
        Trans_Panel = new TransactionPanel(CID, User);
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

        Container contentPane = getContentPane(); //add a panel to a frame
        contentPane.add(Trans_Panel);
        setVisible(true);
    }

}
