package ezpassapplication.service;

import ezpassapplication.dao.CreditCardDAO;
import ezpassapplication.model.CreditCard;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CreditCardService implements CreditCardDAO {

    public CreditCardService() {

    }
    
    @Override
    public String addCreditCardTransaction(CreditCard credit) {
        boolean done = false;
        String CreditID = "";
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            int credit_id = (int) (Math.random() * 1000000) + 1000000; //Id is 7 digits long
            CreditID = String.valueOf(credit_id);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String Date = formatter.format(date);
            formatter = new SimpleDateFormat("HH:mm:ss");
            date = new Date(System.currentTimeMillis());
            String Time = formatter.format(date);
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM CreditCard WHERE CreditID = ?");
            Stmt.setString(1, CreditID);
            ResultSet Rslt = Stmt.executeQuery();
            done = !Rslt.next(); //credit id is useable
            if (done) {
                Stmt = DBConn.prepareStatement("INSERT into CreditCard (CardNumber, Name, ExpirationDate, CVV, CustomerID, Date, Time, CreditAmount, CreditID) VALUES(?,?,?,?,?,?,?,?,?)");
                Stmt.setString(1, credit.getCardNumber());
                Stmt.setString(2, credit.getName());
                Stmt.setString(3, credit.getExpirationDate());
                Stmt.setString(4, credit.getCVV());
                Stmt.setString(5, credit.getCustomerID());
                Stmt.setString(6, Date);
                Stmt.setString(7, Time);
                Stmt.setFloat(8, credit.getCreditAmount());
                Stmt.setString(9, CreditID);
                Stmt.executeUpdate();
            } else { //if not usable, creditID remains empty
                CreditID = "";
            }
            Stmt.close();
            ToDB.closeConn();
        } catch (java.sql.SQLException e) {
            CreditID = "";
            System.out.println("SQLException: " + e);
            while (e != null) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e = e.getNextException();
                System.out.println("");
            }
        } catch (java.lang.Exception e) {
            CreditID = "";
            System.out.println("Exception: " + e);
        }
        return CreditID;
    }

    @Override
    public List<CreditCard> getAllTransactions(String CustomerID) { //populate list with credit transactions
        List<CreditCard> CardList = new ArrayList<>();
        CreditCard credit;
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM CreditCard WHERE CustomerID = ? ORDER BY 'Date','Time' ASC");
            Stmt.setString(1, CustomerID);
            ResultSet Rslt = Stmt.executeQuery();
            while (Rslt.next()) {
                credit = new CreditCard();
                credit.setCardNumber(Rslt.getString("CardNumber"));
                credit.setName(Rslt.getString("Name"));
                credit.setExpirationDate(Rslt.getString("ExpirationDate"));
                credit.setCVV(Rslt.getString("CVV"));
                credit.setCustomerID(Rslt.getString("CustomerID"));
                credit.setDate(Rslt.getString("Date"));
                credit.setTime(Rslt.getString("Time"));
                credit.setCreditAmount(Rslt.getFloat("CreditAmount"));
                credit.setCreditID(Rslt.getString("CreditID"));
                CardList.add(credit);
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
        return CardList;
    }

}
