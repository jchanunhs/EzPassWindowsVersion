package ezpassapplication;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreditCard {

    private String CardNumber;
    private String Name;
    private String ExpirationDate;
    private String CVV;
    private String CustomerID;
    private String Date;
    private String Time;
    private float CreditAmount;
    private String CreditID;
    private DBConnection ToDB;
    private Connection DBConn;

    //card constructor
    CreditCard(String CN, String NM, String EXP, String CV, String CID, float CD_AMT) {
        CardNumber = CN;
        Name = NM;
        ExpirationDate = EXP;
        CVV = CV;
        CustomerID = CID;
        CreditAmount = CD_AMT;
    }

    //Get credit card transactions
    CreditCard(String CID) {
        CustomerID = CID;
    }

    public boolean addCreditCard() {
        boolean done = false;
        try {
            if (!done) {
                ToDB = new DBConnection(); //Have a connection to the DB
                DBConn = ToDB.openConn();
                int credit_id = (int) (Math.random() * 1000000) + 1000000; //Id is 7 digits long
                CreditID = String.valueOf(credit_id);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());
                Date = formatter.format(date);
                formatter = new SimpleDateFormat("HH:mm:ss");
                date = new Date(System.currentTimeMillis());
                Time = formatter.format(date);

                Statement Stmt = DBConn.createStatement();
                //add credit card to db
                String SQL_Command = "INSERT INTO CreditCard(CardNumber, Name, ExpirationDate, CVV, CustomerID, Date, Time, CreditAmount, CreditID)"
                        + " VALUES ('" + CardNumber + "', '" + Name + "', '" + ExpirationDate + "', '" + CVV + "', '" + CustomerID + "', '" + Date + "', '" + Time + "', " + CreditAmount + ", '" + CreditID + "' )";
                Stmt.executeUpdate(SQL_Command);
                done = true;
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

    public ResultSet getAllTransactions() {
               ResultSet Rslt = null;
        try {

            ToDB = new DBConnection(); //Have a connection to the DB
            DBConn = ToDB.openConn();
            Statement Stmt = DBConn.createStatement();
            String SQL_Command = "SELECT * FROM [TangClass].[dbo].[CreditCard] WHERE CustomerID = '" + CustomerID + "' ORDER BY 'Date','Time' ASC";
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

    public void closeAllConn() {
        try {
            DBConn.close();
            ToDB.closeConn();
        } catch (SQLException ex) {
            Logger.getLogger(CreditCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
