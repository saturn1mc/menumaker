/**
 * 
 */
package view.table.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import model.MMIngredient;
import model.MMUnit;

/**
 * @author cmaurice2
 * 
 */
public class MMIngredientTableModel extends AbstractTableModel {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 833954043151307708L;

	public static final int COLUMN_COUNT = 3;

	public static final int COL_INGREDIENT = 0;
	public static final int COL_UNIT = 1;
	public static final int COL_SHOP = 2;

	private String[] columnNames = { "Ingredient", "Unit", "Shop Point" };

	private ArrayList<MMIngredient> data;

	public MMIngredientTableModel(final Collection<MMIngredient> ingredients) {
		super();
		this.data = new ArrayList<MMIngredient>(ingredients);
		sortData();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case COL_INGREDIENT:
			return MMIngredient.class;

		case COL_UNIT:
			return MMUnit.class;

		case COL_SHOP:
			return MMIngredient.class;

		default:
			return Object.class;
		}
	}
	
	public void sortData(){
		Collections.sort(data);
		fireTableChanged(new TableModelEvent(this));
	}

	public void addRow(MMIngredient ingredient) {
		data.add(ingredient);
		fireTableChanged(new TableModelEvent(this));
	}
	
	public void removeRow(MMIngredient ingredient){
		data.remove(ingredient);
		fireTableChanged(new TableModelEvent(this));
	}
	
	public MMIngredient getRowElement(int row){
		if(row >= 0 && row < data.size()){
			return data.get(row);
		}
		else{
			return null;
		}
	}
	
	public int getRowOf(MMIngredient ingredient){
		return data.indexOf(ingredient);
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

			MMIngredient ingredient = data.get(rowIndex);

			switch(columnIndex){
				case COL_INGREDIENT:
					return ingredient;

				case COL_UNIT:
					return ingredient.getUnit();

				case COL_SHOP:
					return ingredient.getShopPoint();

				default:
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
