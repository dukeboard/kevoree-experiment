package rbacTextualEditor.editor.graphicComponents;


import java.io.File;
import java.io.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author obendavi
 */
public class FileChooser {

    private JFileChooser chooser;
    public FileChooser(JFrame parent) {
        chooser = new JFileChooser();       
        int returnVal = chooser.showOpenDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: "
                    + chooser.getSelectedFile().getName());
        }
    }

    /**
     * @return the chooser
     */
    public JFileChooser getChooser() {
        return chooser;
    }

    /**
     * @param chooser the chooser to set
     */
    public void setChooser(JFileChooser chooser) {
        this.chooser = chooser;
    }
}