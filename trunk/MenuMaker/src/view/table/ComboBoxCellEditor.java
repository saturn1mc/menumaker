/**
 * 
 */
package view.table;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

import model.Recipe;

/**
 * @author cmaurice2
 * 
 */
public class ComboBoxCellEditor extends DefaultCellEditor {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 2542965773320367035L;
	
	public ComboBoxCellEditor(Recipe[] items) {
		super(new JComboBox(items));
	}
}
