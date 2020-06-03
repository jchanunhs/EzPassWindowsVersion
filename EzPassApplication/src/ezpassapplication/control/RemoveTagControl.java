package ezpassapplication.control;

import ezpassapplication.model.EzTag;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class RemoveTagControl {

    public RemoveTagControl(ActionEvent evt, String TC, String CID) {
        EzTag tag = new EzTag(TC, CID);

        if (TC.equals("")) {
            JOptionPane.showMessageDialog(null, "Remove tag failed! Plese enter a tag code!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        } else if (tag.removeTag()) {//attempt to remove tag
            JOptionPane.showMessageDialog(null, "Remove Tag is successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        } else if (!tag.checkTag()) { //check if tag code is valid
            JOptionPane.showMessageDialog(null, "Error: The tag code you entered is invalid", "Confirmation", JOptionPane.ERROR_MESSAGE);
        } else { //Remove tag fails if user already used it to pay tolls or tag is currently associated with a vehicle
            JOptionPane.showMessageDialog(null, "Error: Unable to remove tag code. This error occurs when: \n"
                    + "1. Tag code was used to pay toll\n"
                    + "2. Tag code is linked to a vehicle: you can remove vehicle associated with tag code and try again\n", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
