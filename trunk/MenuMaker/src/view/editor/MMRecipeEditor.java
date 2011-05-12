/**
 * 
 */
package view.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import model.MMBook;
import model.MMIngredient;
import model.MMRecipe;
import model.MMRecipeElement;
import view.MenuMakerGUI;
import view.combobox.MMAutoCompleteComboBox;
import view.dialog.MMRecipeDialog;
import view.table.MMRecipeElementTable;

/**
 * @author cmaurice2
 * 
 */
public class MMRecipeEditor extends JDialog {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 4070524773506940562L;

	public static final int DEFAULT_WIDTH = 500;
	public static final int DEFAULT_HEIGHT = 400;

	private MMRecipeDialog parent;
	private MMRecipe recipe;

	private JTextField nameField;
	private MMAutoCompleteComboBox bookComboBox;
	private JSpinner pageSpinner;

	private MMRecipeElementTable recipeElementsTable;
	private MMRecipeElementEditor recipeElementEditor;

	public MMRecipeEditor(MMRecipeDialog parent, MMRecipe recipe) {
		super(parent, "Edit recipe");
		this.parent = parent;
		this.recipe = recipe;
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

		if (recipe != null) {
			nameField.setText(recipe.getName());
		}

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.LINE_AXIS));
		namePanel.add(nameLabel);
		namePanel.add(Box.createHorizontalGlue());
		namePanel.add(nameField);

		// Book input
		JLabel bookLabel = new JLabel("Book");
		bookLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

		MMBook[] books = new MMBook[parent.getBookList().size()];
		parent.getBookList().toArray(books);
		Arrays.sort(books);

		bookComboBox = new MMAutoCompleteComboBox(books);
		bookComboBox.getComboBox().setAlignmentX(Component.RIGHT_ALIGNMENT);
		bookComboBox.getComboBox().setPreferredSize(fieldsDim);
		bookComboBox.getComboBox().setMaximumSize(fieldsDim);

		if (recipe != null) {
			bookComboBox.getComboBox().setSelectedItem(recipe.getBook());
		}

		JPanel bookPanel = new JPanel();
		bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.LINE_AXIS));
		bookPanel.add(bookLabel);
		bookPanel.add(Box.createHorizontalGlue());
		bookPanel.add(bookComboBox.getComboBox());

		// Page input
		JLabel pageLabel = new JLabel("Page");
		pageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		pageSpinner = new JSpinner(new SpinnerNumberModel(0, 0,
				Integer.MAX_VALUE, 1));
		pageSpinner.setAlignmentX(Component.RIGHT_ALIGNMENT);
		pageSpinner.setPreferredSize(fieldsDim);
		pageSpinner.setMaximumSize(fieldsDim);

		if (recipe != null) {
			pageSpinner.setValue(recipe.getPage());
		}

		JPanel pagePanel = new JPanel();
		pagePanel.setLayout(new BoxLayout(pagePanel, BoxLayout.LINE_AXIS));
		pagePanel.add(pageLabel);
		pagePanel.add(Box.createHorizontalGlue());
		pagePanel.add(pageSpinner);

		// Elements input
		recipeElementsTable = new MMRecipeElementTable(this, recipe);
		JScrollPane scrollPane = new JScrollPane(recipeElementsTable);
		scrollPane.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
				"Recipe elements", TitledBorder.LEFT, TitledBorder.TOP));

		JButton addElement = new JButton();
		addElement.setIcon(MenuMakerGUI.ICON_PLUS);
		addElement.setToolTipText("Add an element");
		MouseAdapter addAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				recipeElementEditor = new MMRecipeElementEditor(
						MMRecipeEditor.this);
				recipeElementEditor.setVisible(true);
			}
		};
		addElement.addMouseListener(addAdapter);

		JButton removeElement = new JButton();
		removeElement.setIcon(MenuMakerGUI.ICON_MINUS);
		removeElement.setToolTipText("Remove selected element(s)");
		MouseAdapter removeAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ArrayList<MMRecipeElement> elements = recipeElementsTable
						.getSelectedItems();

				if (!elements.isEmpty()) {
					int retVal = JOptionPane
							.showConfirmDialog(
									MMRecipeEditor.this,
									"All selected elements will be removed form recipe. Confirm ?",
									"Confirm deletion",
									JOptionPane.YES_NO_OPTION);

					if (retVal == JOptionPane.OK_OPTION) {
						for (MMRecipeElement element : elements) {
							recipeElementsTable.removeRow(element);
						}
					}
				}
			}
		};
		removeElement.addMouseListener(removeAdapter);

		JPanel elementsPanel = new JPanel();
		elementsPanel.add(scrollPane);

		// Inputs panel
		JPanel inputsPanel = new JPanel();
		inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.PAGE_AXIS));
		inputsPanel.add(namePanel);
		inputsPanel.add(bookPanel);
		inputsPanel.add(pagePanel);
		inputsPanel.add(elementsPanel);

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
					if (recipe == null) {
						recipe = new MMRecipe(nameField.getText(),
								(MMBook) bookComboBox.getComboBox()
										.getSelectedItem(),
								(Integer) pageSpinner.getValue());

						recipe.setElements(recipeElementsTable
								.getRecipeElements());

						parent.addRecipe(recipe);
					} else {
						recipe.setName(nameField.getText());
						recipe.setBook((MMBook) bookComboBox.getComboBox()
								.getSelectedItem());
						recipe.setPage((Integer) pageSpinner.getValue());
						recipe.setElements(recipeElementsTable
								.getRecipeElements());
						parent.repaint();
					}

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
		if (nameField.getText().isEmpty()) {
			nameField.setBackground(Color.RED);
			JOptionPane.showMessageDialog(MMRecipeEditor.this,
					"Please fill mandatory fields", "Inputs invalid",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (bookComboBox.getComboBox().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(MMRecipeEditor.this,
					"Please select a book", "Inputs invalid",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (recipeElementsTable.getRecipeElements().isEmpty()) {
			JOptionPane.showMessageDialog(MMRecipeEditor.this,
					"Please add one or more elements to the recipe",
					"Inputs invalid", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}

	}

	public Collection<MMIngredient> getIngredientList() {
		return parent.getIngredientList();
	}

	public void addElement(MMRecipeElement element) {
		recipeElementsTable.addRow(element);
	}
}
