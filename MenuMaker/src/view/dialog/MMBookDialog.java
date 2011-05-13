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

import model.MMBook;
import view.MenuMakerGUI;
import view.editor.MMBookEditor;
import view.table.MMBookTable;

/**
 * @author cmaurice2
 * 
 */
public class MMBookDialog extends JDialog {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = -5241460721152660173L;

	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 400;

	private MenuMakerGUI parent;
	private MMBookTable table;
	private MMBookEditor bookEditor;

	public MMBookDialog(MenuMakerGUI parent) {
		super(parent, "Manage books");
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

		this.table = new MMBookTable(this);

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
				bookEditor = new MMBookEditor(MMBookDialog.this, null);
				bookEditor.setVisible(true);
			}
		};
		buttonAdd.addMouseListener(addAdapter);

		// Edit button
		JButton buttonEdit = new JButton(MenuMakerGUI.ICON_EDIT);
		buttonEdit.setToolTipText("Edit book");
		MouseAdapter editAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				editBook();
			}
		};
		buttonEdit.addMouseListener(editAdapter);

		// Remove button
		JButton buttonRemove = new JButton(MenuMakerGUI.ICON_MINUS);
		buttonRemove.setToolTipText("Remove selected book(s)");
		MouseAdapter removeAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				ArrayList<MMBook> books = table.getSelectedItems();

				if (!books.isEmpty()) {
					int retVal = JOptionPane.showConfirmDialog(
							MMBookDialog.this,
							"All selected books will be deleted. Confirm ?",
							"Confirm deletion", JOptionPane.YES_NO_OPTION);

					if (retVal == JOptionPane.OK_OPTION) {
						for (MMBook book : books) {
							if (parent.canDelete(book)) {
								parent.removeBook(book);
								table.removeRow(book);
							} else {
								JOptionPane
										.showMessageDialog(
												MMBookDialog.this,
												"Book \""
														+ book.getName()
														+ "\" is used in one or more recipes.",
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
	
	public Collection<MMBook> getBookList() {
		return parent.getData().getBooks().values();
	}

	public void addBook(MMBook book) {
		parent.addBook(book);
		table.addRow(book);
	}
	
	public void editBook(){
		bookEditor = new MMBookEditor(MMBookDialog.this,
				table.getFirstSelectedItem());
		bookEditor.setVisible(true);
	}

	public void removeBook(MMBook book) {
		parent.removeBook(book);
		table.removeRow(book);
	}
}
