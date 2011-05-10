/**
 * 
 */
package view.table;

import java.util.ArrayList;

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

	public MMBookTable(MMBookDialog parent) {
		this.setDefaultRenderer(Object.class, new MMTableCellRenderer());
		this.setModel(new MMBookTableModel(parent.getBookList()));
	}

	public void sortData(){
		((MMBookTableModel) getModel()).sortData();
	}
	
	public void addRow(MMBook book) {
		((MMBookTableModel) getModel()).addRow(book);
		sortData();
	}

	public void removeRow(MMBook book) {
		((MMBookTableModel) getModel()).removeRow(book);
	}

	public MMBook getFirstSelectedItem() {
		return ((MMBookTableModel) getModel()).getRowElement(getSelectedRow());
	}

	public ArrayList<MMBook> getSelectedItems() {
		ArrayList<MMBook> selectedBooks = new ArrayList<MMBook>();
		int[] rows = getSelectedRows();

		for (int row : rows) {
			selectedBooks.add(((MMBookTableModel) getModel())
					.getRowElement(row));
		}

		return selectedBooks;
	}
}
