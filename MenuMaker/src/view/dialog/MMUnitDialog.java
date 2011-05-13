/**
 * 
 */
package view.dialog;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.MMUnit;
import view.MenuMakerGUI;
import view.editor.MMUnitEditor;
import view.table.MMUnitTable;

/**
 * @author cmaurice2
 * 
 */
public class MMUnitDialog extends JDialog {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = -5241460721152660173L;

	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 400;

	private MenuMakerGUI parent;
	private MMUnitTable table;
	private MMUnitEditor unitEditor;

	public MMUnitDialog(MenuMakerGUI parent) {
		super(parent, "Manage units");
		this.parent = parent;
		this.setModal(true);
		
		this.getContentPane().setLayout(
				new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

		buildTable();
		buildButtons();

		this.getContentPane().setPreferredSize(
				new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.pack();
		this.setLocationRelativeTo(parent);
	}

	private void buildTable() {

		this.table = new MMUnitTable(this);

		JScrollPane scrollPane = new JScrollPane(table);

		this.getContentPane().add(scrollPane);

		this.repaint();
	}

	private void buildButtons() {
		// Add button
		JButton buttonAdd = new JButton(MenuMakerGUI.ICON_PLUS);
		buttonAdd.setToolTipText("Add a unit");
		MouseAdapter addAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				unitEditor = new MMUnitEditor(MMUnitDialog.this, null);
				unitEditor.setVisible(true);
			}
		};
		buttonAdd.addMouseListener(addAdapter);

		// Edit button
		JButton buttonEdit = new JButton(MenuMakerGUI.ICON_EDIT);
		buttonEdit.setToolTipText("Edit unit");
		MouseAdapter editAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				editUnit();
			}
		};
		buttonEdit.addMouseListener(editAdapter);

		// Remove button
		JButton buttonRemove = new JButton(MenuMakerGUI.ICON_MINUS);
		buttonRemove.setToolTipText("Remove selected unit(s)");
		MouseAdapter removeAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				ArrayList<MMUnit> units = table.getSelectedItems();

				if (!units.isEmpty()) {
					int retVal = JOptionPane.showConfirmDialog(
							MMUnitDialog.this,
							"All selected units will be deleted. Confirm ?",
							"Confirm deletion", JOptionPane.YES_NO_OPTION);

					if (retVal == JOptionPane.OK_OPTION) {
						for (MMUnit unit : units) {
							if (parent.canDelete(unit)) {
								parent.removeUnit(unit);
								table.removeRow(unit);
							} else {
								JOptionPane
										.showMessageDialog(
												MMUnitDialog.this,
												"Unit \""
														+ unit.getName()
														+ "\" is used for one or more ingredients.",
												"Can't delete",
												JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			}
		};
		buttonRemove.addMouseListener(removeAdapter);

		// Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(buttonAdd);
		buttonPanel.add(buttonEdit);
		buttonPanel.add(buttonRemove);

		this.getContentPane().add(buttonPanel);
	}

	public Collection<MMUnit> getUnitList() {
		return parent.getData().getUnits().values();
	}

	public void addUnit(MMUnit unit) {
		parent.addUnit(unit);
		table.addRow(unit);
	}
	
	public void editUnit(){
		unitEditor = new MMUnitEditor(MMUnitDialog.this,
				table.getFirstSelectedItem());
		unitEditor.setVisible(true);
	}

	public void removeUnit(MMUnit unit) {
		parent.removeUnit(unit);
		table.removeRow(unit);
	}
}
