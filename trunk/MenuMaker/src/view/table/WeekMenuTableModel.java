/**
 * 
 */
package view.table;

import javax.swing.table.AbstractTableModel;

/**
 * @author cmaurice2
 * 
 */
public class WeekMenuTableModel extends AbstractTableModel {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 833954043151307708L;

	private static final int ROW_COUNT = 14;
	private static final int COLUMN_COUNT = 5;

	private String[] columnNames = { "Day", "Meal", "Book", "Page", "Comments" };
	private String[][] data = { { "Sat. lunch", "", "", "", "" },
			{ "Sat. diner", "", "", "", "" }, { "Sun. lunch", "", "", "", "" },
			{ "Sun. diner", "", "", "", "" }, { "Mon. lunch", "", "", "", "" },
			{ "Mon. diner", "", "", "", "" }, { "Tue. lunch", "", "", "", "" },
			{ "Tue. diner", "", "", "", "" }, { "Wed. lunch", "", "", "", "" },
			{ "Wed. diner", "", "", "", "" }, { "Thu. lunch", "", "", "", "" },
			{ "Thu. diner", "", "", "", "" }, { "Fri. lunch", "", "", "", "" },
			{ "Fri. diner", "", "", "", "" }, };

	public WeekMenuTableModel() {
		super();
	}

	@Override
	public int getRowCount() {
		return ROW_COUNT;
	}

	@Override
	public int getColumnCount() {
		return COLUMN_COUNT;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		data[rowIndex][columnIndex] = aValue.toString();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 1 || columnIndex == 4;
	}
}
