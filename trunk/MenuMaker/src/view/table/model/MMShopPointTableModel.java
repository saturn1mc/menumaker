/**
 * 
 */
package view.table.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import model.MMShopPoint;

/**
 * @author cmaurice2
 * 
 */
public class MMShopPointTableModel extends AbstractTableModel {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 5709250737422667596L;
	
	private static final int COLUMN_COUNT = 2;

	public static int COL_NAME = 0;
	public static int COL_PRIORITY = 1;

	private String[] columnNames = { "Name", "Priority" };
	private ArrayList<MMShopPoint> data;

	public MMShopPointTableModel(final Collection<MMShopPoint> shopPoints) {
		super();
		data = new ArrayList<MMShopPoint>(shopPoints);
		sortData();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex == COL_NAME){
			return String.class;
		}
		else if(columnIndex == COL_PRIORITY){
			return Integer.class;
		}
		else{
			return Object.class;
		}
	}
	
	public void sortData(){
		Collections.sort(data);
		fireTableChanged(new TableModelEvent(this));
	}

	public void addRow(MMShopPoint shopPoint) {
		data.add(shopPoint);
		fireTableChanged(new TableModelEvent(this));
	}
	
	public void removeRow(MMShopPoint shopPoint){
		data.remove(shopPoint);
		fireTableChanged(new TableModelEvent(this));
	}
	
	public MMShopPoint getRowElement(int row){
		if(row >= 0 && row < data.size()){
			return data.get(row);
		}
		else{
			return null;
		}
	}
	
	public int getRowOf(MMShopPoint shopPoint){
		return data.indexOf(shopPoint);
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
			else if(columnIndex == COL_PRIORITY){
				return data.get(rowIndex).getPriority();
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
