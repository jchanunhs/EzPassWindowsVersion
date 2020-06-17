package ezpassapplication.control;

import ezpassapplication.model.EzTag;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class AddTagControl {

    public AddTagControl(ActionEvent evt, String TC, String TT, String IssueD, String CID) {
        EzTag tag = new EzTag(TC, TT, IssueD, CID);
        if (TC.equals("") || TT.equals("") || IssueD.equals("")) {
            JOptionPane.showMessageDialog(null, "Add Tag failed! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        } else if (tag.addTag()) {
            JOptionPane.showMessageDialog(null, "Ez Tag was added successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error: The Ez Tag that you entered is invalid. Please try again.", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
