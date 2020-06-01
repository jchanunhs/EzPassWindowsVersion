package model;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EzTag {

    private String TagCode;
    private String TagType;
    private String IssueDate;
    private String CustomerID;

    //add tag constructor
    public EzTag(String TC, String TT, String IssueD, String CID) {
        TagCode = TC;
        TagType = TT;
        IssueDate = IssueD;
        CustomerID = CID;
    }

    //check tag constructor
    public EzTag(String TC, String CID) {
        CustomerID = CID;
        TagCode = TC;
    }
   
    //to get all tags based on CustomerID
    public EzTag(String CID){
        CustomerID = CID;
    }

    //check if tag belongs to customer
    public boolean checkTag() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                Statement Stmt = DBConn.createStatement();
                String CID = "";
                String SQL_Command = "SELECT * FROM EzTag WHERE TagCode ='" + TagCode + "'"; //SQL query command
                ResultSet Rslt = Stmt.executeQuery(SQL_Command); //get the tag code
                Rslt.next();
                CID = Rslt.getString("CustomerID"); //get the customer id
                if (CustomerID.equals(CID)) { //if the customer id string is same as the customerid in the database, then tag belongs to customer
                    done = true;
                } else {
                    done = false;
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

    public boolean addTag() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                Statement Stmt = DBConn.createStatement();
                String SQL_Command = "SELECT * FROM EzTag WHERE TagCode ='" + TagCode + "'"; //SQL query command
                ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if tag code exist
                done = !Rslt.next(); //if not, insert tag to db
                if (done) {
                    SQL_Command = "INSERT INTO EzTag(TagCode, TagType, IssueDate, CustomerID)"
                            + " VALUES ('" + TagCode + "', '" + TagType + "', '" + IssueDate + "', '" + CustomerID + "')";
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

    public boolean removeTag() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                Statement Stmt = DBConn.createStatement();
                String SQL_Command = "SELECT * FROM EzTag WHERE TagCode ='" + TagCode + "'";
                ResultSet Rslt = Stmt.executeQuery(SQL_Command); //check if tag code exist
                done = Rslt.next(); //if yes, then we can remove tag if tag and customerid match in the db
                if (done) {
                    SQL_Command = "DELETE FROM EzTag WHERE CustomerID ='" + CustomerID + "'" + "AND TagCode ='" + TagCode + "'";
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
    
        public ArrayList<String> getTags() { //populate list with tags
    ArrayList<String> tags = new ArrayList<String>();
     
        try {

            DBConnection ToDB = new DBConnection(); //Have a connection to the DB
            Connection DBConn = ToDB.openConn();
            Statement Stmt = DBConn.createStatement();
            String SQL_Command = "SELECT * FROM EzTag WHERE CustomerID ='" + CustomerID + "'"; //SQL query command
            ResultSet Rslt = Stmt.executeQuery(SQL_Command); 
            while (Rslt.next()) {
                tags.add(Rslt.getString("TagCode"));
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
        return tags;
    }
}
