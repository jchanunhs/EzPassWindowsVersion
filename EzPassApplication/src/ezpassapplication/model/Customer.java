package ezpassapplication.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Customer {

    private String CustomerID;
    private String Name;
    private String Street;
    private String City;
    private String State;
    private String Zip;
    private String Phone;
    private String Email;
    private float Balance;
    private String Username;

    //Add customer to DB
    public Customer(String NM, String ST, String CT, String STE, String ZP, String PN, String EM, float Bal, String UName) {
        this.Name = NM;
        this.Street = ST;
        this.City = CT;
        this.State = STE;
        this.Zip = ZP;
        this.Phone = PN;
        this.Email = EM;
        this.Balance = Bal;
        this.Username = UName;
    }

    public Customer(String CID) {
        CustomerID = CID;
        this.setData();
    }

    public boolean createProfile() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection();
                Connection DBConn = ToDB.openConn();
                int cus_id = (int) (Math.random() * 1000000) + 1000000; //Id is 7 digits long
                CustomerID = String.valueOf(cus_id);
                PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?");
                Stmt.setString(1, CustomerID);
                ResultSet Rslt = Stmt.executeQuery();
                done = !Rslt.next(); //if not exist, insert customer info into db and  update the account with his new customerID
                if (done) {
                    Stmt = DBConn.prepareStatement("INSERT INTO Customer(CustomerID, Name, Street, City, State, Zip, Phone, Email, Balance) VALUES (?,?,?,?,?,?,?,?,?)");
                    Stmt.setString(1, CustomerID);
                    Stmt.setString(2, Name);
                    Stmt.setString(3, Street);
                    Stmt.setString(4, City);
                    Stmt.setString(5, State);
                    Stmt.setString(6, Zip);
                    Stmt.setString(7, Phone);
                    Stmt.setString(8, Email);
                    Stmt.setFloat(9, Balance);
                    Stmt.executeUpdate();
                    Stmt = DBConn.prepareStatement("UPDATE Account SET CustomerID = ? Where Username = ?");
                    Stmt.setString(1, CustomerID);
                    Stmt.setString(2, Username);
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

    public boolean updateAddress(String ST, String CT, String STE, String ZP) {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?");  //check if customer exist
                Stmt.setString(1, CustomerID);
                ResultSet Rslt = Stmt.executeQuery();
                done = Rslt.next(); //if it does exist, then update customer address
                if (done) {
                    Stmt = DBConn.prepareStatement("UPDATE Customer SET Street = ?, City = ?, State = ?, ZIP = ? WHERE CustomerID = ?");
                    Stmt.setString(1, ST);
                    Stmt.setString(2, CT);
                    Stmt.setString(3, STE);
                    Stmt.setString(4, ZP);
                    Stmt.setString(5, CustomerID);
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

    public boolean updatePhone(String PN) {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?");   //check if customer exist
                Stmt.setString(1, CustomerID);
                ResultSet Rslt = Stmt.executeQuery();
                done = Rslt.next(); //if does exist, then update phone number based on id
                if (done) {
                    Stmt = DBConn.prepareStatement("UPDATE Customer SET Phone = ? WHERE CustomerID = ?");
                    Stmt.setString(1, PN);
                    Stmt.setString(2, CustomerID);
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

    public boolean updateEmail(String EM) {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?");   //check if customer exist
                Stmt.setString(1, CustomerID);
                ResultSet Rslt = Stmt.executeQuery();
                done = Rslt.next(); //if it does exist, then update email
                if (done) {
                    Stmt = DBConn.prepareStatement("UPDATE Customer SET Email = ? WHERE CustomerID = ?");
                    Stmt.setString(1, EM);
                    Stmt.setString(2, CustomerID);
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

    public boolean updateBalance(float amount) {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?");   //check if customer exist
                Stmt.setString(1, CustomerID);
                ResultSet Rslt = Stmt.executeQuery();
                done = Rslt.next(); //update new account balance
                if (done) {
                    Stmt = DBConn.prepareStatement("UPDATE Customer SET Balance = ? WHERE CustomerID = ?");
                    Stmt.setFloat(1, amount);
                    Stmt.setString(2, CustomerID);
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

    //DB is configured to delete all customer information when we delete the row with their respective customer id  
    public boolean deleteCustomer() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?");   //check if customer exist
                Stmt.setString(1, CustomerID);
                ResultSet Rslt = Stmt.executeQuery();
                done = Rslt.next(); //if yes, then we can remove tag if tag and customerid match in the db
                if (done) {
                    Stmt = DBConn.prepareStatement("DELETE FROM Customer WHERE CustomerID = ?");
                    Stmt.setString(1, CustomerID);
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

    public boolean setData() { //fetch data from DB using CustomerID
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                PreparedStatement Stmt = DBConn.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?");   //check if customer exist
                Stmt.setString(1, CustomerID);
                ResultSet Rslt = Stmt.executeQuery();
                Rslt.next();
                Name = Rslt.getString("Name");
                Street = Rslt.getString("Street");
                City = Rslt.getString("City");
                State = Rslt.getString("State");
                Zip = Rslt.getString("Zip");
                Phone = Rslt.getString("Phone");
                Email = Rslt.getString("Email");
                Balance = Rslt.getFloat("Balance");
                Stmt.close();
                ToDB.closeConn();
                done = true;
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

    //return information after setData was executed
    public String getName() {
        return Name;
    }

    public String getStreet() {
        return Street;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String getZip() {
        return Zip;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public float getBalance() {
        return Balance;
    }

    public String getCustomerID() {
        return CustomerID;
    }

}
