package ezpassapplication;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class Transaction {

    private String TransactionID;
    private String TagCode;
    private String TransactionDate;
    private String TransactionTime;
    private float TollAmount;
    private String TollPlaza;
    private int TollLaneNumber;
    private String CustomerID;
    private DBConnection ToDB = null;
    private Connection DBConn = null;
    //Constructor to add transaction
    Transaction(String TCode, float TAmt, String TPlaza, int TLN, String CID) {
        TagCode = TCode;
        TollAmount = TAmt;
        TollPlaza = TPlaza;
        TollLaneNumber = TLN;
        CustomerID = CID;
    }

    //constructor to get transaction information by CID
    Transaction(String CID) {
        CustomerID = CID;
    }

    public boolean recordTransaction() {
        boolean done = false;
        try {
            if (!done) {
                ToDB = new DBConnection(); //Have a connection to the DB
                DBConn = ToDB.openConn();
                Statement Stmt = DBConn.createStatement();
                int trans_id = (int) (Math.random() * 1000000) + 1000000; //Id is 7 digits long
                TransactionID = String.valueOf(trans_id);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());
                TransactionDate = formatter.format(date);
                formatter = new SimpleDateFormat("HH:mm:ss");
                date = new Date(System.currentTimeMillis());
                TransactionTime = formatter.format(date);
                
                String SQL_Command = "SELECT * FROM [TangClass].[dbo].[Transaction] WHERE TransactionID = '" + TransactionID + "'"; //SQL query command
                ResultSet Rslt = Stmt.executeQuery(SQL_Command); //if transaction id does not exist, we are safe to add them to db
                done = !Rslt.next();
                if (done) {
                    SQL_Command = "INSERT INTO [TangClass].[dbo].[Transaction](TransactionID, TagCode, TransactionDate, TransactionTime, TollAmount, TollPlaza, TollLaneNumber, CustomerID)"
                            + " VALUES ('" + TransactionID + "', '" + TagCode + "', '" + TransactionDate + "', '" + TransactionTime + "', " + TollAmount + ", '" + TollPlaza + "', " + TollLaneNumber + ", '" + CustomerID + "'" + ")";

                    Stmt.executeUpdate(SQL_Command);
                    done = true;
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
            e.printStackTrace();
        }
        return done;
    }

    public ResultSet getTransactions(String before, String after) {
        ResultSet Rslt = null;
        try {

            ToDB = new DBConnection(); //Have a connection to the DB
            DBConn = ToDB.openConn();
            Statement Stmt = DBConn.createStatement();
            String SQL_Command = "SELECT * FROM [TangClass].[dbo].[Transaction] WHERE CustomerID = '" + CustomerID + "'"
                    + " AND TransactionDate BETWEEN '" + before + "' AND '" + after + "'"
                    + "ORDER BY 'TransactionDate','TransactionTime' ASC";
            Rslt = Stmt.executeQuery(SQL_Command); //execute query to get transaction based on dates

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
            e.printStackTrace();
        }
        return Rslt; //returns the executed query rows
    }

    //to close connection when returning resultset
    public ResultSet getAllTransactions() {
        ResultSet Rslt = null;
        try {

            ToDB = new DBConnection(); //Have a connection to the DB
            DBConn = ToDB.openConn();
            Statement Stmt = DBConn.createStatement();
            String SQL_Command = "SELECT * FROM [TangClass].[dbo].[Transaction] WHERE CustomerID = '" + CustomerID + "' ORDER BY 'TransactionDate','TransactionTime' ASC";
            Rslt = Stmt.executeQuery(SQL_Command); //get all transaction from the customer id

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
            e.printStackTrace();
        }
        return Rslt;
    }
    
    public void closeAllConn() throws SQLException {
        DBConn.close();
        ToDB.closeConn();

    }

}


