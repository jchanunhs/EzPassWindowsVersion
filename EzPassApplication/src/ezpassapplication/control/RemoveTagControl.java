package ezpassapplication.control;

import ezpassapplication.model.EzTag;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class RemoveTagControl {

    public RemoveTagControl(ActionEvent evt, String TC, String CID) {
        EzTag tag = new EzTag(TC, CID); //ez tag object contains the tag code and customer ID

        //Remove tag and check if tag belongs to customer
        //Note: tag will not remove unless tag code cid = current customer cid, aka owner owns this tag code
        if (tag.removeTag()) {
            JOptionPane.showMessageDialog(null, "Remove Tag is successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        } else if (!tag.checkTag()) {
            JOptionPane.showMessageDialog(null, "Error: The tag code you entered is invalid", "Confirmation", JOptionPane.ERROR_MESSAGE);
        } else { //Remove tag fails if user already used it to pay tolls or tag is currently associated with a vehicle
            JOptionPane.showMessageDialog(null, "Error: Unable to remove tag code. This error occurs when: \n"
                    + "1. Tag code was used to pay toll\n"
                    + "2. Tag code is linked to a vehicle: you can remove vehicle associated with tag code and try again\n", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}