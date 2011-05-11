/**
 * 
 */
package view.table.model;

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

	public static final int ROW_COUNT = 14;
	public static final int COLUMN_COUNT = 5;

	public static int COL_DAY = 0; 
	public static int COL_MEAL = 1;
	public static int COL_BOOK = 2;
	public static int COL_PAGE = 3;
	public static int COL_COMMENTS = 4;
	
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
		if(columnIndex == COL_MEAL){
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
		if (column >= 0 && column < COLUMN_COUNT) {
			return columnNames[column];
		} else {
			return null;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if ((rowIndex >= 0 && rowIndex < ROW_COUNT)
				&& (columnIndex >= 0 && columnIndex < COLUMN_COUNT)) {
			return data[rowIndex][columnIndex];
		} else {
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if ((rowIndex >= 0 && rowIndex < ROW_COUNT)
				&& (columnIndex >= 0 && columnIndex < COLUMN_COUNT)) {
			data[rowIndex][columnIndex] = aValue.toString();
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == COL_MEAL || columnIndex == COL_COMMENTS;
	}
}
