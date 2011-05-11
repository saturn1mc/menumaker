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

import model.MMShopPoint;
import view.MenuMakerGUI;
import view.editor.MMShopPointEditor;
import view.table.MMShopPointTable;

/**
 * @author cmaurice2
 * 
 */
public class MMShopPointDialog extends JDialog {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 9208827187905967753L;

	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 400;

	private MenuMakerGUI parent;
	private MMShopPointTable table;
	private MMShopPointEditor shopPointEditor;

	public MMShopPointDialog(MenuMakerGUI parent) {
		super(parent, "Manage shop points");
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
		this.table = new MMShopPointTable(this);

		JScrollPane scrollPane = new JScrollPane(table);

		this.getContentPane().add(scrollPane);

		this.repaint();
	}

	private void buildButtons() {
		// Add button
		JButton buttonAdd = new JButton(MenuMakerGUI.ICON_PLUS);
		buttonAdd.setToolTipText("Add a book");
		MouseAdapter addAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shopPointEditor = new MMShopPointEditor(MMShopPointDialog.this, null);
				shopPointEditor.setVisible(true);
			}
		};
		buttonAdd.addMouseListener(addAdapter);

		// Edit button
		JButton buttonEdit = new JButton(MenuMakerGUI.ICON_EDIT);
		buttonEdit.setToolTipText("Edit book");
		MouseAdapter editAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shopPointEditor = new MMShopPointEditor(MMShopPointDialog.this,
						table.getFirstSelectedItem());
				shopPointEditor.setVisible(true);
			}
		};
		buttonEdit.addMouseListener(editAdapter);

		// Remove button
		JButton buttonRemove = new JButton(MenuMakerGUI.ICON_MINUS);
		buttonRemove.setToolTipText("Remove selected shop point(s)");
		MouseAdapter removeAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				ArrayList<MMShopPoint> shopPoints = table.getSelectedItems();

				if (!shopPoints.isEmpty()) {
					int retVal = JOptionPane.showConfirmDialog(
							MMShopPointDialog.this,
							"All selected shop points will be deleted. Confirm ?",
							"Confirm deletion", JOptionPane.YES_NO_OPTION);

					if (retVal == JOptionPane.OK_OPTION) {
						for (MMShopPoint shop : shopPoints) {
							if (parent.canDelete(shop)) {
								parent.removeShopPoint(shop);
								table.removeRow(shop);
							} else {
								JOptionPane
										.showMessageDialog(
												MMShopPointDialog.this,
												"Shop \""
														+ shop.getName()
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

	public Collection<MMShopPoint> getShopPointList() {
		return parent.getData().getShopPoints().values();
	}

	public void addShopPoint(MMShopPoint shopPoint) {
		parent.addShopPoint(shopPoint);
		table.addRow(shopPoint);
	}

	public void removeBook(MMShopPoint shopPoint) {
		parent.removeShopPoint(shopPoint);
		table.removeRow(shopPoint);
	}
}
