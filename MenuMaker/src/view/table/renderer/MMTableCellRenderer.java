package view.table.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MMTableCellRenderer extends DefaultTableCellRenderer {
	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 3420216439691165390L;

	public static final Color DODGER_BLUE = new Color(30, 144, 255);
	public static final Color LIGHT_GRAY = new Color(170, 170, 170);
	public static final Color LIGHTER_GRAY = new Color(200, 200, 200);

	public MMTableCellRenderer() {
		super();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		Component comp = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

		if (comp != null) {
			if (isSelected) {
				comp.setForeground(Color.WHITE);
				comp.setBackground(DODGER_BLUE);
			} else {
				if (table.getModel().isCellEditable(row, column)) {
					comp.setForeground(Color.BLACK);
					comp.setBackground(Color.WHITE);
				} else {
					if (row % 2 == 0) {
						comp.setForeground(Color.WHITE);
						comp.setBackground(LIGHTER_GRAY);
					} else {
						comp.setForeground(Color.WHITE);
						comp.setBackground(LIGHT_GRAY);
					}
				}
			}

		}

		return comp;
	}
}
