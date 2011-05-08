/**
 * 
 */
package view.table;



import javax.swing.JTable;

import model.MMIngredient;
import view.MenuMakerGUI;

/**
 * @author cmaurice2
 * 
 */
public class MMExtrasTable extends JTable {
	
	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = -7363502429043778293L;

	private MenuMakerGUI parent;
	
	public MMExtrasTable(MenuMakerGUI parent) {
		super(new MMExtrasTableModel());
		
		this.parent = parent;
		this.setDefaultRenderer(Object.class, new MMTableCellRenderer());
		refreshCellEditor();
	}
	
	public void refreshCellEditor(){
		MMIngredient[] items = new MMIngredient[parent.getData().getIngredients().size()];
		parent.getData().getIngredients().values().toArray(items);
		
		this.getColumnModel().getColumn(MMExtrasTableModel.COL_EXTRA)
		.setCellEditor(new MMComboBoxCellEditor(items));
	}
	
	public void addRow(){
		((MMExtrasTableModel)getModel()).addRow();
	}
}
