/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import view.table.MMExtrasTable;
import view.table.MMWeekMenuTable;

/**
 * @author cmaurice2
 * 
 */
public class MenuMakerGUI extends JFrame {

	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = -1017764394785180674L;

	public static final String FOLDER_IMG = "/img/";
	public static final String FOLDER_CONF = "/config/";

	public static final int DEFAULT_WIDTH = 600;
	public static final int DEFAULT_HEIGHT = 600;

	public static ImageIcon ICON_PLUS;
	public static ImageIcon ICON_MINUS;
	public static ImageIcon ICON_UNIT;
	public static ImageIcon ICON_BOOK;
	public static ImageIcon ICON_SHOP;
	public static ImageIcon ICON_INGREDIENT;
	public static ImageIcon ICON_RECIPE;
	public static ImageIcon ICON_PRINT;

	private JToolBar toolBar;

	private MMWeekMenuTable weekMenuTable;
	private MMExtrasTable extrasTable;

	public MenuMakerGUI() {
		super("Menu Maker - Powered by MC");
		this.setLayout(new BorderLayout());

		loadIcons();
		buildToolbar();
		buildCenterPanel();

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
	}

	private void buildToolbar() {

		// TODO add actions

		// Manage units button
		JButton unitButton = new JButton();
		unitButton.setIcon(ICON_UNIT);
		unitButton.setToolTipText("Manage units");

		// Manage shops button
		JButton shopButton = new JButton();
		shopButton.setIcon(ICON_SHOP);
		shopButton.setToolTipText("Manage shop points");

		// Manage books button
		JButton bookButton = new JButton();
		bookButton.setIcon(ICON_BOOK);
		bookButton.setToolTipText("Manage books");

		// Manage ingredients button
		JButton ingredientButton = new JButton();
		ingredientButton.setIcon(ICON_INGREDIENT);
		ingredientButton.setToolTipText("Manage ingredients");

		// Manage recipes button
		JButton recipeButton = new JButton();
		recipeButton.setIcon(ICON_RECIPE);
		recipeButton.setToolTipText("Manage recipes");

		// Print button
		JButton printButton = new JButton();
		printButton.setIcon(ICON_PRINT);
		printButton.setToolTipText("Print week menu");

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
		JPanel weekMenuPanel = new JPanel();

		weekMenuPanel.setLayout(new BorderLayout());

		weekMenuPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
				"WeekMenu", TitledBorder.LEFT, TitledBorder.TOP));

		weekMenuTable = new MMWeekMenuTable();

		JScrollPane scrollpane = new JScrollPane(weekMenuTable);

		weekMenuPanel.add(weekMenuTable.getTableHeader(),
				BorderLayout.PAGE_START);
		weekMenuPanel.add(scrollpane, BorderLayout.CENTER);

		return weekMenuPanel;
	}

	private JPanel buildExtrasPanel() {

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());

		extrasTable = new MMExtrasTable();

		JScrollPane scrollpane = new JScrollPane(extrasTable);

		tablePanel.add(extrasTable.getTableHeader(), BorderLayout.PAGE_START);
		tablePanel.add(scrollpane, BorderLayout.CENTER);

		// Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

		JButton addExtra = new JButton();
		addExtra.setIcon(ICON_PLUS);
		MouseAdapter addAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				extrasTable.addRow();
			}
		};
		addExtra.addMouseListener(addAdapter);

		JButton removeExtra = new JButton();
		removeExtra.setIcon(ICON_MINUS);
		MouseAdapter removeAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO
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

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MenuMakerGUI gui = new MenuMakerGUI();
				gui.setVisible(true);
			}
		});

	}
}
