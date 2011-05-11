/**
 * 
 */
package view.combobox;

import java.awt.Component;
import java.io.Serializable;
import java.util.Arrays;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;


/**
 * @author cmaurice2
 * 
 */
public class MMComboBoxCellEditor extends AbstractCellEditor implements
		TableCellEditor, Serializable {

	private MMAutoCompleteComboBox autoComboBox;

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 2542965773320367035L;

	public MMComboBoxCellEditor(Object[] items) {
		Arrays.sort(items);
		this.autoComboBox = new MMAutoCompleteComboBox(this, items);
	}
	
	@Override
	public boolean stopCellEditing() {
		return super.stopCellEditing();
	}
	
	@Override
	public Object getCellEditorValue() {
		return autoComboBox.getComboBox().getSelectedItem();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {

		autoComboBox.getComboBox().setSelectedItem(value);

		return autoComboBox.getComboBox();
	}
}
