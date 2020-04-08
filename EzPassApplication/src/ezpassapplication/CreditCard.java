package ezpassapplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreditCard {

    private String CardNumber;
    private String Name;
    private String ExpirationDate;
    private String CVV;
    private String CustomerID;

    //card constructor
    CreditCard(String CN, String NM, String EXP, String CV, String CID) {
        CardNumber = CN;
        Name = NM;
        ExpirationDate = EXP;
        CVV = CV;
        CustomerID = CID;
    }

    public boolean addCreditCard() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                Statement Stmt = DBConn.createStatement();
                //add credit card to db
                String SQL_Command = "INSERT INTO CreditCard(CardNumber, Name, ExpirationDate, CVV, CustomerID)"
                            + " VALUES ('" + CardNumber + "', '" + Name + "', '" + ExpirationDate + "', '" + CVV + "', '" + CustomerID + "'" + ")";
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

    public boolean removeCreditCard() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection();
                Connection DBConn = ToDB.openConn();
                Statement Stmt = DBConn.createStatement();
                String SQL_Command = "SELECT * FROM CreditCard WHERE CardNumber ='" + CardNumber + "'"; //SQL query command
                ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if card exist
                done = Rslt.next(); //if it does exist, delete credit card from db
                if (done) {
                    SQL_Command = "DELETE FROM CreditCard WHERE CardNumber ='" + CardNumber + "' AND CustomerID = '" + CustomerID + "'";
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

    public boolean updateCreditCard(String newCard) {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                Statement Stmt = DBConn.createStatement();
                String SQL_Command = "SELECT * FROM CreditCard WHERE CardNumber ='" + CardNumber + "'"; //SQL query command
                ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the card exist.
                done = Rslt.next(); //if it exist, update credit card, allow users to change information
                if (done) {
                    SQL_Command = "UPDATE CreditCard "
                            + "SET CardNumber = '" + newCard + "'" + ", " //set card to new card
                            + "Name = '" + Name + "'" + ", "
                            + "ExpirationDate = '" + ExpirationDate + "'" + ", "
                            + "CVV = '" + CVV + "' "
                            + "WHERE CardNumber ='" + CardNumber + "'"; //where cardnumber = oldcard
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
}
