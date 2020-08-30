package ezpassapplication.service;

import ezpassapplication.dao.AccountDAO;
import ezpassapplication.model.Account;
import java.sql.*;

public class AccountService implements AccountDAO {

    public AccountService() {

    }
    
    @Override
    public boolean signUp(Account account) {
        boolean done = false;
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Account WHERE Username = ?");
            Stmt.setString(1, account.getUsername());
            ResultSet Rslt = Stmt.executeQuery();
            done = !Rslt.next();
            if (done) { //if username not taken, insert into db
                Stmt = DBConn.prepareStatement("INSERT into Account (Username, Password) VALUES(?,?)");
                Stmt.setString(1, account.getUsername());
                Stmt.setString(2, account.getPassword());
                Stmt.executeUpdate();
            }
            Stmt.close();
            ToDB.closeConn();
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
    
    @Override
    public Account getAccountInformation(String Username, String Password) {
        Account account = new Account();
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Account WHERE Username = ? AND Password = ?");
            Stmt.setString(1, Username);
            Stmt.setString(2, Password);
            ResultSet Rslt = Stmt.executeQuery();
            Rslt.next();
            account.setCustomerID(Rslt.getString("CustomerID"));
            account.setUsername(Rslt.getString("Username"));
            account.setPassword(Rslt.getString("Password"));
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
        return account;

    }

    @Override
    public boolean updatePassword(Account account, String NewPassword) {
        boolean done = false;
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Account WHERE Username = ? AND Password = ?");
            Stmt.setString(1, account.getUsername());
            Stmt.setString(2, account.getPassword());
            ResultSet Rslt = Stmt.executeQuery();
            done = Rslt.next();
            if (done) {
                Stmt = DBConn.prepareStatement("UPDATE Account SET Password = ? WHERE Username = ?");
                Stmt.setString(1, NewPassword); //update password to new password
                Stmt.setString(2, account.getUsername());
                Stmt.executeUpdate();
            }
            Stmt.close();
            ToDB.closeConn();
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

}
