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
        if (tag.checkTag() && tag.removeTag()) {
            JOptionPane.showMessageDialog(null, "Remove Tag is successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Remove Tag failed!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
