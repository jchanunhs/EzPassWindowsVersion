package ezpassapplication.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

    //card constructor
    public CreditCard(String CN, String NM, String EXP, String CV, String CID, float CD_AMT) {
        CardNumber = CN;
        Name = NM;
        ExpirationDate = EXP;
        CVV = CV;
        CustomerID = CID;
        CreditAmount = CD_AMT;
    }

    //Get credit card transactions
    public CreditCard(String CID) {
        CustomerID = CID;
    }

    public boolean addCreditCardTransaction() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection();
                Connection DBConn = ToDB.openConn();
                int credit_id = (int) (Math.random() * 1000000) + 1000000; //Id is 7 digits long
                CreditID = String.valueOf(credit_id);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());
                Date = formatter.format(date);
                formatter = new SimpleDateFormat("HH:mm:ss");
                date = new Date(System.currentTimeMillis());
                Time = formatter.format(date);
                PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM CreditCard WHERE CreditID = ?");
                Stmt.setString(1, CreditID);
                ResultSet Rslt = Stmt.executeQuery();
                done = !Rslt.next(); //credit id is useable
                if (done) {
                    Stmt = DBConn.prepareStatement("INSERT into CreditCard (CardNumber, Name, ExpirationDate, CVV, CustomerID, Date, Time, CreditAmount, CreditID) VALUES(?,?,?,?,?,?,?,?,?)");
                    Stmt.setString(1, CardNumber);
                    Stmt.setString(2, Name);
                    Stmt.setString(3, ExpirationDate);
                    Stmt.setString(4, CVV);
                    Stmt.setString(5, CustomerID);
                    Stmt.setString(6, Date);
                    Stmt.setString(7, Time);
                    Stmt.setFloat(8, CreditAmount);
                    Stmt.setString(9, CreditID);
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
        ArrayList<String> list = new ArrayList<>();
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM CreditCard WHERE CustomerID = ? ORDER BY 'Date','Time' ASC");
            Stmt.setString(1, CustomerID);
            ResultSet Rslt = Stmt.executeQuery();
            while (Rslt.next()) {
                switch (column_name) {
                    case "CardNumber":
                        list.add(Rslt.getString("CardNumber"));
                        break;
                    case "Name":
                        list.add(Rslt.getString("Name"));
                        break;
                    case "ExpirationDate":
                        list.add(Rslt.getString("ExpirationDate"));
                        break;
                    case "CVV":
                        list.add(Rslt.getString("CVV"));
                        break;
                    case "Date":
                        list.add(Rslt.getString("Date"));
                        break;
                    case "Time":
                        list.add(Rslt.getString("Time"));
                        break;
                    case "CreditAmount":
                        list.add(Rslt.getString("CreditAmount"));
                        break;
                    case "CreditID":
                        list.add(Rslt.getString("CreditID"));
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
        return list;
    }

    //return credit id when user recharges account
    public String getCreditID() {
        return CreditID;
    }

}
