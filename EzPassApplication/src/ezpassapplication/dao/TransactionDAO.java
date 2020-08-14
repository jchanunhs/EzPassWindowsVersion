package ezpassapplication.dao;

import ezpassapplication.entity.Transaction;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TransactionDAO {

    
    public TransactionDAO() {

    }

    public String recordTransaction(Transaction transaction) {
        boolean done = false;
        String TransactionID = "";
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            int trans_id = (int) (Math.random() * 1000000) + 1000000; //Id is 7 digits long
            TransactionID = String.valueOf(trans_id);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String TransactionDate = formatter.format(date);
            formatter = new SimpleDateFormat("HH:mm:ss");
            date = new Date(System.currentTimeMillis());
            String TransactionTime = formatter.format(date);
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM [TangClass].[dbo].[Transaction] WHERE TransactionID = ?");
            Stmt.setString(1, TransactionID);
            ResultSet Rslt = Stmt.executeQuery(); //if transaction id does not exist, we are safe to add them to db
            done = !Rslt.next();
            if (done) {
                Stmt = DBConn.prepareStatement("INSERT INTO [TangClass].[dbo].[Transaction](TransactionID, TagCode, TransactionDate, TransactionTime, TollAmount, TollPlaza, TollLaneNumber, CustomerID) VALUES (?,?,?,?,?,?,?,?)");
                Stmt.setString(1, TransactionID);
                Stmt.setString(2, transaction.getTagCode());
                Stmt.setString(3, TransactionDate);
                Stmt.setString(4, TransactionTime);
                Stmt.setFloat(5, transaction.getTollAmount());
                Stmt.setString(6, transaction.getTollPlaza());
                Stmt.setInt(7, transaction.getTollLaneNumber());
                Stmt.setString(8, transaction.getCustomerID());
                Stmt.executeUpdate();
            }
            else{// if transaction id is taken, transaction id remains empty
                TransactionID = "";
            }
            Stmt.close();
            ToDB.closeConn();

        } catch (java.sql.SQLException e) {
            TransactionID = "";
            System.out.println("SQLException: " + e);
            while (e != null) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e = e.getNextException();
                System.out.println("");
            }
        } catch (java.lang.Exception e) {
            TransactionID = "";
            System.out.println("Exception: " + e);
        }
        return TransactionID;
    }

    public ArrayList<Transaction> getAllTransactions(String CustomerID) { //populate list with credit transactions
        ArrayList<Transaction> TransactionList = new ArrayList<>();
        Transaction transaction;
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM [TangClass].[dbo].[Transaction] WHERE CustomerID = ?");
            Stmt.setString(1, CustomerID);
            ResultSet Rslt = Stmt.executeQuery();
            while (Rslt.next()) {
                transaction = new Transaction();
                transaction.setTransactionID(Rslt.getString("TransactionID"));
                transaction.setTagCode(Rslt.getString("TagCode"));
                transaction.setTransactionDate(Rslt.getString("TransactionDate"));
                transaction.setTransactionTime(Rslt.getString("TransactionTime"));
                transaction.setTollPlaza(Rslt.getString("TollPlaza"));
                transaction.setTollLaneNumber(Rslt.getInt("TollLaneNumber"));
                transaction.setTollAmount(Rslt.getFloat("TollAmount"));
                TransactionList.add(transaction);
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
        return TransactionList; 
    }

    public ArrayList<Transaction> getTransactions(String CustomerID, String before, String after) {
        ArrayList<Transaction> TransactionList = new ArrayList<>();
        Transaction transaction;
        try {
            DBConnection ToDB = new DBConnection(); //Have a connection to the DB
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM [TangClass].[dbo].[Transaction] WHERE CustomerID = ? AND TransactionDate BETWEEN ? AND ? ORDER BY 'TransactionDate','TransactionTime' ASC");
            Stmt.setString(1, CustomerID);
            Stmt.setString(2, before);
            Stmt.setString(3, after);
            ResultSet Rslt = Stmt.executeQuery(); //execute query to get transaction based on dates
            while (Rslt.next()) {
                transaction = new Transaction();
                transaction.setTransactionID(Rslt.getString("TransactionID"));
                transaction.setTagCode(Rslt.getString("TagCode"));
                transaction.setTransactionDate(Rslt.getString("TransactionDate"));
                transaction.setTransactionTime(Rslt.getString("TransactionTime"));
                transaction.setTollPlaza(Rslt.getString("TollPlaza"));
                transaction.setTollLaneNumber(Rslt.getInt("TollLaneNumber"));
                transaction.setTollAmount(Rslt.getFloat("TollAmount"));
                TransactionList.add(transaction);
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
        return TransactionList; 
    }

    //return transaction id when user pays toll

}
