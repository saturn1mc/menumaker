/**
 * 
 */
package view.table;

import java.util.ArrayList;

import javax.swing.JTable;

import model.MMExtra;
import model.MMIngredient;
import view.MenuMakerGUI;
import view.combobox.MMComboBoxCellEditor;
import view.table.model.MMExtrasTableModel;
import view.table.renderer.MMTableCellRenderer;

/**
 * @author cmaurice2
 * 
 */
public class MMExtrasTable extends JTable {
	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 6404860223050257081L;

	private MenuMakerGUI parent;

	public MMExtrasTable(MenuMakerGUI parent) {
		super();

		this.parent = parent;
		this.setDefaultRenderer(Object.class, new MMTableCellRenderer());
		
		refreshTableModel();
	}
	
	public void refreshTableModel(){
		this.setModel(new MMExtrasTableModel(parent.getData().getExtras()));
		refreshCellEditor();
	}
	
	public void refreshCellEditor() {
		MMIngredient[] items = new MMIngredient[parent.getData().getIngredients()
				.size()];
		parent.getData().getIngredients().values().toArray(items);

		this.getColumnModel()
				.getColumn(MMExtrasTableModel.COL_INGREDIENT)
				.setCellEditor(new MMComboBoxCellEditor(items));
	}

	public ArrayList<MMExtra> getRecipeElements() {
		return ((MMExtrasTableModel) getModel()).getData();
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		if (aValue != null) {
			super.setValueAt(aValue, row, column);
		}
	}
	
	public void sortData(){
		((MMExtrasTableModel) getModel()).sortData();
	}
	
	public void setFocusOn(MMExtra extra) {
		this.changeSelection(((MMExtrasTableModel) getModel()).getRowOf(extra), 0, false, false);
		this.requestFocus();
	}

	public void addRow(MMExtra extra) {
		((MMExtrasTableModel) getModel()).addRow(extra);
		sortData();
		setFocusOn(extra);
	}

	public void removeRow(MMExtra element) {
		((MMExtrasTableModel) getModel()).removeRow(element);
	}

	public MMExtra getFirstSelectedItem() {
		return ((MMExtrasTableModel) getModel())
				.getRowElement(getSelectedRow());
	}

	public ArrayList<MMExtra> getSelectedItems() {
		ArrayList<MMExtra> selectedElements = new ArrayList<MMExtra>();
		int[] rows = getSelectedRows();

		for (int row : rows) {
			selectedElements.add(((MMExtrasTableModel) getModel())
					.getRowElement(row));
		}

		return selectedElements;
	}
}
