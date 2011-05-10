/**
 * 
 */
package view.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.MMBook;
import view.MenuMakerGUI;
import view.dialog.MMBookDialog;

/**
 * @author cmaurice2
 * 
 */
public class MMBookEditor extends JDialog {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 4070524773506940562L;

	private MMBookDialog parent;
	private MMBook book;

	private JTextField nameField;
	private JTextField authorField;

	public MMBookEditor(MMBookDialog parent, MMBook book) {
		super(parent, "Edit book");
		this.parent = parent;
		this.book = book;
		this.setModal(true);

		this.getContentPane().setLayout(
				new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

		buildInputs();
		buildButtons();

		this.setModal(true);
		this.pack();
		this.setLocationRelativeTo(parent);
	}

	private void buildInputs() {
		// Name input
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		nameField = new JTextField();
		nameField.setAlignmentX(Component.RIGHT_ALIGNMENT);

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.LINE_AXIS));
		namePanel.add(nameLabel);
		namePanel.add(Box.createHorizontalGlue());
		namePanel.add(nameField);

		// Author input
		JLabel authorLabel = new JLabel("Author");
		authorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		authorField = new JTextField();
		authorField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		JPanel authorPanel = new JPanel();
		authorPanel.setLayout(new BoxLayout(authorPanel, BoxLayout.LINE_AXIS));
		authorPanel.add(authorLabel);
		authorPanel.add(Box.createHorizontalGlue());
		authorPanel.add(authorField);

		// Inputs panel
		JPanel inputsPanel = new JPanel();
		inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.PAGE_AXIS));
		inputsPanel.add(namePanel);
		inputsPanel.add(authorPanel);

		this.getContentPane().add(inputsPanel);
	}

	private void buildButtons() {
		// OK button
		JButton buttonOK = new JButton();
		buttonOK.setIcon(MenuMakerGUI.ICON_OK);
		MouseAdapter okAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (areInputsValid()) {
					
					String authorStr;
					
					if(authorField.getText().isEmpty()){
						authorStr = "?";
					}
					else{
						authorStr = authorField.getText();
					}
					
					if (book == null) {
						book = new MMBook(nameField.getText(),
								authorStr);
						parent.addBook(book);
					} else {
						book.setName(nameField.getText());
						book.setAuthor(authorStr);
					}

					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(MMBookEditor.this,
							"Please fill mandatory fields", "Inputs invalid",
							JOptionPane.ERROR_MESSAGE);
				}
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

		this.add(buttonPanel);
	}

	public boolean areInputsValid() {
		if(!nameField.getText().isEmpty()){
			return true;
		}
		else{
			nameField.setBackground(Color.RED);
			return false;
		}
	}
}
