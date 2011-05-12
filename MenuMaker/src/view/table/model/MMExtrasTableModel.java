/**
 * 
 */
package view.table.model;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import model.MMExtra;
import model.MMIngredient;
import model.MMUnit;

/**
 * @author cmaurice2
 * 
 */
public class MMExtrasTableModel extends AbstractTableModel {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 833954043151307708L;

	public static final int COLUMN_COUNT = 4;

	public static final int COL_INGREDIENT = 0;
	public static final int COL_UNIT = 1;
	public static final int COL_QUANTITY = 2;
	public static final int COL_COMMENT = 3;

	private String[] columnNames = { "Extra", "Unit", "Quantity", "Comment" };

	private ArrayList<MMExtra> data;

	public MMExtrasTableModel(ArrayList<MMExtra> extras) {
		super();

		if (extras != null) {
			this.data = new ArrayList<MMExtra>(extras);
		} else {
			this.data = new ArrayList<MMExtra>();
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case COL_QUANTITY:
			return Double.class;

		case COL_UNIT:
			return MMUnit.class;

		case COL_INGREDIENT:
			return MMIngredient.class;
		
		case COL_COMMENT :
			return String.class;

		default:
			return Object.class;
		}
	}

	public ArrayList<MMExtra> getData() {
		return data;
	}

	public void sortData() {
		Collections.sort(data);
		fireTableChanged(new TableModelEvent(this));
	}

	public void addRow(MMExtra extra) {
		data.add(extra);
		fireTableChanged(new TableModelEvent(this));
	}

	public void removeRow(MMExtra extra) {
		data.remove(extra);
		fireTableChanged(new TableModelEvent(this));
	}

	public MMExtra getRowElement(int row) {
		if (row >= 0 && row < data.size()) {
			return data.get(row);
		} else {
			return null;
		}
	}

	public int getRowOf(MMExtra recipeElement) {
		return data.indexOf(recipeElement);
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
		if ((rowIndex >= 0 && rowIndex < data.size())
				&& (columnIndex >= 0 && columnIndex < COLUMN_COUNT)) {

			MMExtra extra = data.get(rowIndex);

			switch (columnIndex) {
			case COL_QUANTITY:
				return extra.getQuantity();

			case COL_UNIT:
				return extra.getIngredient().getUnit();

			case COL_INGREDIENT:
				return extra.getIngredient();
				
			case COL_COMMENT:
				return extra.getComment();

			default:
				return null;
			}

		} else {
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if ((rowIndex >= 0 && rowIndex < data.size())
				&& (columnIndex >= 0 && columnIndex < COLUMN_COUNT)) {
			if (aValue instanceof Double) {
				data.get(rowIndex).setQuantity(
						Double.parseDouble(aValue.toString()));
			} else if (aValue instanceof MMIngredient) {
				data.get(rowIndex).setIngredient((MMIngredient) aValue);
			}else if (aValue instanceof String) {
				data.get(rowIndex).setComment((String) aValue);
			}
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == COL_INGREDIENT || columnIndex == COL_QUANTITY || columnIndex == COL_COMMENT;
	}
}
