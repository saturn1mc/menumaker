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
public class MMIngredientDialog extends JDialog {
	
	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = -8385693884884420422L;
	
	private MenuMakerGUI parent;
	
	public MMIngredientDialog(MenuMakerGUI parent) {
		super(parent, "Manage ingredients");
		this.parent = parent;
	}
}
