/**
 * 
 */
package view.editor;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.MMBook;
import view.dialog.MMBookDialog;

/**
 * @author cmaurice2
 *
 */
public class MMBookEditor extends JDialog {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 6428747726067599551L;

	private MMBookDialog parent;
	private MMBook book;
	
	private JTextField nameField;
	private JTextField authorField;
	
	public MMBookEditor(MMBookDialog parent, MMBook book) {
		this.parent = parent;
		this.book = book;
		
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		
		buildInputs();
		buildButtons();
		
		this.pack();
		this.setLocationRelativeTo(parent);
	}
	
	private void buildInputs(){
		//Name input
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		nameField = new JTextField();
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.LINE_AXIS));
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		
		//Author input
		JLabel authorLabel = new JLabel("Author");
		authorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		authorField = new JTextField();
		
		JPanel authorPanel = new JPanel();
		authorPanel.setLayout(new BoxLayout(authorPanel, BoxLayout.LINE_AXIS));
		authorPanel.add(authorLabel);
		authorPanel.add(authorField);
		
		this.getContentPane().add(namePanel);
		this.getContentPane().add(authorPanel);
	}
	
	private void buildButtons(){
		//OK button
		JButton buttonOK = new JButton();
		
		//Cancel button
	}
	
	public boolean isValid(){		
		return !nameField.getText().isEmpty();
	}
}
