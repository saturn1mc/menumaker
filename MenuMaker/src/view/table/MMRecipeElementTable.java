/**
 * 
 */
package view.table;

import java.util.ArrayList;

import javax.swing.JTable;

import model.MMIngredient;
import model.MMRecipe;
import model.MMRecipeElement;
import view.combobox.MMComboBoxCellEditor;
import view.editor.MMRecipeEditor;
import view.table.model.MMRecipeElementTableModel;
import view.table.renderer.MMTableCellRenderer;

/**
 * @author cmaurice2
 * 
 */
public class MMRecipeElementTable extends JTable {
	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 6404860223050257081L;

	private MMRecipeEditor parent;

	public MMRecipeElementTable(MMRecipeEditor parent, MMRecipe recipe) {
		super();

		this.parent = parent;

		if (recipe != null) {
			this.setModel(new MMRecipeElementTableModel(recipe.getElements()));
		}

		this.setDefaultRenderer(Object.class, new MMTableCellRenderer());
		refreshCellEditor();
	}

	public void refreshCellEditor() {
		MMIngredient[] items = new MMIngredient[parent.getIngredientList()
				.size()];
		parent.getIngredientList().toArray(items);

		this.getColumnModel()
				.getColumn(MMRecipeElementTableModel.COL_INGREDIENT)
				.setCellEditor(new MMComboBoxCellEditor(items));
	}

	public ArrayList<MMRecipeElement> getRecipeElements() {
		return ((MMRecipeElementTableModel) getModel()).getData();
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		if (aValue != null) {
			super.setValueAt(aValue, row, column);
		}
	}

	public void addRow(MMRecipeElement element) {
		((MMRecipeElementTableModel) getModel()).addRow(element);
	}

	public void removeRow(MMRecipeElement element) {
		((MMRecipeElementTableModel) getModel()).removeRow(element);
	}
	
	public MMRecipeElement getFirstSelectedItem() {
		return ((MMRecipeElementTableModel) getModel()).getRowElement(getSelectedRow());
	}

	public ArrayList<MMRecipeElement> getSelectedItems() {
		ArrayList<MMRecipeElement> selectedElements = new ArrayList<MMRecipeElement>();
		int[] rows = getSelectedRows();

		for (int row : rows) {
			selectedElements.add(((MMRecipeElementTableModel) getModel())
					.getRowElement(row));
		}

		return selectedElements;
	}
}
