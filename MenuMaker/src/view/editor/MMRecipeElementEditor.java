/**
 * 
 */
package view.editor;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import model.MMIngredient;
import model.MMRecipeElement;
import view.MenuMakerGUI;
import view.combobox.MMAutoCompleteComboBox;

/**
 * @author cmaurice2
 * 
 */
public class MMRecipeElementEditor extends JDialog {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 4070524773506940562L;

	public static final int DEFAULT_WIDTH = 500;
	public static final int DEFAULT_HEIGHT = 400;

	private MMRecipeEditor parent;

	private MMAutoCompleteComboBox ingredientComboBox;
	private JSpinner quantitySpinner;

	public MMRecipeElementEditor(MMRecipeEditor parent) {
		super(parent, "Edit recipe element");
		this.parent = parent;
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

		// Ingredient input
		JLabel ingredientLabel = new JLabel("Ingredient");
		ingredientLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

		MMIngredient[] ingredients = new MMIngredient[parent.getIngredientList().size()];
		parent.getIngredientList().toArray(ingredients);
		Arrays.sort(ingredients);

		ingredientComboBox = new MMAutoCompleteComboBox(ingredients);
		ingredientComboBox.getComboBox().setAlignmentX(Component.RIGHT_ALIGNMENT);
		ingredientComboBox.getComboBox().setPreferredSize(fieldsDim);
		ingredientComboBox.getComboBox().setMaximumSize(fieldsDim);

		JPanel ingredientPanel = new JPanel();
		ingredientPanel.setLayout(new BoxLayout(ingredientPanel, BoxLayout.LINE_AXIS));
		ingredientPanel.add(ingredientLabel);
		ingredientPanel.add(Box.createHorizontalGlue());
		ingredientPanel.add(ingredientComboBox.getComboBox());

		// Quantity input
		JLabel quantityLabel = new JLabel("Quantity");
		quantityLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		quantitySpinner = new JSpinner(new SpinnerNumberModel(0.0d, 0.0d,
				Float.MAX_VALUE, 0.5d));
		quantitySpinner.setAlignmentX(Component.RIGHT_ALIGNMENT);
		quantitySpinner.setPreferredSize(fieldsDim);
		quantitySpinner.setMaximumSize(fieldsDim);

		JPanel quantityPanel = new JPanel();
		quantityPanel.setLayout(new BoxLayout(quantityPanel, BoxLayout.LINE_AXIS));
		quantityPanel.add(quantityLabel);
		quantityPanel.add(Box.createHorizontalGlue());
		quantityPanel.add(quantitySpinner);

		// Inputs panel
		JPanel inputsPanel = new JPanel();
		inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.PAGE_AXIS));
		inputsPanel.add(ingredientPanel);
		inputsPanel.add(quantityPanel);

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
					MMRecipeElement element = new MMRecipeElement((MMIngredient)ingredientComboBox.getComboBox().getSelectedItem(), (Float)quantitySpinner.getValue());
					parent.addElement(element);
					setVisible(false);
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
		if (ingredientComboBox.getComboBox().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(MMRecipeElementEditor.this, "Please select an ingredient", "Inputs invalid", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (((Float)quantitySpinner.getValue()) <= 0) {
			JOptionPane.showMessageDialog(MMRecipeElementEditor.this, "Please set a quantity", "Inputs invalid", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}

	}

	public Collection<MMIngredient> getIngredientList() {
		return parent.getIngredientList();
	}
}
