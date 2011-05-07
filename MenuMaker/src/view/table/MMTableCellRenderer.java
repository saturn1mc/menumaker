package view.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MMTableCellRenderer extends DefaultTableCellRenderer {
	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 3420216439691165390L;
	
	public static final Color DODGER_BLUE = new Color(30,144,255);
	public static final Color GREY82 = new Color(209,209,209);
	
	MMTableCellRenderer(){
		super();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		Component comp = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

		if (comp != null) {
			if (table.getModel().isCellEditable(row, column)) {
				comp.setForeground(Color.BLACK);
				comp.setBackground(Color.WHITE);
			} else {
				if (row % 2 == 0) {
					comp.setForeground(Color.WHITE);
					comp.setBackground(DODGER_BLUE);
				} else {
					comp.setForeground(Color.WHITE);
					comp.setBackground(GREY82);
				}
			}
		}

		return comp;
	}
}
