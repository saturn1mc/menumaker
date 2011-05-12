/**
 * 
 */
package view.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import javax.swing.JTable;

import model.MMIngredient;
import model.MMMenuElement;
import model.MMRecipe;
import model.MMRecipeElement;
import view.table.model.MMShopListTableModel;
import view.table.renderer.MMTableCellRenderer;

/**
 * @author cmaurice2
 * 
 */
public class MMShopListTable extends JTable {
	/**
	 * Auto-generated SVUID
	 */
	private static final long serialVersionUID = 6404860223050257081L;

	private ArrayList<MMRecipeElement> shopList;

	public MMShopListTable(MMMenuElement[] menuElements) {
		super();

		Hashtable<MMIngredient, MMRecipeElement> quantities = new Hashtable<MMIngredient, MMRecipeElement>();

		for (MMMenuElement menuElement : menuElements) {
			
			MMRecipe recipe = menuElement.getRecipe();
			
			if (recipe != null) {
				for (MMRecipeElement element : recipe.getElements()) {

					MMRecipeElement quantity = quantities.get(element
							.getIngredient());

					if (quantity == null) {
						quantity = new MMRecipeElement(element.getIngredient(),
								0);
					}

					quantity.setQuantity(quantity.getQuantity()
							+ element.getQuantity());
					quantities.put(element.getIngredient(), quantity);
				}
			}
		}

		this.shopList = new ArrayList<MMRecipeElement>(quantities.values());
		Collections.sort(shopList);

		this.setDefaultRenderer(Object.class, new MMTableCellRenderer());

		this.setModel(new MMShopListTableModel(shopList));
	}

	public ArrayList<MMRecipeElement> getShopList() {
		return shopList;
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		if (aValue != null) {
			super.setValueAt(aValue, row, column);
		}
	}
}
