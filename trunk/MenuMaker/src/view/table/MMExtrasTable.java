/**
 * 
 */
package view.table;



import javax.swing.JTable;

import model.MMIngredient;

/**
 * @author cmaurice2
 * 
 */
public class MMExtrasTable extends JTable {
	
	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = -7363502429043778293L;

	public MMExtrasTable() {
		super(new MMExtrasTableModel());
		
		//TODO a remplacer par vrai chargement
		MMIngredient[] items = { new MMIngredient("Ingredient test", null , null) };
		//
		
		this.setDefaultRenderer(Object.class, new MMTableCellRenderer());
		this.getColumnModel().getColumn(MMExtrasTableModel.COL_EXTRA)
				.setCellEditor(new MMComboBoxCellEditor(items));
	}
	
	public void addRow(){
		((MMExtrasTableModel)getModel()).addRow();
	}
}
