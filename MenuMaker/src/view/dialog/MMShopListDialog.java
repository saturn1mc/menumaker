/**
 * 
 */
package view.dialog;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import pdf.MMWeekMenuPdf;
import view.MenuMakerGUI;
import view.table.MMShopListTable;

/**
 * @author cmaurice2
 * 
 */
public class MMShopListDialog extends JDialog {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = -5241460721152660173L;

	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 400;

	private MenuMakerGUI parent;
	private MMShopListTable table;
	private MMWeekMenuPdf weekMenuPdf;
	
	public MMShopListDialog(MenuMakerGUI parent) {
		super(parent, "Shop list preview");
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

		this.table = new MMShopListTable(parent.getWeekMenuTable().getRecipes());

		JScrollPane scrollPane = new JScrollPane(table);

		this.getContentPane().add(scrollPane);

		this.repaint();
	}

	private void buildButtons() {
		// OK button
		JButton buttonOK = new JButton();
		buttonOK.setIcon(MenuMakerGUI.ICON_OK);
		MouseAdapter okAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
//				weekMenuPdf = new MMWeekMenuPdf(weekMenuTable);
//
//				try {
//					weekMenuPdf.writePdf();
//					JOptionPane.showMessageDialog(MenuMakerGUI.this, "Pdf successfully created", "Success", JOptionPane.INFORMATION_MESSAGE);
//				} catch (FileNotFoundException fnfe) {
//					fnfe.printStackTrace();
//					
//					JOptionPane.showMessageDialog(MenuMakerGUI.this,
//							"Can't create PDF file", "Failure",
//							JOptionPane.ERROR_MESSAGE);
//					
//				} catch (DocumentException de) {
//					de.printStackTrace();
//					
//					JOptionPane.showMessageDialog(MenuMakerGUI.this,
//							"Can't create PDF file", "Failure",
//							JOptionPane.ERROR_MESSAGE);
//					
//				} catch (IOException ioe) {
//					ioe.printStackTrace();
//					
//					JOptionPane.showMessageDialog(MenuMakerGUI.this,
//							"Can't create PDF file", "Failure",
//							JOptionPane.ERROR_MESSAGE);
//				}

				setVisible(false);
			}
		};

		buttonOK.addMouseListener(okAdapter);

		// Cancel button
		JButton buttonCancel = new JButton();
		buttonCancel.setIcon(MenuMakerGUI.ICON_CANCEL);
		MouseAdapter cancelAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setVisible(false);
			}
		};
		buttonCancel.addMouseListener(cancelAdapter);

		// Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(buttonOK);
		buttonPanel.add(buttonCancel);

		this.getContentPane().add(buttonPanel);
	}
}
