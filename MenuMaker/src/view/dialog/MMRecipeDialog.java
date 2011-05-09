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
public class MMRecipeDialog extends JDialog {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = -7971399639918626521L;

	private MenuMakerGUI parent;
	
	public MMRecipeDialog(MenuMakerGUI parent) {
		super(parent, "Manage recipes");
		this.parent = parent;
	}
}
