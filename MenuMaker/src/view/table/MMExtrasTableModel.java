/**
 * 
 */
package view.table;

import java.util.ArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import model.MMIngredient;

/**
 * @author cmaurice2
 * 
 */
public class MMExtrasTableModel extends AbstractTableModel {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 5709250737422667596L;

	private int rowCount;
	private static final int COLUMN_COUNT = 3;

	public static int COL_NUM = 0;
	public static int COL_EXTRA = 1;
	public static int COL_COMMENT = 2;

	private String[] columnNames = { "#", "Extra", "Comments" };
	private ArrayList<String[]> data;

	public MMExtrasTableModel() {
		super();
		rowCount = 0;
		data = new ArrayList<String[]>();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == COL_EXTRA) {
			return MMIngredient.class;
		} else {
			return String.class;
		}
	}

	public void addRow() {

		rowCount++;

		String[] row = new String[COLUMN_COUNT];
		row[COL_NUM] = new String("Extra " + rowCount);
		row[COL_EXTRA] = new String();
		row[COL_COMMENT] = new String();

		data.add(row);
		
		fireTableChanged(new TableModelEvent(this));
	}

	@Override
	public int getRowCount() {
		return rowCount;
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
		if ((rowIndex >= 0 && rowIndex < rowCount)
				&& (columnIndex >= 0 && columnIndex < COLUMN_COUNT)) {
			return data.get(rowIndex)[columnIndex];
		} else {
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if ((rowIndex >= 0 && rowIndex < rowCount)
				&& (columnIndex >= 0 && columnIndex < COLUMN_COUNT)) {
			data.get(rowIndex)[columnIndex] = aValue.toString();
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex != COL_NUM;
	}
}
