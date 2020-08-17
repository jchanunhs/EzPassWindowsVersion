package ezpassapplication.control;

import ezpassapplication.dao.EzTagDAO;
import ezpassapplication.entity.EzTag;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class AddTagControl {

    public AddTagControl(ActionEvent evt, String TC, String TT, String CID) {
        EzTagDAO tagdao = new EzTagDAO();
        EzTag tag = new EzTag();
        tag.setCustomerID(CID);
        tag.setTagCode(TC);
        tag.setTagType(TT);
        if (tagdao.addTag(tag)) {
            JOptionPane.showMessageDialog(null, "Ez Tag was added successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error: The Ez Tag that you entered is taken. Please try another tag code.", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
