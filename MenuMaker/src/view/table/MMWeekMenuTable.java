/**
 * 
 */
package view.table;

import javax.swing.JTable;

import model.MMRecipe;
import view.MMComboBoxCellEditor;
import view.MenuMakerGUI;
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
	private MMRecipe[] recipes;

	public MMWeekMenuTable(MenuMakerGUI parent) {
		super(new MMWeekMenuTableModel());

		this.parent = parent;
		this.recipes = new MMRecipe[MMWeekMenuTableModel.ROW_COUNT];

		this.setDefaultRenderer(Object.class, new MMTableCellRenderer());
		refreshCellEditor();
	}

	public void refreshCellEditor() {
		MMRecipe[] items = new MMRecipe[parent.getData().getRecipes().size()];
		parent.getData().getRecipes().values().toArray(items);

		this.getColumnModel().getColumn(MMWeekMenuTableModel.COL_MEAL)
				.setCellEditor(new MMComboBoxCellEditor(items));
	}

	public MMRecipe[] getRecipes() {
		return recipes;
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		super.setValueAt(aValue, row, column);

		if (column == MMWeekMenuTableModel.COL_MEAL) {
			recipes[row] = (MMRecipe) aValue;
			setValueAt(((MMRecipe) aValue).getBook(), row,
					MMWeekMenuTableModel.COL_BOOK);
			setValueAt(((MMRecipe) aValue).getPage(), row,
					MMWeekMenuTableModel.COL_PAGE);
			repaint();
		}
	}
}
