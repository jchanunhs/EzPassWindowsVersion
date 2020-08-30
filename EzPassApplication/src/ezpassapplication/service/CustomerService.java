package ezpassapplication.service;

import ezpassapplication.dao.CustomerDAO;
import ezpassapplication.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerService implements CustomerDAO {

    public CustomerService() {

    }

    @Override
    public boolean createProfile(Customer customer, String Username) {
        boolean done = false;
        try {
            DBConnection ToDB = new DBConnection();
            Connection DBConn = ToDB.openConn();
            int cus_id = (int) (Math.random() * 1000000) + 1000000; //Id is 7 digits long
            String CustomerID = String.valueOf(cus_id);
            float Balance = 0; //creation of account does not have any balance
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?");
            Stmt.setString(1, CustomerID);
            ResultSet Rslt = Stmt.executeQuery();
            done = !Rslt.next(); //if not exist, insert customer info into db and  update the account with his new customerID
            if (done) {
                Stmt = DBConn.prepareStatement("INSERT INTO Customer(CustomerID, Name, Street, City, State, Zip, Phone, Email, Balance) VALUES (?,?,?,?,?,?,?,?,?)");
                Stmt.setString(1, CustomerID);
                Stmt.setString(2, customer.getName());
                Stmt.setString(3, customer.getStreet());
                Stmt.setString(4, customer.getCity());
                Stmt.setString(5, customer.getState());
                Stmt.setString(6, customer.getZip());
                Stmt.setString(7, customer.getPhone());
                Stmt.setString(8, customer.getEmail());
                Stmt.setFloat(9, Balance);
                Stmt.executeUpdate();
                Stmt = DBConn.prepareStatement("UPDATE Account SET CustomerID = ? Where Username = ?");
                Stmt.setString(1, CustomerID);
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

    @Override
    public boolean updateAddress(Customer customer, String NewStreet, String NewCity, String NewState, String NewZip) {
        boolean done = false;
        try {
            DBConnection ToDB = new DBConnection(); //Have a connection to the DB
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?");  //check if customer exist
            Stmt.setString(1, customer.getCustomerID());
            ResultSet Rslt = Stmt.executeQuery();
            done = Rslt.next(); //if it does exist, then update customer address
            if (done) {
                Stmt = DBConn.prepareStatement("UPDATE Customer SET Street = ?, City = ?, State = ?, ZIP = ? WHERE CustomerID = ?");
                Stmt.setString(1, NewStreet);
                Stmt.setString(2, NewCity);
                Stmt.setString(3, NewState);
                Stmt.setString(4, NewZip);
                Stmt.setString(5, customer.getCustomerID());
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
    public boolean updatePhone(Customer customer, String NewPhone) {
        boolean done = false;
        try {
            DBConnection ToDB = new DBConnection(); //Have a connection to the DB
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?");   //check if customer exist
            Stmt.setString(1, customer.getCustomerID());
            ResultSet Rslt = Stmt.executeQuery();
            done = Rslt.next(); //if does exist, then update phone number based on id
            if (done) {
                Stmt = DBConn.prepareStatement("UPDATE Customer SET Phone = ? WHERE CustomerID = ?");
                Stmt.setString(1, NewPhone);
                Stmt.setString(2, customer.getCustomerID());
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
    public boolean updateEmail(Customer customer, String NewEmail) {
        boolean done = false;
        try {
            DBConnection ToDB = new DBConnection(); //Have a connection to the DB
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?");   //check if customer exist
            Stmt.setString(1, customer.getCustomerID());
            ResultSet Rslt = Stmt.executeQuery();
            done = Rslt.next(); //if it does exist, then update email
            if (done) {
                Stmt = DBConn.prepareStatement("UPDATE Customer SET Email = ? WHERE CustomerID = ?");
                Stmt.setString(1, NewEmail);
                Stmt.setString(2, customer.getCustomerID());
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
    public boolean updateBalance(Customer customer, float NewBalance) {
        boolean done = false;
        try {
            DBConnection ToDB = new DBConnection(); //Have a connection to the DB
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?");   //check if customer exist
            Stmt.setString(1, customer.getCustomerID());
            ResultSet Rslt = Stmt.executeQuery();
            done = Rslt.next();
            if (done) {
                Stmt = DBConn.prepareStatement("UPDATE Customer SET Balance = ? WHERE CustomerID = ?");
                Stmt.setFloat(1, NewBalance);
                Stmt.setString(2, customer.getCustomerID());
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

    //DB is configured to delete all customer information when we delete the row with their respective customer id  
    @Override
    public boolean deleteCustomer(Customer customer) {
        boolean done = false;
        try {
            DBConnection ToDB = new DBConnection(); //Have a connection to the DB
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?");   //check if customer exist
            Stmt.setString(1, customer.getCustomerID());
            ResultSet Rslt = Stmt.executeQuery();
            done = Rslt.next(); //if yes, then we can remove tag if tag and customerid match in the db
            if (done) {
                Stmt = DBConn.prepareStatement("DELETE FROM Customer WHERE CustomerID = ?");
                Stmt.setString(1, customer.getCustomerID());
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
    public Customer getCustomerInformation(String CustomerID) { //fetch data from DB using CustomerID
        Customer customer = new Customer();
        try {
            DBConnection ToDB = new DBConnection(); //Have a connection to the DB
            Connection DBConn = ToDB.openConn();
            PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?");   //check if customer exist
            Stmt.setString(1, CustomerID);
            ResultSet Rslt = Stmt.executeQuery();
            Rslt.next();
            customer.setCustomerID(Rslt.getString("CustomerID"));
            customer.setName(Rslt.getString("Name"));
            customer.setStreet(Rslt.getString("Street"));
            customer.setCity(Rslt.getString("City"));
            customer.setState(Rslt.getString("State"));
            customer.setZip(Rslt.getString("Zip"));
            customer.setPhone(Rslt.getString("Phone"));
            customer.setEmail(Rslt.getString("Email"));
            customer.setBalance(Rslt.getFloat("Balance"));
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
        return customer;
    }

}
