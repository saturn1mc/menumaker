/**
 * 
 */
package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Arrays;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import view.combobox.MMAutoCompleteComboBox;

/**
 * @author cmaurice2
 * 
 */
public class MMComboBoxCellEditor extends AbstractCellEditor implements
		TableCellEditor, ActionListener, Serializable {

	private MMAutoCompleteComboBox autoComboBox;

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 2542965773320367035L;

	public MMComboBoxCellEditor(Object[] items) {
		Arrays.sort(items);

		JComboBox jcomboBox = new JComboBox(items);
		jcomboBox.addActionListener(this);
		jcomboBox.setEditable(true);
		
		this.autoComboBox = new MMAutoCompleteComboBox(jcomboBox);
	}

	private void findClosestMatch() {
//		String inputStr = comboBox.getSelectedItem().toString();
//
//		for (int i = 0; i < comboBox.getItemCount(); i++) {
//			Object obj = comboBox.getItemAt(i);
//			comboBox.setSelectedItem(obj);
//
//			if (obj.toString().toLowerCase().compareTo(inputStr.toLowerCase()) >= 0) {
//				break;
//			}
//		}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		findClosestMatch();
	}
}
