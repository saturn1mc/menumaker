/**
 * 
 */
package view.table.model;

import java.util.ArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import model.MMBook;

/**
 * @author cmaurice2
 * 
 */
public class MMBookTableModel extends AbstractTableModel {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 5709250737422667596L;
	
	private static final int COLUMN_COUNT = 2;

	public static int COL_NAME = 0;
	public static int COL_AUTHOR = 1;

	private String[] columnNames = { "Name", "Author" };
	private ArrayList<MMBook> data;

	public MMBookTableModel() {
		super();
		data = new ArrayList<MMBook>();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public void addRow(MMBook book) {
		data.add(book);
		fireTableChanged(new TableModelEvent(this));
	}
	
	public void removeRow(MMBook book){
		data.remove(book);
		fireTableChanged(new TableModelEvent(this));
	}

	@Override
	public int getRowCount() {
		return data.size();
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
		if ((rowIndex >= 0 && rowIndex < getRowCount())
				&& (columnIndex >= 0 && columnIndex < COLUMN_COUNT)) {
			
			if(columnIndex == COL_NAME){
				return data.get(rowIndex).getName();
			}
			else if(columnIndex == COL_AUTHOR){
				return data.get(rowIndex).getAuthor();
			}
			else{
				return null;
			}
			
		} else {
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		//Nothing
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
}
