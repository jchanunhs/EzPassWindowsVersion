package ezpassapplication.model;

public class EzTag {
    
    private String TagCode;
    private String TagType;
    private String IssueDate;
    private String CustomerID;
    
    public EzTag() {
        
    } 

    public String getTagCode() {
        return TagCode;
    }

    public void setTagCode(String TagCode) {
        this.TagCode = TagCode;
    }

    public String getTagType() {
        return TagType;
    }

    public void setTagType(String TagType) {
        this.TagType = TagType;
    }

    public String getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(String IssueDate) {
        this.IssueDate = IssueDate;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }
    
}
