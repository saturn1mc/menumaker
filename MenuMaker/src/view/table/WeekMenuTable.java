/**
 * 
 */
package view.table;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;

import model.Recipe;

/**
 * @author cmaurice2
 * 
 */
public class WeekMenuTable extends JTable {
	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 6404860223050257081L;

	public WeekMenuTable() {
		super(new WeekMenuTableModel());
		Recipe[] items = { new Recipe("Recette A", null, 0),
				new Recipe("Recette B", null, 0),
				new Recipe("Recette C", null, 0) };

		this.getColumnModel().getColumn(1)
				.setCellEditor(new ComboBoxCellEditor(items));
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		super.setValueAt(aValue, row, column);
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		super.tableChanged(e);
	}
}
