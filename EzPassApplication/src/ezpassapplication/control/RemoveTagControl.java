package ezpassapplication.control;

import ezpassapplication.model.EzTag;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class RemoveTagControl {

    public RemoveTagControl(ActionEvent evt, String TC, String CID) {
        EzTag tag = new EzTag(TC, CID);

        if (tag.removeTag()) {//attempt to remove tag
            JOptionPane.showMessageDialog(null, "EzTag was removed successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        } else if (!tag.checkTag()) { //check if tag code is valid
            JOptionPane.showMessageDialog(null, "Error: The tag code you entered is invalid", "Confirmation", JOptionPane.ERROR_MESSAGE);
        } else { //Remove tag fails if user already used it to pay tolls or tag is currently associated with a vehicle
            JOptionPane.showMessageDialog(null, "Error: Either EzTag is currently associated with a vehicle or has been used to pay tolls in the past. If you believe this is a mistake please contact help desk.", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
