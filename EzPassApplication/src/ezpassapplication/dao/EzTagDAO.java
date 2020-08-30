package ezpassapplication.dao;

import ezpassapplication.model.EzTag;
import java.util.List;

public interface EzTagDAO {
    public boolean checkTag(EzTag eztag);
    public boolean addTag(EzTag eztag);
    public boolean removeTag(EzTag eztag);
    public boolean updateTagType(EzTag eztag, String NewTagType);
    public List<EzTag> getAllCustomerTag(String CustomerID);
}
