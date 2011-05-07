/**
 * 
 */
package view.table;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

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
		String[] items = {"A", "B", "C"};
		
		TableColumn mealColumn = this.getColumnModel().getColumn(1);
		mealColumn.setCellEditor(new RecipeComboBoxEditor(items));
		mealColumn.setCellRenderer(new RecipeComboBoxRenderer(items));
	}
}
