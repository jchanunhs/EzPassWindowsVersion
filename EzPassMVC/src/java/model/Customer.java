package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
    private String UName;

    public Customer() {

    }
    
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
        this.UName = UName;
    }
    
    
    
    public Customer(String CID){
        CustomerID = CID;
    }
    
    
    public boolean createProfile() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                Statement Stmt = DBConn.createStatement();
                int cus_id = (int) (Math.random() * 1000000) + 1000000; //Id is 7 digits long
                CustomerID = String.valueOf(cus_id);
                String SQL_Command = "SELECT * FROM Customer WHERE CustomerID ='" + CustomerID + "'"; //SQL query command
                ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the customerid already exist.
                done = !Rslt.next(); //if not exist, insert customer info into db and  update the account with his new customerID
                if (done) {
                    SQL_Command = "INSERT INTO Customer(CustomerID, Name, Street, City, State, Zip, Phone, Email, Balance)"
                            + " VALUES ('" + CustomerID + "', '" + Name + "', '" + Street + "', '" + City + "', '" + State + "', '" + Zip + "', '" + Phone + "', '" + Email + "', " + Balance + ")";
                    Stmt.executeUpdate(SQL_Command);
                    SQL_Command = "UPDATE Account Set CustomerID = '" + CustomerID + "' WHERE Username = '" + UName + "'";
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

    public boolean checkExist(String Username) {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                Statement Stmt = DBConn.createStatement();
                String SQL_Command = "SELECT * FROM Account WHERE Username ='" + Username + "'"; //SQL query command
                ResultSet Rslt = Stmt.executeQuery(SQL_Command);
                Rslt.next();
                String Customer_ID = Rslt.getString("CustomerID"); //fetch customerid based on the user name
                if (Customer_ID.isEmpty()) {
                    done = false; //if empty, then profile doesnt exist yet
                } else {
                    done = true; //if not empty, customer already made profile;
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

    public boolean updateAddress() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                Statement Stmt = DBConn.createStatement();
                String SQL_Command = "SELECT * FROM Customer WHERE CustomerID ='" + CustomerID + "'"; //SQL query command
                ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the customerid exist
                done = Rslt.next(); //if it does exist, then update customer address
                if (done) {
                    SQL_Command = "UPDATE Customer "
                            + "SET Street = '" + Street + "', " + "City = '" + City + "', " + "State = '" + State + "', " + "Zip = '" + Zip + "' "
                            + "WHERE CustomerID ='" + CustomerID + "'";
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

    public boolean updatePhone() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                Statement Stmt = DBConn.createStatement();
                String SQL_Command = "SELECT * FROM Customer WHERE CustomerID ='" + CustomerID + "'"; //SQL query command
                ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the customerid exist
                done = Rslt.next(); //if does exist, then update phone number based on id
                if (done) {
                    SQL_Command = "UPDATE Customer "
                            + "SET Phone = '" + Phone + "'"
                            + "WHERE CustomerID ='" + CustomerID + "'";
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

    public boolean updateEmail() {
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                Statement Stmt = DBConn.createStatement();
                String SQL_Command = "SELECT * FROM Customer WHERE CustomerID ='" + CustomerID + "'"; //SQL query command
                ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the customerid exist
                done = Rslt.next(); //if it does exist, then update email
                if (done) {
                    SQL_Command = "UPDATE Customer "
                            + "SET Email = '" + Email + "' "
                            + "WHERE CustomerID ='" + CustomerID + "'";
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

    public boolean recharge(float amount) {
        float newBal = amount; //add the amount to the balance. newBal calculated in RechargeControl
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                Statement Stmt = DBConn.createStatement();
                String SQL_Command = "SELECT * FROM Customer WHERE CustomerID ='" + CustomerID + "'"; //SQL query command
                ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the Customer exist
                done = Rslt.next(); //update new account balance
                if (done) {
                    SQL_Command = "UPDATE Customer "
                            + "SET Balance = " + newBal + ""
                            + "WHERE CustomerID ='" + CustomerID + "'";
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

    public boolean charge(float amount) {
        float newBal = amount; //Charge account the new balance was created in PayTollControl
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                Statement Stmt = DBConn.createStatement();
                String SQL_Command = "SELECT * FROM Customer WHERE CustomerID ='" + CustomerID + "'"; //SQL query command
                ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the Customer exist
                done = Rslt.next(); //update customer balance
                if (done) {
                    SQL_Command = "UPDATE Customer "
                            + "SET Balance = " + newBal + ""
                            + "WHERE CustomerID ='" + CustomerID + "'";
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

   
      public boolean setData() { //fetch data from DB using CustomerID
        boolean done = false;
        try {
            if (!done) {
                DBConnection ToDB = new DBConnection(); //Have a connection to the DB
                Connection DBConn = ToDB.openConn();
                Statement Stmt = DBConn.createStatement();
                String SQL_Command = "SELECT * FROM Customer WHERE CustomerID ='" + CustomerID + "'";
                ResultSet Rslt = Stmt.executeQuery(SQL_Command); // use customer id to get customer information
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
            e.printStackTrace();
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
