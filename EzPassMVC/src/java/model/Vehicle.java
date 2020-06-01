package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
    public Vehicle(String CID, String LicensePlate) {
        LicensePlateNumber = LicensePlate;
        CustomerID = CID;
    }

    public boolean addVehicle() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                Statement Stmt = DBConn.createStatement();
                String SQL_Command = "SELECT * FROM Vehicle WHERE LicensePlateNumber ='" + LicensePlateNumber + "'";
                ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the license plate exist
                done = !Rslt.next(); //if not, insert vehicle into DB
                if (done) {
                    SQL_Command = "INSERT INTO Vehicle(LicensePlateNumber, Make, Model, Year, Color, TagCode, CustomerID)"
                            + " VALUES ('" + LicensePlateNumber + "', '" + Make + "', '" + Model + "', '" + Year + "', '" + Color + "', '" + TagCode + "', '" + CustomerID + "'" + ")";
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

    public boolean removeVehicle() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                Statement Stmt = DBConn.createStatement();
                String SQL_Command = "SELECT * FROM Vehicle WHERE LicensePlateNumber ='" + LicensePlateNumber + "' AND CustomerID = '" + CustomerID + "'"; //SQL query command
                ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the license plate number exist for customer
                done = Rslt.next(); //if it does, then delete vehicle from DB
                if (done) {
                    SQL_Command = "DELETE FROM Vehicle WHERE LicensePlateNumber ='" + LicensePlateNumber + "' AND CustomerID = '" + CustomerID + "'";
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

 public ArrayList<String> getVehicles() { //populate list with vehicles
    ArrayList<String> vehicles = new ArrayList<String>();
     
        try {

            DBConnection ToDB = new DBConnection(); //Have a connection to the DB
            Connection DBConn = ToDB.openConn();
            Statement Stmt = DBConn.createStatement();
            String SQL_Command = "SELECT * FROM Vehicle WHERE CustomerID ='" + CustomerID + "'"; //fetch all license plate numbers 
            ResultSet Rslt = Stmt.executeQuery(SQL_Command); 
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
