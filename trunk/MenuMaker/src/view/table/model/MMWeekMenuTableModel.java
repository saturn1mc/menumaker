/**
 * 
 */
package view.table.model;

import javax.swing.table.AbstractTableModel;

import model.MMBook;
import model.MMData;
import model.MMMenuElement;
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

	public static final int ROW_COUNT = MMData.PERIODS_TO_PLAN;
	public static final int COLUMN_COUNT = 5;

	public static final int COL_PERIOD = 0;
	public static final int COL_RECIPE = 1;
	public static final int COL_BOOK = 2;
	public static final int COL_PAGE = 3;
	public static final int COL_COMMENT = 4;

	private String[] columnNames = { "Meal", "Recipe", "Book", "Page",
			"Comments" };

	private String[] periods = { "Sat. lunch", "Sat. dinner", "Sun. lunch",
			"Sun. dinner", "Mon. lunch", "Mon. dinner", "Tue. lunch",
			"Tue. dinner", "Wed. lunch", "Wed. dinner", "Thu. lunch",
			"Thu. dinner", "Fri. lunch", "Fri. dinner" };

	private MMMenuElement[] data;

	public MMWeekMenuTableModel(MMMenuElement[] menu) {
		super();
		data = menu;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case COL_PERIOD:
			return String.class;

		case COL_RECIPE:
			return MMRecipe.class;

		case COL_BOOK:
			return MMBook.class;

		case COL_PAGE:
			return Integer.class;

		case COL_COMMENT:
			return String.class;

		default:
			return Object.class;
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

			MMRecipe recipe = data[rowIndex].getRecipe();

			switch (columnIndex) {
			case COL_PERIOD:
				return periods[data[rowIndex].getPeriod()];

			case COL_RECIPE:
				if (recipe != null) {
					return recipe;
				}
				else{
					return "";
				}

			case COL_BOOK:
				if (recipe != null) {
					return recipe.getBook();
				}
				else{
					return "";
				}

			case COL_PAGE:
				if (recipe != null) {
					return recipe.getPage();
				}
				else{
					return "";
				}

			case COL_COMMENT:
				return data[rowIndex].getComment();

			default:
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if ((rowIndex >= 0 && rowIndex < ROW_COUNT)
				&& (columnIndex >= 0 && columnIndex < COLUMN_COUNT)) {

			if (aValue instanceof MMRecipe) {
				data[rowIndex].setRecipe((MMRecipe) aValue);
			} else if (aValue instanceof String) {
				data[rowIndex].setComment((String) aValue);
			}

		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == COL_RECIPE || columnIndex == COL_COMMENT;
	}
}
