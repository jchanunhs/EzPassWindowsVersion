package ezpassapplication.service;

import ezpassapplication.dao.VehicleDAO;
import ezpassapplication.model.Vehicle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VehicleService implements VehicleDAO{
 
    public VehicleService() {
        
    }

    //check if vehicle belongs to customer and matches tag code
    @Override
    public boolean checkVehicle(Vehicle vehicle) {
        boolean done = false;
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Vehicle WHERE CustomerID = ? AND TagCode = ? AND LicensePlateNumber = ?");
            Stmt.setString(1, vehicle.getCustomerID());
            Stmt.setString(2, vehicle.getTagCode());
            Stmt.setString(3, vehicle.getLicensePlateNumber());
            ResultSet Rslt = Stmt.executeQuery(); //if there is a row, that means tag code, vehicle and customer id are a match
            done = Rslt.next();
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
    public boolean addVehicle(Vehicle vehicle) {
        boolean done = false;
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Vehicle WHERE LicensePlateNumber = ?");
            Stmt.setString(1, vehicle.getLicensePlateNumber());
            ResultSet Rslt = Stmt.executeQuery();
            done = !Rslt.next(); //if license plate number does not exist, add it to table
            if (done) {
                Stmt = DBConn.prepareStatement("INSERT INTO Vehicle(LicensePlateNumber, Make, Model, Year, Color, TagCode, CustomerID) VALUES (?,?,?,?,?,?,?)");
                Stmt.setString(1, vehicle.getLicensePlateNumber());
                Stmt.setString(2, vehicle.getMake());
                Stmt.setString(3, vehicle.getModel());
                Stmt.setString(4, vehicle.getYear());
                Stmt.setString(5, vehicle.getColor());
                Stmt.setString(6, vehicle.getTagCode());
                Stmt.setString(7, vehicle.getCustomerID());
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
            e.printStackTrace();
        }
        return done;
    }

    @Override
    public boolean removeVehicle(Vehicle vehicle) {
        boolean done = false;
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Vehicle WHERE CustomerID = ? AND LicensePlateNumber = ?");
            Stmt.setString(1, vehicle.getCustomerID());
            Stmt.setString(2, vehicle.getLicensePlateNumber());
            ResultSet Rslt = Stmt.executeQuery();
            done = Rslt.next(); //if vehicle exist, delete vehicle
            if (done) {
                Stmt = DBConn.prepareStatement("DELETE FROM Vehicle WHERE CustomerID = ? AND LicensePlateNumber = ?");
                Stmt.setString(1, vehicle.getCustomerID());
                Stmt.setString(2, vehicle.getLicensePlateNumber());
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
            e.printStackTrace();
        }
        return done;
    }

    @Override
    public List<Vehicle> getAllCustomerVehicles(String CustomerID) { //populate list with vehicles
        List<Vehicle> VehicleList = new ArrayList<>();
        Vehicle vehicle;
        try {
            DBConnection ToDB = new DBConnection(); //Have a connection to the DB
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Vehicle WHERE CustomerID = ?");
            Stmt.setString(1, CustomerID);
            ResultSet Rslt = Stmt.executeQuery();
            while (Rslt.next()) {
                vehicle = new Vehicle();
                vehicle.setLicensePlateNumber(Rslt.getString("LicensePlateNumber"));
                vehicle.setMake(Rslt.getString("Make"));
                vehicle.setModel(Rslt.getString("Model"));
                vehicle.setYear(Rslt.getString("Year"));
                vehicle.setColor(Rslt.getString("Color"));
                vehicle.setTagCode(Rslt.getString("TagCode"));
                vehicle.setCustomerID(Rslt.getString("CustomerID"));
                VehicleList.add(vehicle);
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
        return VehicleList;
    }
}
