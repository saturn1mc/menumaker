/**
 * 
 */
package view.table;

import javax.swing.JTable;

import model.MMBook;
import view.dialog.MMBookDialog;
import view.table.model.MMBookTableModel;
import view.table.renderer.MMTableCellRenderer;

/**
 * @author cmaurice2
 *
 */
public class MMBookTable extends JTable {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = -2250924647556136366L;

	private MMBookDialog parent;
	
	public MMBookTable(MMBookDialog parent){
		this.parent = parent;
		this.setDefaultRenderer(Object.class, new MMTableCellRenderer());
	}
	
	public void addRow(MMBook book){
		((MMBookTableModel)getModel()).addRow(book);
	}
	
	public void removeRow(MMBook book){
		((MMBookTableModel)getModel()).removeRow(book);
	}
}
