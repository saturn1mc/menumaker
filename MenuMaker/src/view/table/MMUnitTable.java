/**
 * 
 */
package view.table;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTable;

import model.MMUnit;
import view.dialog.MMUnitDialog;
import view.table.model.MMUnitTableModel;
import view.table.renderer.MMTableCellRenderer;

/**
 * @author cmaurice2
 * 
 */
public class MMUnitTable extends JTable {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = -2250924647556136366L;

	private MMUnitDialog parent;
	
	public MMUnitTable(MMUnitDialog parent) {
		this.setDefaultRenderer(Object.class, new MMTableCellRenderer());
		this.setModel(new MMUnitTableModel(parent.getUnitList()));
		
		this.parent = parent;

		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					getDialog().editUnit();
				}
			}
		};

		this.addMouseListener(mouseAdapter);
	}
	
	public MMUnitDialog getDialog(){
		return parent;
	}

	public void sortData(){
		((MMUnitTableModel) getModel()).sortData();
	}
	
	public void setFocusOn(MMUnit unit) {
		this.changeSelection(((MMUnitTableModel) getModel()).getRowOf(unit), 0, false, false);
		this.requestFocus();
	}
	
	public void addRow(MMUnit unit) {
		((MMUnitTableModel) getModel()).addRow(unit);
		sortData();
		setFocusOn(unit);
	}

	public void removeRow(MMUnit unit) {
		((MMUnitTableModel) getModel()).removeRow(unit);
	}

	public MMUnit getFirstSelectedItem() {
		return ((MMUnitTableModel) getModel()).getRowElement(getSelectedRow());
	}

	public ArrayList<MMUnit> getSelectedItems() {
		ArrayList<MMUnit> selectedUnits = new ArrayList<MMUnit>();
		int[] rows = getSelectedRows();

		for (int row : rows) {
			selectedUnits.add(((MMUnitTableModel) getModel())
					.getRowElement(row));
		}

		return selectedUnits;
	}
}
