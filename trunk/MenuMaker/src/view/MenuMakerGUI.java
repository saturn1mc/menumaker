/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import legacy.MMLegacyParser;
import model.MMBook;
import model.MMData;
import model.MMExtra;
import model.MMIngredient;
import model.MMMenuElement;
import model.MMRecipe;
import model.MMRecipeElement;
import model.MMShopPoint;
import model.MMUnit;

import org.jdom.JDOMException;

import view.dialog.MMBookDialog;
import view.dialog.MMIngredientDialog;
import view.dialog.MMRecipeDialog;
import view.dialog.MMShopListDialog;
import view.dialog.MMShopPointDialog;
import view.dialog.MMUnitDialog;
import view.editor.MMExtraEditor;
import view.table.MMExtrasTable;
import view.table.MMWeekMenuTable;

/**
 * @author cmaurice2
 * 
 */
public class MenuMakerGUI extends JFrame implements WindowListener {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = -1017764394785180674L;

	public static final String FOLDER_IMG = "/img/";

	public static final int DEFAULT_WIDTH = 600;
	public static final int DEFAULT_HEIGHT = 530;

	public static final int DEFAULT_FIELD_WIDTH = 190;
	public static final int DEFAULT_FIELD_HEIGHT = 25;

	public static final int DEFAULT_WEEK_TABLE_WIDTH = 550;
	public static final int DEFAULT_WEEK_TABLE_HEIGHT = 280;
	
	public static final int DEFAULT_EXTRA_TABLE_WIDTH = 550;
	public static final int DEFAULT_EXTRA_TABLE_HEIGHT = 150;

	public static ImageIcon ICON_PLUS;
	public static ImageIcon ICON_MINUS;
	public static ImageIcon ICON_EDIT;
	public static ImageIcon ICON_UNIT;
	public static ImageIcon ICON_BOOK;
	public static ImageIcon ICON_SHOP;
	public static ImageIcon ICON_INGREDIENT;
	public static ImageIcon ICON_RECIPE;
	public static ImageIcon ICON_PRINT;
	public static ImageIcon ICON_OK;
	public static ImageIcon ICON_CANCEL;
	public static ImageIcon ICON_CLEAR_LIST;

	private JMenuBar menuBar;
	private JToolBar toolBar;

	private MMData data;

	private MMWeekMenuTable weekMenuTable;

	private MMExtrasTable extrasTable;
	private MMExtraEditor extraEditor;

	private MMBookDialog bookManageDialog;
	private MMShopPointDialog shopManageDialog;
	private MMUnitDialog unitManageDialog;
	private MMIngredientDialog ingredientManageDialog;
	private MMRecipeDialog recipeManageDialog;
	private MMShopListDialog shopListDialog;

	public MenuMakerGUI() {
		super("Menu Maker - Powered by MC");
		this.setLayout(new BorderLayout());

		data = new MMData();

		loadIcons();
		buildMenu();
		buildToolbar();
		buildCenterPanel();

		this.addWindowListener(this);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setPreferredSize(
				new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.pack();
		this.setLocationRelativeTo(null);
	}

	private void loadIcons() {
		ICON_PLUS = new ImageIcon(getClass().getResource(
				FOLDER_IMG + "plus-icon.png"));
		ICON_MINUS = new ImageIcon(getClass().getResource(
				FOLDER_IMG + "minus-icon.png"));
		ICON_EDIT = new ImageIcon(getClass().getResource(
				FOLDER_IMG + "edit-icon.png"));
		ICON_UNIT = new ImageIcon(getClass().getResource(
				FOLDER_IMG + "rulers-icon.png"));
		ICON_SHOP = new ImageIcon(getClass().getResource(
				FOLDER_IMG + "shop-icon.png"));
		ICON_BOOK = new ImageIcon(getClass().getResource(
				FOLDER_IMG + "books-icon.png"));
		ICON_INGREDIENT = new ImageIcon(getClass().getResource(
				FOLDER_IMG + "tomato-icon.png"));
		ICON_RECIPE = new ImageIcon(getClass().getResource(
				FOLDER_IMG + "recipe-icon.png"));
		ICON_PRINT = new ImageIcon(getClass().getResource(
				FOLDER_IMG + "printer-icon.png"));
		ICON_OK = new ImageIcon(getClass().getResource(
				FOLDER_IMG + "ok-icon.png"));
		ICON_CANCEL = new ImageIcon(getClass().getResource(
				FOLDER_IMG + "cancel-icon.png"));
		ICON_CLEAR_LIST = new ImageIcon(getClass().getResource(
				FOLDER_IMG + "clear-list-icon.png"));
	}

	private void buildMenu() {
		// Properties menu
		JMenuItem legacyItem = new JMenuItem("Load legacy data");

		MouseAdapter legacyAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setMultiSelectionEnabled(false);
				fileChooser.setDialogTitle("Select legacy file");
				int retVal = fileChooser.showOpenDialog(MenuMakerGUI.this);

				if (retVal == JFileChooser.APPROVE_OPTION) {
					try {
						// TODO swing worker
						MenuMakerGUI.this
								.setData(MMLegacyParser.getInstance()
										.parseLegacyFile(
												fileChooser.getSelectedFile()));
					} catch (IOException ioe) {
						JOptionPane.showMessageDialog(MenuMakerGUI.this,
								"Can't parse legacy file", "Legacy",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		};

		legacyItem.addMouseListener(legacyAdapter);

		JMenu propertiesMenu = new JMenu("Properties");
		propertiesMenu.setMnemonic(KeyEvent.VK_P);
		propertiesMenu.add(legacyItem);

		// Menu bar
		menuBar = new JMenuBar();
		menuBar.add(propertiesMenu);
		this.setJMenuBar(menuBar);
	}

	private void buildToolbar() {
		// Manage units button
		JButton unitButton = new JButton();
		unitButton.setIcon(ICON_UNIT);
		unitButton.setToolTipText("Manage units");

		MouseAdapter unitAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				unitManageDialog = new MMUnitDialog(MenuMakerGUI.this);
				unitManageDialog.setVisible(true);
			}
		};

		unitButton.addMouseListener(unitAdapter);

		// Manage shops button
		JButton shopButton = new JButton();
		shopButton.setIcon(ICON_SHOP);
		shopButton.setToolTipText("Manage shop points");

		MouseAdapter shopAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shopManageDialog = new MMShopPointDialog(MenuMakerGUI.this);
				shopManageDialog.setVisible(true);
			}
		};

		shopButton.addMouseListener(shopAdapter);

		// Manage books button
		JButton bookButton = new JButton();
		bookButton.setIcon(ICON_BOOK);
		bookButton.setToolTipText("Manage books");

		MouseAdapter bookAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bookManageDialog = new MMBookDialog(MenuMakerGUI.this);
				bookManageDialog.setVisible(true);
			}
		};

		bookButton.addMouseListener(bookAdapter);

		// Manage ingredients button
		JButton ingredientButton = new JButton();
		ingredientButton.setIcon(ICON_INGREDIENT);
		ingredientButton.setToolTipText("Manage ingredients");

		MouseAdapter ingredientAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ingredientManageDialog = new MMIngredientDialog(
						MenuMakerGUI.this);
				ingredientManageDialog.setVisible(true);
			}
		};

		ingredientButton.addMouseListener(ingredientAdapter);

		// Manage recipes button
		JButton recipeButton = new JButton();
		recipeButton.setIcon(ICON_RECIPE);
		recipeButton.setToolTipText("Manage recipes");

		MouseAdapter recipeAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				recipeManageDialog = new MMRecipeDialog(MenuMakerGUI.this);
				recipeManageDialog.setVisible(true);
			}
		};

		recipeButton.addMouseListener(recipeAdapter);

		// Print button
		JButton printButton = new JButton();
		printButton.setIcon(ICON_PRINT);
		printButton.setToolTipText("Print week menu");

		MouseAdapter printAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shopListDialog = new MMShopListDialog(MenuMakerGUI.this);
				shopListDialog.setVisible(true);
			}
		};

		printButton.addMouseListener(printAdapter);

		// Tool bar
		toolBar = new JToolBar();
		toolBar.add(bookButton);
		toolBar.add(shopButton);
		toolBar.add(unitButton);
		toolBar.add(ingredientButton);
		toolBar.add(recipeButton);
		toolBar.add(printButton);

		this.add(toolBar, BorderLayout.PAGE_START);
	}

	private void buildCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));

		centerPanel.add(buildWeekMenuPanel());
		centerPanel.add(buildExtrasPanel());

		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
	}

	private JPanel buildWeekMenuPanel() {
		
		//Table panel
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());

		weekMenuTable = new MMWeekMenuTable(this);

		JScrollPane scrollpane = new JScrollPane(weekMenuTable);
		scrollpane.setPreferredSize(new Dimension(DEFAULT_WEEK_TABLE_WIDTH,
				DEFAULT_WEEK_TABLE_HEIGHT));

		tablePanel.add(weekMenuTable.getTableHeader(),
				BorderLayout.PAGE_START);
		tablePanel.add(scrollpane, BorderLayout.CENTER);
		
		//Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		
		JButton clearButton = new JButton();
		clearButton.setIcon(ICON_CLEAR_LIST);
		clearButton.setToolTipText("Clear menu");
		
		MouseAdapter clearAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				data.refreshMenu();
				weekMenuTable.repaint();
			}
		};
		
		clearButton.addMouseListener(clearAdapter);
		
		buttonPanel.add(clearButton);
		
		//Week menu panel
		JPanel weekMenuPanel = new JPanel();
		weekMenuPanel.setLayout(new BoxLayout(weekMenuPanel, BoxLayout.PAGE_AXIS));
		weekMenuPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
				"WeekMenu", TitledBorder.LEFT, TitledBorder.TOP));
		
		weekMenuPanel.add(tablePanel);
		weekMenuPanel.add(buttonPanel);
		
		return weekMenuPanel;
	}

	private JPanel buildExtrasPanel() {

		// Table panel
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());

		extrasTable = new MMExtrasTable(this);

		JScrollPane scrollpane = new JScrollPane(extrasTable);
		scrollpane.setPreferredSize(new Dimension(DEFAULT_EXTRA_TABLE_WIDTH,
				DEFAULT_EXTRA_TABLE_HEIGHT));

		tablePanel.add(extrasTable.getTableHeader(), BorderLayout.PAGE_START);
		tablePanel.add(scrollpane, BorderLayout.CENTER);

		// Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

		JButton addExtra = new JButton();
		addExtra.setIcon(ICON_PLUS);
		addExtra.setToolTipText("Add an extra");
		MouseAdapter addAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				extraEditor = new MMExtraEditor(MenuMakerGUI.this);
				extraEditor.setVisible(true);
			}
		};
		addExtra.addMouseListener(addAdapter);

		JButton removeExtra = new JButton();
		removeExtra.setIcon(ICON_MINUS);
		removeExtra.setToolTipText("Remove selected extra(s)");
		MouseAdapter removeAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ArrayList<MMExtra> extras = extrasTable.getSelectedItems();

				if (!extras.isEmpty()) {
					int retVal = JOptionPane.showConfirmDialog(
							MenuMakerGUI.this,
							"All selected extras will be removed. Confirm ?",
							"Confirm deletion", JOptionPane.YES_NO_OPTION);

					if (retVal == JOptionPane.OK_OPTION) {
						for (MMExtra extra : extras) {
							extrasTable.removeRow(extra);
						}
					}
				}
			}
		};
		removeExtra.addMouseListener(removeAdapter);

		buttonPanel.add(addExtra);
		buttonPanel.add(removeExtra);

		// Extras panel
		JPanel extrasPanel = new JPanel();
		extrasPanel.setLayout(new BoxLayout(extrasPanel, BoxLayout.PAGE_AXIS));
		extrasPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
				"Extras", TitledBorder.LEFT, TitledBorder.TOP));

		extrasPanel.add(tablePanel);
		extrasPanel.add(buttonPanel);

		return extrasPanel;
	}

	public MMData getData() {
		return data;
	}

	public void setData(MMData data) {
		this.data = data;
	}

	public void addBook(MMBook book) {
		this.data.addBook(book);
	}

	public void removeBook(MMBook book) {
		this.data.removeBook(book);
	}

	public void addShopPoint(MMShopPoint shopPoint) {
		this.data.addShopPoint(shopPoint);
	}

	public void removeShopPoint(MMShopPoint shopPoint) {
		this.data.removeShopPoint(shopPoint);
	}

	public void addUnit(MMUnit unit) {
		this.data.addUnit(unit);
	}

	public void removeUnit(MMUnit unit) {
		this.data.removeUnit(unit);
	}

	public void addIngredient(MMIngredient ingredient) {
		this.data.addIngredient(ingredient);
	}

	public void removeIngredient(MMIngredient ingredient) {
		this.data.removeIngredient(ingredient);
	}

	public void addRecipe(MMRecipe recipe) {
		this.data.addRecipe(recipe);
	}

	public void removeRecipe(MMRecipe recipe) {
		this.data.removeRecipe(recipe);
	}

	public void addExtra(MMExtra extra) {
		this.data.addExtra(extra);
		extrasTable.addRow(extra);
	}

	public void removeExtra(MMExtra extra) {
		this.data.removeExtra(extra);
	}

	public boolean canDelete(MMBook book) {
		for (MMRecipe recipe : data.getRecipes().values()) {
			if (recipe.getBook().equals(book)) {
				return false;
			}
		}

		return true;
	}

	public boolean canDelete(MMShopPoint shopPoint) {
		for (MMIngredient ingredient : data.getIngredients().values()) {
			if (ingredient.getShopPoint().equals(shopPoint)) {
				return false;
			}
		}

		return true;
	}

	public boolean canDelete(MMUnit unit) {
		for (MMIngredient ingredient : data.getIngredients().values()) {
			if (ingredient.getUnit().equals(unit)) {
				return false;
			}
		}

		return true;
	}

	public boolean canDelete(MMIngredient ingredient) {
		for (MMRecipe recipe : data.getRecipes().values()) {
			for (MMRecipeElement element : recipe.getElements()) {
				if (element.getIngredient().equals(ingredient)) {
					return false;
				}
			}
		}

		for (MMExtra extra : data.getExtras()) {
			if (extra.getIngredient().equals(ingredient)) {
				return false;
			}
		}

		return true;
	}

	public boolean canDelete(MMRecipe recipe) {
		for (MMMenuElement menuElement : data.getMenu()) {

			MMRecipe weekRecipe = menuElement.getRecipe();

			if (recipe != null && recipe.equals(weekRecipe)) {
				return false;
			}
		}

		return true;
	}

	public boolean canDelete(MMExtra extra) {
		return true;
	}

	public MMWeekMenuTable getWeekMenuTable() {
		return weekMenuTable;
	}

	public MMExtrasTable getExtrasTable() {
		return extrasTable;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO swing worker
		// TODO handle case no config file
		try {
			data.loadData();
			weekMenuTable.refreshTableModel();
			weekMenuTable.repaint();
			extrasTable.refreshTableModel();
			extrasTable.repaint();
		} catch (JDOMException jde) {
			jde.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		try {
			data.saveData();
		} catch (FileNotFoundException fnfe) {
			JOptionPane.showMessageDialog(this, "Impossible to save data : "
					+ fnfe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(this, "Impossible to save data : "
					+ ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MenuMakerGUI gui = new MenuMakerGUI();
				gui.setVisible(true);
				gui.toFront();
			}
		});
	}
}
