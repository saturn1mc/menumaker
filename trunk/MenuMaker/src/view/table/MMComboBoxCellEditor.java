/**
 * 
 */
package view.table;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

import model.MMRecipe;

/**
 * @author cmaurice2
 * 
 */
public class MMComboBoxCellEditor extends DefaultCellEditor {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 2542965773320367035L;
	
	public MMComboBoxCellEditor(MMRecipe[] items) {
		super(new JComboBox(items));
	}
}
