/**
 * 
 */
package view.table;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

/**
 * @author cmaurice2
 * 
 */
public class IngredientComboBoxEditor extends DefaultCellEditor {

	/**
	 * 
	 * Auto-generated SVUID
	 * 
	 */
	private static final long serialVersionUID = 2431552930970352428L;

	public IngredientComboBoxEditor(String[] items) {
		super(new JComboBox(items));
	}
}
