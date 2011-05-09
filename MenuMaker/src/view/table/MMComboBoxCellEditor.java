/**
 * 
 */
package view.table;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Arrays;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 * @author cmaurice2
 * 
 */
public class MMComboBoxCellEditor extends AbstractCellEditor implements
		TableCellEditor, ActionListener, Serializable {

	private JComboBox comboBox;

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 2542965773320367035L;

	public MMComboBoxCellEditor(Object[] items) {
		Arrays.sort(items);

		comboBox = new JComboBox(items);
		comboBox.addActionListener(this);
		comboBox.setEditable(true);
	}

	private void findClosestMatch() {
		String inputStr = comboBox.getSelectedItem().toString();

		for (int i = 0; i < comboBox.getItemCount(); i++) {
			Object obj = comboBox.getItemAt(i);
			comboBox.setSelectedItem(obj);

			if (obj.toString().toLowerCase().compareTo(inputStr.toLowerCase()) >= 0) {
				break;
			}
		}
	}

	@Override
	public Object getCellEditorValue() {
		return comboBox.getSelectedItem();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {

		comboBox.setSelectedItem(value);

		return comboBox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		findClosestMatch();
	}
}
