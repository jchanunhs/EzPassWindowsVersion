package ezpassapplication.entity;

public class Vehicle {
    
    private String LicensePlateNumber;
    private String Make;
    private String Model;
    private String Year;
    private String Color;
    private String TagCode;
    private String CustomerID;
    
    public Vehicle() {
        
    } 

    public String getLicensePlateNumber() {
        return LicensePlateNumber;
    }

    public void setLicensePlateNumber(String LicensePlateNumber) {
        this.LicensePlateNumber = LicensePlateNumber;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String Make) {
        this.Make = Make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getTagCode() {
        return TagCode;
    }

    public void setTagCode(String TagCode) {
        this.TagCode = TagCode;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }
    
}
