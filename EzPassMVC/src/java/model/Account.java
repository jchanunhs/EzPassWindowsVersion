package model;

import java.sql.*;

public class Account {

    private String CustomerID, Username, Password, Password1, Name;
    
    //Create account
    public Account(String UN, String PassW, String PassW1, String NM) {
        Username = UN;
        Password = PassW;
        Password1 = PassW1;
        Name = NM;
    }
    
    //Login
    public Account(String UN, String PassW) {
        Username = UN;
        Password = PassW;

    }
    
    //change account information based on CID
    public Account(String CID) {
        CustomerID = CID;
    }
    
    //default constructor
    public Account() {

    }

    public String getName() {
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            Statement Stmt = DBConn.createStatement();
            String SQL_Command = "SELECT * FROM Account WHERE Username ='" + Username + "'AND Password ='" + Password + "'"; //SQL query command
            ResultSet Rslt = Stmt.executeQuery(SQL_Command); // Execute
            Rslt.next();
            Name = Rslt.getString("Name"); //Fetch name row
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
        return Name;
    }
    
    public String getCustomerID() {
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            Statement Stmt = DBConn.createStatement();
            String SQL_Command = "SELECT * FROM Account WHERE Username ='" + Username + "'AND Password ='" + Password + "'"; //SQL query command
            ResultSet Rslt = Stmt.executeQuery(SQL_Command); // Execute
            Rslt.next();
            CustomerID = Rslt.getString("CustomerID"); //Fetch name row
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
        return CustomerID;
    }

    public boolean signUp() {
        boolean done = !Username.equals("") && !Password.equals("") && !Password1.equals("") && Password.equals(Password1);
        try {
            if (done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                Statement Stmt = DBConn.createStatement();
                String SQL_Command = "SELECT Username FROM Account WHERE Username ='" + Username + "'"; //SQL query command to check if username already taken
                ResultSet Rslt = Stmt.executeQuery(SQL_Command); 
                done = done && !Rslt.next();
                if (done) { //if username not taken, insert into db
                    SQL_Command = "INSERT INTO Account(Username, Password, Name) VALUES ('" + Username + "','" + Password + "','" + Name + "')"; //Save the username, password and Name
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
    
        public boolean UsernameTaken() { //Check if username is taken
        boolean done = !Username.equals("") && !Password.equals("") && !Password1.equals("") && Password.equals(Password1);
        try {
            if (done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                Statement Stmt = DBConn.createStatement();
                String SQL_Command = "SELECT Username FROM Account WHERE Username ='" + Username + "'"; //SQL query command to check if username already taken
                ResultSet Rslt = Stmt.executeQuery(SQL_Command); 
                done = Rslt.next();
                
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

    public boolean changePassword(String NewPassword) {
        boolean done = false;
        try {
            DBConnection ToDB = new DBConnection(); //Have a connection to the DB
            Connection DBConn = ToDB.openConn();
            Statement Stmt = DBConn.createStatement();
            String SQL_Command = "SELECT * FROM Account WHERE Username ='" + Username + "' AND Password ='" + Password + "'"; //Check if username
            ResultSet Rslt = Stmt.executeQuery(SQL_Command); 
            done = Rslt.next();
            if (done) {
                SQL_Command = "UPDATE Account SET Password='" + NewPassword + "' WHERE Username ='" + Username + "'"; //update password to new password
                Stmt.executeUpdate(SQL_Command);
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
            e.printStackTrace();
        }
        return done;
    }

    public boolean signIn() {
        boolean done = false;
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            Statement Stmt = DBConn.createStatement();
            String SQL_Command = "SELECT Username FROM Account WHERE Username ='" + Username + "'AND Password ='" + Password + "'"; //SQL query command
            ResultSet Rslt = Stmt.executeQuery(SQL_Command);
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
            e.printStackTrace();
        }
        return done;
    }

}
