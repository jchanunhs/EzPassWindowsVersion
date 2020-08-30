package ezpassapplication.dao;

import ezpassapplication.model.CreditCard;
import java.util.List;

public interface CreditCardDAO {
    public String addCreditCardTransaction(CreditCard credit);
    public List<CreditCard> getAllTransactions(String CustomerID);
}
