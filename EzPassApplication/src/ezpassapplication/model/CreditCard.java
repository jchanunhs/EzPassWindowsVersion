package ezpassapplication.model;

public class CreditCard {
    
    private String CardNumber;
    private String Name;
    private String ExpirationDate;
    private String CVV;
    private String CustomerID;
    private String Date;
    private String Time;
    private float CreditAmount;
    private String CreditID;
    
    public CreditCard() {
        
    } 

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String CardNumber) {
        this.CardNumber = CardNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(String ExpirationDate) {
        this.ExpirationDate = ExpirationDate;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public float getCreditAmount() {
        return CreditAmount;
    }

    public void setCreditAmount(float CreditAmount) {
        this.CreditAmount = CreditAmount;
    }

    public String getCreditID() {
        return CreditID;
    }

    public void setCreditID(String CreditID) {
        this.CreditID = CreditID;
    }
    
    
    
}
