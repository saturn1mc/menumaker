/**
 * 
 */
package view.table;

import java.util.ArrayList;

import javax.swing.JTable;

import model.MMIngredient;
import view.dialog.MMIngredientDialog;
import view.table.model.MMIngredientTableModel;
import view.table.renderer.MMTableCellRenderer;

/**
 * @author cmaurice2
 * 
 */
public class MMIngredientTable extends JTable {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = -2250924647556136366L;

	public MMIngredientTable(MMIngredientDialog parent) {
		this.setDefaultRenderer(Object.class, new MMTableCellRenderer());
		this.setModel(new MMIngredientTableModel(parent.getIngredientList()));
	}

	public void sortData(){
		((MMIngredientTableModel) getModel()).sortData();
	}
	
	public void setFocusOn(MMIngredient ingredient) {
		this.changeSelection(((MMIngredientTableModel) getModel()).getRowOf(ingredient), 0, false, false);
		this.requestFocus();
	}
	
	public void addRow(MMIngredient ingredient) {
		((MMIngredientTableModel) getModel()).addRow(ingredient);
		sortData();
		setFocusOn(ingredient);
	}

	public void removeRow(MMIngredient ingredient) {
		((MMIngredientTableModel) getModel()).removeRow(ingredient);
	}

	public MMIngredient getFirstSelectedItem() {
		return ((MMIngredientTableModel) getModel()).getRowElement(getSelectedRow());
	}

	public ArrayList<MMIngredient> getSelectedItems() {
		ArrayList<MMIngredient> selectedIngredients = new ArrayList<MMIngredient>();
		int[] rows = getSelectedRows();

		for (int row : rows) {
			selectedIngredients.add(((MMIngredientTableModel) getModel())
					.getRowElement(row));
		}

		return selectedIngredients;
	}
}
