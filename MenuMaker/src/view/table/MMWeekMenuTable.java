/**
 * 
 */
package view.table;

import javax.swing.JTable;

import model.MMRecipe;
import view.MenuMakerGUI;
import view.combobox.MMComboBoxCellEditor;
import view.table.model.MMWeekMenuTableModel;
import view.table.renderer.MMTableCellRenderer;

/**
 * @author cmaurice2
 * 
 */
public class MMWeekMenuTable extends JTable {
	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 6404860223050257081L;

	private MenuMakerGUI parent;

	public MMWeekMenuTable(MenuMakerGUI parent) {
		super();

		this.parent = parent;

		this.setDefaultRenderer(Object.class, new MMTableCellRenderer());
		this.setDefaultRenderer(Integer.class, new MMTableCellRenderer());
		
		refreshTableModel();
	}
	
	public void refreshTableModel(){
		this.setModel(new MMWeekMenuTableModel(parent.getData().getMenu()));
		refreshCellEditor();
	}

	public void refreshCellEditor() {
		MMRecipe[] items = new MMRecipe[parent.getData().getRecipes().size()];
		parent.getData().getRecipes().values().toArray(items);

		this.getColumnModel().getColumn(MMWeekMenuTableModel.COL_RECIPE)
				.setCellEditor(new MMComboBoxCellEditor(items));
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		super.setValueAt(aValue, row, column);
	}
}
