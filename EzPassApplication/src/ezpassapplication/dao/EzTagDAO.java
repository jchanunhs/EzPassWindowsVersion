package ezpassapplication.dao;

import ezpassapplication.entity.EzTag;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EzTagDAO {

    public EzTagDAO() {

    }

    //check if tag belongs to customer
    public boolean checkTag(EzTag ez) {
        boolean done = false;
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM EzTag WHERE CustomerID = ? AND TagCode = ?");
            Stmt.setString(1, ez.getCustomerID());
            Stmt.setString(2, ez.getTagCode());
            ResultSet Rslt = Stmt.executeQuery(); //if there is a row, that means tag code and customer id are a match
            done = Rslt.next();
            if (Rslt.getString("TagType").equals("invalid")) { //if tag type is invalid, that means tag is invalid and return false.
                done = false;
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

    public boolean addTag(EzTag ez) {
        boolean done = false;
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String IssueDate = formatter.format(date);
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM EzTag WHERE TagCode = ?");
            Stmt.setString(1, ez.getTagCode());
            ResultSet Rslt = Stmt.executeQuery();
            done = !Rslt.next(); //if tag code not taken, insert row
            if (done) {
                Stmt = DBConn.prepareStatement("INSERT INTO EzTag(TagCode, TagType, IssueDate, CustomerID) VALUES (?,?,?,?)");
                Stmt.setString(1, ez.getTagCode());
                Stmt.setString(2, ez.getTagType());
                Stmt.setString(3, IssueDate);
                Stmt.setString(4, ez.getCustomerID());
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

    public boolean removeTag(EzTag ez) {
        boolean done = false;
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM EzTag WHERE CustomerID = ? AND TagCode = ?");
            Stmt.setString(1, ez.getCustomerID());
            Stmt.setString(2, ez.getTagCode());
            ResultSet Rslt = Stmt.executeQuery(); //check if tag code exist
            done = Rslt.next(); //if yes, then we can remove tag if tag and customerid match in the db
            if (done) {
                Stmt = DBConn.prepareStatement("DELETE FROM EzTag WHERE CustomerID = ? AND TagCode = ?");
                Stmt.setString(1, ez.getCustomerID());
                Stmt.setString(2, ez.getTagCode());
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

    public boolean updateTagType(EzTag ez, String NewTagType) {
        boolean done = false;
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM EzTag WHERE CustomerID = ? AND TagCode = ?");
            Stmt.setString(1, ez.getCustomerID());
            Stmt.setString(2, ez.getTagCode());
            ResultSet Rslt = Stmt.executeQuery(); //check if tag code exist
            done = Rslt.next(); //if yes, then we can update tag type
            if (done) {
                Stmt = DBConn.prepareStatement("UPDATE EzTag SET TagType = ? WHERE TagCode = ? AND CustomerID = ?");
                Stmt.setString(1, NewTagType);
                Stmt.setString(2, ez.getTagCode());
                Stmt.setString(3, ez.getCustomerID());
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

    public ArrayList<EzTag> getAllCustomerTag(String CustomerID) { //populate list with tags
        ArrayList<EzTag> TagList = new ArrayList<>();
        EzTag ez;
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM EzTag WHERE CustomerID = ?");
            Stmt.setString(1, CustomerID);
            ResultSet Rslt = Stmt.executeQuery();
            while (Rslt.next()) {
                if (!Rslt.getString("TagType").equals("invalid")) { //if tag type is not invalid, add the tag code to array list
                    ez = new EzTag();
                    ez.setCustomerID(Rslt.getString("CustomerID"));
                    ez.setTagCode(Rslt.getString("TagCode"));
                    ez.setTagType(Rslt.getString("TagType"));
                    ez.setIssueDate(Rslt.getString("IssueDate"));
                    TagList.add(ez);
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
        return TagList;
    }
}
