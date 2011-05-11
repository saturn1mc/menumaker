/**
 * 
 */
package view.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.MMIngredient;
import model.MMShopPoint;
import model.MMUnit;
import view.MenuMakerGUI;
import view.combobox.MMAutoCompleteComboBox;
import view.dialog.MMIngredientDialog;

/**
 * @author cmaurice2
 * 
 */
public class MMIngredientEditor extends JDialog {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 4070524773506940562L;

	public static final int DEFAULT_WIDTH = 250;
	public static final int DEFAULT_HEIGHT = 135;

	private MMIngredientDialog parent;
	private MMIngredient ingredient;

	private JTextField nameField;
	private MMAutoCompleteComboBox unitComboBox;
	private MMAutoCompleteComboBox shopPointComboBox;

	public MMIngredientEditor(MMIngredientDialog parent, MMIngredient ingredient) {
		super(parent, "Edit ingredient");
		this.parent = parent;
		this.ingredient = ingredient;
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
		Dimension fieldsDim = new Dimension(MenuMakerGUI.DEFAULT_FIELD_WIDTH,
				MenuMakerGUI.DEFAULT_FIELD_HEIGHT);

		// Name input
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		nameField = new JTextField();
		nameField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		nameField.setPreferredSize(fieldsDim);
		nameField.setMaximumSize(fieldsDim);

		if (ingredient != null) {
			nameField.setText(ingredient.getName());
		}

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.LINE_AXIS));
		namePanel.add(nameLabel);
		namePanel.add(Box.createHorizontalGlue());
		namePanel.add(nameField);

		// Unit input
		JLabel unitLabel = new JLabel("Unit");
		unitLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

		MMUnit[] units = new MMUnit[parent.getUnitsList().size()];
		parent.getUnitsList().toArray(units);
		Arrays.sort(units);

		unitComboBox = new MMAutoCompleteComboBox(units);
		unitComboBox.getComboBox().setAlignmentX(Component.RIGHT_ALIGNMENT);
		unitComboBox.getComboBox().setPreferredSize(fieldsDim);
		unitComboBox.getComboBox().setMaximumSize(fieldsDim);

		if (ingredient != null) {
			unitComboBox.getComboBox().setSelectedItem(ingredient.getUnit());
		}

		JPanel unitPanel = new JPanel();
		unitPanel.setLayout(new BoxLayout(unitPanel, BoxLayout.LINE_AXIS));
		unitPanel.add(unitLabel);
		unitPanel.add(Box.createHorizontalGlue());
		unitPanel.add(unitComboBox.getComboBox());

		// Unit input
		JLabel shopPointLabel = new JLabel("Shop Point");
		shopPointLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

		MMShopPoint[] shopPoints = new MMShopPoint[parent.getShopPointsList()
				.size()];
		parent.getShopPointsList().toArray(shopPoints);
		Arrays.sort(shopPoints);

		shopPointComboBox = new MMAutoCompleteComboBox(shopPoints);
		shopPointComboBox.getComboBox()
				.setAlignmentX(Component.RIGHT_ALIGNMENT);
		shopPointComboBox.getComboBox().setPreferredSize(fieldsDim);
		shopPointComboBox.getComboBox().setMaximumSize(fieldsDim);

		if (ingredient != null) {
			shopPointComboBox.getComboBox().setSelectedItem(
					ingredient.getShopPoint());
		}

		JPanel shopPointPanel = new JPanel();
		shopPointPanel.setLayout(new BoxLayout(shopPointPanel,
				BoxLayout.LINE_AXIS));
		shopPointPanel.add(shopPointLabel);
		shopPointPanel.add(Box.createHorizontalGlue());
		shopPointPanel.add(shopPointComboBox.getComboBox());

		// Inputs panel
		JPanel inputsPanel = new JPanel();
		inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.PAGE_AXIS));
		inputsPanel.add(namePanel);
		inputsPanel.add(unitPanel);
		inputsPanel.add(shopPointPanel);

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
					if (ingredient == null) {
						ingredient = new MMIngredient(nameField.getText(),
								(MMUnit) unitComboBox.getComboBox()
										.getSelectedItem(),
								(MMShopPoint) shopPointComboBox.getComboBox()
										.getSelectedItem());
						parent.addIngredient(ingredient);
					} else {
						ingredient.setName(nameField.getText());
						ingredient.setUnit((MMUnit) unitComboBox.getComboBox()
								.getSelectedItem());
						ingredient.setShopPoint((MMShopPoint) shopPointComboBox
								.getComboBox().getSelectedItem());
						parent.repaint();
					}

					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(MMIngredientEditor.this,
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
		if (nameField.getText().isEmpty()) {
			nameField.setBackground(Color.RED);
			return false;
		} else if (unitComboBox.getComboBox().getSelectedItem() == null) {
			return false;
		} else if (shopPointComboBox.getComboBox().getSelectedItem() == null) {
			return false;
		} else {
			return true;
		}

	}
}
