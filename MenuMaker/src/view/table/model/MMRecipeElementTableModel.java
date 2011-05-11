/**
 * 
 */
package view.table.model;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import model.MMIngredient;
import model.MMRecipeElement;
import model.MMUnit;

/**
 * @author cmaurice2
 * 
 */
public class MMRecipeElementTableModel extends AbstractTableModel {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 833954043151307708L;

	public static final int COLUMN_COUNT = 3;

	public static final int COL_INGREDIENT = 0;
	public static final int COL_UNIT = 1;
	public static final int COL_QUANTITY = 2;

	private String[] columnNames = { "Ingredient", "Unit", "Quantity" };

	private ArrayList<MMRecipeElement> data;

	public MMRecipeElementTableModel(ArrayList<MMRecipeElement> recipeElements) {
		super();

		if (recipeElements != null) {
			this.data = recipeElements;
		} else {
			this.data = new ArrayList<MMRecipeElement>();
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case COL_QUANTITY:
			return Float.class;

		case COL_UNIT:
			return MMUnit.class;

		case COL_INGREDIENT:
			return MMIngredient.class;

		default:
			return Object.class;
		}
	}

	public ArrayList<MMRecipeElement> getData() {
		return data;
	}

	public void sortData() {
		Collections.sort(data);
		fireTableChanged(new TableModelEvent(this));
	}

	public void addRow(MMRecipeElement recipeElement) {
		data.add(recipeElement);
		fireTableChanged(new TableModelEvent(this));
	}

	public void removeRow(MMRecipeElement recipeElement) {
		data.remove(recipeElement);
		fireTableChanged(new TableModelEvent(this));
	}

	public MMRecipeElement getRowElement(int row) {
		if (row >= 0 && row < data.size()) {
			return data.get(row);
		} else {
			return null;
		}
	}

	public int getRowOf(MMRecipeElement recipeElement) {
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

			MMRecipeElement element = data.get(rowIndex);

			switch (columnIndex) {
			case COL_QUANTITY:
				return element.getQuantity();

			case COL_UNIT:
				return element.getIngredient().getUnit();

			case COL_INGREDIENT:
				return element.getIngredient();

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
			if (aValue instanceof Float) {
				data.get(rowIndex).setQuantity(
						Float.parseFloat(aValue.toString()));
			} else if (aValue instanceof MMIngredient) {
				data.get(rowIndex).setIngredient((MMIngredient) aValue);
			}
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == COL_INGREDIENT || columnIndex == COL_QUANTITY;
	}
}
