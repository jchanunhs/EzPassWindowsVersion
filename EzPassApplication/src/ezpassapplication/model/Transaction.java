package ezpassapplication.model;

public class Transaction {
    
    private String TransactionID;
    private String TagCode;
    private String TransactionDate;
    private String TransactionTime;
    private float TollAmount;
    private String TollPlaza;
    private int TollLaneNumber;
    private String CustomerID;
    
    public Transaction() {
        
    } 

    public String getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(String TransactionID) {
        this.TransactionID = TransactionID;
    }

    public String getTagCode() {
        return TagCode;
    }

    public void setTagCode(String TagCode) {
        this.TagCode = TagCode;
    }

    public String getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(String TransactionDate) {
        this.TransactionDate = TransactionDate;
    }

    public String getTransactionTime() {
        return TransactionTime;
    }

    public void setTransactionTime(String TransactionTime) {
        this.TransactionTime = TransactionTime;
    }

    public float getTollAmount() {
        return TollAmount;
    }

    public void setTollAmount(float TollAmount) {
        this.TollAmount = TollAmount;
    }

    public String getTollPlaza() {
        return TollPlaza;
    }

    public void setTollPlaza(String TollPlaza) {
        this.TollPlaza = TollPlaza;
    }

    public int getTollLaneNumber() {
        return TollLaneNumber;
    }

    public void setTollLaneNumber(int TollLaneNumber) {
        this.TollLaneNumber = TollLaneNumber;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }
    
}
