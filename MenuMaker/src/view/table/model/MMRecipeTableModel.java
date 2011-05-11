/**
 * 
 */
package view.table.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import model.MMRecipe;
import model.MMUnit;

/**
 * @author cmaurice2
 * 
 */
public class MMRecipeTableModel extends AbstractTableModel {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 833954043151307708L;

	public static final int COLUMN_COUNT = 3;

	public static final int COL_RECIPE = 0;
	public static final int COL_BOOK = 1;
	public static final int COL_PAGE = 2;

	private String[] columnNames = { "Recipe", "Book", "Page" };

	private ArrayList<MMRecipe> data;
	
	public MMRecipeTableModel(final Collection<MMRecipe> recipes) {
		super();
		this.data = new ArrayList<MMRecipe>(recipes);
		sortData();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case COL_RECIPE:
			return MMRecipe.class;

		case COL_BOOK:
			return MMUnit.class;

		case COL_PAGE:
			return Integer.class;

		default:
			return Object.class;
		}
	}
	
	public void sortData(){
		Collections.sort(data);
		fireTableChanged(new TableModelEvent(this));
	}

	public void addRow(MMRecipe recipe) {
		data.add(recipe);
		fireTableChanged(new TableModelEvent(this));
	}
	
	public void removeRow(MMRecipe recipe){
		data.remove(recipe);
		fireTableChanged(new TableModelEvent(this));
	}
	
	public MMRecipe getRowElement(int row){
		if(row >= 0 && row < data.size()){
			return data.get(row);
		}
		else{
			return null;
		}
	}
	
	public int getRowOf(MMRecipe recipe){
		return data.indexOf(recipe);
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

			MMRecipe recipe = data.get(rowIndex);

			switch(columnIndex){
				case COL_RECIPE:
					return recipe;

				case COL_BOOK:
					return recipe.getBook();

				case COL_PAGE:
					return recipe.getPage();

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
