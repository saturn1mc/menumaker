/**
 * 
 */
package view.table.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import model.MMUnit;

/**
 * @author cmaurice2
 * 
 */
public class MMUnitTableModel extends AbstractTableModel {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 5709250737422667596L;
	
	private static final int COLUMN_COUNT = 1;

	public static int COL_NAME = 0;

	private String[] columnNames = { "Unit" };
	private ArrayList<MMUnit> data;

	public MMUnitTableModel(final Collection<MMUnit> units) {
		super();
		data = new ArrayList<MMUnit>(units);
		sortData();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}
	
	public void sortData(){
		Collections.sort(data);
		fireTableChanged(new TableModelEvent(this));
	}

	public void addRow(MMUnit unit) {
		data.add(unit);
		fireTableChanged(new TableModelEvent(this));
	}
	
	public void removeRow(MMUnit unit){
		data.remove(unit);
		fireTableChanged(new TableModelEvent(this));
	}
	
	public MMUnit getRowElement(int row){
		if(row >= 0 && row < data.size()){
			return data.get(row);
		}
		else{
			return null;
		}
	}
	
	public int getRowOf(MMUnit unit){
		return data.indexOf(unit);
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
		if (column == 0) {
			return columnNames[column];
		} else {
			return null;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if ((rowIndex >= 0 && rowIndex < getRowCount())
				&& (columnIndex == 0)) {
			
			return data.get(rowIndex).getName();
			
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
