package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
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
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
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

    public ArrayList<String> getAllTransactions(String column_name) { //populate list with credit transactions
        ArrayList<String> list = new ArrayList<String>();

        try {

            DBConnection ToDB = new DBConnection(); //Have a connection to the DB
            Connection DBConn = ToDB.openConn();
            Statement Stmt = DBConn.createStatement();
            String SQL_Command = "SELECT * FROM [TangClass].[dbo].[Transaction] WHERE CustomerID = '" + CustomerID + "' ORDER BY 'TransactionDate','TransactionTime' ASC";
            ResultSet Rslt = Stmt.executeQuery(SQL_Command);
            while (Rslt.next()) {
                if (column_name.equals("TransactionID")) {
                    list.add(Rslt.getString("TransactionID"));
                } else if (column_name.equals("TagCode")) {
                    list.add(Rslt.getString("TagCode"));
                } else if (column_name.equals("TransactionDate")) {
                    list.add(Rslt.getString("TransactionDate"));
                } else if (column_name.equals("TransactionTime")) {
                    list.add(Rslt.getString("TransactionTime"));
                } else if (column_name.equals("TollPlaza")) {
                    list.add(Rslt.getString("TollPlaza"));
                } else if (column_name.equals("TollLaneNumber")) {
                    list.add(Rslt.getString("TollLaneNumber"));
                } else if (column_name.equals("TollAmount")) {
                    list.add(Rslt.getString("TollAmount"));
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
            e.printStackTrace();
        }
        return list; //return list
    }

    public ArrayList<String> getTransactions(String before, String after, String column_name) {
        ArrayList<String> list = new ArrayList<String>();
        try {

            DBConnection ToDB = new DBConnection(); //Have a connection to the DB
            Connection DBConn = ToDB.openConn();
            Statement Stmt = DBConn.createStatement();
            String SQL_Command = "SELECT * FROM [TangClass].[dbo].[Transaction] WHERE CustomerID = '" + CustomerID + "'"
                    + " AND TransactionDate BETWEEN '" + before + "' AND '" + after + "'"
                    + "ORDER BY 'TransactionDate','TransactionTime' ASC";
            ResultSet Rslt = Stmt.executeQuery(SQL_Command); //execute query to get transaction based on dates
            while (Rslt.next()) {
                if (column_name.equals("TransactionID")) {
                    list.add(Rslt.getString("TransactionID"));
                } else if (column_name.equals("TagCode")) {
                    list.add(Rslt.getString("TagCode"));
                } else if (column_name.equals("TransactionDate")) {
                    list.add(Rslt.getString("TransactionDate"));
                } else if (column_name.equals("TransactionTime")) {
                    list.add(Rslt.getString("TransactionTime"));
                } else if (column_name.equals("TollPlaza")) {
                    list.add(Rslt.getString("TollPlaza"));
                } else if (column_name.equals("TollLaneNumber")) {
                    list.add(Rslt.getString("TollLaneNumber"));
                } else if (column_name.equals("TollAmount")) {
                    list.add(Rslt.getString("TollAmount"));
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
            e.printStackTrace();
        }
        return list; //returns the list
    }

}

