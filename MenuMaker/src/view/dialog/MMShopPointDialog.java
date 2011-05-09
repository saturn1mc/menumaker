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
public class MMShopPointDialog extends JDialog {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = -3499843926514554167L;

	private MenuMakerGUI parent;
	
	public MMShopPointDialog(MenuMakerGUI parent) {
		super(parent, "Manage shop points");
		this.parent = parent;
	}
}
