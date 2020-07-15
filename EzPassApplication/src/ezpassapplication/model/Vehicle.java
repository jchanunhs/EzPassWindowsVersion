package ezpassapplication.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Vehicle {

    private String LicensePlateNumber;
    private String Make;
    private String Model;
    private String Year;
    private String Color;
    private String TagCode;
    private String CustomerID;

    //Constructor to add vehicle
    public Vehicle(String LicensePlate, String make, String model, String year, String color, String tagCode, String CID) {
        LicensePlateNumber = LicensePlate;
        Make = make;
        Model = model;
        Year = year;
        Color = color;
        TagCode = tagCode;
        CustomerID = CID;
    }

    //Constructor to get vehicle information
    public Vehicle(String CID) {
        CustomerID = CID;
    }

    //Constructor to remove vehicle
    public Vehicle(String LicensePlate, String CID) {
        LicensePlateNumber = LicensePlate;
        CustomerID = CID;
    }
    
    //Constructor to check vehicle
    public Vehicle(String LicensePlate, String tagCode, String CID) {
        LicensePlateNumber = LicensePlate;
        TagCode = tagCode;
        CustomerID = CID;
    }
    
    //check if vehicle belongs to customer and matches tag code
    public boolean checkVehicle() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection();
                Connection DBConn = ToDB.openConn();
                PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Vehicle WHERE CustomerID = ? AND TagCode = ? AND LicensePlateNumber = ?");
                Stmt.setString(1, CustomerID);
                Stmt.setString(2, TagCode);
                Stmt.setString(3, LicensePlateNumber);
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

    public boolean addVehicle() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection();
                Connection DBConn = ToDB.openConn();
                PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Vehicle WHERE LicensePlateNumber = ?");
                Stmt.setString(1, LicensePlateNumber);
                ResultSet Rslt = Stmt.executeQuery();
                done = !Rslt.next(); //if license plate number does not exist, add it to table
                if (done) {
                    Stmt = DBConn.prepareStatement("INSERT INTO Vehicle(LicensePlateNumber, Make, Model, Year, Color, TagCode, CustomerID) VALUES (?,?,?,?,?,?,?)");
                    Stmt.setString(1, LicensePlateNumber);
                    Stmt.setString(2, Make);
                    Stmt.setString(3, Model);
                    Stmt.setString(4, Year);
                    Stmt.setString(5, Color);
                    Stmt.setString(6, TagCode);
                    Stmt.setString(7, CustomerID);
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
            e.printStackTrace();
        }
        return done;
    }

    public boolean removeVehicle() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection();
                Connection DBConn = ToDB.openConn();
                PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Vehicle WHERE CustomerID = ? AND LicensePlateNumber = ?");
                Stmt.setString(1, CustomerID);
                Stmt.setString(2, LicensePlateNumber);
                ResultSet Rslt = Stmt.executeQuery();
                done = Rslt.next(); //if vehicle exist, delete vehicle
                if (done) {
                    Stmt = DBConn.prepareStatement("DELETE FROM Vehicle WHERE CustomerID = ? AND LicensePlateNumber = ?");
                    Stmt.setString(1, CustomerID);
                    Stmt.setString(2, LicensePlateNumber);
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
            e.printStackTrace();
        }
        return done;
    }

    public ArrayList<String> getVehicles() { //populate list with vehicles
        ArrayList<String> vehicles = new ArrayList<String>();
        try {
            DBConnection ToDB = new DBConnection(); //Have a connection to the DB
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Vehicle WHERE CustomerID = ?");
            Stmt.setString(1, CustomerID);
            ResultSet Rslt = Stmt.executeQuery();
            while (Rslt.next()) {
                vehicles.add(Rslt.getString("LicensePlateNumber"));
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
        return vehicles;
    }
}
