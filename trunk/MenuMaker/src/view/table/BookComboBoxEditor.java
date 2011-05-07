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
public class BookComboBoxEditor extends DefaultCellEditor {
	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = -5106018687667841360L;

	public BookComboBoxEditor(String[] items) {
		super(new JComboBox(items));
	}
}
