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
import model.MMIngredient;
import model.MMRecipe;
import view.MenuMakerGUI;
import view.editor.MMRecipeEditor;
import view.table.MMRecipeTable;

/**
 * @author cmaurice2
 * 
 */
public class MMRecipeDialog extends JDialog {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = -5241460721152660173L;

	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 400;

	private MenuMakerGUI parent;
	
	private MMRecipeTable table;
	private MMRecipeEditor recipeEditor;

	public MMRecipeDialog(MenuMakerGUI parent) {
		super(parent, "Manage recipes");
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

		this.table = new MMRecipeTable(this);

		JScrollPane scrollPane = new JScrollPane(table);

		this.getContentPane().add(scrollPane);

		this.repaint();
	}

	private void buildButtons() {
		// Add button
		JButton buttonAdd = new JButton(MenuMakerGUI.ICON_PLUS);
		buttonAdd.setToolTipText("Add a recipe");
		MouseAdapter addAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				recipeEditor = new MMRecipeEditor(MMRecipeDialog.this, null);
				recipeEditor.setVisible(true);
			}
		};
		buttonAdd.addMouseListener(addAdapter);

		// Edit button
		JButton buttonEdit = new JButton(MenuMakerGUI.ICON_EDIT);
		buttonEdit.setToolTipText("Edit recipe");
		MouseAdapter editAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				editRecipe();
			}
		};
		buttonEdit.addMouseListener(editAdapter);

		// Remove button
		JButton buttonRemove = new JButton(MenuMakerGUI.ICON_MINUS);
		buttonRemove.setToolTipText("Remove selected recipe(s)");
		MouseAdapter removeAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				ArrayList<MMRecipe> recipes = table.getSelectedItems();

				if (!recipes.isEmpty()) {
					int retVal = JOptionPane.showConfirmDialog(
							MMRecipeDialog.this,
							"All selected recipes will be deleted. Confirm ?",
							"Confirm deletion", JOptionPane.YES_NO_OPTION);

					if (retVal == JOptionPane.OK_OPTION) {
						for (MMRecipe recipe : recipes) {
							if (parent.canDelete(recipe)) {
								parent.removeRecipe(recipe);
								table.removeRow(recipe);
							} else {
								JOptionPane
										.showMessageDialog(
												MMRecipeDialog.this,
												"Recipe \""
														+ recipe.getName()
														+ "\" is planned for the week.",
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

	public Collection<MMRecipe> getRecipeList() {
		return parent.getData().getRecipes().values();
	}
	
	public Collection<MMIngredient> getIngredientList() {
		return parent.getData().getIngredients().values();
	}
	
	public Collection<MMBook> getBookList() {
		return parent.getData().getBooks().values();
	}
	
	public void addRecipe(MMRecipe recipe) {
		parent.addRecipe(recipe);
		table.addRow(recipe);
	}
	
	public void editRecipe(){
		recipeEditor = new MMRecipeEditor(MMRecipeDialog.this,
				table.getFirstSelectedItem());
		recipeEditor.setVisible(true);
	}

	public void removeRecipe(MMRecipe recipe) {
		parent.removeRecipe(recipe);
		table.removeRow(recipe);
	}
}
