package ezpassapplication.control;

import ezpassapplication.dao.EzTagDAO;
import ezpassapplication.service.EzTagService;
import ezpassapplication.model.EzTag;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class RemoveTagControl {

    public RemoveTagControl(ActionEvent evt, String TC, String CID) {
        EzTagDAO eztagdao = new EzTagService();
        EzTag eztag = new EzTag();
        eztag.setTagCode(TC);
        eztag.setCustomerID(CID);

        if (eztagdao.removeTag(eztag)) {//attempt to remove tag
            JOptionPane.showMessageDialog(null, "EzTag was removed successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        } else if (!eztagdao.checkTag(eztag)) { //check if tag code is valid
            JOptionPane.showMessageDialog(null, "Error: The tag code you entered is invalid", "Confirmation", JOptionPane.ERROR_MESSAGE);
        } else { //Remove tag fails if user already used it to pay tolls or tag is currently associated with a vehicle
            JOptionPane.showMessageDialog(null, "Error: Either EzTag is currently associated with a vehicle or has been used to pay tolls in the past. If you believe this is a mistake please contact help desk.", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
