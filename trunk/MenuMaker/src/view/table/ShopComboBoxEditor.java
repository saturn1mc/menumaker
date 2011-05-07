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
public class ShopComboBoxEditor extends DefaultCellEditor {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = -5080319565958538298L;

	public ShopComboBoxEditor(String[] items) {
		super(new JComboBox(items));
	}
}
