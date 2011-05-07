/**
 * 
 */
package view.table;



import javax.swing.JTable;

import model.MMBook;
import model.MMRecipe;

/**
 * @author cmaurice2
 * 
 */
public class MMWeekMenuTable extends JTable {
	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 6404860223050257081L;

	public MMWeekMenuTable() {
		super(new MMWeekMenuTableModel());
		
		//TODO a remplacer par vrai chargement
		MMRecipe[] items = { new MMRecipe("Recette A", new MMBook("Test book A", "Me"), 12),
				new MMRecipe("Recette B", new MMBook("Test book B", "Me"), 13),
				new MMRecipe("Recette C", new MMBook("Test book C", "Me"), 14) };
		//
		
		this.setDefaultRenderer(Object.class, new MMTableCellRenderer());
		this.getColumnModel().getColumn(MMWeekMenuTableModel.COL_MEAL)
				.setCellEditor(new MMComboBoxCellEditor(items));
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		super.setValueAt(aValue, row, column);
		
		if(column == MMWeekMenuTableModel.COL_MEAL){
			setValueAt(((MMRecipe)aValue).getBook(), row, MMWeekMenuTableModel.COL_BOOK);
			setValueAt(((MMRecipe)aValue).getPage(), row, MMWeekMenuTableModel.COL_PAGE);
			repaint();
		}
	}
}
