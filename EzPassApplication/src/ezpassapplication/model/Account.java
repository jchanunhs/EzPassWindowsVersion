package ezpassapplication.model;

import java.sql.*;

public class Account {

    private String CustomerID, Username, Password, Name;

    //Create account
    public Account(String UN, String PassW, String NM) {
        Username = UN;
        Password = PassW;
        Name = NM;
    }

    //Login
    public Account(String UN, String PassW) {
        Username = UN;
        Password = PassW;

    }


    public boolean signUp() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection();
                Connection DBConn = ToDB.openConn();
                PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Account WHERE Username = ?");
                Stmt.setString(1, Username);
                ResultSet Rslt = Stmt.executeQuery();
                done = !Rslt.next();
                if (done) { //if username not taken, insert into db
                    Stmt = DBConn.prepareStatement("INSERT into Account (Username, Password, Name) VALUES(?,?,?)");
                    Stmt.setString(1, Username);
                    Stmt.setString(2, Password);
                    Stmt.setString(3, Name);
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

    public boolean signIn() {
        boolean done = false;
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Account WHERE Username = ? AND Password = ?");
            Stmt.setString(1, Username);
            Stmt.setString(2, Password);
            ResultSet Rslt = Stmt.executeQuery();
            done = Rslt.next();//If there is a row, user typed in valid username and password and return true
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

    public String getName() {
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Account WHERE Username = ? AND Password = ?");
            Stmt.setString(1, Username);
            Stmt.setString(2, Password);
            ResultSet Rslt = Stmt.executeQuery();
            Rslt.next();
            Name = Rslt.getString("Name");
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
        return Name;
    }

    public String getCustomerID() {
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Account WHERE Username = ? AND Password = ?");
            Stmt.setString(1, Username);
            Stmt.setString(2, Password);
            ResultSet Rslt = Stmt.executeQuery();
            Rslt.next();
            CustomerID = Rslt.getString("CustomerID");
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
        return CustomerID;
    }

    public boolean changePassword(String NewPassword) {
        boolean done = false;
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Account WHERE Username = ? AND Password = ?");
            Stmt.setString(1, Username);
            Stmt.setString(2, Password);
            ResultSet Rslt = Stmt.executeQuery();
            done = Rslt.next();
            if (done) {
                Stmt = DBConn.prepareStatement("UPDATE Account SET Password = ? WHERE Username = ?");
                Stmt.setString(1, NewPassword); //update password to new password
                Stmt.setString(2, Username);
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
