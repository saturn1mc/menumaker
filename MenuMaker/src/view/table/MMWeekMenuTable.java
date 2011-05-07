/**
 * 
 */
package view.table;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;

import model.MMRecipe;

/**
 * @author cmaurice2
 * 
 */
public class MMWeekMenuTable extends JTable {
	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 6404860223050257081L;

	public MMWeekMenuTable() {
		super(new MMWeekMenuTableModel());
		MMRecipe[] items = { new MMRecipe("Recette A", null, 0),
				new MMRecipe("Recette B", null, 0),
				new MMRecipe("Recette C", null, 0) };

		this.getColumnModel().getColumn(1)
				.setCellEditor(new MMComboBoxCellEditor(items));
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
