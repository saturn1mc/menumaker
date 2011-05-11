/**
 * 
 */
package view.table;

import java.util.ArrayList;

import javax.swing.JTable;

import model.MMRecipe;
import view.dialog.MMRecipeDialog;
import view.table.model.MMRecipeTableModel;
import view.table.renderer.MMTableCellRenderer;

/**
 * @author cmaurice2
 * 
 */
public class MMRecipeTable extends JTable {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = -2250924647556136366L;

	public MMRecipeTable(MMRecipeDialog parent) {
		this.setDefaultRenderer(Object.class, new MMTableCellRenderer());
		this.setModel(new MMRecipeTableModel(parent.getRecipeList()));
	}

	public void sortData(){
		((MMRecipeTableModel) getModel()).sortData();
	}
	
	public void setFocusOn(MMRecipe recipe) {
		this.changeSelection(((MMRecipeTableModel) getModel()).getRowOf(recipe), 0, false, false);
		this.requestFocus();
	}
	
	public void addRow(MMRecipe recipe) {
		((MMRecipeTableModel) getModel()).addRow(recipe);
		sortData();
		setFocusOn(recipe);
	}

	public void removeRow(MMRecipe recipe) {
		((MMRecipeTableModel) getModel()).removeRow(recipe);
	}

	public MMRecipe getFirstSelectedItem() {
		return ((MMRecipeTableModel) getModel()).getRowElement(getSelectedRow());
	}

	public ArrayList<MMRecipe> getSelectedItems() {
		ArrayList<MMRecipe> selectedRecipes = new ArrayList<MMRecipe>();
		int[] rows = getSelectedRows();

		for (int row : rows) {
			selectedRecipes.add(((MMRecipeTableModel) getModel())
					.getRowElement(row));
		}

		return selectedRecipes;
	}
}
