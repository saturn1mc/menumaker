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
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

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

	public static final int DEFAULT_WIDTH = 600;
	public static final int DEFAULT_HEIGHT = 600;
	
	public static ImageIcon ICON_PLUS;
	public static ImageIcon ICON_MINUS;
	
	private MMWeekMenuTable weekMenuTable;
	private JTable extrasTable;

	public MenuMakerGUI() {
		super("Menu Maker - Powered by MC");

		loadIcons();
		buildCenterPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	private void loadIcons(){	
		ICON_PLUS = new ImageIcon(getClass().getResource("/img/Plus-icon.png"));
		ICON_MINUS = new ImageIcon(getClass().getResource("/img/Minus-icon.png"));
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
		
		weekMenuPanel.add(weekMenuTable.getTableHeader(), BorderLayout.PAGE_START);
		weekMenuPanel.add(scrollpane, BorderLayout.CENTER);
		
		return weekMenuPanel;
	}
	
	private JPanel buildExtrasPanel() {
		
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());
		
		extrasTable = new JTable(0, 3);
		extrasTable.getColumnModel().getColumn(0).setHeaderValue("#");
		extrasTable.getColumnModel().getColumn(1).setHeaderValue("Extra");
		extrasTable.getColumnModel().getColumn(2).setHeaderValue("Comments");
		
		JScrollPane scrollpane = new JScrollPane(extrasTable);
		
		tablePanel.add(extrasTable.getTableHeader(), BorderLayout.PAGE_START);
		tablePanel.add(scrollpane, BorderLayout.CENTER);
		
		//Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		
		JButton addExtra = new JButton();
		addExtra.setIcon(ICON_PLUS);
		MouseAdapter addAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				((DefaultTableModel)extrasTable.getModel()).addRow(new String[] {"","",""});
			}
		};
		addExtra.addMouseListener(addAdapter);
		
		JButton removeExtra = new JButton();
		removeExtra.setIcon(ICON_MINUS);
		MouseAdapter removeAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//TODO
			}
		};
		removeExtra.addMouseListener(removeAdapter);
		
		buttonPanel.add(addExtra);
		buttonPanel.add(removeExtra);
		
		//Extras panel
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
