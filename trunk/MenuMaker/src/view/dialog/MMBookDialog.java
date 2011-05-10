/**
 * 
 */
package view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.MMBook;
import view.MenuMakerGUI;
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

	public static final int COL_COUNT = 2;
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 400;

	private MenuMakerGUI parent;
	private MMBookTable table;

	public MMBookDialog(MenuMakerGUI parent) {
		super(parent, "Manage books");
		this.parent = parent;
		this.setModal(true);
		
		refreshTableModel();
		buildButtons();
		
		this.getContentPane().setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.pack();
		this.setLocationRelativeTo(parent);
	}

	private void refreshTableModel() {
		
		if(table != null){
			this.getContentPane().remove(table);
		}

		this.table = new MMBookTable(this);

		JScrollPane scrollPane = new JScrollPane(table);
		
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		this.repaint();
	}
	
	private void buildButtons(){
		//Add button
		JButton buttonAdd = new JButton(MenuMakerGUI.ICON_PLUS);
		MouseAdapter addAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new Mous
			}
		};
		buttonAdd.addMouseListener(addAdapter);
		
		//Edit button
		JButton buttonEdit = new JButton(MenuMakerGUI.ICON_EDIT);
		MouseAdapter editAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//TODO
			}
		};
		buttonEdit.addMouseListener(editAdapter);
		
		//Remove button
		JButton buttonRemove = new JButton(MenuMakerGUI.ICON_MINUS);
		MouseAdapter removeAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//TODO
			}
		};
		buttonRemove.addMouseListener(removeAdapter);
		
		//Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(buttonAdd);
		buttonPanel.add(buttonEdit);
		buttonPanel.add(buttonRemove);
		
		JPanel centeredPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		centeredPanel.add(buttonPanel);
		
		this.getContentPane().add(centeredPanel, BorderLayout.SOUTH);
	}
	
	public void addBook(MMBook book){
		parent.addBook(book);
	}
	
	public void removeBook(MMBook book){
		parent.removeBook(book);
	}
}
