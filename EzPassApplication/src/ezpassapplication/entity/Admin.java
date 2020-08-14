package ezpassapplication.entity;

public class Admin {
    
    private String AdminID;
    private String Name;
    private String Password;
    
    public Admin() {
        
    } 

    public String getAdminID() {
        return AdminID;
    }

    public void setAdminID(String AdminID) {
        this.AdminID = AdminID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
}
