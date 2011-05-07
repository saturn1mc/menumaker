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
public class RecipeComboBoxEditor extends DefaultCellEditor {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 2542965773320367035L;

	public RecipeComboBoxEditor(String[] items) {
		super(new JComboBox(items));
	}
}
