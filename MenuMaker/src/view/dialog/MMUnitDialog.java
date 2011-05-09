/**
 * 
 */
package view.dialog;

import javax.swing.JDialog;

import view.MenuMakerGUI;

/**
 * @author cmaurice2
 *
 */
public class MMUnitDialog extends JDialog {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 6802997599286662355L;

	private MenuMakerGUI parent;
	
	public MMUnitDialog(MenuMakerGUI parent) {
		super(parent, "Manage units");
		this.parent = parent;
	}
}
