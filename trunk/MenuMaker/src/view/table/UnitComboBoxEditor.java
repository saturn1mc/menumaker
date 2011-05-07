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
public class UnitComboBoxEditor extends DefaultCellEditor {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 3817384165509056229L;

	public UnitComboBoxEditor(String[] items) {
		super(new JComboBox(items));
	}
}
