package ezpassapplication.control;
import ezpassapplication.model.EzTag;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class AddTagControl {

    public AddTagControl(ActionEvent evt, String TC, String TT, String IssueD, String CID) {
        EzTag tag = new EzTag(TC, TT, IssueD, CID); //call ez tag object
        //add the tag to db
        if(TC.equals("") || TT.equals("") || IssueD.equals("")){
            JOptionPane.showMessageDialog(null, "Add Tag failed! Please fill out all information!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
        else if (tag.addTag()) {
            JOptionPane.showMessageDialog(null, "Add Tag is successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            JComponent component = (JComponent) evt.getSource();
                Window win = SwingUtilities.getWindowAncestor(component);
                win.dispose();
        }
        else {
            JOptionPane.showMessageDialog(null, "Add Tag failed!", "Confirmation", JOptionPane.ERROR_MESSAGE);
        }
    }
}
