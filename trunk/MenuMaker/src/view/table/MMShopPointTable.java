/**
 * 
 */
package view.table;

import java.util.ArrayList;

import javax.swing.JTable;

import model.MMShopPoint;
import view.dialog.MMShopPointDialog;
import view.table.model.MMShopPointTableModel;
import view.table.renderer.MMTableCellRenderer;

/**
 * @author cmaurice2
 * 
 */
public class MMShopPointTable extends JTable {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = -2250924647556136366L;

	public MMShopPointTable(MMShopPointDialog parent) {
		this.setDefaultRenderer(Object.class, new MMTableCellRenderer());
		this.setModel(new MMShopPointTableModel(parent.getShopPointList()));
	}

	public void sortData(){
		((MMShopPointTableModel) getModel()).sortData();
	}
	
	public void setFocusOn(MMShopPoint shopPoint) {
		this.changeSelection(((MMShopPointTableModel) getModel()).getRowOf(shopPoint), 0, false, false);
		this.requestFocus();
	}
	
	public void addRow(MMShopPoint shopPoint) {
		((MMShopPointTableModel) getModel()).addRow(shopPoint);
		sortData();
		setFocusOn(shopPoint);
	}

	public void removeRow(MMShopPoint shopPoint) {
		((MMShopPointTableModel) getModel()).removeRow(shopPoint);
	}

	public MMShopPoint getFirstSelectedItem() {
		return ((MMShopPointTableModel) getModel()).getRowElement(getSelectedRow());
	}

	public ArrayList<MMShopPoint> getSelectedItems() {
		ArrayList<MMShopPoint> selectedShopPoints = new ArrayList<MMShopPoint>();
		int[] rows = getSelectedRows();

		for (int row : rows) {
			selectedShopPoints.add(((MMShopPointTableModel) getModel())
					.getRowElement(row));
		}

		return selectedShopPoints;
	}
}
