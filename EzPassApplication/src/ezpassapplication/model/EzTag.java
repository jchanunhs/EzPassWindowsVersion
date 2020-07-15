package ezpassapplication.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EzTag {

    private String TagCode;
    private String TagType;
    private String IssueDate;
    private String CustomerID;

    //add tag constructor
    public EzTag(String TC, String TT, String CID) {
        TagCode = TC;
        TagType = TT;
        CustomerID = CID;
    }

    //check tag constructor
    public EzTag(String TC, String CID) {
        CustomerID = CID;
        TagCode = TC;
    }

    //to get all tags based on CustomerID
    public EzTag(String CID) {
        CustomerID = CID;
    }

    //check if tag belongs to customer
    public boolean checkTag() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection();
                Connection DBConn = ToDB.openConn();
                PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM EzTag WHERE CustomerID = ? AND TagCode = ?");
                Stmt.setString(1, CustomerID);
                Stmt.setString(2, TagCode);
                ResultSet Rslt = Stmt.executeQuery(); //if there is a row, that means tag code and customer id are a match
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
        }
        return done;
    }

    public boolean addTag() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection();
                Connection DBConn = ToDB.openConn();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());
                IssueDate = formatter.format(date);
                PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM EzTag WHERE TagCode = ?");
                Stmt.setString(1, TagCode);
                ResultSet Rslt = Stmt.executeQuery();
                done = !Rslt.next(); //if tag code not taken, insert row
                if (done) {
                    Stmt = DBConn.prepareStatement("INSERT INTO EzTag(TagCode, TagType, IssueDate, CustomerID) VALUES (?,?,?,?)");
                    Stmt.setString(1, TagCode);
                    Stmt.setString(2, TagType);
                    Stmt.setString(3, IssueDate);
                    Stmt.setString(4, CustomerID);
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

    public boolean removeTag() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection();
                Connection DBConn = ToDB.openConn();
                PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM EzTag WHERE CustomerID = ? AND TagCode = ?");
                Stmt.setString(1, CustomerID);
                Stmt.setString(2, TagCode);
                ResultSet Rslt = Stmt.executeQuery(); //check if tag code exist
                done = Rslt.next(); //if yes, then we can remove tag if tag and customerid match in the db
                if (done) {
                    Stmt = DBConn.prepareStatement("DELETE FROM EzTag WHERE CustomerID = ? AND TagCode = ?");
                    Stmt.setString(1, CustomerID);
                    Stmt.setString(2, TagCode);
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

    //DB Is configured to update tables associated with this tag code
    public boolean updateTagCode(String NewTag) {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection();
                Connection DBConn = ToDB.openConn();
                PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM EzTag WHERE CustomerID = ? AND TagCode = ?");
                Stmt.setString(1, CustomerID);
                Stmt.setString(2, TagCode);
                ResultSet Rslt = Stmt.executeQuery(); //check if tag code exist
                done = Rslt.next(); //if yes, then we can update tag code
                if (done) {
                    Stmt = DBConn.prepareStatement("UPDATE EzTag SET TagCode = ? WHERE TagCode = ? AND CustomerID = ?");
                    Stmt.setString(1, NewTag);
                    Stmt.setString(2, TagCode);
                    Stmt.setString(3, CustomerID);
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

    public boolean updateTagType(String NewTagType) {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection();
                Connection DBConn = ToDB.openConn();
                PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM EzTag WHERE CustomerID = ? AND TagCode = ?");
                Stmt.setString(1, CustomerID);
                Stmt.setString(2, TagCode);
                ResultSet Rslt = Stmt.executeQuery(); //check if tag code exist
                done = Rslt.next(); //if yes, then we can update tag type
                if (done) {
                    Stmt = DBConn.prepareStatement("UPDATE EzTag SET TagType = ? WHERE TagCode = ? AND CustomerID = ?");
                    Stmt.setString(1, NewTagType);
                    Stmt.setString(2, TagCode);
                    Stmt.setString(3, CustomerID);
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

    public ArrayList<String> getTags() { //populate list with tags
        ArrayList<String> tags = new ArrayList<String>();

        try {

            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM EzTag WHERE CustomerID = ?");
            Stmt.setString(1, CustomerID);
            ResultSet Rslt = Stmt.executeQuery();
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
        }
        return tags;
    }
}
