/**
 * 
 */
package view.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import model.MMShopPoint;
import view.MenuMakerGUI;
import view.dialog.MMShopPointDialog;

/**
 * @author cmaurice2
 * 
 */
public class MMShopPointEditor extends JDialog {

	/**
	 *
	 * Auto-generated SVUID
	 *
	 */
	private static final long serialVersionUID = 7595816125776414756L;

	public static final int DEFAULT_WIDTH = 250;
	public static final int DEFAULT_HEIGHT = 110;
	
	private MMShopPointDialog parent;
	private MMShopPoint shopPoint;

	private JTextField nameField;
	private JSpinner prioritySpinner;

	public MMShopPointEditor(MMShopPointDialog parent, MMShopPoint shopPoint) {
		super(parent, "Edit shop point");
		this.parent = parent;
		this.shopPoint = shopPoint;
		this.setModal(true);

		this.getContentPane().setLayout(
				new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

		buildInputs();
		buildButtons();

		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		
		this.setModal(true);
		this.pack();
		this.setLocationRelativeTo(parent);
	}

	private void buildInputs() {
		Dimension fieldsDim = new Dimension(MenuMakerGUI.DEFAULT_FIELD_WIDTH, MenuMakerGUI.DEFAULT_FIELD_HEIGHT);
		
		// Name input
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		nameField = new JTextField();
		nameField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		nameField.setPreferredSize(fieldsDim);
		nameField.setMaximumSize(fieldsDim);
		
		if(shopPoint != null){
			nameField.setText(shopPoint.getName());
		}
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.LINE_AXIS));
		namePanel.add(nameLabel);
		namePanel.add(Box.createHorizontalGlue());
		namePanel.add(nameField);
		
		// Priority input
		JLabel priorityLabel = new JLabel("Priority");
		priorityLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		prioritySpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		prioritySpinner.setAlignmentX(Component.RIGHT_ALIGNMENT);
		prioritySpinner.setPreferredSize(fieldsDim);
		prioritySpinner.setMaximumSize(fieldsDim);
		
		if(shopPoint != null){
			prioritySpinner.setValue(shopPoint.getPriority());
		}
		
		JPanel priorityPanel = new JPanel();
		priorityPanel.setLayout(new BoxLayout(priorityPanel, BoxLayout.LINE_AXIS));
		priorityPanel.add(priorityLabel);
		priorityPanel.add(Box.createHorizontalGlue());
		priorityPanel.add(prioritySpinner);
		
		// Inputs panel
		JPanel inputsPanel = new JPanel();
		inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.PAGE_AXIS));
		inputsPanel.add(namePanel);
		inputsPanel.add(priorityPanel);

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
					if (shopPoint == null) {
						shopPoint = new MMShopPoint(nameField.getText(),
								(Integer)prioritySpinner.getValue());
						parent.addShopPoint(shopPoint);
					} else {
						shopPoint.setName(nameField.getText());
						shopPoint.setPriority((Integer)prioritySpinner.getValue());
						parent.repaint();
					}

					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(MMShopPointEditor.this,
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

		this.getContentPane().add(buttonPanel);
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
