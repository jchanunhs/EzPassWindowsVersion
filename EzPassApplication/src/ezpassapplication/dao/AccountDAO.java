package ezpassapplication.dao;

import ezpassapplication.model.Account;

public interface AccountDAO {
    public boolean signUp(Account account);
    public Account getAccountInformation(String Username, String Password);
    public boolean updatePassword(Account account, String NewPassword);
}
