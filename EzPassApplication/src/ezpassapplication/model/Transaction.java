package ezpassapplication.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Transaction {

    private String TransactionID;
    private String TagCode;
    private String TransactionDate;
    private String TransactionTime;
    private float TollAmount;
    private String TollPlaza;
    private int TollLaneNumber;
    private String CustomerID;

    //Constructor to add transaction
    public Transaction(String TCode, float TAmt, String TPlaza, int TLN, String CID) {
        TagCode = TCode;
        TollAmount = TAmt;
        TollPlaza = TPlaza;
        TollLaneNumber = TLN;
        CustomerID = CID;
    }

    //constructor to get transaction information by CID
    public Transaction(String CID) {
        CustomerID = CID;
    }

    public boolean recordTransaction() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection();
                Connection DBConn = ToDB.openConn();
                int trans_id = (int) (Math.random() * 1000000) + 1000000; //Id is 7 digits long
                TransactionID = String.valueOf(trans_id);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());
                TransactionDate = formatter.format(date);
                formatter = new SimpleDateFormat("HH:mm:ss");
                date = new Date(System.currentTimeMillis());
                TransactionTime = formatter.format(date);
                PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM [TangClass].[dbo].[Transaction] WHERE TransactionID = ?");
                Stmt.setString(1, TransactionID);
                ResultSet Rslt = Stmt.executeQuery(); //if transaction id does not exist, we are safe to add them to db
                done = !Rslt.next();
                if (done) {
                    Stmt = DBConn.prepareStatement("INSERT INTO [TangClass].[dbo].[Transaction](TransactionID, TagCode, TransactionDate, TransactionTime, TollAmount, TollPlaza, TollLaneNumber, CustomerID) VALUES (?,?,?,?,?,?,?,?)");
                    Stmt.setString(1, TransactionID);
                    Stmt.setString(2, TagCode);
                    Stmt.setString(3, TransactionDate);
                    Stmt.setString(4, TransactionTime);
                    Stmt.setFloat(5, TollAmount);
                    Stmt.setString(6, TollPlaza);
                    Stmt.setInt(7, TollLaneNumber);
                    Stmt.setString(8, CustomerID);
                    Stmt.executeUpdate();
                }
                Stmt.close();
                ToDB.closeConn();
            }
        } catch (java.sql.SQLException e) {
            done = false;
            System.out.println("SQLException: " + e);
            while (e != null) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e = e.getNextException();
                System.out.println("");
            }
        } catch (java.lang.Exception e) {
            done = false;
            System.out.println("Exception: " + e);
        }
        return done;
    }

    public ArrayList<String> getAllTransactions(String column_name) { //populate list with credit transactions
        ArrayList<String> list = new ArrayList<String>();
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM [TangClass].[dbo].[Transaction] WHERE CustomerID = ?");
            Stmt.setString(1, CustomerID);
            ResultSet Rslt = Stmt.executeQuery();
            while (Rslt.next()) {
                switch (column_name) {
                    case "TransactionID":
                        list.add(Rslt.getString("TransactionID"));
                        break;
                    case "TagCode":
                        list.add(Rslt.getString("TagCode"));
                        break;
                    case "TransactionDate":
                        list.add(Rslt.getString("TransactionDate"));
                        break;
                    case "TransactionTime":
                        list.add(Rslt.getString("TransactionTime"));
                        break;
                    case "TollPlaza":
                        list.add(Rslt.getString("TollPlaza"));
                        break;
                    case "TollLaneNumber":
                        list.add(Rslt.getString("TollLaneNumber"));
                        break;
                    case "TollAmount":
                        list.add(Rslt.getString("TollAmount"));
                        break;
                    default:
                        break;
                }
            }
            Stmt.close();
            ToDB.closeConn();
        } catch (java.sql.SQLException e) {
            System.out.println("SQLException: " + e);
            while (e != null) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e = e.getNextException();
                System.out.println("");
            }
        } catch (java.lang.Exception e) {
            System.out.println("Exception: " + e);
        }
        return list; //return list
    }

    public ArrayList<String> getTransactions(String before, String after, String column_name) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            DBConnection ToDB = new DBConnection(); //Have a connection to the DB
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM [TangClass].[dbo].[Transaction] WHERE CustomerID = ? AND TransactionDate BETWEEN ? AND ? ORDER BY 'TransactionDate','TransactionTime' ASC");
            Stmt.setString(1, CustomerID);
            Stmt.setString(2, before);
            Stmt.setString(3, after);
            ResultSet Rslt = Stmt.executeQuery(); //execute query to get transaction based on dates
            while (Rslt.next()) {
                switch (column_name) {
                    case "TransactionID":
                        list.add(Rslt.getString("TransactionID"));
                        break;
                    case "TagCode":
                        list.add(Rslt.getString("TagCode"));
                        break;
                    case "TransactionDate":
                        list.add(Rslt.getString("TransactionDate"));
                        break;
                    case "TransactionTime":
                        list.add(Rslt.getString("TransactionTime"));
                        break;
                    case "TollPlaza":
                        list.add(Rslt.getString("TollPlaza"));
                        break;
                    case "TollLaneNumber":
                        list.add(Rslt.getString("TollLaneNumber"));
                        break;
                    case "TollAmount":
                        list.add(Rslt.getString("TollAmount"));
                        break;
                    default:
                        break;
                }
            }
            Stmt.close();
            ToDB.closeConn();
        } catch (java.sql.SQLException e) {
            System.out.println("SQLException: " + e);
            while (e != null) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e = e.getNextException();
                System.out.println("");
            }
        } catch (java.lang.Exception e) {

            System.out.println("Exception: " + e);
        }
        return list; //returns the list
    }

    //return transaction id when user pays toll
    public String getTransactionID() {
        return TransactionID;
    }

}
