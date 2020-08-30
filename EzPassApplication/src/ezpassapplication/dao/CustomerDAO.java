package ezpassapplication.dao;

import ezpassapplication.model.Customer;

public interface CustomerDAO {
    public boolean createProfile(Customer customer, String Username);
    public boolean updateAddress(Customer customer, String NewStreet, String NewCity, String NewState, String NewZip);
    public boolean updatePhone(Customer customer, String NewPhone);
    public boolean updateEmail(Customer customer, String NewEmail);
    public boolean updateBalance(Customer customer, float NewBalance);
    public boolean deleteCustomer(Customer customer);
    public Customer getCustomerInformation(String CustomerID);
}
