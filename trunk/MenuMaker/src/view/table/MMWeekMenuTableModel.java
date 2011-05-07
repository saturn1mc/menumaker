/**
 * 
 */
package view.table;

import javax.swing.table.AbstractTableModel;

import model.MMRecipe;

/**
 * @author cmaurice2
 * 
 */
public class MMWeekMenuTableModel extends AbstractTableModel {

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

	public MMWeekMenuTableModel() {
		super();
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex == 1){
			return MMRecipe.class;
		}
		else{
			return String.class;
		}
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
		if (column >= 0 && column < 5) {
			return columnNames[column];
		} else {
			return null;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if ((rowIndex >= 0 && rowIndex < 14)
				&& (columnIndex >= 0 && columnIndex < 5)) {
			return data[rowIndex][columnIndex];
		} else {
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if ((rowIndex >= 0 && rowIndex < 14)
				&& (columnIndex >= 0 && columnIndex < 5)) {
			data[rowIndex][columnIndex] = aValue.toString();
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 1 || columnIndex == 4;
	}
}
