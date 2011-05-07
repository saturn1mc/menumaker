/**
 * 
 */
package view.table;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * @author cmaurice2
 * 
 */
public class RecipeComboBoxRenderer extends JComboBox implements
		TableCellRenderer {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = -8723920634525445376L;

	public RecipeComboBoxRenderer(String[] items) {
		super(items);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			super.setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(table.getBackground());
		}

		// Select the current value
		setSelectedItem(value);

		return this;
	}
}
